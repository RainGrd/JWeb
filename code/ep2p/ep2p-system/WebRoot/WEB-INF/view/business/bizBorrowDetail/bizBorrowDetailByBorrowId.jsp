<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
	<script type="text/javascript" src="${basePath}resources/js/business/model/borrowDetailManage_Model.js" charset="utf-8"></script>
	<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrowDetail.js" charset="utf-8"></script>
	
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<input type="hidden" id="borrowId" name="borrowId" value="${borrow.pid }" />
			<input type="hidden" value="${borrow.borrowType }" id="borrowType">
			<div style="padding: 5px">
				<table class="userTable percent90 formTable">
					<tr>
						<td class="align_right">贷款类型：</td>
						<td>${borrow.borrowTypeName }</td>
						<td class="align_right">年化率：</td>
						<td>${borrow.borrowRate }%</td>
						<td class="align_right">借款状态：</td>
						<td>${borrow.borStatusName }</td>
					</tr>
					<tr>
						<td class="align_right">借款标题：</td>
						<td>${borrow.borrowName }</td>
						<td class="align_right">期限：</td>
						<td>
							${borrow.borDeadline }
							<c:if test="${borrow.borrowType==1 || borrow.borrowType==2 || borrow.borrowType==3}">
								月
							</c:if>
							<c:if test="${borrow.borrowType==4 || borrow.borrowType==5}">
								天
							</c:if>
						</td>
						<td class="align_right">备注：</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td class="align_right">借款金额：</td>
						<td>
							${borrow.borrowMoney }
						</td>
						<td class="align_right">
							还款方式：
						</td>
						<td>${borrow.repaymentTypeName }</td>
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</table>							
			</div>
		</div>
		<div class="easyui-layout" style="height: 330px;">
			<div class="showDataListWrap">
				<table id="bizBorrowDetailGrid"></table>
			</div>
		</div>
	</div>
</body>