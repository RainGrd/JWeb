
window.onload=function(){
    function fnViewScale(){
        var web_wrap = $("#web_wrap")[0];
        var sreenWidth = document.body.clientWidth;
        var iScale = sreenWidth/375;
        web_wrap.style.transform = "scale("+iScale+")";
        web_wrap.style.webkitTransform = "scale("+iScale+")";  
    }
    fnViewScale();
    
    window.onresize = function(){
        fnViewScale();
    };
	
	//e计划详情页
	$(".eplanInfoObj h4").click(function(){
		$(".eplanInfoObj div,.eplanInfoObj h4 span").toggleClass("active");
	});
	$(".eplanInfoDrop h4").click(function(){
		$(".eplanInfoDrop div,.eplanInfoDrop h4 span").toggleClass("active");
	});

		// 环状进度
		$('.circle').each(function(index, el) {
			var num = $(this).find('i').text() * 3.6;
			//alert(num);
			if (num<=180) {
				$(this).find('.right').css("transform","rotate(" + num + "deg)");
			} else {
				$(this).find('.right').css("transform","rotate(180deg)");
				$(this).find('.left').css("transform","rotate(" + (num - 180) + "deg)");
			};
		});

	/*加息券*/
	var interestIthis = 0;
	$(".addInterestList li").click(function(){
		var interestIthis=$(this).index();
		$(".addInterestList li").find(".addInterestChoose").removeClass("active").html("未选择");
		$(".addInterestList li").eq(interestIthis).find(".addInterestChoose").addClass("active").html("已选择");
	});	

	/** 立即投资*/
	$("#sure").click(function(){
		$(".popUpWrap,.popPassword").addClass("transformScale");
		
	});
	
	
/* 模拟支付宝的密码输入形式 
*/
	var password=$(".popPassword")[0];
	(function (window, password) { 
		var active = 0, 
		inputBtn = password.querySelectorAll('input'); 
		for (var i = 0; i < inputBtn.length; i++) { 
			inputBtn[i].addEventListener('click', function () { 
			inputBtn[active].focus(); 
		}, false); 
		inputBtn[i].addEventListener('focus', function () { 
			this.addEventListener('keyup', listenKeyUp, false); 
		}, false); 
		inputBtn[i].addEventListener('blur', function () { 
			this.removeEventListener('keyup', listenKeyUp, false); 
		}, false); 
	} 
	/** 
	* 监听键盘的敲击事件 
	*/
	function listenKeyUp() { 
		var beginBtn = password.querySelector('#beginBtn'); 
		if (!isNaN(this.value) && this.value.length !=0 ) { 
			if (active <5 ) { 
			active += 1; 
			} 
			inputBtn[active].focus(); 
		} else if (this.value.length ==0 ) { 
			if (active > 0) { 
			active -= 1; 
		} 
			inputBtn[active].focus(); 
		} 
		if (active >= 0) { 
			var _value = inputBtn[active].value; 
			if (beginBtn.className == 'begin-no' && !isNaN(_value) && _value.length != 0) { 
			beginBtn.className = 'begin'; 
			beginBtn.addEventListener('click', function () { 
			calculate.begin(); 
		}, false); 
		} 
		} else { 
			if (beginBtn.className == 'begin') { 
			beginBtn.className = 'begin-no'; 
		} 
		} 
		} 
	})(window, password);
	
	/*预期收益*/
	$(".eplanDropInfo .btnRound").click(function(){
		$(".btnRound,.calculatorInfo").toggleClass("active");
	});
	
	
}