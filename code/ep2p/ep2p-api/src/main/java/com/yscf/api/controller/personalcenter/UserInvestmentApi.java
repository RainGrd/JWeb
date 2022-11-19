/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户个人中心我的投资中的各项列表信息数据
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月26日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.common.util.PageInfoUtil;
import com.yscf.core.model.ptp.investment.DueInDetailModel;
import com.yscf.core.model.ptp.investment.InvestDueInModel;
import com.yscf.core.model.ptp.investment.InvestmentInfoModel;
import com.yscf.core.model.ptp.investment.InviteTendersModel;
import com.yscf.core.model.ptp.investment.TransferTendersMode;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;

/**
 * Description：<br> 
 * 用户个人中心我的投资中的各项列表信息数据
 * @author  Lin Xu
 * @date    2016年1月26日
 * @version v1.0.0
 */
@RequestMapping("/personalCenter/userInvestmentApi")
@Controller
public class UserInvestmentApi extends EscfBaseApi {
	
	//日志处理
	private Logger logger = Logger.getLogger(UserInvestmentApi.class);
	
	@Autowired
	public UserInvestmentApi(UserCenterServiceImpl service) {
		super(service);
	}
	
	/**
	 * Description：<br> 
	 * 获取我的投资-待收中的投标信息
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectDueinBorrow",method=RequestMethod.POST)
	@ResponseBody
	public String selectDueinBorrow(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String token = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(token);
			if(StringUtil.isNotEmpty(token) && StringUtil.isNotEmpty(userId)){
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("customerId", userId);
				//获取开始时间和结束时间，每页显示条数和当前页
				PageInfo pageinfo = new PageInfo();
				InvestDueInModel inDuModel = (InvestDueInModel) ApiUtil.convertObjectByBody(request, InvestDueInModel.class);
				if(null != inDuModel){
					pageinfo = PageInfoUtil.getIncePageInfo(inDuModel.getPagesize(), inDuModel.getPage());
					String startTime = inDuModel.getStartInvestmentTime();
					String endTime = inDuModel.getEndInvestmentTime();
					Integer tranflag = inDuModel.getTransfStatus();
					if(StringUtil.isNotEmpty(startTime)){
						param.put("startInvestmentTime", startTime);
					}
					if(StringUtil.isNotEmpty(endTime)){
						param.put("endInvestmentTime", endTime);
					}
					if(null != tranflag){
						param.put("transfStatus", tranflag+"");
					}
				}
				UserCenterServiceImpl ucenterService = (UserCenterServiceImpl) getService();
				PageList<InvestDueInModel> duemList = ucenterService.selectDueinBorrow(param, pageinfo);
				//返回结果集信息
				remap.put("tatol", duemList.getPaginator().getTotalCount());
				remap.put("reList", duemList);
				jsonobject.setResult(remap);
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"获取数据信息成功",false);
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的待收中，参数异常",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("API获取用户的待收中："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的待收中，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);	
	}
	
	/**
	 * Description：<br> 
	 * 查询我的投资-招标中投标信息
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectInviteTendersInfo",method=RequestMethod.POST)
	@ResponseBody
	public String selectInviteTendersInfo(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String token = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(token);
			if(StringUtil.isNotEmpty(token) && StringUtil.isNotEmpty(userId)){
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("customerId", userId);
				//获取开始时间和结束时间，每页显示条数和当前页
				PageInfo pageinfo = new PageInfo();
				InviteTendersModel inTenModel = (InviteTendersModel) ApiUtil.convertObjectByBody(request, InviteTendersModel.class);
				if(null != inTenModel){
					pageinfo = PageInfoUtil.getIncePageInfo(inTenModel.getPagesize(), inTenModel.getPage());
					String startTime = inTenModel.getStartInvestmentTime();
					String endTime = inTenModel.getEndInvestmentTime();
					if(StringUtil.isNotEmpty(startTime)){
						param.put("startInvestmentTime", startTime);
					}
					if(StringUtil.isNotEmpty(endTime)){
						param.put("endInvestmentTime", endTime);
					}
				}
				UserCenterServiceImpl ucenterService = (UserCenterServiceImpl) getService();
				PageList<InviteTendersModel> duemList = ucenterService.selectInviteTendersInfo(param, pageinfo);
				//返回结果集信息
				remap.put("tatol", duemList.getPaginator().getTotalCount());
				remap.put("reList", duemList);
				jsonobject.setResult(remap);
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"获取数据信息成功",false);
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的招标中，参数异常",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("API获取用户的招标中异常："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的招标中，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);	
	}
	
	/**
	 * Description：<br> 
	 * 查询我的投资-转让投标信息
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectTransferInfo",method=RequestMethod.POST)
	@ResponseBody
	public String selectTransferInfo(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String token = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(token);
			if(StringUtil.isNotEmpty(token) && StringUtil.isNotEmpty(userId)){
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("customerId", userId);
				//获取开始时间和结束时间，每页显示条数和当前页
				PageInfo pageinfo = new PageInfo();
				TransferTendersMode tranTenModel = (TransferTendersMode) ApiUtil.convertObjectByBody(request, TransferTendersMode.class);
				if(null != tranTenModel){
					pageinfo = PageInfoUtil.getIncePageInfo(tranTenModel.getPagesize(), tranTenModel.getPage());
					String startTime = tranTenModel.getStartInvestmentTime();
					String endTime = tranTenModel.getEndInvestmentTime();
					if(StringUtil.isNotEmpty(startTime)){
						param.put("startInvestmentTime", startTime);
					}
					if(StringUtil.isNotEmpty(endTime)){
						param.put("endInvestmentTime", endTime);
					}
				}
				UserCenterServiceImpl ucenterService = (UserCenterServiceImpl) getService();
				PageList<TransferTendersMode> duemList = ucenterService.selectTransferInfo(param, pageinfo);
				//返回结果集信息
				remap.put("tatol", duemList.getPaginator().getTotalCount());
				remap.put("reList", duemList);
				jsonobject.setResult(remap);
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"获取数据信息成功",false);
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的转让，参数异常",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("API获取用户的转让异常："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的转让，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);	
	}
	
	/**
	 * Description：<br> 
	 * 查询我的投资-已结清信息数据
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectInvestmentInfo",method=RequestMethod.POST)
	@ResponseBody
	public String selectInvestmentInfo(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String token = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(token);
			if(StringUtil.isNotEmpty(token) && StringUtil.isNotEmpty(userId)){
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("customerId", userId);
				//获取开始时间和结束时间，每页显示条数和当前页
				PageInfo pageinfo = new PageInfo();
				InvestmentInfoModel inverTModel = (InvestmentInfoModel) ApiUtil.convertObjectByBody(request, InvestmentInfoModel.class);
				if(null != inverTModel){
					pageinfo = PageInfoUtil.getIncePageInfo(inverTModel.getPagesize(), inverTModel.getPage());
					String startTime = inverTModel.getStartInvestmentTime();
					String endTime = inverTModel.getEndInvestmentTime();
					if(StringUtil.isNotEmpty(startTime)){
						param.put("startInvestmentTime", startTime);
					}
					if(StringUtil.isNotEmpty(endTime)){
						param.put("endInvestmentTime", endTime);
					}
				}
				UserCenterServiceImpl ucenterService = (UserCenterServiceImpl) getService();
				PageList<InvestmentInfoModel> duemList = ucenterService.selectInvestmentInfo(param, pageinfo);
				//返回结果集信息
				remap.put("tatol", duemList.getPaginator().getTotalCount());
				remap.put("reList", duemList);
				jsonobject.setResult(remap);
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"获取数据信息成功",false);
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的已完结，参数异常",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("API获取用户的已完结异常："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的已完结，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);	
	}
	
	
	/**
	 * Description：<br> 
	 * 查询我的投资-待收项目详细信息
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectDueInDetailList",method=RequestMethod.POST)
	@ResponseBody
	public String selectDueInDetailList(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String token = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(token);
			if(StringUtil.isNotEmpty(token) && StringUtil.isNotEmpty(userId)){
				//获取开始时间和结束时间，每页显示条数和当前页
				DueInDetailModel dueInTModel = (DueInDetailModel) ApiUtil.convertObjectByBody(request, DueInDetailModel.class);
				if(null != dueInTModel && StringUtil.isNotEmpty(dueInTModel.getPid())){
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("userId", userId);
					param.put("borrowId", dueInTModel.getPid());
					UserCenterServiceImpl ucenterService = (UserCenterServiceImpl) getService();
					DueInDetailModel redueInMod = ucenterService.selectDueInDetailList(param);
					if(null != redueInMod){
						//返回结果集信息
						remap.put("redueinm", redueInMod);
						jsonobject.setResult(remap);
						processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"获取数据信息成功",false);
					}else{
						processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"无法找到对应的待收详细",false);
					}
				}else{
					processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"传入的借款ID数据为空",false);
				}
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的待收项目详细信息，参数异常",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("API获取用户的待收项目详细信息异常："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"API获取用户的待收项目详细信息，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);	
	}
	
	
}


