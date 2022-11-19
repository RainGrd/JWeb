/**
 * 数据字典内容js类
 */
var distionaryContent = {
		isUpdate:"",
		dictId :"",
		toListParm :"",
		initDataGrid:function(){
			$('#distionaryContentGrid').datagrid({    
				url:BASE_PATH+'sysDistionaryContentController/selectAllPage.shtml?dictId='+distionaryContent.dictId,
				width:'100%',
				fit:true,
				title:'数据字典内容列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:dictionaryContentManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#distionaryContentGrid').datagrid('clearChecked');
					$('#distionaryContentGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 初始化加载数据
		init:function (){
			distionaryContent.isUpdate = $("#isUpdate").val();
			distionaryContent.dictId = $("#dictId").val().trim();
			distionaryContent.toListParm = "?dictId="+distionaryContent.dictId+"&isUpdate="+distionaryContent.isUpdate;
		},
		// 保存
		save:function(){
			// 验证表单必填项
			if(!$("#distionaryContentAdd").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#distionaryContentAdd"));
			
			//验证编码是否存在 
			if(!distionaryContent.validateContentCode(obj.dictContCode,obj.dictId,obj.pid)){
				return;
			}
			
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysDistionaryContentController/save.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$("#distionaryContentGrid").datagrid('reload');
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		validateContentCode:function(code,dictId,pid){
			var result = false;
			var parm = "";
			if(pid != ''){
				parm ="{dictContCode:'"+code+"',dictId:"+dictId+",pid:"+pid+"}";
			}else{
				parm ="{dictContCode:'"+code+"',dictId:"+dictId+"}";
			}
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysDistionaryContentController/validateContentCode.shtml",
		    	data:{"data":parm},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		if(data.result == 'suc'){
			    			result = true;
			    		}else{
			    			$.messager.alert('操作提示',"您输入的数据字典内容编码已存在,请检查！",'error');
			    		}
			    	}else{
			    		$.messager.alert('操作提示',"验证数据字典内容编码失败,请联系管理员！",'error');
			    	}
				}
			}); 
			return result;
		},
		// 初始化数据
		loadData:function(){
			var pid = $("#pid").val();
			if("" != pid){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"sysDistionaryContentController/getByPid.shtml",
			    	data:{data:"{pid:"+pid+"}"},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		$("#distionaryContentAdd").form('load',data.result);
				    		//var test = $("input[name=dictContCode]").parent().prev().attr("readonly","readonly");
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		},
		// 跳转到新增页面
		toAdd:function(){
			$("<div id='addDialog' /> ").dialog({
				href:BASE_PATH+"sysDistionaryContentController/toAdd.shtml"+distionaryContent.toListParm,
				title:"数据字典内容-新增",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:distionaryContent.save
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
		toUpdate:function(pid){
			$("<div id='addDialog' /> ").dialog({
				href:BASE_PATH+"sysDistionaryContentController/toAdd.shtml"+distionaryContent.toListParm+"&pid="+pid,
				title:"数据字典内容-修改",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:distionaryContent.save
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
		// 启用 禁用
		updateStatus:function(status){
			var rows = $('#distionaryContentGrid').datagrid('getSelections');
		 	if ( rows.length == 0 ) {
		 		$.messager.alert("操作提示","请选择数据","error");
				return;
			}
		 	// 获取选中pid 
		 	var pId = "";
			for(var i=0;i<rows.length;i++){
		  		if(i==0){
		  			pId+=rows[i].pid;
		  		}else{
		  			pId+=","+rows[i].pid;
		  		}
		  	}
			var message = "确定禁用选择项?";
			if(status == 1){
				message = "确定启用选择项?";
			}
			$.messager.confirm("操作提示",message,
				function(r) {
		 			if(r){
						$.post(BASE_PATH+"sysDistionaryContentController/udpateStatus.shtml",{pid:pId,status:status}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"操作成功！",'success');
									$("#distionaryContentGrid").datagrid('reload');
								}else{
									$.messager.alert('操作提示',"操作失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
		}
};

/**
 * 页面数据初始化
 */
$(function(){
	distionaryContent.init();
	distionaryContent.initDataGrid();
});
