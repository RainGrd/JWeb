package com.yscf.api.vo.user;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.vo.BaseVo;
/**
 * Description：<br> 
 * 验证交易密码信息记录vo
 * @author  heng.wang
 * @date    2016年1月27日
 * @version v1.0.0
 */
public class ValidateTradPwdVo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9214282751994664315L;
    
	private String pid;
	
	//客户id
	private String custId;
	
	//模块名
	private String model;
	
	//是否验证成功
	private String isValidateSucess;
	
	//结束时间
	private String endTime;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getIsValidateSucess() {
		return isValidateSucess;
	}

	public void setIsValidateSucess(String isValidateSucess) {
		this.isValidateSucess = isValidateSucess;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
