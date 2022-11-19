(function(){
	//radio 单选效果调用,提交父容器参数
    radios_(".bo_bd");
    $(".ceshi_btn").click(function(){
    	if ($(".shenfenz").val().length<10) {
    		$(".shenfenz").attr("va",$(".shenfenz").val());
    		$(".shenfenz").val("省份证错误")
    		$(".shenfenz").addClass("style_tis");
    		$(".style_tis").focus(function(){
    	$(this).val($(this).attr("va"));
    })
    	}
    	
    });
    
})();