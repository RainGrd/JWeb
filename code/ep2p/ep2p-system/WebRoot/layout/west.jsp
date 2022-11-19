<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath %>resources/js/system/menu/menu.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/js/layout/west.js"></script>

<div id="sidebar" class="sidebar responsive display" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">

<ul class="easyui-tree" id="west-tree"></ul>

</div>