<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/newStandard_add.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/newStandard.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/newStandard_Protocol.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/model/borrowChoseProtocolModel.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/borrow/borrowChoseProtocolHandle.js"></script>


<body class="easyui-layout">
	<input type="hidden" value="${view }" id="view">
	<input type="hidden" value="${type }" id="type">
	<div data-options="region:'center',border:false" class="borrowDetailPage">
				<div class="p10">
				<table>
					<tr>
						<td>
							<a class="overState borrowState " href="javascript:void(0);" onclick="styleController.productInfoShow()">产品资料</a>
						</td>
						<td>
							<div id="bar" class="greyBar "></div>
						</td>
						<td>
							<a id="bproc" class="greyState  borrowState "  href="javascript:void(0);" onclick="newStandard_add.next()">产品介绍</a>
						</td>
					
					</tr>
				</table>
				</div>
			<div class="pt10"></div>
			
			<form id="baseInfo" name="baseInfo">
			
			<div id="productInfo" class="easyui-panel" style="width:auto;padding-top:30px;overflow: auto;">
					<input type="hidden" name="pid" id="pid" value="${pid}" />
					<input type="hidden" name="sname" id="sname" value="${borrow.sname}" />
					<input type="hidden" name="customerName" id="customerName" value="${borrow.customerName}" />
					<input type="hidden" name="customerId" id="customerId" value="${borrow.customerId}" />
					<table class="cus_table formTable">
						<tr>
							<td class="align_right" width="120">借款人：</td>
							<td><a href="javacript:void(0)" onclick="viewCustomerInfo(${borrow.customerId})">${borrow.customerName }</a></td>
							<td class="align_right">借款人名称：</td>
							<td>${borrow.sname }</td>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 产品编号：</td>
							<td><input value="${borrow.borrowCode }" class="text_style easyui-validatebox" data-options="required:true" name="borrowCode" readonly="readonly"  /></td>
							<td class="align_right"> 产品标签：</td>
							<td><input class="text_style easyui-textbox" value="${borrow.borrowTag }" maxlength="20" name="borrowTag" /></td>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 产品名称：</td>
							<td colspan="3"><input class="text_style easyui-validatebox" data-options="required:true" value="${borrow.borrowName }" name="borrowName" id="borrowName"   style="width:705px" maxlength="40" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 标的类型： </td>
							<td >
							<c:if test="${type == '1' || borrow.borrowType == '4' }">
								<input type="radio"  name="borrowType" value="4"  checked="checked">新手标</input>
							</c:if>
							<c:if test="${type == '2' || borrow.borrowType == '5' }">
								<input type="radio" name="borrowType" value="5"  checked="checked">体验标</input></td>
							</c:if>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 标的金额：</td>
							<td colspan="3"><input id="borrowMoney" name="borrowMoney" value="${borrow.borrowMoney }" class="text_style easyui-validatebox" onblur="formatAmount(this);"  data-options="required:true" style="width: 200px;" /></td>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 还款方式：</td>
							<td>
								<input name="repaymentType" value="${borrow.repaymentType }" editable="false" id="repaymentType" class="easyui-validatebox easyui-combobox" 
									data-options="loadFilter:common.dictionaryFilter,
													valueField:'dictContCode',
													textField:'dictContName',
													multiple:false,
													url:'<%=REPAYMENT_TYPE %>'"  missingMessage="请选择还款方式!"/>
							</td>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 年化率：</td>
							<td colspan="3"><input id="borrowRate" value='<fmt:formatNumber value="${borrow.borrowRate*100}" pattern="#0.00" />'  name="brrowRateStr" class="text_style easyui-validatebox" onblur="formatAmount(this);" data-options="required:true" style="width: 100px;" /> %</td>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 期限：</td>
							<td colspan="3"><input id="borDeadline" value="${borrow.borDeadline }"  name="borDeadline" class="text_style easyui-validatebox"  validtype="intVilidate" data-options="required:true" style="width: 100px;" /> 天</td>
						</tr>
						
					 <c:if test="${type == '1' || borrow.borrowType == '4' }">
						<tr>
							<td class="align_right"><font color="red">*</font> 起投金额：</td>
							<td><input id="startMoney" name="startMoney" value="${borrow.startMoney }"  class="text_style easyui-validatebox" onblur="formatAmount(this);" data-options="required:true"   /></td>
							<td class="align_right"> 是否倍数投资：</td>
							<td><input name="isTimesInvest" class="text_style" type="checkbox" value="1" <c:if test="${borrow.isTimesInvest == 1}"> checked="checked"</c:if> /> 是 （只能是倍数投资）</td>
						</tr>
						<tr>
							<td class="align_right"><font color="red">*</font> 管理费率：</td>
							<td colspan="3"><input id="manageExpenseRate" value="<fmt:formatNumber value="${borrow.manageExpenseRate*100}" pattern="#0.00" />"  name="manageExpenseRate" class="text_style easyui-validatebox" onblur="formatAmount(this);" data-options="required:true" style="width: 100px;" /> %</td>
						</tr>
						<tr>
							<td class="align_right"> 投资上限：</td>
							<td><input id="endMoney" name="endMoney" value="${empty(borrow.endMoney)?'':borrow.endMoney }" onblur="formatAmount(this);" class="text_style easyui-validatebox"   /></td>
						</tr>
						</c:if>
						<tr>
							<td class="align_right"><font color="red">*</font> 计息类型：</td>
							<td>
								<input name="accrualType" value="${borrow.accrualType }" readonly="readonly" editable="false" id="accrualType" class="easyui-validatebox easyui-combobox" 
									data-options="loadFilter:common.dictionaryFilter,
											valueField:'dictContCode',
											textField:'dictContName',
											multiple:false,
											value:'2',
											url:'<%=NEW_ACCRUAL_TYPE %>'" missingMessage="请选择计息类型!" />
							</td>
						</tr>
						<tr>
							<td class="align_right vertical_align_top">产品描述：</td>
							<td colspan="3"><input class="easyui-textbox" value="${borrow.borrowDesc }" name="borrowDesc" data-options="multiline:true,validType:'length[0,120]'" style="width: 634px;height: 80px;" /></td>
						</tr>
						
						<tr>
							<td class="align_right vertical_align_top" >参与条件：</td>
							<td colspan="3">
								<input type="hidden" name="condIds" id="condIds" />
								<%-- 条件选择 --%>
								<%@ include file="common/pubCondition.jsp" %>	
							</td>
						</tr>
						<tr>
							<%-- 保存or编辑按钮 --%>
							<td colspan="4" align="center">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="newStandard_add.next()">下一步</a>
							</td>
						</tr>
					</table>
			</div>
		</form>
		<form id="productForm" name="productForm">
		<div id="productInc" class="easyui-panel" style="width:auto;padding-top:30px;overflow: auto;display: none">
				<input type="hidden" name="fpid" value="${financeProducts.pid }">
				<input type="hidden" name="borrowId" value="${financeProducts.borrowId }">
				<table class="cus_table formTable percent68">
					<tr>
						<td class="align_right" width="120">产品名称：</td>
						<td><span id="proname"></span></td>
					</tr>
					<tr>
						<td class="align_right">期限：</td>
						<td><span id="dl"></span>天</td>
					</tr>
						<td class="align_right">年化收益：</td>
						<td><span id="ac"></span>%</td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>加入条件：</td>
						<td><input class="text_style easyui-textbox" value="${financeProducts.joinCondition }" name="joinCondition" data-options="required:true" style="width: 200px;" /></td>
					</tr>
					
					<tr>
						<td class="align_right"><font color="red">*</font>计息时间：</td>
						<td><input class="text_style easyui-textbox" value="${financeProducts.interestTime }" name="interestTime" data-options="required:true" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>收益方式：</td>
						<td><input class="easyui-textbox" value="${financeProducts.earningMode }"  name="earningMode" data-options="multiline:true,required:true,validType:'length[0,255]'" style="width: 634px;height: 80px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>到期时间：</td>
						<td><input name="expirationDate" class="text_style easyui-textbox" value="${financeProducts.expirationDate }"  data-options="required:true" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>保障方式：</td>
						<td><input name="guaranteeMode" value="${financeProducts.guaranteeMode }" class="text_style easyui-textbox"  data-options="required:true" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>服务协议：</td>
						<input type="hidden" id="borrowAgreementId" value="${financeProducts.borrowAgreementId }" name="borrowAgreementId" />
						<td><input class="easyui-textbox" name="projectDescription" id="financeName" value="${financeProducts.projectDescription }" data-options="required:true"   readonly="readonly" style="width: 200px"  missingMessage="请选择协议!"/>
						<a id="choose" href="javascript:void(0);" class="easyui-linkbutton" onclick="newStandard_Protocol.toChoseProtocolPage();">选择协议</a></td>
					</tr>
					<tr>
						<td class="align_right"><font color="red">*</font>规则介绍：</td>
						<td>
							<input name="ruleIntroduction" class="easyui-textbox" value="${financeProducts.ruleIntroduction }" data-options="multiline:true,required:true,validType:'length[0,255]'" style="width: 634px;height: 80px;" />
						</td>
					</tr>
					
					<tr>
						<%-- 保存or编辑按钮 --%>
						<td colspan="2" align="center" height="30">
							<a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="newStandard_add.save()">保存</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="10">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {
            intOrFloat: {// 验证整数或小数
                validator: function (value) {
                    return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
                },
                message: '请输入数字，只可保留两位小数'
            },
            intVilidate:{// 验证整数或小数
                validator: function (value) {
                    return /^[1-9]\d*$/.test(value);
                },
                message: '请输入正整数'
            }
          
        });
		var view = $("#view").val();
		if(view == "yes"){
			$("#baseInfo :input").attr("readonly", "readonly");
			$("#productForm :input").attr("readonly", "readonly");
			$("#choose").hide();
			$("#save").hide();
		}
		
		/**格式化金额，带两位小数点*/
		function formatAmount(elName){
			var val = $(elName).val();
			if(val==null || val.trim()==""){
				$(elName).val("");
			}else{
				var value = parseFloat(val);
				if(isNaN(value)){
					$(elName).val("");
				}else{
					$(elName).val(value.toFixed(2));
				}
			}
		
		}
		
		</script>
</body>