<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		
		<div data-options="region:'center',border:false">
								<div class="easyui-tabs" id="toGatherDetailsTabs1" fit="true" border="false" 
									style="overflow-x:hidden;" data-options="width:'100%',onSelect:fundwater.waterType">
							    	<div title="交易记录" id="tab1" class="p10">
									 </div>
									 <div title="投资记录" id="tab2" class="pt10" >
									 </div>
									 <div title="收益记录" id="tab3" class="pt10">
									 </div>
									  <div title="充值流水" id="tab4" class="pt10">
									 </div>
									 <div title="提现流水" id="tab5" class="pt10">
									 </div>
									 <div title="其它" id="tab6" class="pt10">
									 </div>
								</div>  
							</div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcher1"
					name="searcher1">
					 <input type="hidden" id="pid" name="pid" value="${pid}">
						<table class="userTable percent90">
						  <tr>
							<td class="align_right">时间段：</td>
							<td>
								<div class="rangDate">
								    <input type="hidden"  id="waterType" name="waterType"/>
									<input class="easyui-datebox" id="happenBeginTime" name="happenBeginTime" data-options="editable:false" />~
									<input class="easyui-datebox" id="happenEndTime" name="happenEndTime" data-options="editable:false" />
									<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="fundwater.searchData()">查询</a>
								</div>
							</td>
						  </tr>
						</table>
				</form>
		</div>
					
		<!-- 数据列表 -->
		<div class="showDataListWrap">
			<table id="showFundWaterGrids"></table>
		</div>
		
<script type="text/javascript" src="${basePath}resources/js/custom/selectFundWaterDetails.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/selectFundWaterDetailsModel.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			fundwater.initDataGrid();	
		});
		</script>
	</div>
</body>