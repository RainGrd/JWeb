<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/custom/batchReplaceCustomer.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/customerSearchList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/batchReplaceCustomerModel.js"></script>
<script type="text/javascript">
batchReplaceCus.initDataGrid();
</script>
	<div data-options="region:'center',border:false">
		<div id="batchToolbar">
			<form id="baseInfo" name="baseInfo" action="<%=basePath%>customerController/batchUpdateCus.shtml" method="POST">
			<table class="percent90 formTable" >
			   <tr>
			   	  <td class="align_right" width="120"><span class="cus_red">*</span>选择要更换的客服：</td>
					<td>
					    <input id="cusServicePidDlg" name="cusServicePid" class="easyui-combobox"
					         panelHeight="auto"
							 data-options="loadFilter:common.dictionaryFilter,
							 valueField:'cusServicePid',
							 textField:'cusServiceName',
							 multiple:false,
							 editable:false,
							 url:'<%=basePath%>/customerController/selectCustomer.shtml'"/>
					</td>
			   </tr>
			   <tr>
			      <td class="align_right" ><span class="cus_red">*</span>更换说明：</td>
			       <td>
			       <textarea name="updSerWatDesc" class="easyui-validatebox percent70" required="true"></textarea>
			       </td>
			   </tr>
			</table>	
			<input type="hidden" id="pid" name="pid" value="${pid}" />
			<input type="hidden" id="oldcusServiceId" name="oldcusServiceId" value="${oldcusServiceId}" />
			</form>
		</div>
		<div class="showDataListWrap">
			<table id="showCusGrid"></table>
		</div>
	</div> 
	
	
</body>