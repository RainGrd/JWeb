
/**
 * 条件设定新增js类
 */
var pubCondition_add = {
		// 保存
		save:function(){
			// 赋值状态
			$("#baseInfo input[name='status']").val($("input[name='checkboxWarn']:checked").val());
			// URL 
			var url = BASE_PATH + "pubConditionController/save.shtml";
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
	 				if(result.message == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
	 							reloadTabGrid("条件设定");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"新增条件设定");
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("条件设定");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"编辑条件设定");
	 						});
	 					}
	 				}else{
	 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
	 				}
				}
			});
		},
		// 返回
		revert:function(){
			// 判断是什么操作
			if($("#pid").val() == -1){
				// 刷新页面 
				reloadTabGrid("条件设定");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"新增条件设定");
			}else{
				// 刷新页面 
				reloadTabGrid("条件设定");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"编辑条件设定");
			}
		},
		// 初始化条件设定新增
		initPubCondition_add:function(){
			$("#baseInfo").form("reset");
			var condCode = common.generatNoRuleTemNoDateTime("T","1",4);
			$("#baseInfo input[name='condCode']").val(condCode);
		},
		// 初始化条件设定编辑
		initPubCondition_edit:function(objectId){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubConditionController/getPubConditionByPid.shtml?pid="+objectId,
				async : false,
				dataType : "json",
				success : function(data) {
					// 重置数据
					$("#baseInfo").form("reset");
					// 赋值
			 		$("#baseInfo").form('load',data.pubCondition);
				}
			}); 
			// 状态判断
			var status = $("#status").val();
			// 如果等于1,启用  反之 禁用
			if(status == 1){     
				$("#baseInfo input[name='checkboxWarn']")[0].checked = true;
			}else{
				$("#baseInfo input[name='checkboxWarn']")[1].checked = true;
			}
		}
}

/**
 * 页面数据初始化
 */
$(function(){
	var pid = $("#baseInfo input[name=pid]").val();
	// 判断是新增or编辑
	if(pid == -1){
		// 如果是新增  == -1
		pubCondition_add.initPubCondition_add();
	}else{
		// 如果是编辑  == 其他
		pubCondition_add.initPubCondition_edit(pid);
	}
});