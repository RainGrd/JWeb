<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<form id="baseInfo" name="baseInfo" method="POST">
	<input type="hidden" id="pid" name="pid" value="${pid}">
	<input type="hidden" id="oldcusServiceId" name="oldcusServiceId" value="${oldcusServiceId}">
	<!-- 操作按钮 -->
	<div class="formWrap">
		<ul class="operateUl">
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="customerHistoryView();" >客服历史查看</a>			
			</li>
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="giveVip();">赠送VIP</a>
			</li>
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="frozenOrEnableCustomer();">冻结/启用该客户</a>
			</li>
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="banOrAllowWithdrawals();">禁止/允许提现</a>
			</li>
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="enableOrRevokeBlacklist();">启用/撤销黑名单</a>
			</li>
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="banOrAllowBid();">禁止/允许投标</a>
			</li>
			<li>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="banOrAllowZQZR();">禁止/允许债权转让</a>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
</form>	   
<script type="text/javascript" src="${basePath}resources/js/custom/toCustomerOperationPage.js"></script>
</body>