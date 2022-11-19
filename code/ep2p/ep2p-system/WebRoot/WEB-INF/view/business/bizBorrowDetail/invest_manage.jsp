<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/model/invest_manage_model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/invest_manage.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<!-- 查询条件 -->
				<form action="" method="post" id="invest_manage_form" name="invest_manage_form">
					<div style="padding: 5px">
						<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right">投资人：</td>
								<td><input class="easyui-textbox"
									name="customer.customerName" id="customerName" /></td>
								<td class="align_right">姓名：</td>
								<td><input class="easyui-validatebox" name="customer.sname"
									id="sname" /></td>
							</tr>
							<tr>
								<td class="align_right">手机号码：</td>
								<td><input class="easyui-textbox"
									name="customer.phoneNo" id="phoneNo" /></td>
								<td class="align_right">投资日期：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" id="beginInvestmentTime"
											name="beginInvestmentTime" data-options="editable:false" /> ~
											<input
											class="easyui-datebox" id="endInvestmentTime"
											name="endInvestmentTime" data-options="editable:false" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="align_right">借款状态：</td>
								<td><input name="bizBorrow.borStatus" id="borStatus"
								class="easyui-validatebox easyui-combobox"
								data-options="editable:false,loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=INVEST_BOR_STATUS%>'" />
								</td>
								<td class="align_right">计息日期：</td>
								<td>
									<div class="rangDate">
										<input class="easyui-datebox" id="beginInvestmentTime"
											name="beginInterestTime" data-options="editable:false" /> ~
										<input class="easyui-datebox" id="endInvestmentTime"
											name="endInterestTime" data-options="editable:false" />
										<a href="javascript:void(0);" data-options='iconCls:"icon-more"'
										onclick="InvestManage.searchCd()">更多条件</a> 
									</div>
								</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">借款编号：</td>
								<td><input class="easyui-textbox" style="width:173px;" name="bizBorrow.borrowCode" id="borrowCode" /></td>
								<td class="align_right">借款名称：</td>
								<td><input class="easyui-validatebox" name="bizBorrow.borrowName" id="borrowName"/></td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">借款类型：</td>
								<td>
									<input name="bizBorrow.borrowType" id="borrowType" class="easyui-validatebox easyui-combobox" 
										data-options="editable:'false',loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=BORROW_TYPE %>'" />
								</td>
								<td class="align_right">金额范围：</td>
								<td>
									<input class="easyui-validatebox" id="minInvestmentAmount" name="minInvestmentAmount" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="maxInvestmentAmount" name="maxInvestmentAmount"  style="width: 80px;"/>
								</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">利率范围(%)：</td>
								<td>
									<input class="easyui-validatebox" id="borrowRateMin" name="bizBorrow.borrowRateMin" style="width: 80px;"/>~
									<input class="easyui-validatebox" id="borrowRateMax" name="bizBorrow.borrowRateMax" style="width: 80px;" />
								</td>
								<td class="align_right">期限范围（月）：</td>
								<td>
									<input class="easyui-validatebox" id="borDeadlineMin" name="bizBorrow.borDeadlineMin" style="width: 80px;" />~
									<input class="easyui-validatebox" id="borDeadlineMax" name="bizBorrow.borDeadlineMax" style="width: 80px;" />
								</td>
							</tr>
							<tr class="moreTr none">
								<td class="align_right">还款方式：</td>
								<td>
									<input name="bizBorrow.repaymentType" id="repaymentType" class="easyui-combobox" 
										data-options="editable:'false',loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=REPAYMENT_TYPE %>'" />
								</td>
								<td class="align_right">计息方式：</td>
								<td class="align_right">
									<div class="left">
									<input name="bizBorrow.accrualType" id="accrualType" class="easyui-combobox" 
										data-options="editable:'false',loadFilter:common.dictionaryFilter,
														valueField:'dictContCode',
														textField:'dictContName',
														multiple:false,
														url:'<%=ACCRUAL_TYPE %>'" />
									</div>
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-search"'
									onclick="InvestManage.searchData()">查询</a> 
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-clear"'
									onclick="InvestManage.resetForm()">重置</a>
								</td>
							</tr>
							<tr class="moreTr">
								<td class="align_right"></td>
								<td>
								</td>
								<td></td>
								<td class="align_right">
									
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-search"'
									onclick="InvestManage.searchData()">查询</a> 
									<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options='iconCls:"icon-clear"'
									onclick="InvestManage.resetForm()">重置</a>
								</td>
							</tr>
						</table>							
					</div>
				</form>
		</div>
		<div class="showDataListWrap">
			<table id="invest_manage_grid"></table>
		</div>
	</div>
</body>