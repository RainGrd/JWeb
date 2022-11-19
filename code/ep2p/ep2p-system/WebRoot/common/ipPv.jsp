<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="ipPv">
	<input type="hidden" name="path" id="path" value="<%=request.getParameter("path") %>">
</div>

<script>
$(function(){
	var path =  $("#ipPv #path").val();
	$.ajax({
		type: "POST",
    	url : BASE_PATH+"sysIpPvCountController/executeIpPvCount.shtml",
    	data:{path:path},
		dataType: "json",
	    success: function(data){
		}
	});
})
</script>