<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>500</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>common/common.css"></link>
<link rel="stylesheet" type="text/css" href="<%=basePath %>common/error.css"></link>
</head>
<body>
	<div class="admin-content">
		<div class="am-g">
			<div class="am-u-sm-12 am-text-center ">
				<h2 class="errorTip">500. Server Error</h2>
				<p class="am-text-center">服务器端的网页程序设计有误</p>
				<pre class="page-404">
          .----.
       _.'__    `.
   .--($)($$)---/#\
 .' @          /###\
 :         ,   #####
  `-..__.-' _.-\###/
        `;_:    `"'
      .'"""""`.
     /,  ya ,\\
    //  500!  \\
    `-._______.-'
    ___`. | .'___
   (______|______)
        </pre>
			</div>
		</div>
	</div>
</body>
</html>