<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/bizReceiptTransfer_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/bizReceiptTransfer.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
				<!-- 查询条件 -->
				<form action="" method="post" id="invest_manage_form" name="invest_manage_form">
					<div style="padding: 5px">
						<table class="userTable percent90 formTable">
							<tr>
								<td colspan="7">项目信息</td>
							</tr>
							<tr>
								<td class="align_right">借款编号：</td>
								<td><input class="easyui-textbox" name="borrowCode" /></td>
								<td class="align_right">借款名称：</td>
								<td><input class="easyui-validatebox" name="borrowName"  /></td>
								<td class="align_right">申请时间：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" name="applyStartTime" data-options="editable:false" /> ~
										<input name="applyEndTime" class="easyui-datebox" data-options="editable:false" />
									</div>
										
								</td>
								<td>	<a href="javascript:void(0);" data-options='iconCls:"icon-more"'
										onclick="InvestManage.searchCd()">更多条件</a> </td>
							</tr>
							<tr class="moreTr none">
								<td colspan="7"></td>
							</tr>
							<tr class="moreTr none">
								<td colspan="7">转让客户信息</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">客户名：</td>
								<td><input name="createUserName" class="easyui-validatebox non" style="width: 200px" /></td>
								<td class="align_right">姓名：</td>
								<td><input  name="createUserSName"  class="easyui-validatebox non" /></td>
								<td class="align_right">手机号码：</td>
								<td><input name="createUserMobile" class="easyui-validatebox non" /></td>
								<td></td>
							</tr>
							<tr class="moreTr none">
								<td colspan="7"></td>
							</tr>
							<tr class="moreTr none">
								<td colspan="7">购买客户信息</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">客户名：</td>
								<td><input name="customerName" class="easyui-validatebox non" style="width: 200px"/></td>
								<td class="align_right">姓名：</td>
								<td><input name="customerSName"  class="easyui-validatebox non"  /></td>
								<td class="align_right">手机号码：</td>
								<td><input name="customerMobile" class="easyui-validatebox non" /></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="7"></td>
							</tr>
							<tr>
								<td class="align_right">发布时间：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" name="releaseStartTime" id="releaseStartTime"  data-options="editable:false" /> ~
										<input class="easyui-datebox" name="releaseEndTime" id="releaseEndTime" data-options="editable:false" />
								
									</div>
								</td>
								<td class="align_right">成交时间：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" name="successStartTime"  data-options="editable:false" /> ~
										<input class="easyui-datebox" name="successEndTime" data-options="editable:false" />
								
									</div>
								</td>
								<td colspan="3">
							
								</td>								
							</tr>
							<tr>
								<td class="align_right">状态：</td>
								<td>
									<input name="status" editable="false" id="status" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=RECEIPT_TANSFER_STATUS %>'" />
														
								</td>
								<td colspan="3">
								</td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-search"'
									onclick="InvestManage.searchData()">查询</a> 
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-clear"'
									onclick="InvestManage.resetForm()">重置</a>
								</td>				
							</tr>
							<tr>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="InvestManage.toDownloadPage();">导出excel</a>
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