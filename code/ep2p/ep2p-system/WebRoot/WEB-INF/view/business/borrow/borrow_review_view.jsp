<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow.js" charset="utf-8"></script>
<body class="easyui-layout ">
	<div data-options="region:'center',border:false">
	
		<!-- 引用借款公共页面 -->
		<%@ include file="borrow_common.jsp"%>
		
		<!-- 借款信息 -->
		<div id="loanInfo" class="easyui-panel" title="借款信息">
			<div style="padding: 5px">
				<form action="" method="post" id="loanInfoForm" name="loanInfoForm">
					<input type="hidden" name="view" id="view" value="${view}"/>
					<table class="formTable">
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款类型： </td>
							<td >${borrow.borrowTypeName}</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款编号： </td>
							<td >  <input class="easyui-textbox" name="borrowCode" id="borrowCode" style="width: 200px;" required="true" readOnly="readOnly" missingMessage="请输入借款编号!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款名称： </td>
							<td ><input class="easyui-textbox" name="borrowName" id="borrowName" style="width: 400px;" required="true" missingMessage="请输入借款名称!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款金额： </td>
							<td >  <input class="easyui-numberbox" precision="2" groupSeparator="," name="borrowMoney" id="borrowMoney" min="0" max="20000000" style="width: 200px;" required="true" missingMessage="请输入借款金额!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 还款方式： </td>
							<td >
								<input id="repaymentType" name="repaymentType" class="easyui-combobox" required="true" panelHeight="auto" missingMessage="请选择还款方式!"  style="width:100px"
           							 data-options="validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=REPAYMENT_TYPE%>'" />
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款期限： </td>
							<td >
								<input id="borDeadline" name="borDeadline" class="easyui-combobox" required="true" panelHeight="auto" missingMessage="请选择借款期限!"  style="width:100px"
           							 data-options="validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=BORROW_TIME%>'" />&nbsp; 月
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 年化率： </td>
							<td >
								 <input class="easyui-numberbox" precision="2" groupSeparator="," name="borrowRate" id="borrowRate" style="width: 200px;" required="true" missingMessage="请输入借款利率!" /> %
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 管理费率： </td>
							<td >
								 <input class="easyui-numberbox" precision="2" groupSeparator="," name="manageExpenseRate" id="manageExpenseRate" style="width: 200px;" required="true" missingMessage="请输入借款利率!" /> %
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 计息类型： </td>
							<td >
								<input id="accrualType" name="accrualType" class="easyui-combobox" required="true" panelHeight="auto" missingMessage="请选择计息类型!"  style="width:100px"
           							 data-options="validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=BORROW_ACCRUAL_TYPE%>'" />
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 起投金额： </td>
							<td >
								 <input class="easyui-numberbox" name="startMoney" id="startMoney" precision="2" groupSeparator="," style="width: 200px;" required="true" missingMessage="请输入起投金额!" />
								 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 是否倍数投资：  <input type="checkbox" id="isTimesInvest" name="isTimesInvest" value="1"> 是 （只能是起投金额的倍数）
							</td>
						</tr>
						<tr>
							<td class="label_right"> 投资上限： </td>
							<td >
								<input class="easyui-numberbox" name="endMoney" id="endMoney" precision="2" groupSeparator="," style="width: 200px;" missingMessage="请输入投资上限金额!" />
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 允许使用加息券： </td>
							<td >
								<input type="radio" id="isJiaxiTicket" name="isJiaxiTicket" value="1">是</input><input type="radio" id="isJiaxiTicket" name="isJiaxiTicket" value="2" checked >否</input></td>
							</td>
						</tr>
						<tr>
							<td class="label_right"> 投资奖励比例： </td>
							<td >
								<input class="easyui-numberbox" precision="2" groupSeparator="," name="investRewardScale" id="investRewardScale" style="width: 50px;" missingMessage="请输入投资奖励比例!" /> % 
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 允许转让债权： </td>
							<td >
								<input type="radio" id="isEquitableAssignment" name="isEquitableAssignment" value="1" checked  >是</input><input type="radio" id="isEquitableAssignment" name="isEquitableAssignment" value="2">否</input></td>
							</td>
						</tr>
						<!-- 
						<tr>
							<td class="label_right"> <font color="red">*</font> 债权转让允许的利率： </td>
							<td >
								<input class="easyui-numberbox" name="startValue" precision="2" groupSeparator="," id="startValue" style="width: 50px;"/> % ~ <input class="easyui-numberbox" precision="2" groupSeparator="," name="endValue" id="endValue" style="width: 50px;"/>%
							</td>
						</tr>
						 -->
						<tr>
							<td class="label_right"></td>
							<td >
								
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.saveLoadInfo('#loanInfoForm');">保存</a>
							</td>
						</tr>
					</table>
					
					<!-- 借款ID隐藏域 -->
					<input type="hidden" id="pid" name="pid" value="${borrow.pid}">	
				</form>
			</div>
		</div>
		<div class="pt10"></div>
		<!-- 个人基本资料 -->
		<div id="baseInfo" class="easyui-panel" title="借款人基本资料">			
			<div style="padding: 5px">
				<form action="" method="post" id="basicDataForm" name="basicDataForm">
					<table class="formTable">
						<tr>
							<td class="label_right"> 借款人：</td>
							<td ><label id="customerName"></label></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 姓名： </td>
							<td >  <input class="easyui-textbox" name="sname" id="sname" required="true" data-options="validType:'length[0,10]'" missingMessage="请输入借款人姓名!"/></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 性别： </td>
							<td ><input type="radio" id="sex" name="sex" value="1">男</input><input type="radio" id="sex" name="sex" value="2">女</input></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 婚姻状况： </td>
							<td >  
								<input id="isMarriage" name="isMarriage" class="easyui-combobox" panelHeight="auto" missingMessage="请选择婚姻状况!" required="true"
           							 data-options="validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=MARITAL_STATUS%>'" />
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 年龄： </td>
							<td >  <input class="easyui-validatebox" name="age" id="age" required="true" data-options="validType:'length[0,4]'" missingMessage="请输入年龄!"/></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 户籍： </td>
							<td >  <input class="easyui-validatebox" name="resReg" id="resReg" style="width:450px" required="true" data-options="validType:'length[0,20]'" missingMessage="请输入户籍!"/></td>
						</tr>
						<tr>
							<td class="label_right"></td>
							<td ><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.saveBasicData()">保存</a></td>
						</tr>
					</table>	
					<!-- 客户ID隐藏域 -->
					<input type="hidden" name="pid" id="curId" value="${borrow.customerId}">
				</form>
			</div>
		</div>
		<div class="pt10"></div>
		<!-- 项目信息 -->
		<div id="baseInfo" class="easyui-panel" title="项目信息">			
			<div style="padding: 5px">
				<form action="" method="post" id="projectForm" name="projectForm">
					<table class="formTable">
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款用途： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="borrowUse" id="borrowUse" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入借款用途!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 还款来源： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="payment" id="payment" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" /></td>
						</tr>
						<tr>
							 <c:if test="${borrow.borrowType == 1 }">
								<td class="label_right"> <font color="red">*</font>
							 		抵押物信息:
								</td>
								<td >
									<input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="hostageInfo" id="hostageInfo" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" />
								</td>
							 </c:if>
							 <c:if test="${borrow.borrowType == 2 }">
							 	<td class="label_right"> <font color="red">*</font>
							 		房产信息信息:
								</td>
								<td >
									<input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="houseInfo" id="houseInfo" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" />
								</td>
							 </c:if>
						</tr>
						<c:if test="${borrow.borrowType == 1 }">
							<tr>
								<td class="label_right"> <font color="red">*</font> 抵押物价值： </td>
								<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="hostageValuable" id="hostageValuable" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" /></td>
							</tr>
						</c:if>
						<tr>
							<td class="label_right"></td>
							<td ><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.saveLoadInfo('#projectForm')">保存</a></td>
						</tr>
					</table>	
					<!-- 客户ID隐藏域 -->
					<input type="hidden" name="pid" id="pid" value="${borrow.pid}">
				</form>
			</div>
		</div>
		<div class="pt10"></div>
		<!-- 风控信息 -->
		<div id="baseInfo" class="easyui-panel" title="风控信息">			
			<div style="padding: 5px">
				<form action="" method="post" id="riskControlForm" name="riskControlForm">
					<table class="formTable">
						<tr>
							<td class="label_right"> <font color="red">*</font>担保机构： </td>
							<td > 
								<input id="guaOrgId" name="guaOrgId" class="easyui-combobox" panelHeight="auto" missingMessage="请选择婚姻状况!" required="true"
           							 data-options="validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=GUA_ORG%>'" />
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 担保承诺： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="guaAcc" id="guaAcc" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 风控综述： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="riskDesc" id="riskDesc" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" /></td>
						</tr>
						<tr>
							<td class="label_right"></td>
							<td ><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.saveLoadInfo('#riskControlForm')">保存</a></td>
						</tr>
					</table>	
					<!-- 客户ID隐藏域 -->
					<input type="hidden" name="pid" value="${borrow.pid}">
				</form>
			</div>
		</div>
		<div class="pt10"></div>
		<!-- 用户材料 -->
		<div id="fileInfo" class="easyui-panel" title="用户材料">				
			<div class="p10">
				<form action="" method="post" id="fileInfoForm" name="fileInfoForm">
					<div class="easyui-panel p10" title="">
						<ul id="fileDiv_7" class="filePreview">
						<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(7)">单击上传资料</a></li>
						</ul>
					</div>
				</form>
			</div>
		</div>	
		
		<div class="pt10"></div>
		
		<!-- 文件上传 -->
		<div id="uploadFile" class ="easyui-dialog" title="文件上传" modal="true" closed="true" style="width:500px;height:180px">
			<form action="${basePath}bizBorrowController/uploadFile.shtml" id="fileUploadForm" method="post" enctype="multipart/form-data">
				<table  width="100%" height="120">				
					<tr>
						<td align="right"><font color="red">*</font>选择文件：</td>
						<td>
							<input class="text_style" required="true" missingMessage="请选择文件!" type="text" id="txt2" name="txt2" style="width:300px;"/>
					        <input style="width:70px;" type="file" name="borrowFile" id="borrowFile" onchange="txt2.value=this.value"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" >
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.uploadFile()">上传</a>
						</td>
					</tr>
				</table>
				<input type="hidden" id="borrowId" name="borrowId" value="${borrow.pid}">
				<input type="hidden" id="borFileRelType" name="borFileRelType">
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			borrow.initBorrowReviewView();			
		})
	</script>
	
</body>