/**
 * 编号生成规则内容js类
 */
var cusBirWarnHis = {
	//初始化
	initDataGrid:function(){
		$('#cusBirWarnHisGrid').datagrid({    
			url:basePath + 'authenVipCustomerController/selectCusBirWarnHisByCondition.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'VIP生日提醒',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:cusBirWarnHis_Model,
		    onLoadSuccess:function(data){
				clearSelectRows('#cusBirWarnHisGrid');
			}
		});
	},
	// 展示短信模版选择窗口
	openShowTemp:function(){
		// 判断是否选中数据
		var row = $("#cusBirWarnHisGrid").datagrid("getSelections");
		if(row.length == 1){
			// 只能对没有提醒的用户进行短信提醒操作
			if(row[0].isWran == 1){
				$.messager.alert("操作提示","只能对未提醒的用户进行提醒操作,请重新操作.");
				return;
			}
			// 打开需要展示的短信模版窗口
			$('#cusBirWarnHis_dlg').dialog('open').dialog('setTitle', "VIP生日提醒短信选择");
			// 重置表单数据
			$("#cbwh_searcm").form("reset");
			$("#cbwh_searcm input[name='customerId']").val(row[0].pid);
		}else if(row.length > 1){
			$.messager.alert("操作提示","每次只能对一个用户发送生日提醒");
		} else {
	 		$.messager.alert("操作提示","请选择数据");
		}
	},
	// 添加提醒信息
	saveCusBirWarnHis:function(){
		// 判断是否选中短信模板
		if( !$("#smsTempId").val() ){
			$.messager.alert("操作提示","请选择短信模板.");
			return;
		}
		var url = basePath +"authenVipCustomerController/saveCusBirWarnHis.shtml";
		// 提交form表单
		$("#cbwh_searcm").form('submit', {
			url : url,
			success : function(data) {
				var result = eval("("+data+")");
 				if(result.message == 200){
 					$.messager.alert('操作提示',"提醒成功");
 					$('#cusBirWarnHis_dlg').dialog('close');
 					$("#cusBirWarnHisGrid").datagrid("load");
 				}else{
 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
 				}
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#cusBirWarnHisGrid','#searcm');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#cusBirWarnHisGrid","#searcm",basePath + "authenVipCustomerController/exportDownLoadFile.shtml");
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	cusBirWarnHis.initDataGrid();
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#cusBirWarnHisGrid").datagrid( "load");
};