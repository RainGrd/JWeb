<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<body class="easyui-layout">
	
	<%-- 信息管理窗口 --%>
	<div class="easyui-dialog" fitColumns="true"  id="smsTemplates" buttons="#dlg_buttons_xuanZe" 
		style="width:600px;padding: 10px;height: 450px;" data-options="modal:true" closed="true" >
		<table id="smsTemplatesGrid"></table>
	</div>
	<%-- 选择确认按钮 --%>
	<div id="dlg_buttons_xuanZe">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="commonActivity.saveSmsTemplates()">选择</a>
	</div>
	<%-- 查询条件 --%>
	<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
		<form action="" method="post" id="searcm" name="searcm">
			<div style="padding: 5px">
				<table class="beforeloanTable">
					<tr>
						<td>短信编号：</td>
						<td>
							<input class="easyui-textbox" name="tempCode" id="tempCode" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="commonActivity.searchData()">查询</a>
						</td>
					</tr>
				</table>	
			</div>
		</form>
	</div>
	
</body>