<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/report/golddeposit_preview.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- title -->
		<div class="amount_money_col pt10">
			当前备付金余额：<fmt:formatNumber value="${bem.balance}" pattern="#,#00.00#"/> 元             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备付金状态：  ${statusInfo}
		</div>
		
		<!-- search -->
		<div class="amount_money_col pt10 pb10">
			<div class="typeItem">
				<span class="left lineHeight22">时间：</span>
				<div class="rangDate left">
					<!-- 默认值 -->
					 <input id="year" type="text" name="year" runat="server"  onfocus="WdatePicker({skin:'default',dateFmt:'yyyy'})" class="Wdate" style=" width:80px;"/>
				</div>
				<div class="left ml10">
					<a href="javascript:void(0)" onclick="golddeposit.onclickReport();" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'"
							>统计</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		<!-- 报表信息 -->
		<div id="rep_golddeposit_preview"></div>
		
	</div>
</body>