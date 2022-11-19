<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!--  提现 s-->
<div class="ka_g">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">余额提现</span>
		<a class=" fr_ mt20 size16 c95a1a6 tis_jilu" href="#" onclick="queryWithdrawaClick(2)">查看提现记录</a>
	</div>
	<div class="kong25"></div>
	<div class="ka_g_list " id="pay_bank_list_div">
	</div>
</div>
<!-- 提现 e-->
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/rechargewithdrawal/payIndex.js"></script>
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/rechargewithdrawal/payBackList.js"></script>