
/**
 * 理财产品管理 
 */
var financialBorrowManage_add = {
		// 保存 
		saveBorrow:function(){
			var customerId = $("#customerId").val();//客户id
			var productsPid = $("#productsPid").val();//理财产品介绍
			var borrowPid = $("#borrowPid").val(); //借款id 
			// URL 
			var url = BASE_PATH + "bizBorrowController/saveBorrow.shtml";
			// 验证表单必填项
			if(!$("#borrowLowForm").form('validate') || !$("#borrowBaseForm").form('validate') || !$("#borrowMiddelForm").form('validate')){
				return ;
			} 
			var condIds = "";
			// 赋值
			$("#condIds").val(condIds);
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
			
			$.ajax( {
				type : "POST",
				url : url,
				data:$("#borrowLowForm").serialize()+"&"+$("#borrowBaseForm").serialize()+"&"+$("#borrowMiddelForm").serialize()+"&borrowPid="+borrowPid+"&productsPid="+productsPid,
				async : false,
				dataType : "json",
				success : function(data) {  
	 				if(data.message == 200){ 
	 					$.messager.alert('操作提示','操作成功','info');
							// 刷新页面
	 					reloadTabGrid("理财产品管理");
	 					// 关闭当前页面
	 					parent.$('#centerFrame').tabs('close',"选择客户");
	 					parent.$('#centerFrame').tabs('close',"理财产品管理新增\修改"); 
	 				}else{
	 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
	 				}  
				},
				error : function(data){
					
				}
			}); 
		}, 
		generatNoRuleTem:function(rulePrefix,ruleType,seqLength){
			// 获取临时单号,并显示在页面
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"sysCreateCodeRuleController/generatLabelNoRuleTem.shtml?rulePrefix="+rulePrefix+"&ruleType="+ruleType,
				async : false,
				dataType : "json",
				success : function(data) {
					// 赋值临时活动单号
					$("#borrowBaseForm input[name=borrowCode]").val(data.message);
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
		initPubExpGold_edit:function(pid,borrowType){
			$.ajax( {
				type : "GET",
				url : BASE_PATH+"bizBorrowController/getBorrowInfoVOById.shtml?pid="+pid,
				dataType : "json",
				success : function(data) {
					if(data.result != null ){
						$("#customerIdShow").html("<a href='javacript:void(0)' onclick='openUserPage("+data.result.customerId +")'>"+data.result.customerName );
						$("#sname").html(data.result.sname);
					}
					var custName ="";
							if(data.result != null ){ 
								if(data.result.customerName == null || "" == data.result.customerName){
									custName = data.result.phoneNo;
								}else{
									custName = data.result.customerName;
								}
							}
					var custHtml = '<a href="javacript:void(0)" onclick="openUserPage('+data.result.customerId+')">'+custName+'</a>'		
					$("#sname").text(data.result.sname == null ?"":data.result.sname);
					$("#customerIdShow").text(custName);
					$("#borrowBaseForm").form("reset");
			 		$("#borrowBaseForm").form('load',data.result);
			 		$("#borrowMiddelForm").form("reset");
			 		$("#borrowMiddelForm").form('load',data.products);
			 		$("#borrowLowForm").form("reset");
			 		$("#borrowLowForm").form('load',data.products); 
			 		$("#borrowRateShow").numberbox('setValue',data.result.borrowRate*100);
			 		$("#manageExpenseRate").numberbox('setValue',data.result.manageExpenseRate*100);
			 		$("#investRewardScale").numberbox('setValue',data.result.investRewardScale*100);
			 		
				}
			}); 
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
				url : BASE_PATH+"bizBorrowController/selectNewStandardConRelByObjectId.shtml?objectId="+pid,
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
		//跳转到发信息页面
		toChoseProtocolPage:function(){
			$("<div id='newStandard_Protocol' /> ").dialog({
				href:BASE_PATH + "agreementContextController/chooseAgreement.shtml",
				title:"借款协议选择",
				method:'post',
				maximized:false,
				width:580,
				height:450,
				modal: true,
				buttons:[{
					text:'选择',
					iconCls:'icon-save',
					handler:function(){
						financialBorrowManage_add.checkInfo();
					}
				}],
				onClose : function() {
					$(this).dialog('destroy');
				} 
			});
		},
		//选择协议的信息数据
		checkInfo:function(){
			var checkList = $("#agreementMContext_datagrid_list").datagrid("getSelections");
			if(checkList.length == 1){
				var pid = checkList[0].pid;
				var agrContName = checkList[0].borAgrAnme;
				$("#financeName").textbox("setValue",agrContName);
				$("#borrowAgreementId").val(pid);
				$("#newStandard_Protocol").dialog('close');
			}else{
				$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
			}
		}
		
} 

function openUserPage(customerId){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+customerId;
	childLayout_addTab(path,'查看客户资料');
}
