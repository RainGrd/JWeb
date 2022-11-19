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
 * 2015年10月21日     jenkin.yu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.financial;

import com.achievo.framework.entity.BaseEntity;


/**
 * Description：<br> 
 * 备付金参数
 * @author  jenkin.yu
 * @date    2015年10月21日
 * @version v1.0.0
 */
public class BizEnsureMoneyParams extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5102744933974055249L;
	/**
	 * 备付金本金垫付罚息利率
	 */
	private String bizEnsureCapitalPenaltyRate;
	/**
	 * 备付金利息垫付罚息利率
	 */
	private String bizEnsureInterestPenaltyRate;
	/**
	 * 备付金管理费收取比例
	 */
	private String bizEnsureManageRate;
	/**
	 * 备付金活期利率
	 */
	private String bizEnsureRateOfCall;
	/**
	 * B级风险预警
	 */
	private String bizEnsureBRiskWarn;
	/**
	 * A级风险预警
	 */
	private String bizEnsureARiskWarn;
	
	public BizEnsureMoneyParams() {
		super();
	}
	public BizEnsureMoneyParams(String bizEnsureCapitalPenaltyRate,
			String bizEnsureInterestPenaltyRate, String bizEnsureManageRate,
			String bizEnsureRateOfCall, String bizEnsureBRiskWarn,
			String bizEnsureARiskWarn) {
		super();
		this.bizEnsureCapitalPenaltyRate = bizEnsureCapitalPenaltyRate;
		this.bizEnsureInterestPenaltyRate = bizEnsureInterestPenaltyRate;
		this.bizEnsureManageRate = bizEnsureManageRate;
		this.bizEnsureRateOfCall = bizEnsureRateOfCall;
		this.bizEnsureBRiskWarn = bizEnsureBRiskWarn;
		this.bizEnsureARiskWarn = bizEnsureARiskWarn;
	}
	public String getBizEnsureCapitalPenaltyRate() {
		return bizEnsureCapitalPenaltyRate;
	}
	public void setBizEnsureCapitalPenaltyRate(String bizEnsureCapitalPenaltyRate) {
		this.bizEnsureCapitalPenaltyRate = bizEnsureCapitalPenaltyRate;
	}
	public String getBizEnsureInterestPenaltyRate() {
		return bizEnsureInterestPenaltyRate;
	}
	public void setBizEnsureInterestPenaltyRate(String bizEnsureInterestPenaltyRate) {
		this.bizEnsureInterestPenaltyRate = bizEnsureInterestPenaltyRate;
	}
	public String getBizEnsureManageRate() {
		return bizEnsureManageRate;
	}
	public void setBizEnsureManageRate(String bizEnsureManageRate) {
		this.bizEnsureManageRate = bizEnsureManageRate;
	}
	public String getBizEnsureRateOfCall() {
		return bizEnsureRateOfCall;
	}
	public void setBizEnsureRateOfCall(String bizEnsureRateOfCall) {
		this.bizEnsureRateOfCall = bizEnsureRateOfCall;
	}
	public String getBizEnsureBRiskWarn() {
		return bizEnsureBRiskWarn;
	}
	public void setBizEnsureBRiskWarn(String bizEnsureBRiskWarn) {
		this.bizEnsureBRiskWarn = bizEnsureBRiskWarn;
	}
	public String getBizEnsureARiskWarn() {
		return bizEnsureARiskWarn;
	}
	public void setBizEnsureARiskWarn(String bizEnsureARiskWarn) {
		this.bizEnsureARiskWarn = bizEnsureARiskWarn;
	}
	@Override
	public String toString() {
		return "BizEnsureMoneyParams [bizEnsureCapitalPenaltyRate="
				+ bizEnsureCapitalPenaltyRate
				+ ", bizEnsureInterestPenaltyRate="
				+ bizEnsureInterestPenaltyRate + ", bizEnsureManageRate="
				+ bizEnsureManageRate + ", bizEnsureRateOfCall="
				+ bizEnsureRateOfCall + ", bizEnsureBRiskWarn="
				+ bizEnsureBRiskWarn + ", bizEnsureARiskWarn="
				+ bizEnsureARiskWarn + "]";
	}
	
	
}


