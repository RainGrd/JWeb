/**
 * 个人中心所有的公共方法及变量
 */
var userCenter = {
		/*选项卡切换*/
		initfunc:function(){
			$(".type_title li:not('.fr')").click(function(){
				$(this).addClass("change_li").siblings().removeClass("change_li");
				/*如果是我的借款的选项卡*/
				var text = $(this).find("a").text();
				if($(this).parents("#borrow_area").size()>0){
					if(text=="待还款"){
						$("#tb_01").show().siblings().not("#borrow_area").hide();
					}else if(text=="已还款"){
						$("#tb_02").show().siblings().not("#borrow_area").hide();
					}else if(text=="招标中"){
						$("#tb_03").show().siblings().not("#borrow_area").hide();
					}else if(text=="申请进度"){
						$("#tb_04").show().siblings().not("#borrow_area").hide();
					}
				}else if($(this).parents("#credit_area").size()>0){  /*如果是我的债权的选项卡*/
					if(text=="已持有债权"){
						$("#tb_01").show().siblings().not("#credit_area").hide();
					}else if(text=="转让中债权"){
						$("#tb_02").show().siblings().not("#credit_area").hide();
					}else if(text=="已转让债权"){
						$("#tb_03").show().siblings().not("#credit_area").hide();
					}
				}
			});
			/*展开全部项目明细*/
			$(".item_all").click(function(){
				$(".invest_div,.borrow_div").fadeOut("fast");
				$(".item_div").fadeIn("fast");
			});
		},
		
		/*个人中心panel切换效果*/
		qie:function(a,b,c){
			$(a).click(function(){
				var eq = $(this).index(a);
				$(a).removeClass(c).eq(eq).addClass(c);
				$(b).addClass("none").eq(eq).removeClass("none");
			});
		}
};




$(function(){
	/*选项卡切换*/
	userCenter.initfunc();
	
	//个人中心--资金流水
	userCenter.qie(".liu_but",".liu_c","");
	userCenter.qie(".ms_ta",".ms_c","ms_t_se");
});




