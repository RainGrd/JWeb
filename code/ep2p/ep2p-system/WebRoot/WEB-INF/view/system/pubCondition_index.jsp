<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/pubCondition_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/system/model/pubCondition_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right">条件编号：</td>
							<td>
								<input class="easyui-textbox" name="condCode"/>
							</td>
							<td class="align_right">条件名称：</td>
							<td >
								<input class="easyui-textbox" name="condName"/>
							</td>
						</tr>
						<tr>
							<td class="align_right">状态：</td>
							<td>
								<select class="easyui-combobox" name="status" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >启用</option>
									<option value=2 >禁用</option>
								</select>
							</td>
							<td colspan="2"> 
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="pubCondition.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="pubCondition.openAdd()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="pubCondition.remove()">删除</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="pubConditionGrid" >
			</table>
		</div>
</body>