/**
 * 个人中心_我的银行卡JS类
 */
/*勾选效果*/
function gougou(a,b){
	$(a +" .gou_s").attr("val","0");
	$(b).attr("val","0");
	$(a +" .gou_s").click(function(){
		if($(this).attr("val")==1){
			$(this).html('');
			$(this).attr("val","0");
			$(this).parent().removeClass("c2980b9");
		}else{
			$(this).html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
			$(this).attr("val","1");
			$(this).parent().addClass("c2980b9");
		}
	});
	 $(b).click(function(){
	    	if($(b).attr("val")=="0"){
	    		 $(this).html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
	    		  $(a +" .gou_s").html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
	    		  $(a +" .gou_s").attr("val","1");
	    		  $(b).attr("val","1");
	    	}else{
	    		 $(this).html('');
	    		  $(a +" .gou_s").html('');
	    		  $(a +" .gou_s").attr("val","0");
	    		  $(b).attr("val","0");
	    	}
	    })

}

var myBank = {
	// 加载数据
	loadData:function(){
		var url = BASE_PATH + "securityCenter/bankController/selectBankListByCusId.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{data:""},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		   myBank.myBankInfo(data.data);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	//我的银行卡信息
	myBankInfo:function(data){
		$(".bankPage").html('');
		var bankList = "";
		if(null != data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var pid = data[i].pid;
				var bankcardNo = data[i].bankcardNo;
				bankList='<span class="ka_g_list_ka mr15 mb25 bank1 js_'+i+'">'+
				//添加银行图片,给银行图片添加class ="yin_h_ka" ,自动判断 alt的值 然后自动填充图片--> 
				'<img src="" class="ml10 mt13 yin_h_ka" alt="'+data[i].belongingBank+'"/>' +
				'<i class="ka_g_list_i i_ inline_block size14 tc ffffff ">储蓄卡'+'<i/>'+
				'</span>';
				$(".bankPage").append(bankList);
				var str = '<p class="ka_g_list_p"><span class="ml15 fl_">尾号'+bankcardNo+'</span>';
				str += '<span class="ml15 fr_ c2980b9 mr20 jie_ka_js cus_p" onclick="myBank.unbundling('+"'"+bankcardNo+"'"+','+"'"+pid+"'"+');">解绑</span></p>';
				$(".js_"+i).append(str);
			}
		}
		
		//添加银行图片,给银行图片添加class ="yin_h_ka" ,自动判断 alt的值 然后自动填充图片
		var y_;
		var src_;
		for(var i=0;i<$(".yin_h_ka").length;i++){
			y_ = $(".yin_h_ka").eq(i).attr("alt");
			$(".yin_h_ka").eq(i).attr("src",'/ep2p/theme/default/images/bank'+f_(y_)+'.png');
		}
	},
	
	//添加银行卡
	addBankCare:function(){
		//校验是否实名认证过
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"/securityCenter/bankController/validateIsAttestation.shtml",
	    	data:{"":""},
			dataType: "json",
			async:false, 
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.count >0){
		    			myBank.toAddBankCardPage();
		    		}else{
		    			layer.msg('实名认证后才能添加银行卡!', {icon: 5});
		    		}
		    	}
		    }
		});
	},
	//跳转到添加银行卡页面
	toAddBankCardPage:function(){
		//iframe层
		layer.open({
		    type: 2,
		    title: '',
		    shadeClose: true,
		    shade: 0.8,
		    offset: ['10%', '30%'],
		    area: ['700px', '48%'],
		    content: BASE_PATH+'securityCenter/bankController/toAddBankCardPage.shtml'//iframe的url
		});
	},		
	
	
	//银行卡解绑事件
	unbundling:function(bankCardNo,pid){
		//iframe层
		layer.open({
		    type: 2,
		    title: '',
		    shadeClose: true,
		    shade: 0.8,
		    offset: ['20%', '40%'],
		    area: ['600px', '30%'],
		    content: BASE_PATH+'securityCenter/bankController/toUnbundlingBankPage.shtml?bankCardNo='+bankCardNo+'&pid='+pid //iframe的url
		});
	},
	
}

$(document).ready(function(){
	myBank.loadData();
	gougou(".lh45");

});

var yin =["工商银行","农业银行","建设银行","招商银行","民生银行","交通银行","中国银行","中信银行","广发银行","兴业银行","华夏银行","上海银行","北京银行","光大银行","平安银行","中国信合","广州银行","南京银行","深圳发展银行","邮政银行","浦发银行","江苏银行","成都银行","成都农商银行","南充市商业银行","渤海银行","郑州银行","杭州银行","宁波银行","顺德农商银行","华融湘江银行","湖北银行","晋商银行","广州农商银行","常熟农商银行","东莞银行"];
function f_(y_){
	for(var i=0;i<36;i++){
		if (y_==yin[i]) {
			return i+1;
		}
	}
}