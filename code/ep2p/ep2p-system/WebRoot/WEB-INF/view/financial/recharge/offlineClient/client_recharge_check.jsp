<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/recharge/offlineClient/offline_recharge.js" charset="utf-8"></script>
	<div id="offline_index_toolbar" data-option="fit:true">
		<div class="formWrap">
			<%@ include file="../../../common/client_details.jsp" %>
			<!-- 客户充值 -->
			<form action="" method="post" id="client_rechagre_check_form" name="client_rechagre_check_form">
				<input type="hidden" id="pid" name="pid" value="${offRhgId}">
				<input type="hidden" name="amount" id="amount" value="${amount}"/>
				<input type="hidden" name="recTime" id="recTime" value="${recTime}"/>
				<table class="client_rechagre_check_table formTable">
					<tr>
						<td class="align_right" width="83">充值金额：</td>
						<td>${amount}</td>
					</tr>
					<tr>
						<td class="align_right" >充值时间：</td>
						<td>${recTime}</td>
					</tr>
					<tr>
						<td class="align_right" >审核：</td>
						<td>
							<input type="radio" name="recStatus" value="2" checked="checked">同意&nbsp;&nbsp;
							<input type="radio" name="recStatus" value="3">拒绝
						</td>
					</tr>
					<tr>
						<td class="align_right">备注信息：</td>
						<td>
							<textarea id="recOffDesc" class="easyui-validatebox percent70" name="recOffDesc" data-options="validType:['length[0,125]']" rows="5"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>