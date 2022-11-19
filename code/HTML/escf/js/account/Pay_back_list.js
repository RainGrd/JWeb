(function(){
	/*勾选效果*/
	
	function gougou(a,b){
		$(a +" .gou_s").attr("val","0");
		$(b).attr("val","0");
		$(a +" .gou_s").click(function(){
			if($(this).attr("val")==1){
				$(this).html('');
				$(this).attr("val","0");
				$(this).parent().removeClass("c2980b9");
			}else{
				$(this).html('<img src="../images/gou_b.png" class="block" />');
				$(this).attr("val","1");
				$(this).parent().addClass("c2980b9");
			}
		});
	}
		
	gougou(".tan_ka_js_g");
	 //充值平台 选中效果
    $(".tis_yin_ping").click(function(){
    	$(".tis_yin_ping").removeClass("bgebeff1");
    	$(this).addClass("bgebeff1");
    })
    
    
    //添加银行图片,给银行图片添加class ="yin_h_ka" ,自动判断 alt的值 然后自动填充图片
    $(document).ready(function(){ 
		var y_;
		var src_;
		for(var i=0;i<$(".yin_h_ka").length;i++){
			y_ = $(".yin_h_ka").eq(i).attr("alt");
			$(".yin_h_ka").eq(i).attr("src",'../images/bank'+f_(y_)+'.png');
		}
		
		
	}); 
	var yin =["工商银行","农业银行","建设银行","招商银行","民生银行","交通银行","中国银行","中信银行","广发银行","兴业银行","华夏银行","上海银行","北京银行","光大银行","平安银行","中国信合","广州银行","南京银行","深圳发展银行","邮政银行","浦发银行","江苏银行","成都银行","成都农商银行","南充市商业银行","渤海银行","郑州银行","杭州银行","宁波银行","顺德农商银行","华融湘江银行","湖北银行","晋商银行","广州农商银行","常熟农商银行","东莞银行"];
	function f_(y_){
		for(var i=0;i<36;i++){
			if (y_==yin[i]) {
				return i+1;
			}
		}
	}
})();