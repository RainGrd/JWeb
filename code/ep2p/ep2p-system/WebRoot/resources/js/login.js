$(document).ready(function() {
	// inputtxt forcus
	$('.inputtxt').each(function(){
		$(this).focus(function(){
			var s=$(this).parent().find('>span:first');
			s.addClass('active'+s.attr('class'));
		});
		$(this).blur(function(){
			var s=$(this).parent().find('>span:first');
			s.removeClass('active'+s.attr('class'));
		});
	});
	//remember Label
	$('.checkboxLabel').click(function(){
		if($(this).hasClass('ischeckLabel')){
			$(this).removeClass('ischeckLabel');
			$('.remember').removeAttr('checked');
		}else{
			$(this).addClass('ischeckLabel');
			$('.remember').prop('checked','checked');
		}
	});
	
	getLocalStorge();
	$('.tip a ').tipsy({
		gravity : 'sw'
	});
	$('#login').show().animate({
		opacity : 1
	}, 2000);
	$('.logo').show().animate({
		opacity : 1,
		top : '32%'
	}, 800, function() {
		$('.logo').show().delay(1200).animate({
			opacity : 1,
			top : '1%'
		}, 300, function() {
			$('.formLogin').animate({
				opacity : 1,
				left : '0'
			}, 300);
			$('.userbox').animate({
				opacity : 0
			}, 200).hide();
		});
	});

	$('.checkboxLabel').click(function(e){
		if($('.remember').val() == '1'){
			$('.remember').val('0');
		}else {
			$('.remember').val('1');
		}
	});
	$('.userload').click(function(e) {
		$('.formLogin').animate({
			opacity : 1,
			left : '0'
		}, 300);
		$('.userbox').animate({
			opacity : 0
		}, 200, function() {
			$('.userbox').hide();
		});
	});
	// 重置
	$('#forgetpass').click(function(e) {
		$(":input").each(function() {
		$('#'+this.name).val("");
		});
	});
	// 点击登录
	$('#but_login').click(function(e) {
		//$('#formLogin').submit();
		submit();
	});
	//回车登录
	$(document).keydown(function(e){
		if(e.keyCode == 13) {
			submit();
		}
	});

	$('#Kaptcha').click(     
        function() {     
           $(this).hide().attr('src','Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();     
    });
});
//表单提交
function submit(){
	var submit = true;
	$("input[nullmsg]").each(function() {
		if ($("#" + this.name).val() == "") {
			showError($("#" + this.name).attr("nullmsg"), 500);
			jrumble();
			setTimeout('hideTop()', 1000);
			submit = false;
			return false;
		}
	});
	if (submit) {
		hideTop();
		loading('核实中..', 1);
		setTimeout("unloading()", 1000);
		setTimeout("Login()", 1000);
	}
}
//登录处理函数
function Login(){
	setCookie();
	setLocalStorge();
	var actionurl=$('form').attr('action');//提交路径
	var checkurl=$('form').attr('check');//验证路径
	var formData = new Object();
	var data=$(":input").each(function() {
		 formData[this.name] =$("#"+this.name ).val();
	});
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : checkurl,// 请求的action路径
		data : formData,
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			if (data.message.message=="200") {
				loginsuccess();
				$("#discuzLogin").html(data.discuzLogin);
				setTimeout("window.location.href='"+actionurl+"'", 1000);
			} else {
				if(data.data == "a"){
					$.dialog.confirm("数据库无数据,是否初始化数据?", function(){
						window.location = "init.jsp";
					}, function(){
						//
					});
				} else
					showError(data.data);
			}
		}
	});
}
//设置cookie
function setCookie()
{
	if ($('.remember').val() == '1') {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name, $("#"+this.name).val(), "/",24);
			$.cookie("COOKIE_NAME","true", "/",24);
		});
	} else {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name,null);
			$.cookie("COOKIE_NAME",null);
		});
	}
}
//读取cookie
function getCookie()
{
	var COOKIE_NAME=$.cookie("COOKIE_NAME");
	if (COOKIE_NAME !=null) {
		$("input[iscookie='true']").each(function() {
			$($("#"+this.name).val( $.cookie(this.name)));
		});
		$(".remember").attr("checked", true);
		$(".remember").val("1");
		$(".checkboxLabel").addClass('ischeckLabel');
	}else{
		$(".remember").attr("checked", false);
		$(".remember").val("0");
		$(".checkboxLabel").removeClass('ischeckLabel');
	}
}
/**取LocalStorge对象**/
function getLocalStorge(){
	if(window.localStorage){
		var ls = window.localStorage;
		var result = window.localStorage.getItem("userMeg");
		if(result){
			var userAndPaw = result.split('=');
			$('#userName').val(userAndPaw[0]);
			$('#password').val(userAndPaw[1]);
		}else{
			getCookie();	
		}		
	}else{
		getCookie();
	}
}
/**存LocalStorge对象**/
function setLocalStorge(){
	if(window.localStorage){
		var checked = $(".remember").attr("checked");
		if('checked' == checked){
			var userName = $('#userName').val();
			var password = $('#password').val();
			window.localStorage.setItem("userMeg", userName+"="+password);
		}else{
			localStorage.removeItem("userMeg");
		}
	}else {
		setCookie();
	}
}
//点击消息关闭提示
$('#errorMessage').click(function() {
	hideTop();
});
//显示错误提示
function showError(str) {
	//$.messager.alert('提示',str,'error');   
	$('#errorMessage').addClass('error').html('<div class="str">'+str+'</div>').stop(true, true).show().animate({
		opacity : 1
	}, 500);

}
//验证通过加载动画
function loginsuccess()
{
	$("#login").animate({
		opacity : 1,
		top : '49%'
	}, 200, function() {
		$('.userbox').show().animate({
			opacity : 1
		}, 500);
		$("#login").animate({
			opacity : 0,
			top : '60%'
		}, 500, function() {
			$(this).fadeOut(200, function() {
				$(".text_success").slideDown();
				$("#successLogin").animate({
					opacity : 1,
					height : "200px"
				}, 1000);
			});
		});
	});
}
function showSuccess(str) {
	$('#errorMessage').removeClass('error').addClass('success').html('<div class="str">'+str+'</div>').stop(true, true).show().animate({
		opacity : 1
	}, 500);
}
function hideTop() {
	$('#errorMessage').animate({
		opacity : 0
	}, 500, function() {
		$(this).hide();
	});
}
//加载信息
function loading(name, overlay) {
	$('body').append('<div id="overlay"></div><div id="preloader">' + name + '..</div>');
	if (overlay == 1) {
		$('#overlay').css('opacity', 0.1).fadeIn(function() {
			$('#preloader').fadeIn();
		});
		return false;
	}
	$('#preloader').fadeIn();
}

function unloading() {
	$('#preloader').fadeOut('fast', function() {
		$('#overlay').fadeOut();
	});
}
// 表单晃动
function jrumble() {
	$('.inner').jrumble({
		x : 4,
		y : 0,
		rotation : 0
	});
	$('.inner').trigger('startRumble');
	setTimeout('$(".inner").trigger("stopRumble")', 500);
}