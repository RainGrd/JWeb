<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/business/borrow/borrow.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/cityArray.js" charset="utf-8"></script>
<body class="easyui-layout ">
	<div data-options="region:'center',border:false" class="borrowDetailPage">
		<c:if test="${borrow.approveStatus == 1}">
			<div data-options="region:'center',border:false" class="borrowDetailPage">
			<div id="borrowStateinfo" class="easyui-panel" title="申请状态">
				<div class="p10">
				<table>
					<tr>
						<td>
							<a class="overState borrowState">借款人资料</a>
						</td>
						<td>
							<div class="greyBar"></div>
						</td>
						<td>
							<a class="greyState borrowState">担保初审</a>
						</td>
						<td>
							<div class="greyBar"></div>
						</td>
						<td>
							<a class="greyState borrowState">贷前审核</a>
						</td>
						<td>
							<div class="greyBar greyBarLast"></div>
						</td>
						<td>
							<a class="greyState borrowState">发布</a>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="pt10"></div>
		</c:if>
		<c:if test="${borrow.approveStatus == 2}">
			<%@ include file="borrow_common.jsp"%>
		</c:if>
		<!-- 个人基本资料 -->
		<div id="baseInfo" class="easyui-panel" title="个人基本资料" <c:if test="${view == 'yes'}"> tools="#baseEditDiv"</c:if>	>	
			<c:if test="${isEdit == 'yes'}">
				<c:if test="${view == 'yes'}">
					<div id="baseEditDiv" class="right"><a href="javascript:void(0);" onclick="borrow.recoveryBasicDataForm('basicDataForm')">编辑</a></div>
				</c:if>		
			</c:if>
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
							<td class="label_right"> <font color="red">*</font> 手机号码： </td>
							<td >  <input class="easyui-textbox" name="phoneNo" id="phoneNo" required="true" data-options="validType:'length[0,13]'" missingMessage="请输入手机号码!"/></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 婚姻状况： </td>
							<td >  
								<input id="isMarriage" name="isMarriage" class="easyui-combobox" panelHeight="auto" missingMessage="请选择婚姻状况!" required="true"
           							 data-options="editable:false,validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=MARITAL_STATUS%>'" />
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 身份证号： </td>
							<td > 
								<input class="easyui-textbox" name="identificationNo" id="identificationNo" required="true" data-options="validType:'length[0,20]'" missingMessage="请输入身份证号!"/>
								<input type="hidden" name="age" id="age"/>
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 家庭地址： </td>
							<td >  <input class="easyui-textbox" name="homeAddress" id="homeAddress" style="width:450px" required="true" data-options="validType:'length[0,20]'" missingMessage="请输入家庭地址!"/></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 现居住地址省份： </td>
							<td >  <input class="easyui-combobox" name="nowProvince" id="nowProvince" data-options="valueField:'name',textField:'name',onSelect:borrow.selectNowProvince" required="true" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 现居住地址市区： </td>
							<td >  <input class="easyui-combobox" name="nowCity" id="nowCity" data-options="valueField:'name',textField:'name'"  required="true"  /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 现居住详细地址： </td>
							<td >  <input class="easyui-textbox" name="nowAddress" id="nowAddress" style="width:450px" required="true" data-options="validType:'length[0,20]'" missingMessage="请输入现居住地址!"/></td>
						</tr>
						<!-- 
						<tr>
							<td class="label_right"> <font color="red">*</font> 来深时间： </td>
							<td >  <input class="easyui-numberbox" name="cameSzTime" id="cameSzTime" style="width:100px" required="true" /><span class="pl9">年</span></td>
						</tr>
						 -->
						<tr>
							<td class="label_right"> 邮箱： </td>
							<td >  <input class="easyui-textbox" name="email" id="email" data-options="validType:'length[0,20]'" missingMessage="请输入直系亲属联系方式!"/></td>
						</tr>
						<tr>
							<td class="label_right"> 微信： </td>
							<td >  <input class="easyui-textbox" name="wechatNo" id="wechatNo" data-options="validType:'length[0,20]'" missingMessage="请输入直系亲属联系方式!"/></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 直系亲属： </td>
							<td >  <input class="easyui-textbox" name="immediateFamily" id="immediateFamily" required="true" data-options="validType:'length[0,100]'" missingMessage="请输入直系亲属联系方式!"/></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 联系方式： </td>
							<td >  <input class="easyui-textbox" name="immediateFamilyNo" id="immediateFamilyNo" style="width:450px"required="true" data-options="validType:'length[0,100]'" missingMessage="请输入直系亲属联系方式!" /></td>
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
		<!-- 资质材料 -->
		<div id="fileInfo" class="easyui-panel" title="资质材料"<c:if test="${view == 'yes'}"> tools="#fileEditDiv"</c:if>>				
			<c:if test="${isEdit == 'yes'}">
				<c:if test="${view == 'yes'}">
					<div id="fileEditDiv" class="right"><a href="javascript:void(0);" onclick="borrow.recoveryFileInfoForm('fileInfoForm')">编辑</a></div>
				</c:if>
			</c:if>	
			
			<div class="p10">				
				<form action="" method="post" id="fileInfoForm" name="fileInfoForm">
					<div class="easyui-panel p10" title="<span class='red'>*</span>身份证明+婚姻登记证明">
						<ul id="fileDiv_1" class="filePreview">
						<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(1)">单击上传资料</a></li>
						</ul>
					</div>
					<div class="pt10">
					<div class="easyui-panel p10" title="<span class='red'>*</span>个人征信报告  （图片JPG PNG GIF、PDF）">
						<ul id="fileDiv_2" class="filePreview">
							<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(2)">单击上传资料</a></li>
						</ul>
					</div>
					</div>
					
					<div class="pt10">
					<div class="easyui-panel p10" title="<span class='red'>*</span>收入证明 （图片JPG PNG GIF、PDF）">
						<ul id="fileDiv_3" class="filePreview">
							<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(3)">单击上传资料</a></li>
						</ul>
					</div>
					</div>
					
					<div class="pt10">
					<div class="easyui-panel p10" title="<span class='red'>*</span>近半年银行流水 （图片JPG PNG GIF、PDF）">
						<ul id="fileDiv_4" class="filePreview">
							<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(4)">单击上传资料</a></li>
						</ul>
					</div>
					</div>
					
					<div class="pt10">
					<div class="easyui-panel p10" title="<span class='red'>*</span>社保查询单（图片JPG PNG GIF、PDF）">
						<ul id="fileDiv_5" class="filePreview">
							<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(5)">单击上传资料</a></li>
						</ul>
					</div>
					</div>
					
					<!-- 抵押贷借款,展示房产证材料 -->
					<c:if test="${borrow.borrowType == 2}">
					<div class="pt10">
					<div class="easyui-panel p10" title="<span class='red'>*</span>房产证材料 （图片JPG PNG GIF、PDF）">
						<ul id="fileDiv_6" class="filePreview">
							<li><a href="javascript:void(0);" title="单击上传资料" class="addFileBtn" onclick="borrow.openFileUploadDiv(6)">单击上传资料</a></li>
						</ul>
					</div>
					</div>
					</c:if>
				</form>
			</div>
		</div>	
		
		<div class="pt10"></div>
		<!-- 借款信息 -->
		<div id="loanInfo" class="easyui-panel" title="借款信息"<c:if test="${view == 'yes'}"> tools="#loanEditDiv"</c:if>>
			<c:if test="${isEdit == 'yes'}">
				<c:if test="${view == 'yes'}">
					<div id="loanEditDiv" class="right"><a href="javascript:void(0)" onclick="borrow.recoveryLoanInfoForm('loanInfoForm')">编辑</a></div>
				</c:if>
			</c:if>
			<div class="p5">				
				<form action="" method="post" id="loanInfoForm" name="loanInfoForm">
					<table class="formTable">
					
					<!-- 抵押贷借款,展示楼盘信息 -->
					<c:if test="${borrow.borrowType == 2}">
						<tr>
							<td class="label_right"> <font color="red">*</font> 楼盘地址： </td>
							<td >
								<!-- 省 -->
								<input id="province" name="homesProvince" class="easyui-combobox" panelHeight="auto" required="true"
           							 data-options="editable:false,onSelect:borrow.selectProvince,loadFilter:common.dictionaryFilter,valueField:'homesProvince',textField:'homesProvince',multiple:false,url:'<%=basePath%>bizHousesController/selectDistinctProvince.shtml'" />
           						&nbsp;&nbsp;
           						<!-- 市 -->
           						<input id="city"  name="homesCity"  class="easyui-combobox" panelHeight="auto" required="true"
           							data-options="editable:false,onSelect:borrow.selectCity,loadFilter:common.dictionaryFilter,valueField:'homesCity',textField:'homesCity',multiple:false,method:'POST'"/>
           						&nbsp;&nbsp;
           						<!-- 区 -->
           						<input id="area"   name="homesArea" class="easyui-combobox" panelHeight="auto" required="true"
									data-options="editable:false,onSelect:borrow.selectArea,loadFilter:common.dictionaryFilter,valueField:'homesArea',textField:'homesArea',multiple:false,method:'POST'"/>
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 楼盘： </td>
							<td >
								<input id="homesName"  name="homesName" class="easyui-combobox" panelHeight="auto" 	required="true"
									data-options="editable:false,onSelect:borrow.selectHousesName,loadFilter:common.dictionaryFilter,valueField:'homesName',textField:'homesName',multiple:false,method:'POST'"/>
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 户型： </td>
							<td >
								<input id="homesType" name="homeId" class="easyui-combobox" panelHeight="auto" 	required="true"
									data-options="editable:false,loadFilter:common.dictionaryFilter,valueField:'pid',textField:'homesType',multiple:false,method:'POST'"/>
							</td>
						</tr>					
					</c:if>
					
					<!-- 首付贷借款,展示房产描述信息 -->
					<c:if test="${borrow.borrowType == 1}">
						<tr class="mortgageLoan">
							<td class="label_right"> <font color="red">*</font> 房产描述： </td>
							<td >  <input class="easyui-validatebox" name="homeDesc" id="homeDesc" style="width:450px;" required="true" data-options="validType:'length[0,100]'" missingMessage="请输入房产描述!" /></td>
						</tr>
					</c:if>
					
					<!-- 抵押贷借款,展示房产总价 -->
					<c:if test="${borrow.borrowType == 2}">
						<tr>
							<td class="label_right"> <font color="red">*</font> 房产总价： </td>
							<td >  <input class="easyui-numberbox" precision="2" groupSeparator="," name="homeTotal" id="homeTotal" min="0" max="999999999" style="width: 200px;" required="true" missingMessage="请输入房产总价!" /></td>
						</tr>
					</c:if>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款金额： </td>
							<td >  <input class="easyui-numberbox" precision="2" groupSeparator="," name="borrowMoney" id="borrowMoney" min="0" max="20000000" style="width: 200px;" required="true" missingMessage="请输入借款金额!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款期限： </td>
							<td >
								<input id="borDeadline" name="borDeadline" class="easyui-combobox" required="true" panelHeight="auto" missingMessage="请选择借款期限!"  style="width:100px"
           							 data-options="editable:false,validType:'checkSelectedValue',loadFilter:common.dictionaryFilter,valueField:'dictContCode',textField:'dictContName',multiple:false,url:'<%=BORROW_TIME%>'" />&nbsp; 月
							</td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 借款用途： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="borrowUse" id="borrowUse" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入借款用途!" /></td>
						</tr>
						<tr>
							<td class="label_right"> <font color="red">*</font> 还款来源： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="payment" id="payment" required="true" data-options="multiline:true,validType:'length[0,255]'" missingMessage="请输入还款来源!" /></td>
						</tr>
						<tr>
							<td class="label_right"> 其他： </td>
							<td >  <input class="easyui-textbox" style="width:65%;height:60px;min-width:450px;" name="other" id="other" data-options="multiline:true,validType:'length[0,500]'"/></td>
						</tr>
						<tr>
							<td class="label_right"></td>
							<td >
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.saveLoadInfo('#loanInfoForm');">保存</a>
								<c:if test="${view != 'yes'}">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="borrow.submitAudit();">提交担保初审</a>
								</c:if>
							</td>
						</tr>
					</table>
					
					<!-- 借款类型,借款ID隐藏域 -->
					<input type="hidden" name="borrowType" id="borrowType" value="${borrow.borrowType}"/>	
					<input type="hidden" name="pid" id="pid" value="${borrow.pid}"/>	
					<input type="hidden" name="approveId" id="approveId" value="${borrow.approveId}"/>	
					<input type="hidden" name="approveStatus" id="approveStatus" value="${borrow.approveStatus}"/>	
					<input type="hidden" name="surplusMoney" id="surplusMoney" />	
					<input type="hidden" name="view" id="view" value="${view}"/>	
				</form>
			</div>
		</div>
		
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
			borrow.initAddBorrow();			
		})
	</script>
	
</body>