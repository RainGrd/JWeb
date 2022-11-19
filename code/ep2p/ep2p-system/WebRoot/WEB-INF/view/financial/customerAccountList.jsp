<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/finance/model/customerAccountManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/finance/customerAccount.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div style="padding: 5px">
						<table class="beforeloanTable percent90 formTable">
						  <tr>
								<td class="label_right">
									充值总额： 									
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-textbox" id="rechargeStartValue" name="rechargeStartValue" />~
										<input class="easyui-textbox" id="rechargeEndValue" name="rechargeEndValue" />
									</div>
								</td>
								<td class="label_right">
									提现总额： 									
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-textbox" id="withdrawStartValue" name="withdrawStartValue" />~
										<input class="easyui-textbox" id="withdrawEndValue" name="withdrawEndValue" />
									</div>
								</td>
								<td class="label_right">
									总待收金额： 								
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-textbox" id="dueStartValue" name="dueStartValue" />~
										<input class="easyui-textbox" id="dueEndValue" name="dueEndValue" />
									</div>
								</td>
							</tr>
							  <tr>
								<td class="label_right">
									可用金额： 									
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-textbox" id="availableStartValue" name="availableStartValue" />~
										<input class="easyui-textbox" id="availableEndValue" name="availableEndValue" />
									</div>
								</td>
								<td class="label_right">
									冻结金额： 									
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-textbox" id="freezeStartValue" name="freezeStartValue" />~
										<input class="easyui-textbox" id="freezeEndValue" name="freezeEndValue" />
									</div>
								</td>
								<td class="label_right">
									投标金额： 								
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-textbox" id="tenderStartValue" name="tenderStartValue" />~
										<input class="easyui-textbox" id="tenderEndValue" name="tenderEndValue" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="label_right">
									客户名： 									
								</td>
								<td><input class="easyui-textbox" name="customerName" id="customerName" /></td>
								<td class="label_right">
									姓名： 									
								</td>
								<td><input class="easyui-textbox" name="sname" id="sname" /></td>
								<td class="label_right">
									手机号码： 								
								</td>
								<td><input class="easyui-textbox" name="phoneNo" id="phoneNo"/></td>
							</tr>
							<tr>
								<td class="label_right">
									注册时间： 									
								</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" id="registrationStartValue" name="registrationStartValue" />~
										<input class="easyui-datebox" id="registrationEndValue" name="registrationEndValue"  />
									</div>
								</td>
								<td class="label_right">
								</td>
								<td>
								</td>
								<td></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="customerAccount.searchData()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="customerAccount.cleanData()">重置</a>
								</td>
							</tr>
							
						</table>	
					</div>
				</form>
			</div>	
				
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true"  onclick="customerAccount.toDownloadPage()">导出EXCEL</a>
		</div>
	
		<div class="showDataListWrap">
			<table id="customerAccountGrid">
			</table>
		</div>
	</div>
</body>