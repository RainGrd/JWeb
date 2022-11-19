<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<c:if test="${borrow.approveStatus == 2 ||  borrow.approveStatus == 3 ||  borrow.approveStatus == 5}">
	<div id="borrowStateinfo" class="easyui-panel" title="担保初审">
</c:if>
<c:if test="${borrow.approveStatus eq '4'}">
	<div id="borrowStateinfo" class="easyui-panel" title="借前审核">
</c:if>
<c:if test="${borrow.approveStatus eq '6'}">
	<div id="borrowStateinfo" class="easyui-panel" title="借款发布">
</c:if>
<c:if test="${empty borrow.approveStatus}">
	<div id="borrowStateinfo" class="easyui-panel" title="借款资料">
</c:if>
	<div class="p10">
		<table>
			<tr>
				<td>
					<a class="overState borrowState" onclick="borrow.viewBorrowVouchInfo('${borrow.pid}')">担保初审</a>
				</td>
				<td>
					<c:if test="${borrow.approveStatus == 2 ||  borrow.approveStatus == 3 ||  borrow.approveStatus == 5}">
						<div class="greyBar"></div>
					</c:if>
					<c:if test="${borrow.approveStatus == 4 ||  borrow.approveStatus == 5 ||  borrow.approveStatus == 6 || !empty borrow.borStatus}">
						<div class="greyBar overBar"></div>
					</c:if>
				</td>
				<td>
					<c:if test="${borrow.approveStatus == 2 ||  borrow.approveStatus == 3 ||  borrow.approveStatus == 5}">
						<a class="greyState borrowState" onclick="borrow.viewBorrowInfo('${borrow.pid}')">借前审核</a>
					</c:if>
					<c:if test="${borrow.approveStatus == 4 ||  borrow.approveStatus == 6 || !empty borrow.borStatus}">
						<a class="overState borrowState" onclick="borrow.viewBorrowInfo('${borrow.pid}')">借前审核</a>
					</c:if>
				</td>
				<td>
					<c:if test="${ !empty borrow.borStatus}">
						<div class="greyBar overBar"></div>
					</c:if>
					<c:if test="${ empty borrow.borStatus}">
						<div class="greyBar greyBarLast"></div>
					</c:if>
				</td>
				<td>
					<c:if test="${ empty borrow.borStatus}">
						<a class="greyState borrowState">发布</a>
					</c:if>
					<c:if test="${ !empty borrow.borStatus}">
						<a class="overState borrowState" onclick="borrow.viewBorrowReleaseInfo('${borrow.pid}')">发布</a>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="pt10"></div>
<!-- 数据展示div -->
<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
	<div style="padding: 5px" > 
		<table class="beforeloanTable formTable"  width="90%">
			<tr>
				<td class="label_right"> 借款人：</td>
				<td>${(borrow.customerName == null || borrow.customerName == '')?borrow.phoneNo:borrow.customerName }</td>
				<td class="label_right"> 姓名：</td>
				<td>${borrow.sname}</td>
				<td class="label_right"> 手机号码：</td>
				<td>${borrow.phoneNo}</td>
				<td class="label_right"> 申请时间：</td>
				<td>${borrow.applyTime}</td>
			</tr>
			<tr>
				<td class="label_right"> 贷款类型：</td>
				<td>${borrow.borrowTypeName}</td>
				<c:if test="${borrow.borrowType == 1}">
					<td class="label_right"></td>
					<td></td>
				</c:if>
				<c:if test="${borrow.borrowType == 2}">
					<td class="label_right"> 房产总价：</td>
					<td>${borrow.homeTotalStr}</td>
				</c:if>
				<td class="label_right"> 借款金额：</td>
				<td>${borrow.borrowMoneyStr}</td>
				<td class="label_right"> 期限(月)：</td>
				<td>${borrow.borDeadline}</td>
			</tr>
		</table>	
	</div>
</div>