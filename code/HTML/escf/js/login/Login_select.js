(function(){
	//radio 单选效果调用,提交父容器参数
    radios_(".select_js",".list_a_p1");
    
    $(".select_js .radio_s").click(function(){
    	var eq =$(this).index(".select_js .radio_s");
    	$(".select_c").removeClass("c2980b9").eq(eq).addClass("c2980b9");
    })
})();