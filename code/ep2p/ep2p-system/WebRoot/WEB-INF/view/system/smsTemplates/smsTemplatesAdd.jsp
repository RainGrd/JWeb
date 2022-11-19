<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/smsTemplates.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="<%=basePath%>sysDistionaryController/create.shtml" id="smsTemplatesAdd" name="smsTemplatesAdd" method="post" >
	<input type="hidden" id="pid" name="pid" value="${smsTemplates.pid}">
	<div class="pt10">
		<table class="formTable">
			<tr>
				<td class="label_right" style="width: 100px"><font color="red">*</font>短信编码：</td>
				<td> <input class="easyui-textbox" id="tempCode" name="tempCode" value="${smsTemplates.tempCode}" required="true" data-options="validType:'length[0,20]'"  style="width:150px" missingMessage="请输入短信编码!"  /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>短信名称：</td>
				<td> <input class="easyui-textbox" id="smsTempName" name="smsTempName" value="${smsTemplates.smsTempName}" required="true" data-options="validType:'length[0,20]'"  style="width:150px" missingMessage="请输入短信名称!"    /> </td>
				<td rowspan="3" class="label_right">替换字符：</td>
				<td rowspan="3">
					 <ul class="easyui-datalist" style="width:120px;height:100px" id="smsKeyword" >
					 	
					</ul>
				</td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>短信内容：</td>
				<td><input class="easyui-textbox" id="smsContent" name="smsContent" value="${smsTemplates.smsContent}" required="true" data-options="multiline:true,validType:'length[0,250]'" style="width:100%;height:60px" missingMessage="请输入短信内容!"    /> </td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$("#smsKeyword").datalist({
			url: BASE_PATH+'sysDistionaryContentController/selectByDisctCodeToDataList.shtml?dictCode='+'SMS_TEMP_REPLICE',
			valueField:'dictContCode',
			textField:'dictContName', 
			onDblClickRow: function (rowIndex, rowData) {  
				var smsContent = $("#smsContent").textbox('getValue');
				smsContent +=rowData.dictContName;
				$("#smsContent").textbox('setValue',smsContent);
			}
		})
	</script>
</form>
</body>
