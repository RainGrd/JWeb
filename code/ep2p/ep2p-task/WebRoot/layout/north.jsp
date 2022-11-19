<%@page import="com.achievo.framework.security.domain.ContextUser"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
%>
<div id="navbar" class="navbar navbar-default">		
			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<!-- <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button> -->

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left navbar-brand-div">					
					<a href="#" class="navbar-brand">
						<img src="../resources/images/logo.png" />
					</a>	
					<a id="collspanBar">collspanNav</a>				
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-left" role="navigation">
				</div>
				<div id="bbsLogout">
				</div>
				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>