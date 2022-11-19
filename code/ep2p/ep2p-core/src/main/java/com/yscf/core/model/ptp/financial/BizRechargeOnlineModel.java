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
 * 2015年10月30日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;

/**
 * Description：<br>
 * 线上充值客户列表VO
 * 
 * @author JunJie.Liu
 * @date 2015年10月30日
 * @version v1.0.0
 */
public class BizRechargeOnlineModel {
	// 客户名
	private String customerName;
	// 真实姓名
	private String realName;
	// 手机号码
	private String phoneNo;
	// 充值渠道
	private String rechargeType;
	// 充值时间
	private String rechargeTime;
	// 充值金额
	private BigDecimal rechargeAmount;
	// 订单号
	private Integer orderCode;
	// 审核状态
	private String statusName;
	// 处理人
	private String createUser;
	// 备注
	private String remark;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(String rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}
	
	public String getStatusNameByCode(String code){
		if("1".equals(code)){
			return "待付款";
		}else if("2".equals(code)){
			return "待充值";
		}else if("3".equals(code)){
			return "付款失败";
		}else if("4".equals(code)){
			return "充值成功";
		}else if("5".equals(code)){
			return "系统充值";
		}else if("6".equals(code)){
			return "手动补单";
		}else if("7".equals(code)){
			return "错误修正";
		}
		return "";

	}

}
