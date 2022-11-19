<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/content/contractManage/model/contractManageListModel.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/content/contractManage/handle/contractManageHandle.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 搜索选项及工具栏 -->
		<div id="contractM_list_toolbar">
		<div>
			<!-- 查询条件 -->
			<form action="" method="post" id="contract_serach_from" name="searcm">
				<div class="p5" > 
					<table class="beforeloanTable formTable"  width="90%">
						<tr>
							<td class="label_right"> 协议名称：</td>
							<td><input class="easyui-validatebox" name="borAgrAnme" id="borAgrAnme" /></td>
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
								<input class="easyui-textbox easyui-datebox" name="startCTime" id="startCTime" data-options="editable:false" />~
								<input class="easyui-textbox easyui-datebox" name="endCtime" id="endCtime" data-options="editable:false" />
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
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="contractManageList.serachContract();">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="$('#contract_serach_from').form('clear');">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- tool button  -->
			<div class="p10">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="contractManageList.toContractAddPage();">新增</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-enable" plain="true" onclick="contractManageList.activAndDisContractM(1);">启用</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-disable" plain="true" onclick="contractManageList.activAndDisContractM(2);">禁用</a>
			</div>
		</div>
		</div>
		
		<!-- 合同管理内容信息 -->
		<div id="contractM_context">
		<div class="showDataListWrap">
			<table id="contractM_datagrid_list" ></table>
		</div>
		</div>
		
		<!-- 文件上传 -->
		<div id="uploadFile" class ="easyui-dialog" title="文件上传" modal="true" closed="true" style="width:500px;height:180px">
			<form id="fileUploadForm" method="post" enctype="multipart/form-data">
				<table  width="100%" height="120">				
					<tr>
						<td align="right"><font color="red">*</font>选择文件：</td>
						<td>
							<input class="text_style" required="true" missingMessage="请选择文件!" type="text" id="txt2" name="txt2" style="width:300px;"/>
					        <input style="width:70px;" type="file" name="file" id="file" onchange="txt2.value=this.value"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" >
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="contractManageList.uploadContractFile()">上传</a>
						</td>
					</tr>
				</table>
				<input type="hidden" id="uppid" name="pid" value="">
			</form>
		</div>
		
	</div>
</body>