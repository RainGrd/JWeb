/**
 * 协议管理模板及内容
 */
var agreementMContext = {
	//协议内容管理
	initAgreementMContextDatagrid:function(){
		var vagrConTemId = $("#agrConTemId").val();
		$('#agreementMContext_datagrid_list').datagrid({    
			url:basePath + "agreementContextController/getAgreementContextMList.shtml?agrConTemId=" + vagrConTemId,
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    toolbar:'#agreementMContext_list_toolbar',
		    columns:agreementMContextListModel
		});
	},
	//搜索协议内容
	serachAgreementContext:function(){
		var vagrContTempName = $("#agrContName").val();
		var vprotocol = $("#protocol").val();
		$('#agreementMContext_datagrid_list').datagrid('load',{
			agrContName:vagrContTempName,
			protocol:vprotocol
		}); 
	},
	//跳转新增协议内容页面
	toAgreementContextAddPage:function(){
		var fidv = $("#agrConTemId").val();
		$("<div id='agreementContext_add' /> ").dialog({
			href:basePath + "agreementContextController/toAgreementMContextAdd.shtml?fid=" + fidv,
			title:"新增协议内容",
			method:'post',
			maximized:false,
			width:550,
			height:150,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					agreementMContext.saveAgreementContext();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#agreementContext_add").dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//跳转编辑协议内容页面
	toAgreementContextEidtPage:function(pid){
		var pidv = pid;
		$("<div id='agreementContext_eidt' /> ").dialog({
			href:basePath + "agreementContextController/toAgreementMContextEidt.shtml?pid=" + pidv,
			title:"新增协议内容",
			method:'post',
			maximized:false,
			width:550,
			height:150,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					agreementMContext.eidtAgreementContext();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#agreementContext_eidt").dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//保存协议内容模板信息
	saveAgreementContext:function(){
		$('#agreementMC_add_from').form('submit', {
			url: basePath + "agreementContextController/saveAgreementContext.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			ajax:true,
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#agreementMContext_datagrid_list').datagrid('reload'); 
		    		$("#agreementContext_add").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	//保存协议内容模板信息
	eidtAgreementContext:function(){
		$('#agreementMC_edit_from').form('submit', {
			url: basePath + "agreementContextController/eidtAgreementContext.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			ajax:true,
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#agreementMContext_datagrid_list').datagrid('reload'); 
		    		$("#agreementContext_eidt").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	//启用或禁用内容模板信息 1.启用  2.禁用
	activAndDisAgreementContext:function(fstat){
		var vagrConTemId = $("#agrConTemId").val();
		var checkList = $("#agreementMContext_datagrid_list").datagrid("getSelections");
		if(checkList.length <= 0){
			$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
		}else{
			//启用数据
			if(fstat == 1){
				$.messager.confirm('确认','您确认想要启用记录吗？',function(r){    
				    if (r){  
				    	$.post(basePath + "agreementContextController/updateBatchAgreementContext.shtml", {
				    		upids : objToArrayPid(checkList).join(","),
				    		fpid:vagrConTemId,
				    		status:'1'
						}, function(data) {
							if(data.code == "200" ){
					    		$.messager.alert('操作提示',data.message,'success');
					    		$('#agreementMContext_datagrid_list').datagrid('reload'); 
					    	}else{
					    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
					    	}
						});
				    }
				});
			}
			
			//禁用数据
			if(fstat == 2){
				$.messager.confirm('确认','您确认想要禁用记录吗？',function(r){    
				    if (r){  
				    	$.post(basePath + "agreementContextController/updateBatchAgreementContext.shtml", {
				    		upids : objToArrayPid(checkList).join(","),
				    		fpid:vagrConTemId,
							status:'2'
						}, function(data) {
							if(data.code == "200" ){
					    		$.messager.alert('操作提示',data.message,'success');
					    		$('#agreementMContext_datagrid_list').datagrid('reload'); 
					    	}else{
					    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
					    	}
						});
				    }
				});
			}
		}
	}
	
	
};

//初始化信息
$(function(){
	agreementMContext.initAgreementMContextDatagrid();
});