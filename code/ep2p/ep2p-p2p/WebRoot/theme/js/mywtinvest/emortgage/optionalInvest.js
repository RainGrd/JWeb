$(function(){
 		 $('#plan_tab>div').eq(0).css({'border-style': 'solid','color':'#297FB9','border-width':'0px 0px 1px 0px'});//渲染选中节点
 		//选中协议的渲染效果
 		$("#agree").html("<img src="+BASE_PATH+"theme/default/images/gou_b.png class='block' />");
 		$("#agree").attr("val","1");
 		$("#agree").parent().addClass("c2980b9"); 
 		
 		$("#agree").click(function(){
 			if($(this).attr("val")=="1"){
 				$(this).html('');
 				$(this).attr("val","0");
 				$(this).parent().removeClass("c2980b9"); 
 			}else{
 				$(this).html("<img src="+BASE_PATH+"theme/default/images/gou_b.png class='block' />");
 				$(this).attr("val","1");
 				$(this).parent().addClass("c2980b9"); 
 			}
 			
 		})
		 
		
 		$('#plan_tab>span').click(function(){ 
 			 $(this).addClass("ms_t_se");//渲染选中节点
 			 $(this).siblings().removeClass("ms_t_se")//取消兄弟节点的样式 
 			 if('projectInfo' == $(this).attr('id')){ 
 				 //选择的元素是“项目信息”
 				 $('#projectInfoContent').show();
 				 $('#riskInfoContent').hide(); 
 				 $('#repayPlan').hide(); 
 				 $('#investHisContent').hide();  
 			 }else if('riskInfo' == $(this).attr('id')){
 				 //选择的元素是“风控信息”
 				 $('#riskInfoContent').show();
 				 $('#projectInfoContent').hide();    
 				 $('#repayPlan').hide(); 
 				 $('#investHisContent').hide();  
 				// 加载用户材料
 				 getFileInfo();
 			 }else if('returnInfo' == $(this).attr('id') ){
 				 //选择的元素是“还款计划”
 				 $('#repayPlan').show(); 
 				 $('#projectInfoContent').hide();
 				 $('#riskInfoContent').hide();  
 				 $('#investHisContent').hide();
 				 //加载还款计划
 				repayPlan();
 			 }else if('investHis' == $(this).attr('id')){
 				 //选择的元素是“投资记录”
 				 $('#investHisContent').show(); 
 				 $('#repayPlan').hide(); 
 				 $('#projectInfoContent').hide();
 				 $('#riskInfoContent').hide();   
				 investHisContent();
 			 }
 		});
 		$("#nowInvest").click(function(){
 			var investNumber = $("#investNumber").val();//投资金额
 			var borrowId = $("#borrowId").val();//借款编号
 			var startMoney = formatNull($("#startMoney").val());//起投金额  
 			var expectAmount = formatNull($("#expectAmountVal").val());//预期收益
 			var investReward = formatNull($("#investRewardVal").val());//投资奖励
 			var eplanIntegral = formatNull($("#eplanIntegralVal").val());//可获积分
 			var availableAmount = formatNull($("#availableAmount").val());//账户可用余额
 			var endMoney = formatNull($("#endMoney").val());//投资上限
// 			if(investNumber==undefined){
// 				layer.alert("起投金额不能为空");
// 				return;
// 			} 
 			if($("#agree").attr("val")!="1"){
 				layer.alert("请仔细阅读并同意协议");
 				return;
 			}
 			//投资金额不能大于可用余额
 			//if(availableAmount-investNumber<0){ 
 			//	layer.alert("可用余额不足!");
 			//	return 
 			//}
 			//iframe层
 			layer.open({
 			    type: 2,
 			    title: false,
 			    closeBtn: 0,
 			    scrollbar: false,
 			    shadeClose: true,
 			    shade: 0.8,
 			    offset: ['10%', '25%'],
			    area: ['950px', '65%'], 
			    content: BASE_PATH+"/business/optionalInvestController/selectOptionalInvestDataInfoDataPreview.shtml?investNumber="+investNumber+"&pageTag=2&borrowId="+borrowId+"&expectAmount="+expectAmount+
			    	"&investReward="+investReward+"&eplanIntegral="+eplanIntegral, //iframe的url
 			});
 		}) 
 		 var customerId = $("#customerId").val();
		 var borrowId  = $("#borrowId").val(); 
 		 getProjectInfo($("#customerId").val());//加载借款人基本信息
		 getBorrowInfo($("#borrowId").val());//加载项目基本信息
 	});
	/**
	 * 格式化 null Undefined 
	 * @param value 需要格式化的值
	 */
	function formatNull(value){
		//如果传入值为数字  为空返回0
		 if(typeof(value)=="number"){
			 if(value == undefined || value == null){
				 return 0;
			 }else{
				 return value;
			 }
		 }else{
			 //只要不是数字  为空一律返回""
			 if(value == undefined || value == null){
				 return "";
			 }else{
				 return value;
			 }
		 }
	}
 	//得到项目基本信息（联系人信息）
 	function getProjectInfo(customerId){
 		if(null!=customerId && ''!=customerId){
			$.ajax({
				type: 'POST',
		    	url : BASE_PATH+'/business/optionalInvestController/index/getCustomerByPid.shtml',
		    	data:{customerId:customerId},
				dataType: 'json',
				async:false,
			    success: function(data){  
			    	
			    	//婚姻状况
			    	var isMarriage = "";
			    	if(data.cusTomer.isMarriage!=undefined&&data.cusTomer.isMarriage!=null){
			    		if(data.cusTomer.isMarriage=="1"){
			    			isMarriage = "未婚";
			    		}else if(data.cusTomer.isMarriage=="2"){
			    			isMarriage="已婚"
			    		}else if(data.cusTomer.isMarriage=="3"){
			    			isMarriage ="离异";
			    		}else if(data.cusTomer.isMarriage=="4"){
			    			isMarriage="丧偶";
			    		}
			    	}
			    	var sex = "";
			    	if(data.cusTomer.sex!=undefined&&data.cusTomer.sex!=null){
			    		if(data.cusTomer.sex==1){
			    			sex="男";
			    		}else{
			    			sex="女"
			    		}
			    	}
			    	var html ='<p class="backgroundBlue mt38">基本资料</p>'+
			                   ' <p class="size14 eRoomBasic">'+
			                   '     <span class="textalignR gray">借款人</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+formatNull(data.cusTomer.customerName)+'</span>'+
			                   '     <span class="textalignR gray">婚姻状况</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+isMarriage+'</span>'+
			                   '     <span class="textalignR gray">性别</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+sex+'</span>'+
			                   ' </p>'+
			                   ' <p class="size14 eRoomBasic">'+
			                   '     <span class="textalignR gray">姓名</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+formatNull(data.cusTomer.sname)+'</span>'+
			                   '     <span class="textalignR gray">年龄</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+data.cusTomer.age+'</span>'+
			                   '     <span class="textalignR gray">户籍</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+formatNull(data.cusTomer.homeAddress)+'</span>'+
			                   ' </p>';
			                   
		    		$('#projectInfoContent').append(html);
				}
			});
		}  
 	}
 	//加载 借款信息
 	function getBorrowInfo(borrowId){
 		$.ajax({
			type: 'POST',
	    	url : BASE_PATH+'business/optionalInvestController/index/getHousesByBorrowId.shtml',
	    	data:{borrowId:borrowId},
			dataType: 'json', 
			async:false,
		    success: function(data){
		    		result = data.borrow;
		    		houses = data.houses;
		    		var str;
		    		//e抵押
		    		if("1" == result.borrowType){
		    			str ='<p class="size14 eRoomInfo">'+
		                   '    <span class="textalignR gray">抵押物信息</span>'+
		                   '    <span class="textalignL colorDarkBlue">'+formatNull(result.hostageInfo)+'</span>'+
		                   '</p>'+
		                   '<p class="size14 eRoomInfo">'+ 
		                   '    <span class="textalignR gray">抵押物价值</span>'+
		                   '    <span class="textalignL colorDarkBlue">'+formatNull(result.hostageValuable)+'</span>'+
		                   '</p>';
		    		}else if("2" == result.borrowType){
		    			//e首房
		    			str =  '<p class="size14 eRoomInfo">'+
			                   '    <span class="textalignR gray">房产信息</span>'+
			                   '    <span class="textalignL colorDarkBlue">'+formatNull(houses.homeDesc)+'</span>'+
			                   '</p>';
		    		}
		    		//基本信息  
		    		var	userBaseHtml=' <p class="backgroundBlue mt38">项目信息</p>'+
			                   ' <p class="size14 eRoomInfo">'+
			                   '     <span class="textalignR gray">借款用途</span>'+
			                   '     <span class="textalignL colorDarkBlue">'+result.borrowUse+'</span>'+
			                   ' </p>'+
			                   ' <p class="size14 eRoomInfo">'+
			                   '    <span class="textalignR gray">还款来源</span>'+
			                   '    <span class="textalignL colorDarkBlue">'+result.payment+'</span>'+
			                   ' </p>'+
			                   str+ 
			                   '</p>'; 
		    		$("#projectInfoContent").append(userBaseHtml); 
		    		
		    		var riskInfoHtml =' <p class="backgroundBlue mt38">风控信息</p>'+
					                     '<p class="size14 eRoomInfo">'+
					                     '    <span class="textalignR gray">担保机构</span>'+
					                     '    <span class="textalignL colorDarkBlue">'+formatNull(result.guaOrgName)+'</span>'+
					                     '</p>'+
					                     '<p class="size14 eRoomInfo">'+
					                     '    <span class="textalignR gray">担保承诺</span>'+
					                     '    <span class="textalignL colorDarkBlue">'+formatNull(result.guaAcc)+'</span>'+
					                     '</p>'+
					                     '<p class="size14 eRoomInfo">'+
					                     '    <span class="textalignR gray">风控综述</span>'+
					                     '    <span class="textalignL colorDarkBlue">'+formatNull(result.riskDesc)+'</span>'+
					                     '</p>';

		    		$("#riskInfoContent").append(riskInfoHtml); 
		    } 
		});
 	}
 	//加载用户材料
 	function getFileInfo(){ 
		var borrowId = $('#borrowId').val();//借款id
		if($("#custMakings").html() != null) return false;
 		$.ajax({
			type: 'POST',
			url : BASE_PATH+'business/optionalInvestController/index/getBorrowFileRelByType.shtml',
	    	data:{borrowId:borrowId,borFileRelType:'7'}, 
			dataType: 'json',
			async:false,
		    success: function(data){ 
		    	var riskInfoHtml='<div id="custMakings"><p class="backgroundBlue mt38">用户材料</p>'+
			                 	'<div class="eroomMaterial">'+
			                 	'<div class="scrollWrap">'+
			                    '     <ul class="scrollBox" style="height: 250px">';
				for(var i = 0 ; i < data.result.length ; i++){
					var fileList = data.result;
					var url = BASE_PATH+'bizBorrowController/download.shtml?path='+fileList[i].fileUrl+'&fileName=\''+fileList[i].fileName+'.'+fileList[i].fileType+'\'';
	    			//pdf
	    			if(fileList[i].fileType == 'pdf' || fileList[i].fileType == 'PDF'){
	    			}else{
	    				riskInfoHtml+='<li><img height="150" src="'+BASE_PATH+fileList[i].fileUrl+'"/>';
	    			}
				}  
		    	riskInfoHtml+=' </ul>'+
				              ' </div>'+
				              ' <div class="scrollBoxBtn">';
		    	if(data.result.length > 4){
		    		riskInfoHtml+= '     <a href="javascript:;" class="scrollPageP">&lt;</a>'+
		              '     <a href="javascript:;" class="scrollPageN">&gt;</a>';
		    	}
				              
		    	riskInfoHtml+=  ' </div>'+
				              '</div> </div>'; 
				$('#riskInfoContent').append(riskInfoHtml);
			}
		}); 
	 //e首房焦点图 
		var oThis=0;
		var l=$(".scrollWrap li").length-4;
		$(".scrollBox li").click(function(){
			oThis=$(this).index();
			//alert(oThis);
		});
		$(".scrollBoxBtn .scrollPageP").click(function(){
			if(oThis>0){
				oThis--;
				$(".scrollWrap ul").animate({"left":-oThis*248+"px"},500)
			}
			else{oThis=0}
		});
		$(".scrollBoxBtn .scrollPageN").click(function(){
			if(oThis<l){
				oThis++;
				$(".scrollWrap ul").animate({"left":-oThis*248+"px"},500)
			}
			else{oThis=l}
		});
 	}
 	//获得投资记录
 	function investHisContent(){
 		
 		 var borrowId = $('#borrowId').val();
 		$("#page").page('destroy');
		 /*分页*/ 
			$('#page').page({
				pageSize : 10,
				showInfo : false,
				showJump : true,
				showPageSizes : false,
				loadFirstPage : true,  // 自动加载第一页
				remote : {
					url : BASE_PATH+'business/optionalInvestController/index/selectInvestHisByBorrowId.shtml?borrowId='+borrowId,
					params : null,
					success : function(result, pageIndex) {
						var html = "";
						if(result.detailList ==null ){
							html = "<div>暂无投资记录</div>"
							$(".finProHistory").append(html);
							return;
						}else{
							html ="<div class='invest_table'>" +
								"<table width='100%' border='0' cellspacing='0' cellpadding='0' class='esc_t'>"+ 
										"<thead> "+
										"	<tr> "+
										"		<th>投资人</td>"+
										"		<th>投资方式</td>"+
										"		<th>投资金额(元)</td>"+
										"		<th>投资时间</td>"+
										"		<th>状态</td>"+
										"	</tr> "+
										"</thead> ";
							for(var i=0;i<result.detailList.length;i++){
								var detail = result.detailList[i];
								var investmentTypeName = detail.investmentTypeName==null?'':detail.investmentTypeName;
								  html+="	<tr> "+
										"		<td>"+detail.privateName+"</td>"+
										"		<td>"+detail.investmentTypeName+"</td>"+
										"		<td>"+escfutil.formatCurrency(detail.investmentAmount)+"</td>"+
										"		<td>"+detail.investmentTime+"</td>"+
										"		<td>成功</td>"+
										"	</tr> ";
							}	 		
							html+="</table></div>"
								$("#investHispage").html("");
								$("#investHispage").append(html);
						}
					},
					pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
					pageSizeName : 'pageSize'//请求参数，每页数量
				}
			}); 
 	}
 	
 	/**
 	 * 投资金额输入框 失去光标事件
 	 * @param obj
 	 */
 	function count(obj){
		getEplanIntegral(obj);//获取积分
		$('#repaymentAmt').val($(obj).val());
		getInvestReward();//获取投资奖励
		
	}
 	
 	
 	/**
 	 * 得到奖励积分
 	 * @param obj 
 	 */
 	function getEplanIntegral(obj){
		var investNumber = $(obj).val();//输入的投资金额
		var customerId = $("#customerId").val();
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/business/financialProductsManageController/index/getEplanIntegral.shtml",
			data : {pointGetType:"eplan_integral",investmentMoney:investNumber,customerId:customerId},
			dataType : "json",
			success : function(data) {
				 $("#eplanIntegral").text(data.eplanIntegral);
				 $("#eplanIntegralVal").val(data.eplanIntegral);
			},
			error : function() {
				layer.alert("查询积分接口异常!"); 
			}
		});
	}
 	
 	/**
	 * 得到预期收益 和  投资奖励
	 * @param obj 
	 */
	function getInvestReward(){
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/business/financialProductsManageController/index/getInvestReward.shtml",
			data : $("#investRewardForm").serializeArray(),
			dataType : "json",
			success : function(data) {  
				 $("#expectAmount").text("￥"+escfutil.formatCurrency(data.eplanIntegral));//预期收益
				 $("#investReward").text(escfutil.formatCurrency(data.investmentReward));//投资奖励
				 $("#expectAmountVal").val(data.eplanIntegral);//预期收益
				 $("#investRewardVal").val(data.investmentReward);//投资奖励
				},
			error : function() {
				layer.alert("查询预期收益接口异常!"); 
			}
		});
	}
	/**
	 * 还款计划
	 */
	function repayPlan(){
		var borrowId = $('#borrowId').val();
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/business/financialProductsManageController/index/selectRepaymentsByBorrowId.shtml",
			data : {"borrowId":$("#borrowId").val()},
			dataType : "json",
			success : function(data) { 
					var html = '';
					if(data.planList ==null || data.planList.length==0){
						html = '<div>暂无还款计划</div>'
						$('#repayPlan').html(html);
						return;
					}else{
						html = "<div class='invest_table'>"+ 
						"<table width='100%' border='0' cellspacing='0' cellpadding='0' class='esc_t mt30'>"+
									"	<tr> "+
									"		<th>期次</td>"+
									"		<th>还款时间</td>"+
									"		<th>应收本金(元)</td>"+
									"		<th>应收利息(元)</td>"+
									"		<th>应收本息(元)</td>"+
									"		<th>剩余本金(元)</td>"+
									"	</tr> ";
						for(var i=0;i< data.planList.length;i++){
							var plan = data.planList[i];
							  html+="	<tr> "+
									"		<td>"+formatNull(plan.planindex)+"</td>"+
									"		<td>"+formatDate(formatNull(plan.expireTime))+"</td>"+
									"		<td>"+escfutil.formatCurrency(plan.capital)+"</td>"+
									"		<td>"+escfutil.formatCurrency(plan.interest)+"</td>"+
									"		<td>"+escfutil.formatCurrency(accAdd(formatNull(plan.capital),formatNull(plan.interest)))+"</td>"+
									"		<td>"+escfutil.formatCurrency(plan.remainingCapital)+"</td>"+
									"	</tr> ";
						}
						html+="</table></div>";
						$('#repayPlan').html("");
						$('#repayPlan').html(html);
				}
			}
		}) 
	}
	function countDown(d) {
		if (d <= 0) {
			// 失效 
			$("#countDown").text("00天00小时00分00秒"); 
			$("#nowInvest").removeClass("btn");
			$("#nowInvest").addClass("btn_gray");
			$("#nowInvest").attr("id","");
			// 关闭倒计时
			clearTimeout(countDown);
			return false; 
		} 
		var day = parseInt(d / 60 / 60 / 24);
		var h = parseInt(d / 60 / 60 % 24);
		var m = parseInt(d / 60 % 60);
		var s = parseInt(d % 60);

		day = day > 9 ? day : "0" + day;
		h = h > 9 ? h : "0" + h;
		m = m > 9 ? m : "0" + m;
		s = s > 9 ? s : "0" + s;
		var time = day + "天" + h + "小时" + m + "分" + s + "秒";
		$("#countDown").text(time); 
		setTimeout("countDown('" + ( d - 1) + "')", 1000);
	}
	 
function formatDate(date){
	if(date.lastIndexOf(" ") <= 0 || date.length <= 0) return date;
	return date.substring(0,date.lastIndexOf(" "));
}