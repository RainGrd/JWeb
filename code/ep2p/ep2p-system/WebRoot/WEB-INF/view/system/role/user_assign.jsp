<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/system/model/user_manager_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/role/user_assign.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			userAssign.initChoosableUserGrid();	
			userAssign.initSelectedUserGrid();	
		});
	</script>
	<div data-options="region:'center',border:false,fit:true">
				<!-- 查询条件 -->
				<form action="" method="post" id="user_assign_form" name="user_assign_form">
					<div>
						<table class="formTable">
							<tr>
								<td>角色编码：</td>
								<td>
									<input type="hidden" value="${roleId}" name="roleId" id="roleId" />
									<input class="easyui-validatebox" value="${roleCode}" disabled="disabled" name="roleCode" id="roleCode" />
								</td>
								<td>
									角色名称：
								</td>
								<td>
									 <input class="easyui-validatebox" value="${roleName}" disabled="disabled" name="roleName" id="roleName" />
								</td>
							</tr>
						</table>
					</div>
					<div style="padding: 5px">
						<table class="beforeloanTable">
							<tr>
								<td class="label_right">
									<div id="choosable_user_toolbar">
										<input class="easyui-validatebox" name="choosableQueryStr" id="choosableQueryStr" />
										<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="userAssign.searchChoosableData()">查询</a>
									</div>
									<div class="showDataListWrap">
										<table id="choosable_user_grid"></table>
									</div>
								</td>
								<td>
									<a href="#" class="easyui-linkbutton" onclick="userAssign.userAssign()">&gt;&gt;</a>
									<br/>
									<a href="#" class="easyui-linkbutton" onclick="userAssign.cancelUserAssign()">&lt;&lt;</a>
								</td>
								<td class="label_right">
									<div id="selected_user_toolbar">										
										<input class="easyui-validatebox" name="selectedQueryStr" id="selectedQueryStr" />
										<a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="userAssign.searchSelectedData()">查询</a>
									</div>
									<div class="showDataListWrap">
										<table id="selected_user_grid"></table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</form>
	</div>
</body>