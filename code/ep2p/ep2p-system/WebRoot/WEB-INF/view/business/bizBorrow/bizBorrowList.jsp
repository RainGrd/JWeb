<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/bizBorrow_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/bizBorrow.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right">借款编号：</td>
								<td><input class="easyui-textbox" name="borrowCode" id="borrowCode" /></td>
								<td class="align_right">借款名称：</td>
								<td><input class="easyui-textbox" name="borrowName" id="borrowName"/></td>
							</tr>
							<tr>
								<td class="align_right">借款人：</td>
								<td><input class="easyui-textbox" name="customerName" id="customerName" /></td>
								<td class="align_right">姓名：</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
							</tr>
							<tr>
								<td class="align_right">手机号码：</td>
								<td><input class="easyui-textbox" name="phoneNo" id="phoneNo" /></td>
								<td class="align_right">申请时间：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" id="startApproveTime" name="startApproveTime" data-options="editable:false" />
										<input class="easyui-datebox" id="endApproveTime" name="endApproveTime" data-options="editable:false" />
										<a href="javascript:void(0);" iconCls="icon-more" onclick="bizBorrow.searchCd()">更多条件</a>
									</div>
								</td>
							</tr>
							
							<tr class="moreTr none">
								<td class="align_right">金额范围：</td>
								<td>
									<input class="easyui-validatebox" id="borrowMoneyMin" name="borrowMoneyMin" style="width: 100px;"/>~
									<input class="easyui-validatebox" id="borrowMoneyMax" name="borrowMoneyMax"  style="width: 100px;"/>
								</td>
								<td class="align_right">利率范围(%)：</td>
								<td>
									<input class="easyui-validatebox" id="borrowRateMin" name="borrowRateMin" style="width: 100px;"/>~
									<input class="easyui-validatebox" id="borrowRateMax" name="borrowRateMax" style="width: 100px;" />
								</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">期限范围（月）：</td>
								<td>
									<input class="easyui-validatebox" id="borDeadlineMin" name="borDeadlineMin" style="width: 100px;" />~
									<input class="easyui-validatebox" id="borDeadlineMax" name="borDeadlineMax" style="width: 100px;" />
								</td>
							
								<td class="align_right">还款方式：</td>
								<td>
									<input name="repaymentType" editable="false" id="repaymentType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=REPAYMENT_TYPE %>'" />
								</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">计息方式：</td>
								<td>
									<input name="accrualType" editable="false" id="accrualType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=ACCRUAL_TYPE %>'" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="align_right">借款类型：</td>
								<td>
									<input name="borrowType" editable="false" id="borrowType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=BORROW_TYPE_SERACH %>'" />
								</td>
								<td></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="bizBorrow.searchData()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="bizBorrow.resetForm()">重置</a>
								</td>
							</tr>
						</table>							
					</div>
				</form>
		</div>
		<div id="borrowRelease_context">
			<div class="showDataListWrap">
				<table id="bizBorrowGrid"></table>
			</div>
		</div>
	</div>
</body>