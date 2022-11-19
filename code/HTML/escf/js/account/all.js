
var sheen = {
	ceshi: "", //测试参数，没用
	gun: 0, //解决滚动条返回顶部与一屏页面自动高冲突的判断条件

	banner_xyx: function(slid) {
		//			作者：xyx
		//      	时间：2015-09-02
		//      	function：banner() 
		//      	作用：幻灯两片滚动轮播，具有上下一页，轮播数字导航，悬停停止播放
		//      	用发：传送一个幻灯片的父容器标签，例如 sheen.banner_xyx("#focus001");
		//          范围：全局轮播


		var sWidth = $(slid).width(); //获取焦点图的宽度（显示面积）
		var len = $(slid).find("ul li").length; //获取焦点图个数
		var index = 0;
		var picTimer;
		
		$(slid + " ul").css("width", sWidth * (len + 1));
		$(slid + " ul").append('<li>' + $(slid + " ul li").eq(0).html() + '</li>'); //把第一个复制到最后一个，
		$(slid + " ul li").css("width", sWidth); //获取图片的宽为banner父容器的宽
		$(slid).css("height", $(slid + " ul li img").height()); //获取图片的高为banner父容器的高
		//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
		var btn = "<div class='btnBg'></div><div class='btn'>";
		for (var i = 0; i < len; i++) {
			var ii = i + 1;
			//btn += "<span>" + ii + "</span>";
		}
		btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
		$(slid).append(btn);
		$(slid).find("div.btnBg").css("opacity", 0.5);
		//为小按钮添加鼠标滑入事件，以显示相应的内容
		$(slid + " div.btn span").css("opacity", 0.4).mouseenter(function() {
			index = $(slid + " .btn span").index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
		//上一页按钮
		$(slid + " .pre").click(function() {
			index -= 1;
			if (index == -1) {
				index = len - 1;
			}
			showPics(index);
		});
		//下一页按钮
		$(slid + " .next").click(function() {
			index += 1;
			if (index == len) {
				index = 0;
			}
			showPics(index);
		});
		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$(slid).hover(function() {
			//clearInterval(picTimer);
		}, function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if (index == len + 1) {
					index = 1;
				}
			}, 4000); //此4000代表自动播放的间隔，单位：毫秒
		}).trigger("mouseleave");
		
		
		//显示图片函数，根据接收的index值显示相应的内容
		function showPics(index) { //普通切换
			var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
			var nowLeft_ = index * sWidth;
			if (index == len) {
				setTimeout(function() {
					$(slid + " ul").css("left", "0px");
				}, 700);
				index = 0;
			}
			$(slid + " ul").stop(true, false).animate({
				"left": nowLeft
			}, 500); //通过animate()调整ul元素滚动到计算出的position
			$(slid + " .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
			$(slid + " .btn span").stop(true, false).animate({
				"opacity": "0.4"
			}, 500).eq(index).stop(true, false).animate({
				"opacity": "1"
			}, 300); //为当前的按钮切换到选中的效果
		}
		return this;
	}
}


sheen.banner_xyx("#uesr_banner");