/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 交易密码验证拦截器
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月28日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ConstantApi;
import com.yscf.api.util.ApiUtil;

/**
 * Description：<br> 
 * 交易密码验证拦截器
 * @author  Lin Xu
 * @date    2016年1月28日
 * @version v1.0.0
 */
public class TransactionPwdInterceptor implements HandlerInterceptor  {
	
	//获取Memcache客户端信息
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;

	/** 
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在 
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返 
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。 
     */ 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean bool = true;
		//获取request中的body值信息
		String token = ApiUtil.getHeadXtokenDecryptStr(request);
		if(StringUtil.isNotEmpty(token)){
			//获取body中的tranPKey中的交易密码主键信息
			String htranPKeyVal = ApiUtil.getHeadTranPKeyDecryptStr(request);
			String tranPKeyVal = htranPKeyVal == null ? "" : htranPKeyVal;
			if(StringUtil.isNotEmpty(tranPKeyVal)){
				//进行比较memcache中信息数据
				String memtranPK = memcachedClient.get(token + ConstantApi.JFDH);
				if(StringUtil.isNotEmpty(memtranPK) && memtranPK.equals(tranPKeyVal)){
					bool = true;
				}else{
					bool = false;
				}
			}else{
				bool = false;
			}
		}else{
			bool = false;
		}
		//进行返回结果对象信息
		if(!bool){
			//返回消息对象信息
			JsonObject jsonobject = new JsonObject();
			jsonobject.setCode("900");
			jsonobject.setStatus(false);
			jsonobject.setMessage("交易密码失效或者错误");
			String outSTR = ApiUtil.getEncryptStr(jsonobject);
			//进行对象信息输出
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.write(outSTR);
			out.flush();
			out.close();
		}
		return bool;
	}

	/** 
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操 
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像， 
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor 
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。 
     */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/** 
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
     */ 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}


