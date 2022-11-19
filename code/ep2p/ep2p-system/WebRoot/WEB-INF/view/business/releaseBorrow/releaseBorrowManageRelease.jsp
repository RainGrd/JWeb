<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/releaseBorrowManage.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="" id="bizBorrowRelease" name="bizBorrowRelease" method="post" >
	<input type="hidden" id="pid" name="pid" value="${bizBorrow.pid}">
	<input type="hidden" id="borrowMoney" name="borrowMoney" value="${bizBorrow.borrowMoney}">
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
			<tr>
				<td class="label_right" style="width: 100px">招标密码：</td>
				<td colspan="5">
					<input class="easyui-textbox" id="borrowPassword" name="borrowPassword" data-options="validType:'length[0,20]'"  style="width:150px"   />
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
