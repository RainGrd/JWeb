<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%-- <script type="text/javascript" src="${basePath}resources/js/activity/experience/pubWealthCoop_add.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowManage_add.js" charset="utf-8"></script>
<body class="easyui-layout">
	
	<div class="easyui-panel" data-options="fit:true" style="width:auto;padding-top:30px;">
		<form id="baseInfo" name="baseInfo" action="" method="POST"> 
			<input type="hidden" name="pid" id="pid" value="${bizFinanceProducts.pid}" />  
			<input type="hidden" name="pageTag" id="pageTag" value="${pageTag }" /> 
			<input type="hidden" name="customerId" id="customerId" value="${customerId }" />
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
									<div class="borrowBar" style="width: 100px;"></div>
								</td>
								<td align="center" width="145"><a href="#" class="overState borrowState" onclick="javascript:borrow.toAdd(2)">产品描述</a></td>					
							</tr>
						</table>
					</div>
				</div>
			<table class="cus_table formTable percent68">
				<tr>
					<td class="align_right" width="120"><font color="red">*</font>项目描述：</td>
					<td>
					<input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="projectDescription" id="projectDescription" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入规则介绍!" />
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
	 	var pid = $("#pid").val(); 
	 	if(pid!="" && pid!="0" && pid!="1"){ 
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
	 	</script>
</body>