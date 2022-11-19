<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<code>
		<%
			out.println(request.getAttribute("discuzLogin"));
		%>
	</code>
	
	<iframe src="${forumAddress}" width="100%" height="98%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes"> 
</body>