<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
%>

<link rel="shortcut icon" type="image/x-icon" href="${basePath}resources/images/favicon.ico" media="screen" />
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/jquery-easyui-1.4.3/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/jquery-easyui-1.4.3/themes/icon.css">

<link rel="stylesheet" type="text/css" href="${basePath}resources/css/zice.style.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/tipsy.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/buttons.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/rewrite.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/extend/style.css">
<!-- ace util-->
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/ace/css/ace.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/ace/css/ace-fonts.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/ace/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/ace/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/css/expand.css">
<link rel="stylesheet" type="text/css" href="${basePath}resources/plugins/My97DatePicker/skin/whyGreen/datepicker.css"> 


<script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.3/jquery.min.js"></script>

<%-- <script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.3/easyloader.js"></script> --%>
<script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.3/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${basePath}resources/plugins/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="${basePath}resources/js/extend/datagrid-detailview.js"></script>
<script type="text/javascript" src="${basePath}resources/js/extend/mootools-1.4.5.js"></script>


<script type="text/javascript" src="${basePath}resources/plugins/base64.js"></script>

<!-- 一些弹出页面的js add yql -->
<script type="text/javascript" src="${basePath}resources/js/extend/financeCommon.js" charset="utf-8"></script>


<!-- easyui portal插件 -->
<link rel="stylesheet" href="${basePath}resources/css/extend/portal.css" type="text/css"></link>
<script type="text/javascript" src="${basePath}resources/js/extend/jquery.portal.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/plugins/My97DatePicker/WdatePicker.js"></script>

<!-- 表单验证   jquery.validate-->
<script type="text/javascript" src="${basePath}resources/js/extend/jquery.validate.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/extend/messages_zh.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/extend/validater.js" charset="utf-8"></script>

<!--  json  -->
<script type="text/javascript" src="${basePath}resources/js/extend/json2.js" charset="utf-8"></script>

<!-- jquery util -->
<script type="text/javascript" src="${basePath}resources/js/extend/jqueryUtil.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/extend/common_dowload.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/extend/common_constant.js" charset="utf-8"></script>

<script type="text/javascript" src="${basePath}resources/js/extend/mainFrame.js" charset="utf-8"></script>
<script src="${basePath}resources/js/extend/yscfutil.js" type="text/javascript"></script>
<script src="${basePath}resources/js/common.js" type="text/javascript"></script>

<!-- ace util-->

<!-- zTree树形菜单 -->
<%-- <link rel="stylesheet" href="${ctx}/p/xlkfinance/plug-in/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/p/xlkfinance/plug-in/zTree/js/jquery.ztree.core-3.5.js"></script>
 --%>
<!-- My97日历控件 --> 
<%-- <link rel="stylesheet" href="${ctx}/p/xlkfinance/plug-in/My97DatePicker/skin/whyGreen/datepicker.css" type="text/css"> 
<script type="text/javascript" src="${ctx}/p/xlkfinance/plug-in/My97DatePicker/WdatePicker.js"></script>
 --%>
<!-- 省市区级联 -->
<%-- <script type="text/javascript" src="${ctx}/p/xlkfinance/js/sitedata.bas.js" charset="utf-8"></script>
 --%>
<!-- zhengxiu 重写样式 -->
<%-- <link rel="stylesheet" href="${ctx}/p/xlkfinance/css/rewritecss.css" type="text/css"></link>
 --%>

<script type="text/javascript">
	var basePath = "${basePath}";	
	var BASE_PATH = "${basePath}";
</script>

 
