$(function(){
	/*选项卡切换*/
	$(".type_title li:not('.fr')").click(function(){
		$(this).addClass("change_li").siblings().removeClass("change_li");
		/*如果是我的借款的选项卡*/
		var text = $(this).find("a").text();
		if($(this).parents("#borrow_area").size()>0){
			if(text=="待还款"){
				$("#tb_01").show().siblings().hide();
			}else if(text=="已还款"){
				$("#tb_02").show().siblings().hide();
			}else if(text=="招标中"){
				$("#tb_03").show().siblings().hide();
			}else if(text=="申请进度"){
				$("#tb_04").show().siblings().hide();
			}
		}else if($(this).parents("#credit_area").size()>0){  /*如果是我的债权的选项卡*/
			if(text=="已持有债权"){
				$("#tb_01").show().siblings().hide();
			}else if(text=="转让中债权"){
				$("#tb_02").show().siblings().hide();
			}else if(text=="已转让债权"){
				$("#tb_03").show().siblings().hide();
			}
		}
	});
	/*展开全部项目明细*/
	////$(".item_all").click(function(){
	//	$(".invest_div,.borrow_div").fadeOut("fast");
	//	$(".item_div").fadeIn("fast");
	//});
	/*个人账户购买vip弹层按钮点击*/
	$(".g_vip").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip").removeClass("none");
		$(".vip_g").css("top",($(window).height()-480)/2);
		$(".vip_g").css("left",($(window).width()-700)/2);
		
	});
	
	$(".vip_qu_js").click(function(){
		$("body").css("overflow","");
		$(".goumai_vip").addClass("none");
	});
	/*个人账户提现添加银行卡弹层按钮点击*/
	$(".tan_ka_js").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip").removeClass("none");
		$(".vip_g").css("top",($(window).height()-530)/2);
		$(".vip_g").css("left",($(window).width()-700)/2);
		
	});
	/*个人账户提现解绑银行卡弹层按钮点击*/
	$(".jie_ka_js").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip_a").removeClass("none");
		$(".vip_g_a").css("top",($(window).height()-350)/2);
		$(".vip_g_a").css("left",($(window).width()-700)/2);
		
	});
	/*个人账户安全中心修改交易密码弹层按钮点击*/
	$(".jiaoyi_key_js").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip_b").removeClass("none");
		$(".vip_g_bs").css("top",($(window).height()-350)/2);
		$(".vip_g_bs").css("left",($(window).width()-700)/2);
		
	});
	/*个人账户安全中心设定交易密码弹层按钮点击*/
	$(".she_pawss").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip_b_b").removeClass("none");
		$(".vip_g_bs_b").css("top",($(window).height()-350)/2);
		$(".vip_g_bs_b").css("left",($(window).width()-700)/2);
		
	});	
	/*个人账户安全中心修改登录密码弹层按钮点击*/
	$(".login_key_js").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip_c").removeClass("none");
		$(".vip_g_c").css("top",($(window).height()-350)/2);
		$(".vip_g_c").css("left",($(window).width()-700)/2);
		
	});
	/*个人账户安全中心修改安全邮箱弹层按钮点击*/
	$(".email_key_js").click(function(){
		$("body").css("overflow","hidden");
		$(".goumai_vip_d").removeClass("none");
		$(".vip_g_d").css("top",($(window).height()-350)/2);
		$(".vip_g_d").css("left",($(window).width()-700)/2);
		
	});
	
	/*个人账户我的投资确认债权转让弹层按钮点击*/
	$(".zhuangr_key_js").click(function(){
		$("body").css("overflow","hidden");
		$(".tan_a").removeClass("none");
		$(".tan_s_a").css("top",($(window).height()-350)/2);
		$(".tan_s_a").css("left",($(window).width()-700)/2);
		
	});
	
	/*个人账户债权管理撤消弹层按钮点击*/
	$(".che_js").click(function(){
		$("body").css("overflow","hidden");
		$(".credito_t_js").removeClass("none");
		$(".vip_g").css("top",($(window).height()-480)/2);
	});
	
	$(".credito_qu_js").click(function(){
		$("body").css("overflow","");
		$(".credito_t_js").addClass("none");
	});
	
	$(".loanInfoBtn").click(function(){
		$(".loanInfoPopUp").show();
	});
	$(".loanPopupSuessBtn").click(function(){
		$(".dropPopupBox2").show();
		$(".vip_g_d").hide();
	});
	$(".loanPopClose").click(function(){
		$(".loanInfoPopUp").hide();
		//$(".dropPopupBox2").fadeIn();
	});
	
	
	
	
	
	
});
/*单选效果*/
function radios_(a,b){
		$(a +" .radio_s").eq(0).addClass("c2980b9").html('<img src="../images/radio_x.png" class="block" />').attr("val","1");
	    $(a +" .radio_s").click(function(){
		$(a +" "+b).removeClass("c2980b9");
		$(a +" "+b).eq($(this).index(a +" .radio_s")).addClass("c2980b9");
		$(a +" .radio_s").html("");
		$(a +" .radio_s").attr("val","0");
		$(this).html('<img src="../images/radio_x.png" class="block" />');
		$(this).attr("val","1");
	})
	
		
}
/*勾选效果*/
function gougou(a,b){
	$(b).attr("val","0");
	$(a +" .gou_s").click(function(){
		if($(this).attr("val")==1){
			$(this).html('');
			$(this).attr("val","0");
			$(this).parent().removeClass("c2980b9");
		}else{
			$(this).html('<img src="../images/gou_b.png" class="block" />');
			$(this).attr("val","1");
			$(this).parent().addClass("c2980b9");
		}
	});
	/*$(b).click(function(){
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
    })*/
	
}
/*切换效果*/
function qie(a,b,c){
	$(a).click(function(){
		var eq = $(this).index(a);
		$(a).removeClass(c).eq(eq).addClass(c);
		$(b).addClass("none").eq(eq).removeClass("none");
	});
}













