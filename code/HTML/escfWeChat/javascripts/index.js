
window.onload=function(){
	 // 页面缩放
    function fnViewScale(){
        var web_wrap = $("#web_wrap")[0];
        var sreenWidth = document.body.clientWidth;
		
        var iScale = sreenWidth/375;
		//var sreenHeight=$("#web_wrap").height()*iScale
        web_wrap.style.transform = "scale("+iScale+")";
        web_wrap.style.webkitTransform = "scale("+iScale+")";  
		//web_wrap.style.height=sreenHeight;
    }
    fnViewScale();
    
    window.onresize = function(){
        fnViewScale();
    };
	// 触摸焦点图
	$(".downloadClose").click(function(){
		$(".downloadBox").css("display","none");	
	})
	for(var i=0; i<$(".scroll_wrap").length;i++){	
		fnZonePic(i);
	};

	function fnZonePic(iNum){
		var parScroll = $(".scroll_wrap").eq(iNum);
		var oScroll = $(".scroll").eq(iNum);
		var aScroll = $(".scroll").eq(iNum).find("li");
		var aScrollIcon = $(".scroll_bar").eq(iNum).find("li");
		var iNow = 0;
	
		for(var i=0; i<aScroll.length ; i++){
			//alert(aScroll.length);
			fnPic(i);
		}
	
		function fnPic(num){
		// 触摸开始
		aScroll[num].addEventListener('touchstart',function(event){
				// event.preventDefault(); //滚动时阻止浏览器滚动或缩放
			if (event.targetTouches.length == 1){
				touch = event.targetTouches[0];
				startX = touch.clientX ;       
				iLeft = oScroll[0].offsetLeft ;
			}
		},false);
		// 触摸移动
		aScroll[num].addEventListener('touchmove', function(event) {
				// event.preventDefault(); //滚动时阻止浏览器滚动或缩放
	
			  // 如果这个元素的位置内只有一个手指的话
			  if (event.targetTouches.length == 1) {
				touch = event.targetTouches[0];
				x = Number(touch.pageX); //页面触点X坐标  
				oScroll[0].style.left = iLeft+x-startX+"px" ; 
			  }
		}, false);
		// 触摸结束
		aScroll[num].addEventListener('touchend',function(event){
				//运动
				if( x-startX > 50){     // 向右
					if( iNow >0 ){
						iNow-- ;
					}
				}else if( x-startX < -50){  // 向左
					if( iNow < aScroll.length-1 ){
						iNow++ ;
					}        
				}
				// 既不向右，也不向左
				oScroll.animate({"left":-375*iNow});  
				fnIcon(iNow);     
		},false);
	
		// function fnIcon(a){
		//     for( var j=0; j<aScroll.length ; j++ ){
		//         aScrollIcon[j].className = "" ;
		//     }
		//     aScrollIcon[a].className = "active" ;
		// }
	
		function fnIcon(iNum){
			aScrollIcon.eq(iNum).addClass("active").siblings().removeClass("active");
		}
		// console.//Log(iNum)
	
		}
	}
}