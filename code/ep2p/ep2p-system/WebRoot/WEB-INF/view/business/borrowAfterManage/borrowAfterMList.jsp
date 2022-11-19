<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/business/model/borrowAfterManage_Model.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/borrowAfterManage/borrowAfterManageHandle.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 搜索选项及工具栏 -->
		<div id="borrowAfterM_list_toolbar">
		<div>
			<input type="hidden" id="isTaskListOpen" value="${nextMonth}">
			<!-- 查询条件 -->
			<form action="" method="post" id="borrowAfterM_search_from" >
				<div style="padding: 5px">
					<table class="userTable percent90 formTable">
						<tr>
							<td class="align_right">借款编号：</td>
							<td><input class="easyui-textbox" name="borrowCode" id="borrowCode" /></td>
							<td class="align_right">借款名称：</td>
							<td><input class="easyui-textbox" name="borrowName" id="borrowName"/></td>
						</tr>
						<tr>
							<td class="align_right">客户账号：</td>
							<td><input class="easyui-textbox" name="customerName" id="customerName" /></td>
							<td class="align_right">姓名：</td>
							<td><input class="easyui-textbox" name="sname" id="sname" /></td>
						</tr>
						<tr>
							<td class="align_right">手机号码：</td>
							<td><input class="easyui-textbox" name="phoneNo" id="phoneNo" /></td>
							<td class="align_right">还款时间：</td>
							<td>
								<div class="rangDate">
									<input class="easyui-datebox" id="repaidTime" name="repaidTime" data-options="editable:false" value="${repaidTime}"/>
									<input class="easyui-datebox" id="repaidEndTime" name="repaidEndTime" data-options="editable:false"  value="${repaidEndTime}"/>
									<a href="javascript:void(0);" iconCls="icon-more" onclick="borrowAfterManage.searchHidenShow()">更多条件</a>
								</div>
							</td>
						</tr>
						
						
						<tr class="moreTr none">
							<td class="align_right">借款类型：</td>
							<td>
								<input name="borrowType" editable="false" id="borrowType" class="easyui-validatebox easyui-combobox" 
										data-options="loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=BORROW_TYPE %>'" />
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr class="moreTr none">
							<td class="align_right">金额范围：</td>
							<td>
								<input class="easyui-validatebox" id="borrowMoney" name="borrowMoney" style="width: 100px;"/>~
								<input class="easyui-validatebox" id="borrowEndMoney" name="borrowEndMoney"  style="width: 100px;"/>
							</td>
							<td class="align_right">利率范围(%)：</td>
							<td>
								<input class="easyui-validatebox" id="borrowApr" name="borrowApr" style="width: 100px;"/>~
								<input class="easyui-validatebox" id="borrowEndApr" name="borrowEndApr" style="width: 100px;" />
							</td>
						</tr>
						<tr class="moreTr none">
							<td class="align_right">期限范围（月）：</td>
							<td>
								<input class="easyui-validatebox" id="planIndex" name="planIndex" style="width: 100px;" />~
								<input class="easyui-validatebox" id="planEndIndex" name="planEndIndex" style="width: 100px;" />
							</td>
						
							<td class="align_right">还款方式：</td>
							<td>
								<input name="repaymentType" editable="false" id="repaymentType" class="easyui-validatebox easyui-combobox" 
									data-options="loadFilter:common.dictionaryFilter,
													valueField:'dictContCode',
													textField:'dictContName',
													multiple:false,
													url:'<%=REPAYMENT_TYPE %>'" />
							</td>
						</tr>
						<tr class="moreTr none">
							<td class="align_right">计息方式：</td>
							<td>
								<input name="accrualType" editable="false" id="accrualType" class="easyui-validatebox easyui-combobox" 
									data-options="loadFilter:common.dictionaryFilter,
													valueField:'dictContCode',
													textField:'dictContName',
													multiple:false,
													url:'<%=ACCRUAL_TYPE %>'" />
							</td>
							<td class="align_right">逾期天数</td>
							<td>
								<input name="overdueDays" editable="false" id="overdueDays" class="easyui-validatebox easyui-combobox" 
									data-options="valueField:'dictContCode',
												textField:'dictContName',
												multiple:false,
												data:[
													{dictContCode:'',dictContName:'--请选择--'},
													{dictContCode:'15',dictContName:'15天内待还款'},
													{dictContCode:'30',dictContName:'30天内待还款'}
												]" />
							</td>
						</tr>
						<tr>
							<td class="align_right">状态：</td>
							<td>
								<input name="receiptPalnStatus" editable="false" id="receiptPalnStatus" class="easyui-validatebox easyui-combobox" 
									data-options="loadFilter:common.dictionaryFilter,
													valueField:'dictContCode',
													textField:'dictContName',
													multiple:false,
													url:'<%=BIZ_REPLAN_STATUS %>'" />
							</td>
							<td></td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowAfterManage.searchMoreParam()">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="borrowAfterManage.resetBorrowAfterForm()">重置</a>
							</td>
						</tr>
					</table>							
				</div>
			</form>

			<!-- tool button  -->
			<div class="p10">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="borrowAfterManage.toDownloadPage();">导出excel</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remind" plain="true" onclick="borrowAfterManage.toSendSMSPage();">批量提醒</a>
			</div>
			
		</div>
		</div>
		
		<!-- 合同管理内容信息 -->
		<div id="borrowAfterM_context">
			<div class="showDataListWrap">
				<table id="borrowAfterM_datagrid_list" ></table>
			</div>
		</div>
	</div>
</body>