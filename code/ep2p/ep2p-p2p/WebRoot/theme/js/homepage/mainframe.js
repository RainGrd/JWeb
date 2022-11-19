/**
 * 
 */
function win_load(){
	//数字滚动动画
	var options = {
	useEasing : true, 
	useGrouping : true, 
	separator : ',', 
	decimal : '.', 
	prefix : '', 
	suffix : '' 
	};

	//出现弹出事件
	var v_t1 = $("#v-targetElemet1").val();
	var v_t1_num = judgePiontNum(v_t1);
	var demo1 = new CountUp("myTargetElement1", 0, Number(v_t1), v_t1_num, 2.5, options);
	
	var v_t2 = $("#v-targetElemet2").val() == "" ? 0.00 : $("#v-targetElemet2").val();
	var demo2 = new CountUp("myTargetElement2", 0, Number(v_t2), 2, 2.5, options);
	
	var v_t3 = $("#v-targetElemet3").val() == "" ? 0.00 : $("#v-targetElemet3").val();
	var demo3 = new CountUp("myTargetElement3", 0, Number(v_t3), 2, 2.5, options);
	
	demo1.start();
	demo2.start();
	demo3.start();
};


//判断数字的小数点位数
function judgePiontNum(num){
	var arr = num.toString().split(".");
	var alen = arr.length;
	if(alen<=1){
		return 0;
	}else{
		if(parseInt(arr[1]) > 0){
			return arr[1].length;
		}else{
			return 0;
		}
	}
}


$(function(){
	//金额滚动
	win_load();
});