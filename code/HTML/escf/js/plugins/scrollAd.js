/**
 +-------------------------------------------------------------------
 * jQuery FontScroll - 文字行向上滚动插件 - http://java2.sinaapp.com
 +-------------------------------------------------------------------
 * @version    1.0.0 beta
 * @since      2014.06.12
 * @author     kongzhim <kongzhim@163.com> <http://java2.sinaapp.com>
 * @github     http://git.oschina.net/kzm/FontScroll
 +-------------------------------------------------------------------
 */

;(function($){
	$.fn.jcMarquee = function(options) {
		var defaults = {
			'marquee':'x',
			'margin_bottom':'0',
			'margin_right':'0',
            'speed':'10'
		};
		var options = $.extend(defaults,options);
		return this.each(function() {
	        var $marquee = $(this),
			    $marquee_scroll = $marquee.children('ul');
			$marquee_scroll.append("<li class='clone'>"+"</li>");
			$marquee_scroll.find('li').eq(0).children().clone().appendTo('.clone');
			var $marquee_left = $marquee_scroll.find('li');
			if (options.marquee == 'x') {
				var x = 0;
		        $marquee_scroll.css('width','1000%');
				$marquee_left.find('div').css({'margin-right':options.margin_right});
				$marquee_left.css({'margin-right':options.margin_right});
                function Marquee_x(){ 
	                $marquee.scrollLeft(++x);
					_margin = parseInt($marquee_left.find('div').css('margin-right'));
		            if(x==$marquee_left.width()+_margin) { x = 0 };
				};
		    	var MyMar=setInterval(Marquee_x,options.speed); 
                $marquee.hover(function(){
                    clearInterval(MyMar);
                },function(){
                    MyMar=setInterval(Marquee_x,options.speed);
                });
			}
			if (options.marquee == 'y') {
				var y = 0;
		        $marquee_scroll.css('height','1000%');
				$marquee_left.find('div').css('margin-bottom',options.margin_bottom);
				$marquee_left.css('margin-bottom',options.margin_bottom);
                function Marquee_y(){ 
	                $marquee.scrollTop(++y);
					_margin = parseInt($marquee_left.find('div').css('margin-bottom'));
		            if(y==$marquee_left.height()+_margin) { y = 0 };
				};
		    	var MyMar=setInterval(Marquee_y,options.speed); 
                $marquee.hover(function(){
                    clearInterval(MyMar);
                },function(){
                    MyMar=setInterval(Marquee_y,options.speed);
                });
			};
		});
	};
	
	
  $.fn.FontScroll = function(options){
        var d = {time: 3000,s: 'fontColor',num: 1}
        var o = $.extend(d,options);
        

        this.children('ul').addClass('line');
        var _con = $('.line').eq(0);
        var _conH = _con.height(); //滚动总高度
        var _conChildH = _con.children().eq(0).height();//一次滚动高度
        var _temp = _conChildH;  //临时变量
        var _time = d.time;  //滚动间隔
        var _s = d.s;  //滚动间隔


        _con.clone().insertAfter(_con);//初始化克隆

        //样式控制
        var num = d.num;
        var _p = this.find('li');
        var allNum = _p.length;

        _p.eq(num).addClass(_s);


        var timeID = setInterval(Up,_time);
		this.hover(function(){clearInterval(timeID)},function(){timeID = setInterval(Up,_time);});

        function Up(){
            _con.animate({marginTop: '-'+_conChildH});
            //样式控制
            _p.removeClass(_s);
            num += 1;
            _p.eq(num).addClass(_s);
            
            if(_conH == _conChildH){
                _con.animate({marginTop: '-'+_conChildH},"normal",over);
            } else {
                _conChildH += _temp;
            }
        }
        function over(){
            _con.attr("style",'margin-top:0');
            _conChildH = _temp;
            num = 1;
            _p.removeClass(_s);
            _p.eq(num).addClass(_s);
        }
    }	
	
})(jQuery)