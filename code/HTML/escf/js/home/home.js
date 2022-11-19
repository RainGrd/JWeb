/*
$(function(){
	$('.marquee_y').jcMarquee({ 'marquee':'y','margin_bottom':'10px','speed':35 });
}); */

//banner
$(function() {
	var bannerSlider = new Slider($('#banner_tabs'), {
		time: 4000,
		delay: 400,
		event: 'hover',
		auto: true,
		mode: 'fade',
		controller: $('#bannerCtrl'),
		activeControllerCls: 'active'
	});
	$('#banner_tabs .flex-prev').click(function() {
		bannerSlider.prev()
	});
	$('#banner_tabs .flex-next').click(function() {
		bannerSlider.next()
	});
	
	//公告滚动
	 $('#scrollAd').FontScroll({time: 3000,num: 1});
	//滚动投资排行榜
	$('.marquee_y').jcMarquee({ 'marquee':'y','margin_bottom':'0px','speed':35 });
	
	animate();
});
	
	//专区转让
	var transfer_zone=$(".transfer_zone").hide();
	$(".tz_bt").click(function(){
		$(".transfer_zone").slideToggle();
		$(this).toggleClass("down");
	});
			
			 
//关闭顶部
$(".colse").click(function(){
	$(".remindTip").hide();
});		
			
		
//微信
 $(".weixin_q").hover(function() {
 $(this).find(".s_int").animate({opacity: "show"},"slow");
 }, function() {
 $(this).find(".s_int").animate({opacity: "hide"},"fast");
 });

//手机客户端
$(".mobile_client").hover(function() {
 $(this).find(".u_code").animate({opacity: "show"},"slow");
 }, function() {
 $(this).find(".u_code").animate({opacity: "hide"},"fast");
 });
		
			
			
//登录  
$(".v_login").mouseover(function(e){					
	if(checkHover(e,this)){
		$(this).addClass('ldown');
		$(".login_in").stop().slideDown("fast");
	}
	
 });
 $(".v_login").mouseout(function(e){
	if(checkHover(e,this)){
		$(".login_in").stop().slideUp("fast");
		$(this).removeClass('ldown');
	}
 });
			 
			
//滚动登录			 
 $(".small_login").mouseover(function(e){					
	if(checkHover(e,this)){
		$(this).addClass('small_ldown');
		$(".login_s").stop().slideDown("fast");
	}
	
 });
 $(".small_login").mouseout(function(e){
	if(checkHover(e,this)){
		$(".login_s").stop().slideUp("fast");
		$(this).removeClass('small_ldown');
	}
 });
			

//顶部导航	
$(".menu_ul li").hover(function(){		
	$(this).find(".sub_menu").show();
	$(this).addClass("over_menu");
	$('span',this).stop().css('height','3px');
	$('span',this).animate({
	left:'8px',		
	width:'80%',
	right:'0'
		},100);
},function(){
	$(this).find(".sub_menu").hide();
	$(this).removeAttr("class");
	$('span',this).stop().animate({
	left:'50%',
	width:'0'
},100);
});

//滚动后导航	
$(".menu_small li").hover(function(){		
	$(this).find(".sub_menu_small").show();
	$(this).addClass("over_menu_small");
	$('span',this).stop().css('height','3px');
	$('span',this).animate({
	left:'8px',		
	width:'80%',
	right:'0'
		},100);
},function(){
	$(this).find(".sub_menu_small").hide();
	$(this).removeAttr("class");
	$('span',this).stop().animate({
	left:'50%',
	width:'0'
},100);
});
		
//滚动后导航固定
$(window).on("scroll",function(){
	if($(window).scrollTop()>=135){	
		$('.nav_small').css({'display':'block'});	
		$('.nav_small').stop().animate({top:"0"},400);	
	}
	if($(window).scrollTop()<135){
		$('.nav_small').animate().stop();
		$('.nav_small').css({'display':'none','top':'-50px'});
		
	}
	
});

		
//数字滚动动画
var options = {
useEasing : true, 
useGrouping : true, 
separator : ',', 
decimal : '.', 
prefix : '', 
suffix : '' 
　　　　};
var demo = new CountUp("myTargetElement",   0, 356, 0, 2.5, options);
var demo2 = new CountUp("myTargetElement2", 0, 256, 0, 2.5, options);
var demo3 = new CountUp("myTargetElement3", 0, 385, 0, 2.5, options);
var demo4 = new CountUp("myTargetElement4", 0, 870109, 0, 2.5, options);
var demo5 = new CountUp("myTargetElement5", 10, 21, 0, 2.5, options);

function animate(){
	var max="barred";
	var middle="baryellow";
	var min="barblue";	
	
	var maxValue=0;
	var minValue=0;
	
	var maxIndex=0;
	var minIndex=0;
	
	$(".charts").each(function(i,item){
		var a=parseInt($(item).attr("w"));
	
		if(i==0){
			minValue=a;
			minIndex=i;
		}
	
		if(a>maxValue){
			maxValue=a;
			maxIndex=i;
		}else if(a<minValue){
			minValue=a;
			minIndex=i;
		}
	
	});
	
	$(".charts").each(function(i,item){
		var addStyle="";
		var divindex=parseInt($(item).attr("divindex"));
		if(divindex==maxIndex){
			addStyle=max;
		}else if(divindex==minIndex){
			addStyle=min;
		}else{
			addStyle=middle;
		}
	
		$(item).addClass(addStyle);
		var a=$(item).attr("w");
		$(item).animate({
			width: a+"%"
		},1000);
	});
	
}

function window_onload(){
				demo.start();
				demo2.start();
				demo3.start();
				demo4.start();
				demo5.start();
			
}
$(".redGift").click(function(){
	$(".popUpWrap").fadeIn();
	$("path").addClass("path");
	var Time1 = null;
	Time1 = setInterval(function(){
		$("path").fadeOut();
		$(".popUpBoxBg,.popUpBoxBg2,.popUpBox,.popUpBoxCry").fadeIn();
		$(".popUpBox").addClass("bounceInUp animated");
		$(".popUpBoxBg").addClass("rotate");
		$(".popUpBoxBg2").addClass("opacity");
		$(".popUpWrap ul li").addClass("money");
	},3000);
});
/*var Time = null;
Time = setInterval(function(){
	$(".popUpBox").removeClass("bounceInUp");
	$(".popUpWrap").addClass("hinge");
	$(".popUpWrap,.popUpBoxBg,.popUpBoxBg2,.popUpBox").fadeOut();
	},12000);*///定时器
	$(".popClose").click(function(){
		$(".popUpWrap,.popUpBoxBg,.popUpBoxBg2,.popUpBox,.popUpBoxCry").fadeOut();
	});
	$(".receive").click(function(){
		$(".receive").addClass("receiveActive");	
		$(".receive p").addClass("receiveP");	
		var Time2 = null;
		Time2 = setInterval(function(){
			$(".receive p").css("display","none")
			$(".receiveEd").css("display","block");
		},1200);
		
	});