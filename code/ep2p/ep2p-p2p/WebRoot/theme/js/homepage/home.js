/*
$(function(){
	$('.marquee_y').jcMarquee({ 'marquee':'y','margin_bottom':'10px','speed':35 });
}); */



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
	
	 
	//滚动投资排行榜
//	$('.marquee_y').jcMarquee({ 'marquee':'y','margin_bottom':'0px','speed':35 });
	 var picTimer = setInterval(function() {
		 $('.marquee_y ul').animate({ 
			 top:'-70'
			  }, 1500,'linear',function(){
				  var ss  = $('.marquee_y ul li').eq(0).html();
				  $('.marquee_y ul li').eq(0).remove();
				  $('.marquee_y ul').css("top","");
				  $('.marquee_y ul').append('<li>'+ss+'</li>');
			  }); 
		}, 1500);
	animate();
	$(".maquee_wrap").hover(function(){
		clearInterval(picTimer);
	},function(){
		setInterval(function() {
			 $('.marquee_y ul').animate({ 
				 top:'-70'
				  }, 1500,'linear',function(){
					  var ss  = $('.marquee_y ul li').eq(0).html();
					  $('.marquee_y ul li').eq(0).remove();
					  $('.marquee_y ul').css("top","");
					  $('.marquee_y ul').append('<li>'+ss+'</li>');
				  }); 
			}, 1500);
		animate();
	
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
	
	//专区转让
	/*var transfer_zone=$(".transfer_zone").hide();
	$(".tz_bt").click(function(){
		$(".transfer_zone").slideToggle();
		$(this).toggleClass("down");
	});*/
	
	//拆红包
	$(".redGift").click(function(){
		$(".popUpWrap").fadeIn();
		$("path").addClass("path");
		var Time1 = null;
		Time1 = setInterval(function(){
			$("path").fadeOut();
			$(".popUpBoxBg,.popUpBoxBg2,.popUpBox").fadeIn();
			$(".popUpBox").addClass("bounceInUp animated");
			$(".popUpBoxBg").addClass("rotate");
			$(".popUpBoxBg2").addClass("opacity");
		},3000);
	});
	
		$(".popClose").click(function(){
			$(".popUpWrap,.popUpBoxBg,.popUpBoxBg2,.popUpBox,.popUpBoxCry,.popUpAdd,.popUpAddCry,.popUpWrapAdd").fadeOut();
		});
	/*加息券*/
		$(".receive").click(function(){
			$(".receive").addClass("receiveActive");	
			$(".receive p").addClass("receiveP");	
			$(".popUpWrapAdd,.popUpAdd").fadeIn();
			var Time2 = null;
			Time2 = setInterval(function(){
				$(".receive p").css("display","none")
				$(".receiveEd").css("display","block");
			},1200);
			
		});

	
});
$(function(){
	 $.ajax({
			type : "POST",
			url : BASE_PATH + "help/helpCenterController/index/selectColContentByWebTagSpecial.shtml",
			data : {webTag:"P2P_SYS_NOTICE",'isHome':'0'},
			dataType : "json",
			success : function(data) { 
				  if(data.retCode != "200"){
					  layer.alert(data.retMessage);
					  return;
				  }
				  if(data.colContentList == null )return;
				  if(data.colContentList.length > 0){ 
					  var aboutUsListHtml ='<ul class="scrollItem left" >';
					  var contentPid ;
					  for(var i = 0 ; i<data.colContentList.length ;i++){ 
						  var colContent = data.colContentList[i];
						  contentPid = colContent.pid;
						  if(colContent.webTag != null){ 
							  $("#remindTip").hide();
							  $("#scrollAd").hide(); 
							  continue;
						  }
						  $("#remindTip").show();
						  $("#scrollAd").show(); 
						  var url = BASE_PATH+"help/helpCenterController/index/toHelpCenterLeftList.shtml?webTag='P2P_SYS_NOTICE'&contentPid="+contentPid;
						  aboutUsListHtml+=' <li> <a href="${basePath}'+colContent.fileUrl+'" target="_blank">'+colContent.titleName+'</a> '+
						  '<a href='+url+' >更多>></a></li>';
						  
					  }
					  aboutUsListHtml+= '</ul><a class="right noRemind colse" href="javascript:void(0)">不再提醒</a> <div class="clear"></div>';
					 $("#scrollAd").html(aboutUsListHtml);
					 $('#scrollAd').FontScroll({time: 3000,num: 1});
					 $(".colse").click(function(e){ 
						 $("#remindTip").hide();
						 $("#scrollAd").hide(); 
					 });
				 }else{
					 $("#remindTip").hide();
					 $("#scrollAd").hide(); 
				 }
				
			},
			error : function() {
				layer.alert("查询系统公告异常!"); 
			}
		}); 
	 //关于我们
	 aboutUs();
	 //帮助中心菜单
	 helpCenter();
})

//加载关于我们 二级菜单
function aboutUs(){
	 $.ajax({
			type : "POST",
			url : BASE_PATH + "about/aboutUsController/index/toAboutUsLeftList.shtml",
			data : {webTag:"ep2p_col_aboutUsPage_t_1"},
			dataType : "json",
			success : function(data) {
				if(!data)return ; 
				if(!data.colContentList) return;
				if(data.colContentList.length <= 0) return ;
				        var appendHtml = "";
						for(var i=0;i<data.colContentList.length;i++){
							appendHtml+= "<a target='_blank' href='javascript:void(0);'>"+data.colContentList[i].titleName+"</a>"
						}
						$("#aboutUs").append(appendHtml);
			}
	 })
	
}
//加载帮助中心 二级菜单
function helpCenter(){
	 $.ajax({
			type : "POST",
			url : BASE_PATH + "about/aboutUsController/index/toAboutUsLeftList.shtml",
			data : {webTag:"ep2p_col_helpCenterPage_t_1"},
			dataType : "json",
			success : function(data) {
				if(!data)return ; 
				if(!data.colContentList) return;
				if(data.colContentList.length <= 0) return ;
				        var appendHtml = "";
						for(var i=0;i<data.colContentList.length;i++){
							appendHtml+= "<a target='_blank' href='javascript:void(0);'>"+data.colContentList[i].titleName+"</a>"
						}
						$("#helpCenter").append(appendHtml);
			}
	 })
	
}
//查看更多
function clickMore(contentPid){
	 $.ajax({
			type : "POST",
			url : BASE_PATH + "help/helpCenterController/index/toHelpCenterLeftList.shtml",
			data : {webTag:"A001",contentPid:contentPid},
			dataType : "json",
			success : function(data) {
				
			}
	 })
}
/**
 * 客服窗口
 */
function playJsAdPopWin(){ 
		popwin=window.location.href="tencent://message/?Menu=yes&amp;amp;uin=938063769&amp;amp;Service=58&amp;amp;SigT=A7F6FEA02730C98889AA2F59119078EDD6400B5762CF4DBE3EA9FD8FABBD2FBCC930C3CE21C121B0EA6E32C1B8798CB567FCE89F24D951C3ACB7E357CA55DC8B12B0BD7309BC7054C71C9D44CECC53A424A6F7A83E9D81DCA4D7A6007AF68E9445DFBB3EB263618D0E3AF2743CDDDF9127B2ED4B81E0A8EF&amp;amp;SigU=30E5D5233A443AB29B21D8779BD2C8F5C77AA0EAFF0D520E66839554BA1CE2FBBD30387D42992A94E23E66D5D33B168290DAE39B598D5E29B3A0225DFF4763BBF756A7BB9CDE6301";
};
/**
 * 打开手机客户端页面
 * @returns
 */
function openPhonePage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "help/helpCenterController/index/selectContentByWebTag.shtml",
		data : {webTag:"ep2p_col_phonePage_t_1"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
			
		}
	});
}

/**
 * 新手指引
 */
function noviceGuidePage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "index/homepController/index/selectColContentByWebTag.shtml",
		data : {webTag:"ep2p_col_noviceGuidePage_t_1"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
			
		}
	});
}
/**
 * 安全保障
 */
function safeProtectPage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "index/homepController/index/selectColContentByWebTag.shtml",
		data : {webTag:"ep2p_col_safeProtectPage_t_1"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
		}
	});
}
/**
 * 安全保障
 */
function safeProtectPage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "index/homepController/index/selectColContentByWebTag.shtml",
		data : {webTag:"ep2p_col_safeProtectPage_t_1"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
		}
	});
}
/**
 * e计划了解详情
 */
function ePlanInfoPage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "index/homepController/index/selectColContentByWebTag.shtml",
		data : {webTag:"ep2p_col_homePage_m_1"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
		}
	});
}
/**
 * 散标了解详情
 */
function emortgagePage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "index/homepController/index/selectColContentByWebTag.shtml",
		data : {webTag:"ep2p_col_homePage_m_2"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
		}
	});
}
/**
 * 安全保障
 */
function safeProtectPage(){
	$.ajax({
		type : "POST",
		url : BASE_PATH + "index/homepController/index/selectColContentByWebTag.shtml",
		data : {webTag:"ep2p_col_safeProtectPage_t_1"},
		dataType : "json",
		async:false,
		success : function(data) {
			if(data.retCode != "200"){
				return;
			}
			window.open(BASE_PATH+data.colContent.fileUrl);
		}
	});
}

/**
 * 新浪分享
 */
function onClickShareTSina(){
	var title = $("title").html();
	var rlink = window.location.search;
	var pic = "http://www.yscf.com/images/yscf/header_logo1.png";
	var site = "http://www.yscf.com";
	shareJavaScript.shareToSina(title,rlink,site,pic);
}
