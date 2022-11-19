(function(){
	//3个表格切换
	 $(".ge_ye_tilte_nav").click(function(){
			var eq = $(this).index(".ge_ye_tilte_nav");
			$(".ge_ye_tilte_nav").removeClass("ms_t_se").eq(eq).addClass("ms_t_se");
			$(".inv_js").addClass("none").eq(eq).removeClass("none");
	});
})();