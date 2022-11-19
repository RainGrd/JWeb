<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath %>resources/js/extend/mainFrame.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/js/layout/center.js"></script>

<div id="centerFrame" class="easyui-tabs welcometab ">
	<div title="首页" data-options="href:'<%=basePath %>scheduleJobController/toTaskList.shtml'"></div>
</div>