<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript"src="${basePath}resources/js/statistics/investment_statistics.js"charset="utf-8"></script>
	<div data-options="region:'center',border:false">
		<div style="text-align: center;">
			<select class="easyui-combobox" id="statisticType" style="width: 140px;" panelHeight="auto" editable="false" >
				<option value=1  selected="selected" >年龄</option>
				<option value=2 >性别</option>
			</select>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="investment_statistics.singleClickStatistics()" style="width: 100px;">统计</a>
		</div>
		<!-- 统计结果 -->
		<div id="investment_statisticResult">
		</div>
	</div>
</body>