/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标接口接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月25日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.json.gson.GsonUtil;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.google.gson.Gson;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.persionalcenter.AutoBiddingVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.ptp.autobidding.AutoBidding;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;
import com.yscf.core.service.autobidding.impl.AutoBiddingQueueServiceImpl;
import com.yscf.core.service.autobidding.impl.AutoBiddingServiceImpl;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;

/**
 * Description：<br> 
 * 自动投标接口接口
 * @author  Lin Xu
 * @date    2015年12月25日
 * @version v1.0.0
 */
@RequestMapping("/personalCenter/autoTenderApi")
@Controller
public class AutoTenderApi extends EscfBaseApi {
	
	Logger logger = LoggerFactory.getLogger(AutoTenderApi.class);
	
	//自动投标服务
	@Resource(name = "autoBiddingServiceImpl")
	private AutoBiddingServiceImpl autoBiddingServiceImpl;
	
	//自动投标增加排队序列服务
	@Resource(name = "autoBiddingQueueServiceImpl")
	private AutoBiddingQueueServiceImpl autoBiddingQueueServiceImpl;
	
	
	@Autowired
	public AutoTenderApi(UserCenterServiceImpl service) {
		super(service);
	}
	
	
	
	/**
	 * Description：<br> 
	 * 查询对应的自动投标列表
	 * @author  Lin Xu
	 * @date    2015年12月25日
	 * @version v1.0.0
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAllAutoTender",method=RequestMethod.POST)
	@ResponseBody
	public String queryAllAutoTender(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerVo tcustomer = getCustomer(xtoken);
			//获取个人自动投标的列表
			String userid = tcustomer.getPid();
			List<AutoBidding> ablist = autoBiddingServiceImpl.selectAutoBiddingList(userid);
			remap.put("autolist", ablist);
			jsonObject.setCode(ApiCode.HTTP_CODE_200);
			jsonObject.setMessage("查询成功");
			jsonObject.setResult(remap);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("用户App登录失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	/**
	 * Description：<br> 
	 * 添加自动投标的项目信息
	 * @author  Lin Xu
	 * @date    2015年12月26日
	 * @version v1.0.0
	 * @param request
	 * @param abvo
	 * @return
	 */
	@RequestMapping(value="/addAutoTender",method=RequestMethod.POST)
	@ResponseBody
	public String addAutoTender(HttpServletRequest request,HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String tuserid = getCustomerId(xtoken);
			//检查是否达到最高纪录数
			List<AutoBidding> ablist = autoBiddingServiceImpl.selectAutoBiddingList(tuserid);
			if(null != ablist && ablist.size() > 3){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"自动投标纪录数已经达到上限",false);
			}else{
				String reqStr = ApiUtil.getBodyDecryptStr(request);
				Gson gson = GsonUtil.create();
				AutoBiddingVo abvo = gson.fromJson(reqStr, AutoBiddingVo.class);
				if(null != abvo && StringUtil.isNotEmpty(tuserid)){
					AutoBidding abmodel = new AutoBidding();
					abmodel = ConvertObjectUtil.convertObject(abvo, AutoBidding.class);
					abmodel.setPid(abmodel.getPK());
					abmodel.setCustomerId(tuserid);
					abmodel.setCreateUser(tuserid);
					abmodel.setLastUpdateUser(tuserid);
					//新增投标-启动-检查是否是有启动状态的数据   失效以前的启动的数据
					if(abmodel.getAutostatus() == 1){
						boolean bool = autoBiddingServiceImpl.updateInvalidStartBidding(tuserid);
						if(bool){
							//自动投标成功,并加入到队列中
							autoBiddingServiceImpl.insertSelective(abmodel);
							//添加对象
							AutoBiddingQueue abqmodel = new AutoBiddingQueue();
							abqmodel.setPid(abqmodel.getPK());
							abqmodel.setCustomerId(tuserid);
							abqmodel.setAutoBiddingId(abmodel.getPid());
							abqmodel.setCreateUser(tuserid);
							abqmodel.setLastUpdateUser(tuserid);
							autoBiddingQueueServiceImpl.insert(abqmodel);
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"新建启动自动投标成功",true);
						}else{
							//历史已启动的信息未成功禁用
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"添加自动投标信息时出现出队错误",false);
						}
					//新增投标-禁用-直接加入现有的自动投标信息
					}else{
						//自动投标成功
						autoBiddingServiceImpl.insertSelective(abmodel);
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"新建禁用自动投标成功",true);
					}
				}else{
					//数据上传异常
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"数据解析错误",false);
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("前台==添加自动投标信息异常：" + e.getMessage());
				e.printStackTrace();
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"新增自动投标信息服务器异常",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	/**
	 * Description：<br> 
	 * 修改自动投标的项目信息
	 * @author  Lin Xu
	 * @date    2015年12月26日
	 * @version v1.0.0
	 * @param request
	 * @param token
	 * @param abvo
	 * @return
	 */
	@RequestMapping(value="/editAutoTender",method=RequestMethod.POST)
	@ResponseBody
	public String editAutoTender(HttpServletRequest request,HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userid = getCustomerId(xtoken);
			String reqStr = ApiUtil.getBodyDecryptStr(request);
			Gson gson = GsonUtil.create();
			AutoBiddingVo abvo = gson.fromJson(reqStr, AutoBiddingVo.class);
			if(null != abvo && StringUtil.isNotEmpty(userid)){
				AutoBidding abmodel = autoBiddingServiceImpl.selectKeyVersionAutoBidding(userid, abvo.getPid(), abvo.getVersion());
				if(null != abmodel){
					abmodel = ConvertObjectUtil.convertObject(abvo, AutoBidding.class);
					abmodel.setLastUpdateTime(DateUtil.format(new Date()));
					//修改自动投标--启动--把以前启动的进行停止重新排队
					if(abmodel.getAutostatus() == 1){
						//移除排队及清除以前启动信息
						boolean bool = autoBiddingServiceImpl.updateInvalidStartBidding(userid);
						if(bool){
							//修改投标信息
							autoBiddingServiceImpl.updateByPrimaryKeySelective(abmodel);
							//重新排队
							AutoBiddingQueue abqmodel = new AutoBiddingQueue();
							abqmodel.setPid(abqmodel.getPK());
							abqmodel.setCustomerId(userid);
							abqmodel.setAutoBiddingId(abmodel.getPid());
							abqmodel.setCreateUser(userid);
							abqmodel.setLastUpdateUser(userid);
							autoBiddingQueueServiceImpl.insert(abqmodel);
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"修改并启动自动投标信息成功",true);
						}else{
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"自动投标信息被锁定，修改失败",false);
						}
					//修改自动投标--禁用--不做任何操作
					}else{
						//移除排队
						List<AutoBiddingQueue> selabqlist = autoBiddingQueueServiceImpl.selectByPrimaryUbdId(userid,abmodel.getPid());
						if(null != selabqlist && selabqlist.size() > 0){
							for(AutoBiddingQueue abqmodel : selabqlist){
								autoBiddingQueueServiceImpl.updateRemoveQueue(abqmodel);
							}
						}
						//修改投标信息
						autoBiddingServiceImpl.updateByPrimaryKeySelective(abmodel);
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"修改自动投标信息成功",true);
					}
				}else{
					//数据上传异常
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"自动投标信息已过期，请重新获取对应自动投标信息",false);
				}
			}else{
				//数据上传异常
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"传入的自动投标信息错误",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("前台==添加自动投标信息异常：" + e.getMessage());
				e.printStackTrace();
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"修改自动投标信息服务器异常",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	
	/**
	 * Description：<br> 
	 * 删除自动投标信息数据
	 * @author  Lin Xu
	 * @date    2015年12月11日
	 * @version v1.0.0
	 * @param request
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/deleteAutoTender",method=RequestMethod.POST)
	@ResponseBody
	public String deleteAutoTender(HttpServletRequest request,HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userid = getCustomerId(xtoken);
			AutoBiddingVo abvo =  (AutoBiddingVo)ApiUtil.convertObjectByBody(request, AutoBiddingVo.class);
			String pid = abvo.getPid();
			if(StringUtil.isNotEmpty(pid) && StringUtil.isNotEmpty(userid)){
				AutoBidding delabmodel = (AutoBidding) autoBiddingServiceImpl.selectByPrimaryKey(pid);
				if(null != delabmodel){
					delabmodel.setStatus("0");
					delabmodel.setLastUpdateUser(userid);
					autoBiddingServiceImpl.updateByPrimaryKeySelective(delabmodel);
					//移除排队信息
					List<AutoBiddingQueue> selabqlist = autoBiddingQueueServiceImpl.selectByPrimaryUbdId(userid, pid);
					if(null != selabqlist && selabqlist.size() > 0){
						for(AutoBiddingQueue abqmodel : selabqlist){
							autoBiddingQueueServiceImpl.updateRemoveQueue(abqmodel);
						}
					}
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"自动投标信息删除成功",true);
				}else{
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"自动投标信息不存在",false);
				}
			}else{
				//数据上传异常
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"数据参数异常，无法解析",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("前台==添加自动投标信息异常：" + e.getMessage());
				e.printStackTrace();
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"删除自动投标，服务器异常",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	

}


