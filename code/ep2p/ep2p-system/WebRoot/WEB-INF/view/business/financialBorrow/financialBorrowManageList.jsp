<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/financialBorrowManage_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowManage.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="userTable percent90 formTable">
						<tr>
							<td class="align_right">产品编号：</td>
							<td><input class="easyui-validatebox" name="borrowCode" id="borrowCode" />
								<input type="hidden" name="customerId" id="customerId" value="${customerId }"/>
							</td>
							<td class="align_right">产品名称：</td>
							<td><input class="easyui-validatebox" name="borrowName" id="borrowName"/></td>
						</tr>
						<tr>
							<td class="align_right">理财金额：</td>
							<td>
								<input class="easyui-validatebox" id="borrowMoneyMin" name="borrowMoneyMin" style="width: 80px;"/>~
								<input class="easyui-validatebox" id="borrowMoneyMax" name="borrowMoneyMax"  style="width: 80px;"/>
							</td>
							<td class="align_right">利率范围(%)：</td>
							<td>
								<input class="easyui-validatebox" id="borrowAprMin" name="borrowAprMin" style="width: 80px;"/>~
								<input class="easyui-validatebox" id="borrowAprMax" name="borrowAprMax" style="width: 80px;" />
							</td>
						</tr>
						<tr>
							<td class="align_right">期限范围：</td>
							<td>
								<input class="easyui-validatebox" id="borDeadlineMin" name="borDeadlineMin" style="width: 80px;" />~
								<input class="easyui-validatebox" id="borDeadlineMax" name="borDeadlineMax" style="width: 80px;" />
							</td>
							<td class="align_right">还款方式：</td>
							<td>
								<input name="accrualType" editable="false" id="accrualType" class="easyui-validatebox easyui-combobox" 
									data-options="loadFilter:common.dictionaryFilter,
													valueField:'dictContCode',
													textField:'dictContName',
													multiple:false,
													url:'<%=REPAYMENT_TYPE %>'" />
							</td>
						</tr>
						<tr>
							<td class="align_right"> </td>
							<td>
								 
							</td>
							<td></td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="financialBorrowManage.searchData()">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="financialBorrowManage.resetForm()">重置</a>
							</td>
						</tr>
					</table>							
				</div>
			</form>
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="financialBorrowManage.openAdd()">新增</a>
			</div>
		</div>
		<div id="borrowSearch_context">
			<div class="showDataListWrap">
				<table id="financialBorrowManageGrid"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
				financialBorrowManage.initDataGrid();	
		});
	 </script>
</body>