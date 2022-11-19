<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<body class="easyui-layout">
	
	<%-- 左边可选条件列表 --%>
	<div id="zuo" style="width: 200px;float: left;margin-left: 5px;">
		<span style="padding-left: 20px;color: red;">可选条件:</span>
		<select id="zuo_condition" style="width: 200px;margin-top: 10px;height:250px; " size="14" ondblclick="commonActivity.leftDoubleClick()" >
		</select>
	</div>
	
	<div class="activity_condition">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.leftToRight()" >></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.rightToLeft()" ><</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.allLeftToRight()" >>></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="commonActivity.allRightToLeft()" ><<</a>
	</div>
	
	<%-- 右边已选条件列表 --%>
	<div id="you" style="width: 200px;float: left">
		<span style="padding-left: 20px;color: red;">已选条件:</span>
		<select id="you_condition" style="width: 200px;margin-top: 10px;height:250px; " size="14" ondblclick="commonActivity.rightDoubleClick()" >
		</select>
	</div>
</body>