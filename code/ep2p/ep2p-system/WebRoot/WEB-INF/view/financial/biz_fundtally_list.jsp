<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/finance/model/biz_fundtally_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/biz_fundtally.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	BizFundtally.initDataGrid();	
});
</script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="biz_fundtally_toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
				<!-- 查询条件 -->
				<form action="" method="post" id="biz_fundtally_form" name="biz_fundtally_form">
					<div style="padding: 5px">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right">
									客户名： 									
								</td>
								<td><input class="easyui-textbox" name="customer.customerName" id="customerName" /></td>
								<td class="label_right">
									姓名： 									
								</td>
								<td><input class="easyui-textbox" name="customer.sname" id="sname" /></td>
								<td class="label_right">
									手机号码： 								
								</td>
								<td><input class="easyui-textbox" name="customer.phoneNo" id="phoneNo"/></td>
							</tr>
							<tr>
								<td class="label_right">
									日期： 									
								</td>
								<td>
									<div class="rangDate">
									<input class="easyui-datebox" id="beginActorTime" name="beginActorTime"     
        							data-options="showSeconds:true"> ~
									<input class="easyui-datebox" id="endActorTime" name="endActorTime"     
       								data-options="showSeconds:true">
       								</div> 
								</td>
								<td class="label_right">
									流水类型：									
								</td>
								<!-- FUND_WATER -->
								<td><input id="detailType" name="detailType"
									class="easyui-combobox"
									data-options="editable:'false',loadFilter:common.dictionaryFilter,
									valueField:'dictContCode',textField:'dictContName',multiple:false,
									url:'<%=basePath%>sysDistionaryContentController/selectByDisctCode.shtml?dictCode=SYSTEM_TRADE_TYPE'" />
								</td>
								<td></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" 
									data-options="iconCls:'icon-search'"
									onclick="BizFundtally.searchButClick('#biz_fundtally_grid')">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" 
									data-options="iconCls:'icon-clear'"
									onclick="BizFundtally.resetButClick('#biz_fundtally_form')">重置</a>
								</td>
							</tr>
							
						</table>	
					</div>
				</form>
			</div>	
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export',plain:'true'" onclick="BizFundtally.toDownloadPage()">导出EXCEL</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="biz_fundtally_grid">
			</table>
		</div>
	</div>
</body>