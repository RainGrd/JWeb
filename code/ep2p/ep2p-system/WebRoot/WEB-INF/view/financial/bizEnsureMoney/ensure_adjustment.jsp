<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div class="formWrap" data-options="region:'center',border:false">
		<form id="role_add_or_update_form" name="menu_add_child_node_form" method="POST">
			<input type="hidden" id="ensureType" name="ensureType" value="${type}">
			<table class="role_form_table_one">
				<tr>
					<td class="align_right" > 类型：</td>
					<c:if test="${type == 2007 }">
						<td>初始化备付金</td>
					</c:if>
					<c:if test="${type == 2010 }">
						<td>调减备付金</td>
					</c:if>
					<c:if test="${type == 2009 }">
						<td>调增备付金</td>
					</c:if>
				</tr>
				<tr>
					<td class="align_right" > 金额： </td>
					<td>
						<input class="easyui-numberbox" precision="2" groupSeparator="," name="amount" id="amount" min="0" style="width: 200px;" required="true" missingMessage="请输入金额!" />
					</td>
				</tr>
				<tr>
					<td class="align_right">备注信息： </td>
					<td>
						<input class="easyui-textbox" style="width:65%;height:60px;min-width:300px;" name="ensMonDetDesc" id="ensMonDetDesc" data-options="multiline:true,validType:'length[0,255]'" required="true" missingMessage="请输入备注信息!" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>