(function(){
	
	
	//勾选 效果调用,提交父容器参数 和全部勾选按钮参数
    gougou(".dropAgreeBox");
    //切换模块调用,提交按钮标签和切换的容器标签和按钮样式标签  此乃系统消息与活动消息的切换 
    qie(".ge_ye_tilte_nav",".dropTab","ms_t_se");
	//radio 单选效果调用,提交父容器参数
	radios_(".vip_g_list");
	
	//弹框
	$(".dropJionBtn").click(function(){
		$(".dropPopupWrap ,.dropPopupBox").fadeIn(function(){
			$("#dropPopupSure").click(function(){
				$(".dropPopupBox").fadeOut();
				$(".dropPopupBox2").show();
			});
		});
		
		$("#dropPopupDone,.dropPopupWrap").click(function(){
			$(".dropPopupWrap ,.dropPopupBox,.dropPopupBox2").fadeOut();
		});
	});
	
	//进度条
	var jindu=$(".dropIComplete span").text();
	//alert(jindu);
		  $(".dropScheduleF").animate({ 
		    width: jindu
		  }, 1500 ); 
	//列表页进度
	var jinduUl=$(".etabConUl li").length;
	var jinduLi=0;
	//alert(jinduUl);
	for(i=0; i<jinduUl; i++){
		jinduLi=$(".etabConUl li").eq(i).find(".dropIComplete span").text();
		$(".etabConUl li").eq(i).find(".dropScheduleF").animate({ 
		    width: jinduLi
		  }, 1000 ); 
	}	  
	
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
		  
})();
