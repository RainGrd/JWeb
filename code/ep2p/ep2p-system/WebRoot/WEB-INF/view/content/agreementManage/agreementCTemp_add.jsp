<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/handle/agreementManageHandle.js"></script>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<!-- 表单信息 -->
	<div class="p10">
		<form id="agreementCTemp_add_from" method="post" >
		<table class="fromtable">
			<tr>
				 <td class="align_right" ><span class="red">*</span> 协议模版名称：</td>
				 <td >
				 	<input type="text" class="easyui-textbox" name="agrContTempName" required="required" > 
				 </td>
			</tr>
			<tr>
				 <td class="align_right" >&nbsp;</td>
				 <td >
				 </td>
			</tr>
			<tr>
				 <td class="align_right" ><span class="red">*</span> 状态：</td>
				 <td>
				 	<input type="radio" name="status" value="1" checked="checked" /> 启用 
				 	&nbsp;&nbsp;
				 	<input type="radio" name="status" value="2" /> 禁用 
				 </td>
			</tr>
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