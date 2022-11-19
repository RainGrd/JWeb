/**
 * 邀请有奖js类
 */
var newrReferralCode;
var myReferralCode;//我的邀请码
var rpgjt_prize = {
	// 加载数据
	loadData:function(){
		var url = BASE_PATH + "login/userController/selectReferralCodeByCustd.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{data:""},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		myReferralCode =  data.data.referralCode;
		    		$(".ccc").html(myReferralCode);
		    		$("#copyTxt").html(BASE_PATH+'login/userController/toRegistered.shtml?referralCode='+myReferralCode);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
		//加载邀请有奖的广告位
		rpgjt_prize.selectAdvCloum();
		//加载邀请有奖的栏目位
		rpgjt_prize.selectPrizeCloum();
	},
	
	//加载邀请有奖的广告位
	selectAdvCloum:function(){
		var url = BASE_PATH + "login/userController/selectAdvCloum.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status=="200"){
		    		var fileUrl = BASE_PATH+data.data[0].fileUrl;
		    		$(".yaoImage_").attr("src",fileUrl);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	
	//加载邀请有奖的栏目位
	selectPrizeCloum:function(){
		var url = BASE_PATH + "login/userController/selectPrizeCloum.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status=="200"){
		    		$(".yqyj_").html(data.data.content);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	
	//我分享过的合伙人
	mySharePartner:function(){
		var url = BASE_PATH + "login/userController/selectMySharePartner.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{data:""},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		rpgjt_prize.mySharePartnerList(data.data);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	//分享的合伙人列表
	mySharePartnerList:function(data){
		var html="";
		var str="";
		var investmentAccount="";
		if(data!=null && data.length > 0){
			for(var i =0;i<data.length;i++){
				var phone="未验证手机";
				if(data[i].phoneNo!=null){
					phone="已验证手机";
				}
				//如果用户名不存在就用手机号码
				if(data[i].customerName==null || data[i].customerName==""){
					str= data[i].phoneNo;
				}else{
					str=data[i].customerName;
				}
				//如果参数过活动，有推荐奖励的话  就用推荐奖励的值，否则是0
				if(data[i].investmentAccount !=null && data[i].investmentAccount != ""){
					investmentAccount = data[i].investmentAccount;
				}else{
					investmentAccount = 0;
				}
				html +='<tr>'+'<td class="size14" style="text-align:center;">'+str+'</td>'+
							  '<td class="size14" style="text-align:center;">'+data[i].registrationTime+'</td>'+
							  '<td class="size14" style="text-align:center;">'+phone+'</td>'+
							  '<td class="size14" style="text-align:center;">'+investmentAccount+'</td>'+
					    '</tr>'
			}
		}
		$(".my_share").html(html);
	},
	
	//生成邀请码
	createStatusCode:function(referralCode){
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"login/userController/generateInvitaCode.shtml",
	    	data:{"referralCode":referralCode},
			dataType: "json",
			async:false, 
		    success: function(data){
		    	if(data.message.status ==200){
		    		newrReferralCode= data.data;
		    	}else{
		    		layer.msg('生成邀请码失败啦!', {icon: 5});
		    	}
			}
		});
	},
	//跳转到注册页面(复制链接分享)
	openUserRegisteredWin:function(){
		//flag=1 就是从我邀请有奖进去的，推荐人不能编辑
		var flag="1";
		window.location.href = BASE_PATH+'login/userController/toRegistered.shtml?referralCode='+myReferralCode+'&flag='+flag
	},
}

$(document).ready(function(){
	rpgjt_prize.loadData();
	rpgjt_prize.mySharePartner();
});