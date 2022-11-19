<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/disableOrAllowWithdrawalList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/disableOrAllowWithdrawalListModel.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar">
				<%@ include file="../common/customer_details.jsp" %>
				<%@ include file="../common/customer_operation.jsp" %>
			<!-- 操作按钮 -->	
				<div class="align_center">		
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="cusAllowStatus.save();">提交</a>
				</div>	
				<div class="pt10"></div>
				<div class="panel-header topBorder">
					<div class="panel-title">客户状态变更历史：</div>
					<div class="panel-tool">
						<a href="javascript:void(0)" class="panel-tool-collapse" onclick="foldDatagrid()"></a>
						<a href="javascript:void(0)" class="panel-tool-expand" onclick="openDatagrid()"></a>
					</div>
				</div>	
		</div>
		
		<div class="showDataListWrap">
			<table id="showCusGrids"></table>
		</div>
	</div> 
<script type="text/javascript">
	$(document).ready(function(){
		cusAllowStatus.loadData();
		cusAllowStatus.init();
		cusAllowStatus.initDataGrid();
	});
</script>
	
</body>