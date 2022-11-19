<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.*"%>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<jsp:include page="../common/common.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>错误异常</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link rel="stylesheet" type="text/css" href="../common/common.css">
<link rel="stylesheet" type="text/css" href="../common/error.css">
<script src="${basePath}resources/plugins/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
<script>
if (top != self) {
		if (top.location != self.location)
			top.location = self.location;
	}
	$(document).ready(function(){
		$('#errorMessageDiv').css({'overflow':'auto','height':(document.documentElement.clientHeight-80-200)+'px'});
		$('body').css('height',(document.documentElement.clientHeight-80)+'px');
	});
function showErrorMessage(){
	if($('#errorMessageDiv').hasClass('none')){
		$('#errorMessageDiv').removeClass('none');
	}else{
		$('#errorMessageDiv').addClass('none');
	}
}
$(window).resize(function(){
  resizeWindow();
 });
 
function resizeWindow(){
	$('#errorMessageDiv').css({'overflow':'auto','height':(document.documentElement.clientHeight-80-200)+'px'});
	$('body').css('height',(document.documentElement.clientHeight-80)+'px');
}
</script>
</head>
<body class="errorbody">
	<div class="admin-content">
    <table width="100%" class="errorTable">
		<tr>
			<td>
			<div class="errorTitle">错误提示 </div>
			</td>
		</tr>
		<tr>
			<td>错误状态：<b>500</b></td>
		</tr>
		<tr>
			<td>
				尊敬的用户：<br />系统出现了异常，请重试。
                <br />如果问题重复出现，请向系统管理员反馈。<br /><br />
                <a id="showErrorMessageButton" href="javascript:showErrorMessage();">详细错误信息</a>
			</td>
		</tr>
		<tr>
			<td>
				<div id="errorMessageDiv" class="none">
            <pre>
                <%
                    try {
                        //全部内容先写到内存，然后分别从两个输出流再输出到页面和文件
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        PrintStream printStream = new PrintStream(byteArrayOutputStream);

                        printStream.println();
                        //printStream.println("用户信息");
                        //printStream.println("账号：" + request.getSession().getAttribute("userName"));
                        printStream.println("访问的路径: " + request.getAttribute("javax.servlet.forward.request_uri"));
                        printStream.println();

                        printStream.println("异常信息");
                        printStream.println(exception.getClass() + " : " + exception.getMessage());
                        printStream.println();

                        Enumeration<String> e = request.getParameterNames();
                        if (e.hasMoreElements()) {
                            printStream.println("请求中的Parameter包括：");
                            while (e.hasMoreElements()) {
                                String key = e.nextElement();
                                printStream.println(key + "=" + request.getParameter(key));
                            }
                            printStream.println();
                        }

                        printStream.println("堆栈信息");
                        exception.printStackTrace(printStream);
                        printStream.println();

                        out.print(byteArrayOutputStream);    //输出到网页
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                %>
            </pre>
        </div>
			</td>
		</tr>
	</table>
  </div>
</body>
</html>