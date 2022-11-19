/**
 * 线下充值类
 */
var OfflineRecharge = {
	//加载线下充值首页列表
	initIndexGrid:function(){
		$('#offline_index_grid').datagrid({    
			url:basePath +"customerController/getCustomerSearchList.shtml",
			width:'100%',
			fit:true,
			toolbar:'#offline_index_toolbar',
			pagination: true,
			rownumbers: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:offline_index_client_model,
		    onClickRow:function(rowIndex, rowData){
				$('#offline_index_grid').datagrid('clearChecked');
				$('#offline_index_grid').datagrid('checkRow',rowIndex);
		    },
		    onLoadSuccess:function(data){
		    	if(data){
		    		if(data.rows){
		    			$('#offline_index_grid').datagrid('deleteRow',(data.rows.length-1));
		    		}
		    	}
		    	return data;
		    }
		});
	},
	//查询按钮click事件 （查询客户；列表）
	queryIndexGridButClick: function(){
		var obj = jqueryUtil.serializeObject($("#offline_index_form"));
		$('#offline_index_grid').datagrid('load',obj);
	},
	//加载线下充值列表数据
	initOfflineRechargeGrid:function(){
		$('#offline_recharge_list_grid').datagrid({    
			url:basePath +"bizRechargeOfflineController/selectRechargeOfflinePageList.shtml",
			width:'100%',
			fit:true,
			toolbar:'#offline_recharge_list_toolbar',
			pagination: true,
			rownumbers: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:offline_recharge_list_model,
		    onClickRow:function(rowIndex, rowData){
				$('#offline_recharge_list_grid').datagrid('clearChecked');
				$('#offline_recharge_list_grid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	selectReferendumTask:function(){
		$('#offline_recharge_list_grid').datagrid({    
			url:basePath +"bizRechargeOfflineController/selectRechargeOfflinePageList.shtml",
			width:'100%',
			fit:true,
			toolbar:'#offline_recharge_list_toolbar',
			pagination: true,
			rownumbers: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    queryParams:{recStatus:1},
		    columns:offline_recharge_list_model,
		    onClickRow:function(rowIndex, rowData){
				$('#offline_recharge_list_grid').datagrid('clearChecked');
				$('#offline_recharge_list_grid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	queryOfflineRechageButClick:function(){
		var obj = jqueryUtil.serializeObject($("#offline_recharge_list_form"));
		$('#offline_recharge_list_grid').datagrid('load',obj);
	},
	//保存充值
	rechargeSave:function(){
		//验证表单
		if(!$('#client_rechagre_form').form('validate')){
			return;
		}
		//客户ID
		var clientPid = $("form[id=client_details_form] input[id=clientPid]").val();
		var obj = jqueryUtil.serializeObject($("#client_rechagre_form"));
		obj["customerId"] = clientPid;
		if(!clientPid){
			$.messager.alert('操作提示',"关联客户出现异常请联系管理员！",'error');
		}
		$.post(BASE_PATH+"bizRechargeOfflineController/addRccountRecharge.shtml",obj, 
			function(ret) {
				if(ret.message.message == 200){
					OfflineRecharge.queryIndexGridButClick();
					$.messager.alert('操作提示',ret.data,'success',function(){
						$("#client_rechagre_dialog").dialog('close');
					});
				}else{
					$.messager.alert('操作提示',ret.data,'error');
				}
			},'json'
		);
	},
	//线下充值审核
	offlineRechargeCheck:function(){
		//验证表单
		if(!$('#client_rechagre_check_form').form('validate')){
			return;
		}
		//客户ID
		var clientPid = $("form[id=client_details_form] input[id=clientPid]").val();
		//序列化表单数据
		var obj = jqueryUtil.serializeObject($("#client_rechagre_check_form"));
		obj["customerId"] = clientPid;
		if(!clientPid){
			$.messager.alert('操作提示',"关联客户出现异常请联系管理员！",'error');
		}
		$.post(BASE_PATH+"bizRechargeOfflineController/addOfflineRechargeCheck.shtml",obj, 
			function(ret) {
				if(ret.message.message == 200){
					OfflineRecharge.queryOfflineRechageButClick();
					$.messager.alert('操作提示',ret.data,'success',function(){
						$("#client_rechagre_check_dialog").dialog('close');
					});
				}else{
					$.messager.alert('操作提示',ret.data,'error');
				}
			},'json'
		);
	},
	cleanData:function(){
		$('#offline_recharge_list_form').form('reset');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#offline_recharge_list_grid","offline_recharge_list_form",
				basePath + "bizRechargeOfflineController/exportDownLoadFile.shtml");
	}
	
}
