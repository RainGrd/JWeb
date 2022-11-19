<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="height:100%">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
				<form action="" method="post" id="searcm" name="searcm">
					<div class="pt5"> 
						<table class="beforeloanTable formTable"  width="90%">
							<tr>
								<td class="label_right"> 任务名称：</td>
								<td><input class="easyui-validatebox" name="jobName" id="jobName" /></td>
								<td class="label_right"> 任务组：</td>
								<td><input class="easyui-validatebox" name="jobGroup" id="jobGroup" /></td>
							</tr>
							<tr>
								<td class="label_right"></td>
								<td></td>
								<td class="label_right"></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="task.searchData()">查询</a>&nbsp;&nbsp;&nbsp;
	           						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="javascript:$('#searcm').form('reset');">重置</a> 
								</td>
							</tr>
						</table>	
					</div>
				</form>
				
			<!-- 操作按钮 -->	
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="task.openEditWin('')">新增</a>
		</div>
	
		<div class="loanExtensionListDiv" style="height:100%">
			<table id="grid" title="定时任务列表" class="easyui-datagrid"
				style="height: auto; width: auto;"
				data-options="
			     url:'${basePath}scheduleJobController/selectAllPage.shtml',
		         method: 'POST',
			     toolbar:'#toolbar',
				 pagination: true,
				 rownumbers:true,
				 sortOrder:'asc',
				 fitColumns: true,
				 onLoadSuccess:function(data){
						clearSelectRows('#grid');
				 }
			    ">
				<!-- 表头 -->
				<thead>
					<tr>
					  <th data-options="field:'pid',checkbox:true"></th> 
						<th	data-options="field:'jobName',width:120">任务名称</th>
						<th data-options="field:'jobGroup',width:100">任务组</th>
						<th data-options="field:'className',width:200">触发器</th>
						<th data-options="field:'jobStatusName',width:50">任务状态</th>
						<th data-options="field:'cronExpression',width:100">定时表达式</th>
						<th data-options="field:'jobDesc',width:100">任务描述</th>
						<th data-options="field:'createUser',width:100">创建人</th>
						<th data-options="field:'createTime',width:100">创建时间</th>
						<th data-options="field:'lastUpdateUser',width:100">最后更新人</th>
						<th data-options="field:'lastUpdateTime',width:100">最后更新时间</th>
						<th data-options="field:'yy',width:200,formatter:task.formatOperation">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<script >
	var task = {
		// 操作格式化
		formatOperation:function(value,row,index){
			
			// 处理中 提供查看日志操作
			if(row.jobStatus == 2){
				return '<a href="#" onclick="task.searchLog(\''+row.pid+'\',\''+row.jobName+'\',\''+row.jobGroup+'\')">查看日志</a> ';
			}
			
			// 待处理、完成、异常 提供所有操作
			if(row.jobStatus == 1 || row.jobStatus == 4 || row.jobStatus == 5){
				return '<a href="#" onclick="task.pauseJob(\''+row.pid+'\')">暂停</a> | <a href="#" onclick="task.openEditWin(\''+row.pid+'\')">编辑</a> | <a href="#" onclick="task.triggerJob(\''+row.pid+'\')">立即运行一次</a> | <a href="#" onclick="task.deleteJob(\''+row.pid+'\')">删除</a>  | <a href="#" onclick="task.searchLog(\''+row.pid+'\',\''+row.jobName+'\',\''+row.jobGroup+'\')">查看日志</a> ';
			}
			
			// 暂停任务 提供恢复操作
			if(row.jobStatus == 3){
				return '<a href="#" onclick="task.resumeJob(\''+row.pid+'\')">恢复</a> | <a href="#" onclick="task.openEditWin(\''+row.pid+'\')">编辑</a> | <a href="#" onclick="task.deleteJob(\''+row.pid+'\')">删除</a>  | <a href="#" onclick="task.searchLog(\''+row.pid+'\',\''+row.jobName+'\',\''+row.jobGroup+'\')">查看日志</a> ';
			}
			
		},
		validate:function(obj){
			var result = true;
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"scheduleJobController/selectByJobName.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		result = data.result;
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			}); 
			return result;
		},
		// 暂停任务
		pauseJob:function(pid){
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"scheduleJobController/pauseJob.shtml?pid="+pid,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"操作成功！",'successs');
			    		$('#grid').datagrid('reload'); 
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			});
		},
		// 恢复任务
		resumeJob:function(pid){
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"scheduleJobController/resumeJob.shtml?pid="+pid,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"操作成功！",'successs');
			    		$('#grid').datagrid('reload'); 
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			});
		},
		// 立即运行
		triggerJob:function(pid){
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"scheduleJobController/triggerJob.shtml?pid="+pid,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"操作成功！",'successs');
			    		$('#grid').datagrid('reload'); 
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			});
		},
		// 删除
		deleteJob:function(pid){
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"scheduleJobController/deleteJob.shtml?pid="+pid,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"操作成功！",'successs');
			    		$('#grid').datagrid('reload'); 
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
				}
			});
		},
		// 保存
		save:function(){
			// 验证表单必填项
			if(!$("#taskAdd").form('validate')){
				return ;
			}
			
			// 验证任务名不能重复
			var obj = jqueryUtil.serializeObject($("#taskAdd"));
			if(!task.validate(obj)){
				$.messager.alert('操作提示',"任务名已经存在任务组中，请检查！",'info');
				return;
			}
			
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"scheduleJobController/save.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$('#grid').datagrid('reload'); 
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 打开新增，编辑窗口
		openEditWin:function(pid){
			var title  = "定时任务-新增";
			if(pid != "" && pid != null){
				var title  = "定时任务-编辑";
			}
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "scheduleJobController/toAdd.shtml?pid="+pid,
				title:title,
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:task.save
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#addDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 查看日志
		searchLog:function(pid,jobName,groupName){
			childLayout_addTab(basePath + "scheduleJobController/toSearchLog.shtml?jobid="+pid,"查看日志-"+groupName+"."+jobName);
			/* $("<div id='addDialog' /> ").dialog({
				href:basePath + "scheduleJobController/toSearchLog.shtml?jobid="+pid,
				title:"查看日志-"+groupName+"."+jobName,
				method:'post',
				width:'700',
				height:'600',
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#addDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			}); */
		},
		// 查询
		searchData:function(){
			$('#grid').datagrid('load',jqueryUtil.serializeObject($("#searcm")));
		}
	}
	</script>
</body>