<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/custom/customerSearchList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/batchReplaceCustomer.js"></script>
<script type="text/javascript" src="${basePath}resources/js/custom/model/customerSearchModel.js"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel">
			<!-- 查询条件 -->
				<form action="getCustomerSearchList.shtml" method="post" id="searcher"
					name="searcher">
					<div style="padding: 5px">
						<table class="userTable percent100 formTable">
						  <tr>
							<td class="align_right">用户名：</td>
							<td><input class="easyui-textbox"  id="customer_name" name="customerName"/> </td>
							<td class="align_right">姓名：</td>
							<td><input class="easyui-textbox" id="s_name" name="sname" /> </td>
							<td class="align_right">注册时间：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" id="happen_Begin_Time" name="happenBeginTime" data-options="editable:false" />~
								<input class="easyui-datebox" id="happen_End_Time" name="happenEndTime" data-options="editable:false" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="align_right">手机号码：</td> 
							<td><input class="easyui-textbox"  id="phone_no" name="phoneNo" /> </td>
							<td class="align_right">客服：</td>
								<td>
								    <input id="cusServicePid" name="cusServicePid" class="easyui-combobox" 
								         panelHeight="auto"
										 data-options="loadFilter:common.dictionaryFilter,
										 valueField:'cusServicePid',
										 textField:'cusServiceName',
										 multiple:false,
										 editable:false,
										 url:'<%=basePath%>/customerController/selectCustomer.shtml'"/>
								</td>
							<td class="align_right">可用余额：</td>
							<td>
								<input class="easyui-textbox" data-options="width:100" id="available_startbalance" name="availableBeginBalance" />~
								<input class="easyui-textbox" data-options="width:100" id="available_endbalance"  name="availableEndBalance" />
							</td>
						</tr>
						<tr>
							<td class="align_right">认证状态：</td>
							<td>
						        <select name="isAttestation" editable="false" id="isAttestation"
									  class="easyui-combobox">
								<option value="0">--全部--</option>
								<option value="1">待审核</option>
								<option value="2">已认证</option>
								<option value="3">未认证</option>
								<option value="4">已拒绝</option>
								</select> 
							</td>
							<td class="align_right">VIP：</td>
							<td>
								    <input id="vipPID" name="vipPID" class="easyui-combobox"
								         panelHeight="auto"
										 data-options="loadFilter:common.dictionaryFilter,
										 valueField:'vipPID',
										 textField:'vipLevelName',
										 multiple:false,
										 editable:false,
										 multiple:true,
										 url:'<%=basePath%>/customerController/selectVipLevel.shtml'"/>
								</td>
							<td class="align_right">推荐人：</td>
							<td><input class="easyui-textbox"  id="referee_user" name="refereeUser" /> </td>
					  </tr>
					  <tr>
					  	<td></td>
					  	<td colspan="5">
					  		<div class="check_box pt10">
						   		<label class="fontNormal"><input type="checkbox"  id="is_blacklist" name="isBlacklist" value="0" />黑名单</label>
							    <label class="fontNormal"><input type="checkbox"  id="is_vip" name="isVip" value="1"/>公海客户</label>
							    <label class="fontNormal"><input type="checkbox"  id="is_forbid_withdraw" name="isForbidWithdraw" value="1"/>禁止提现</label>
							    <label class="fontNormal"><input type="checkbox"  id="is_forbid_transfer" name="isForbidTransfer" value="1"/>禁止债权转让</label>
							    <label class="fontNormal"><input type="checkbox"  id="" name="disableUser" value="1"/>禁用客户</label>
							    <label class="fontNormal"><input type="checkbox"  id="" name="cusBor" value="1"/>仅借款客户</label>
							    <label class="fontNormal"><input type="checkbox"  id="" name="cusInv" value="1"/>仅投资客户</label>
							   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="customerList.searchData()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							   <a href="#" onclick="javascript:$('#searcher').form('reset');" iconCls="icon-clear" class="easyui-linkbutton">重置</a>
					  		</div>
					  	</td>
					  </tr>
					</table>
				</div>
				</form>
			
			<!-- 操作按钮 -->
			<div>	
			<table>
				<tr>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true"  onclick="customerList.excelPort();">导出Excel</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  onclick="batchReplaceCus.batchReplaceCustomer();">批量更换客服</a>
					</td>
				</tr>
			</table>
			</div>
		</div>
		<!--  数据列表 -->		
		<div class="userListDiv" style="height:100%">
			<table id="grid" >
			</table>
		</div>
		
		<script type="text/javascript">
		customerList.initDataGrid();
		$(document).ready(function(){
			//customerList.initDataGrid();	
		});
	</script>
	</div>
</body>