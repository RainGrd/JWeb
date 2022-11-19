package com.yscf.core.model.reflect;

/**
 * 借款人的借款合同中，还款表映射类
 * @author JunJie.Liu
 *
 */
public class BorrowUser {
	
	private String capital;
	
	private String interest;
	
	private String totalMoney;

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	
}
