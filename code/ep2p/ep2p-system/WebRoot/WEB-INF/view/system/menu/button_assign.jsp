<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript" src="${basePath}resources/js/system/model/button_model.js" charset="utf-8"></script>
	<script type="text/javascript" src="${basePath}resources/js/system/menu/button_assign.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//按钮加载
			ButtonAssign.initChoosableButtonGrid();
			ButtonAssign.initSelectedButtonGrid();
		});
	</script>
	<div id="user_assign" class="easyui-panel" data-options="fit:true">
		<input type="hidden" value="${menuId}" name="menuId" id="menuId" />
		<!-- 查询条件 -->
		<form action="" method="post" id="user_assign_form" name="user_assign_form">
			<div style="padding: 5px">
				<table class="beforeloanTable">
					<tr>
						<td class="label_right">
							<div class="showDataListWrap">
								<table id="choosable_button_grid"></table>
							</div>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton" onclick="ButtonAssign.buttonAssign()">&gt;&gt;</a>
							<br/>
							<a href="#" class="easyui-linkbutton" onclick="ButtonAssign.cancelButtonAssign()">&lt;&lt;</a>
						</td>
						<td class="label_right">
							<div class="showDataListWrap">
								<table id="selected_button_grid"></table>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>