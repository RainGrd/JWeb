/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 满足自动投标人默认列表
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月18日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.autobidding;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 满足自动投标人默认列表
 * @author  Lin Xu
 * @date    2015年12月18日
 * @version v1.0.0
 */
public class AutoBiddPersonModel extends BaseEntity{
	
	private static final long serialVersionUID = 6377067714296786273L;
	
	//pid
	private String pid;
	//用户id
	private String customerId;
	//自动投标id
	private String autoPid;
	//自动投标金额
	private BigDecimal automoney;
	//投资的金额类型  0 比例投资，1 固定投资
	private String automoneyType;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAutoPid() {
		return autoPid;
	}
	public void setAutoPid(String autoPid) {
		this.autoPid = autoPid;
	}
	public BigDecimal getAutomoney() {
		return automoney;
	}
	public void setAutomoney(BigDecimal automoney) {
		this.automoney = automoney;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAutomoneyType() {
		return automoneyType;
	}
	public void setAutomoneyType(String automoneyType) {
		this.automoneyType = automoneyType;
	}
	
}


