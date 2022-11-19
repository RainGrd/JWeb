<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/bizEnsureMoney/ensureDtailList.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/model/bizEnsureMoney/ensureDetailModel.js" charset="utf-8"></script>
		<div data-options="region:'center',border:false">
		<div id="biz_ensure_index_toolbar" class="easyui-panel" title="备付金使用明细">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="ensure_detail_list_form" name="ensure_detail_list_form">
					<div class="p5">
						<table class="beforeloanTable formTable">
							<tr>
								<td class="align_right" width="100">客户名：</td>
								<td>	
									<input class="easyui-validatebox" name="customerName" id="customerName" />
								</td>							
								<td class="label_right">姓名：</td>
								<td >
									<input class="easyui-validatebox" name="sname" id="sname" />
								</td>								
							</tr>
							<tr>
								<td class="label_right">手机号码：</td>
								<td >
									<input class="easyui-validatebox" name="phoneNo" id="phoneNo" />
								</td>								
								<td class="align_right" width="100">日期：</td>
								<td>	
									<input class="easyui-datebox" name="startDate" editable="false" />~
									<input class="easyui-datebox" name="endDate" editable="false" /> 
								</td>							
							</tr>
							<tr>
								<td class="label_right">类型：</td>
								<td >
									<input id="ensMonDetType" name="ensMonDetType" class="easyui-combobox"
										 panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 multiple:false,
	           							 url:'<%=ENSURE_DETAIL_TYPE%>'" />
								</td>								
								<td class="align_right" width="100">收入/支出：</td>
								<td>	
									<input id="feeType" name="feeType" class="easyui-combobox"
										 panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 multiple:false,
	           							 url:'<%=ENS_MON_FEE_TYPE%>'" />
	           						&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
									   onclick="ensureDetailList.searchData()">查询</a>
									   
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-clear'"
									   onclick="ensureDetailList.cleanData()">重置</a>
								</td>							
							</tr>
						</table>
						
					</div>
				</form>
			</div>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="ensureDetailList.toDownloadPage();">导出excel</a>
		</div>
		<div class="showDataListWrap" id="ensureDetailListTableDiv">
			<table id="ensureDetailListTable" >
			</table>
		</div>
	</div>
	</div>
</body>
	<script type="text/javascript">
		 $(document).ready(function(){
			 ensureDetailList.initGrid();	
		}); 
	</script>