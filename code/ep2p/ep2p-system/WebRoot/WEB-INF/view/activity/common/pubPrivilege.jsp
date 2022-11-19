<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<body class="easyui-layout">
	<!-- 共有的特权页面  -->
	<%-- 左边可选条件列表 --%>
	<div id="priRight" style="width: 200px;float: left;margin-left: 5px;">
		<span style="padding-left: 20px;color: red;">可选特权:</span>
		<select id="priRightSelect" style="width: 200px;margin-top: 10px; " size="14" ondblclick="commonActivity.leftDoubleClickByPri()">
		</select>
	</div>
	
	<div class="activity_condition">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.leftToRightByPri()" >></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.rightToLeftByPri()" ><</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.allLeftToRightByPri()" >>></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.allRightToLeftByPri()" ><<</a>
	</div>
	
	<%-- 右边已选条件列表 --%>
	<div id="priLeft" style="width: 200px;float: left">
		<span style="padding-left: 20px;color: red;">*已选特权:</span>
		<select id="priLeftSelect" style="width: 200px;margin-top: 10px;" size="14" ondblclick="commonActivity.rightDoubleClickByPri()">
		</select>
	</div>
</body>