<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/releaseBorrowManage.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="" id="bizBorrowRevoke" name="bizBorrowRevoke" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizBorrow.pid}">
	<div class="pt10">
		<table class="formTable">
			<tr>
				<td class="label_right" style="width: 100px;">借款人：</td>
				<td>${bizBorrow.customerName}</td>
				<td class="label_right" style="width: 100px;">姓名：</td>
				<td>${bizBorrow.sname}</td>
				<td class="label_right" style="width: 100px;">手机号码：</td>
				<td>${bizBorrow.phoneNo}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">贷款类型：</td>
				<td>${bizBorrow.borrowTypeName}</td>
				<td class="label_right" style="width: 100px;">借款标题：</td>
				<td>${bizBorrow.borrowName}</td>
				<td class="label_right" style="width: 100px;">借款金额：</td>
				<td>${bizBorrow.borrowMoney}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">年化率：</td>
				<td>${bizBorrow.borrowRate}%</td>
				<td class="label_right" style="width: 100px;">期限：</td>
				<td>${bizBorrow.borDeadline}月</td>
				<td class="label_right" style="width: 100px;">还款方式：</td>
				<td>${bizBorrow.repaymentTypeName}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">投标奖励：</td>
				<td colspan="5">${bizBorrow.investRewardScale}%</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px"><font color="red">*</font>撤销原因：</td>
				<td colspan="5">
					<input class="easyui-textbox" id="restReason" name="restReason" required="true" data-options="validType:'length[0,255]',multiline:true"  style="width:150px; height: 100px;" missingMessage="请输入还款来源!"/>
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
