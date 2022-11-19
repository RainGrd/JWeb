<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/model/recharge/offline_recharge_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/recharge/offlineClient/offline_recharge.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//判断是否从任务列表进入审核列表的
			var isTaskListOpen = $("#isTaskListOpen").val();
			if("true"==isTaskListOpen){
				//查询任务为  （审核中的任务）
				OfflineRecharge.selectReferendumTask();
				//设置下拉列表框 初始值为 （审核中）
				$('#recStatus').combobox('setValue', '1');
			}else{
				OfflineRecharge.initOfflineRechargeGrid();	
			}
		});
	</script>
	<div data-options="region:'center',border:false">
		<div id="offline_recharge_list_toolbar" class="easyui-panel">
			<div>
				<input type="hidden" id="isTaskListOpen" value="${isTaskListOpen}">
				<!-- 查询条件 -->
				<form action="" method="post" id="offline_recharge_list_form" name="offline_recharge_list_form">
					<div class="pt5">
						<table class="beforeloanTable percent90 formTable">
							<tr>
								<td class="label_right">客户名：</td>
								<td>
									<input class="easyui-textbox" name="customer.customerName" id="customerName" />
								</td>
								<td class="label_right">真实姓名：</td>
								<td>
									<input class="easyui-textbox" name="customer.sname" id="sname"/>
								</td>
								<td class="label_right">手机号码：</td>
								<td>
									<input class="easyui-textbox" name="customer.phoneNo" id="phoneNo" />
								</td>
							</tr>
							<tr>
								<td class="label_right">审核状态：</td>
								<td>
									<select id="recStatus" class="easyui-combobox" name="recStatus">   
									    <option value="">全部</option>   
									    <option value="1">审核中</option>   
									    <option value="2">成功</option>   
									    <option value="3">拒绝</option>   
									</select> 
								</td>
								<td class="label_right">充值时间：</td>
								<td  colspan="2">
									<div class="rangDate">
									<input class="easyui-datebox" id="recTimeBegin" name="recTimeBegin"     
        							data-options="showSeconds:true"> ~
									<input class="easyui-datebox" id="recTimeEnd" name="recTimeEnd"     
       								data-options="showSeconds:true">
       								</div>  
								</td>
								<td>
									<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'"
									onclick="OfflineRecharge.queryOfflineRechageButClick()">查询</a>
									
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'icon-clear'"
									onclick="OfflineRecharge.cleanData()">重置</a>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 操作按钮 -->
			<c:forEach items="${buttons}" var="li"> 
				<c:if test="${li.buttonCode == 10002}">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-export',plain:true"
						onclick="OfflineRecharge.toDownloadPage()">导出excel</a>
				</c:if>
			</c:forEach> 
		</div>
		<div class="showDataListWrap">
			<table id="offline_recharge_list_grid">
			</table>
		</div>
	</div>
</body>