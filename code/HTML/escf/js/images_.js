(function() {
	var img_ = [];
	if ($(window).width() < 1200) {
		for (var i = 0; i < $("img").length; i++) {
			$("img").eq(i).attr("data-original", "/" + $("img").eq(i).attr("data-original"))
		}
	}
	$("img.lazy").lazyload();
})();