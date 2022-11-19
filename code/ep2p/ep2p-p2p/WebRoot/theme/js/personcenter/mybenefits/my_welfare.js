/**
 * 个人中心_我的福利JS类
 */
var myWelfare = {
	// 初始化
	initData:function(flag){
		if(flag==null || flag == ''){
			flag = "1";
		}
		myWelfare.loadData(flag);
	},
	// 加载数据
	loadData:function(flag){
		var pageElId='my_zj';
		var url = "";
		if(flag == '1'){
			// 我的赠劵
			url = BASE_PATH + "securityCenter/bankController/selectMyCoupon.shtml?flag="+flag;
			myWelfare.initPage(url,pageElId);
		}else{
			// 我的积分
			url = BASE_PATH + "securityCenter/bankController/toMyPointList.shtml";
			myWelfare.init(url,flag);
		}
		
	},
	init:function(url,flag,pageElId){
		
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{data:"{flag:"+flag+"}"},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    	   if(flag==1){
		    		   myWelfare.myCoupon(data.data);
		    	   }else{
		    		   myWelfare.myPoint(data.data);
		    	   }
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	// 初始化分页
	initPage:function(url,pageElId){
		$("#"+pageElId).page({
			pageSize : 12,    // 测试设置为1
			showInfo : false,
			showJump : false,
			showPageSizes : false,
			loadFirstPage : true,  // 自动加载第一页
			remote : {
				url : url,
				params : "", //条件查询
				success : function(result, pageIndex) {
					if(result.message=='查询成功'){
						myWelfare.myCoupon(result.data);
			    	}
				},
				pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
				pageSizeName : 'pageSize'//请求参数，每页数量
			}
		});
	},
	// 我的积分
	myPoint:function(data){
		$("#div_point").html(" ");
		// 累计积分
		var pointValue = 0 ;
		// 可用积分
		var availablePoint ;
		if(null != data){
			// 获取累计积分
			pointValue = data.pointValue == null ? 0 :data.pointValue;
			// 获取可用积分
			availablePoint = data.availablePoint == null ? 0 :data.availablePoint;
		} 
		// 拼接
		var str = '<span class="fui_ji_a fl_ inline_block fui_bor tc">' +
			'<p class="fui_ji_p1 colorc size24">'+pointValue+'</p>' +
			'<p class="fui_ji_p2 size14 colorDarkBlue">累计积分</p></span>' +
			'<span class="fui_ji_a fl_ inline_block tc">' +
			'<p class="fui_ji_p1 colorc size24">'+availablePoint+'</p>' + 
			'<p class="fui_ji_p2 size14 colorDarkBlue">可用积分</p></span>';
		$("#div_point").html(str);
	},
	// 赠劵
	myCoupon:function(data){
		$("#div_zj").html("");
		var fId = "";
		debugger;
		if(null != data && data.length > 0){
			var linkType = data[0].linkType;// 系统参数的值   1：e计划，2：e首房/e抵押
			for(var i=0;i<data.length;i++){
				var couponType=data[i].couponType == '仅限使用于允许使用加息券的投资项目' ?"加息劵":"体验金劵";
				var useStatus = data[i].useStatus;//useStatus 1:已使用  2：未使用
				var dataTime = data[i].dataTime;
				//体验金未使用
				if(couponType=='体验金劵' && useStatus =='2'){
					var arr = new Array();
					var amount = data[i].amount;
					arr = amount.split(".");
					var amount1 = arr[0].substring(0, 9);
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_ti mt20 pos-r">'+
						    '<p class=" size30 fffe80 fui_zj_listp2 tc mt40">'+'￥'+amount1+'</p>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">'+'还有'+data[i].dataTime+'天过期'+'</p>'+
						    '<a href="'+BASE_PATH+'activityArea/acticityAreaController/index/toActivityArea.shtml" class="inline_block fui_zj_list_a pos-a">未使用</a>'+
						    '</span>'
				}
				//体验金已使用
				else if(couponType=='体验金劵' && useStatus =='1'){
					var arr = new Array();
					var amount = data[i].amount;
					arr = amount.split(".");
					var amount1 = arr[0].substring(0, 9);
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_ti_ mt20 pos-r">'+
						    '<p class=" size30 ffffff fui_zj_listp2 tc mt40">'+'￥'+amount1+'</p>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">'+'还有'+data[i].dataTime+'天过期'+'</p>'+
						    '<a href="'+BASE_PATH+'business/financialProductsManageController/index/toFinProdPage.shtml" class="inline_block fui_zj_list_a pos-a">已过期</a>'+
						    '</span>'
				}
				//加息券未使用,  linkType=1 点击使用跳到e计划  与下面唯一的区别就是根据系统参数值跳不同的页面
				else if(couponType=='加息劵' && useStatus=='2' && linkType == '1'){
					var amount = data[i].amount * 100;
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_jia mt20 pos-r">'+
						    '<p class=" size30 fffe80 fui_zj_listp2 tc mt40">'+'+'+amount+'%'+'</p>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">'+'还有'+data[i].dataTime+'天过期'+'</p>'+
						    '<a href="'+BASE_PATH+'business/financialProductsManageController/index/toFinProdPage.shtml" class="inline_block fui_zj_list_a pos-a">未使用</a>'+
						    '</span>'
				}
				//加息券未使用,  linkType=2 点击使用跳到e首房/e抵押
				else if(couponType=='加息劵' && useStatus=='2' && linkType == '2'){
					var amount = data[i].amount * 100;
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_jia mt20 pos-r">'+
						    '<p class=" size30 fffe80 fui_zj_listp2 tc mt40">'+'+'+amount+'%'+'</p>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tl pl15 mt30">'+'还有'+data[i].dataTime+'天过期'+'</p>'+
						    '<a href="'+BASE_PATH+'business/optionalInvestController/index/toOptionalInvestList.shtml" class="inline_block fui_zj_list_a pos-a">未使用</a>'+
						    '</span>'
				}
				//加息已使用
				else{
					var amount = data[i].amount * 100;
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_jia_ mt20 pos-r">'+
						    '<p class="size14 ffffff fui_zj_listp1 pl10">'+'</p>'+
						    '<p class=" size30 fffe80 fui_zj_listp2 tc amount mt40">'+'+'+amount+'%'+'</p>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tc">'+'</p>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tc">'+'</p>'+
						    '<div class="inline_block kong15">'+'</div>'+
						    '<p class=" size12 ffffff fui_zj_listp3 tl pl15 dateTime mt40">'+'还有'+data[i].dataTime+'天过期'+'</p>'+
						    '<a href="'+BASE_PATH+'business/financialProductsManageController/index/toFinProdPage.shtml" class="inline_block fui_zj_list_a pos-a">已使用</a>'+
						    '</span>'
				}
				
			}
		}
		$("#div_zj").html(fId);
	},
	
	
	// 积分明细的拼接
	myPonintDetail:function(data){
		var html = "";
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0;i<data.length;i++){
				html += '<tr>' ;
				// 判断是什么类型
				if(data[i].pointType != 2 && data[i].pointType != 3 && data[i].pointType != 4 && data[i].pointType != 5 ){
					// 是获得的积分情况
					html += '<td > '+data[i].pointValue+' </td>'+ 
					 	'<td> '+data[i].availablePoint+'  </td>'+
					 	'<td> '+data[i].happenTime+' </td>'+
					 	'<td> '+myWelfare.formatterPointType(data[i].pointType)+'  </td>'+
					 	'<td> '+data[i].potWatDesc+' </td>'+ 
					 	'</tr> ';
				}else{
					// 是兑换的积分情况
					html += '<td class="colorc"> -'+data[i].pointValue+' </td>'+ 
						'<td> '+data[i].availablePoint+'  </td>'+
						'<td> '+data[i].happenTime+' </td>'+
						'<td> '+myWelfare.formatterPointType(data[i].pointType)+'  </td>'+
						'<td> '+data[i].potWatDesc+' </td>'+ 
						'</tr> ';
				}
			}
		}
		// 填充
		$("#pointDetail_div").html(html);
	},
	// 格式化积分类型
	formatterPointType:function(type){
		if(null == type || type == ""){
			return "";
		}
		// 调用获取积分类型的方法
		var dictContName = escfutil.formatDictionaryContentName('POINT_TYPE',type);
		return dictContName;
	},
	// 积分明细查询
	pointDetail:function(pointType){
		// 销毁
		$("#pointDetailPage").page('destroy');
		// URL 
		var url = BASE_PATH + "securityCenter/bankController/selectUserPointDetail.shtml";
		$("#pointType").val(pointType);
		var params = $("#pointDetailForm").serializeArray();
		myWelfare.initPag(url, params);
	},
	// 初始化分页
	initPag:function(url,params){
		$("#pointDetailPage").page({
			pageSize : 10,    // 测试设置为1
			showInfo : false,
			showJump : false,
			showPageSizes : false,
			loadFirstPage : true,  // 自动加载第一页
			remote : {
				url : url,
				params : params, //条件查询
				success : function(result, pageIndex) {
					myWelfare.myPonintDetail(result.data);
				},
				pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
				pageSizeName : 'pageSize'//请求参数，每页数量
			}
		});
	},
	// 福利-panl-切换
	qieHuan:function(a,b,c){
		$(a).click(function(){
			var eq = $(this).index(a);
			$(a).removeClass(c).eq(eq).addClass(c);
			$(b).addClass("none").eq(eq).removeClass("none");
			
			// 判断是那个调用的
			if(a == ".fui_ta"){
				// 我的赠卷or我的积分
				if(eq == 0){
					// 我的赠卷
					myWelfare.loadData("1");
				}else{
					// 我的积分
					myWelfare.loadData("2");
				}
			}else if(a == ".fui_j_x"){
				// 兑换or积分明细查询
				if(eq == 1){
					// 积分明细查询
					myWelfare.pointDetail();
				}
			}else if(a == ".fui_juan_nav span"){
				// 兑换话费or兑换加息劵or兑换VIP
				$(".fui_juan_lists").attr("style","");
				$(b).eq(eq).children(".fui_juan_list").children("span").eq(0).css("background","url("+ basePath +"theme/default/images/fu_2.png) no-repeat");
				$(".fui_juan_xuan_js").html($(b).eq(eq).children(".fui_juan_list").children("span").eq(0).children(".fui_juan_list_p1").html()+"兑换"+$(b).eq(eq).children(".fui_juan_list").children("span").eq(0).children(".fui_juan_list_p2").html())
				
				// 默认关闭全部提示框
				$(".fui_juan_bd .input_tis").addClass("none_");
				// 默认全部栏目隐藏
				$(".fui_juan_bd p").addClass("none");
				// 兑换话费初始化打开
				$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none");
				$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none_");
				$(".fui_juan_bd .duihuan_chongzhi").removeClass("none");
				$(".fui_juan_bd .duihuan_chongzhi").removeClass("none_");
				// 兑换加息卷初始化打开
				$(".fui_juan_bd .duihuan_jiaxi_y").removeClass("none");
				$(".fui_juan_bd .duihuan_jiaxi_").removeClass("none");
				// 兑换vip初始打开
				$(".fui_juan_bd .duihuan_vip_y").removeClass("none");
				$(".fui_juan_bd .duihuan_vip").removeClass("none");
				// 兑换现金初始打开
				$(".fui_juan_bd .duihuan_xianjin_y").removeClass("none");
				$(".fui_juan_bd .duihuan_xianjin_").removeClass("none");
				// mo默认表单清空
				$(".fui_c input").val("");
				$("#exchangeType").val($(b).eq(eq).children(".fui_juan_list").children("span").eq(0).attr("v_"));
				
			}
		});
	},
	// 判断手机号码是否输入正确
	checkPhoneNo:function(){
		// 获取输入的手机号码
		var phoneNo = $("#phoneNo1").val();
		// 验证手机号码是否正确的正则表达式
		var reg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
		// 判断
		if(phoneNo == ""){
			// 如果没有输入手机号码
			$("#promptError_1_1").removeClass("none_");
			$("#promptError_1_1").html("手机号码不能为空");
		}else if(!reg.test(phoneNo)){
			// 输入错误的手机号码
			$("#promptError_1_1").removeClass("none_");
			$("#promptError_1_1").html("手机格式不正确");
		}else{
			// 隐藏,显示
			$(".duihuan_chongzhi").addClass("none_");
			$(".duihuan_chongzhi_q").removeClass("none_");
			$(".duihuan_chongzhi_q").removeClass("none");
			// 赋值
			$("#phoneNo2").html(phoneNo);
		}
	},
	// 校验交易密码是否为空
	checkTradePassword:function(tradePassword,promptErrorId,url){
		// 校验交易密码
		if( null == tradePassword || tradePassword.trim() == ''){
			// 密码不能为空
			// 去掉隐藏提示框的样式
			$(promptErrorId).removeClass("none_");
			$(promptErrorId).removeClass("none");
			$(promptErrorId).text(USER_PWD_MSG.TRADE_BLANK_MSG);
			$(promptErrorId).show();
			return;
		}else{
			// 如果交易密码不为空,就调用兑换的方法
			myWelfare.exchange(url,promptErrorId);
		}
	},
	// 兑换
	exchange:function(url,promptErrorId){
		// 请求后台兑换
		$.ajax({
			type: "POST",
	    	url : url,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status == 200 ){
		    		if(promptErrorId == "#promptError_1"){
		    			// 如果是兑换话费
		    			$(".duihuan_chongzhi_q").addClass("none_");
		    			$(".duihuan_chongzhi_xm").addClass("none_");
		    			$(".duihuan_chongzhi_xm").addClass("none");
		    			$(".chongzhi_y").removeClass("none_");
		    			$(".chongzhi_y").removeClass("none");
		    		}else if(promptErrorId == "#promptError_2"){
		    			// 如果是兑换加息劵
		    			$(".duihuan_jiaxi_y").addClass("none");
		    			$(".duihuan_jiaxi_m").addClass("none");
		    			$(".duihuan_jiaxi_qr").addClass("none");
		    			$(".duihuan_jiaxi_y_").removeClass("none");
		    		}else if(promptErrorId == "#promptError_3"){
		    			// 如果是兑换VIP
		    			$(".duihuan_vip_").addClass("none");
		    			$(".duihuan_vip_y").addClass("none");
		    			$(".duihuan_vip_y_").removeClass("none");
		    			// 设置VIP到期时间 
		    			$(".duihuan_vip_y_ span i").html(data.vipEndTime);
		    		}else if(promptErrorId == "#promptError_4"){
		    			// 如果是兑换现金
		    			$(".duihuan_xianjin_q").addClass("none");
		    			$(".duihuan_xianjin_y").addClass("none");
		    			$(".duihuan_xianjin_y_").removeClass("none");
		    		}
		    	}else{
		    		// 兑换失败,交易密码错误
		    		if(data.errorCode == 'tradePwdError'){
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).text(USER_PWD_MSG.TRADE_ERROR_MSG+"（还可以输入"+data.num+"次）");
		    		}else if(data.errorCode == 'tradePwdBank'){
		    			// 您还没有设置交易密码，去设置。
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).html(USER_PWD_MSG.TO_SET_TRADE_PWD);
		    		}else if(data.errorCode == "pointNotEnough"){
		    			// 积分不够
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).html(POINT_EXCHANGE.POINT_NOT_ENOUGH);
		    		}else if(data.errorCode == "tradeFreeze"){
		    			// 交易密码被锁
		    			// 去掉隐藏提示框的样式
		    			$(promptErrorId).removeClass("none_");
		    			$(promptErrorId).show();
		    			$(promptErrorId).html(POINT_EXCHANGE.EXCHANGE_PASSWROD_FREEZE+data.t+"分钟!");
		    		}else{
			    		// 兑换失败弹窗
		    			layer.msg(CLAIM_TRANSFER.EXCHANGE_ERROR, {icon: 5});
		    		}
		    	}
			}
		}); 
	},
	// 兑换话费
	exchangeTelephoneFare:function(){
		// 获取兑换的哪一种类型
		var exchangeType = $("#exchangeType").val();
		// 获取交易密码
		var tradePassword = $("#tradePassword_1").val();
		// 获取需要充值的手机号码
		var phoneNo = $("#phoneNo1").val();
		// url
		var url = BASE_PATH+"usercenter/exchangeController/exchangeTelephoneFare.shtml?exchangeType="+exchangeType+"&tradePassword="+tradePassword+"&phoneNo="+phoneNo;
		// 调用校验交易密码是否为空的函数
		myWelfare.checkTradePassword(tradePassword,"#promptError_1",url);
	},
	// 兑换加息劵
	exchangeInterestTicket:function(){
		// 获取兑换的哪一种类型
		var exchangeType = $("#exchangeType").val();
		// 获取交易密码
		var tradePassword = $("#tradePassword_2").val();
		// url
		var url = BASE_PATH+"usercenter/exchangeController/exchangeInterestTicket.shtml?exchangeType="+exchangeType+"&tradePassword="+tradePassword;
		// 调用校验交易密码是否为空的函数
		myWelfare.checkTradePassword(tradePassword,"#promptError_2",url);
	},
	// 兑换VIP
	exchangeVIP:function(){
		// 获取兑换的哪一种类型
		var exchangeType = $("#exchangeType").val();
		// 获取交易密码
		var tradePassword = $("#tradePassword_3").val();
		// url
		var url = BASE_PATH+"usercenter/exchangeController/exchangeVIP.shtml?exchangeType="+exchangeType+"&tradePassword="+tradePassword;
		// 调用校验交易密码是否为空的函数
		myWelfare.checkTradePassword(tradePassword,"#promptError_3",url);
	},
	// 兑换现金
	exchangeCash:function(){
		// 获取兑换的哪一种类型
		var exchangeType = $("#exchangeType").val();
		// 获取交易密码
		var tradePassword = $("#tradePassword_4").val();
		// url
		var url = BASE_PATH+"usercenter/exchangeController/exchangeCash.shtml?exchangeType="+exchangeType+"&tradePassword="+tradePassword;
		// 调用校验交易密码是否为空的函数
		myWelfare.checkTradePassword(tradePassword,"#promptError_4",url);
	},

	//我的赠劵，可使用的
	canUse:function(flag){
		debugger;
		/**$("#pointDetailPage").page('destroy');
		var pageElId='my_zj';
		var url = "";
			// 我的赠劵
			url = BASE_PATH + "securityCenter/bankController/selectMyCoupon.shtml?flag="+flag;
			myWelfare.initPage(url,pageElId);**/
		var url = BASE_PATH + "securityCenter/bankController/selectMyCoupon.shtml?flag="+flag;
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{},
			dataType: "json",
		    success: function(data){
		    	debugger;
		    	if(data.message=="查询成功"){
		    		if(flag=='1'){
		    			myWelfare.myCoupon(data.data);
		    		}else{
		    			//已失效
		    			myWelfare.expired(data.data);
		    		}
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	
	//已失效
	expired:function(data){
		$("#div_zj").html("");
		var fId = "";
		if(null != data && data.length > 0){
			var linkType = data[0].linkType;// 系统参数的值   1：e计划，2：e首房/e抵押
			for(var i=0;i<data.length;i++){
				var couponType=data[i].couponType == '仅限使用于允许使用加息券的投资项目' ?"加息劵":"体验金劵";
				var useStatus = data[i].useStatus;//useStatus 1:已使用  2：未使用
				var dataTime = data[i].dataTime;//如果小于0 说明已过期
				var amount = data[i].amount;
				arr = amount.split(".");
				var amount1 = arr[0].substring(0, 9);
				debugger;
				//体验金 已过期 未使用
				if(couponType=="体验金劵" && dataTime < 0 && useStatus=='2'){
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_ti_ mt20 pos-r">'+
						    '<p class=" size30 ffffff fui_zj_listp2 tc mt40">'+'￥'+amount1+'</p>'+
						    '<a href="#" class="inline_block fui_zj_list_a pos-a">未使用</a>'+
						    '</span>'
				}
				//体验金 已过期 已使用
				else if(couponType=="体验金劵" && dataTime < 0 && useStatus=='1'){
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_ti_ mt20 pos-r">'+
						    '<p class=" size30 ffffff fui_zj_listp2 tc mt40">'+'￥'+amount1+'</p>'+
						    '<a href="#" class="inline_block fui_zj_list_a_ pos-a">已使用</a>'+
						    '</span>'
				}
				//加息劵 已过期 未使用
				else if(couponType=="加息劵" && dataTime < 0 && useStatus=='2'){
					var amount = data[i].amount * 100;
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_jia_ mt20 pos-r">'+
						    '<p class=" size30 ffffff fui_zj_listp2 tc mt40">'+'+'+amount+'%'+'</p>'+
						    '<a href="'+BASE_PATH+'business/financialProductsManageController/index/toFinProdPage.shtml" class="inline_block fui_zj_list_a pos-a">未使用</a>'+
						    '</span>'
				}
				
				//加息劵 已过期 已使用
				else if(couponType=="加息劵" && dataTime < 0 && useStatus=='1'){
					var amount = data[i].amount * 100;
					fId +=
						'<span class="fui_zj_list inline_block fl_ fui_zj_jia_ mt20 pos-r">'+
						    '<p class=" size30 ffffff fui_zj_listp2 tc mt40">'+'+'+amount+'%'+'</p>'+
						    '<a href="'+BASE_PATH+'business/financialProductsManageController/index/toFinProdPage.shtml" class="inline_block fui_zj_list_a_ pos-a">已使用</a>'+
						    '</span>'
				}
			}
			$("#div_zj").html(fId);
		}
	},
}


$(document).ready(function(){
	var exchangeUrl = BASE_PATH+"personcenter/sysDistionaryContentController/selectByDisctCode.shtml";
	// 初始化显示兑换的信息
	// 初始化兑换话费的显示信息
	var exchangeTelephoneFare = "";
	// 先设置为空
	$("#exchangeTelephoneFare").html("");
	$.ajax({
		type: "POST",
    	url : exchangeUrl+"?dictCode=EXCHANGE_TELEPHONE_FARE",
		dataType: "json",
	    success: function(result){
	    	if(result.message.status == 200){
	    		var data = result.data;
	    		if(data != null && data.length > 0){
		    		for(var i = 0 ; i < data.length ; i++){
		    			if(i == 0){
			    			// 如果是第一个
		    				exchangeTelephoneFare += '<span class="inline_block fui_juan_lists cus_p tc fl_" v_="'+data[i].pid+'" style="background: url('+BASE_PATH+'theme/default/images/fu_2.png) no-repeat;">';
		    				$(".fui_juan_xuan_js").html(data[i].dictContDesc+"积分兑换"+data[i].dictContName+"话费");
		    				$("#exchangeType").val(data[i].pid);
		    			}else if(i != 0){
		    				// 如果不是第一个
		    				exchangeTelephoneFare += '<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="'+data[i].pid+'" >';
		    			}
		    			exchangeTelephoneFare += '<p class="fui_juan_list_p1 size14 ffffff" >'+data[i].dictContDesc+'积分</p>'
	    				+ '<p class="fui_juan_list_p2 size30 fffe80">'+data[i].dictContName+'<i class="size14 i_">话费</i></p>'
	    				+ '<p class="fui_juan_list_p3 size12 ffffff">支持移动、联通、电信</p>'
	    				+ '</span> ';
		    		}
	    		}
	    		$("#exchangeTelephoneFare").html(exchangeTelephoneFare);
	    		
	    		// 兑换话费,单选功能,选择的span标签的值eq等于1,否则等于0
	    	    $(".fui_juan_lists").click(function(){
	    	    	var v_ = $(this).attr("v_");
	    	    	
	    	    	$(".fui_juan_lists").attr("style","");
	    	    	$(".fui_juan_xuan_js").html($(this).children(".fui_juan_list_p1").html()+"兑换"+$(this).children(".fui_juan_list_p2").html());
	    	    	$(this).css("background","url("+ basePath +"theme/default/images/fu_2.png) no-repeat");
	    	    	
	    	    	// 默认关闭全部提示框
	    			$(".fui_juan_bd .input_tis").addClass("none_");
	    			// 默认全部栏目隐藏
	    			$(".fui_juan_bd p").addClass("none");
	    			// 兑换话费初始化打开
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none_");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none_");
	    			// 兑换加息卷初始化打开
	    			$(".fui_juan_bd .duihuan_jiaxi_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_jiaxi_").removeClass("none");
	    			// 兑换vip初始打开
	    			$(".fui_juan_bd .duihuan_vip_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_vip").removeClass("none");
	    			// 兑换现金初始打开
	    			$(".fui_juan_bd .duihuan_xianjin_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_xianjin_").removeClass("none");
	    			// mo默认表单清空
	    			$(".fui_c input").val("");
	    			// 赋值类型
	    	    	$("#exchangeType").val(v_);
	    	    	
	    	    });
	    	}else{
	    		layer.msg("初始化兑换数据失败.", {icon: 5});
	    	}
	    }
	});
	
	// 初始化兑换加息卷的显示信息
	var exchangeInterestTicket = "";
	// 先设置为空
	$("#exchangeInterestTicket").html("");
	$.ajax({
		type: "POST",
    	url : exchangeUrl+"?dictCode=EXCHANGE_INTEREST_TICKET",
		dataType: "json",
	    success: function(result){
	    	if(result.message.status == 200){
	    		var data = result.data;
	    		if(data != null && data.length > 0){
		    		for(var i = 0 ; i < data.length ; i++){
		    			if(i == 0){
			    			// 如果是第一个
		    				exchangeInterestTicket += '<span class="inline_block fui_juan_lists cus_p tc fl_" v_="'+data[i].pid+'" style="background: url('+BASE_PATH+'theme/default/images/fu_2.png) no-repeat;">';
		    				$("#exchangeType").val(data[i].pid);
		    			}else if(i != 0){
		    				// 如果不是第一个
		    				exchangeInterestTicket += '<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="'+data[i].pid+'" >';
		    			}
		    			exchangeInterestTicket += '<p class="fui_juan_list_p1 size14 ffffff">'+data[i].dictContDesc+'积分</p>'
	    				+ '<p class="fui_juan_list_p2 size30 fffe80"><i class="size14 i_">+</i>'+data[i].dictContName+'%<i class="size14 i_">加息卷</i></p>'
	    				+ '<p class="fui_juan_list_p3 size12 ffffff">有效期6个月</p>'
	    				+ '</span> ';
		    		}
	    		}
	    		$("#exchangeInterestTicket").html(exchangeInterestTicket);
	    		
	    		// 兑换话费,单选功能,选择的span标签的值eq等于1,否则等于0
	    	    $(".fui_juan_lists").click(function(){
	    	    	var v_ = $(this).attr("v_");
	    	    	
	    	    	$(".fui_juan_lists").attr("style","");
	    	    	$(".fui_juan_xuan_js").html($(this).children(".fui_juan_list_p1").html()+"兑换"+$(this).children(".fui_juan_list_p2").html());
	    	    	$(this).css("background","url("+ basePath +"theme/default/images/fu_2.png) no-repeat");
	    	    	
	    	    	// 默认关闭全部提示框
	    			$(".fui_juan_bd .input_tis").addClass("none_");
	    			// 默认全部栏目隐藏
	    			$(".fui_juan_bd p").addClass("none");
	    			// 兑换话费初始化打开
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none_");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none_");
	    			// 兑换加息卷初始化打开
	    			$(".fui_juan_bd .duihuan_jiaxi_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_jiaxi_").removeClass("none");
	    			// 兑换vip初始打开
	    			$(".fui_juan_bd .duihuan_vip_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_vip").removeClass("none");
	    			// 兑换现金初始打开
	    			$(".fui_juan_bd .duihuan_xianjin_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_xianjin_").removeClass("none");
	    			// mo默认表单清空
	    			$(".fui_c input").val("");
	    			// 赋值类型
	    	    	$("#exchangeType").val(v_);
	    	    	
	    	    });
	    	}else{
	    		layer.msg("初始化兑换数据失败.", {icon: 5});
	    	}
	    }
	});
	
	// 初始化兑换VIP的显示信息
	var exchangeVIP = "";
	// 先设置为空
	$("#exchangeVIP").html("");
	$.ajax({
		type: "POST",
    	url : exchangeUrl+"?dictCode=EXCHANGE_VIP",
		dataType: "json",
	    success: function(result){
	    	if(result.message.status == 200){
	    		var data = result.data;
	    		if(data != null && data.length > 0){
		    		for(var i = 0 ; i < data.length ; i++){
		    			if(i == 0){
			    			// 如果是第一个
		    				exchangeVIP += '<span class="inline_block fui_juan_lists cus_p tc fl_" v_="'+data[i].pid+'" style="background: url('+BASE_PATH+'theme/default/images/fu_2.png) no-repeat;">';
		    				$("#exchangeType").val(data[i].pid);
		    			}else if(i != 0){
		    				// 如果不是第一个
		    				exchangeVIP += '<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="'+data[i].pid+'" >';
		    			}
		    			exchangeVIP += '<p class="fui_juan_list_p1 size14 ffffff">'+data[i].dictContDesc+'积分</p>'
	    				+ '<p class="fui_juan_list_p2 size30 fffe80">'+data[i].dictContName+'<i class="size14 i_">月VIP</i></p>'
	    				+ '<p class="fui_juan_list_p3 size12 ffffff">可获时长</p>'
	    				+ '</span> ';
		    		}
	    		}
	    		$("#exchangeVIP").html(exchangeVIP);

	    		// 兑换话费,单选功能,选择的span标签的值eq等于1,否则等于0
	    	    $(".fui_juan_lists").click(function(){
	    	    	var v_ = $(this).attr("v_");
	    	    	
	    	    	$(".fui_juan_lists").attr("style","");
	    	    	$(".fui_juan_xuan_js").html($(this).children(".fui_juan_list_p1").html()+"兑换"+$(this).children(".fui_juan_list_p2").html());
	    	    	$(this).css("background","url("+ basePath +"theme/default/images/fu_2.png) no-repeat");
	    	    	
	    	    	// 默认关闭全部提示框
	    			$(".fui_juan_bd .input_tis").addClass("none_");
	    			// 默认全部栏目隐藏
	    			$(".fui_juan_bd p").addClass("none");
	    			// 兑换话费初始化打开
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none_");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none_");
	    			// 兑换加息卷初始化打开
	    			$(".fui_juan_bd .duihuan_jiaxi_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_jiaxi_").removeClass("none");
	    			// 兑换vip初始打开
	    			$(".fui_juan_bd .duihuan_vip_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_vip").removeClass("none");
	    			// 兑换现金初始打开
	    			$(".fui_juan_bd .duihuan_xianjin_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_xianjin_").removeClass("none");
	    			// mo默认表单清空
	    			$(".fui_c input").val("");
	    			// 赋值类型
	    	    	$("#exchangeType").val(v_);
	    	    	
	    	    });
	    	}else{
	    		layer.msg("初始化兑换数据失败.", {icon: 5});
	    	}
	    }
	});
	
	// 初始化兑换现金的显示信息
	var exchangeCash = "";
	// 先设置为空
	$("#exchangeCash").html("");
	$.ajax({
		type: "POST",
    	url : exchangeUrl+"?dictCode=EXCHANGE_CASH",
		dataType: "json",
	    success: function(result){
	    	if(result.message.status == 200){
	    		var data = result.data;
	    		if(data != null && data.length > 0){
		    		for(var i = 0 ; i < data.length ; i++){
		    			if(i == 0){
			    			// 如果是第一个
			    			exchangeCash += '<span class="inline_block fui_juan_lists cus_p tc fl_" v_="'+data[i].pid+'" style="background: url('+BASE_PATH+'theme/default/images/fu_2.png) no-repeat;">';
			    			$("#exchangeType").val(data[i].pid);
		    			}else if(i != 0){
		    				// 如果不是第一个
		    				exchangeCash += '<span class="inline_block fui_juan_lists cus_p tc fl_ ml20" v_="'+data[i].pid+'" >';
		    			}
		    			exchangeCash += '<p class="fui_juan_list_p1 size14 ffffff">'+data[i].dictContDesc+'积分</p>'
	    				+ '<p class="fui_juan_list_p2 size30 fffe80">'+data[i].dictContName+'<i class="size14 i_">元</i></p>'
	    				+ '</span> ';
		    		}
	    		}
	    		$("#exchangeCash").html(exchangeCash);
	    		
	    		// 兑换话费,单选功能,选择的span标签的值eq等于1,否则等于0
	    	    $(".fui_juan_lists").click(function(){
	    	    	var v_ = $(this).attr("v_");
	    	    	
	    	    	$(".fui_juan_lists").attr("style","");
	    	    	$(".fui_juan_xuan_js").html($(this).children(".fui_juan_list_p1").html()+"兑换"+$(this).children(".fui_juan_list_p2").html());
	    	    	$(this).css("background","url("+ basePath +"theme/default/images/fu_2.png) no-repeat");
	    	    	
	    	    	// 默认关闭全部提示框
	    			$(".fui_juan_bd .input_tis").addClass("none_");
	    			// 默认全部栏目隐藏
	    			$(".fui_juan_bd p").addClass("none");
	    			// 兑换话费初始化打开
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi_xm").removeClass("none_");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none");
	    			$(".fui_juan_bd .duihuan_chongzhi").removeClass("none_");
	    			// 兑换加息卷初始化打开
	    			$(".fui_juan_bd .duihuan_jiaxi_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_jiaxi_").removeClass("none");
	    			// 兑换vip初始打开
	    			$(".fui_juan_bd .duihuan_vip_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_vip").removeClass("none");
	    			// 兑换现金初始打开
	    			$(".fui_juan_bd .duihuan_xianjin_y").removeClass("none");
	    			$(".fui_juan_bd .duihuan_xianjin_").removeClass("none");
	    			// mo默认表单清空
	    			$(".fui_c input").val("");
	    			// 赋值类型
	    	    	$("#exchangeType").val(v_);
	    	    	
	    	    });
	    	    
	    	}else{
	    		layer.msg("初始化兑换数据失败.", {icon: 5});
	    	}
	    }
	});

	
	// 参数 1显示我的赠劵 2显示我的积分(默认显示我的赠劵)
	var welfareType = $("#welfareType").val();
	var val_;
	//勾选
	function gougou_(a){
		$(a +" .gou_s").click(function(){
			if($(this).attr("val")==1){
				 val_ = $(this).parent("span").parent("p").siblings('p').children(".s_btn").html();
				$(this).html('');
				$(this).attr("val","0");
				$(this).parent().removeClass("c2980b9");
				$(this).parent("span").parent("p").siblings('p').children(".s_btn").html('<a class="fl_  btn_samll_gray" href="javascript:void(0)">同意协议并兑换</a>');
			}else{
				$(this).html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
				$(this).attr("val","1");
				$(this).parent().addClass("c2980b9");
				$(this).parent("span").parent("p").siblings('p').children(".s_btn").html(val_);
			}
		});
	}
	gougou_(".fui_juan_bd");
	//d兑换加息同意协议并兑换
	$(".duihuan_jiaxi").click(function(){
		 $(".duihuan_jiaxi").addClass("none");
		 $(".duihuan_jiaxi_").addClass("none");
		 $(".duihuan_jiaxi_qr").removeClass("none");
		 $(".duihuan_jiaxi_m").removeClass("none");
	 })
	//兑换vip同意协议并兑换
	$(".duihuan_vip_qr").click(function(){
		 $(".duihuan_vip").addClass("none");
		 $(".duihuan_vip_").removeClass("none");
	 })
	// 兑换现金 同意协议并兑换
	$(".duihuan_xianjin").click(function(){
		 $(".duihuan_xianjin_").addClass("none");
		 $(".duihuan_xianjin_q").removeClass("none");
	 })
	// 切换模块调用,提交按钮标签和切换的容器标签和按钮样式标签  此乃我的证卷和我的积分切换
	myWelfare.qieHuan(".fui_ta",".fui_c","ms_t_se");
	// 切换 积分兑换or积分明细查询
	myWelfare.qieHuan(".fui_j_x",".fui_juan","");
	// 切换模块调用,提交按钮标签和切换的容器标签和按钮样式标签  此乃兑换话费,加息,vip切换
	myWelfare.qieHuan(".fui_juan_nav span",".fui_juan_lists_div","bg297FB9");
	
	if(welfareType=="1"){
		
		$(".fui_t span").eq(0).click();
	}else{
		$(".fui_t span").eq(1).click();
	}
	// 我的福利 1:我的赠劵 2：我的积分
	myWelfare.initData("1");
	myWelfare.canUse("1");
	
	 $(".liu_but").click(function(){
		 	$(".liu_but").addClass("b0b0b0");
		 	$(".liu_but").addClass("bgffffff");
		 	$(this).removeClass("b0b0b0");
		 	$(this).removeClass("bgffffff");
		 });

});
