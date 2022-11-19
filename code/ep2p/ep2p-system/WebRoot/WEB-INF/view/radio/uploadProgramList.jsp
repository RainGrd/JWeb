<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/radio/uploadProgramList.js"></script>
	<div data-options="region:'center',border:false">
		<form action=".shtml" method="post" id="uploadFile" enctype="multipart/form-data"
			name="baseInfo">
			<div style="padding: 5px">
				<table class="userTable percent100 formTable">
				  <tr>
					<td class="align_right">节目板块：</td>
					<td>
				        <select name="programPlateId" editable="false" id="programPlateId"
							  class="easyui-combobox">
						<option  value="1">专家讲座</option>
						<option  value="2">百姓理财</option>
						<option  value="3">金融知识</option>
						</select> 
					</td>
				  </tr>
				  <tr>
					<td class="align_right"><span class="cus_red">*</span>节目标题：</td>
					<td><input class="easyui-textbox" data-options="required:true" id="programTitle" name="programTitle"/> </td>
				</tr>
				<tr>
					<td class="align_right">选择配图：</td>
					<td><input class="text_style" required="true"  style="width: 200px;"
							missingMessage="请选择文件!" type="text"  name="programType" id="txt2" 
							value=""/>
							 <input style="width:70px;" type="file" name="borrowFile" id="borrowFile" onchange="txt2.value=this.value" /></td>
				</tr>
				  <tr>
			  			<td class="align_right">选择节目：</td>
							<td>
							<input class="text_style" required="true"  style="width: 200px;" missingMessage="请选择文件!" name="pictureFileId" type="text" id="txt3"  value=""/>
							<input style="width:70px;" type="file" name="borrowFile1" id="borrowFile1" onchange="txt3.value=this.value" /></td>
			  			</td>    
			 	 </tr>
			 	 <tr>
		  			<td class="align_right">节目配文：</td>
						<td><input class="easyui-textbox" id="program_text" name="programText" /></td>
		  			</td>    
			 	 </tr>
			</table>
		</div>
		</form>
	</div>
</body>