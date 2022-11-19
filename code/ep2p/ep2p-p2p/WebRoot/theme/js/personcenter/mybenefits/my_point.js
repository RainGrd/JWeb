/**
 * 个人中心_我的积分JS类
 */
var myPoint = {
	// 初始化
	initData:function(flag){
		if(flag==null || flag == ''){
			flag = "1";
		}
		myWelfare.loadData(flag);
	},
	
	// 加载数据
	loadData:function(flag){
		var url = "";
		if(flag == '1'){
			url = BASE_PATH + "securityCenter/bankController/selectMyCoupon.shtml";
		}else{
//			url = BASE_PATH + "securityCenter/bankController/selectMyPoint.shtml";
			url = BASE_PATH + "securityCenter/bankController/toMyPointList.shtml";
		}
		myWelfare.init(url,flag);
	},
	init:function(url,flag){
		
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
		    		   myWelfare.myPoint(data);
		    	   }
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	//赠劵
	myCoupon:function(data){
		var fId = "";
		if(null != data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var type=data[i].type == 1?"加息劵":"体验金劵";
				var types=data[i].type == 1?"加息":"体验金";
				var typess=data[i].type == 1?"单笔投资满5000元可用":"系统发布的体验标中使用";
				fId +='<div>' +
					     '<ul>' +
							'<li>' +type+'</li>'+
							'<li>'+types +data[i].investAwardValue+'</li>'+
							'<li>'+typess+'</li>'+
							'<li>' +data[i].endTime+'到期</li>'+
					 	'</ul>'+
							'<a href="javascript:void(0);">使用'+'</a>'+
					'</div>' +'<br/>';
			}
		}
		$("#div_01").html(fId);
	},
}

$(document).ready(function(){
	// 我的福利 1:我的赠劵 2：我的积分
	myWelfare.initData("1");
	
});