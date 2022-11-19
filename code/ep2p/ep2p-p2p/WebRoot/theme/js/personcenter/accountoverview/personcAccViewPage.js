/**
 * 账户总览页面
 */
var personcAccview = {
		/*新增获取所选时间的方法*/
		getTime:function(){
			$("#CalendarMain").on("click",".item",function(){
				//进行加载数据
				var year = $(".selectYear").text().replace("年","") == "" ? "0000" : $(".selectYear").text().replace("年","");
				var month = $(".selectMonth").text().replace("月","") == "" ? 0 : $(".selectMonth").text().replace("月","");
				var day = $(this).find("a").text() == "" ? 0 : $(this).find("a").text();
				var dataStr = year + "-" + (month < 10 ? "0" + month : month + "") + "-" + (day < 10 ? "0" + day : day + "");
				var thisday = new Date();
				var tdy = new Date(thisday.getFullYear() + "/" + (thisday.getMonth()+1) + "/" + thisday.getDate());
				var r = dataStr.replace(/-/g, "/");
				var today = new Date(r);
				if(today >= tdy ){
					$("#usercenter_daishou").load(basePath + "userinfo/centerController/getDueinMoenyList.shtml?randm=" + randm,{data:dataStr});
					//设置表头信息
					var selectTime =$(".selectYear").text()+$(".selectMonth").text()+$(this).find("a").text()+"日";	
					$(".select_date").text(selectTime + " 待收");
				}
				
			});
		},
		//跳转格式化
		borURLProNameFormat:function(pid,borrowType){
			var eshouf_href = basePath + "business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId=";
			var ejihua_href = basePath + "business/financialProductsManageController/index/selectFinProdInfoData.shtml?borrowId=";
			var xins_href = basePath + "mybids/borrowNewStandardController/index/toBorrowStandardInfo.shtml?pid=";
			var tiyan_href = basePath + "mybids/experienceBorrowController/index/toBorrowStandardInfo.shtml?pid=";
			var re_href = "";
			//判断是不是转让
			if(undefined != borrowType && "" != borrowType){
				//e首房，e抵押
				if(borrowType == '1' || borrowType == '2'){
					re_href = eshouf_href + pid;
				}
				//e计划
				if(borrowType == '3'){
					re_href = ejihua_href + pid;
				}
				//新手
				if(borrowType == '4'){
					re_href = xins_href + pid;
				}
				//体验标
				if(borrowType == '5'){
					re_href = tiyan_href + pid;
				}
			}
			//返回
			return re_href;
		},
		//单机项目名称跳转
		pronameClick:function(pid,borrowType){
			var vlocahref = personcAccview.borURLProNameFormat(pid,borrowType);
			window.location.href = vlocahref;
		}
};
/**
 * 点击提现
 */
function clickWithdraw(){
	$("#withdraw_div").empty();
	$("#withdraw_div").load( BASE_PATH+'userinfo/custRechargeWithdrawController/toWithdrawPage.shtml');
}


//初始化
$(function(){
	//--------我的账户时间
	CalendarHandler.initialize();
	personcAccview.getTime();
	//我的账户---自动投标
	$(".zi_tian").click(function(){
    	$(".zi").addClass("none").eq(1).removeClass("none");
    });

	//sheen.banner_xyx("#uesr_banner");
	var oThis=0;
	var l=$("#uesr_banner li").length-1;
	$("#uesr_banner li").click(function(){
		oThis=$(this).index();
		//alert(oThis);
	});
	$(".scrollBoxBtn .scrollPageP").click(function(){
		if(oThis>0){
			oThis--;			
		}
		else{oThis=0}
		$("#uesr_banner ul").animate({"left":-oThis*910+"px"},500)
	});
	$(".scrollBoxBtn .scrollPageN").click(function(){
		if(oThis<l){
			oThis++;			
		}
		else{oThis=l}
		$("#uesr_banner ul").animate({"left":-oThis*910+"px"},500)
	});
	var TimeBanner = null;
	TimeBanner = setInterval(function(){
		if(oThis<l)
		{
		oThis++;
		}else{oThis=0;}
		$("#uesr_banner ul").animate({"left":-oThis*910+"px"},500)
	},3000);
	$("#uesr_banner").hover(function(){
		clearInterval(TimeBanner);
	},function(){
		TimeBanner = setInterval(function(){
		if(oThis<l)
		{
		oThis++;
		}else{oThis=0;}
		$("#uesr_banner ul").animate({"left":-oThis*910+"px"},500)
	},3000);
	});
});