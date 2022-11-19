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
		BizWithdraw.selectReferendumTask({auditStatus:1},"#biz_withdraw_grid");
		//设置下拉列表框 初始值为 （s申请）
		$('#auditStatus').combobox('setValue', '1');
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
		BizWithdraw.init("#biz_withdraw_grid","提现审核","#biz_withdraw_toolbar",{queryStatus:1},1);	
	}
});
</script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="biz_withdraw_toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
				<!-- 查询条件 -->
				<form action="" method="post" id="biz_withdraw_form" name="biz_withdraw_form">
					<input type="hidden" name="queryStatus" value="1">
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
									审核状态：									
								</td>
								<td><input id="auditStatus" name="auditStatus"
									class="easyui-combobox"
									data-options="editable:'false',loadFilter:common.dictionaryFilter,
									valueField:'dictContCode',textField:'dictContName',multiple:false,
									url:'<%=basePath%>sysDistionaryContentController/selectByDisctCode.shtml?dictCode=AUDIT_STATUS'" />
								</td>
								<td></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" 
									data-options="iconCls:'icon-search'"
									onclick="BizWithdraw.searchButClick('#biz_withdraw_grid',1)">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" 
									data-options="iconCls:'icon-clear'"
									onclick="BizWithdraw.resetButClick('#biz_withdraw_form')">重置</a>
								</td>
							</tr>
							
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="BizWithdraw.batchAudit()">批量审核</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export',plain:'true'" onclick="BizWithdraw.toDownloadPage('biz_withdraw_grid','biz_withdraw_form');">导出EXCEL</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="biz_withdraw_grid">
			</table>
		</div>
	</div>
</body>