<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<body class="easyui-layout">
<input type="hidden" id="pid" name="pid" value="${pid}">
<div data-options="region:'center',border:false">
	<div class="easyui-tabs diseaseGatherParamDiv detailFormWrap" id="toGatherDetailsTabs1" border="false" style="overflow-x:hidden;" data-options="selected:${flag},width:'100%',height:'355'">
    	<div title="加息" id="tab1" class="p10"
    		data-options="href:'<%=basePath%>customerController/toCusjiaxiDetailsList.shtml?pid='+${pid}">
		 </div>
		 <div title="积分" id="tab2" class="p10"
		 	data-options="href:'<%=basePath%>customerController/toCusPointDetailsList.shtml?pid='+${pid}">
		  </div>
		 <div title="经验" id="tab3" class="pt10"
		 	data-options="href:'<%=basePath%>customerController/toJingyanDetailsList.shtml?pid='+${pid}">
		 </div>
		 <div title="红包" id="tab4" class="pt10"
		 	data-options="href:'<%=basePath%>customerController/toHongbaoDetailsList.shtml?pid='+${pid}">
		 </div>
	</div>  
</div>
</body>