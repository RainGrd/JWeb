<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/model/dictionaryManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/dictionary/dictionary.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="beforeloanTable">
							<tr>
								<td class="label_right">
									数据类型： <input class="easyui-validatebox" name="dictNameOrCode" id="dictNameOrCode" />
									&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="distionary.searchData()">查询</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="distionary.toAdd()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="distionary.batchRemove()">删除</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="distionaryGrid"></table>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			distionary.initDataGrid();	
		});
	</script>
</body>