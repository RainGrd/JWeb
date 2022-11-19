(function(){
	//勾选 效果调用,提交父容器参数 和全部勾选按钮参数
    gougou(".tan_ka_js_g",".gou_s_js");
    //修改邮箱确定 的下一个页面
    $(".goumai_vip_d_meial_but").click(function(){
    	$(".goumai_vip_d_meial").addClass("none").eq(1).removeClass("none")
    })
    
    var jifen=0;
    for(var i=0;i<8;i++){
    	var img = $(".an_x_gr_list_im").eq(i).attr("im");
    	if(img=="1"){
    		jifen=parseInt(jifen)+parseInt($(".an_x_gr_list_im").eq(i).attr("jf"));
    	}
    }
    $(".an_x_jd_lv").css("width",jifen*(535/100));
    $(".an_x_jd_jifen").html(jifen);
})();