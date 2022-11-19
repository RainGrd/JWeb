/**
 * 协议管理模板及内容
 */
var agreementManageTemp = {
	//协议模板管理
	initAgreementManageDatagrid:function(){
		$('#agreementM_datagrid_list').datagrid({    
			url:basePath + "agreementTempManageController/getAgreementTempMList.shtml",
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    toolbar:'#agreementM_list_toolbar',
		    columns:agreementTempManListModel
		});
	},
	//点击搜索信息内容模板
	serachAgreementTemp:function(){
		var vagrContTempName = $("#agrContTempName").val();
		var vstatus = $("#status").combo("getValue");
		var vcreateUser = $("#createUser").val();
		var vstartCTime = $("#startCTime").datebox("getValue") != '' ? $("#startCTime").datebox("getValue") + " 00:00:00" : "";
		var vendCtime = $("#endCtime").datebox("getValue") != '' ? $("#endCtime").datebox("getValue") + " 23:59:59" : "";
		var vlastUpdateUser = $("#lastUpdateUser").val();
		var vstartLupTime = $("#startLupTime").datebox("getValue") != '' ? $("#startLupTime").datebox("getValue") + " 00:00:00" : "";
		var vendLuptime = $("#endLuptime").datebox("getValue") != '' ? $("#endLuptime").datebox("getValue") + " 23:59:59" : "";
		$('#agreementM_datagrid_list').datagrid('load',{
			agrContTempName:vagrContTempName,
			status:vstatus,
			createUser:vcreateUser,
			startCTime:vstartCTime,
			endCtime:vendCtime,
			lastUpdateUser:vlastUpdateUser,
			startLupTime:vstartLupTime,
			endLuptime:vendLuptime
		}); 
	},
	//跳转新增协议内容模板页面
	toAgreementMTempAddPage:function(){
		$("<div id='agreementCTemp_add' /> ").dialog({
			href:basePath + "agreementTempManageController/toAgreementTempMAdd.shtml",
			title:"新增协议内容模板",
			method:'post',
			maximized:false,
			width:400,
			height:160,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					agreementManageTemp.saveAgreementMTemp();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#agreementCTemp_add").dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//跳转新增协议内容模板页面
	toAgreementMTempEidtPage:function(pid){
		$("<div id='agreementCTemp_eidt' /> ").dialog({
			href:basePath + "agreementTempManageController/toAgreementTempMEidt.shtml?pid="+pid,
			title:"新增协议内容模板",
			method:'post',
			maximized:false,
			width:400,
			height:160,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					agreementManageTemp.eidtAgreementMTemp();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#agreementCTemp_eidt").dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//保存协议内容模板信息
	saveAgreementMTemp:function(){
		$('#agreementCTemp_add_from').form('submit', {
			url: basePath + "agreementTempManageController/saveAgreementTemp.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			ajax:true,
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#agreementM_datagrid_list').datagrid('reload'); 
		    		$("#agreementCTemp_add").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	//保存协议内容模板信息
	eidtAgreementMTemp:function(){
		$('#agreementCTemp_eidt_from').form('submit', {
			url: basePath + "agreementTempManageController/eidtAgreementTemp.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			ajax:true,
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#agreementM_datagrid_list').datagrid('reload'); 
		    		$("#agreementCTemp_eidt").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	//启用或禁用内容模板信息 1.启用  2.禁用
	activAndDisAgreementMTemp:function(fstat){
		var checkList = $("#agreementM_datagrid_list").datagrid("getSelections");
		if(checkList.length <= 0){
			$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
		}else{
			//启用数据
			if(fstat == 1){
				$.messager.confirm('确认','您确认想要启用记录吗？',function(r){    
				    if (r){  
				    	$.post(basePath + "agreementTempManageController/updateAgreementTemp.shtml", {
				    		pids : objToArrayPid(checkList).join(","),
				    		status:'1'
						}, function(data) {
							if(data.code == "200" ){
					    		$.messager.alert('操作提示',data.message,'success');
					    		$('#agreementM_datagrid_list').datagrid('reload'); 
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
				    	$.post(basePath + "agreementTempManageController/updateAgreementTemp.shtml", {
							pids : objToArrayPid(checkList).join(","),
							status:'2'
						}, function(data) {
							if(data.code == "200" ){
					    		$.messager.alert('操作提示',data.message,'success');
					    		$('#agreementM_datagrid_list').datagrid('reload'); 
					    	}else{
					    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
					    	}
						});
				    }
				});
			}
		}
	},
	//点击协议内容管理中的编辑操作
	eidtAgreementTempAlement:function(atpid,atpname){
		var urlpath = basePath + "agreementContextController/toAgreeContPage.shtml?ppid=" + atpid;
		childLayout_addTab(urlpath,atpname);
	}
};

//初始化信息
$(function(){
	agreementManageTemp.initAgreementManageDatagrid();
});