<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/finance/model/biz_withdraw_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/biz_withdraw.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	//判断是否从任务列表进入审核列表的
	var isTaskListOpen = $("#isTaskListOpen").val();
	if("true"==isTaskListOpen){
		//查询待审核的任务
		BizWithdraw.selectReferendumTask();
		//设置下拉列表框 初始值为 （s申请）
		$('#auditStatus').combobox('setValue', '0');
	}else{
		/**
		 * @param gridId datagrid表格ID
		 * @param title 标题
		 * @param toolbar 工具栏
		 * @param queryParams 查询参数 (queryStatus 必须字段)
		 * 这个字段用于查询逻辑处理
		 * 查询状态queryStatus（提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4）
		 * @param status 提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4
		 */
		BizWithdraw.init("#withdraw_query_grid","转账已确认","#withdraw_query_toolbar",{queryStatus:4},4);	
	}
});
</script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="withdraw_query_toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="withdraw_query_form" name="withdraw_query_form">
					<input type="hidden" value="4" name="queryStatus">
					<div style="padding: 5px">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right">
									客户名： 									
								</td>
								<td><input class="easyui-textbox" name="customerName" id="customerName" /></td>
								<td class="label_right">
									姓名： 									
								</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
								<td class="label_right">
									手机号码： 								
								</td>
								<td><input class="easyui-textbox" name="phoneNo" id="phoneNo"/></td>
							</tr>
							<tr>
								<td class="label_right">
									申请时间： 									
								</td>
								<td><input class="easyui-datebox" name="applyTime" id="applyTime" /></td>
								<td class="label_right">
									转账状态：									
								</td>
								<td>
								<input id="auditStatus" name="auditStatus" class="easyui-combobox" editable="false" required="true"  panelHeight="auto"  
				            			data-options="loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=basePath%>sysDistionaryContentController/selectByDisctCode.shtml?dictCode=WITHDRAW_QUERY_STATUS'" />
								</td>
								<td></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" 
									data-options="iconCls:'icon-search'"
									onclick="BizWithdraw.searchButClick('#withdraw_query_grid',4)">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" 
									data-options="iconCls:'icon-clear'"
									onclick="BizWithdraw.resetButClick('#withdraw_query_form')">重置</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  onclick="alreadyTransferService.batchAudit()">批量转账确认</a> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true"  onclick="BizWithdraw.toDownloadPage('withdraw_query_grid','withdraw_query_form')">导出EXCEL</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="withdraw_query_grid">
			</table>
		</div>
	</div>
</body>