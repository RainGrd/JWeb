	//微信
		$(".weixin_q").hover(function() {
			$(this).find(".s_int").stop(true,true).animate({
				opacity: "show"
			}, "slow");
		}, function() {
			$(this).find(".s_int").animate({
				opacity: "hide"
			}, "fast");
		});
		//手机客户端
		$(".mobile_client").hover(function() {
			$(this).find(".u_code").animate({
				opacity: "show"
			}, "slow");
		}, function() {
			$(this).find(".u_code").animate({
				opacity: "hide"
			}, "fast");
		});
		　　　 //登录
		$(".v_login").toggle(function() {
				$(".login_in").slideDown("fast");
				$(this).addClass('ldown')
			},
			function() {
				$(".login_in").slideUp("fast");
				$(this).removeClass('ldown');
			});
		//滚动登录
		$(".small_login").toggle(function() {
				$(".login_s").slideDown("fast");
				$(this).addClass('small_ldown')
			},
			function() {
				$(".login_s").slideUp("fast");
				$(this).removeClass('small_ldown');
			});
		//顶部导航	
		$(".menu_ul li").hover(function() {
			$(this).find(".sub_menu").show();
			$(this).addClass("over_menu");
			$('span', this).stop().css('height', '3px');
			$('span', this).animate({
				left: '8px',
				width: '80%',
				right: '0'
			}, 100);
		}, function() {
			$(this).find(".sub_menu").hide();
			$(this).removeAttr("class");
			$('span', this).stop().animate({
				left: '50%',
				width: '0'
			}, 100);
		});
		//滚动后导航	
		$(".menu_small li").hover(function() {
			$(this).find(".sub_menu_small").show();
			$(this).addClass("over_menu_small");
			$('span', this).stop().css('height', '3px');
			$('span', this).animate({
				left: '8px',
				width: '80%',
				right: '0'
			}, 100);
		}, function() {
			$(this).find(".sub_menu_small").hide();
			$(this).removeAttr("class");
			$('span', this).stop().animate({
				left: '50%',
				width: '0'
			}, 100);
		});
		　 //滚动后导航固定
		$(window).scroll(function() {
			height = $(window).scrollTop();
			if (height > 200) {
				$('.nav_small').fadeIn();
			} else {
				$('.nav_small').fadeOut();
			};
		});
		