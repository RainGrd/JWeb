<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/recharge/offlineClient/offline_recharge.js" charset="utf-8"></script>
	<div id="offline_index_toolbar" data-option="fit:true">
		<div class="p5">
			<%@ include file="../../../common/client_details.jsp" %>
			<!-- 客户充值 -->
			<form action="" method="post" id="client_rechagre_form" name="offline_index_form">
				<table class="client_rechagre_table percent90 formTable">
					<tr>
						<td class="align_right" width="100">充值金额：</td>
						<td><input type="text" class="easyui-validatebox"
							id="amount" name="amount" data-options="required:true,validType:['number']" /></td>
					</tr>
					<tr>
						<td class="align_right">备注信息：</td>
						<td>
							<textarea id="recOffDesc" class="easyui-validatebox percent70" name="recOffDesc" data-options="validType:['length[0,125]']" rows="5"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>