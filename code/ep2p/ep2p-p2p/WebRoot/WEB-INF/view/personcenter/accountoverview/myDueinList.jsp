<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<c:forEach items="${brpmlisto}" var="items">
	<ul class="record_ul">
		<li class="name"><a href="javascript:void(0);" onclick="personcAccview.pronameClick('${items.borrowId}','${items.borrowType}');"><c:out	value="${items.borrowName}"></c:out></a></li>
		<li class="type">本息</li>
		<li class="price">￥<fmt:formatNumber value="${items.capital + items.interest}" pattern="#,##0.00#"></fmt:formatNumber></li>
	</ul>
</c:forEach>