<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="pointToolbar">			
				<input type="hidden" id="pid" name="pid" value="${pid}">
				<!-- 查询条件 -->
				<form action="" method="post" id="searcher"
					name="searcher">
					<div style="padding: 5px">
						<table class="userTable percent90">
						  <tr>
							<td class="align_right">时间段：</td>
							<td>
								<div class="rangDate">
									<input class="easyui-datebox" id="happenTime" name="happenBeginTime" data-options="required:true,editable:false" />~
									<input class="easyui-datebox" id="happenTime" name="happenEndTime" data-options="required:true,editable:false" />
								</div>
							</td>
							<td class="align_right">类型：</td>
							<td>
						        <select name="expGetType" editable="false" id="status"
									  class="easyui-combobox" style="width: 150px;">
								<option value="0">--全部--</option>
								<option value="1">投资活动奖励</option>
								<option value="2">投资奖励</option>
								</select> 
								<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="experience.searchData()">查询</a>
							</td>
						  </tr>
						</table>
					</div>
				</form>
		</div>
					
		<!-- 数据列表 -->
		<div class="showDataListWrap">
			<table id="showCusGrids"></table>
		</div>
		
		<script type="text/javascript" src="${basePath}resources/js/custom/toExperienceDetailsList.js"></script>
		<script type="text/javascript" src="${basePath}resources/js/custom/model/toExperienceDetailsListModel.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			experience.initDataGrid();	
		});
		</script>
	</div>
</body>