<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/recharge/onlineClient/online_recharge.js" charset="utf-8"></script>
	<div id="replacement_order_toolbar" data-option="fit:true">
		<div class="formWrap">
			<!-- 客户充值 -->
			<form action="" method="post" id="replacement_order_form" name="replacement_order_form">
				<input type="hidden" id="pid" name="pid" value="${onRhgId}">
				<input type="hidden" id=amount name="amount" value="${amount}">
				<input type="hidden" id=customerId name="customerId" value="${customerId}">
				<table class="replacement_order_table formTable">
					<tr>
						<td class="align_right" >客户名：</td>
						<td>${customerName}</td>
					</tr>
					<tr>
						<td class="align_right" >真实姓名：</td>
						<td>${sname}</td>
					</tr>
					<tr>
						<td class="align_right" >充值金额：</td>
						<td>${amount}</td>
					</tr>
					<tr>
						<td class="align_right" >充值时间：</td>
						<td>${recTime}</td>
					</tr>
					<tr>
						<td class="align_right" >充值渠道：</td>
						<td>${payName}</td>
					</tr>
					<tr>
						<td class="align_right" >订单号：</td>
						<td>${recOrderNo}</td>
					</tr>
					<tr>
						<td class="align_right">备注信息：</td>
						<td>
							<textarea id="recOnlDesc" class="easyui-validatebox percent70"
								name="recOnlDesc" data-options="validType:['length[0,225]']"
								rows="5"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>