(function(){
	 gougou(".tyb_a_r");
	 $(".email_key_js").click(function(){
			$("body").css("overflow","hidden");
			$(".goumai_vip_d").removeClass("none");
			$(".top1500").css("top","0");
			$(".vip_g_d").css("top",($(window).height()-575)/2);
			$(".vip_g_d").css("left",($(window).width()-700)/2);
		});
	$(".drop_list").toggle(
			  function () {
				  $(this).removeClass("drop_list_bg_b").addClass("drop_list_bg_l");
				  $(this).attr("eq","1")
			  },
			  function () {
				  $(this).removeClass("drop_list_bg_l").addClass("drop_list_bg_b");
				  $(this).attr("eq","0")
			  }
		);	 
	 var oThis=0;
	 $(".pageIndex").html(1);
	var l=$(".scrollBox ul").length-1;
	$(".scrollBox ul").click(function(){
		oThis=$(this).index();
		//alert(oThis);
	});
	$(".scrollWrap .pagePre").click(function(){
		if(oThis>0){
			oThis--;
			$(".scrollBox").animate({"left":-oThis*570+"px"},500)
			$(".pageIndex").html(oThis+1);
		}
		else{oThis=0}
	});
	$(".scrollWrap .pageNext").click(function(){
		if(oThis<l){
			oThis++;
			$(".scrollBox").animate({"left":-oThis*570+"px"},500);
			$(".pageIndex").html(oThis+1);
			
		}
		else{oThis=l}
	});
	$(".pageLenght").html(l+1);
})();
