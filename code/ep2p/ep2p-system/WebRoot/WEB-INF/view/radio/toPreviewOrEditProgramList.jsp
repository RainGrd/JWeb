<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/radio/programList.js"></script>
<script type="text/javascript" src="${basePath}resources/js/radio/model/programList_Model.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="batchToolbar">
			<input type="hidden" id="pid" name="pid" value="${pid}">
			<input type="hidden" id="flag" name="flag" value="${flag}">
			<form id="baseInfo" name="baseInfo" method="POST">
			<table class="formTable percent90">
			   <tr>
			   	  <td class="align_right">节目标题：</td>
					<td>
					 <c:if test="${flag ==1}">
					   <input name="programTitle" class="text_style" disabled="true"/>
					  </c:if>
					  <c:if test="${flag ==2}">
					   <input name="programTitle" class="text_style"/>
					  </c:if>
					</td>
					<td class="align_right" style="width: 170px;">节目板块：</td>
					<td>
					<c:if test="${flag ==1}">
						<select name="programPlateId" disabled="true"  id=""
									  class="easyui-combobox">
								<option value="0">--全部--</option>
								<option value="1">专家讲座</option>
								<option value="2">百姓理财</option>
								<option value="3">金融知识</option>
						</select> 
					</c:if>	
					<c:if test="${flag ==2}">
						<select name="programPlateId" id=""
									  class="easyui-combobox">
								<option value="0">--全部--</option>
								<option value="1">专家讲座</option>
								<option value="2">百姓理财</option>
								<option value="3">金融知识</option>
						</select> 
					</c:if>	
					</td>
					
			   </tr>
			   <tr>
			        <td class="align_right" style="width: 130px;">配图：</td>
					<td width="260">
					 <c:if test="${flag ==1}">
						<input name="programType" class="text_style" disabled="true"/>
					 </c:if>
					 <c:if test="${flag ==2}">
						  <input class="text_style left" required="true"  style="width: 173px;"
							missingMessage="请选择文件!" type="text"  name="programType" id="txt2" 
							value=""/>
							 <input style="width:70px;" class="left"
							type="file" name="borrowFile" id="borrowFile"
							onchange="txt2.value=this.value" />
							<div class="clear"></div>
						</div>
					 </c:if>
					</td>
					<td class="align_right">节目：</td>
					<td width="260">
						<c:if test="${flag ==1}">
						   <input name="pictureFileId" class="text_style" disabled="true"/>
						</c:if>  
						<c:if test="${flag ==2}">
							<input class="text_style left" required="true"  style="width: 173px;"
							missingMessage="请选择文件!" name="pictureFileId" type="text" id="txt3" 
							value=""/>
							<input style="width:70px;"
							type="file" name="borrowFile" class="left" id="borrowFile"
							onchange="txt3.value=this.value" />
								<div class="clear"></div>
							</td>
			  			  
						</c:if>   
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >上传时间：</td>
					<td>
						<c:if test="${flag ==1}">
							<input name="uploadTime" class="easyui-datetimebox" disabled="disabled"/>
						</c:if>  
						<c:if test="${flag ==2}">
							<input name="uploadTime" class="easyui-datetimebox" disabled="disabled"/>
						</c:if> 
					</td>
					<td class="align_right " >发布时间：</td>
					<td>
						<c:if test="${flag ==1}">
							<input name="publishTime" class="easyui-datetimebox" disabled="true"/>
						</c:if>
						<c:if test="${flag ==2}">
							<input name="publishTime" class="easyui-datetimebox" disabled="true"/>
						</c:if>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >获赞数：</td>
					<td>
						<input name="praiseNum" class="text_style" disabled="true"/>
					</td>
					<td class="align_right ">收听数：</td>
					<td>
						<input name="listenNum" class="text_style" disabled="true"/>
					</td>
			   </tr>
			   <tr>
					<td class="align_right " >下架时间：</td>
					<td>
					<c:if test="${flag ==1}">
						<input name="eliminateTime" class="text_style" disabled="true"/>
					</c:if>	
					<c:if test="${flag ==2}">
						<input name="eliminateTime" class="text_style" disabled="true"/>
					</c:if>
					</td>
					<td class="align_right " >状态：</td>
					<td>
					<c:if test="${flag ==1}">
						<select name="status" disabled="true" id="status"
									  class="easyui-combobox">
								<option value="0">--全部--</option>
								<option value="1">已发布</option>
								<option value="2">预发布</option>
								<option value="3">未发布</option>
								<option value="4">已下架</option>
						</select> 
					 </c:if>
					 <c:if test="${flag ==2}">
						<select name="status" disabled="true" id="status"
									  class="easyui-combobox">
								<option value="0">--全部--</option>
								<option value="1">已发布</option>
								<option value="2">预发布</option>
								<option value="3">未发布</option>
								<option value="4">已下架</option>
						</select> 
					 </c:if>	
					</td>
			   </tr>
			</table>	
		</form>
		</div>
	</div> 
<script type="text/javascript">
	$(document).ready(function(){
		var pid =$("#pid").val();
		if("" != pid){
			$.ajax({
				type: "POST",
		    	url : basePath + 'radioController/previewOrEditProgramById.shtml',
		    	data:{data:"{pid:"+pid+"}"},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		$("#baseInfo").form('load',data.result);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		}
	});
</script>
	
</body>