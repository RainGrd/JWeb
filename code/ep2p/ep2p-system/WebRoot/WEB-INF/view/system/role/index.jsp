<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/model/role_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/role/role.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		role.initDataGrid();	
	});
</script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="role_index_toolbar" class="easyui-panel">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="role_index_form" name="role_index_form">
					<div style="padding: 5px">
						<table class="beforeloanTable">
							<tr>
								<td class="label_right">
									角色名称： <input class="easyui-validatebox" name="roleName" id="roleName" />
								</td>
								<td class="label_right">
									<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="role.searchData()">查询</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="role.openAddOrUpdate()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="role.batchRemove()">删除</a>
		</div>
	
	<div class="showDataListWrap">
		<table id="role_index_grid"></table>
	</div>
	</div>
</body>