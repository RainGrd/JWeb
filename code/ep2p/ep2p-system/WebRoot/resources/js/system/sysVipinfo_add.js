
/**
 * VIP信息新增js类
 */
var sysVipinfo_add = {
		// 保存
		save:function(){
			// URL 
			var url = BASE_PATH + "sysVipinfoController/save.shtml";
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
			// 启用状态判断
			$("#baseInfo input[name='status']").val($("input[name='checkboxStatus']:checked").val()); 
			var condIds = "";
			// 条件选择
			var options = $("#you_condition option");
			for(var i=0;i<options.length;i++){
				 var opt = options[i];
				 	// 判断是否第一个
					if(i == 0){
						condIds += opt.value;
					}else{
						condIds += ","+opt.value;
					}
			}
			// 赋值
			$("#condIds").val(condIds);
			// 提交form表单
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) {
					var result = eval("("+data+")");
	 				if(result.message == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
	 							reloadTabGrid("VIP设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"新增VIP信息");
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("VIP设置");
		 						// 关闭当前页面
		 						parent.$('#centerFrame').tabs('close',"编辑VIP信息");
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
				reloadTabGrid("VIP设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"新增VIP信息");
			}else{
				// 刷新页面 
				reloadTabGrid("VIP设置");
				// 关闭当前页面
				parent.$('#centerFrame').tabs('close',"编辑VIP信息");
			}
		},
		// 初始化VIP信息新增
		initSysVipinfo_add:function(){
			$("#baseInfo").form("reset");
			var vipCode = common.generatNoRuleTemNoDateTime("VIP","1",4);
			$("#baseInfo input[name='vipCode']").val(vipCode);
			// 初始化条件列表
			sysVipinfo_add.initCondition_add();
		},
		// 初始化VIP信息编辑
		initSysVipinfo_edit:function(vipId){
			// 初始化页面数据
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"sysVipinfoController/getSysVipinfoByPid.shtml?pid="+vipId,
				async : false,
				dataType : "json",
				success : function(data) {
					$("#baseInfo").form("reset");
			 		$("#baseInfo").form('load',data.sysVipinfo);
				}
			}); 
			// 判断启用状态选中状态
			var status = $("#status").val();
			// 如果等于1,复选框就需要选中
			if(status == 1){
				$("#baseInfo input[name='checkboxStatus']")[0].checked = true;
			}else{
				$("#baseInfo input[name='checkboxStatus']")[1].checked = true;
			}
			// 初始化条件列表
			sysVipinfo_add.initCondition_edit(vipId);
			
		},
		// 初始化条件列表_新增
		initCondition_add:function(){
			// 加载可选条件列表
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubConditionController/getAllCondition.shtml",
				async : false,
				dataType : "json",
				success : function(data) {
					$("#zuo_condition  option").remove();
					var htmlStr = "";
					for(var i = 0;i < data.data.length;i++){
						var str = data.data[i];
						htmlStr += "<option  value="+str.pid+"  name="+str.condName+">"+str.condName+"</option>";
					}
					$("#zuo_condition").append(htmlStr);
					$("#you_condition  option").remove();
				}
			}); 
		},

		// 初始化条件列表_编辑
		initCondition_edit:function(vipId){
			// 可选条件列表
			var  kxData = "";
			// 加载可选条件列表
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"pubConditionController/getAllCondition.shtml",
				async : false,
				dataType : "json",
				success : function(data) { 
					kxData = data;
				}
			});
			// 已选条件列表
			var yxData = "";
			// 加载已选条件列表
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"sysVipinfoController/seleCondRelByVipId.shtml?vipId="+vipId,
				async : false,
				dataType : "json",
				success : function(data) {
					yxData = data;
				}
			});
			
			// 可选条件列表
			$("#zuo_condition  option").remove();
			var htmlkx = "";
			if(yxData.data.length != 0){
				for(var i = 0;i < kxData.data.length;i++){
					var str = kxData.data[i];
					for(var j = 0;j < yxData.data.length;j++){
						var str1 = yxData.data[j];
						if( str1.condId == str.pid){
							break;
						}
						if(j == yxData.data.length-1 && str1.condId != str.pid){
							htmlkx += "<option  value="+str.pid+"  name="+str.condName+">"+str.condName+"</option>";
						}
					}
				}
			}else{
				for(var i = 0;i < kxData.data.length;i++){
					var str = kxData.data[i];
					htmlkx += "<option  value="+str.pid+"  name="+str.condName+">"+str.condName+"</option>";
				}
			}
			$("#zuo_condition").append(htmlkx);
			
			// 已选条件列表
			$("#you_condition  option").remove();
			var htmlyx = "";
			for(var i = 0;i < yxData.data.length;i++){
				var str = yxData.data[i];
				htmlyx += "<option  value="+str.condId+"  name="+str.condName+">"+str.condName+"</option>";
			}
			$("#you_condition").append(htmlyx);
		},
		// 条件 双击左边某行移到右边  
		leftDoubleClick:function(){
			// 获取当前选择的文本的value
			var value = $("#zuo_condition option:selected").val();
			// 获取当前选中的文本的name
			var text = $("#zuo_condition option:selected").text();
			// 创建option标签
			var optionobj=new Option(text,value);
			// 在右边当中添加 option 标签 
			$("#you_condition").append(optionobj);
			// 在左边移除当前选中的option
			$("#zuo_condition option:selected").remove();
		},
		// 条件 双击右边某行一行左边
		rightDoubleClick:function(){
			// 获取当前选择的文本的value
			var value = $("#you_condition option:selected").val();
			// 获取当前选中的文本的name
			var text = $("#you_condition option:selected").text();
			// 创建option标签
			var optionobj=new Option(text,value);
			// 在右边当中添加 option 标签 
			$("#zuo_condition").append(optionobj);
			// 在左边移除当前选中的option
			$("#you_condition option:selected").remove();
		},
		// 条件左移到右边
		leftToRight:function(){
			if($("#zuo_condition option:selected").val()!=undefined){
				sysVipinfo_add.leftDoubleClick();
			}else{
				$.messager.alert("操作提示","请选择需要移动的项...");
			}
		},
		// 条件右移到左边
		rightToLeft:function(){
			if($("#you_condition option:selected").val()!=undefined){
				sysVipinfo_add.rightDoubleClick();
			}else{
				$.messager.alert("操作提示","请选择需要移动的项...");
			}
		},
		// 条件左边全部移到右边
		allLeftToRight:function(){
			var optionobj=$("#zuo_condition option");
			var you=$("#you_condition");
			you.append(optionobj);
			$("#zuo_condition option").remove();
		},
		// 右边全部移到左边
		allRightToLeft:function(){
			var optionobj=$("#you_condition option");
			var zuo=$("#zuo_condition");
			zuo.append(optionobj);
			$("#you_condition option").remove();
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
		sysVipinfo_add.initSysVipinfo_add();
	}else{
		// 如果是编辑  == 其他
		sysVipinfo_add.initSysVipinfo_edit(pid);
	}
});