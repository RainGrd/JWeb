<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowManage.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/model/financialBorrowManage_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<input type="hidden" id="type" value="${type }">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px" > 
						<table class="beforeloanTable"  width="90%">
							<tr>
								<td class="label_right"> 客户名：</td>
								<td><input class="easyui-validatebox" name="customerName" id="customerName" /></td>
								<td class="label_right"> 姓名：</td>
								<td><input class="easyui-validatebox" name="sname" id="sname" /></td>
							
								<td class="label_right"> 手机号码：</td>
								<td><input class="easyui-validatebox" name="phoneNo" id="phoneNo" /></td>
								<td class="label_right"></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="financialBorrowManage.searchCusTomer()">查询</a> </td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
		</div>
	
		<div class="showDataListWrap">
			<table id="cusTomerGrid"></table>
		</div> 
	</div>
	
	<script type="text/javascript">
		$(function(){
			financialBorrowManage.initCusTomerDataGrid();
		})
	</script>
</body>