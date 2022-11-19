<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/model/dictionaryContentManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/dictionary/dictionaryContent.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 操作按钮 -->	
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="distionaryContent.toAdd()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-enable" plain="true"  onclick="distionaryContent.updateStatus(1)">启用</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-disable" plain="true"  onclick="distionaryContent.updateStatus(0)">禁用</a>
		</div>	
		
		<div>
			<input type="hidden" id="dictId" name="dictId" value="${dictId}">
			<input type="hidden" id="isUpdate" name="isUpdate" value="${isUpdate}">
			
			<!-- 数据表格 -->
			<div class="showDataListWrap">
				<table id="distionaryContentGrid">
				</table>
			</div>
		</div>
	</div>
</body>