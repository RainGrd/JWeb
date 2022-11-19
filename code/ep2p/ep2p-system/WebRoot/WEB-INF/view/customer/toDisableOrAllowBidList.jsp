<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/toDisableOrAllowBidList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/disableOrAllowBidModel.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar">
				<%@ include file="../common/customer_details.jsp" %>
				<%@ include file="../common/customer_operation.jsp" %>
			<!-- 操作按钮 -->	
				<div class="align_center">		
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="cusBidStatus.save();">提交</a>
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
		cusBidStatus.loadData();
		cusBidStatus.init();
		cusBidStatus.initDataGrid();
	});
</script>
	
</body>