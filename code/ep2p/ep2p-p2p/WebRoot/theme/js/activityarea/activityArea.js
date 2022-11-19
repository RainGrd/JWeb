/**
 * 活动专区JS类
 */
var activityArea = {
	// 初始化活动专区信息
	initActivityArea:function(){
		var url = BASE_PATH + "activityArea/acticityAreaController/selectShowActicityArea.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		activityArea.activityAreaDarenCharts(data.data,1);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	// 加载活动专区数据
	loadActivityArea:function(){
		var pageIndex = $("#pageIndex").val();
		var url = BASE_PATH + "activityArea/acticityAreaController/selectLoadActicityArea.shtml?pageIndex="+pageIndex;
		$.ajax({
			type: "POST",
	    	url : url,
	    	async : false ,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		activityArea.activityAreaDarenCharts(data.data,2);
		    		// 赋值
		    		$("#pageIndex").val(data.pageIndex);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	// 活动专区数据绑定
	activityAreaDarenCharts:function(data,type){
		var html = '';
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0;i<data.length;i++){
				var butStr = "";
				if(data[i].activityType == 1){
					// 根据活动ID查询当前登录的用户是否领取了当前活动的红包,如果领取了红包,就不让他再领取

//					butStr = '<a href="#" class="btn_gray">已领取</a></p> ';
					if(data[i].activityStatus == 1){
						butStr = '<a href="#" class="btn">抢红包</a></p> ';
					}else if(data[i].activityStatus == 2){
						butStr = '<a href="#" class="btn_gray">已结束</a></p> ';
					}
				}else if(data[i].activityType == 2){
					// 根据活动ID查询当前登录的用户是否领取了当前活动的加息劵,如果领取了加息劵,就不让他再领取

//					butStr = '<a href="#" class="btn_gray"></a>已领取</p> ';
					if(data[i].activityStatus == 1){
						butStr = '<a href="#" class="btn">领取加息劵</a></p> ';
					}else if(data[i].activityStatus == 2){
						butStr = '<a href="#" class="btn_gray">已结束</a></p> ';
					}
				}else{
					if(data[i].activityStatus == 1){
						butStr = '<a href="'+data[i].activityUrl+'" class="btn">查看详情</a></p> ';
					} else {
						butStr = '<a href="'+data[i].activityUrl+'" class="btn_gray">已结束</a></p> ';
					}
				}
				html += '<div class="al_l_list"> ' + 
					 '<span style="display: inline-block;float:left; width: 656px;height: 179px;"><img src="'+basePath+data[i].activityImage+'" alt="ss" class="al_l_list_img" /> </span>'+
					 '<div class="al_l_list_r fr_"> '+ 
					 '<p class="mt15 colorDarkBlue size16 pr15">' + data[i].activityName + '</p> ' +
					 '<p class=" c2980b9 size12 mt5">'+ 
					 '<img src="/ep2p/theme/default/images/ac_3.png" class="mr10 mt1 fl"/>'+ activityArea.formatterDate(data[i].activityStartDate) + 
					 '-' + activityArea.formatterDate(data[i].activityEndDate) + '</p> '+
					 '<p class="mt20"> ' + butStr +
					 '</div> '+
					 '</div> ';
			}
		}
		// 判断是什么类型
		if(type == 1){
			// 如果是初始化,就是直接覆盖
			$("#activityArea_div").html(html);
		}else{
			// 如果是加载,就需要追加数据
			$("#activityArea_div").append(html);
		}
	},
	// 初始化签到达人榜信息
	initDrb:function(){
		var url = BASE_PATH + "activityArea/acticityAreaController/selectSignInDarenCharts.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		activityArea.selectSignInDarenCharts(data.data);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	// 签到达人榜数据绑定
	selectSignInDarenCharts:function(data){
		var html = '<i class="i_ pos-a size12 c2980b9 i bgffffff">签到排行榜</i>';
		// 判断是否为空
		if(data != null && data.length>0){
			// 循环拼接数据
			for(var i=0;i<data.length;i++){
				var imageUrl = "";
				if(null == data[i].imageUrl || data[i].imageUrl == ""){
					imageUrl = "theme/default/images/cont_vip_i.png";
				}else{
					imageUrl = data[i].imageUrl;
				}
				
				if(i==0){
					html += '<p class="lh40 ac_r_list_p mt9">'+
					 '<i class="i_ ac_r_list_i">'+(i+1)+'</i><img src="'+BASE_PATH+imageUrl+'" class="ac_r_list_img" />'+ 
					 '<a class="colorDarkBlue w95 cus_p">'+data[i].sname+'</a>'+ 
					 '<i class="i_ fr_ bdc3c7"> '+data[i].signInCount+'天</i>' +
					 '</p> ';
				}else{
					html += '<p class="lh40 ac_r_list_p">'+
					 '<i class="i_ ac_r_list_i">'+(i+1)+'</i><img src="'+BASE_PATH+imageUrl+'" class="ac_r_list_img" />'+ 
					 '<a class="colorDarkBlue w95 cus_p">'+data[i].sname+'</a>'+ 
					 '<i class="i_ fr_ bdc3c7"> '+data[i].signInCount+'天</i>' +
					 '</p> ';
				}
				
			}
		}
		$("#drb_div").html(html);
	},
	// 点击签到
	signIn:function(){
		var url = BASE_PATH + "activityArea/acticityAreaController/custSignInEdit.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		// 判断是否签到
		    		if(data.message.message == 500){
		    			// 弹出错误提示
		    			alert("请勿重复签到,谢谢");
		    		}else{
		    			// 重新加载客户积分信息
		    			activityArea.initPersonalIntegral();
		    			// 初始化签到达人榜
		    			activityArea.initDrb();
		    		}
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	// 初始化个人信息
	initPersonalIntegral:function(){
		// 获取用户姓名,用户积分,是否签到 
		var url = BASE_PATH + "activityArea/acticityAreaController/selectPersonalIntegral.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		var html = "";
		    		// 拼接
		    		html = '<p class="mt10 tl size14">Hi <a class="i_ c2980b9 cus_p ml5" href="'+basePath+'userinfo/centerController/toUserCenterHome.shtml" >'+data.sname+'</a>'+'</p>' +
		    		'<p class="size12 oh"><span class="fl_ inline_block">积分:'+data.availablePoint+'</span>' +
		    		'<a class="fr_ inline_block cus_p" style="color:#bdc3c7;" href="'+basePath+'help/helpCenterController/index/toHelpCenterPage.shtml">如何赚取积分？</a>' +'</p>' +
		    		'<p class="mt15">';
		    		if(data.isSignln == 0){
		    			// 如果是未签到
		    			html += '<a class="btn_samll_h" href="#" onclick="activityArea.signIn()">立即签到</a>'+
		    				'<a class="btn_samll ml10" href="'+basePath+'securityCenter/bankController/toWelfareList.shtml?welfareType=2">兑换</a>' +
		    				'</p><p style="color:#2980b9;line-height:26px;">APP签到额外奖励3积分</p><p class="kong30"></p>';
		    		}else{
		    			// 如果是已签到
		    			html += '<a class="btn_samll_gray" href="#" >已签到'+data.signInSum+'天</a>'+
		    				'<a class="btn_samll ml10" href="'+basePath+'securityCenter/bankController/toWelfareList.shtml?welfareType=2">兑换</a>' +
		    				'</p><p style="color:#2980b9;line-height:26px;">APP签到额外奖励3积分</p><p class="kong30"></p>';
		    		}
		    		// 赋值
		    		$("#personalIntegral").html(html);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	// 格式化日期
	formatterDate:function(str){
		// 非空排除
		if(null == str || str == ""){
			return "";
		}
		// 获取日期对象
		var date = new Date(str);
		// 获取时间的年月日
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y + '年' + (m<10?('0'+m):m) + '月' + (d<10?('0'+d):d) + '日';
	}

}

$(document).ready(function(){
	// 判断用户是否登录
	var cusTomerId = $("#cusTomerId").val();
	// 只有登录的用户才可以查询自己的积分信息已经签到
	if(null != cusTomerId && cusTomerId != ""){
		// 初始化个人积分信息
		activityArea.initPersonalIntegral();
	}
	
	// 初始化活动专区数据
	activityArea.initActivityArea();
	
	// 初始化签到达人榜
	activityArea.initDrb();
	
	// 加载时触发 
	$(window).on("scroll", function() {
		console.log( $(window).height())
		if ($(".ac_l").height() <= $(window).scrollTop() + $(window).height()) {
			activityArea.loadActivityArea();
		}
	})
});