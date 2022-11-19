<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<div class="iframeBox">

	
<iframe id="iframer" name="left" frameborder="0" scrolling=no src="<%=basePath %>${fileName }" width="100%" height="100%"></iframe>

<a class="protocolDownload btn" href="<%=basePath %>${fileName }" download="<%=basePath %>${fileName }">点击下载</a>
</div>

<script>
$("#iframer").load(function(){
	var mainheight = $(this).contents().find("body").height()+30;
	$(this).height(mainheight);
	//alert(mainheight);
}); 
</script>
