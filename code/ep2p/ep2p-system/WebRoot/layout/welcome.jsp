<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath %>resources/js/welcome.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/js/layout/task/task.js"></script>
<!-- 近7日新增客户 ip pv访问量 -->
<%@ include file="deals/deals.jsp"%>

<!-- 任务管理 -->
<%@ include file="task/task.jsp"%>

<!-- 资金交易流水表 -->
<%@ include file="state/state.jsp"%>