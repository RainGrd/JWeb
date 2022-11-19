<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/releaseBorrowManage.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="" id="releaseBorrowManageReleaseConfirm" name="releaseBorrowManageReleaseConfirm" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizBorrow.pid}">
	<input type="hidden" id="publishTime" name="publishTime" value="${bizBorrow.publishTime}">
	<input type="hidden" id="deadline" name="deadline" value="${bizBorrow.deadline}">
	<input type="hidden" id="isSplit" name="isSplit" value="${bizBorrow.isSplit}">
	<input type="hidden" id="splitBorCount" name="splitBorCount" value="${bizBorrow.splitBorCount}">
	<input type="hidden" id="borrowPassword" name="borrowPassword" value="${bizBorrow.borrowPassword}">
	<input type="hidden" id="approveId" name="approveId" value="${bizBorrow.approveId}">
	<input type="hidden" id="approveNode" name="approveNode" value="${bizBorrow.approveNode}">
	<input type="hidden" id="approveStatus" name="approveStatus" value="2">
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
				<td>${bizBorrow.borrowMoneyStr}</td>
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
				<td>${bizBorrow.investRewardScale}%</td>
				<td class="label_right" style="width: 100px;">招标天数：</td>
				<td>${bizBorrow.deadline}</td>
				<td class="label_right" style="width: 100px;">投标密码：</td>
				<td>${bizBorrow.borrowPassword}</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">最大投标金额：</td>
				<td>${bizBorrow.endMoneyStr}</td>
				<td class="label_right" style="width: 100px;">最小投标金额：</td>
				<td>${bizBorrow.startMoneyStr}</td>
				<td class="label_right" style="width: 100px;">发标时间：</td>
				<td>${bizBorrow.publishTime}</td>
			</tr>
		</table>
	</div>
</form>
</body>
