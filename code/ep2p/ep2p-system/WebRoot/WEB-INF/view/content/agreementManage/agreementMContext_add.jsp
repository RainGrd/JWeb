<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/handle/agreementMContextHandle.js"></script>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<!-- 表单信息 -->
	<div class="p10">
		<form id="agreementMC_add_from" method="post" >
		<table class="fromtable">
			<tr>
				 <td class="align_right" ><span class="red">*</span>内容名称：</td>
				 <td >
				 	<input type="hidden" name="agrConTemId" id="agrConTemId" value="${agrConTemId}"/>
				 	<input type="text" class="easyui-textbox" name="agrContName" required="required" > 
				 </td>
				 <td class="align_right" ><span class="red">*</span>关键字：</td>
				 <td>
				 	<input type="text" class="easyui-textbox" name="protocol" required="required" >
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