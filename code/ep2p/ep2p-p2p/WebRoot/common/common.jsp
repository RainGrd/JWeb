<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<%@ include file="config.jsp"%>
<script type="text/javascript">
	var BASE_PATH = "${basePath}";	
	var basePath = "${basePath}";
	var randm = Math.random();
</script>
 <!-- 导入css -->
<!-- <link rel="shortcut icon" href="" type="image/x-icon">  -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/comcss/common.css"> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/yscf.css"> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/plugins/layer/skin/layer.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/plugins/layer/skin/layer.ext.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/plugins/Jcrop/css/jquery.Jcrop.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/plugins/jqpcss/jquery.pagination.css"> 

<!-- 个人中心页面 -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/usercenter.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/activity.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/borrow.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/drop_xyx.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/drop.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/reg.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/login.css">


<!-- 导入框架js -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jquery/jquery-1.9.1.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/common/common.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jqueryForm/jquery.form.js"></script>

<!-- 公共的常量和提示信息 -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/common/constant.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/common/common-msg-zh.js"></script>

<!-- 导入插件js -->
<!-- banner slide  js-->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jplugins/slider.js"></script> 
<!-- time  js-->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jplugins/timeJs.js"></script>
<!-- top Ad scroll  js-->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jplugins/scrollAd.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jplugins/countUp.min.js"></script>
<!-- 分页 -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jplugins/jquery.pagination-1.2.7.min.js"></script>
<!-- 异步上传文件 -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/ajaxfileupload/ajaxfileupload.js"></script>

<!-- 导入项目js -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/common/common.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/jsutil/jqueryUtil.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/jsutil/json2.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/jsutil/accounting.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/helpcenter/calculator.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/jsutil/all.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/jsutil/yscfUtil.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/homepage/home.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/bankcommuity/bankcommuity.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/common/validate.js"></script> 

<!--  导入layer 弹框插件 -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/layer/layer.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/layer/extend/layer.ext.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/jplugins/datepicker.js"></script> 

<!-- My97日历控件 --> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/plugins/My97DatePicker/skin/whyGreen/datepicker.css"> 
<script type="text/javascript" src="<%=basePath %>resources/plugins/My97DatePicker/WdatePicker.js"></script>

<!-- 导入其他 -->
<script type="text/javascript" src="<%=basePath %>theme/js/personcenter/common/customerVo.js"></script>
