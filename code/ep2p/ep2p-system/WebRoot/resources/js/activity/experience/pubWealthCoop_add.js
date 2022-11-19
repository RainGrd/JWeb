
/**
 * 财富合伙人活动新增js类
 */
var pubWealthCoop_add = {
		// 保存
		save:function(){
			// URL 
			var url = BASE_PATH + "pubWealthCoopController/save.shtml";
			// 验证表单必填项
			if(!$("#baseInfo").form('validate')){
				return ;
			}
			// 时间验证
			if($("#endTime").datetimebox("getValue") != "" && $("#endTime").datetimebox("getValue") != ""){
				if($("#startTime").datetimebox("getValue") > $("#startTime").datetimebox("getValue")){
					$.messager.alert("操作提示","适用结束日期必须大于适用开始日期");
					return;
				}
			}
			if($("#investAwardTypeShow").combobox("getValue") == 0 || $("#investAwardTypeShow").combobox("getValue") == undefined){
				$.messager.alert("操作提示","奖励类型必须选择");
				return;
			}
			$("#investAwardType").val($("#investAwardTypeShow").combobox("getValue"));
			// 检查各项输入问题  》》 1表示有生日特权那一个选项
			commonActivity.checkActivity(0);
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
	 				if(result.message == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
	 							reloadTabGrid("财富合伙人奖励设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"新增财富合伙人活动");
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("财富合伙人奖励设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"编辑财富合伙人活动");
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
				reloadTabGrid("财富合伙人奖励设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"新增财富合伙人活动");  
			}else{
				// 刷新页面
				reloadTabGrid("财富合伙人奖励设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"编辑财富合伙人活动");
			}
		},
		// 初始化财富合伙人新增
		initPubWealthCoop_add:function(){
			$("#baseInfo").form("reset");
			commonActivity.generatNoRuleTem("TJ","7",4);
			// 初始化条件列表
			commonActivity.initCondition_add();
		},
		// 初始化财富合伙人编辑
		initPubWealthCoop_edit:function(objectId,activityType){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubWealthCoopController/getPubWealthCoopByPid.shtml?pid="+objectId,
				async : false,
				dataType : "json",
				success : function(data) {
					$("#baseInfo").form("reset");
			 		$("#baseInfo").form('load',data.pubWealthCoop);
				}
			}); 
			// 初始化条件列表
			commonActivity.initCondition_edit(objectId,activityType);
			// 初始化复选框选中状态
			commonActivity.initCheckActivity(0);
			if($("#investAwardType").val() != null && $("#investAwardType").val() !="" && $("#investAwardType").val()!=undefined){
				$("#investAwardTypeShow").combobox("select",$("#investAwardType").val());
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
		pubWealthCoop_add.initPubWealthCoop_add();
	}else{
		// 如果是编辑  == 其他
		pubWealthCoop_add.initPubWealthCoop_edit(pid,8);
	} 
	$("#investAwardTypeShow").combobox({
		onChange: function (n,o) { 
			//
			if($("#investAwardTypeShow").combobox("getValue") == 1){ 
				$("#wealthCoopValueHTML").html("<font color='red'>奖励金(元)：</font>");
			}else if($("#investAwardTypeShow").combobox("getValue") == 2){
				$("#wealthCoopValueHTML").html("<font color='red'>奖金比例(%)：</font>");
			}
		}
	});
	if($("#investAwardType").val() != null && $("#investAwardType").val() !="" && $("#investAwardType").val()!=undefined){
		$("#investAwardTypeShow").combobox("select",$("#investAwardType").val());
	}
});

/**
 * 从数据字典中 得到所有的财富合伙人奖励类型
 */
function getInvestAwardType(){ 
	$
	.ajax({
		type : "POST",
		url : BASE_PATH
				+ "sysDistionaryContentController/selectByDisctCode.shtml?dictCode=WEALTH_COOP_TYPE",
		dataType : "json",
		success : function(data) {
			var str = "";
			for (var i = 0; i < data.data.length; i++) {
				if (data.data[i].dictId != null) {
					str +="<option value='"+data.data[i].dictId+"'>"+data.data[i].dictContName+"</option>";
				}else{
					str +="<option value='"+0+"'>"+data.data[i].dictContName+"</option>";
				}
			}
			$("#investAwardTypeShow").combobox('setValue',str);   
		},
		error : function() { 
			$.messager.alert('操作提示',"查询失败！",'error');    
		}
	});
}