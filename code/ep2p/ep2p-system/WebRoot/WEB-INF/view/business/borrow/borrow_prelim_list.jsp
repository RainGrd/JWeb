<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/model/borrowPrelimManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow_approve.js" charset="utf-8"></script>
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
								<td class="label_right"> 借款人：</td>
								<td><input class="easyui-validatebox" name="customerName" id="customerName" /></td>
								<td class="label_right"> 姓名：</td>
								<td><input class="easyui-validatebox" name="sname" id="sname" /></td>
							</tr>
							<tr>
								<td class="label_right"> 手机号码：</td>
								<td><input class="easyui-validatebox" name="phoneNo" id="phoneNo" /></td>
								<td class="label_right"> 申请时间：</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox" name="startApproveTime" id="startApproveTime" />
									- 
									<input class="easyui-datebox" name="endApproveTime" id="endApproveTime" />
									</div>
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
	           							 url:'<%=BORROW_STATUS_VOUCH%>'" />
	           						&nbsp;&nbsp;&nbsp;	 
	           						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowApprove.searchPrelimList()">查询</a>&nbsp;&nbsp;&nbsp;
	           						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="javascript:$('#searcm').form('reset');">重置</a> 
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
		</div>
	
		<div class="showDataListWrap" id="borrowPrelimGridDiv">
			<table id="borrowPrelimGrid"></table>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			//判断是否从任务列表进入审核列表的
			var isTaskListOpen = $("#isTaskListOpen").val();
			if("true"==isTaskListOpen){
				//查询任务为  （审核中的任务）
				borrowApprove.selectReferendumPrelimTask();
				//设置下拉列表框 初始值为 （审核中）
				$('#approveStatus').combobox('setValue', '1');
			}else{
				borrowApprove.initBorrowPrelimDataGrid();
			}
		})
	</script>
</body>