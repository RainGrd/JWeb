<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/personcenter/mybenefits/my_point.js"></script>
<div class="sidebar_b borrow_div fl"> 
	<div class="fl title_div" id="borrow_area">
		<ul id="typeUl" class="type_title fl">
			<li class="change_li" onclick="myPoint.loadData('1')">
				<a href="javascript:void(0)">兑换积分</a>
			</li>
			<li onclick="myPoint.loadData('2')">
				<a href="javascript:void(0)">查看积分明细</a>
			</li>
		</ul>
	</div>
	<%--兑换积分--%>
	<div class="invest_table" id="div_01">
	</div>
	
	<%--查看积分明细--%>
	<div class="invest_table" id="div_02">

	</div>
	
	<div class="clearfix"></div>
	
</div>


