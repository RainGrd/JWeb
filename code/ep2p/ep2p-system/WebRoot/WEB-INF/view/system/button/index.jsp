<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript" src="${basePath}resources/js/system/model/button_model.js" charset="utf-8"></script>
	<script type="text/javascript" src="${basePath}resources/js/system/button/button.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			sysButton.initDataGrid();	
		});
	</script>
	<div data-options="region:'center',border:false">
		<div id="button_index_toolbar" class="easyui-panel">
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="sysButton.openAddOrUpdate()">新增</a>
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="sysButton.batchRemove()">删除</a> -->
		</div>
	
		<div class="showDataListWrap">
			<table id="button_index_grid"></table>
		</div>
	</div>
</body>