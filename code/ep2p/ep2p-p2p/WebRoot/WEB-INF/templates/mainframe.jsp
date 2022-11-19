<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<tiles:insertAttribute name="headmeta"></tiles:insertAttribute>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>theme/js/homepage/mainframe.js"></script>
	<title>E生财富</title>
</head>
<body>

	<!--滚动后 浮动导航  0 -->
	<tiles:insertAttribute name="navsmall"></tiles:insertAttribute>
	<!-- 顶部滚动广告 1 -->
	<tiles:insertAttribute name="remindtip"></tiles:insertAttribute>
	<!-- 服务热线 2 -->
	<tiles:insertAttribute name="servicebar"></tiles:insertAttribute>
	<!-- 主菜单 mainMenu 3 -->
	<tiles:insertAttribute name="mainmenu"></tiles:insertAttribute>
	<!-- 图片切换 banner 4 -->
	<tiles:insertAttribute name="banner"></tiles:insertAttribute>
	<!-- 投资统计 5 -->
	<tiles:insertAttribute name="statisticsdata"></tiles:insertAttribute>
	<!-- 安全提示 6 -->
	<tiles:insertAttribute name="safetip"></tiles:insertAttribute>
	<!--活动专区 7 -->
	<tiles:insertAttribute name="activitywrap"></tiles:insertAttribute>
	<!--投资专区8 -->
	<tiles:insertAttribute name="content"></tiles:insertAttribute>
	<!-- 新闻动态+媒体动态 9 -->
	<tiles:insertAttribute name="newsdynamic"></tiles:insertAttribute>
	<!-- 合作伙伴 10/页脚 导航 /页脚 备案号信息 -->
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	
</body>
</html>