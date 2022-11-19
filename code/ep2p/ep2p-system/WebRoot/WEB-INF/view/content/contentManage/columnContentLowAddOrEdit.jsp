<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<style type="text/css"> 
</style>
<script type="text/javascript">
	$(function(){ 
		selectTag(); 
	})
</script> 
<script type="text/javaScript"
	src="${basePath}resources/js/content/contentManage/columnContent.js" charset="utf-8"></script>
<link href="../resources/plugins/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="../resources/plugins/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../resources/plugins/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="../resources/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="scroll-bar-div"> 
			<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<form action="addContColumn.shtml" method="post" id="selectCont"
					name="selectCont" enctype="multipart/form-data">
					<div style="padding: 5px">
						<table class="formTable"> 
							<tr>
								<td width="40%" align="right" height="25"><font
									color="red">*</font>标题名称：</td>
								<td><input class="easyui-textbox" name="titleName" id="titleName" value="${content.titleName}" /> 
									<input type="hidden" value="${content.pid}" name="pid" id="pid"/><!-- 栏目内容id 用于修改 -->
									<input type="hidden" value="${parentId}" name="parentId" id="parentId"/><!-- 顶级菜单id 用于返回页面 -->
									<input type="hidden" value="${tag}" id="tag"/>
								</td>
							</tr> 
							<tr>
								<td align="right" height="25">封面：</td>
								<td>
								<input class="easyui-textbox" style="width: 200px;"
									missingMessage="请选择文件!" class="easyui-textbox" id="txt3" 
									value="${content.fileUrlImg}"/> 
									 <input style="width: 70px;"
									type="file" name="borrowFileImg" id="borrowFileImg"
									onchange="changeFileImg(this)" />                                                                                                                                                                                                                                                                                                    
								 </td>
							</tr>
							<tr>
								<td align="right" height="25">上架时间：</td>
								<td>
									<input class="easyui-datebox" name="beginTime" id="beginTime" value="${content.beginTime}">
								</td>
							</tr> 
						    <tr>
								<td align="right" height="25" >下架时间：</td>
								<td>
									<input class="easyui-datebox" name="endTime" id="endTime" value="${content.endTime}">
								</td>
							</tr> 
							
							<tr>
								<td align="right" height="25"><font
									color="red">*</font>状态：</td>
								<td>
								<input type="hidden" id="hiddenStatus"  value="${content.status}"/>
								<input type="radio" name="status" id="activationStatus" value="1" />启用<input
									type="radio" name="status" id="disableStatus" value="2" />禁用</td>
							</tr>
							<tr style="display: none;">
								<td align="right" height="25"><font
									color="red">*</font>是否存在下级：</td>
								<td >   
									<input type="hidden" id="isLowerLevel" value="0"/>
									</td>
							</tr>
							<tr class="level">
								<td align="right" height="25">所属标签：</td>
								<td id="colTag">  
									</td>
							</tr>
							<tr class="level">
								<td align="right" height="25"><font
									color="red"></font>是否自定义文件：</td>
								<td>
<%-- 								<input type="hidden" id="hiddenStatus"  value="${content.status}"/> --%>
								<input type="hidden" id="isCustomFile" value="${content.isCustomFile }"/>
								<input type="radio" name="isCustomFile" id="notIsCustomFile" value="0"/>否<input
									type="radio" name="isCustomFile" id="isCustomFileShow" value="1" />是</td>
							</tr>
							<tr id="file" class="level">
								<td align="right" height="25"><font
									color="red">*</font>文件路径：</td>
								<td>
								<input class="easyui-textbox"  style="width: 200px;"
									missingMessage="请选择文件!" type="text" id="txt2" 
									value="${content.fileUrl}"/>
									<input type="hidden" name="fileId" value="${content.fileUrl}" />
									<input type="hidden" name="isChangeFile" id="isChangeFile" value="0" />
									 <input style="width: 70px;"
									type="file" name="borrowFile" id="borrowFile"
									onchange="changeFile(this)" />                                                                                                                                                                                                                                                                                                    
									
<%-- 								 <input type="text" name="url" id="url" value="${content.url}" /> <input type="file" value="浏览文件" /> --%>
								 </td>
							</tr>
							<tr id="conten" class="level">
								<td  align="right" height="25"><font
									color="red">*</font>内容：</td>
								<td> 
									 <script type="text/plain" id="myEditor" style="width:800px;height:240px;">${content.content } </script>
									 <input type="hidden" name="content" id="content" value="">
								 </td>
							</tr>
							<tr>
								<td></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="addContent()">保存</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-back" onclick="retPage()">返回</a>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){ 
	 
	var isCustomFile = $("#isCustomFile").val();
	 
	//没有文件上传
	if(isCustomFile == "0"){
		$("#notIsCustomFile").attr("checked","true");
		$("#txt2").val("");
		$("#fileId").val(""); 
		$("#file").hide();
	}else{
		$("#isCustomFileShow").attr("checked","true"); 
		$("#file").show();
	}
	//页面初始化  如果新增 默认状态为 激活，如果为修改回显原来的状态1
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
//文件上传按钮点击触发事件--改变文件上传标示  把路径上传框改变为选择的路径
function changeFile(obj){
	$("#txt2").val(obj.value);
	$("#isChangeFile").val(1);
}
//文件上传按钮点击触发事件--改变文件上传标示  把路径上传框改变为选择的路径
function changeFileImg(obj){
	$("#txt3").textbox("setValue",obj.value);
	$("#isChangeFile").val(1);
}
//实例化编辑器 
var um = UM.getEditor('myEditor');
</script> 



