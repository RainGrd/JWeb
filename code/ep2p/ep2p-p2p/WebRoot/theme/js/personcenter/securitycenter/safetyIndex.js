/**
 * 个人中心-安全中心-主页
 */
var emcontPid;// 紧急联系人id
var bankNumber = 0;// 银行卡张数
var temp = 0;
var safetyIndex = {
	smsTime : 0,
	sendEmailTemp : 0,
	initfunc : function() {
		$(".goumai_vip_d_meial_but").click(function() {
			$(".goumai_vip_d_meial").addClass("none").eq(1).removeClass("none")

		})

	},
	// 加载数据
	loadData : function() {
		var url = BASE_PATH
				+ "securityCenter/bankController/selectPersonData.shtml";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				data : ""
			},
			dataType : "json",
			success : function(data) {
				if (data.message.status == 200) {
					safetyIndex.personData(data.data);
				} else {
					$.messager.alert("操作提示", "数据加载失败,请联系管理员!", "error");
				}
			}
		});
	},

	// 填充个人资料数据
	personData : function(data) {
		var html = "";
		if (null != data && data.length > 0) {
			for (var i = 0; i < data.length; i++) {
				var phone = data[i].phoneNo;
				var myphone = phone.substr(3, 4);
				var iphone = phone.replace(myphone, "****");
				$("#phoneNo").html(iphone);
				$("#userName").html(data[i].customerName);
				$("#email").html(data[i].email);
				$(".homeAddress").html(data[i].homeAddress);
				$(".emergerncyName").html(data[i].emergerncyName);// 紧急联系人
				$(".tradTime").html(data[i].tradingTime);// 交易时间
				$(".loginTime").html(data[i].loginTime);// 交易时间
				if (data[i].emergerncyRelation == 1) {
					$('.ddd').html('亲人');
				}
				if (data[i].emergerncyRelation == 2) {
					$('.ddd').html('朋友');
				}
				// 如果交易密码为空，就显示为设定按钮，否则就修改
				var tradPwd = data[i].tradePassword;
				if (tradPwd == null || tradPwd == "") {
					$(".set_tradpwd").attr("class",
							"i_ mr10 cus_p jiaoyi_key_js");
				} else {
					$(".update_tradpwd").attr("class",
							"i_ mr10 cus_p jiaoyi_key_js");
				}
				$(".emergerncyPhoneNo").html(data[i].emergerncyPhoneNo);// 紧急联系方式
				// 修改图片状态
				if (data[i].customerName != null && data[i].customerName != "") {
					$(".updateCustName").css({
						"color" : "#798383",
						"cursor" : "no-drop"
					});
					$(".updateCustName").attr("onclick", "");
					$(".username_").attr("im", 1);
				} else {
					$(".updateCustName").attr("class",
							"fr_ c2980b9 cus_p updateCustName");// 用户名
				}
				//安全手机
				if(data[i].phoneNo && data[i].phoneNo != ""){
					$(".phoneBd").html("更换");
					safetyIndex.phoneNo = data[i].phoneNo;
					safetyIndex.isPhoneNo = 1;
				}
				// 安全邮箱
				if (data[i].email != null && data[i].email != "") {
					$(".unb_").html("保护中");
					$(".unb_").attr("class", " ml10 mr40 unb_ c96bc7f");
					$(".softEmail_").attr("src",
							"/ep2p/theme/default/images/an_1.png");
					$(".emailBd").html("更换");
					$(".softEmail_").attr("im", 1);
					safetyIndex.isEmail = 1;
					safetyIndex.email = data[i].email;
					temp = temp + 10;
				}
				// 实名认证
				if (data[i].isAttestation == '2') {
					$(".smrz_").html("已认证");
					$(".smrz_").attr("class", " ml10 mr40 unb_ c96bc7f");
					$(".smrzPic").attr("src",
							"/ep2p/theme/default/images/an_1.png");
					$(".smrzOk").html("认证");
					$(".smrzPic").attr("im", 1);
					temp = temp + 20;
				}
				// 用户名
				if (data[0].customerName != "" && data[0].customerName != null) {
					$(".unset_").html("已设定");
					$(".unset_").attr("class", "ml10 mr40 unset_ c96bc7f");
					$(".username_").attr("src",
							"/ep2p/theme/default/images/an_1.png")
					$(".username_").attr("im", 1);
					temp = temp + 5;
				}
				// 联系地址
				if (data[0].homeAddress != null) {
					$(".updateAddress").html("修改");
					$(".unaddress_").html("已设定");
					$(".unaddress_").attr("class",
							" ml10 mr40 unaddress_ c96bc7f");
					$(".addres_").attr("src",
							"/ep2p/theme/default/images/an_1.png");
					$(".addres_").attr("im", 1);
					temp = temp + 5;
				}

				emcontPid = data[i].emergencyContactPid;// 紧急联系人id
				// 紧急联系人pid
				if (emcontPid != null && emcontPid != "") {
					$(".relat_").html("已设定");
					$(".relat_").attr("class", " ml10 mr40 unaddress_ c96bc7f");
					$(".relation_").attr("src",
							"/ep2p/theme/default/images/an_1.png");
					$(".updateEmergerncyName").html("修改");
					$(".relation_").attr("im", 1);
					temp = temp + 5;
				}
				// 安全手机 ：计算安全得分
				if (phone != null && phone != "") {
					$(".phone_").attr("im", 1);
					temp = temp + 10;
				}
				// 登录密码
				if (data[i].password != null && data[i].password != "") {
					$(".loginPwd_").attr("im", 1);
					temp = temp + 20;
				}
				// 交易密码
				if (data[i].tradePassword != null
						&& data[i].tradePassword != "") {
					$(".trads_").html("已设定");
					$(".trads_").attr("class", " ml10 mr40 trads_ c96bc7f");
					$(".tradss_").attr("src",
							"/ep2p/theme/default/images/an_1.png");
					$(".tradss_").attr("im", 1);
					temp = temp + 20;
				}
			}
			// 查询银行卡张数
			safetyIndex.selectBankCountByCustId();
		}
	},

	// 修改用户名
	updateCustName : function() {
		$('.updateCustName').html('保存');
		$('.updateCustName').attr("onclick", "safetyIndex.saveCustName()");
		// 设置为可编辑状态
		$('#userName')
				.html(
						'<input text="type" name="customerName" id="customer_name" value="">');
	},

	// 保存用户名(系统用户和客户没有当前设定的名字才能设定)
	saveCustName : function() {
		var userName = $("#customer_name").val();
		$.ajax({
					type : "POST",
					url : BASE_PATH
							+ "/securityCenter/bankController/selectUserName.shtml",
					data : {
						"userName" : userName
					},
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.message.status == 200) {
							if (data.count > 0) {
								layer.msg('该用户名已经存在!', {
									icon : 5
								});
							} else {
								$.ajax({
											type : "POST",
											url : BASE_PATH
													+ "/securityCenter/bankController/updateUserData.shtml",
											data : {
												"userName" : userName
											},
											dataType : "json",
											async : false,
											success : function(data) {
												if (data.message.status == 200) {
													if (data.count > 0) {
														layer.msg('用户名设定成功!', {
															icon : 5
														});
														$('.userName').html(
																userName);
														location.reload(false);
													}
												}
											}
										});
							}
						}
					}
				});
	},

	// 修改联系地址
	updateHomeAddress : function() {
		$('.updateAddress').html('保存');
		// 为保存按钮添加事件
		$('.updateAddress').attr('onclick', "safetyIndex.saveHomeAddress()");
		// 设置为可编辑状态
		$('.homeAddress').html(
				'<input text="type" name="homeAddress" id="home_address" value='
						+ $('.homeAddress').html() + '>');
	},

	// 保存联系地址
	saveHomeAddress : function() {
		var homeAddress = $('#home_address').val();
		$.ajax({
			type : "POST",
			url : BASE_PATH
					+ "/securityCenter/bankController/updateHomeAddress.shtml",
			data : {
				"homeAddress" : homeAddress
			},
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.message.status == 200) {
					if (data.count > 0) {
						layer.msg('联系地址修改成功!', {
							icon : 5
						});
						$('.homeAddress').html(homeAddress);
						$('.updateAddress').html('修改');
						location.reload(false);

					}
				}
			}
		});
	},
	//修改手机号码
	updatePhone : function(){
		//获取手机号码
		var phoneNo = $("#phoneNo").val();
		
	},
	// 跳转到绑定邮箱的页面
	bindEmailPhonePage : function() {
		//根据isEmail判断邮箱是否已绑定（如果isEmail==1[已绑定] 走更换邮箱逻辑）
		if (safetyIndex.isPhoneNo == 1) {
			//跳转到跟换邮箱选择页面
			window.location.href = BASE_PATH+"login/userController/toSafetyChanemodePage.shtml?phone="+safetyIndex.phoneNo;
		}else{
			// iframe层
			layer.open({
				type : 2,
				title : '',
				shadeClose : true,
				shade : 0.8,
				offset : [ '10%', '30%' ],
				area : [ '1000px', '60%' ],
				content : BASE_PATH
						+ 'securityCenter/bankController/toBindEmail.shtml?isEmail='
						+ safetyIndex.isEmail// iframe的url
			});
		}
	},
	// 修改紧急联系人
	updateEmergerncyName : function() {
		$('.updateEmergerncyName').html('保存');
		$('.updateEmergerncyName').attr('onclick',
				"safetyIndex.saveEmergerncyName()");
		$('.emergerncyName').html(
				'<input text="type" name="emergerncyName" id="emergerncy_name" value='
						+ $('.emergerncyName').html() + '>');
		$('.emergerncyRelation')
				.html(
						'<select class=""><option value="1">亲人</option><option value="2">朋友</option></select>');
		$('.emergerncyPhoneNo').html(
				'<input text="type" name="emergerncyPhoneNo" id="emergerncy_phoneNo" value='
						+ $('.emergerncyPhoneNo').html() + '>');
	},

	// 保存紧急联系人
	saveEmergerncyName : function() {
		var emergerncyName = $("#emergerncy_name").val();
		var relation = $("select").val();
		var emergerncyPhoneNo = $("#emergerncy_phoneNo").val();
		$.ajax({
					type : "POST",
					url : BASE_PATH
							+ "/securityCenter/bankController/updateEmergencyContact.shtml",
					data : {
						"emergerncyName" : emergerncyName,
						"relation" : relation,
						"emergerncyPhoneNo" : emergerncyPhoneNo,
						"emcontPid" : emcontPid
					},
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.message.status == 200) {
							if (data.count > 0) {
								layer.msg('紧急联系人信息修改成功!', {
									icon : 5
								});
								$('.emergerncyName').html(emergerncyName);
								$('.emergerncyRelation').html(relation);
								$('.emergerncyPhoneNo').html(emergerncyPhoneNo);
								location.reload(false);
							}
						}
					}
				});
	},

	// 设定交易密码
	setTradPwd : function() {
		// iframe层
		layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : 0.8,
			offset : [ '10%', '30%' ],
			area : [ '700px', '35%' ],
			content : BASE_PATH
					+ 'securityCenter/bankController/updateTradingPwd.shtml' // iframe的url
		});
	},

	// 修改交易密码弹窗
	toupdateTrad : function() {
		// iframe层
		layer
				.open({
					type : 2,
					title : '',
					shadeClose : true,
					shade : 0.8,
					offset : [ '10%', '30%' ],
					area : [ '700px', '35%' ],
					content : BASE_PATH
							+ 'securityCenter/bankController/updateTradingPwdList.shtml' // iframe的url
				});
	},

	// 修改登录密码
	toupdateLoginPwd : function() {
		// iframe层
		layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : 0.8,
			offset : [ '10%', '30%' ],
			area : [ '700px', '35%' ],
			content : BASE_PATH
					+ 'securityCenter/bankController/toupdateLoginPwd.shtml' // iframe的url
		});
	},

	// 忘记登录密码事件
	forgetLoginPwd : function() {
		// window.location.href =
		// BASE_PATH+'securityCenter/bankController/toForgetLoginPwdPage.shtml';
		window.location.href = BASE_PATH
				+ 'login/userController/toRetrievePage.shtml';
	},

	// 忘记交易密码事件
	forgetTradPwd : function() {
		window.location.href = BASE_PATH
				+ 'login/userController/toRetrievePage.shtml?flag=1';
	},

	// 银行卡管理列表
	banKManger : function() {
		// var str = $(".smrz_").html();//实名认证
		// if(str="未认证"){
		// layer.msg('实名认证后才能添加银行卡!', {icon: 5});
		// }else{
		window.location.href = BASE_PATH
				+ 'securityCenter/bankController/toBankList.shtml';
		// }
	},

	// 查询银行卡数量
	selectBankCountByCustId : function() {
		var url = BASE_PATH + "securityCenter/bankController/selectBankListByCusId.shtml";
		$.ajax({
					type : "POST",
					url : url,
					data : {
						data : ""
					},
					dataType : "json",
					success : function(data) {
						if (data.message.status == 200) {
							var count = data.count;
							// 绑定了银行卡
							if (count > 0) {
								$(".bankNum").html(count + '张银行卡');
								$(".bank_").html("已设定");
								$(".bank_").attr("class",
										"ml10 mr40 bank_ c96bc7f");
								$(".bank_ts").attr("src",
										"/ep2p/theme/default/images/an_1.png")
								// 如果银行卡
								bankNumber = 1;
								if (bankNumber > 0) {
									$(".bank_ts").attr("im", 1);
									temp = temp + 5;
								}
								$(".securityScore").html(
										'您当前的安全得分为' + temp + '分');
								$(".an_x_jd_lv").css("width", temp + "%");
								$(".an_x_jd_jifen").html(temp);
								if (temp >= 75) {
									$(".jindu_")
											.attr("class",
													"i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_7 jindu_");
								}
								if (temp >= 50 && temp < 75) {
									$(".jindu_")
											.attr("class",
													"i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_5 jindu_");
								}
								if (temp < 50) {
									$(".jindu_")
											.attr("class",
													"i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_2 jindu_");
								}

							}
							// 没有绑定银行卡
							if (count == 0) {
								$(".securityScore").html(
										'您当前的安全得分为' + temp + '分');
								$(".an_x_jd_lv").css("width", temp + "%");
								$(".an_x_jd_jifen").html(temp);
								if (temp >= 75) {
									$(".jindu_")
											.attr("class",
													"i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_7 jindu_");
								}
								if (temp >= 50 && temp < 75) {
									$(".jindu_")
											.attr("class",
													"i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_5 jindu_");
								}
								if (temp < 50) {
									$(".jindu_")
											.attr("class",
													"i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_2 jindu_");
								}
							}

						} else {
							$.messager.alert("操作提示", "数据加载失败,请联系管理员!", "error");
						}

					}
				});
	},

	// 添加银行卡
	addBankCare : function() {
		// 校验是否实名认证过
		$
				.ajax({
					type : "POST",
					url : BASE_PATH
							+ "/securityCenter/bankController/validateIsAttestation.shtml",
					data : {
						"" : ""
					},
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.message.status == 200) {
							if (data.count > 0) {
								safetyIndex.toAddBankCardPage();
							} else {
								layer.msg('实名认证后才能添加银行卡!', {
									icon : 5
								});
							}
						}
					}
				});
	},
	// 跳转到添加银行卡页面
	toAddBankCardPage : function() {
		// iframe层
		layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : 0.8,
			offset : [ '10%', '30%' ],
			area : [ '700px', '48%' ],
			content : BASE_PATH
					+ 'securityCenter/bankController/toAddBankCardPage.shtml'// iframe的url
		});
	},

	// 跳转到绑定邮箱的页面
	bindEmail : function() {
		//根据isEmail判断邮箱是否已绑定（如果isEmail==1[已绑定] 走更换邮箱逻辑）
		if (safetyIndex.isEmail == 1) {
			//跳转到跟换邮箱选择页面
			window.location.href = BASE_PATH+"login/userController/toSafetyChangeemailPage.shtml?email="+safetyIndex.email;
		}else{
			// iframe层
			layer.open({
				type : 2,
				title : '',
				shadeClose : true,
				shade : 0.8,
				offset : [ '10%', '30%' ],
				area : [ '700px', '35%' ],
				content : BASE_PATH
						+ 'securityCenter/bankController/toBindEmail.shtml?isEmail='
						+ safetyIndex.isEmail// iframe的url
			});
		}
	},

	// 确定提交
	sureSubmit : function() {
		// 判断文本框是否有值
		var email = $("#email_").val();
		// 是否已绑定邮箱(0未绑定、1已绑定)
		var isEmail = $("#isEmail").val();
		if (email == null || email == '') {
			layer.msg('请输入邮箱地址!', {
				icon : 5
			});
			return false;
		} else {
			// 发送邮件
			safetyIndex.sendEmail(email, isEmail);
		}
	},

	// 发送邮件
	sendEmail : function(email, isEmail) {
		safetyIndex.sendEmailTemp = safetyIndex.smsTime;
		var emailAddress = email;
		var result = false;
		$.ajax({
			type : "POST",
			url : BASE_PATH + "/securityCenter/bankController/sendEmail.shtml",
			data : {
				"emailAddress" : emailAddress,
				"isEmail" : isEmail
			},
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.message.status == 200) {
					parent.window.location.href = BASE_PATH + "/login/userController/toSafetySentEmailPage.shtml?email="+data.email;
					//setTimeout("toEmail('"+data.email+"')", 3000);
				} else {
					layer.msg('发送邮件失败,请联系管理员!', {
						icon : 5
					});
				}
			}
		});
		return result;
	},

	// 邮箱返回到安全中心的
	retrunSafeCenter : function() {
		window.location.href = BASE_PATH
				+ 'securityCenter/bankController/personalData.shtml';
	}

};
function toEmail(emailAddres, isOpen) {
	debugger;
	var uurl = gotoEmail(emailAddres);
	if (uurl != "") {
		if(isOpen==true){
			window.open("http://" + uurl);
		}else{
			window.location.href = "http://" + uurl;
		}
	} else {
		alert("抱歉!未找到对应的邮箱登录地址，请自己登录邮箱查看邮件！");
	}
}

// 功能：根据用户输入的Email跳转到相应的电子邮箱首页
function gotoEmail($mail) {
	$t = $mail.split('@')[1];
	$t = $t.toLowerCase();
	if ($t == '163.com') {
		return 'mail.163.com';
	} else if ($t == 'vip.163.com') {
		return 'vip.163.com';
	} else if ($t == '126.com') {
		return 'mail.126.com';
	} else if ($t == 'qq.com' || $t == 'vip.qq.com' || $t == 'foxmail.com') {
		return 'mail.qq.com';
	} else if ($t == 'gmail.com') {
		return 'mail.google.com';
	} else if ($t == 'sohu.com') {
		return 'mail.sohu.com';
	} else if ($t == 'tom.com') {
		return 'mail.tom.com';
	} else if ($t == 'vip.sina.com') {
		return 'vip.sina.com';
	} else if ($t == 'sina.com.cn' || $t == 'sina.com') {
		return 'mail.sina.com.cn';
	} else if ($t == 'tom.com') {
		return 'mail.tom.com';
	} else if ($t == 'yahoo.com.cn' || $t == 'yahoo.cn') {
		return 'mail.cn.yahoo.com';
	} else if ($t == 'tom.com') {
		return 'mail.tom.com';
	} else if ($t == 'yeah.net') {
		return 'www.yeah.net';
	} else if ($t == '21cn.com') {
		return 'mail.21cn.com';
	} else if ($t == 'hotmail.com') {
		return 'www.hotmail.com';
	} else if ($t == 'sogou.com') {
		return 'mail.sogou.com';
	} else if ($t == '188.com') {
		return 'www.188.com';
	} else if ($t == '139.com') {
		return 'mail.10086.cn';
	} else if ($t == '189.cn') {
		return 'webmail15.189.cn/webmail';
	} else if ($t == 'wo.com.cn') {
		return 'mail.wo.com.cn/smsmail';
	} else if ($t == '139.com') {
		return 'mail.10086.cn';
	} else {
		return '';
	}
};
//加载文件
(function() {
	//勾选 效果调用,提交父容器参数 和全部勾选按钮参数
	//	escfutil.gougou(".tan_ka_js_g",".gou_s_js");
	//修改邮箱确定 的下一个页面
	safetyIndex.initfunc();
	safetyIndex.loadData();

})();