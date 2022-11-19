<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e生财富后台管理</title>
<%@ include file="/common/common.jsp" %>
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>resources/images/favicon.ico" media="screen" />
</head>
<body class="easyui-layout" id="bodyLayout" data-options="fit:true" style="min-width:880px;">
	<div id="north" data-options="region:'north',border:false,href:'<%=basePath %>layout/north.jsp'" style="height:50px;padding:0px;overflow-y:hidden;border-color:none;background:#283747">
		
	</div>
		<!-- west width:235px -->
	<div id="west" data-options="region:'west',split:true,href:'<%=basePath %>layout/west.jsp'" style="width:190px;padding:0px;overflow-x:hidden;overflow-y:hidden;border:none;background:#bdc3c7">
		
	</div>

	<div id="center" style="border:none;" data-options="split:true,region:'center',title:'',href:'<%=basePath %>layout/center.jsp'">
	
	</div>
	<div id="alertMessage"></div>
	<script>
	$(document).ready(function(){
		 $('.layout-panel-west').addClass('none');
		 $('#bodyLayout').layout('collapse','west');
	});
	</script>
</body>
</html>