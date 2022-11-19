<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript"
	src="${basePath}resources/js/system/user/userManagerList.js"></script>
<script type="text/javascript"
	src="${basePath}resources/js/system/user/addUser.js"></script>
<body class="easyui-layout">
    <input type="hidden" id="pass_word" name="password" value="${password}"/>
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="query.action" method="post" id="searcher"
				name="searcher">
				<div style="padding: 5px">
					<table class="userTable percent90 formTable">
						<tr>
							<td class="align_right">用户账号：</td>
							<td><input class="easyui-textbox" id="account_No"
								name="accountNo" /></td>
							<td class="align_right">真实姓名：</td>
							<td><input class="easyui-textbox" id="real_Name"
								name="name" /></td>
							<td class="align_right">手机号码：</td>
							<td><input class="easyui-textbox " id="phone_No"
								name="phoneNo" /></td>
						</tr>
						<tr>
							<td class="align_right">用户状态：</td>
							<td><select name="status" editable="false" id="status"
								class="easyui-combobox">
									<option value="">--全部--</option>
									<option value="1">启用</option>
									<option value="0">禁用</option>
							</select></td>
							<td></td>
							<td colspan="3"><a href="#" class="easyui-linkbutton"
								iconCls="icon-search" onclick="userList.searchData()">查询</a> <a
								href="#" onclick="javascript:$('#searcher').form('reset');"
								iconCls="icon-clear" class="easyui-linkbutton">重置</a></td>
						</tr>
					</table>
					<input type="hidden" name="page" id="page" value="1"> <input
						type="hidden" name="rows" id="rows">
				</div>
			</form>
			<!-- 操作按钮 -->
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="userList.add()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="userList.batchRemove()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload',plain:'true'"
					onclick="userList.passwordReset()">密码重置</a>
			</div>
		</div>


		<div>
			<div class="userListDiv" style="height: 100%">
				<table id="grid" title="查询用户管理" class="easyui-datagrid"
					style="height: 100%; width: auto;"
					data-options="
			     url:BASE_PATH+'sysUserController/queryUserList.shtml',
		        method: 'POST',
			    rownumbers: true,
			    pagination: true,
			    toolbar: '#toolbar',
			    idField: 'pid',
			    fitColumns:true,
				selectOnCheck: true,
				singleSelect: false,
				checkOnSelect: true
			    ">
					<!-- 表头 -->
					<thead>
						<tr>
							<th data-options="field:'pid',checkbox:true,width:100"></th>
							<th data-options="field:'accountNo',width:100,sortable:true"
								align="center">用户账号</th>
							<th data-options="field:'name',width:100,sortable:true"
								align="center">真实姓名</th>
							<th data-options="field:'webchatNo',width:100,sortable:true"
								align="center">微信号</th>
							<th data-options="field:'qqNo',width:100,sortable:true"
								align="center">QQ</th>
							<th data-options="field:'phoneNo',width:100,sortable:true"
								align="center">手机号码</th>
							<th data-options="field:'status',width:100,sortable:true"
								align="center">状态</th>
							<th
								data-options="field:'yy',width:150,formatter:userList.formatOperation">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
</body>