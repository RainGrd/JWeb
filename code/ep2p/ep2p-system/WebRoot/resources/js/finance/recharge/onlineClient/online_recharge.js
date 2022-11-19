/**
 * 线上充值类
 */
var OnlineRecharge = {
	//加载线下充值列表数据
	initOnlineRechargeGrid:function(){
		$('#online_recharge_list_grid').datagrid({    
			url:basePath +"bizRechargeOnlineController/selectRechargeOnlinePageList.shtml",
			width:'100%',
			fit:true,
			toolbar:'#online_recharge_list_toolbar',
			pagination: true,
			rownumbers: true,
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:online_recharge_list_model,
		    onClickRow:function(rowIndex, rowData){
				$('#online_recharge_list_grid').datagrid('clearChecked');
				$('#online_recharge_list_grid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	queryOnlineRechageButClick:function(){
		var obj = jqueryUtil.serializeObject($("#online_recharge_list_form"));
		$('#online_recharge_list_grid').datagrid('load',obj);
	},
	//加载充值方式（充值渠道）下拉列表框数据
	initRechargeMode:function(){
		 $('#payConfigId').combobox({  
			url:basePath+'payConfigController/selectComboboxAll.shtml',
			valueField:'pid',    
			textField:'payName',
			loadFilter: function(res){
				return res.data;
			}
		 }); 
	},
	//补单
	replacementOrder:function(){
		var obj = jqueryUtil.serializeObject($("#replacement_order_form"));
		//$('#online_recharge_list_grid').datagrid('load',obj);
		$.post(BASE_PATH+"bizRechargeOnlineController/replacementOrder.shtml",obj, 
			function(ret) {
				if(ret.message.message == 200){
					OnlineRecharge.queryOnlineRechageButClick();
					$.messager.alert('操作提示',ret.data,'success',function(){
						$("#replacement_order_dialog").dialog('close');
					});
				}else{
					$.messager.alert('操作提示',ret.data,'error');
				}
			},'json'
		);
	},
	cleanData:function(){
		$('#online_recharge_list_form').form('reset');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#online_recharge_list_grid","online_recharge_list_form",
				basePath + "bizRechargeOnlineController/exportDownLoadFile.shtml");
	}
}
