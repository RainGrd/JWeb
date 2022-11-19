/**
 * 实名认证审核js类
 */
var custAuthenticationHis = {
	//初始化
	initDataGrid:function(){
		$('#custAuthenticationHisGrid_detail').datagrid({    
			url:basePath + 'authenVipCustomerController/selectAuthenticationByPid.shtml',
			width:'100%',
			method: 'POST',
			queryParams:{'pid':$("#pid").val()},
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: true,
			checkOnSelect: true,
		    columns:custAuthenticationHisAuditing_Model,
		    onLoadSuccess:function(data){
				clearSelectRows('#custAuthenticationHisGrid_detail');
			}
		});
	},
	//加载客服历史详情
	loadData:function(){
		var pid =$("#pid").val();
		if("" != pid){
			$.ajax({
				type: "POST",
		    	url : basePath + 'customerController/selectCusHistoryDetailedById.shtml',
		    	data:{data:"{pid:"+pid+"}"},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		$("#baseInfo").form('load',data.result);
			    		$("#pid").val(pid);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		}
	},
	// 保存提交
	save:function(){
		// 判断选择的是那一个
		$("#baseInfo input[name='status']").val($("input[name='statusRadio']:checked").val()); 
		var url = basePath +"authenVipCustomerController/saveCustAuthenticationHis.shtml";
		// 提交form表单
		$("#baseInfo").form('submit', {
			url : url,
			success : function(data) {
				var result = eval("("+data+")");
 				if(result.message == 200){
 					$.messager.alert('操作提示','实名认证审核成功','info',function(){
						reloadTabGrid("实名认证");
 						// 关闭当前页面
 						parent.$('#centerFrame').tabs('close',"实名认证审核");
					});
 				}else{
 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
 				}
			}
		});
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	custAuthenticationHis.loadData();
	custAuthenticationHis.initDataGrid();	
});
