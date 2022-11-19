<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/finance/model/bizWithdraw.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="<%=basePath%>bizWithdrawController/create.shtml" id="bizWithdrawAdd" name="bizWithdrawAdd" method="post" >
	<div class="formWrap">
		<input type="hidden" id="pid" name="pid" value="${pid}">
		<table >
			<tr>
				<td class="label_right">审核状态：</td>
				<td> 
					<input type="radio" name="auditStatus" value="2" checked="checked" />同意&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="auditStatus" value="3" />拒绝
				</td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>描述：</td>
				<td> 
					<input name="witDesc" id="witDesc" required="true"   
					missingMessage="描述!" class="easyui-textbox" 
					style="width:300px;height:60px" data-options="multiline:true,validType:'length[0,800]'" >
				</td>
			</tr>
		</table>
	</div>
</form>
</body>