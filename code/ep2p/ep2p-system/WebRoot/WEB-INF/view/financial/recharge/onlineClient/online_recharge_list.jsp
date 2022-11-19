<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/finance/model/recharge/online_recharge_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/recharge/onlineClient/online_recharge.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			OnlineRecharge.initOnlineRechargeGrid();
			OnlineRecharge.initRechargeMode();
		});
	</script>
	<div data-options="region:'center',border:false">
		<div id="online_recharge_list_toolbar" class="easyui-panel">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="online_recharge_list_form" name="online_recharge_list_form">
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
								<td class="label_right">订单号：</td>
								<td>
									<input class="easyui-textbox" name="recOrderNo" id="recOrderNo" />
								</td>
								<td class="label_right">状态：</td>
								<td>
									<select id="recStatus" class="easyui-combobox" name="recStatus">   
									    <option value="">全部</option>
									    <option value="1">待付款</option>
									    <option value="2">付款失败</option>
									    <option value="3">充值成功</option> 
									    <option value="4">待充值</option> 
									    <option value="5">系统充值</option>
									    <option value="6">手动补单</option>
									    <option value="7">错误修正</option>
									</select> 
								</td>
								<td class="label_right">充值渠道：</td>
								<td>
									<input id="payConfigId" class="text_style easyui-combobox" name="payConfigId"/>
								</td>
							</tr>
							<tr>
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
										onclick="OnlineRecharge.queryOnlineRechageButClick()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'icon-clear'"
									onclick="OnlineRecharge.cleanData()">重置</a>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 操作按钮 -->
			<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-export',plain:true"
			onclick="OnlineRecharge.toDownloadPage()">导出excel</a>
		</div>
		<div class="showDataListWrap">
			<table id="online_recharge_list_grid">
			</table>
		</div>
	</div>
</body>