<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%-- <script type="text/javascript" src="${basePath}resources/js/activity/experience/pubWealthCoop_add.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowManage_add.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/model/borrowChoseProtocolModel.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/business/borrow/borrowChoseProtocolHandle.js"></script>
<body class="easyui-layout">
	
	<div class="easyui-panel" data-options="fit:true" style="width:auto;padding-top:30px;">
		<form id="baseInfo" name="baseInfo" action="" method="POST">
			<input type="hidden" name="borrowId" id="borrowId" value="${borrow.pid}" />  
			<input type="hidden" name="pageTag" id="pageTag" value="${pageTag }" /> 
			<input type="hidden" name="customerId" id="customerId" value="${customerId }" />
			<input type="hidden" name="pid" id="pid" value="${bizFinanceProducts.pid}" />  
			<div id="addBorrow"   modal="true" closed="true" style="width:500px;height:180px">
					<div class="p10">
						<table  width="100%" height="120">				
							<tr>
								<td align="center" width="145"><a href="#" class="overState borrowState" onclick="javascript:borrow.toAdd(1)">产品资料</a></td>
								<td>
									<div class="borrowBar" style="width: 100px;"></div>
								</td>
								<td align="center" width="145"><a href="#" class="overState borrowState" onclick="javascript:borrow.toAdd(2)">产品介绍</a></td>	
								<td>
									<div class="greyBar greyBarLast" style="width: 100px;"></div>
								</td>
								<td align="center" width="145"><a href="#" class="greyState borrowState" onclick="javascript:borrow.toAdd(2)">产品描述</a></td>					
							</tr>
						</table>
					</div>
				</div>
			<table class="cus_table formTable percent68">
				<tr>
					<td class="align_right" width="120">理财产品：</td>
					<td >${borrow.borrowName }</td> 
				</tr>
				<tr>
					<td class="align_right">期限：</td>
					<td>${borrow.borDeadline }个月</td> 
				</tr>
				<tr>
					<td class="align_right">年化收益：</td>
					<td class="align_left"><fmt:formatNumber value="${borrow.borrowRate }" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>%</td> 
				</tr>
				<tr>
					<td class="align_right" ><font color="red">*</font>加入条件：</td>
					<td><input class="text_style easyui-validatebox" id="joinCondition" name="joinCondition" data-options="required:true" style="width: 200px;" /></td>
				</tr>  
				<tr>
					<td class="align_right" ><font color="red">*</font>计息时间：</td>
					<td><input class="text_style easyui-validatebox" id="interestTime" name="interestTime" data-options="required:true" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="align_right" ><font color="red">*</font>收益方式：</td>
					<td>
						<input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="earningMode" id="earningMode" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入收益方式!" />
					</td>
				</tr>
				<tr>
					<td class="align_right" ><font color="red">*</font>到期时间：</td>
					<td><input class="text_style easyui-validatebox" id="expirationDate" name="expirationDate" data-options="required:true" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="align_right" ><font color="red">*</font>保障方式：</td>
					<td><input class="text_style easyui-validatebox" id="guaranteeMode" name="guaranteeMode" data-options="required:true" style="width: 200px;" /></td>
				</tr>
				
				<tr>
					<td class="align_right" ><font color="red">*</font>服务协议：</td>
					<td>
					<input type="hidden" id="borrowAgreementId" name="borrowAgreementId" />
					<input class="easyui-textbox" name="financeName" id="financeName"  data-options="required:true"   readonly="readonly" style="width: 200px"  missingMessage="请选择协议!"/>
					<a id="choose" href="javascript:void(0);" class="easyui-linkbutton" onclick="financialBorrowManage_add.toChoseProtocolPage();">选择协议</a>
					</td>
					 
				</tr>
				<tr>
					<td class="align_right" ><font color="red">*</font>规则介绍：</td>
					<td>
					<input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="ruleIntroduction" id="ruleIntroduction" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入规则介绍!" />
					</td>
				</tr>
				 
				<tr>
					<%-- 保存or编辑按钮 --%>
					<td colspan="2" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="financialBorrowManage_add.saveProducts()">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	 <script type="text/javascript">
	 	var borrowId = $("#borrowId").val();
	 	var pid = $("#pid").val();
	 	
	 	if(pid!="" && pid!="0" && pid!="1"){
	 		if(borrowId != ""){
	 			$.ajax( {
	 				type : "GET",
	 				url : BASE_PATH+"bizBorrowController/getProductByBorrowId.shtml?pid="+pid,
	 				dataType : "json",
	 				success : function(data) { 
	 					$("#baseInfo").form("reset");
	 			 		$("#baseInfo").form('load',data.product);
	 				}
	 			}); 
	 		}
	 	}
	 	 
	 </script>
</body>