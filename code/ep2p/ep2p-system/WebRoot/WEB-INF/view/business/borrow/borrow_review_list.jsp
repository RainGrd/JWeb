<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow_approve.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/model/borrowReviewManager_Model.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div class="pt5"> 
						<table class="beforeloanTable formTable"  width="90%">
							<tr>
								<td class="label_right" width="126"> 借款人：</td>
								<td width="280"><input class="easyui-textbox" name="customerName" id="customerName" /></td>
								<td class="label_right"  width="126"> 姓名：</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
							</tr>
							<tr>
								<td class="label_right"> 手机号码：</td>
								<td><input class="easyui-textbox" name="phoneNo" id="phoneNo" /></td>
								<td class="label_right"> 申请时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox" name="startApproveTime" id="startApproveTime"/>
									- 
									<input class="easyui-datebox" name="endApproveTime" id="endApproveTime"/> <a href="#" onclick="borrowApprove.moreConditions();" >更多条件</a>
									</div>
								</td>
							</tr>
							<tr class="more none">
								<td class="label_right"> 借款编号：</td>
								<td width="280"><input class="easyui-textbox" style="width:173px;" name="borrowCode" id="borrowCode" /></td>
								<td class="label_right"> 借款名称：</td>
								<td>
									<input class="easyui-textbox"  style="width:173px;" name="borrowName" id="borrowName" />
								</td>
							</tr>
							<tr class="more none">
								<td class="label_right"> 金额范围：</td>
								<td>
									<input class="easyui-numberbox" name="startBorrowMoney" id="startBorrowMoney" style="width: 100px;"/>
									- 
									<input class="easyui-numberbox" name="endBorrowMoney" id="endBorrowMoney" style="width: 100px;"/>
								</td>
								<td class="label_right"> 利率范围（%）：</td>
								<td>
									<input class="easyui-numberbox" name="startBorrowRate" id="startBorrowRate" style="width: 100px;"/>
									- 
									<input class="easyui-numberbox" name="endBorrowRate" id="endBorrowRate" style="width: 100px;"/>
								</td>
							</tr>
							<tr class="more none">
								<td class="label_right"> 期限范围（月）：</td>
								<td>
									<input class="easyui-numberbox" name="startBorDeadline" id="startBorDeadline" style="width: 100px;"/>
									- 
									<input class="easyui-numberbox" name="endBorDeadline" id="endBorDeadline" style="width: 100px;"/>
								</td>
								<td class="label_right"> 还款方式：</td>
								<td>
									<input id="repaymentType" name="repaymentType" class="easyui-combobox" panelHeight="auto"
           							 data-options="loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=REPAYMENT_TYPE%>'" />
								</td>
							</tr>
							<tr class="more none">
								<td class="label_right"> 计息类型：</td>
								<td>
									<input id="accrualType" name="accrualType" class="easyui-combobox" panelHeight="auto"
           							 data-options="loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=ACCRUAL_TYPE%>'" />
								</td>
								<td class="label_right"> </td>
								<td>
								</td>
							</tr>
							<tr>
								<td class="label_right"> 借款类型：</td>
								<td>
									<input id="borrowType" name="borrowType" class="easyui-combobox"
										 panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 multiple:false,
	           							 url:'<%=BORROW_TYPE_SERACH%>'" />
								</td>
								<td class="label_right"> 审核状态：</td>
								<td>
									<input id="approveStatus" name="approveStatus" class="easyui-combobox"
										 panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 multiple:false,
	           							 url:'<%=BORROW_STATUS_LEND%>'" />
	           						&nbsp;&nbsp;&nbsp;	 
	           						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowApprove.searchReviewList()">查询</a> &nbsp;&nbsp;&nbsp;
	           						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="javascript:$('#searcm').form('reset');">重置</a> 
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
		</div>
	
		<div class="showDataListWrap" id="borrowReviewGridDiv">
			<table id="borrowReviewGrid"></table>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			//判断是否从任务列表进入审核列表的
			var isTaskListOpen = $("#isTaskListOpen").val();
			if("true"==isTaskListOpen){
				//查询任务为  （审核中的任务）
				borrowApprove.selectReferendumReviewTask();
				//设置下拉列表框 初始值为 （审核中）
				$('#approveStatus').combobox('setValue', '1');
			}else{
				borrowApprove.initBorrowReviewDataGrid();	
			}
		})
	</script>
</body>