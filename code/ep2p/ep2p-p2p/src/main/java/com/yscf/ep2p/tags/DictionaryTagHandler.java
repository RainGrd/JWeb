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
 * 2015年11月26日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;

/**
 * Description：<br> 
 * 数据字典值显示标签
 * @author  Lin Xu
 * @date    2015年11月26日
 * @version v1.0.0
 */
public class DictionaryTagHandler extends SimpleTagSupport {
	
	//服务层
	private SysDictionaryContentServiceImpl sysDictionaryContentServiceImpl;
	
	//结果集信息
	private List<SysDictionaryContent> itemList;
	//数据字典编码值
	private String codekey;
	//数据key值
	private String keyval;
	
	
	
	/**
	 * Description：<br> 
	 * 进行判断是否是数据字典，然后对该对象的传入的结果集是否为空处理
	 * @author  Lin Xu
	 * @date    2015年11月23日
	 * @version v1.0.0
	 */
	private void initItemList(){
		//判断是否数据字典查询
		if(null != codekey && !"".equals(codekey)){
			this.setItemList(sysDictionaryContentServiceImpl.selectByDisctCode(codekey));
		}
	}
	
	/**
	 * Description：<br> 
	 * 注入service层
	 * @author  Lin Xu
	 * @date    2015年11月24日
	 * @version v1.0.0
	 */
	private void setNewsService() {
        PageContext pageContext = (PageContext) this.getJspContext();
        ServletContext servletContext = pageContext.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.sysDictionaryContentServiceImpl =  (SysDictionaryContentServiceImpl) wac.getBean("sysDictionaryContentService");
    }

	@Override
	public void doTag() throws JspException, IOException {
		//初始化数据字典值
		setNewsService();
		initItemList();
		
		//进行输入
		PageContext pagecontext = (PageContext) getJspContext();
		JspWriter out = pagecontext.getOut();
		
		//进行显示值
		if(null != itemList && itemList.size() > 0 && null != keyval && !"".equals(keyval)){
			for(SysDictionaryContent sdc : itemList){
				String dictContCode = sdc.getDictContCode();
				String dictContName = sdc.getDictContName();
				if(null != dictContCode && dictContCode.equals(keyval)){
					out.print(dictContName);
					break;
				}
			}
		}
	}


	//*******************set 和   get **********************
	public String getCodekey() {
		return codekey;
	}

	public void setCodekey(String codekey) {
		this.codekey = codekey;
	}

	public String getKeyval() {
		return keyval;
	}

	public void setKeyval(String keyval) {
		this.keyval = keyval;
	}

	public List<?> getItemList() {
		return itemList;
	}

	public void setItemList(List<SysDictionaryContent> itemList) {
		this.itemList = itemList;
	}
	
}


