<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript">
	var ep2pUrl = "${ep2pUrl}";
</script>
<script type="text/javascript" src="${basePath}resources/js/business/model/invest_info_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/invest_info.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<!-- 查询条件 -->
				<form action="" method="post" id="invest_manage_form" name="invest_manage_form">
					<div style="padding: 5px">
						<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right">借款编号：</td>
								<td><input class="easyui-textbox"
									name="borrowCode" id="borrowCode" /></td>
								<td class="align_right">借款名称：</td>
								<td><input class="easyui-validatebox" name="borrowName"
									id="borrowName" /></td>
							</tr>
							<tr>
								<td class="align_right">投资人：</td>
								<td><input class="easyui-textbox"
									name="customerName" id="customerName" /></td>
								<td class="align_right">待收时间：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" id="beginInvestmentTime"
											name="startExpireTime" data-options="editable:false" /> ~
											<input
											class="easyui-datebox" id="endInvestmentTime"
											name="endExpireTime" data-options="editable:false" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="align_right">状态：</td>
								<td><input name="receiptStatus" id="receiptStatus"
								class="easyui-validatebox easyui-combobox"
								data-options="editable:false,loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=RECEIPT_STATUS%>'" />
								</td>
								<td class="align_right">待收时间：</td>
								<td><input name="timeType" id="timeType"
								class="easyui-validatebox easyui-combobox"
								data-options="editable:false,loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=FOR_CLOSED_TYPE_TIME%>'" />
								</td>
							</tr>
							
							<tr class="moreTr">
								<td class="align_right">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="InvestManage.toDownloadPage();">导出excel</a>
								</td>
								<td>
							
								</td>
								<td class="align_right"></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-search"'
									onclick="InvestManage.searchData()">查询</a> 
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-clear"'
									onclick="InvestManage.resetForm()">重置</a>
								</td>
							</tr>
							
						</table>							
					</div>
				</form>
		</div>
		<div class="showDataListWrap">
			<table id="invest_manage_grid"></table>
		</div>
	</div>
</body>