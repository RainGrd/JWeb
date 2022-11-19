(function(){
	function logings() {
		var html = '<div class="al_l_list">'
					+'<img src="../images/ac_2.png" alt="ss" class="al_l_list_img" />'
					+'<div class="al_l_list_r fr_">'
					+'<p class="mt15 colorDarkBlue size16 pr15">'	
					+'有梦想齐分享——你推我奖活动正在火热进行中</p>'	
					+'<p class=" c2980b9 size12">'	
					+'<img src="../images/ac_3.png" />2015年11月3日-2015年12月3日</p>'	
					+'<p class="mt20">'	
					+'<a href="#" class="btn">查看详情</a></p></div></div>';
		$(".ac_l").append(html);
	
	}
	$(window).on("scroll", function() {
		console.log( $(window).height())
		if ($(".ac_l").height() <= $(window).scrollTop() + $(window).height()) {
			logings();
		}
	})


})();