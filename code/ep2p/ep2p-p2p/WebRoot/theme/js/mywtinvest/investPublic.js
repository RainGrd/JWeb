(function(){
	//勾选 效果调用,提交父容器参数 和全部勾选按钮参数
	$(".gou_s").html('<img src="'+ basePath + 'theme/default/images/gou_b.png" class="block" />');
	$(".gou_s").attr("val","1");
	$(".gou_s").parent().addClass("c2980b9");
	
	$(".dropConTopRight .gou_s").click(function(){
		if($(this).attr("val")==1){
			$(this).html('');
			$(this).attr("val","0");
			$(this).parent().removeClass("c2980b9");
		}else{
			$(this).html('<img src="'+ basePath + 'theme/default/images/gou_b.png" class="block" />');
			$(this).attr("val","1");
			$(this).parent().addClass("c2980b9");
		}
	});
 
	userCenter.qie(".ge_ye_tilte_nav",".dropTab","ms_t_se");
	
})();
