<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<script type="text/javascript"
	src="${basePath}resources/js/content/contentManage/contColumn.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		
		<div id="scroll-bar-div"> 
			<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<form action="addContColumn.shtml" method="post" id="selectCont"
					name="selectCont">
					<div class="p5">
						<table class="formTable"> 
							<tr>
								<td class="align_right"><font
									color="red">*</font>栏目名称：</td>
								<td><input class="easyui-textbox" name="coluName" id="coluNameForm" value="${content.coluName}" required="required"/> 
									<input type="hidden" value="${content.pid}" name="pid" id="pid"/>
								</td>
							</tr>
							<tr>
								<td class="align_right"><font
									color="red">*</font>前台标示：</td>
								<td><input class="easyui-textbox" name="webTag" id="webTag" value="${content.webTag}" required="required"/> 
								</td>
							</tr>
							<tr>
								<td class="align_right"><font
									color="red"></font>描述：</td>
								<td><input class="easyui-textbox" name="cloDesc" id="cloDesc" value="${content.cloDesc}" /> 
								</td>
							</tr>
							<tr>
								<td class="align_right"><font
									color="red">*</font>状态：</td>
								<td>
								<input type="hidden" id="hiddenStatus"  value="${content.status}"/>
								<input type="radio" name="status" id="activationStatus" value="1" />启用<input
									type="radio" name="status" id="disableStatus" value="2" />禁用</td>
							</tr>

						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		//页面初始化  如果新增 默认状态为 激活，如果为修改回显原来的状态
		 if($("#hiddenStatus").val() != ""){
			 //激活
			 if($("#hiddenStatus").val() == "1"){
				 $("#activationStatus").attr("checked","checked");
			 }else if($("#hiddenStatus").val() == "2"){
				 //禁用
				 $("#disableStatus").attr("checked","checked");
			 }
		 } else{
			 //默认激活 
			 $("#activationStatus").attr("checked","checked");
		 }
	})
</script>
</body>




