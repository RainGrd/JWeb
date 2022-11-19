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
		BizWithdraw.selectReferendumTask({auditStatus:2},"#transfer_affirm_grid");
		//设置下拉列表框 初始值为 （s申请）
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
		BizWithdraw.init("#transfer_affirm_grid","转账确认","#transfer_affirm_toolbar",{queryStatus:2},2);	
	}
});
</script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="transfer_affirm_toolbar" class="easyui-panel"
			style="border-bottom: 0;">
			<div>
				<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
				<!-- 查询条件 -->
				<form action="" method="post" id="transfer_affirm_form"
					name="transfer_affirm_form">
					<input type="hidden" name="queryStatus" value="2">
					<div style="padding: 5px">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right">客户名：</td>
								<td><input class="easyui-textbox" name="customerName"
									id="customerName" /></td>
								<td class="label_right">姓名：</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
								<td class="label_right">手机号码：</td>
								<td><input class="easyui-textbox" name="phoneNo"
									id="phoneNo" /></td>
							</tr>
							<tr>
								<td class="label_right">申请时间：</td>
								<td><input class="easyui-datebox" name="applyTime"
									id="applyTime" /></td>
								<td><input id="auditStatus" name="auditStatus"
									type="hidden" value="" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'"
									onclick="BizWithdraw.searchButClick('#transfer_affirm_grid',2)">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'icon-clear'"
									onclick="BizWithdraw.resetButClick('#transfer_affirm_form')">重置</a>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>

			<!-- 操作按钮 -->
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'"
				onclick="BizWithdraw.batchAffirm()">批量转账确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-export',plain:'true'"
				onclick="BizWithdraw.toDownloadPage('transfer_affirm_grid','transfer_affirm_form')">导出EXCEL</a>
		</div>

		<div class="showDataListWrap">
			<table id="transfer_affirm_grid">
			</table>
		</div>
	</div>
</body>