<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/common/usercenter.js"></script> 
	<tiles:insertAttribute name="headmeta"></tiles:insertAttribute>
	<title>我要借款</title>
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
	<div class="content">
		<div class="w1200 bgc oh m_auto">
			<div id="borrowContent">
				<tiles:insertAttribute name="borrowContent"></tiles:insertAttribute>
			</div>
		</div>
	</div>

	<!-- 合作伙伴 10/页脚 导航 /页脚 备案号信息 -->
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>