<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	<!-- 表单信息 -->
	<div class="p10" id="preview">
	<c:if test="${type eq  'jpg'}" >
		<a href="${url}" id="${filePid }">
			<input type="hidden" name="fileUrl" id="fileUrl" value="${fileUrl }" />
			<img src=" "></img>
		</a>
	</c:if>	
	<c:if test="${type eq  'html'}" >
		<input type="hidden" name="fileUrl" id="fileUrl" value="${fileUrl }" />
		 <script type="text/javascript">
		 	childLayout_addTab(BASE_PATH+$("#fileUrl").val(),"预览html"); 
		 	parent.$('#centerFrame').tabs('close',"预览");
		 </script>
	</c:if>	
	</div>
</div>
<script type="text/javascript">
	$(function(){
		var fileUrl = $("#fileUrl").val(); 
		$("img").attr("src",BASE_PATH+fileUrl); 
	})
</script>
</body>