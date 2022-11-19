
/**
 * VIP信息活动新增js类
 */
var pubVipinfo_add = {
		// 保存
		save:function(){
			// URL 
			var url = BASE_PATH + "pubVipinfoController/save.shtml";
			// 验证表单必填项
			if(!$("#baseInfo").form('validate')){
				return ;
			}
			// 时间验证
			if($("#startTime").datetimebox("getValue") != "" && $("#endTime").datetimebox("getValue") != ""){
				if($("#startTime").datetimebox("getValue") > $("#endTime").datetimebox("getValue")){
					$.messager.alert("操作提示","适用结束日期必须大于适用开始日期");
					return;
				}
			}
			// 检查各项输入问题 》》 -1表示没有生日特权那一个选项
			commonActivity.checkActivity(-1);
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
	 				if(result.message == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
	 							reloadTabGrid("赠送VIP活动设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"新增VIP赠送活动");
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("赠送VIP活动设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"编辑VIP赠送活动");
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
				reloadTabGrid("赠送VIP活动设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"新增VIP赠送活动");
			}else{
				// 刷新页面 
				reloadTabGrid("赠送VIP活动设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"编辑VIP赠送活动");
			}
		},
		// 初始化VIP信息新增
		initPubVipinfo_add:function(){
			$("#baseInfo").form("reset");
			commonActivity.generatNoRuleTem("VIP","7",4);
			// 初始化条件列表
			commonActivity.initCondition_add();
		},
		// 初始化VIP信息编辑
		initPubVipinfo_edit:function(objectId,activityType){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubVipinfoController/getPubVipinfoByPid.shtml?pid="+objectId,
				async : false,
				dataType : "json",
				success : function(data) {
					$("#baseInfo").form("reset");
			 		$("#baseInfo").form('load',data.pubVipinfo);
				}
			}); 
			// 初始化条件列表
			commonActivity.initCondition_edit(objectId,activityType);
			// 初始化复选框选中状态
			commonActivity.initCheckActivity(-1);
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
		pubVipinfo_add.initPubVipinfo_add();
	}else{
		// 如果是编辑  == 其他
		pubVipinfo_add.initPubVipinfo_edit(pid,1);
	}
});