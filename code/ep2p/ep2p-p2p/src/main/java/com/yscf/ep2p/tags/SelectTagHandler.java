/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 下拉列表自定义标签
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.tags;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;

/**
 * Description：<br> 
 * 下拉列表自定义标签
 * @author  Lin Xu
 * @date    2015年11月23日
 * @version v1.0.0
 */
public class SelectTagHandler extends SimpleTagSupport implements DynamicAttributes {
	//服务层
	private SysDictionaryContentServiceImpl sysDictionaryContentServiceImpl;
	
	//静态参数
	private static final String ATTR_TEMPLATE = "%s='%s'";

	//是否是数据字典查询false表示不是，true表示数据字典查询
	private Boolean isdictory;
	//数据字典的key值
	private String dicKey;
	//结果集信息
	private List<?> itemList;
	//options的text;
	private String optText;
	//options的value
	private String optVal;
	//选中的默认值
	private String checkKey;
	//默认选择项
	private String defoption;
	//其他属性值
	private Map<String, Object> tagAttrs = new HashMap<String, Object>();
	
	
	/**
	 * Description：<br> 
	 * 进行判断是否是数据字典，然后对该对象的传入的结果集是否为空处理
	 * @author  Lin Xu
	 * @date    2015年11月23日
	 * @version v1.0.0
	 */
	private void initItemList(){
		//判断是否数据字典查询
		if(isdictory && null != dicKey && !"".equals(dicKey)){
			this.itemList = sysDictionaryContentServiceImpl.selectByDisctCode(dicKey);
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
	
	/**
	 * 根据属性名获取属性值
	 * */
	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		//参数初始化
		setNewsService();
		initItemList();
		//进行输入
		PageContext pagecontext = (PageContext) getJspContext();
		JspWriter out = pagecontext.getOut();
		//开始标签
		out.print("<select "); 
		//添加动态属性
		for (String attrName : tagAttrs.keySet()) {
			String attrDefinition = String.format(ATTR_TEMPLATE, attrName, tagAttrs.get(attrName));
			out.print(attrDefinition);
		}
		//开始标签结束符
		out.println(" >");
		//添加默认选项
		if(null != defoption && !"".equals(defoption)){
			if(null == checkKey || "".equals(checkKey)){
				out.println("<option value=\"\" selected >" + defoption + "</option>");
			}else{
				out.println("<option value=\"\" >" + defoption + "</option>");
			}
		}
		//添加子选项
		for (Object option : this.itemList) {
			 String optObj = "";
			 String text = "";
			 String value = "";
			 //进行判断传入的参数是否是对象或者列表属性
			 if(null != optText && !"".equals(optText) && null != optVal && !"".equals(optVal)){
				 text = getFieldValueByName(optText,option) == null ? "" : getFieldValueByName(optText,option).toString();
				 value = getFieldValueByName(optVal,option) == null ? "" : getFieldValueByName(optVal,option).toString();
			 }
			 else if((null == optText || "".equals(optText)) && (null == optVal || "".equals(optVal))){
				 text = option.toString();
				 value = option.toString();
			 }else{
				 if((null == optText || "".equals(optText)) && (null != optVal || !"".equals(optVal))){
					 text = getFieldValueByName(optVal,option) == null ? "" : getFieldValueByName(optVal,option).toString();
					 value = text;
				 }
				 if((null != optText || !"".equals(optText)) && (null != optVal || !"".equals(optVal))){
					 text = getFieldValueByName(optText,option) == null ? "" : getFieldValueByName(optText,option).toString();
					 value = text; 
				 }
			 }
			 
			//添加下拉列表信息
			if(null != text && !"".equals(text) && null != value && !"".equals(value)){
				 if(null != checkKey && !"".equals(checkKey) && value.equals(checkKey)){
					 optObj = "<option value=\""+ value +"\" selected \">" + text + "</option>";
				 }else{
					 if(null != text && !"".equals(text) && null != value && !"".equals(value)){
						 optObj = "<option value=\""+ value +"\">" + text + "</option>";
					 }
				 }
			}
			
			 out.println(optObj); 
		}
		//结束标签
		out.print("</select>");
	}

	

	@Override
	public void setDynamicAttribute(String uri, String name, Object value)
			throws JspException {
		tagAttrs.put(name, value);
	}

	
//**********************set and get *****************************************************
	public Boolean getIsdictory() {
		return isdictory;
	}

	public void setIsdictory(Boolean isdictory) {
		this.isdictory = isdictory;
	}

	public String getDicKey() {
		return dicKey;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}

	public List<?> getItemList() {
		return itemList;
	}

	public void setItemList(List<?> itemList) {
		this.itemList = itemList;
	}

	public String getOptText() {
		return optText;
	}

	public void setOptText(String optText) {
		this.optText = optText;
	}

	public String getOptVal() {
		return optVal;
	}

	public void setOptVal(String optVal) {
		this.optVal = optVal;
	}

	public String getCheckKey() {
		return checkKey;
	}

	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}

	public Map<String, Object> getTagAttrs() {
		return tagAttrs;
	}

	public void setTagAttrs(Map<String, Object> tagAttrs) {
		this.tagAttrs = tagAttrs;
	}

	public String getDefoption() {
		return defoption;
	}

	public void setDefoption(String defoption) {
		this.defoption = defoption;
	}
	
	

}


