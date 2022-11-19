<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/disOrEnableCusStatus.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/disOrEnableCusStatusModel.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar" class="pt10 noPadding">
				<%@ include file="../common/customer_details.jsp" %>
				<%@ include file="../common/customer_operation.jsp" %>
			<!-- 操作按钮 -->	
				<div class="align_center">		
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="cusStatus.save();">提交</a>
				</div>	
				<div class="pt10"></div>
				<!-- 操作按钮 -->
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
		cusStatus.loadData();
		cusStatus.init();
		cusStatus.initDataGrid();
	});
</script>
	
</body>