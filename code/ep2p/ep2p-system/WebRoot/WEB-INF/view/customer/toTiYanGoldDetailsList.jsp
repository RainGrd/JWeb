<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="pointToolbar1">			
				
				<!-- 查询条件 -->
				<form action="" method="post" id="searcher"
					name="searcher">
					<input type="hidden" id="pid" name="pid" value="${pid}">
					<div style="padding: 5px">
						<table class="userTable percent90">
						  <tr>
							<td class="align_right">时间段：</td>
							<td>
								<div class="rangDate">
									<input class="easyui-datebox" id="happenTime" name="happenBeginTime" data-options="editable:false" />~
									<input class="easyui-datebox" id="happenTime" name="happenEndTime" data-options="editable:false" />
								<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="tiyanjinGold.searchData()">查询</a>
								</div>
							</td>
						  </tr>
						</table>
					</div>
				</form>
		</div>
					
		<!-- 数据列表 -->
		<div class="showDataListWrap">
			<table id="showCusTiYanJinGrids"></table>
		</div>
		
		<script type="text/javascript" src="${basePath}resources/js/custom/toTiYanGoldDetailsList.js"></script>
		<script type="text/javascript" src="${basePath}resources/js/custom/model/toTiYanGoldDetailsListModel.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			tiyanjinGold.initDataGrid();	
		});
		</script>
	</div>
</body>