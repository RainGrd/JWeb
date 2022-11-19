<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath %>resources/js/extend/mainFrame.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/js/layout/center.js"></script>
<div id="centerFrame" class="easyui-tabs welcometab ">
	<div title="首页" data-options="href:'<%=basePath %>layout/welcome.jsp'"></div>
</div>
<div id="layout_center_tabsMenu" style="width: 120px;display:none;">
	<div type="refresh">刷新</div>
	<div class="menu-sep"></div>
	<div type="close">关闭</div>
	<div type="closeOther">关闭其他</div>
	<div type="closeAll">关闭所有</div>
</div>
