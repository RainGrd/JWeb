/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月19日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 理财产品介绍 控制器
 * 
 * @author shiliang.feng
 * @date 2015年10月19日
 * @version v1.0.0
 */
public class BizFinanceProductsController extends EscfBaseController {

	public BizFinanceProductsController(BizFinanceProductsServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizFinanceProducts.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据主键查询 理财产品介绍表
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getFinanceProductsByIds")
	@ResponseBody
	public ModelAndView getFinanceProductsByIds(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("business/borrow/borrow_cus_list");
	}

	/**
	 * 
	 * Description：<br>
	 * 根据借款信息表id查询 理财产品介绍表
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBizFinanceProductsByBorrowId")
	@ResponseBody
	public ModelAndView getBizFinanceProductsByBorrowId(
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("business/borrow/borrow_cus_list");
	}
}
