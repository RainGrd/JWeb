<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/finance/model/biz_withdraw.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="<%=basePath%>bizWithdrawController/create.shtml" id="transferServiceAdd" name="transferServiceAdd" method="post" >
	<div class="formWrap">
		<input type="hidden" id="pid" name="pid" value="${pid}">
		<table >
			<tr>
				<td class="label_right">审核状态：</td>
				<td> 
					<input type="radio" name="auditStatus" value="4" checked="checked" onclick="BizWithdraw.getSelectData('<%=DESC_PROMPT_SUCCESS%>');" />转账成功&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="auditStatus" value="5" onclick="BizWithdraw.getSelectData('<%=DESC_PROMPT_ERROR%>');" />转账失败
				</td>
			</tr>
				<tr>
					<td class="label_right">备注提示：</td>
					<td><input id="descPromptId" name="descPromptId"
						class="easyui-combobox"style="width: 250px"
						data-options="onSelect:BizWithdraw.setWitSureDesc,
						loadFilter:common.dictionaryFilter,valueField:'dictContCode',
						textField:'dictContName',multiple:false,
						url:'<%=DESC_PROMPT_SUCCESS%>'" />
					</td>
				</tr>
				<tr>
				<td class="label_right"><font color="red">*</font>备注信息：</td>
				<td> 
					<input name="witSureDesc" id="witSureDesc" 
					class="easyui-textbox" style="width:300px;height:60px" data-options="required:true, missingMessage:'描述!', multiline:true,validType:'length[0,800]'" >
				</td>
			</tr>
		</table>
	</div>
</form>
</body>