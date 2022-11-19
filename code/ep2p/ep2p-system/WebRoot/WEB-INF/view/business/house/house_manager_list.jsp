<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/houseManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/houseManager/houseManager.js" charset="utf-8"></script>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="beforeloanTable formTable" width="90%">
							<tr>
								<td class="label_right">
									楼盘地址：
								</td>
								<td >
									省 <input class="easyui-validatebox" name="homesProvince" id="homesProvince" />
									&nbsp;&nbsp;&nbsp;市 <input class="easyui-validatebox" name="homesCity" id="homesCity" />
									&nbsp;&nbsp;&nbsp;区 <input class="easyui-validatebox" name="homesArea" id="homesArea" />
								</td>
							</tr>
							<tr>
								<td class="label_right">
									楼盘：
								</td>
								<td >
									<input class="easyui-validatebox" name="homesName" id="homesName" />
								</td>
							</tr>
							<tr>
								<td class="label_right">
									户型：
								</td>
								<td >
									<input class="easyui-validatebox" name="homesType" id="homesType" /> &nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="houseManager.searchList()">查询</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="javascript:$('#searcm').form('reset');">重置</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
				
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="houseManager.toAdd()">新增</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="houseManagerGrid"></table>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			houseManager.initHouseManagerDataGrid();	
		});
	</script>
</body>