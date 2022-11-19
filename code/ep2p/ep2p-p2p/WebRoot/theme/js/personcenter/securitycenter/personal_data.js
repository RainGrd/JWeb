/**
 * 个人中心_我的资料JS类
 */
var myPerson_data = {
		pwdReg:/(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,32}$/,
	    reg: /^-?[1-9]\d*$/,
		init:function(){
			$("#jiaoYiPwd").hide();
			
		},
	// 加载数据
	loadData:function(){
		var url = BASE_PATH + "securityCenter/bankController/selectPersonData.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{data:""},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		myPerson_data.personData(data.data);
		    	}else{
		    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
		    	}
			}
		});
	},
	//我的银行卡信息
	personData:function(data){
		var fId = "";
		var fId1 = "";
		if(null != data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var bankcardNo = data[i].bankcardNo;
				//个人资料
				fId +='<div>' +
					     '<ul>' +
							'<li style="margin-bottom:15px;">安全手机' +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +'<input type="type" name="phoneNo" readonly="true" value="'+data[i].phoneNo+'"/>'+'<a href="#" style="margin-left:100px;"  >更换' +'</li>'+
							'<li style="margin-bottom:15px;">用户名' +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +'<input text="type" id="user_name" name="customerName" value="'+data[i].customerName+'"/>'+'<a href="javascript:myPerson_data.setUserName();" style="margin-left:100px;"  >设定'+'</li>'+
							'<li style="margin-buttom:15px;">安全邮箱'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'  +'<input text="type" name="email" value="'+data[i].email+'" readonly="true">'+'<a href="#" style="margin-left:100px;"  >更改'+'</li>'+
							'<li style="margin-buttom:15px;">实名认证'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +'实名认证方便日后转账和提高账户安全级别</li>'+
							'<li style="margin-buttom:15px;">联系地址' +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +'<input text="type" id="home_address" name="homeAddress" value="'+data[i].homeAddress+'">'+'<a href="javascript:myPerson_data.updateHomeAddress();" style="margin-left:100px;"  >修改'+'</li>'+
							'<li style="float:left;">紧急联系人姓名'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'  +'<input text="type" name="emergerncyName" value="'+data[i].emergerncyName+'" readonly="true">'+'</li>'+
							'<li style="float:left;margin-buttom:15px;">&nbsp;&nbsp;&nbsp;&nbsp;关系' +'&nbsp;&nbsp;&nbsp;&nbsp;' +data[i].emergerncyRelation+'</li>'+
							'<li style="margin-buttom:15px;">&nbsp;&nbsp;&nbsp;&nbsp;电话' +'&nbsp;&nbsp;&nbsp;&nbsp;' +data[i].emergerncyPhoneNo+'</li>'+
					 	'</ul>'
					'</div>';
				//银行卡列表
				var url=basePath+"securityCenter/bankController/toBankList.shtml";
				//添加银行卡
				var url1=basePath+'securityCenter/bankController/toAddBankCardPage.shtml'
				
				//安全信息
				fId1 += '<div>'+
							'<ul>'+
							    '<li>登录密码'+'&nbsp;&nbsp;&nbsp;'+data[i].loginTime+'</li>'+'</br>'+
							    '<li>交易密码'+'&nbsp;&nbsp;&nbsp;'+data[i].tradingTime+'&nbsp;&nbsp;&nbsp;'+'<a href="javascript:myPerson_data.setTradingPwd();">设定'+'<a href="javascript:myPerson_data.updateTradingPwd();">修改'+'</a>'+'</li>'+
							    '<li>银行卡'+'&nbsp;&nbsp;&nbsp;'+'<a href='+url+'>银行卡'+'</a>'+'&nbsp;&nbsp;&nbsp;'+'<a href='+url1+'>未添加，请添加'+'</a>'+'&nbsp;&nbsp;&nbsp;'+'<a href='+url+'>管理'+'</a>'+'</li>'+
							'</ul>'
					'</div>';
			}
		}
		$("#personal_data").html(fId);
		$("#personal_data1").html(fId1);
	},
	//设定交易密码
	setTradingPwd:function(){
		//iframe层
		layer.open({
		    type: 2,
		    title: '验证登录密码',
		    shadeClose: true,
		    shade: 0.8,
		    offset: ['10%', '30%'],
		    area: ['400px', '30%'],
		    content: BASE_PATH+'securityCenter/bankController/updateTradingPwd.shtml' //iframe的url
		});
	},
	//修改交易密码
	updateTradingPwd:function(){
		//iframe层
		layer.open({
		    type: 2,
		    title: '修改交易密码',
		    shadeClose: true,
		    shade: 0.8,
		    offset: ['10%', '30%'],
		    area: ['400px', '30%'],
		    content: BASE_PATH+'securityCenter/bankController/updateTradingPwdList.shtml' //iframe的url
		});
	},
	//设定交易密码的下一步事件
	next:function(){
		var loginPwd = $("#login_pass_word").val();
		if(loginPwd==null || loginPwd==""){
			$(".loginPwd_").removeAttr("style");
			$(".loginPwd_").attr("class","input_tis ts_lh w210 mb5 ml99 tc loginPwd_ ");
			$(".loginPwd_").html("登录密码不能为空!");
		}else{
		var url = BASE_PATH + "securityCenter/bankController/validateTradingPwd.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{"loginPassWord":loginPwd},
			dataType: "json",
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.count >0){
		    			// div 隐藏
						$(".loginPwd").attr("class","lh30 w360 m_auto none");
						$(".tradPwd").attr("class","lh30 w360 m_auto"); 
						$(".sureTradPwd").attr("class","lh30 w360 m_auto"); 
						$(".save_").attr("class","save_"); //显示保存按钮
						$(".next_").attr("class","none"); //隐藏下一步
		    		}else{
		    			$(".loginPwd_").removeAttr("style");
		    			$(".loginPwd_").attr("class","input_tis ts_re w210 mb5 ml99 tc loginPwd_ ");
		    			$(".loginPwd_").html("登录密码错误!");
		    		}
		    	}else{
		    		layer.alert("数据加载失败,请联系管理员!", {icon: 5});
		    	}
			}
		});
		}
	},
	//保存交易密码
	save:function(){
		var oldPwd = rsa.encode64($("#trad_pass_word").val());
		var str =$("#trad_pass_word").val();
		var newPwd = rsa.encode64($("#sureTrad_pass_word").val());
		if(oldPwd==null || oldPwd ==""){
			$(".tradPwd_").css("display","");
			$(".tradPwd_").attr("class","input_tis ts_lh w205 mb5  tc tradPwd_");
			$(".tradPwd_").html("交易密码不可为空!");
		}
		else if(newPwd==null || newPwd ==""){
			$(".suretradPwd_").css("display","");
			$(".suretradPwd_").attr("class","input_tis ts_lh w205 mb5  tc suretradPwd_");
			$(".suretradPwd_").html("确认交易密码不可为空!");
		}
		else if(oldPwd != newPwd){
			$(".suretradPwd_").css("display","");
			$(".suretradPwd_").attr("class","input_tis ts_lh w205 mb5  tc suretradPwd_");
			$(".suretradPwd_").html("交易密码与确认交易密码不一致!");
		}else if(!myPerson_data.reg.test(newPwd) && str.length !=6){
			$(".suretradPwd_").css("display","");
			$(".suretradPwd_").attr("class","input_tis ts_lh w205 mb5  tc suretradPwd_");
			$(".suretradPwd_").html("交易密码只能是6位数字!");
		}
		else{
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"/securityCenter/bankController/updateTradingPwdByCustId.shtml",
	    	data:{"newPwd":newPwd},
			dataType: "json",
			async:false, 
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.count >0){
		    			parent.location.reload(false);
		    		}
		    	}else{
		    		layer.msg('设置交易密码失败!', {icon: 5});
		    	}
			}
		});
		}
		
	},
	//保存交易按钮事件
	saveTradPwd:function(){
		var oldPwd = rsa.encode64($("#oldPwd").val());//加密后的为空的话值等于AA==
		var new_pwd = rsa.encode64($("#newPwd").val());
		var sureNewPwd = rsa.encode64($("#sureNewPwd").val());
		var sureNewPwd1 = $("#sureNewPwd").val();
		
		if(oldPwd=='AA=='){
			$("#oldPwd_s").css('display','block');
			$("#oldPwd_s").html("原密码不能为空！");
		}else if(oldPwd !='AA=='){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/securityCenter/bankController/searcherTradingPwd.shtml",
		    	data:{"oldPwd":oldPwd},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		if(data.flag){
			    			if(new_pwd=='AA=='){
			    				$("#newPwd_s").css('display','block');
			    				$("#newPwd_s").html("新密码不能为空！"); 
			    				$("#oldPwd_s").css("display","none");
			    			}else if(sureNewPwd=='AA=='){
			    				$("#sureNewPwd_s").css('display','block');
			    				$("#sureNewPwd_s").html("确认新密码不能为空！");
			    				$("#oldPwd_s").css('display','none');
			    				$("#newPwd_s").css('display','none');
			    			}else if(new_pwd != sureNewPwd){
			    				$("#sureNewPwd_s").css('display','block');
			    				$("#sureNewPwd_s").html("两次输入的密码不一致！");
			    				$("#oldPwd_s").css('display','none');
			    				$("#newPwd_s").css("display","none");
			    			}else if(!myPerson_data.reg.test(sureNewPwd) && sureNewPwd1.length !=6){
			    				$("#sureNewPwd_s").css('display','block');
			    				$("#sureNewPwd_s").html("登录密码只能为 6个纯数字");
			    			}else{
			    				//保存心的交易密码
			    				myPerson_data.saveNewTradPassWord(sureNewPwd);
			    			}
			    		}else{
			    			$("#oldPwd_s").css('display','block');
							$("#oldPwd_s").html("原密码不正确！");
							$("#newPwd_s").css('display','none');
							$("#sureNewPwd_s").css('display','none');
							return;
			    		}
			    	}
			    }
			});
		}
	},
	//保存修改后的交易密码
	saveNewTradPassWord:function(sureNewPwd){
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"/securityCenter/bankController/updateTradingPwdByCustId.shtml",
	    	data:{"newPwd":sureNewPwd},
			dataType: "json",
			async:false, 
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.count > 0){
//		    			layer.msg('密码修改成功!', {icon: 5});
		    			//关闭当前窗口
		    			parent.layer.close(parent.layer.getFrameIndex(window.name));
		    		}
		    	}
		    }
		});
	},
	//设定用户名(系统用户和客户没有当前设定的名字才能设定)
	setUserName:function(){
		var userName = $("#user_name").val();
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"/securityCenter/bankController/selectUserName.shtml",
	    	data:{"userName":userName},
			dataType: "json",
			async:false, 
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.count > 0){
		    			layer.msg('该用户名已经存在!', {icon: 5});
		    		}else{
		    			$.ajax({
		    				type: "POST",
		    		    	url : BASE_PATH+"/securityCenter/bankController/updateUserData.shtml",
		    		    	data:{"userName":userName},
		    				dataType: "json",
		    				async:false, 
		    			    success: function(data){
		    			    	if(data.message.status ==200){
		    			    		if(data.count >0){
		    			    			layer.msg('用户名设定成功!', {icon: 5});
		    			    		}
		    			    	}
		    			    }
		    			});
		    		}
		    	}
		    }
		});
	},
}

$(document).ready(function(){
	myPerson_data.loadData();
	myPerson_data.init();
});