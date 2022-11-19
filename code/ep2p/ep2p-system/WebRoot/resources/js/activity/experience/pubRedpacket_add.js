
/**
 * 红包活动新增js类
 */
var pubRedpacket_add = {
		// 保存
		save:function(){
			// URL 
			var url = BASE_PATH + "pubRedpacketController/save.shtml";
			// 验证表单必填项
			if(!$("#baseInfo").form('validate')){
				return ;
			}
			// 时间验证
			if($("#effTime").datetimebox("getValue") != "" && $("#expTime").datetimebox("getValue") != ""){
				if($("#effTime").datetimebox("getValue") > $("#expTime").datetimebox("getValue")){
					$.messager.alert("操作提示","适用结束日期必须大于适用开始日期");
					return;
				}
			}
			// 检查各项输入问题  》》 1表示有生日特权那一个选项
			commonActivity.checkActivity(2);
			// 赋值--红包类型为抢红包
			$("#redpacketType").val(2);
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
	 				if(result.message == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
	 							reloadTabGrid("抢红包活动设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"新增抢红包活动");
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("抢红包活动设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"编辑抢红包活动");
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
			// 刷新页面
			reloadTabGrid("抢红包活动设置");
			// 判断是什么操作
			if($("#pid").val() == -1){
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"新增抢红包活动");
			}else{
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"编辑抢红包活动");
			}
		},
		// 初始化红包新增
		initPubRedpacket_add:function(){
			$("#baseInfo").form("reset");
			commonActivity.generatNoRuleTem("QHB","7",4);
			// 初始化条件列表
			commonActivity.initCondition_add();
		},
		// 初始化红包编辑
		initPubRedpacket_edit:function(objectId,activityType){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubRedpacketController/getPubRedpacketByPid.shtml?pid="+objectId,
				async : false,
				dataType : "json",
				success : function(data) {
					$("#baseInfo").form("reset");
			 		$("#baseInfo").form('load',data.pubRedpacket);
				}
			}); 
			// 初始化条件列表
			commonActivity.initCondition_edit(objectId,activityType);
			// 初始化复选框选中状态
			commonActivity.initCheckActivity(2);
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
		pubRedpacket_add.initPubRedpacket_add();
	}else{
		// 如果是编辑  == 其他
		pubRedpacket_add.initPubRedpacket_edit(pid,2);
	}
});