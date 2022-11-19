<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/radio/statistics/system_amount.js" charset="utf-8"></script>
	<div data-options="region:'center',border:false">
		<div class="queryTerms">
			<!-- 查询条件 -->
			<form action="" method="post" id="system_search_from">
				<div class="p5">
					<table class="beforeloanTable formTable">
						<tr>
							<td class="label_right">时间类型：</td>
							<td>
								<div class="rangDate">
								<select id="timeType_info" class="easyui-combobox" data-options="onSelect:reportSysObject.selectTypeInfo" name="timeType">   
								    <option value="1">年</option>   
								    <option value="2">月</option>   
								</select> 
								</div>
							</td>	
							<td width="10"></td>
							<td>
								<div class="typeItem_info timeType_info1">
									<span class="left lineHeight22">时间：</span>
									<div class="rangDate left">
										<input id="year2" name="year2" class="easyui-combobox" panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
		           							 valueField:'dictContCode',
		           							 textField:'dictContName',
		           							 value:'2015',
		           							 multiple:false,
		           							 url:'<%=SREACH_YEARS%>'" /> 
									</div>
									<div class="clear"></div>
								</div>	
								
								<div class="typeItem_info timeType_info2 none">
									<span class="left lineHeight22">时间：</span>
									<div class="rangDate left">
										<input id="year3" name="year3" class="easyui-validatebox  easyui-combobox" panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
		           							 valueField:'dictContCode',
		           							 textField:'dictContName',
		           							 value:'2015',
		           							 multiple:false,
		           							 url:'<%=SREACH_YEARS%>'" />
	           							<input id="month1" name="month1" class="easyui-combobox" panelHeight="auto"
	           							data-options="
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 value:'1',
	           							 data:[
	           							 	{dictContCode:'1',dictContName:'1月'},
	           							 	{dictContCode:'2',dictContName:'2月'},
	           							 	{dictContCode:'3',dictContName:'3月'},
	           							 	{dictContCode:'4',dictContName:'4月'},
	           							 	{dictContCode:'5',dictContName:'5月'},
	           							 	{dictContCode:'6',dictContName:'6月'},
	           							 	{dictContCode:'7',dictContName:'7月'},
	           							 	{dictContCode:'8',dictContName:'8月'},
	           							 	{dictContCode:'9',dictContName:'9月'},
	           							 	{dictContCode:'10',dictContName:'10月'},
	           							 	{dictContCode:'11',dictContName:'11月'},
	           							 	{dictContCode:'12',dictContName:'12月'}
	           							 ],
	           							 multiple:false"/> 
									</div>
									<div class="clear"></div>
								</div>	
							</td>										
							<td>
								<a href="javascript:void(0)" onclick="reportSysObject.serarLineReport();" class="easyui-linkbutton"
								data-options="iconCls:'icon-search'"
								>统计</a>
							</td>
						</tr>	
											
					</table>
				</div>
			</form>
		</div>
		
		<!-- 统计结果 -->
		<div id="system_detail_statisticResult" class="showDataListWrap">
		</div>
	</div>
</body>