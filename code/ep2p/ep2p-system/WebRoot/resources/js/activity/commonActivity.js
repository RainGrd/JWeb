
// 短信模版需要加载的列表字段
var smsTemplates_model = [[
                {field:'pid',checkbox:true},
       	        {field:'tempCode',title:'短信编号',width:'25%',align:'center',sortable:true},    
       	        {field:'smsContent',title:'短信内容',width:'60%',align:'center',sortable:true}
            ]];

/**
 * 活动公共的JS类 
 */
var commonActivity = {
		// 临时单号的生成
		generatNoRuleTem:function(rulePrefix,ruleType,seqLength){
			// 获取临时单号,并显示在页面
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"sysCreateCodeRuleController/generatNoRuleTem.shtml?rulePrefix="+rulePrefix+"&ruleType="+ruleType+"&seqLength="+seqLength,
				async : false,
				dataType : "json",
				success : function(data) {
					// 赋值临时活动单号
					$("#baseInfo input[name=actCode]").val(data.message);
				}
			}); 
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
		// 初始化特权列表_新增
		initPrivilege_add:function(){
			// 加载可选条件列表
			$.ajax( {
				type : "post",
				url : BASE_PATH+"actBirPriItemActivityRelController/toBirthdayPrivilegePage.shtml",
				async : false,
				dataType : "json",
				success : function(data) {
					
					$("#priRightSelect  option").remove();
					var htmlStr = "";
					for(var i = 0;i < data.priList.length;i++){
						var str = data.priList[i];
						htmlStr += "<option  value="+str.privilegeId+"  name="+str.privilegeName+" type="+str.privilegeType+">"+str.privilegeName+"</option>";
					}
					$("#priRightSelect").append(htmlStr);
					$("#priLeftSelect  option").remove();
				}
			}); 
		},
		// 初始化条件列表_编辑
		initCondition_edit:function(objectId,activityType){
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
				url : BASE_PATH+"actActivityController/selectActParConRelByObjectId.shtml?objectId="+objectId+"&activityType="+activityType,
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
			
			//如果活动类型是vip生日特权 需要查询特权信息
			if("7"==activityType){
				
				//可选特权数据
				var kxtqData ="";
				// 加载可选特权列表
				$.ajax( {
					type : "post",
					url : BASE_PATH+"actBirPriItemActivityRelController/toBirthdayPrivilegePage.shtml",
					async : false,
					dataType : "json",
					success : function(data) { 
						kxtqData = data;
					}
				}); 
				// 已选条件列表
				var yxtqData = "";
				// 加载已选条件列表
				$.ajax( {
					type : "GET",
					url : BASE_PATH+"actActivityController/selectBirPriByObjectId.shtml?objectId="+objectId ,
					async : false,
					dataType : "json",
					success : function(data) {
						yxtqData = data;
					}
				});
				
				// 可选特权列表
				$("#priRightSelect  option").remove();
				var htmlkxtq = "";
				if(yxtqData.data.length != 0){
					for(var i = 0;i < kxtqData.priList.length;i++){
						var str = kxtqData.priList[i];
						for(var j = 0;j < yxtqData.data.length;j++){
							var str1 = yxtqData.data[j];
							if( str1.objectId == str.privilegeId){
								break;
							}
							if(j == yxtqData.data.length-1 && str1.objectId != str.privilegeId){
								htmlkxtq += "<option  value="+str.privilegeId+"  name="+str.privilegeName+" type="+str.privilegeType+">"+str.privilegeName+"</option>";
							}
						}
					}
				}else{
					for(var i = 0;i < kxtqData.priList.length;i++){
						var str = kxtqData.priList[i];
						htmlkxtq += "<option  value="+str.privilegeId+"  name="+str.privilegeName+" type="+str.privilegeType+">"+str.privilegeName+"</option>";
					}
				}
				$("#priRightSelect").append(htmlkxtq);
				
				// 已选条件列表
				$("#priLeftSelect  option").remove();
				var htmlyxtq = "";
				for(var i = 0;i < yxtqData.data.length;i++){
					var str = yxtqData.data[i];
					htmlyxtq +="<option  value="+str.objectId+"  name="+str.birPriItemDesc+" type="+str.priType+">"+str.birPriItemDesc+"</option>";
				}
				$("#priLeftSelect").append(htmlyxtq);
				
			}
			 
		},
		// 初始化复选框的状态
		initCheckActivity:function(isSrtq){
			if(isSrtq == 1){
				// 是否有生日特权
				var isBirPrivilege = $("#isBirPrivilege").val();
				// 如果等于1,复选框就需要选中
				if(isBirPrivilege == 1){     
					$("#baseInfo input[name='checkboxPrivilege']")[0].checked = true;
				}
			}
			// 短信提醒状态
			var isSmsWarn = $("#isSmsWarn").val();
			// 如果等于1,复选框就需要选中
			if(isSmsWarn == 1){
				$("#baseInfo input[name='checkboxWarn']")[0].checked = true;
			}
			// 活动状态
			var status = $("#status").val();
			// 如果等于1,复选框就需要选中
			if(status == 1){
				$("#baseInfo input[name='checkboxStatus']")[0].checked = true;
			}
		},
		// 初始化
		initDataGrid:function(){
			$('#smsTemplatesGrid').datagrid({    
				url:basePath + 'sysSmsTemplatesController/smsTemplatesList.shtml',
				width:'100%',
				fit:true,
				/*title:'短信模板列表',*/
				toolbar:'#toolbar',
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
			commonActivity.initDataGrid();
			// 重置表单数据
			$("#searcm").form("reset");
		},
		// 查询短信模版信息
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#searcm"));
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
			$("#smsCode").val(row.tempCode);
			$("#tempCode").val(row.tempCode);
			$("#smsContent").val(row.smsContent);
			$("#smsId").val(row.pid);
			// 关闭窗口
			$('#smsTemplates').dialog('close');
		},
		// 检查复选框的选中状态
		checkActivity:function(isSrtq){
			if(isSrtq == 1){
				// 是否选中生日特权
				var checkboxPrivilege = $("#baseInfo input[name='checkboxPrivilege']");
				// 判断是否选中
				if(checkboxPrivilege[0].checked == true){
					$("#isBirPrivilege").val(1);
				}else{
					$("#isBirPrivilege").val(2);
				}
			}
			// 短信提醒状态
			var checkboxWarn = $("#baseInfo input[name='checkboxWarn']");
			// 判断是否选中
			if(checkboxWarn[0].checked == true){
				$("#isSmsWarn").val(1);
			}else{
				$("#isSmsWarn").val(2);
			}
			// 活动状态
			var checkboxStatus = $("#baseInfo input[name='checkboxStatus']");
			// 判断是否选中
			if(checkboxStatus[0].checked == true){
				$("#status").val(1);
			}else{
				$("#status").val(2);
			}
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
		},
		// 检查复选框的选中状态
		checkActivitypPri:function(){ 
			// 短信提醒状态
			var checkboxWarn = $("#baseInfo input[name='checkboxWarn']");
			// 判断是否选中
			if(checkboxWarn[0].checked == true){
				$("#isSmsWarn").val(1);
			}else{
				$("#isSmsWarn").val(2);
			}
			// 活动状态
			var checkboxStatus = $("#baseInfo input[name='checkboxStatus']");
			// 判断是否选中
			if(checkboxStatus[0].checked == true){
				$("#status").val(1);
			}else{
				$("#status").val(2);
			}
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
			var priIds ="";
			var priValues ="";
			var priTypes =""; 
			// 特权选择
			var optionsPri = $("#priLeft option");
			for(var i=0;i<optionsPri.length;i++){
				 var opt = optionsPri[i];
				 	// 判断是否第一个
					if(i == 0){
						priIds += opt.value;
						priValues += opt.text;
						priTypes += opt.getAttribute("type")
					}else{
						priIds += ","+opt.value;
						priValues += ","+opt.text;
						priTypes += ","+opt.getAttribute("type")
					}
			}
			$("#priIds").val(priIds);
			$("#birPriValues").val(priValues);
			$("#birPriTypes").val(priTypes);
			// 赋值
			$("#condIds").val(condIds);
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
				commonActivity.leftDoubleClick();
			}else{
				$.messager.alert("操作提示","请选择需要移动的项...");
			}
		},
		// 特权左移到右边
		leftToRightByPri:function(){
			if($("#priRightSelect option:selected").val()!=undefined){
				commonActivity.leftDoubleClickByPri();
			}else{
				$.messager.alert("操作提示","请选择需要移动的项...");
			}
		},
		// 条件右移到左边
		rightToLeft:function(){
			if($("#you_condition option:selected").val()!=undefined){
				commonActivity.rightDoubleClick();
			}else{
				$.messager.alert("操作提示","请选择需要移动的项...");
			}
		},
		// 特权右移到左边
		rightToLeftByPri:function(){
			if($("#priLeftSelect option:selected").val()!=undefined){
				commonActivity.rightDoubleClickByPri();
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
		// 特权左边全部移到右边
		allLeftToRightByPri:function(){
			var optionobj=$("#priRightSelect option");
			var zuo=$("#priLeftSelect");
			zuo.append(optionobj);
			$("#priRightSelect option").remove(); 
		},
		// 右边全部移到左边
		allRightToLeft:function(){
			var optionobj=$("#you_condition option");
			var zuo=$("#zuo_condition");
			zuo.append(optionobj);
			$("#you_condition option").remove();
		},
		// 右边全部移到左边
		allRightToLeftByPri:function(){
			var optionobj=$("#priLeftSelect option");
			var you=$("#priRightSelect");
			you.append(optionobj);
			$("#priLeftSelect option").remove();
		}
}

