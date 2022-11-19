$(function(){  
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
 		$("#title_drop>span").click(function(){  
 			 $(this).addClass("ms_t_se");
 			$(this).siblings().removeClass("ms_t_se");
 			 if("description" == $(this).attr("id")){
 				 //选择的元素是“项目描述”
 				 $("#finProDescription").show();   
 				 $("#finProContent").hide();
 				 $("#finProHistory").hide(); 
 			 }else if("product" == $(this).attr("id")){
 				 //选择的元素是“产品介绍”
 				 $("#finProContent").show();    
 				 $("#finProHistory").hide(); 
 				 $("#finProDescription").hide(); 
 			 }else if("history" == $(this).attr("id")){
 				 //选择的元素是“投资记录”
 				 $("#finProHistory").show();  
 				 $("#finProContent").hide();    
 				 $("#finProDescription").hide(); 
				 var borrowId = $("#borrowId").val();
				 /*分页*/ 
					$("#page").page({
						pageSize : 10,
						showInfo : false,
						showJump : true,
						showPageSizes : false,
						loadFirstPage : true,  // 自动加载第一页
						remote : {
							url : BASE_PATH+"/business/financialProductsManageController/index/selectBizBorrowDetailByBorrowId.shtml?borrowId="+borrowId,
							params : null,
							success : function(result, pageIndex) {
								var html = "";
								if(result.detailList ==null ){
									html = "<div>暂无投资记录</div>"
									$(".finProHistory").append(html);
									return;
								}else{
									html ="<div class='invest_table'>" +
										"<table width='100%' border='0' cellspacing='0' cellpadding='0' class='esc_t mt30'>"+ 
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
										var investmentTypeName = detail.investmentTypeName==null?'':detail.investmentTypeName
										  html+="	<tr> "+
												"		<td>"+detail.privateName+"</td>"+
												"		<td>"+investmentTypeName+"</td>"+
												"		<td>"+escfutil.formatCurrency(detail.investmentAmount)+"</td>"+
												"		<td>"+detail.investmentTime+"</td>"+
												"		<td>成功</td>"+
												"	</tr> ";
									}	 		
									html+="</table></div>"
										$(".finProHistory").html("");
										$(".finProHistory").append(html);
								}
							},
							pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
							pageSizeName : 'pageSize'//请求参数，每页数量
						}
					});  
 			 }
 		});
 		//点击立即投资
 		$("#nowInvest").click(function(){ 
 			var investNumber = $("#investNumber").val();//投资金额
 			var borrowId = $("#borrowId").val();//借款编号
 			var startMoney = formatNull($("#startMoney").val());//起投金额  
 			var expectAmount = formatNull($("#expectAmountVal").val());//预期收益
 			var investReward = formatNull($("#investRewardVal").val());//投资奖励
 			var eplanIntegral = formatNull($("#eplanIntegralVal").val());//可获积分
 			var availableAmount = formatNull($("#availableAmount").val());//账户可用余额
 			var endMoney = formatNull($("#endMoney").val());//投资上限
// 			if(investNumber-startMoney< 0){
// 				layer.alert("起投金额不能小于："+startMoney+"元");
// 				return;
// 			}  
// 			//投资金额不能大于投资上限
// 			if(endMoney!=0 && endMoney!=undefined){
// 				if(endMoney-investNumber<0){
//	 				layer.alert("金额上限不能大于："+endMoney+"元");
//	 				return;
// 				}
// 			}
 			if($("#agree").attr("val")!="1"){
 				layer.alert("请仔细阅读并同意协议");
 				return;
 			}
 			//投资金额不能大于可用余额
// 			if(availableAmount-investNumber<0){ 
// 				layer.alert("可用余额不足!");
// 				return 
// 			}
 			//iframe层
 			layer.open({
 			    type: 2,
 			    title: '',
 			    closeBtn: 0,
 			    scrollbar: false,
 			    shadeClose: true,
 			    shade: 0.8,
 			    offset: ['10%', '25%'],
 			    area: ['950px', '65%'], 
 			    content: BASE_PATH+"/business/financialProductsManageController/selectFinProdInfoDataPreview.shtml?investNumber="+investNumber+"&borrowId="+borrowId+"&expectAmount="+expectAmount+
 			    	"&investReward="+investReward+"&eplanIntegral="+eplanIntegral, //iframe的url
 			});
 		}) 
 	})

 	/**
 	 * 投资金额输入框 失去光标事件
 	 * @param obj
 	 */
 	function count(obj){
		if($(obj).val() == "" || $(obj).val() == null || $(obj).val() ==undefined || $(obj).val() == 0){return }
		getEplanIntegral(obj); //获得积分
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
			async: false,
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
			async: false,
			success : function(data) {
				 $("#expectAmount").text("￥"+escfutil.formatCurrency(data.eplanIntegral));//预期收益
				 $("#investReward").text("￥"+escfutil.formatCurrency(data.investmentReward));//投资奖励
				 $("#expectAmountVal").val(data.eplanIntegral);//预期收益
				 $("#investRewardVal").val(data.investmentReward);//投资奖励
			},
			error : function() {
				layer.alert("查询预期收益接口异常!"); 
			}
		});
	}
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
	 