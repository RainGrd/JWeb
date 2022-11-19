<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<form action="" id="bizBorrowRelease" name="bizBorrowRelease" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizReceiptTransfer.pid}">
	<div class="pt10">
		<table class="formTable">
			<tr>
				<td class="label_right" style="width: 100px;">借款编号：</td>
				<td>${bizReceiptTransfer.borrowCode}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">标的名称：</td>
				<td>${bizReceiptTransfer.borrowName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">转让人：</td>
				<td>${bizReceiptTransfer.createUserName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">姓名：</td>
				<td>${bizReceiptTransfer.createUserSName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">发布时间：</td>
				<td>${bizReceiptTransfer.createTime}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">原始金额：</td>
				<td><fmt:formatNumber value="${bizReceiptTransfer.initInvestAmount}" pattern="#,###.00"/></td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">转让价格：</td>
				<td><fmt:formatNumber value="${bizReceiptTransfer.successAmount}" pattern="#,###.00"/></td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">差额：</td>
				<td><fmt:formatNumber value="${bizReceiptTransfer.balanceAmount}" pattern="#,###.00"/></td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px"><font color="red">*</font>撤销原因：</td>
				<td colspan="3"><input class="easyui-textbox" name="recTraDesc" data-options="multiline:true,required:true,validType:'length[0,255]'" style="width: 500px;height: 80px;" /></td>
			</tr>
			
		</table>
	</div>
</form>
</body>
