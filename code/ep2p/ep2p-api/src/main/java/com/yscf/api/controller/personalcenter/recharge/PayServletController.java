/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 支付平台通知消息通用功能的处理类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter.recharge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.achievo.framework.service.IBaseService;
import com.achievo.framework.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.ptp.base.BeCurrentModel;
import com.yscf.core.service.financial.impl.CustRechargeServiceImpl;
import com.yscf.core.service.financial.impl.llpay.vo.RetBean;

/**
 * Description：<br> 
 * 支付平台通知消息通用功能的处理类
 * @author  Jie.Zou
 * @date    2015年11月17日
 * @version v1.0.0
 */
public abstract class PayServletController extends EscfBaseApi  {
	
	private Logger logger = LoggerFactory.getLogger(PayServletController.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Autowired
	public PayServletController(IBaseService service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BeCurrentModel.class;
	}
	
	@Resource(name = "custRechargeServiceImpl")
	public CustRechargeServiceImpl custRechargeServiceImpl;
	
	/**
	 * 
	 * Description：<br> 
	 * 获取当前的支付平台类别
	 * @author  Jie.Zou
	 * @date    2015年11月17日
	 * @version v1.0.0
	 * @return
	 */
	protected abstract String getPaySystemType();
	
	/**
	 * 
	 * Description：<br> 
	 * 获取日志处理器
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @return
	 */
	protected abstract Logger getLogger();
	
	/**
	 * 
	 * Description：<br> 
	 * 获取跳转页面URL,不带任务页面参数
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @return
	 */
	protected String getRechargeResultURLRoot(){
		return null;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 所有支付平台统一使用相同的充值结果页面
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param feedbackData
	 * @return
	 */
	protected String getRechargeResultURL(Map<String, String> feedbackData){
		return null;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 通知消息处理完毕后，各个不用的支付平台需要处理不同的跳转方式
	 * @author  Jie.Zou
	 * @date    2015年11月17日
	 * @version v1.0.0
	 * @param req 请求对象
	 * @param resp 响应对象
	 * @param result 对通知的处理结果
	 * @param feedbackData 通知的数据
	 */
	protected abstract void postHandle(HttpServletRequest req,HttpServletResponse resp,String result,Map<String,String> feedbackData) throws IOException;
	
	@RequestMapping("/doPost")
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
		Map<String,String> feedbackData = this.getFeedbackData(req);
//		feedbackData.put("oid_partner", "201509141000497504");
//		feedbackData.put("dt_order", "20160223160220");
//		feedbackData.put("no_order", "20160223-2-277");
//		feedbackData.put("oid_paybill", "2016022381782184");
//		feedbackData.put("money_order", "0.01");
//		feedbackData.put("result_pay", "SUCCESS");
//		feedbackData.put("settle_date", "20160223");
//		feedbackData.put("pay_type", "D");
//		feedbackData.put("bank_code", "01040000");
//		feedbackData.put("sign_type", "MD5");
//		feedbackData.put("sign", "4cc96946134aadd92acc02c4c99f9253");
		if(Constant.LL_PAY.equals(this.getPaySystemType())){
			feedbackData = readReqStr(req);
		}
		String result = custRechargeServiceImpl.doRechargeFeedback(feedbackData, this.getPaySystemType(), DateUtil.getSystemDate());
		if(Constant.LL_PAY.equals(this.getPaySystemType())){
			llpayHandle(result, resp);
		}
		this.postHandle(req, resp, result, feedbackData);
	}
	
	private Map<String,String> getFeedbackData(HttpServletRequest req){
		Map<String,String> paramMap = new HashMap<String,String>();
		Enumeration<String> paramNames = req.getParameterNames();
		while(paramNames.hasMoreElements()){
			String name = paramNames.nextElement();
			paramMap.put(name, getParamFromRequest(name, req));
		}
		return paramMap;
	}
	
	private String getParamFromRequest(String key,HttpServletRequest request){
		if(request != null){
			String val = request.getParameter(key);
			if(val != null){
				return val.trim();
			}
		}
		return "";
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 处理充值结果返回给第三方
	 * @author  Jie.Zou
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param result
	 * @param resp
	 */
	private void llpayHandle(String result,HttpServletResponse resp){
		try {
			if(Constant.PAY_OK.equals(result)||Constant.PAY_FAILED.equals(result)){
				RetBean retBean = new RetBean();
				retBean.setRet_code("0000");
		        retBean.setRet_msg("交易成功");
		        resp.getWriter().write(JSON.toJSONString(retBean));
		        resp.getWriter().flush();
			}
		} catch (IOException e) {
			if(logger.isDebugEnabled()){
				logger.debug("充值结果返回第三方异常："+e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 从request中读取数据流
	 * @author  Jie.Zou
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	private Map<String, String> readReqStr(HttpServletRequest request)
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(request
                    .getInputStream(), "utf-8"));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (null != reader)
                {
                    reader.close();
                }
            } catch (IOException e)
            {

            }
        }
        Map<String, String> map = new HashMap<String, String>();
        JSONObject reqObj = JSON.parseObject(sb.toString());
        for(String k : reqObj.keySet()){  
            String v = reqObj.get(k).toString();   
            map.put(k.toString(), v);  
        }
        return map;
    }
	
}


