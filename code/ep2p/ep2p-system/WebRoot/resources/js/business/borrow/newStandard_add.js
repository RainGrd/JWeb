
/**
 * 新手标体验标新增类
 */
var newStandard_add = {
		// 保存
		save:function(){
			
			 var array = ""; //定义数组
			    $("#you_condition option").each(function(){ //遍历全部option
			        var val = $(this).val(); //获取option的内容
			        array+= val+","; //添加到数组中
			    });
			 array = array.substring(0,(array.length-1));
			// URL 
			var url = BASE_PATH + "bizBorrowController/saveNewStandard.shtml";
			// 验证表单必填项
			if(!$("#baseInfo").form('validate')){
				$.messager.alert('操作提示',"产品资料未完善",'info');
				return ;
			}
			// 验证表单必填项
			if(!$("#productForm").form('validate')){
				return ;
			}
			// 提交form表单
			$.ajax( {
				type : "POST",
				url : url,
				data:$("#baseInfo").serialize()+"&"+$("#productForm").serialize()+"&condIds="+array,
				async : false,
				dataType : "json",
				success : function(data) {
	 				if(data.message.status == 200){
	 					// 判断是什么操作
	 					if($("#pid").val() == -1){
		 					$.messager.alert('操作提示','新增成功','info',function(){
		 						if(data.borType == '4'){
		 							if (parent.$("#centerFrame" ).tabs('exists', '体验标新手标管理')) {
		 								parent.$('#centerFrame').tabs('close',"选择客户");
		 								parent.$('#centerFrame').tabs('select', '体验标新手标管理');
		 								window.top.reload_New_Standard.call();
		 						   }
		 							parent.$('#centerFrame').tabs('close',"新增新手标");
		 						} else if(data.borType == '5'){
		 							reloadTabGrid("体验标新手标管理");
		 							parent.$('#centerFrame').tabs('close',"新增体验标");
		 						}
	 						});
	 					}else{
	 						$.messager.alert('操作提示','编辑成功','info',function(){
	 							reloadTabGrid("体验标新手标管理");
		 						parent.$('#centerFrame').tabs('close',"编辑新手标体验标");
	 						});
	 					}
	 				}else{
	 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
	 				}
				}
			}); 
		},
		next:function(){
			// 验证表单必填项
			if($("#baseInfo").form('validate')){
				if($('#repaymentType').combobox('getValue')==""){
					$.messager.alert('操作提示',"请选择还款方式",'info');
					return;
				}
				if($('#accrualType').combobox('getValue')==""){
					$.messager.alert('操作提示',"请选择计息方式",'info');
					return;
				}	
				// 设置介绍页产品名称
				$("#proname").text($("#borrowName").val());
				// 设置介绍页期限
				$("#dl").text($("#borDeadline").val());
				// 设置介绍页年化收益
				$("#ac").text($("#borrowRate").val());
				styleController.productIncShow();
			}
		},
		// 初始化新手标体验标新增
		initPubRedpacket_add:function(){
			$("#baseInfo").form("reset");
			if($("#type").val() == '2'){
				newStandard_add.generatNoRuleTem("T","8");
			}else{
				newStandard_add.generatNoRuleTem("X","8");
			}
			// 初始化条件列表
			condition.initCondition_add();
		},
		// 初始化新手标体验标编辑
		initPubRedpacket_edit:function(objectId){
			// 初始化页面数据
			condition.initCondition_edit(objectId);
		},
		// 获取临时单号,并显示在页面
		generatNoRuleTem:function(rulePrefix,ruleType){
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"sysCreateCodeRuleController/generatLabelNoRuleTem.shtml?rulePrefix="+rulePrefix+"&ruleType="+ruleType,
				async : false,
				dataType : "json",
				success : function(data) {
					// 赋值临时活动单号
					$("#baseInfo input[name=borrowCode]").val(data.message);
				}
			}); 
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
		newStandard_add.initPubRedpacket_add();
	}else{
		// 如果是编辑  == 其他
		newStandard_add.initPubRedpacket_edit(pid);
	}
});



/**
 * 可选条件
 */
var condition = {
	
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
		initCondition_edit:function(objectId){
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
				url : BASE_PATH+"bizBorrowController/selectNewStandardConRelByObjectId.shtml?objectId="+objectId,
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
		// 特权 双击左边某行移到右边   
		leftDoubleClickByPri:function(){
			// 获取当前选择的文本的value
			var value = $("#priRightSelect option:selected").val();
			// 获取当前选中的文本的name
			var text = $("#priRightSelect option:selected").text();
			//获取当前选中的文本的type
			var type = $("#priRightSelect option:selected").attr("type");
			// 创建option标签
			var optionobj=new Option(text,value);
			optionobj.setAttribute("type",type);
			// 在右边当中添加 option 标签 
			$("#priLeftSelect").append(optionobj);
			// 在左边移除当前选中的option
			$("#priRightSelect option:selected").remove();
		},
		// 条件双击右边某行一行左边
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
		// 特权 双击右边某行一行左边
		rightDoubleClickByPri:function(){
			// 获取当前选择的文本的value
			var value = $("#priLeftSelect option:selected").val();
			// 获取当前选中的文本的name
			var text = $("#priLeftSelect option:selected").text();
			//获取当前选中的文本的type
			var type = $("#priLeftSelect option:selected").attr("type");
			// 创建option标签
			var optionobj=new Option(text,value);
			//设置option 标签中的type属性
			optionobj.setAttribute("type",type);
			// 在右边当中添加 option 标签 
			$("#priRightSelect").append(optionobj);
			// 在左边移除当前选中的option
			$("#priLeftSelect option:selected").remove();
		},
		// 条件左移到右边
		leftToRight:function(){
			if($("#zuo_condition option:selected").val()!=undefined){
				condition.leftDoubleClick();
			}else{
				$.messager.alert("操作提示","请选择需要移动的项...");
			}
		},
	
		// 条件右移到左边
		rightToLeft:function(){
			if($("#you_condition option:selected").val()!=undefined){
				condition.rightDoubleClick();
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