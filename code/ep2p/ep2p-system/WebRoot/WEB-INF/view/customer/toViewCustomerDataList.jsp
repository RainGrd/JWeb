<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<input type="hidden" id="pid" name="pid" value="${pid}">
<div data-options="region:'center',border:false">
	<div class="easyui-tabs diseaseGatherParamDiv detailFormWrap" id="toGatherDetailsTabs" fit="true" border="false" style="overflow-x:hidden;" data-options="width:'100%'">
    	<div title="客户资料" id="tab1" class="p10"
    		data-options="href:'<%=basePath%>customerController/selectCusHistoryById.shtml?pid=+${pid}'"> 
		 </div>
		 <div title="投标记录" id="tab2" class="pt10"
			 data-options="href:'<%=basePath%>bizBorrowDetailController/toSelectBidRecordList.shtml?pid=+${pid}'">
		  </div>

		 <div title="资金流水" id="tab3" class="pt10"
		 data-options="href:'<%=basePath%>bizBorrowDetailController/toSelectFundWaterList.shtml?pid=+${pid}'">
		 </div>
	</div>  
</div>
<script type="text/javascript" src="${basePath}resources/js/custom/selectCusDetailsById.js"></script>
<script>
//加载文件
$(document).ready(function(){
	bank.loadData();
});
</script>
</body>