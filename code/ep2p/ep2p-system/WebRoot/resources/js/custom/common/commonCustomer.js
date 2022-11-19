
// 短信模版需要加载的列表字段
var smsTemplates_model = [[
                {field:'pid',checkbox:true},
       	        {field:'tempCode',title:'短信编号',width:'25%',align:'center',sortable:true},    
       	        {field:'smsContent',title:'短信内容',width:'60%',align:'center',sortable:true}
            ]];

/**
 * 活动公共的JS类 
 */
var commonCustomer = {
		// 初始化
		initDataGrid:function(){
			$('#smsTemplatesGrid').datagrid({    
				url:basePath + 'sysSmsTemplatesController/smsTemplatesList.shtml',
				width:'100%',
				fit:true,
				/*title:'短信模板列表',*/
				toolbar:'#toolbarTemplates',
				pagination: true,
			    rownumbers:true,
			    singleSelect:true,
			    columns:smsTemplates_model,
			    onLoadSuccess:function(rowIndex, rowData){
			    	clearSelectRows('#smsTemplatesGrid');
			    }
			});
		},
		// 选择短信模版
		openSmsTemplates:function(){
			// 打开短信模版信息
			$('#smsTemplates').dialog('open').dialog('setTitle', "选择短信模版");
			// 初始化
			commonCustomer.initDataGrid();
			// 重置表单数据
			$("#searcmTemplates").form("reset");
		},
		// 查询短信模版信息
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#searcmTemplates"));
			var objStr = JSON.stringify(obj);
			$('#smsTemplatesGrid').datagrid('load',{data:objStr});
		},
		// 选择短信模版信息
		saveSmsTemplates:function(){
			// 获取选中的数据行
			var row = $("#smsTemplatesGrid").datagrid("getSelected");
			if ( !row ) {
		 		$.messager.alert("操作提示","请选择数据");
				return;
			}
			// 赋值
			$("#tempCode").val(row.tempCode);
			$("#vipMessage").textbox("setValue",row.smsContent);
			$("#smsTempId").val(row.pid);
			// 关闭窗口
			$('#smsTemplates').dialog('close');
		}
}

