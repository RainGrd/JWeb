/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 账户信息VO视图
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.vo.usercenter;

import java.io.Serializable;
import java.math.BigDecimal;

import com.achievo.framework.vo.BaseVo;
import com.yscf.common.util.ArithmeticUtil;

/**
 * Description：<br> 
 * 账户信息VO视图
 * @author  Lin Xu
 * @date    2015年11月24日
 * @version v1.0.0
 */
public class AccountInfoVo extends BaseVo implements Serializable {

	private static final long serialVersionUID = -1977682612118083209L;
	//可用余额
	private BigDecimal availableBalance;
	//冻结金额
	private BigDecimal freezingAmount;
	//待收本金
	private BigDecimal dueinAmount;
	//待收利息
	private BigDecimal dueinInterest;
	//投资利息
	private BigDecimal investInterest;
	//加息利息
	private BigDecimal rateInterest;
	//投资奖励
	private BigDecimal investmentIncentive;
	//红包收益
	private BigDecimal redEnvelope;
	//推荐奖励
	private BigDecimal recommendedAwards;
	
	
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getFreezingAmount() {
		return freezingAmount;
	}
	public void setFreezingAmount(BigDecimal freezingAmount) {
		this.freezingAmount = freezingAmount;
	}
	public BigDecimal getDueinAmount() {
		return dueinAmount;
	}
	public void setDueinAmount(BigDecimal dueinAmount) {
		this.dueinAmount = dueinAmount;
	}
	public BigDecimal getDueinInterest() {
		return dueinInterest;
	}
	public void setDueinInterest(BigDecimal dueinInterest) {
		this.dueinInterest = dueinInterest;
	}
	public BigDecimal getInvestInterest() {
		return investInterest;
	}
	public void setInvestInterest(BigDecimal investInterest) {
		this.investInterest = investInterest;
	}
	public BigDecimal getRateInterest() {
		return rateInterest;
	}
	public void setRateInterest(BigDecimal rateInterest) {
		this.rateInterest = rateInterest;
	}
	public BigDecimal getInvestmentIncentive() {
		return investmentIncentive;
	}
	public void setInvestmentIncentive(BigDecimal investmentIncentive) {
		this.investmentIncentive = investmentIncentive;
	}
	public BigDecimal getRedEnvelope() {
		return redEnvelope;
	}
	public void setRedEnvelope(BigDecimal redEnvelope) {
		this.redEnvelope = redEnvelope;
	}
	public BigDecimal getRecommendedAwards() {
		return recommendedAwards;
	}
	public void setRecommendedAwards(BigDecimal recommendedAwards) {
		this.recommendedAwards = recommendedAwards;
	}
	
	//账户余额
	public BigDecimal getAccountBalance() {
		availableBalance = availableBalance == null ? new BigDecimal(0.0) : availableBalance;
		freezingAmount = freezingAmount == null ?  new BigDecimal(0.0) : freezingAmount;
		return ArithmeticUtil.add(availableBalance, freezingAmount);
	}
	
	//总资产
	public BigDecimal getTotalAssets() {
		availableBalance = availableBalance == null ?  new BigDecimal(0.0) : availableBalance;
		freezingAmount = freezingAmount == null ?  new BigDecimal(0.0) : freezingAmount;
		dueinAmount = dueinAmount == null ?  new BigDecimal(0.0) : dueinAmount;
		dueinInterest = dueinInterest == null ?  new BigDecimal(0.0) : dueinInterest;
		
		BigDecimal keyong = ArithmeticUtil.add(availableBalance, freezingAmount);
		BigDecimal daiyong = ArithmeticUtil.add(dueinAmount, dueinInterest);
		return ArithmeticUtil.add(keyong, daiyong);
	}
	
	//总收益
	public BigDecimal getTotalRevenue() {
		investInterest = investInterest == null ?  new BigDecimal(0.0) : investInterest;
		rateInterest = rateInterest == null ?  new BigDecimal(0.0) : rateInterest;
		investmentIncentive = investmentIncentive == null ?  new BigDecimal(0.0) : investmentIncentive;
		redEnvelope = redEnvelope == null ?  new BigDecimal(0.0) : redEnvelope;
		recommendedAwards = recommendedAwards == null ?  new BigDecimal(0.0) : recommendedAwards;
		
		BigDecimal aa = ArithmeticUtil.add(investInterest,rateInterest);
		BigDecimal bb = ArithmeticUtil.add(investmentIncentive,redEnvelope);
		BigDecimal cc = ArithmeticUtil.add(aa, bb);
		return ArithmeticUtil.add(cc, recommendedAwards);
	}
	
	
	
}


