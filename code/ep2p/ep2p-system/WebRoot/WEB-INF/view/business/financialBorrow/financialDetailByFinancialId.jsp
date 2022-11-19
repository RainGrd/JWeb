<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
	<script type="text/javascript" src="${basePath}resources/js/business/model/borrowDetailManage_Model.js" charset="utf-8"></script>
	<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrowDetail.js" charset="utf-8"></script>
	
		<div id="bizBorrowDetailToolbar">
			<input type="hidden" id="borrowId" name="borrowId" value="${borrow.pid }" />
			<div style="padding: 5px">
				<table class="userTable percent90 formTable">
					<tr>
						<td class="align_right">产品名称：</td>
						<td>${borrow.borrowName }</td>
						<td class="align_right">年利率：</td>
						<td>${borrow.borrowRate }%</td>
						<td class="align_right">状态：</td>
						<td>${borrow.borStatusName }</td>
					</tr>
					<tr>
						<td class="align_right">产品编号：</td>
						<td>${borrow.borrowCode }</td>
						<td class="align_right">期限：</td>
						<td>${borrow.borDeadline }月</td>
						<td class="align_right">备注：</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td class="align_right">金额：</td>
						<td>
							${borrow.borrowMoney }
						</td>
						<td class="align_right">
							还款方式：
						</td>
						<td colspan="2">${borrow.repaymentTypeName }</td>
						<td></td>
						<td></td>
					</tr>
				</table>							
			</div>
		</div>
		<div class="showDataListWrap">
			<table id="bizBorrowDetailGrid"></table>
		</div>
	</div>
</body>