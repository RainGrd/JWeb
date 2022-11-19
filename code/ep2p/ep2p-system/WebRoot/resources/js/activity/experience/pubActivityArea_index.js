/**
 * 活动专区js类
 */
var pubActivityArea = {
	//初始化
	initDataGrid:function(){
		$('#pubActivityAreaGrid').datagrid({    
			url:basePath + 'pubActivityAreaController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'活动专区列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: true,
			checkOnSelect: true,
		    columns:pubActivityArea_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#pubActivityAreaGrid');
			}
		}); 
	},
	// 打开新增
	openAdd:function(){
		// 重置表单数据
		$("#baseInfo").form("reset");
		$("#pid").val("-1");// 设置PID为-1,表示新增
		$("#activityType").val("99");// 设置类型为其他,只有这个类型才能新增,其他的类型不能通过这里新增
		// 如果是其他类型的话，就打开所有输入框
		$("#baseInfo input[name='activityCode']").removeAttr("disabled");
		$("#baseInfo input[name='activityName']").removeAttr("disabled");
		$("#baseInfo input[name='activityStartDate']").removeAttr("disabled");
		$("#baseInfo input[name='activityEndDate']").removeAttr("disabled");
		$("#baseInfo input[name='activityDesc']").removeAttr("disabled");
		// URL输入框,显示
		$("#activityUrl").show();
		// 打开新增窗口
		$('#pubActivityArea_dlg').dialog('open').dialog('setTitle', "新增");
	},
	// 打开编辑
	openEdit:function(index){
		// 获取当前需要编辑的行对象
		var row = $('#pubActivityAreaGrid').datagrid('getRows')[index];
		// 重置表单数据
		$("#baseInfo").form("reset");
		// 打开编辑窗口
		$('#pubActivityArea_dlg').dialog('open').dialog('setTitle', "编辑");
		// 赋值
		$('#baseInfo').form('load', row);
		// 判断类型
		if(row.activityType != 99){
			// 如果不是其他类型的话，就禁用一部分输入框
			$("#baseInfo input[name='activityCode']").attr("disabled","disabled");
			$("#baseInfo input[name='activityName']").attr("disabled","disabled");
			$("#baseInfo input[name='activityStartDate']").attr("disabled","disabled");
			$("#baseInfo input[name='activityEndDate']").attr("disabled","disabled");
			$("#baseInfo input[name='activityDesc']").attr("disabled","disabled");
			// URL输入框,隐藏
			$("#activityUrl").hide();
		}else{
			// 如果是其他类型的话，就打开所有输入框
			$("#baseInfo input[name='activityCode']").removeAttr("disabled");
			$("#baseInfo input[name='activityName']").removeAttr("disabled");
			$("#baseInfo input[name='activityStartDate']").removeAttr("disabled");
			$("#baseInfo input[name='activityEndDate']").removeAttr("disabled");
			$("#baseInfo input[name='activityDesc']").removeAttr("disabled");
			// URL输入框,显示
			$("#activityUrl").show();
		}
	},
	// 新增
	save:function(){
		// url
		var url = BASE_PATH + "pubActivityAreaController/save.shtml";
		// 验证表单必填项
		if(!$("#baseInfo").form('validate')){
			return ;
		}
		// 时间验证
		if($("#activityStartDate").datetimebox("getValue") != "" && $("#activityEndDate").datetimebox("getValue") != ""){
			if($("#activityStartDate").datetimebox("getValue") > $("#activityEndDate").datetimebox("getValue")){
				$.messager.alert("操作提示","适用结束日期必须大于适用开始日期");
				return;
			}
		}
		// file文件类型验证
		var file = $("#activityFile").val();
		// 判断不为空时，做验证
		if( null != file && "" != file){
			// 获取文件的后缀
			var fileType = file.split(".")[file.split(".").length-1]
			// 验证上传文件格式
			if("png" != fileType && "PNG" != fileType && "jpg" != fileType && "JPG" != fileType && "gif" != fileType && "GIF" != fileType ){
				$.messager.alert('操作提示',"文件格式只支持JPG PNG GIF，请检查！",'info');
				return;
			}
			// 赋值
			$("#activityImage").val(file);
		}
		// 提交form表单
		$("#baseInfo").form('submit', {
			url : url,
			success : function(data) {
				var result = eval("("+data+")");
 				if(result.message == 200){
 					// 判断是什么操作
 					if($("#pid").val() == -1){
 						$.messager.alert('操作提示',"新增成功!");
 					}else{
 						$.messager.alert('操作提示',"编辑成功!");
 					}
 					// 关闭
					$('#pubActivityArea_dlg').dialog('close');
 					// 刷新
					$("#pubActivityAreaGrid").datagrid('reload');
 				}else{
 					$.messager.alert('操作提示',result.message,'error');
 				}
			}
		});
	},
	// 删除 
	remove:function(pid){
		$.messager.confirm("操作提示","确定此条数据吗?",
			function(r) {
	 			if(r){
	 				$.ajax( {
	 					type : "post",
	 					url : BASE_PATH+"pubActivityAreaController/deletePubActivityArea.shtml?pid="+pid,
	 					async : false,
	 					dataType : "json",
	 					success : function(data) {
	 						if(data.message == 200){
		 						$.messager.alert('操作提示',"删除成功!");
								$("#pubActivityAreaGrid").datagrid('reload');
	 						}else{
	 							$.messager.alert('操作提示',"删除失败,请重新操作!","error");
	 						}
	 					}
	 				}); 
	 			}
		});
		
	},
	// 是否展示
	editIsShows:function(pid,isShows){
		// 修改是否展示的值,如果展示就改为不展示，如果不展示就改为展示
		isShows = isShows == 1 ? 2 : 1;
		var url = BASE_PATH+ "pubActivityAreaController/updateIsShows.shtml?pid="+pid+"&isShows="+isShows;
		$.ajax( {
			type : "post",
			url : url,
			async : false,
			dataType : "json",
			success : function(data) {
				if(data.message == 200){
					$.messager.alert('操作提示',"修改成功!");
					$("#pubActivityAreaGrid").datagrid('reload');
				}else{
					$.messager.alert('操作提示',"修改失败,请重新操作!","error");
				}
			}
		}); 
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#pubActivityAreaGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	pubActivityArea.initDataGrid();	
});
