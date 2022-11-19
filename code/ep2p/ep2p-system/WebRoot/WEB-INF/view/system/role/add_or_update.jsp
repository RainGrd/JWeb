<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript">
	$(document).ready(function(){
		 //初始化父角色列表
		 $('#parentRoleCode').combobox({  
			url:BASE_PATH+'sysRoleController/roleAddOrUpdateCombx.shtml',
			valueField:'roleCode',    
			textField:'roleName',
			loadFilter: function(res){
				return res.data;
			},
			onLoadSuccess:function(){
				var pid = $("#pid").val();
				if(pid && null != pid && "" != pid ){
					 $.post(BASE_PATH+"sysRoleController/getRoleByPid.shtml",{pid:pid}, 
						function(result) {
							if(result.message.message == 200){
								$('#role_add_or_update_form').form('load', result.data); 
							}else{
								$.messager.alert('操作提示',"加载失败！",'error');
							}
						},'json'
					 );
				} 
			}
		 }); 
	});
</script>
	<div class="formWrap" data-options="region:'center',border:false">
		<form id="role_add_or_update_form" name="menu_add_child_node_form" method="POST">
			<input type="hidden" id="pid" name="pid" value="${pid}">
			<table class="role_form_table_one">
				<tr>
					<td class="align_right" >角色名称：</td>
					<td><input type="text" class="text_style easyui-validatebox"
						id="roleName" name="roleName" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="align_right">父角色：</td>
					<td>
						<input id="parentRoleCode" class="text_style easyui-combobox" name="parentRoleCode" data-options="width:200"/>
					</td>
				</tr>
				<tr>
					<td class="align_right">菜单描述：</td>
					<td>
						<textarea id="roleDesc" name="roleDesc"  rows="5"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>