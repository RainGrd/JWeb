(function(){
    $(".zi_tian").click(function(){
    	$(".zi_").addClass("none").eq(1).removeClass("none");
    })
    //radio 单选效果调用,提交父容器参数
    radios_(".zi_sz_list_a1",".list_a_p1");
    radios_(".zi_sz_list_b1",".list_a_p2");
    radios_(".zi_sz_list_c1",".list_a_p3");
    radios_(".zi_sz_list_d1",".list_a_p4");
     //勾选 效果调用,提交父容器参数 和全部勾选按钮参数
    gougou(".list_a_p5");
    //设置显示效果
    radios_n(".zi_sz_list_a1 .radio_s",".yu_b",".yu_g");
    radios_n(".zi_sz_list_b1 .radio_s",".yue_f");
    radios_n(".zi_sz_list_c1 .radio_s",".shou_f");
    function radios_n(a,b,c){
    	$(a).click(function(){
    		if ($(this).index(a)==1) {
    			$(a).eq(1).siblings().show();
    			$(c).eq(0).siblings().hide();
    		}else{
    			$(c).eq(0).siblings().show();
    			$(a).eq(1).siblings().hide();
    		    $(b).show();
    			
    		}
    		//console.log()
    	})
    }
})();