<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!-- <style type="text/css"> -->
<!-- </style> -->
<script type="text/javascript"
	src="${basePath}resources/js/content/contentManage/contColumn.js"
	charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="scroll-bar-div">
			<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<form action="selectContColumnList.shtml" method="post"
					id="selectCont" name="selectCont">
					<div class="p5">
						<table class="userTable formTable" width="100%">
							<tr>
								<td class="align_right">栏目名称：</td>
								<td><input class="easyui-textbox" id="coluName"
									name="coluName" /></td>
								<td width="90" align="right">状态：</td>
								<td><select name="status" editable="false"
									id="status" class="easyui-combobox">
										<option value="">--全部--</option>
										<option value="1">启用</option>
										<option value="2">禁用</option>
								</select></td>
								<td class="align_right">创建时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox" name="createBeginTime"
									id="createBeginTime"> - <input class="easyui-datebox"
									name="createEndTime" id="createEndTime">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="align_right">创建人：</td>
								<td>
									<input
									class="easyui-textbox" id="createUser"
									name="createUser" />									
								</td>
								<td class="align_right">最后更新人：</td>
								<td><input class="easyui-textbox"
									name="lastUpdateUser" id="lastUpdateUser" /></td>
								<td class="align_right">最后更新时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox"
									name="lastBeginUpdateTime" id="lastBeginUpdateTime"> -
									<input class="easyui-datebox" name="lastEndUpdateTime"
									id="lastEndUpdateTime">
									</div>	
								</td>
								<td>
									<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'"
									onclick="loadSysParam()">查询</a>
									<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-clear'"
									onclick="resetInput()">重置</a>
								</td>
							</tr>
						</table>
						<div class="pt10">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddPage()">新增</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-enable" plain="true" onclick="activationOrDisable(1)">启用</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-disable" plain="true" onclick="activationOrDisable(2)">禁用</a>
						</div>
					</div>
				</form>
			</div>

			<div class="showDataListWrap">
				<table id="grid">
				</table>
			</div>
		</div>
	</div>
</body>




