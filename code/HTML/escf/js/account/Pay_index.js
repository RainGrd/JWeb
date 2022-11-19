(function(){
	//添加银行卡界面打开
    $(".tis_yin_tian").click(function(){
    	$(".tis_yin").addClass("none").eq(1).removeClass("none");
    });
    
    //提现界面打开
    $(".tixian").click(function(){
    	$(".tis").addClass("none").eq(1).removeClass("none");
    })
    
    //记录打开
    $(".tis_jilu").click(function(){
    	$(".tis").addClass("none").eq(0).removeClass("none");
    });
     //切换模块调用,
    qie(".tis_qie_ta",".tis_qie_cs","ms_t_se");
})();