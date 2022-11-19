<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/financialBorrowSearchManage_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowSearch.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right">产品编号：</td>
								<td><input class="easyui-validatebox" name="borrowCode" id="borrowCode" /></td>
								<td class="align_right">产品名称：</td>
								<td><input class="easyui-validatebox" name="borrowName" id="borrowName"/></td>
							</tr>
							<tr>
								<td class="align_right">理财金额：</td>
								<td>
									<input class="easyui-validatebox" id="borrowMoneyMin" name="borrowMoneyMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowMoneyMax" name="borrowMoneyMax"  style="width: 80px;"/>
								</td>
								<td class="align_right">产品标签：</td>
								<td>
									<input class="easyui-validatebox" name="borrowName" id="borrowName"/>
								</td>
							</tr>
							<tr>
								<td class="align_right">期限范围：</td>
								<td>
									<input class="easyui-validatebox" id="borDeadlineMin" name="borDeadlineMin" style="width: 80px;" />~
									<input class="easyui-validatebox" id="borDeadlineMax" name="borDeadlineMax" style="width: 80px;" />
								</td>
								<td class="align_right">利率范围(%)</td>
								<td>
									<input class="easyui-validatebox" id="borrowRateMin" name="borrowRateMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowRateMax" name="borrowRateMax" style="width: 80px;" />
								</td>
							</tr>
							<tr>
								<td class="align_right">计息方式：</td>
								<td>
									<input name="accrualType" editable="false" id="accrualType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=ACCRUAL_TYPE %>'" />
								</td>
								<td class="align_right"></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="financialBorrowSearch.searchData()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="financialBorrowSearch.resetForm()">重置</a>
								</td>
							</tr>
						</table>							
					</div>
				</form>
		</div>
		
		<div id="financialBorrow_context">
			<div class="showDataListWrap">
				<table id="financialBorrowSearchGrid"></table>
			</div>
		</div>	
	</div>
</body>