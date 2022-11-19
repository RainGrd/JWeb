(function(){
	//切换模块调用,提交按钮标签和切换的容器标签和按钮样式标签  此乃我的证卷和我的积分切换
    //qie(".ge_ye_tilte_nav",".fui_c","ms_t_se");
    $(".ge_ye_tilte_nav").click(function(){
		var eq = $(this).index(".ge_ye_tilte_nav");
		$(".ge_ye_tilte_nav").removeClass("ms_t_se").eq(eq).addClass("ms_t_se");
		$('.fui_c').addClass("none").eq(eq).removeClass("none");
	});
  //切换模块调用,提交按钮标签和切换的容器标签和按钮样式标签  此乃兑换话费,加息,vip切换
    //qie(".fui_juan_nav span",".fui_juan_lists_div","bg297FB9");
    $(".fui_juan_nav span").click(function(){
		var eq = $(this).index(".fui_juan_nav span");
		$(".fui_juan_nav span").removeClass("bg297FB9").eq(eq).addClass("bg297FB9");
		$(".fui_juan_lists_div").addClass("none").eq(eq).removeClass("none");
	});
    //积分查询和明细切换
    //qie(".fui_j_x",".fui_juan","");
    $(".fui_j_x").click(function(){
		var eq = $(this).index(".fui_j_x");
		$(".fui_juan").addClass("none").eq(eq).removeClass("none");
	});
  //兑换话费,单选功能,选择的span标签的值eq等于1,否则等于0
    $(".fui_juan_lists").click(function(){
    	$(".fui_juan_lists").attr("style","");
    	$(".fui_juan_xuan_js").html($(this).children(".fui_juan_list_p1").html()+"兑换"+$(this).children(".fui_juan_list_p2").html())
    	$(this).css("background","url(../images/fu_2.png) no-repeat");
    	$(".fui_juan_lists").removeClass("fuli_duihuan_x");
    	$(".fui_juan_lists").attr("val","");
    	$(this).addClass("fuli_duihuan_x");
    	$(this).attr("val",$(".fui_juan_nav .bg297FB9").html());
    	
    });
    	 $(".liu_but").click(function(){
	 	$(".liu_but").addClass("b0b0b0");
	 	$(".liu_but").addClass("bgffffff");
	 	$(this).removeClass("b0b0b0");
	 	$(this).removeClass("bgffffff");
	 });
})();