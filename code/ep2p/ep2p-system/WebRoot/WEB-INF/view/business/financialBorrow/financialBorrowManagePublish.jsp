<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<script type="text/javascript" src="${basePath}resources/js/business/financial/financialBorrowManage.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 --> 
				<form action="" method="post" id="baseInfo" name=baseInfo>
					<div style="padding: 5px">
						<div class="easyui-panel" title="">
						<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right" width="100">产品名称：</td>
								<td> 
								${borrow.borrowName}
								</td> 
							</tr>
							<tr>
								<td class="align_right">金额：</td>
								<td>
								￥<fmt:formatNumber value="${borrow.borrowMoney}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>
								</td>
								 
							</tr>
							<tr>
								<td class="align_right">年利率：</td>
								<td>
								<fmt:formatNumber value="${borrow.borrowRate *100}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>% 
								</td>
								 
							</tr>
							<tr>
								<td class="align_right">期限：</td>
								<td> 
								${borrow.borDeadline}月
								</td> 
							</tr>
							
							<tr>
								<td class="align_right">还款方式：</td>
								<td> 
								${borrow.repaymentTypeName}
								</td> 
							</tr>
							<tr>
								<td class="align_right">投标奖励：</td>
								<td> 
								<fmt:formatNumber value="${borrow.investRewardScale == 0 || borrow.investRewardScale == null ? 0 :borrow.investRewardScale*100}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>% 
								</td> 
							</tr> 
							</table>
						</div>	
						<div class="pt10"></div>
						<div class="easyui-panel " title="预发布/发布">
							<table class="userTable percent90 formTable">
							<tr>
								<td class="align_right" width="100"><font color="red">*</font>发布时间：</td>
								<td>
									<input type="hidden" name="pid" id="pid" value="${borrow.pid}" />
									<input class="easyui-datetimebox" name="publishTime" data-options="required:true"/> 
								</td> 
							</tr>
							<tr>
								<td class="align_right"><font color="red">*</font>招标时间：</td> 
								<td>
									<input class="text_style easyui-validatebox" name="deadline" id="deadline"  data-options="required:true" onblur="isasdf(this)"/> 天
								</td> 
							</tr> 
						</table>	
						</div>						
					</div>
				</form>
			</div>	
		</div> 
	</div> 
	<div data-options="region:'center',border:false"  class="none" id="publish"> 
				<div style="padding: 5px">
					<table class="userTable percent90 formTable">							
						<tr>
							<td class="align_right" width="100">产品名称：</td>
							<td>  
							${borrow.borrowName}
							</td> 
						</tr>
						<tr>
							<td class="align_right">金额：</td>
							<td>
							￥<fmt:formatNumber value="${borrow.borrowMoney}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>
							</td>
							 
						</tr>
						<tr>
							<td class="align_right">年利率：</td>
							<td>
							<fmt:formatNumber value="${borrow.borrowRate*100}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>%
							</td>
							 
						</tr>
						<tr>
							<td class="align_right">期限：</td>
							<td> 
							${borrow.borDeadline}月
							</td> 
						</tr>
						
						<tr>
							<td class="align_right">还款方式：</td>
							<td> 
							${borrow.repaymentTypeName}
							</td> 
						</tr>
						<tr>
							<td class="align_right">投标奖励：</td>
							<td> 
							<fmt:formatNumber value="${borrow.investRewardScale == '' ? 0 :borrow.investRewardScale*100}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>%
							</td> 
						</tr> 
						
						<tr>
							<td class="align_right">招标天数：</td>
							<td id="deadlineShow"> 
							${borrow.deadline}天
							</td> 
						</tr>
						
						<tr>
							<td class="align_right">最大投资金额：</td>
							<td> 
							￥<fmt:formatNumber value="${borrow.endMoney == null ? 0 :borrow.endMoney}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber>
							</td> 
						</tr>
						<tr>
							<td class="align_right">最小投资金额：</td>
							<td> 
							￥<fmt:formatNumber value="${borrow.startMoney == null ? 0 :borrow.startMoney}" pattern="#,##0.00" minFractionDigits="2" ></fmt:formatNumber> 
							</td> 
						</tr>  
						<tr>
							<td height="35"></td>
							<td>
							<!-- 操作按钮 -->
							<div style="padding-bottom:5px">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="financialBorrowManage.publishConfirm()">确认</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="financialBorrowManage.publishCancel()">取消</a>
							</div>
							</td>
						</tr>
					</table>							
				</div>
	</div> 
	<script type="text/javascript"> 
	//$(function(){
	//	alert($("#deadline").parent().html())
		//alert($("input",$("#deadline").next("span").html()));
	//$("input",$("#deadline").next("span").children().first()).blur(function(){  
		//    alert("登录名已存在");  
		//})
	//}) 
	function isasdf(obj){ 
		var re = /^[0-9]*[1-9][0-9]*$/ ; 
		if(re.test(obj.value)){
			$("#deadlineShow").text(obj.value+"天");
		}else{
			$.messager.alert('操作提示',"招标天数必须是正整数！",'error');
		}
	}
	 </script>
</body>