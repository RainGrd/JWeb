<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="pointToolbar1">			
				<input type="hidden" id="pid" name="pid" value="${pid}">
		</div>
		<!-- 数据列表 -->
		<div class="showDataListWrap">
			<table id="showBidRecordGrids"></table>
		</div>
		<script type="text/javascript" src="${basePath}resources/js/custom/toSelectBidRecordList.js"></script>
		<script type="text/javascript" src="${basePath}resources/js/custom/model/toSelectBidRecordListModel.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			bidRecord.initDataGrid();	
		});
		</script>
	</div>
</body>