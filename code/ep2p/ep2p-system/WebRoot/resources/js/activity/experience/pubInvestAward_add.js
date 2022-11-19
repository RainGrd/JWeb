
/**
 * 投资奖励活动新增js类
 */
var pubInvestAward_add = {
		// 保存
		save:function(){
			// URL 
			var url = BASE_PATH + "pubInvestAwardController/save.shtml";
			// 验证表单必填项
			if(!$("#baseInfo").form('validate')){
				return ;
			}
			// 有效期验证
			if($("#investAwardType").combobox("getValue") == "3"){
				if($("#validity").numberbox("getValue") == "" ){
					$.messager.alert("操作提示","你选择的奖励类型为加息劵,请填写有效期");
					return;
				}
				if($("#interest").numberbox("getValue") == "" ){
					$.messager.alert("操作提示","你选择的奖励类型为加息劵,请填写加息劵总数");
					return;
				}
			}else{
				$("#validity").numberbox("setValue","");
			}
			// 时间验证
			if($("#startTime").datetimebox("getValue") != "" && $("#endTime").datetimebox("getValue") != ""){
				if($("#startTime").datetimebox("getValue") > $("#endTime").datetimebox("getValue")){
					$.messager.alert("操作提示","适用结束日期必须大于适用开始日期");
					return;
				}
			}
			// 检查各项输入问题  》》 1表示有生日特权那一个选项
			commonActivity.checkActivity(1);
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
	 				if(result.message == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
	 							reloadTabGrid("投资奖励活动设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"新增投资奖励活动");
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("投资奖励活动设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"编辑投资奖励活动");
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
				reloadTabGrid("投资奖励活动设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"新增投资奖励活动");
			}else{
				// 刷新页面
				reloadTabGrid("投资奖励活动设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"编辑投资奖励活动");
			}
		},
		// 初始化投资奖励新增
		initPubInvestAward_add:function(){
			$("#baseInfo").form("reset");
			commonActivity.generatNoRuleTem("TO","7",4);
			// 初始化条件列表
			commonActivity.initCondition_add();
		},
		// 初始化投资奖励编辑
		initPubInvestAward_edit:function(objectId,activityType){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubInvestAwardController/getPubInvestAwardByPid.shtml?pid="+objectId,
				async : false,
				dataType : "json",
				success : function(data) {
					$("#baseInfo").form("reset");
			 		$("#baseInfo").form('load',data.pubInvestAward);
			 		var investAwardType = data.pubInvestAward.investAwardType;
			 		// 判断等于什么类型  1积分  2经验  3加息劵
					if(investAwardType == "1"){
						$("#dtgb1").html("奖励：");
						$("#dtgb").html("点积分");
						$("#yxq").hide();
						$("#yxq1").hide();
						$("#zs").hide();
						$("#zs1").hide();
						// 设置加息劵有效期为空
						$("#validity").numberbox("setValue","");
					}else if(investAwardType == "2"){
						$("#dtgb1").html("奖励：");
						$("#dtgb").html("点经验");
						$("#yxq").hide();
						$("#yxq1").hide();
						$("#zs").hide();
						$("#zs1").hide();
						// 设置加息劵有效期为空
						$("#validity").numberbox("setValue","");
					}else if(investAwardType == "3"){
						$("#dtgb1").html("加息比例：");
						$("#dtgb").html("%");
						$("#yxq").show();
						$("#yxq1").show();
						$("#zs").show();
						$("#zs1").show();
						$("#validity").numberbox("setValue",data.pubInvestAward.validity);
					}
			 		
				}
			}); 
			// 初始化条件列表
			commonActivity.initCondition_edit(objectId,activityType);
			// 初始化复选框选中状态
			commonActivity.initCheckActivity(1);
		},
		// 选择奖励类型事件
		selectEvent:function(rec){
			// 判断等于什么类型  1积分  2经验  3加息劵
			if($("#investAwardType").combobox("getValue") == "1"){
				$("#dtgb1").html("奖励：");
				$("#dtgb").html("点积分");
				$("#yxq").hide();
				$("#yxq1").hide();
				$("#zs").hide();
				$("#zs1").hide();
				// 设置加息劵有效期为空
				$("#validity").numberbox("setValue","");
				// 奖励值改变为空
				$("#investAwardValue").numberbox("setValue","");
			}else if($("#investAwardType").combobox("getValue") == "2"){
				$("#dtgb1").html("奖励：");
				$("#dtgb").html("点经验");
				$("#yxq").hide();
				$("#yxq1").hide();
				$("#zs").hide();
				$("#zs1").hide();
				// 设置加息劵有效期为空
				$("#validity").numberbox("setValue","");
				// 奖励值改变为空
				$("#investAwardValue").numberbox("setValue","");
			}else if($("#investAwardType").combobox("getValue") == "3"){
				$("#dtgb1").html("加息比例：");
				$("#dtgb").html("%");
				$("#yxq").show();
				$("#yxq1").show();
				$("#zs").show();
				$("#zs1").show();
				// 奖励值改变为空
				$("#investAwardValue").numberbox("setValue","");
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
		pubInvestAward_add.initPubInvestAward_add();
	}else{
		// 如果是编辑  == 其他
		pubInvestAward_add.initPubInvestAward_edit(pid,4);
	}
});