<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/borrowSearchManage_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrowSearch.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="userTable percent100 formTable">
							<tr>
								<td class="align_right">借款编号：</td>
								<td><input class="easyui-validatebox" name="borrowCode" id="borrowCode" /></td>
								<td class="align_right">借款名称：</td>
								<td><input class="easyui-validatebox" name="borrowName" id="borrowName"/></td>
							</tr>
							<tr>
								<td class="align_right">借款人：</td>
								<td><input class="easyui-validatebox" name="customerName" id="customerName" /></td>
								<td class="align_right">姓名：</td>
								<td><input class="easyui-validatebox" name="sname" id="sname" /></td>
							</tr>
							<tr>
								<td class="align_right">手机号码：</td>
								<td><input class="easyui-validatebox" name="phoneNo" id="phoneNo" /></td>
								<td class="align_right">发布时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox" id="startPublishTime" name="startPublishTime" data-options="editable:false" />
									<input class="easyui-datebox" id="endPublishTime" name="endPublishTime" data-options="editable:false" />
									<a href="javascript:void(0);" iconCls="icon-more" onclick="borrowSearch.searchCd()">更多条件</a>
									</div>
								</td>
							</tr>
							
							<tr class="moreTr none">
								<td class="align_right">金额范围：</td>
								<td>
									<input class="easyui-validatebox" id="borrowMoneyMin" name="borrowMoneyMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowMoneyMax" name="borrowMoneyMax"  style="width: 80px;"/>
								</td>
								<td class="align_right">利率范围(%)：</td>
								<td>
									<input class="easyui-validatebox" id="borrowRateMin" name="borrowRateMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowRateMax" name="borrowRateMax" style="width: 80px;" />
								</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">期限范围（月）：</td>
								<td>
									<input class="easyui-validatebox" id="borDeadlineMin" name="borDeadlineMin" style="width: 80px;" />~
									<input class="easyui-validatebox" id="borDeadlineMax" name="borDeadlineMax" style="width: 80px;" />
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
								<td class="align_right">审核状态：</td>
								<td>
									<input name="approveStatus" editable="false" id="approveStatus" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=BORROW_CHECK_STATUTS %>'" />
								</td>
							</tr>
							<tr>
								<td class="align_right">借款类型：</td>
								<td>
									<input name="borrowType" editable="false" id="borrowType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=BORROW_TYPE %>'" />
								</td>
								<td class="align_right">借款状态：</td>
								<td>
									<input name="borStatus" editable="false" id="borStatus" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=BORROW_STATUS_SEARCH %>'" />
								
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowSearch.searchData()">查询</a>
									
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="borrowSearch.resetForm()">重置</a>
								</td>
							</tr>
						</table>							
					</div>
				</form>
			</div>	
		</div>
	</div>
		
	<div id="borrowSearch_context">
		<div class="showDataListWrap">
			<table id="borrowSearchGrid"></table>
		</div>
	</div>
</body>