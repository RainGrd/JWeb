<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/model/recharge/offline_recharge_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/recharge/offlineClient/offline_recharge.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			OfflineRecharge.initIndexGrid();	
		});
	</script>
	<div data-options="region:'center',border:false">
		<div id="offline_index_toolbar" class="easyui-panel">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="offline_index_form" name="offline_index_form">
					<div class="p5">
						<table class="beforeloanTable percent90">
							<tr>
								<td class="label_right">客户名：</td>
								<td><input class="easyui-textbox" name="customerName"
									id="customerName" /></td>
								<td class="label_right">姓名：</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
								<td class="label_right">手机号码：</td>
								<td><input class="easyui-textbox" name="phoneNo"
									id="phoneNo" /></td>
								<td>
									<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'"
									onclick="OfflineRecharge.queryIndexGridButClick()">查询</a>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 操作按钮 -->
			
		</div>
		<div class="showDataListWrap">
			<table id="offline_index_grid">
			</table>
		</div>
	</div>
</body>