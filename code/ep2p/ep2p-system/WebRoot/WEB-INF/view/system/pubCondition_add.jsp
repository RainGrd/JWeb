<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/pubCondition_add.js" charset="utf-8"></script>

<body class="easyui-layout">
	
	<div class="easyui-panel" data-options="fit:true" style="width:auto;padding-top:30px;">
		<form id="baseInfo" name="baseInfo" action="<%=basePath%>pubConditionController/save.shtml" method="POST">
			<input type="hidden" name="pid" id="pid" value="${pid}" />
			<table class="cus_table formTable percent68">
				<tr>
					<td class="align_right">条件编号：</td>
					<td><input class="easyui-validatebox"  name="condCode" readonly="readonly" /></td>
					<td class="align_right">表示顺序：</td>
					<td><input class="easyui-validatebox" name="condOrder" style="width: 100px;" /></td>
				</tr>
				<tr>
					<td class="align_right"><label style="color: red;">*</label>条件名称：</td>
					<td colspan="3">
						<input class="easyui-validatebox" name="condName" data-options="required:true" style="width: 500px;" />
					</td>
				</tr>
				<tr>
					<td class="align_right">条件描述：</td>
					<td colspan="3">
						<input class="easyui-validatebox" name="condDesc" style="width: 500px;" />
					</td>
				</tr>
				<tr>
					<td class="align_right">条件标签：</td>
					<td>
						<input id="condTag" name="condTag" class="easyui-combobox" editable="false"
								 panelHeight="auto"
       							 data-options="loadFilter:common.dictionaryFilter,
       							 valueField:'dictContCode',
      							 textField:'dictContName',
       							 multiple:false,
          						 url:'<%=COND_TAG%>'" />
					</td>
					<td class="align_right">启用状态：</td>
					<td>
						<input type="radio" name="checkboxWarn" value="1" checked="checked" /> 启用
						<input type="radio" name="checkboxWarn" value="2" /> 禁用
						<input type="hidden" name="status" id="status" />
					</td>
				</tr>
				<tr>
					<td class="align_right vertical_align_top">条件值（SQL）：</td>
					<td colspan="3"><input class="easyui-textbox" name="condValue" data-options="multiline:true,required:true" style="width: 500px;height: 80px;" /></td>
				</tr>
				<tr>
					<%-- 保存or编辑按钮 --%>
					<td colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="pubCondition_add.save()">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="pubCondition_add.revert()">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>