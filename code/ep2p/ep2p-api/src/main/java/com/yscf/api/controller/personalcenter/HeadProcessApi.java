/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 个人头像信息处理API
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月28日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUpDownFileUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.system.FileConfig;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.system.impl.SysUserServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;

/**
 * Description：<br> 
 * 个人头像信息处理API
 * @author  Lin Xu
 * @date    2015年12月28日
 * @version v1.0.0
 */
@RequestMapping("/personalCenter/headProcessApi")
@Controller
public class HeadProcessApi extends EscfBaseApi {

	Logger logger = Logger.getLogger(HeadProcessApi.class);
	
	//存放头像信息
	private static String CURRENTLY_PATH = "upload/currently/avatar/";
	
	@Autowired
	public HeadProcessApi(SysUserServiceImpl service) {
		super(service);
	}
	
	//客户资料明细
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;

	//文档配置对象
	@Resource(name="fileConfig")
	private FileConfig fileconfig;

	/**
	 * Description：<br> 
	 * 手机APP上传头像信息
	 * @author  Lin Xu
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploadHeadImage",method=RequestMethod.POST)
	@ResponseBody
	public String saveUploadHeadImage(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			if(StringUtil.isNotEmpty(xtoken)){
				//读取上传中的文件信息并保存
				String basePath = fileconfig.getFileRoot() + "/";
				String savePath = CURRENTLY_PATH;
				String rflname = getCustomer(xtoken).getPhoneNo() + "_currently_" + System.currentTimeMillis() + ".jpg";
				BufferedImage bimage = ImageIO.read(request.getInputStream());
				String saveImaURL = ApiUpDownFileUtil.saveUploadImage(bimage, basePath, savePath, rflname);
				if(StringUtil.isNotEmpty(saveImaURL)){
					//保存成功之后 通过cusTomerService 修改信息
					String imageURL = saveImaURL;
					if(StringUtil.isNotEmpty(imageURL)){
						CusTomer avatarimg = new CusTomer();
						avatarimg.setPid(xtoken);
						avatarimg.setImageUrl(imageURL);
						avatarimg.setLastUpdateUser(xtoken);
						avatarimg.setLastUpdateTime(DateUtil.format(new Date()));
						cusTomerServiceImpl.updateByPrimaryKeySelective(avatarimg);
						//把修改的对象信息返回客户端
						remap.put("customer", avatarimg);
						jsonobject.setResult(remap);
						processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"头像更新成功",true);
					}else{
						processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"图片保存地址错误",false);
					}
				}else{
					processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"保存头像地址不存在",false);
				}
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"访问数据参数异常",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("上传个人头像信息异常："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"上传个人头像，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);
	}


}


