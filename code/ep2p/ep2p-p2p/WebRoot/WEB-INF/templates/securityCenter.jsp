<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<tiles:insertAttribute name="headmeta"></tiles:insertAttribute>
	<title>E生财富</title>
</head>
<body>
	<!--添加银行卡信息页面 -->
	<tiles:insertAttribute name="addBankCard"></tiles:insertAttribute>
	<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/addBankCard.js"></script>
</body>
</html>