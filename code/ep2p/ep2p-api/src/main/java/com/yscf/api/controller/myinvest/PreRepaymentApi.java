/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.myinvest;

import java.util.HashMap;

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

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.myinvest.PrepaymentVo;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.business.RepaymentDto;
import com.yscf.core.service.business.impl.RepaymentServiceImpl;

/**
 * 
 * Description：<br> 
 * 提前还款接口
 * @author  Yu.Zhang
 * @date    2016年1月26日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/myinvest/preRepaymentApi")
public class PreRepaymentApi extends EscfBaseApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PreRepaymentApi(RepaymentServiceImpl service) {
		super(service);
	}
	
	@Resource(name = "repaymentService")
	private RepaymentServiceImpl repaymentService;	// 客户信息

	@Override
	public Class<?> getModel() {
		return BizReceiptTransfer.class;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 提前还款确定接口
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @param tradePwd
	 * @param userId
	 * 
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/prepayment", method = RequestMethod.POST)
	@ResponseBody
	public String prepayment(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			PrepaymentVo prepaymentVo = (PrepaymentVo) ApiUtil.convertObjectByBody(request, PrepaymentVo.class);
			repaymentService.prepayment(prepaymentVo.getBorrowId(),getCustomerId(xtoken));
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
			
		}catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 提前还款详情接口
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @param tradePwd
	 * @param userId
	 * 
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getPrepaymentInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getPrepaymentInfo(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			PrepaymentVo prepaymentVo = (PrepaymentVo) ApiUtil.convertObjectByBody(request, PrepaymentVo.class);
			RepaymentDto dto = repaymentService.getPrepaymentInfo(prepaymentVo.getBorrowId(), DateUtil.format(DateUtil.getSystemDate()));
			map.put("repaymentVo", dto);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
		}catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	

}
