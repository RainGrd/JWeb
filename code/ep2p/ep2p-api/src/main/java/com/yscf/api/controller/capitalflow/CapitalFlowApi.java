package com.yscf.api.controller.capitalflow;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.service.financial.impl.CustFundWaterServiceImpl;
/**
 * Description：<br> 
 * 资金流水接口
 * @author  heng.wang
 * @date    2015年12月30日
 * @version v1.0.0
 */
@RequestMapping("/capitalflow/capitalFlowApi")
@Controller
public class CapitalFlowApi extends EscfBaseApi{
   
	Logger logger = LoggerFactory.getLogger(CapitalFlowApi.class);
	
	@Autowired
	public CapitalFlowApi(CustFundWaterServiceImpl service) {
		super(service);
	}
  
	/**
	 * 
	 * Description：<br> 
	 * 查询资金流水
	 * @author  heng.wang
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCapitalFlow", method = RequestMethod.POST)
	@ResponseBody
	public String selectCapitalFlow(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
			List<CustFundWater> list = service.selectCapitalFlow(userId);
			if(list != null && list.size()>0){
				map.put("list",list);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询资金流水成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询资金流水没有数据",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}
