<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/model/borrowPrelimManager_Model.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow_approve.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 引用借款公共页面 -->
		<%@ include file="borrow_common.jsp"%>
		<c:if test="${borrow.approveStatus == 2 ||  borrow.approveStatus == 3 ||  borrow.approveStatus == 5}">
			<div class="easyui-panel" title="借款担保审核">
		</c:if>
		
		<c:if test="${borrow.approveStatus == 4 ||  borrow.approveStatus == 5}">
			<div class="easyui-panel" title="借款贷前审核">
		</c:if>
		
			<form action="" method="post" id="borrowApprove" name="borrowApprove">
				<div style="padding: 5px" > 
					<table class="beforeloanTable formTable"  width="90%">
						<tr>
							<td class="label_right"> <font color="red">*</font>审核结果：</td>
							<td>
							<c:if test="${borrow.approveStatus eq '2' ||  borrow.approveStatus eq '5'}">
								<input type="radio" name="approveStatus" value="4" checked="checked"/>通过&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="approveStatus" value="3" />拒绝
							</c:if>
							<c:if test="${borrow.approveStatus eq '4'}">
								<input type="radio" name="approveStatus" value="6" checked="checked"/>通过&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="approveStatus" value="5" />拒绝
							</c:if>
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font>风控意见：</td>
							<td>
								<c:if test="${borrow.approveStatus eq '2' ||  borrow.approveStatus eq '5'}">
									<input class="easyui-textbox" name="vouchOpinion" id="vouchOpinion" style="width:65%;height:60px;min-width:450px;" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入意见信息!"/>
								</c:if>
								<c:if test="${borrow.approveStatus eq '4'}">
									<input class="easyui-textbox" name="lendOpinion" id="lendOpinion" style="width:65%;height:60px;min-width:450px;" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入意见信息!"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="label_right"><font color="red">*</font>备注：</td>
							<td><input class="easyui-textbox" name="borCondDesc" id="borCondDesc" style="width:65%;height:60px;min-width:450px;" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入备注信息!"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" id="pid" name="pid" value="${borrow.pid}">
								<input type="hidden" id="approveId" name="approveId" value="${borrow.approveId}">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="submitApprove()">提交</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	
		function submitApprove(){
			borrowApprove.submitApprove();
		}
		
		$(function(){
			var approveStatus = "${borrow.approveStatus}";
			if(approveStatus ==  2 ||  approveStatus == 5){
				borrow.borrowViewType = "1"; // 进度条能点击担保初审
			}else if(approveStatus == 4){
				borrow.borrowViewType = "2"; // 进度条能点击担保初审 贷前审批
			}
		})
	</script>
</body>