<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/model/agreementManageListModel.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/handle/agreementManageHandle.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 搜索选项及工具栏 -->
		<div id="agreementM_list_toolbar">
		<div>
			<!-- 查询条件 -->
			<form method="post" id="agreementM_serach_from" >
				<div style="padding: 5px" > 
					<table class="beforeloanTable formTable" width="90%">
						<tr>
							<td class="label_right"> 协议模版名称：</td>
							<td><input class="easyui-validatebox" name="agrContTempName" id="agrContTempName" /></td>
							<td class="label_right"> 状态：</td>
							<td>
								<input id="status" name="status" id="status" class="easyui-validatebox easyui-combobox"
										 panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 multiple:false,
	           							 url:'<%=DATA_STATUS%>'" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="label_right"> 创建人：</td>
							<td><input class="easyui-validatebox" name="createUser" id="createUser" /></td>
							<td class="label_right"> 创建时间：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-textbox easyui-datebox" id="startCTime" name="startCTime" data-options="editable:false" />~
								<input class="easyui-textbox easyui-datebox" id="endCtime" name="endCtime" data-options="editable:false" />
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="label_right"> 最后更新人：</td>
							<td><input class="easyui-validatebox" name="lastUpdateUser" id="lastUpdateUser" /></td>
							<td class="label_right"> 最后更新时间：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-textbox easyui-datebox" id="startLupTime" name="startLupTime" data-options="editable:false" />~
								<input class="easyui-textbox easyui-datebox" id="endLuptime" name="endLuptime" data-options="editable:false" />
								</div>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="agreementManageTemp.serachAgreementTemp();">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="$('#agreementM_serach_from').form('clear');">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- tool button  -->
			<div class="p10"> 
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="agreementManageTemp.toAgreementMTempAddPage()">新增</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-enable" plain="true" onclick="agreementManageTemp.activAndDisAgreementMTemp(1)">启用</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-disable" plain="true" onclick="agreementManageTemp.activAndDisAgreementMTemp(2)">禁用</a>
			</div>
		</div>
		</div>
		
		<!-- 合同管理内容信息 -->
		<div id="agreementM_context">
		<div class="showDataListWrap" >
			<table id="agreementM_datagrid_list" ></table>
		</div>
		</div>
		
	</div>
</body>