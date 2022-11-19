<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript"src="${basePath}resources/js/statistics/investment_statistics_terminal.js"charset="utf-8"></script>
	<div data-options="border:false">
		<table class="beforeloanTable formTable">
			<tr>
				<td class="label_right">时间类型:</td>
				<td>
					<div class="rangDate">
					<select class="easyui-combobox" id="statisticType" style="width: 100px;" data-options="onSelect:investment_statistics_terminal.selectType" panelHeight="auto" editable="false" >
						<option value=-1  selected="selected" >全部</option>
						<option value=1 >年</option>
						<option value=2 >月</option>
					</select>
					</div>
				</td>
				<td width="10"></td>
				<td>
					<div class="none typeItem_info" id="yearDiv">
						<span class="left lineHeight22">时间：</span>
						<div class="rangDate left">
							<input id="year" type="text" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy'})" style="width: 100px;" /> 
						</div>
					</div>	
				</td>
				<td>
					<div class="none typeItem_info"none"" id="monthDiv">
						<select class="easyui-combobox" id="month" style="width: 100px;" panelHeight="auto" editable="false" >
							<option value=1 >1月</option>
							<option value=2 >2月</option>
							<option value=3 >3月</option>
							<option value=4 >4月</option>
							<option value=5 >5月</option>
							<option value=6 >6月</option>
							<option value=7 >7月</option>
							<option value=8 >8月</option>
							<option value=9 >9月</option>
							<option value=10 >10月</option>
							<option value=11 >11月</option>
							<option value=12 >12月</option>
						</select>
					</div>
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="investment_statistics_terminal.singleClickStatistics()" >统计</a>
				</td>
			</tr>
		</table>
		<!-- 统计结果 -->
		<div id="investment_statistic_terminalResult">
		</div>
	</div>
</body>