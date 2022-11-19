<%@page import="com.achievo.framework.security.domain.ContextUser"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String sysPath = request.getContextPath();
	String sysBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + sysPath + "/";

	//数据字典查询基础访问地址
	String baseLookupValPath = sysBasePath + "sysDistionaryContentController/selectByDisctCode.shtml?dictCode=";

	//查询所有的协议模板内容信息
	String baseAgreementTempPath = sysBasePath + "agreementTempManageController/selectAllAgreementTemp.shtml";
	
	// 数据字典查询基础访问地址（不带请选择） 
	String baseLookupValNoDefaultPath = sysBasePath + "sysDistionaryContentController/selectByDisctCodeNoPlease.shtml?dictCode=";
	/*
	* 数据字典访问查询的地址
	*/
	
	// 婚姻状况
	String MARITAL_STATUS = baseLookupValPath + "MARITAL_STATUS";
	
	// 借款审核状态
	String BORROW_APPROVE_STATUS = baseLookupValPath +"BORROW_APPROVE_STATUS";
	
	// 借款类型
	String BORROW_TYPE = baseLookupValPath +"BORROW_TYPE";
	
	// 借款期限
	String BORROW_TIME =  baseLookupValPath +"BORROW_TIME";
	
	//借款状态
	String BORROW_STATUS = baseLookupValPath +"BORROW_STATUS";
	
	//借款发布状态
	String BORROW_STATUS_RELEASE = baseLookupValPath +"BORROW_STATUS_RELEASE";
	
	//借款查询状态
	String BORROW_STATUS_SEARCH = baseLookupValPath +"BORROW_STATUS_SEARCH";
	
	//理财产品查询状态
	String BORROW_STATUS_FINANCIAL = baseLookupValPath +"BORROW_STATUS_FINANCIAL";
	
	// 还款方式
	String REPAYMENT_TYPE =  baseLookupValPath +"REPAYMENT_TYPE";
	
	//理财产品的还款方式
	String BORROW_STATUS_FINANCIAL_MANAGE = baseLookupValPath +"BORROW_STATUS_FINANCIAL_MANAGE";
	// 计息类型
	String ACCRUAL_TYPE =  baseLookupValPath +"ACCRUAL_TYPE";
	//计息类型（理财产品计息类型）
	String FINANCE_ACCRUAL_TYPE = baseLookupValPath +"FINANCE_ACCRUAL_TYPE";
	// 计息类型(新手标体验标查询)
	String NEW_ACCRUAL_TYPE =  baseLookupValNoDefaultPath +"NEW_ACCRUAL_TYPE";

	// 提现备注失败提示
	String DESC_PROMPT_ERROR = baseLookupValPath +"DESC_PROMPT_ERROR";

	// 提现备注成功提示
	String DESC_PROMPT_SUCCESS = baseLookupValPath +"DESC_PROMPT_SUCCESS";
	
	// 担保机构
	String GUA_ORG =  baseLookupValPath +"GUA_ORG";
	
	// 数据状态 1 启用 2 禁用
	String DATA_STATUS =  baseLookupValPath + "DATA_STATUS";
	
	//协议内容模板
	String AGREEM_TEMP = baseAgreementTempPath;
	
	//还款方式
	String REPAYMENTTYPE = baseLookupValPath + "REPAYMENTTYPE";
	
	//计息类型
	String ACCRUALTYPE = baseLookupValPath + "ACCRUALTYPE";
	
	//借款类型
	String BORROWTYPE = baseLookupValPath + "BORROWTYPE";
	
	//财务审核任务
	String FINANCE_CHECK_TASK = baseLookupValPath+"FINANCE_CHECK_TASK";
	
	// 借款审核状态（借前审核查询页面）
	String BORROW_STATUS_LEND = baseLookupValPath+"BORROW_STATUS_LEND";
	
	// 借款审核状态（担保初审查询页面）
	String BORROW_STATUS_VOUCH =  baseLookupValPath+"BORROW_STATUS_VOUCH";
	
	//标的类型（新手标体验标查询）
	String BID_TYPE = baseLookupValNoDefaultPath + "BID_TYPE";
	
	//标的状态（新手标体验标查询）
	String BID_STATUS = baseLookupValPath + "BID_STATUS";
	
	// 条件标签
	String COND_TAG = baseLookupValPath + "COND_TAG";
	
	//借款审核状态
	String BORROW_CHECK_STATUTS = baseLookupValPath + "BORROW_CHECK_STATUTS";
	
	//还款计划中的状态
	String BIZ_REPLAN_STATUS = baseLookupValPath + "BIZ_REPLAN_STATUS";
	
	//债权转让状态
	String RECEIPT_TANSFER_STATUS = baseLookupValPath + "RECEIPT_TANSFER_STATUS";

	// 备付金类型
	String ENS_MON_DET_TYPE = baseLookupValPath + "ENS_MON_DET_TYPE";
	
	// 备付金明细类型
	String ENSURE_DETAIL_TYPE = baseLookupValPath + "ENSURE_DETAIL_TYPE";
	
	// 备付金费用类型
	String ENS_MON_FEE_TYPE =  baseLookupValPath + "ENS_MON_FEE_TYPE";

	//搜索的年份列表
	String SREACH_YEARS = baseLookupValPath + "SREACH_YEARS";
	
	//借款类型（担保、借前 审核查询使用）
	String BORROW_TYPE_SERACH = baseLookupValPath + "BORROW_TYPE_SERACH";
	
	//收款计划中的状态
	String RECEIPT_STATUS = baseLookupValPath + "RECEIPT_STATUS";
	
	//待收时间类型（投资明细查询）
	String FOR_CLOSED_TYPE_TIME = baseLookupValPath + "FOR_CLOSED_TYPE_TIME";
	
	//
	String INVEST_BOR_STATUS = baseLookupValPath + "INVEST_BOR_STATUS";
	
	// 计息类型（借款贷前审核）
	String BORROW_ACCRUAL_TYPE = baseLookupValNoDefaultPath + "BORROW_ACCRUAL_TYPE";
%>