<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/handle/agreementManageHandle.js"></script>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<!-- 表单信息 -->
	<div class="p10">
		<form id="contract_eidt_from" method="post" enctype="multipart/form-data">
		<table class="formTable">
			<tr class="p10">
				 <td class="align_right" ><span class="red">*</span> 协议内容模版名称 :</td>
				 <td >
				 	<input type="hidden" name="pid" id="pid" value="${bbaO.pid}" />
				 	<input type="text" class="easyui-textbox" id="borAgrAnme" name="borAgrAnme" required="required" value="${bbaO.borAgrAnme}" > 
				 </td>
			</tr>
			<tr class="p10">
				 <td class="align_right" ><span class="red">*</span> 状态  :</td>
				 <td>
				 	<c:if test="${bbaO.status eq '1'}">
					 	<input type="radio" name="status" value="1" checked='checked' /> 启用 
					 	&nbsp;&nbsp;
					 	<input type="radio" name="status" value="2" /> 禁用 
				 	</c:if>
				 	<c:if test="${bbaO.status eq '2'}">
					 	<input type="radio" name="status" value="1"  /> 启用 
					 	&nbsp;&nbsp;
					 	<input type="radio" name="status" value="2" checked='checked' /> 禁用 
				 	</c:if>
				 </td>
			</tr>
			<tr class="p10">
				 <td class="align_right" ><span class="red"></span> 协议模版  :</td>
				 <td>
					<input id="status" name="agrConTemId" id="agrConTemId"
							class="easyui-validatebox easyui-combobox" panelHeight="auto"
							data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'pid',
	           							 textField:'agrContTempName',
	           							 multiple:false,
	           							 value:'${bbaO.agrConTemId}',
	           							 url:'<%=AGREEM_TEMP%>'" />
				</td>
			</tr>
			<c:if test="${bbaO.fileId != '' }">
				<tr class="p10">
					<td class="align_right" ><span class="red">*</span> 文件路径  :</td>
					<td id="upfile_tag">
					 	<a href="javascript:void(0);" onclick="contractManageList.downloadContractFile('${bbaO.pid}','temp')">下载</a>
					 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="contractManageList.updateUpfileTag()"></a>
					</td>
				</tr>
			</c:if>
			<c:if test="${bbaO.fileId == '' }">
				<tr class="p10">
					 <td class="align_right" ><span class="red">*</span> 文件路径  :</td>
					 <td>
					 	<!-- 
					 	<input class="text_style" required="true" missingMessage="请选择文件!" type="text" id="txt2" name="txt2" />
						<input style="width:70px;" type="file" name="file" id="borrowFile" onchange="txt2.value=this.value"/> 
						-->
						<input class="easyui-filebox" name="file" id="file" required="required" validType="doctypevalid" data-options="buttonText:'选择文件'" style="width:100%">
					</td>
				</tr>
			</c:if>
		</table>
		</form>
	</div>
	
	<!-- 表单按钮 -->
	<!-- <div id="agreementCTemp_add_toolbut">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="">保存</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-colse" plain="true" onclick="">返回</a>
	</div> -->
	
</div>
</body>