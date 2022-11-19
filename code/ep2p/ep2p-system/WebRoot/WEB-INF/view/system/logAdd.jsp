<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/dictionary.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="<%=basePath%>sysDistionaryController/create.shtml" id="distionaryAdd" name="distionaryAdd" method="post" >
	<input type="hidden" id="pid" name="pid" value="${sysDistionary.pid}">
	<div>
		<table>
			<tr>
				<td class="label_right"><font color="red">*</font>数据类型编码：</td>
				<td> <input class="easyui-textbox" id="dictCode" name="dictCode" required="true" data-options="validType:'length[0,20]'"  style="width:150px" missingMessage="请输入数据类型编码!"  /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>数据类型名称：</td>
				<td> <input class="easyui-textbox" id="dictName" name="dictName" required="true" data-options="validType:'length[0,20]'"  style="width:150px" missingMessage="请输入数据类型名称!"    /> </td>
			</tr>
			<tr>
				<td class="label_right">修改标志：</td>
				<td> <input type="radio" name="isUpdate" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="isUpdate" value="0" />否 </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>描述：</td>
				<td> 
					<input name="dictDesc" id="dictDesc" required="true"   missingMessage="描述!" class="easyui-textbox" style="width:65%;height:60px" data-options="multiline:true,validType:'length[0,800]'" >
				</td>
			</tr>
		</table>
	</div>
</form>
</body>