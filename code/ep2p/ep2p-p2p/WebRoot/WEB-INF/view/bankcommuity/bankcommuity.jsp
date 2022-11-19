<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body>
	<code>
		<%
			out.println(request.getAttribute("forumLoginResult"));
		%>
	</code>
	
	<iframe src="${forumAddress}" width="100%" height="98%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes"> 
</body>