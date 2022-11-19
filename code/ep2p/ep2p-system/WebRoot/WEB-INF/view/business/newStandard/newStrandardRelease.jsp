<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<form action="" id="bizBorrowRelease" name="bizBorrowRelease" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizBorrow.pid}">
	<input type="hidden" id="borrowMoney" name="borrowMoney" value="${bizBorrow.borrowMoney}">
	<div class="pt10">
		<table class="formTable">
			<tr>
				<td class="label_right" style="width: 100px;">产品名称：</td>
				<td>${bizBorrow.borrowName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">金额：</td>
				<td><fmt:formatNumber value="${bizBorrow.borrowMoney}" pattern="#,###.00"/> </td>
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
				<td colspan="6">预发布\发布</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px"><font color="red">*</font>发布时间：</td>
				<td colspan="5">
					<input class="easyui-datetimebox" id="publishTime" name="publishTime" data-options="required:true,editable:false" missingMessage="请输入定时发标时间" />
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px"><font color="red">*</font>招标时间：</td>
				<td colspan="5">
					<input class="easyui-textbox" id="deadline" name="deadline" required="true" data-options="validType:'length[0,20]'"  style="width:150px" missingMessage="请输入招标时间!"    />天
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
