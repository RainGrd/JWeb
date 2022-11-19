<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/radio/model/displayList_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/radio/statistics/display_lists.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div class="queryTerms" id="queryTermsToolbar">
			<!-- 查询条件 -->
			<form action="" method="post" id="queryTerms_form">
				<div class="p5">
					<table class="beforeloanTable formTable">
						<tr>
							<td class="label_right">时间类型：</td>
							<td>
								<div class="rangDate">
								<select id="timeType" class="easyui-combobox" data-options="onSelect:mystatistics.selectType" name="timeType">   
								    <option value="1">年</option>   
								    <option value="2">月</option>   
								    <option value="3">时间段</option>   
								</select> 
								</div>
							</td>	
							<td class="align_right" width="40"></td>
							<td>
								<div class="typeItem timeType1">
									<span class="left lineHeight22">时间：</span>
									<div class="rangDate left">
										<input id="year" name="year" class="easyui-combobox" panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
		           							 valueField:'dictContCode',
		           							 textField:'dictContName',
		           							 loadFilter:removeloadFilter,
		           							 multiple:false,
		           							 url:'<%=SREACH_YEARS%>'" /> 
									</div>
									<div class="clear"></div>
								</div>	
								
								<div class="typeItem timeType2 none">
									<span class="left lineHeight22">时间：</span>
									<div class="rangDate left">
										<input id="year1" name="year1" class="easyui-combobox" panelHeight="auto"
	           							 data-options="loadFilter:common.dictionaryFilter,
		           							 valueField:'dictContCode',
		           							 textField:'dictContName',
		           							 loadFilter:removeloadFilter,
		           							 multiple:false,
		           							 url:'<%=SREACH_YEARS%>'" />
	           							<input id="month" name="month" class="easyui-combobox" panelHeight="auto"
	           							data-options="
	           							 valueField:'dictContCode',
	           							 textField:'dictContName',
	           							 value:'01',
	           							 data:[
	           							 	{dictContCode:'01',dictContName:'1月'},
	           							 	{dictContCode:'02',dictContName:'2月'},
	           							 	{dictContCode:'03',dictContName:'3月'},
	           							 	{dictContCode:'04',dictContName:'4月'},
	           							 	{dictContCode:'05',dictContName:'5月'},
	           							 	{dictContCode:'06',dictContName:'6月'},
	           							 	{dictContCode:'07',dictContName:'7月'},
	           							 	{dictContCode:'08',dictContName:'8月'},
	           							 	{dictContCode:'09',dictContName:'9月'},
	           							 	{dictContCode:'10',dictContName:'10月'},
	           							 	{dictContCode:'11',dictContName:'11月'},
	           							 	{dictContCode:'12',dictContName:'12月'}
	           							 ],
	           							 multiple:false"/> 
									</div>
									<div class="clear"></div>
								</div>	
								
								<div class="typeItem timeType3 none">
									<span class="left lineHeight22">  从：</span>
									<div class="rangDate left">
									<input class="easyui-datebox" id="startDate" name="startDate" editable="false" />~
									<input class="easyui-datebox" id="endDate" name="endDate" editable="false" /> 
									</div>
									<div class="clear"></div>
								</div>	
							</td>										
							<td>
								<a href="javascript:void(0)" onclick="mystatistics.searchData();" class="easyui-linkbutton"
								data-options="iconCls:'icon-search'"
								>统计</a>
							</td>
						</tr>						
					</table>
				</div>
			</form>
		</div>
		
		<!-- 统计结果 -->
		<div class="programListDiv" style="height:100%">
			<table id="queryTerms_grid"></table>
		</div>
	</div>
</body>