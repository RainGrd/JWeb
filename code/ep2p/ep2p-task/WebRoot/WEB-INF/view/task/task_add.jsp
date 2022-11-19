<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/dictionary/dictionary.js" charset="utf-8"></script>
<body class="easyui-layout">
<form action="<%=basePath%>sysDistionaryController/create.shtml" id="taskAdd" name="taskAdd" method="post" >
	<div class="formWrap">
		<input type="hidden" id="pid" name="pid" value="${pid}">
		<table >
			<tr>
				<td class="label_right"><font color="red">*</font>任务名称：</td>
				<td> <input class="easyui-validatebox text_style" id="jobName1" name="jobName" required="true"  data-options="validType:'length[0,30]'" missingMessage="请输入任务名称!"  /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>任务组：</td>
				<td> <input class="easyui-validatebox text_style" id="jobGroup1" name="jobGroup" required="true" data-options="validType:'length[0,30]'" missingMessage="请输入任务组!"    /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>触发器：</td>
				<td> <input class="easyui-validatebox text_style" id="className1" name="className" required="true" data-options="validType:'length[0,100]'" missingMessage="请输入触发器!"    /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>定时表达式：</td>
				<td> <input class="easyui-validatebox text_style" id="cronExpression" name="cronExpression" required="true" data-options="validType:'length[0,30]'" missingMessage="请输入定时表达式!"    /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>任务描述：</td>
				<td> <input class="easyui-validatebox text_style" id="jobDesc" name="jobDesc" required="true" data-options="validType:'length[0,100]'" missingMessage="请输入数据类型名称!"    /> </td>
			</tr>
		</table>
	</div>
</form>

<script type="text/javascript" >
$(function(){
	var pid = $("#pid").val();
	if(pid != "" && pid != null){
		$.ajax({
			type: "POST",
	        async:false, 
	    	url : BASE_PATH+"scheduleJobController/selectByPid.shtml?pid="+pid,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status == 200 ){
		    		$("#taskAdd").form('load',data.result);
		    		debugger;
		    		$("#jobName1").attr('disabled',"disabled");
		    		$("#jobGroup1").attr('disabled',"disabled");
		    		$("#className1").attr('disabled',"disabled");
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
		    	
			}
		}); 
	}
})
</script>

</body>