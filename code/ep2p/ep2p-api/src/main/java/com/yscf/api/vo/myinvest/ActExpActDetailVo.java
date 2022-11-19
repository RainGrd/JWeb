/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体验金卷的VO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月5日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 体验金卷的VO
 * @author  Jie.Zou
 * @date    2016年1月5日
 * @version v1.0.0
 */
public class ActExpActDetailVo {
	private String pid;
	private BigDecimal expAmount;// 体验金金额
	private String expTime;// 过期时间
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public BigDecimal getExpAmount() {
		return expAmount;
	}
	public void setExpAmount(BigDecimal expAmount) {
		this.expAmount = expAmount;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	
	
}


