<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<style>
.tree-node-hover{
text-decoration:none;color:#555555; background: #fae8bd; 
}
</style>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/system/role/permission_assign.js" charset="utf-8"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){
			permissionAssign.initPermissionTree();	
		});
	</script>

	<div data-options="region:'center',border:false,fit:true">
		<div id="user_assign">
			<!-- 查询条件 -->
			<form action="" method="post" id="user_assign_form" name="user_assign_form">
				<div style="padding: 5px">
					<table class="beforeloanTable">
						<tr>
							<td class="label_right">
								<div id="choosable_user_toolbar">
									<div>
										<input type="hidden" value="${roleId}" name="roleId" id="roleId" />
										角色编码： <input class="easyui-validatebox" value="${roleCode}" disabled="disabled" name="roleCode" id="roleCode" />
									</div>
									<div>
										角色名称： <input class="easyui-validatebox" value="${roleName}" disabled="disabled" name="roleName" id="roleName" />
									</div>
									<div>
										<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-save'" onclick="permissionAssign.save()">保存</a>
										<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-clear'" onclick="permissionAssign.reset()">重置</a>
										<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="permissionAssign.close()">取消</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div style="width:260px;">
									<ul class="easyui-tree" id="role_permission_tree"></ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>