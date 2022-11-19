<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<tiles:insertAttribute name="headmeta"></tiles:insertAttribute>
	<title><tiles:getAsString name="title"></tiles:getAsString></title>
	
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
	
	<!-- 中心内容 -->
	<div class="content_ac mt40">
		<div class="w1140 m_auto">
			<tiles:insertAttribute name="helpcenterleft"></tiles:insertAttribute>
			<tiles:insertAttribute name="helpcenterright"></tiles:insertAttribute>
		</div>
	</div>

	<!-- 合作伙伴 10/页脚 导航 /页脚 备案号信息 -->
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>