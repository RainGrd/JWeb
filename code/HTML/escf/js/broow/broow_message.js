(function(){
	//radio 单选效果调用,提交父容器参数
   se(".bo_f_m");
   se(".bo_y_m"); 
   se(".bo_h_m"); 
    
})();

function se(a){
	$(a).click(function(){
		$(a).html("");
		$(a).removeClass("cd5d5d5").addClass("colorDarkBlue");
	})
};