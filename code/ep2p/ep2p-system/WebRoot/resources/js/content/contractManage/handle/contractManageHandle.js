/**
 * 合同管理列表js脚本信息
 */
var contractManageList = 
{
	//初始化列表信息数据
	initContractMdatagrid:function(){
		$('#contractM_datagrid_list').datagrid({    
			url:basePath + 'contractManageController/getContractMList.shtml',
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    toolbar:'#contractM_list_toolbar',
		    columns:contractMListModel
		});
	},
	//搜索合同信息数据
	serachContract:function(){
		var vborAgrAnme = $("#borAgrAnme").val();
		var vstatus = $("#status").combo("getValue");
		var vcreateUser = $("#createUser").val();
		var vstartCTime = $("#startCTime").datebox("getValue") != '' ? $("#startCTime").datebox("getValue") + " 00:00:00" : "";
		var vendCtime = $("#endCtime").datebox("getValue") != '' ? $("#endCtime").datebox("getValue") + " 23:59:59" : "";
		var vlastUpdateUser = $("#lastUpdateUser").val();
		var vstartLupTime = $("#startLupTime").datebox("getValue") != '' ? $("#startLupTime").datebox("getValue") + " 00:00:00" : "";
		var vendLuptime = $("#endLuptime").datebox("getValue") != '' ? $("#endLuptime").datebox("getValue") + " 23:59:59" : "";
		$('#contractM_datagrid_list').datagrid('load',{
			borAgrAnme:vborAgrAnme,
			status:vstatus,
			createUser:vcreateUser,
			startCTime:vstartCTime,
			endCtime:vendCtime,
			lastUpdateUser:vlastUpdateUser,
			startLupTime:vstartLupTime,
			endLuptime:vendLuptime
		}); 
	},
	//跳转到协议管理新增页面
	toContractAddPage:function(){
		$("<div id='contract_Add' /> ").dialog({
			href:basePath + "contractManageController/toContractAddPage.shtml",
			title:"新增协议",
			method:'post',
			maximized:false,
			width:460,
			height:300,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					contractManageList.saveContractInfo();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#contract_Add").dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//保存合同信息
	saveContractInfo:function(){
		$('#contract_add_from').form('submit', {
			url: basePath + "contractManageController/saveContractInfo.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			ajax:false,
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#contractM_datagrid_list').datagrid('reload'); 
		    		$("#contract_Add").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	//跳转到修改页面
	toContractEidtPage:function(pid){
		$("<div id='contract_Eidt' /> ").dialog({
			href:basePath + "contractManageController/toContractEidtPage.shtml?pid=" + pid,
			title:"新增协议",
			method:'post',
			maximized:false,
			width:460,
			height:300,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					contractManageList.eidtContractInfo();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#contract_Eidt").dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//编辑合同信息
	eidtContractInfo:function(){
		$('#contract_eidt_from').form('submit', {
			url: basePath + "contractManageController/updateContractInfo.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			ajax:false,
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#contractM_datagrid_list').datagrid('reload'); 
		    		$("#contract_Eidt").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	//启用或禁用内容模板信息 1.启用  2.禁用
	activAndDisContractM:function(fstat){
		var checkList = $("#contractM_datagrid_list").datagrid("getSelections");
		if(checkList.length <= 0){
			$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
		}else{
			//启用数据
			if(fstat == 1){
				$.messager.confirm('确认','您确认想要启用记录吗？',function(r){    
				    if (r){  
				    	$.post(basePath + "contractManageController/updateContractStatus.shtml", {
				    		pids : objToArrayPid(checkList).join(","),
				    		status:'1'
						}, function(data) {
							if(data.code == "200" ){
					    		$.messager.alert('操作提示',data.message,'success');
					    		$('#contractM_datagrid_list').datagrid('reload'); 
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
				    	$.post(basePath + "contractManageController/updateContractStatus.shtml", {
							pids : objToArrayPid(checkList).join(","),
							status:'2'
						}, function(data) {
							if(data.code == "200" ){
					    		$.messager.alert('操作提示',data.message,'success');
					    		$('#contractM_datagrid_list').datagrid('reload'); 
					    	}else{
					    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
					    	}
						});
				    }
				});
			}
		}
	},
	//下载协议源文件或者预览下载文件
	downloadContractFile:function(pid,status){
		window.location.href=basePath + "contractManageController/downloadContractFile.shtml?pid="+pid+"&status=" + status;
	},
	//修改上传的标签
	updateUpfileTag:function(){
		var html_tag = '<input class="easyui-filebox" name="file" id="file" required="required" validType="doctypevalid" data-options="buttonText:\'选择文件\'" style="width:100%">';
		/*
		html_tag = '<input class="text_style" required="true" missingMessage="请选择文件!" type="text" id="txt2" name="txt2" />';
		html_tag = html_tag + '<input style="width:70px;" type="file" name="file" id="borrowFile" onchange="txt2.value=this.value"/>';
		*/
		$("#upfile_tag").html(html_tag);
		$('#upfile_tag #file').filebox({});
		
	},
	// 打开文件上传DIV
	openFileUploadDiv:function(pid){
		$("#fileUploadForm #uppid").val(pid);
		$("#uploadFile").dialog("open");
	},
	// 文件上传
	uploadContractFile:function(){
		$("#fileUploadForm").form('submit', {
			url:basePath+'contractManageController/updateAgainContractFile.shtml',
			onSubmit : function() {
				var bool = $(this).form('validate');
				if(bool){
					var valfile = $("#fileUploadForm #file").val();
					var extype = valfile.slice(valfile.lastIndexOf(".")+1).toLowerCase();
					if ("doc" == extype || "docx" == extype ){   
	        			return true;
	        		}else{
	        			return false;
	        		}
				}else{
					return false;
				}
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#contractM_datagrid_list').datagrid('reload'); 
		    		$("#uploadFile").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	}
};

//进行datagrid加载初始化
$(function(){
	contractManageList.initContractMdatagrid();
});