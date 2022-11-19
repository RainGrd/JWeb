<%@page import="com.achievo.framework.security.domain.ContextUser"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://" + request.getServerName() + ":"+request.getServerPort() + path + "/";
	request.setAttribute("currUser", (ContextUser) SecurityUtils.getSubject().getSession().getAttribute("contextUser"));
%>
<script type="text/javascript" src="<%=basePath %>resources/js/system/menu/menu.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/js/layout/north.js"></script>

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
					<div class="scrollNavDiv">
					<ul class="nav ace-nav" id="north_div">
						<!-- /section:basics/navbar.user_menu -->
					</ul>
					</div>
					<div class="clear"></div>
					<div id="slideMenuLeft" class="grey none" c="0">
							<a class="slideMenu">&lt; </a>
					</div>
					<div id="slideMenuLi_" class="grey none" c="0">
							<a class="slideMenu" style="display:none;">></a>
					</div>
					<a class="userinfoItem easyui-menubutton"  href="#" data-options="menu:'#mm'">						
							<div title="${currUser.userName}" class="uNameTitle">${currUser.userName}</div>
							<i class="ace-icon fa fa-caret-down"></i>
						
					</a>

			 		<ul id="mm" class="none" style="width:140px;">
						<li>
							<a href="javascript:openChangePassword()">
								 账户设置
							</a>
						</li>
						
						<li>
							<a href="javascript:logoutFun()">
								 退出
							</a>
						</li>
					</ul> 
					<a class="editNum" href="javascript:void(0)">
						<i id="taskTotalCountI"></i>
					</a>
				</div>
				<div id="bbsLogout">
				</div>
				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>