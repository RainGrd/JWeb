(function(){
	$(".dr_sx_p i").click(function(){
		$(this).siblings("i").removeClass("dr_sx_i").addClass("dr_sx_i_");
		
		$(this).removeClass("dr_sx_i_").addClass("dr_sx_i");
	})
	qie(".ge_ye_tilte_nav",".dr_2_bd","ms_t_se");
	
	var n=0;
	for(n=0; n<4; n++){
		$(".etabConUl li").eq(4*n+3).addClass("marginR0");
	};
})();
