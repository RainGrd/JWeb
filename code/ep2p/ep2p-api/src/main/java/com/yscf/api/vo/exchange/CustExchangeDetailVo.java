package com.yscf.api.vo.exchange;

/**
 * 
 * @ClassName : CustExchangeDetailVo
 * @Description : 客户兑换VO
 * @Author : Qing.Cai
 * @Date : 2016年1月21日 下午3:33:11
 */
public class CustExchangeDetailVo {

	private String pid;

	private String customerId;
	/** 兑换类型 */
	private String exchangeType;
	/** 交易密码 */
	private String tradePassword;
	/** 手机号码 */
	private String phoneNo;
	/** 数据字典内容ID */
	private String dictionaryContentId;
	/** 数据字典编码 */
	private String dictCode;
	/** 当前页码 */
	private Integer pageIndex;
	/** 每页显示数 */
	private Integer pageSize;
	/** 安全检验字段 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDictionaryContentId() {
		return dictionaryContentId;
	}

	public void setDictionaryContentId(String dictionaryContentId) {
		this.dictionaryContentId = dictionaryContentId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}