<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="pointToolbar">			
				<input type="hidden" id="pid" name="pid" value="${pid}">
		</div>
		<!-- 数据列表 -->
		<div class="showDataListWrap">
			<table id="showCusHongbaoGrids"></table>
		</div>
		
		<script type="text/javascript" src="${basePath}resources/js/custom/toHongBaoDetailsList.js"></script>
		<script type="text/javascript" src="${basePath}resources/js/custom/model/toHongbaoDetailsListModel.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			hongbao.initDataGrid();	
		});
		</script>
	</div>
</body>