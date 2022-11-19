<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%-- <script type="text/javascript" src="${basePath}resources/js/activity/experience/pubWealthCoop_add.js" charset="utf-8"></script> --%>  
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowManage_add.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/model/borrowChoseProtocolModel.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/borrow/borrowChoseProtocolHandle.js"></script>
<body class="easyui-layout">

	<div class="easyui-panel" data-options="fit:true">


		<!-- 产品资料开始 -->
		<div modal="true" closed="true" id="borrowBase">
			<div class="" style="width: 500px; margin-left: 100px">
				<table>
					<tr>
						<td align="center" width="145"><a href="#"
							class="overState borrowState" onclick="switchTab('borrowBase')">产品资料</a></td>
						<td>
							<c:if test="${view == 'readView'}">
								<div class="greyBar overBar"></div>
							</c:if>
							<c:if test="${view != 'readView'}">
								<div class="greyBar greyBarLast"></div>
							</c:if>
						</td>
						<td align="center" width="145">
							<c:if test="${view == 'readView'}">
								<a href="#"	class="overState borrowState" onclick="switchTab('borrowMiddel')">产品介绍</a>
							</c:if>
							<c:if test="${view != 'readView'}">
								<a href="#"	class="greyState borrowState" onclick="switchTab('borrowMiddel')">产品介绍</a>
							</c:if>
						</td>
						<td>
							<c:if test="${view == 'readView'}">
								<div class="greyBar overBar"></div>
							</c:if>
							<c:if test="${view != 'readView'}">
								<div class="greyBar greyBarLast"></div>
							</c:if>
						</td>
						<td align="center" width="145">
							<c:if test="${view == 'readView'}">
								<a href="#" class="overState borrowState" onclick="switchTab('borrowLow')">产品描述</a>
							</c:if>
							<c:if test="${view != 'readView'}">
								<a href="#" class="greyState borrowState" onclick="switchTab('borrowLow')">产品描述</a>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			<form id="borrowBaseForm" name="baseInfo" action="" method="POST">
				<input type="hidden" name="infoVO.pid" id="borrowPid" value="${borrow.pid}" />
				<input type="hidden" name="customerId" id="customerId" value="${cusTomer.pid}" />  
				<table class="cus_table formTable percent68 ">
					<tr>
						<td class="align_right">借款人：</td>
						<td id="customerIdShow"><a href="javacript:void(0)"
							onclick="openUserPage(${cusTomer.pid})">${empty cusTomer.customerName ? cusTomer.phoneNo : cusTomer.customerName }</a>
							<%-- 					<a onclick="openUserPage(${cusTomer.customerId})" href="javacript:void(0)">${cusTomer.customerName}</a>  --%>
						</td>
						<td class="align_right">借款人名称：</td>
						<td id="sname">${cusTomer.sname}</td>
					</tr>
					<tr>
						<td class="align_right">产品编号：</td>
						<td><input class="text_style easyui-validatebox"
							name="borrowCode" readonly="readonly" /></td>
						<td class="align_right">产品标签：</td>
						<td><input class="text_style easyui-validatebox"
							name="borrowTag" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>产品名称：</td>
						<td colspan="3" class="align_left"><input
							class="text_style easyui-validatebox" name="borrowName"
							data-options="required:true" /></td>
					</tr>
					<tr>
						<td class="align_right" id="wealthCoopValueHTML"><font
							color="red">*</font>理财金额：</td>
						<td><input class="text_style easyui-numberbox " precision="2"
							name="borrowMoney" data-options="required:true" /></td>
						<td class="align_right"></td>
						<td></td>
					</tr>
					<tr>
						<td class="align_right" id="wealthCoopValueHTML"><font
							color="red">*</font>还款方式：</td>
						<td colspan="3"><input id="repaymentType"
							name="repaymentType" class="easyui-validatebox easyui-combobox"
							panelHeight="auto" missingMessage="请选择还款方式!" style="width: 100px"
							data-options="editable:false,validType:'checkSelectedValue',
           							 loadFilter:common.dictionaryFilter,
           							 valueField:'dictContCode',
           							 textField:'dictContName',
           							 multiple:false,url:'<%=REPAYMENT_TYPE%>'" />
						</td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>年化率：</td>
						<td><input name="borrowRate" id="borrowRateShow"
							class="text_style easyui-numberbox" precision="2"
							data-options="required:true" style="width: 100px;" />%</td>
						<td class="align_right"></td>
						<td></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>管理费率：</td>
						<td><input id="manageExpenseRate" name="manageExpenseRate"
							class="text_style easyui-numberbox" precision="2"
							data-options="required:true" style="width: 100px;" />%</td>
						<td class="align_right"></td>
						<td></td>
					</tr>
					<tr>
						<td class="align_right vertical_align_top">期限(月)：</td>
						<td colspan="3"><select class="easyui-combobox"
							name="borDeadline" panelHeight="auto" editable="false">
								<option value=1 selected="selected">1</option>
								<option value=3>3</option>
								<option value=6>6</option>
								<option value=12>12</option>
								<option value=16>16</option>
								<option value=24>24</option>
								<option value=30>30</option>
								<option value=36>36</option>
						</select></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>起投金额：</td>
						<td class="align_left"><input
							class="text_style easyui-numberbox" id="startMoney"
							name="startMoney" precision="2" data-options="required:true"
							style="width: 100px;" /></td>
						<td class="align_right">是否倍数投资：</td>
						<td class="align_left"><input type="checkbox"
							name="isTimesInvest" value="1">是 （只能是起投金额的倍数）</td>

					</tr>
					<tr>
						<td class="align_right">投资上限：</td>
						<td><input class="text_style easyui-numberbox" id="endMoney"
							precision="2" name="endMoney" style="width: 100px;" /></td>
						<td class="align_right"></td>
						<td></td>
					</tr>
					<tr>
						<td class="align_right vertical_align_top"><font color="red">*</font>计息类型：</td>
						<td colspan="3"><input id="accrualType" name="accrualType"
							class="easyui-combobox" panelHeight="auto"
							missingMessage="请选择计息类型！" style="width: auto"
							data-options="editable:false,validType:'checkSelectedValue',
           							 loadFilter:common.dictionaryFilter,
           							 valueField:'dictContCode',
           							 textField:'dictContName',
           							 value:'1',
           							 multiple:false,url:'<%=FINANCE_ACCRUAL_TYPE%>'" />
						</td>
					</tr>
					<tr>
						<td class="align_right">允许使用加息券：</td>
						<td class="align_left"><input type="radio"
							name="isJiaxiTicket" id="isJiaxiTicketTrue" value="1">是 <input
							type="radio" name="isJiaxiTicket" id="isJiaxiTicketFalse"
							value="2">否</td>
						<td class="align_left"></td>
						<td class="align_right"></td>

					</tr>
					<tr>
						<td class="align_right">投资奖励比列：</td>
						<td class="align_left"><input
							class="text_style easyui-numberbox" precision="2"
							id="investRewardScale" name="investRewardScale"
							style="width: 100px;" /> %（投资金额的百分比）</td>
						<td class="align_right"></td>
						<td class="align_right"></td>

					</tr>
					<tr>
						<td class="align_right">允许转让债权：</td>
						<td class="align_left"><input type="radio"
							name="isEquitableAssignment" id="isEquitableAssignmentTrue"
							value="1">是 <input type="radio"
							name="isEquitableAssignment" id="isEquitableAssignmentFalse"
							value="2">否</td>
						<td class="align_right"></td>
						<td class="align_right"></td>
					</tr>
					<tr style="display: none;">
						<td class="align_right">债权转让允许的利率：</td>
						<td class="align_left"><input
							class="text_style easyui-numberbox" id="startValue" precision="2"
							name="startValue" style="width: 100px;" value="5.0" />% ~<input
							class="text_style easyui-numberbox" id="endValue" precision="2"
							name="endValue" style="width: 100px;" value="24.0" />%</td>
						<td class="align_right"></td>
						<td class="align_right"></td>

					</tr>
					<tr>
						<td class="align_right">产品描述：</td>
						<td colspan="3"><input class="easyui-textbox"
							name="borrowDesc"
							data-options="multiline:true,validType:'length[0,120]'"
							style="width: 634px; height: 80px;" /></td>
					</tr>
					<tr>
						<td class="align_right vertical_align_top">活动参与条件：</td>
						<td colspan="3"><input type="hidden" name="condIds"
							id="condIds" /> <%-- 条件选择 --%> <%@ include
								file="../../activity/common/pubCondition.jsp"%>
						</td>
					</tr>
					<tr>
						<%-- 保存or编辑按钮 --%>
						<td colspan="4" align="center"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-save"
							onclick="switchTab('borrowMiddel')">下一步</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 产品资料结束 -->

		<!-- 产品介绍开始 -->

		<div modal="true" closed="true" id="borrowMiddel" class="none">
			<div class="" style="width: 500px; margin-left: 100px">
				<table width="100%" height="120">
					<tr>
						<td align="center" width="145"><a href="#"
							class="overState borrowState" onclick="switchTab('borrowBase')">产品资料</a></td>
						<td>
							<div class="borrowBar" style="width: 100px;"></div>
						</td>
						<td align="center" width="145"><a href="#"
							class="overState borrowState" onclick="switchTab('borrowMiddel')">产品介绍</a></td>
						<td>
							<c:if test="${view == 'readView'}">
								<div class="borrowBar" style="width: 100px;"></div>
							</c:if>
							<c:if test="${view != 'readView'}">
								<div class="greyBar greyBarLast" style="width: 100px;"></div>
							</c:if>
						</td>
						<td align="center" width="145">
							<c:if test="${view == 'readView'}">
								<a href="#" class="overState borrowState" onclick="switchTab('borrowLow')">产品描述</a>
							</c:if>
							<c:if test="${view != 'readView'}">
								<a href="#" class="greyState borrowState" onclick="switchTab('borrowLow')">产品描述</a>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			<form id="borrowMiddelForm" name="baseInfo" action="" method="POST"> 
				<input type="hidden" name="products.pid" id="productsPid" value="${bizFinanceProducts.pid}" />
				<table class="cus_table formTable percent68">

					<tr>
						<td class="align_right" width="120">理财产品：</td>
						<td id="borrowName"></td>
					</tr>
					<tr>
						<td class="align_right">期限：</td>
						<td id="borDeadline"></td>
					</tr>
					<tr>
						<td class="align_right">年化收益：</td>
						<td class="align_left" id="borrowRate"> </td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>加入条件：</td>
						<td><input class="text_style easyui-validatebox"
							id="joinCondition" name="joinCondition"
							data-options="required:true" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>计息时间：</td>
						<td><input class="text_style easyui-validatebox"
							id="interestTime" name="interestTime"
							data-options="required:true" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>收益方式：</td>
						<td><input class="easyui-textbox"
							style="width: 65%; height: 60px; min-width: 450px;"
							name="earningMode" id="earningMode" required="true"
							data-options="multiline:true,validType:'length[0,255]'"
							missingMessage="请输入收益方式!" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>到期时间：</td>
						<td><input class="text_style easyui-validatebox"
							id="expirationDate" name="expirationDate"
							data-options="required:true" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>保障方式：</td>
						<td><input class="text_style easyui-validatebox"
							id="guaranteeMode" name="guaranteeMode"
							data-options="required:true" style="width: 200px;" /></td>
					</tr>

					<tr>
						<td class="align_right"><font color="red">*</font>服务协议：</td>
						<td><input type="hidden" id="borrowAgreementId"
							name="borrowAgreementId" /> <input class="easyui-textbox"
							name="financeName" id="financeName" data-options="required:true"
							readonly="readonly" style="width: 200px" missingMessage="请选择协议!" />
							<a id="choose" href="javascript:void(0);"
							class="easyui-linkbutton"
							onclick="financialBorrowManage_add.toChoseProtocolPage();">选择协议</a>
						</td>

					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>规则介绍：</td>
						<td><input class="easyui-textbox"
							style="width: 65%; height: 60px; min-width: 450px;"
							name="ruleIntroduction" id="ruleIntroduction" required="true"
							data-options="multiline:true,validType:'length[0,255]'"
							missingMessage="请输入规则介绍!" /></td>
					</tr>

					<tr>
						<%-- 保存or编辑按钮 --%>
						<td colspan="2" align="center"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-save"
							onclick="switchTab('borrowLow')">下一步</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 产品介绍结束 -->

		<!-- 产品描述开始 -->
		<div modal="true" closed="true" id="borrowLow" class="none">
			<div class="" style="width: 500px; margin-left: 100px">
				<table width="100%" height="120">
					<tr>
						<td align="center" width="145"><a href="#"
							class="overState borrowState" onclick="switchTab('borrowBase')">产品资料</a></td>
						<td>
							<div class="borrowBar" style="width: 100px;"></div>
						</td>
						<td align="center" width="145"><a href="#"
							class="overState borrowState" onclick="switchTab('borrowMiddel')">产品介绍</a></td>
						<td>
							<div class="borrowBar" style="width: 100px;"></div>
						</td>
						<td align="center" width="145"><a href="#"
							class="overState borrowState" onclick="switchTab('borrowLow')">产品描述</a></td>
					</tr>
				</table>
			</div>
			<form id="borrowLowForm" name="baseInfo" action="" method="POST">
				<table class="cus_table formTable percent68 ">
					<tr>
						<td class="align_right" width="120"><font color="red">*</font>项目描述：</td>
						<td><input class="easyui-textbox"
							style="width: 65%; height: 60px; min-width: 450px;"
							name="projectDescription" id="projectDescription" required="true"
							data-options="multiline:true,validType:'length[0,255]'"
							missingMessage="请输入规则介绍!" /></td>
					</tr>
					<tr>
						<%-- 保存or编辑按钮 --%>
						<td colspan="2" align="center"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-save"
							onclick="financialBorrowManage_add.saveBorrow()">保存</a></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 产品描述结束 -->
	</div>
	<script type="text/javascript">
	/**
	 * 页面数据初始化
	 */
	$(function(){
		var pid = $("#borrowPid").val();
		var view = '${view}';
		if(pid=="" || pid=="0" || pid=="1"){
			financialBorrowManage_add.generatNoRuleTem("C","8",4);
			financialBorrowManage_add.initCondition_add();
			$("#isJiaxiTicketTrue").attr("checked","checked");
			$("#isEquitableAssignmentTrue").attr("checked","checked");
			$("#startValue").combo("setValue","5.0");
			$("#endValue").combo("setValue","24.0");
		}else{
			financialBorrowManage_add.initPubExpGold_edit(pid,3);
		} 
		if(view =='readView'){
			disabledForm('borrowBaseForm');
			disabledForm('borrowMiddelForm');
			disabledForm('borrowLowForm');
		}
	});
	/*
	  点击 产品资料  产品介绍 产品描述 事件 
	 */ 
	function switchTab(tableId){
		var view = '${view}';
		//如果是显示的 则不做操作
		if($("#"+tableId).is(":visible")){
			return;
		}
		//点击时 校验上一个页面的必填项
		if(tableId == "borrowMiddel"){
			if(view != 'readView'){
				if(!$("#borrowBaseForm").form('validate')){
					return ;
				} 
			}
			$("#borrowName").text($("#borrowBaseForm [name=borrowName]").val());
			$("#borDeadline").text($("#borrowBaseForm [name=borDeadline]").val()+"个月");
			$("#borrowRate").text($("#borrowBaseForm [name=borrowRate]").val()+"%"); 
		}else if(tableId == "borrowLow"){
			if(view != 'readView'){
				if(!$("#borrowBaseForm").form('validate')){
					return ;
				}
				if(!$("#borrowMiddelForm").form('validate')){
					$("#borrowMiddel").show();
					$("#borrowMiddel").siblings().hide(); 
					return ;
				}
			}
		} 
		$("#"+tableId).show();
		$("#"+tableId).siblings().hide();
		if(!$("#"+tableId+"Form").form('validate')){
			return ;
		}  
	} 
	//禁用表单方法
	function disabledForm(fromId){
		$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').attr('disabled','disabled');
		$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').attr('readOnly','readOnly');
		$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' input,#'+fromId+' textarea').addClass('l-btn-disabled');
		$('#'+fromId+' .easyui-linkbutton ,#'+fromId+' a:not(.downloadImg)').removeAttr('onclick');
		$('#'+fromId+' a font').attr('color','gray');
	}
	</script>
</body>