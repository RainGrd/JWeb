<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/report/model/borrow_time_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/report/borrow_time_report.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div class="showDataListWrap" id="borrowTimeGrid">
			<table id="borrowTimeGrid"></table>
		</div>
	</div>
	
	
	<script type="text/javascript">
		$(function(){
			borrowTime.initborrowTimeGrid();
		})
	</script>
</body>

