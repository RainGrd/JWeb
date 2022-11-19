/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * escf3.0 移动端基础API
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.IBaseService;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.achievo.framework.web.api.BaseApi;
import com.yscf.api.vo.system.FileConfig;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.user.impl.CusTomerDtoServiceImpl;

/**
 * Description：<br> 
 * escf3.0 移动端基础API
 * @author  JingYu.Dai
 * @date    2015年12月23日
 * @version v1.0.0
 */
public abstract class EscfBaseApi extends BaseApi{

	//存放个人头像信息接口表
	private static String CURRENTLY_PATH = "upload/currently/avatar/";
	
	//文档配置对象
	@Resource(name="fileConfig")
	private FileConfig fileconfig;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name = "cusTomerDtoService")
	private CusTomerDtoServiceImpl cusTomerDtoService;	// 客户信息
	
	@Autowired
	public EscfBaseApi(IBaseService<BaseEntity, String> service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BaseEntity.class;
	}
	
	/**
	 * Description: <br>
	 * 获取Request对象
	 * @author Simon.Hoo
	 * @date 2015年6月9日
	 * @version v1.0.0
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * Description：<br> 
	 * 通过token值查询对应的用户信息
	 * @author  Lin Xu
	 * @date    2015年12月25日
	 * @version v1.0.0
	 * @param token
	 * @return
	 */
	public CustomerVo getCustomer(String token){
		CustomerVo custvo = null;
		try {
			custvo = memcachedClient.get(token);
			if(null == custvo){
				CustomerDto customerVo = cusTomerDtoService.getCustomerDtoByPid(token);
				custvo = ConvertObjectUtil.convertObject(customerVo, CustomerVo.class);
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return custvo;
	}
	
	/**
	 * Description：<br> 
	 * 获取用户ID
	 * @author  Lin Xu
	 * @date    2015年12月26日
	 * @version v1.0.0
	 * @param token
	 * @return
	 */
	public String getCustomerId(String token){
		CustomerVo csm = getCustomer(token);
		if(null != csm){
			return csm.getPid();
		}else{
			return null;
		}
	}
	
	/**
	 * Description：<br> 
	 * 处理结果状态
	 * @author  Lin Xu
	 * @date    2015年12月26日
	 * @version v1.0.0
	 * @param o
	 * @param code
	 * @param msg
	 */
	public void processResultStatus(JsonObject o,String code,String msg,Boolean bool){
		if(null != o){
			if(StringUtil.isNotEmpty(code)){
				o.setCode(code);
			}
			if(StringUtil.isNotEmpty(msg)){
				o.setMessage(msg);
			}else{
				o.setMessage("操作异常！");
			}
			if(null != bool){
				o.setStatus(bool);
			}
		}
	}
	
	
	/**
	 * Description：<br> 
	 * 保存个人设置中的头像信息
	 * @author  Lin Xu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param bimage
	 * @param customerName
	 * @return
	 */
	public String saveHeadSculpture(BufferedImage bimage,String customerName){
		String imageUrl = "";
		// 获取request对象
		HttpServletRequest request = getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			String rootPath = fileconfig.getFileRoot();
			// 说明是绝对路径
			String juploadPath = rootPath + "/" + CURRENTLY_PATH;
			String filename = customerName + "_currently" + ".jpg";
			File jfile = new File(juploadPath);
			if (!jfile.exists()) {
				jfile.mkdirs();
			}
			//写入的文件的绝对路径
			String imagePath = juploadPath + filename;
			ImageIO.write(bimage, "jpg", new File(imagePath));
			//保存成功后进行返回文件的路径信息、
			imageUrl = CURRENTLY_PATH + filename;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageUrl;
	}
	
	public String getEncryptKey(){
		return fileconfig.getEncryptKey();
	}
	
	

}


