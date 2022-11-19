<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!-- <style type="text/css"> -->
<!-- </style>  -->
<script type="text/javascript"
	src="${basePath}resources/js/content/advertisementManage/advContent.js"
	charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
	})
</script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="scroll-bar-div">
			<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<form action="selectContColumnList.shtml" method="post"
					id="selectCont" name="selectCont">
					<div style="padding: 5px">
						<table class="userTable formTable percent100" >
							<tr>
								<td width="80" class="align_right">广告名称：</td>
								<td><input class="easyui-textbox" id="titleName"
									name="titleName" /> <!-- 栏目id --> <input type="hidden"
									name="advId" id="advId" value="${advId }" /> <!-- 父级栏目内容id -->
									<input type="hidden" name="pid" id="pid"
									value="${content.pid }" />
								<td width="120" class="align_right">状态：</td>
								<td width="100"><select name="status" editable="false"
									id="status" class="easyui-combobox" style="width: 150px;">
										<option value="">--全部--</option>
										<option value="1">启用</option>
										<option value="2">禁用</option>
								</select></td>
								<td></td>
							</tr>
							<tr>
								<td class="align_right">创建人：</td>
								<td><input class="easyui-textbox"
									name="createUser" id="createUser" /></td>
								<td class="align_right">创建时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox" name="createBeginTime"
									id="createBeginTime" />~<input class="easyui-datebox"
									name="createEndTime" id="createEndTime" />
									</div>
								</td>
								<td></td>
							</tr>
							<tr>

								<td class="align_right">最后更新人：</td>
								<td><input class="easyui-textbox"
									name="lastUpdateUser" id="lastUpdateUser" /></td>
								<td class="align_right">最后更新时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox"
									name="lastBeginUpdateTime" id="lastBeginUpdateTime" />~<input class="easyui-datebox" name="lastEndUpdateTime"
									id="lastEndUpdateTime" />
									</div>
								</td>
								<td>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" 
									onclick="loadSysParam()" >查询</a> 
									<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-clear'"
									onclick="resetInput()">重置</a> </td>
									

							</tr>
						</table>
						<div>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddPage()" >新增</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-enable" plain="true" onclick="activationOrDisable(1)" >启用</a> 
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-disable" plain="true"  onclick="activationOrDisable(2)">禁用</a>
						</div>
					</div>
				</form>
			</div>

			<div class="comBaseInfoDiv" style="height: 100%;">
				<table id="grid">
				</table>
			</div>
		</div>
	</div>
</body>




