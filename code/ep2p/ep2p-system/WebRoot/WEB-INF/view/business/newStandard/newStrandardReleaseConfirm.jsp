<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<form action="" id="bizBorrowRelease" name="bizBorrowRelease" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizBorrow.pid}">
	<input type="hidden" id="borrowMoney" name="borrowMoney" value="${bizBorrow.borrowMoney}">
	<input type="hidden" id="deadline" name="deadline" value="${bizBorrow.deadline}">
	<input type="hidden" id="publishTime" name="publishTime" value="${bizBorrow.publishTime}">
	<div class="pt10">
		<table class="formTable">
			<tr>
				<td class="label_right" style="width: 100px;">产品名称：</td>
				<td>${bizBorrow.borrowName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">金额：</td>
				<td><fmt:formatNumber value="${bizBorrow.borrowMoney}" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">期限：</td>
				<td>${bizBorrow.borDeadline}天</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">年利率：</td>
				<td><fmt:formatNumber value="${bizBorrow.borrowRate*100}" pattern="#0.00" />%</td>
				
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">还款方式：</td>
				<td>${bizBorrow.repaymentTypeName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">招标天数：</td>
				<td >${bizBorrow.deadline}天</td>
			</tr>
			
		</table>
	</div>
</form>
</body>

