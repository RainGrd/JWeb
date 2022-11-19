<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<form action="" id="bizBorrowRelease" name="bizBorrowRelease" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizBorrow.pid}">
	<input type="hidden" id="borrowType" name="borrowType" value="${bizBorrow.borrowType}">
	<div class="pt10">
		<table class="formTable">
			<tr>
				<td class="label_right" style="width: 100px;">标的名称：</td>
				<td>${bizBorrow.borrowName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">金额：</td>
				<td>${bizBorrow.borrowMoney}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">年利率：</td>
				<td><fmt:formatNumber value="${bizBorrow.borrowRate*100}" pattern="#0.00" />%</td>
				
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">期限：</td>
				<td>${bizBorrow.borDeadline}天</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">还款方式：</td>
				<td>${bizBorrow.repaymentTypeName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px"><font color="red">*</font>撤销原因：</td>
				<td colspan="3"><input class="easyui-textbox" name="restReason" data-options="multiline:true,required:true,validType:'length[0,255]'" style="width: 500px;height: 80px;" /></td>
			</tr>
			
		</table>
	</div>
</form>
</body>
