<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/report/platform_data.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div class="amount_money_date">
			<p>数据截止至： <fmt:formatDate pattern="yyyy-MM-dd " value="${yesterday}" /> 00:00:00</p>
		</div>
		<!-- 顶部菜单栏 -->
		<div class="amount_money_titles">
			<div class="platform_panle platform_panle_hov" onclick="platformReport.bindOnclick(this,'1');">
				<p><fmt:formatNumber value="${reslut.cumulativei}" pattern="#,#00.00#"/> 元</p>
				<p>累计投资</p>
			</div>
			<div class="platform_panle" onclick="platformReport.bindOnclick(this,'2');">
				<p><fmt:formatNumber value="${reslut.alsoprincipal}" pattern="#,#00.00#"/> 元</p>
				<p>已还本金</p>
			</div>
			<div class="platform_panle" onclick="platformReport.bindOnclick(this,'3');">
				<p><fmt:formatNumber value="${reslut.userbenefit}" pattern="#,#00.00#"/> 元</p>
				<p>累计用户收益</p>
			</div>
			<div class="platform_panle" onclick="platformReport.bindOnclick(this,'4');">
				<p><fmt:formatNumber value="${reslut.grantprofit}" pattern="#,#00.00#"/> 元</p>
				<p>已发放收益</p>
			</div>
		</div>
		<!-- 搜索框 -->		
		<div class="amount_money_from">
			<div class="amount_money_col">
				<div class="typeItem">
					<span class="left lineHeight22">时间：</span>
					<div class="rangDate left">
						<!-- 默认值 -->
						<input type="hidden" name="def_check_val" id="def_check_val" value="1">
						<input id="year" name="year" class="easyui-combobox" panelHeight="auto"
	           				 data-options="loadFilter:common.dictionaryFilter,
		           		 valueField:'dictContCode',
		           		 textField:'dictContName',
		           		 loadFilter:removeloadFilter,
		           		 multiple:false,
		           		 url:'<%=SREACH_YEARS%>'" /> 
					</div>
					<div class="left ml10">
						<a href="javascript:void(0)" onclick="platformReport.searchPlant();" class="easyui-linkbutton"
								data-options="iconCls:'icon-search'"
								>统计</a>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div id="" class="amount_money_col">
				<span id="amc_show_title">年度累计投资: <fmt:formatNumber value="${reslut.cumulativei}" pattern="#,#00.00#"/></span><span> 元</span>
			</div>
		</div>
		<!-- 图标框 -->
		<div id="amount_money_view" class="amount_money_view">
		
		</div>
	</div>
</body>