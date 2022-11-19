<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<!-- 图片切换 banner 4 -->
<c:if test="${!empty advContent}">
	<div id="banner_tabs" class="flexslider">
	  <ul class="slides">
	  	<c:forEach items="${advContent}" var="item">
	    	<li><a title="<c:out value="${item.titleName}"></c:out>" target="_blank" href="<c:out value="${item.url}"></c:out>"> 
	    		<img height="310" alt="<c:out value="${item.titleName}"></c:out>" style="width:100%;background: url(<%=basePath%><c:out value="${item.fileUrl}"></c:out>) no-repeat center;" 
	    		src="<%=basePath%><c:out value="${item.fileUrl}"></c:out>"> 
	    	</a> </li>
	    </c:forEach>
	  </ul>
	  <ul class="flex-direction-nav">
	    <li><a class="flex-prev" href="javascript:void(0);">Previous</a></li>
	    <li><a class="flex-next" href="javascript:void(0);">Next</a></li>
	  </ul>
	  <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
	  	<c:forEach items="${advContent}" var="piontitems">
	    	<li><a><c:out value="${index}"></c:out></a></li>
	    </c:forEach>
	  </ol>
	</div>
</c:if>
<c:if test="${empty advContent}">
	<div id="banner_tabs" class="flexslider">
	  <ul class="slides">
	    <li> <a title="" target="_blank" href="#"> <img height="310" alt="" style="width:100%;background: url(../../theme/default/images/banner1.png) no-repeat center;" src="<%=basePath %>theme/default/images/alpha.png"> </a> </li>
	    <li> <a title="" href="#"> <img height="310" alt="" style="width:100%;background: url(../../theme/default/images/banner1.jpg) no-repeat center;" src="<%=basePath %>theme/default/images/alpha.png"> </a> </li>
	    <li> <a title="" href="#"> <img height="310" alt="" style="width:100%;background: url(../../theme/default/images/banner3.jpg) no-repeat center;" src="<%=basePath %>theme/default/images/alpha.png"> </a> </li>
	  </ul>
	  <ul class="flex-direction-nav">
	    <li><a class="flex-prev" href="javascript:void(0);">Previous</a></li>
	    <li><a class="flex-next" href="javascript:void(0);">Next</a></li>
	  </ul>
	  <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
	    <li><a>1</a></li>
	    <li><a>2</a></li>
	    <li><a>2</a></li>
	  </ol>
	</div>
</c:if>