	<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/custom/toFundWaterList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/toFundWaterListModel.js"></script>
<script type="text/javascript">
waters.initDataGrid();
$(document).ready(function(){
	
});
</script>
	<div data-options="region:'center',border:false">
		<div id="toolbar">
			<!-- 查询条件 -->
				<form action="getCustomerSearchList.shtml" method="post" id="searcher1"
					name="searcher">
					<div style="padding: 5px">
						<table class="userTable percent100 formTable">
						  <tr>
							<td class="align_right">查询条件：</td>
							<td><input class="easyui-textbox"  id="searchCondition" name="searchCondition"/> 
							<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="waters.searchData()">查询</a></td>
					 </div>
					  </tr>
					</table>
				</div>
				</form>
		</div>
		<!--  数据列表 -->		
		<div class="showDataListWrap" style="height:100%">
			<table id="fundWaterGrid" >
			</table>
		</div>
		
	</div>
</body>