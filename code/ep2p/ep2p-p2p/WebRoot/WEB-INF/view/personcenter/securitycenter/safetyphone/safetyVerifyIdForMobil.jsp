<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>更换验证身份证页面 -->
<div class="sidebar_b borrow_div fl">
<div class="gh_m">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">更换安全手机</span>
	</div>
	<div class="gh_m_ico size14 colorDarkBlue hui_l">
		<div class="kong30"></div>
		<div class="gh_m_ico_c fl_">
			<span class="gh_m_ico_list tc" >
				<img src="<%=basePath%>theme/default/images/an_14.png" class="tc" />
				<span class="lh55 ">验证身份证</span>
			</span>
		</div>
		<div class="gh_m_ico_c fl_ w330">
			<p class="hui_l kong30"></p>
		</div>
		<div class="gh_m_ico_c fl_">
			<span class="gh_m_ico_list tc">
				<img src="<%=basePath%>theme/default/images/an_8.png" class="tc" />
				<span class="lh55 ">验证新手机</span>
			</span>
		</div>
		<div class="gh_m_ico_c fl_ w330">
			<p class="hui_l kong30"></p>
		</div>
		<div class="gh_m_ico_c fl_">
			<span class="gh_m_ico_list tc">
				<img src="<%=basePath%>theme/default/images/an_7.png" class="tc" />
				<span class="lh55 ">成功</span>
			</span>
		</div>
	</div>
	<div class=" hui_l size14 colorDarkBlue">
		<p class="kong20"></p>
		<p class="lh45 w400 ">
	        <span class="w95 inline_block tl">
	        	<i class="i_ colorc mr10"></i>用户名:
	        </span>
        	<span class="">
        		${getCustomerName}
        	</span>
        </p>
        <p class="lh45  ">
	        <span class="w95 inline_block tl">
	        	<i class="i_ colorc mr10">*</i>身份证号码:
	        </span>
        	<span class="">
        		<input type="text" class="w220 pl10" id="idCard" onblur="verificeIdCard()">
        		<br />
				 <span class="input_tis ts_lh w215 mb5 none " id="idCard_message" style="margin-left: 98px;">
				     <!--  ts_re 是红色警告背景  
				      ts_lh 是黄色警告背景
				      ts_lv 是绿色正确提示背景	 -->
				          请正确输入您的身份证号码
				</span>
        	</span>
        </p>
        <p class=" w360 mt10 ">
	        <span class="w95 inline_block tl">
	        	<i class="i_ colorc mr10">*</i>交易密码:
	        </span>
        	<span class="">
        		<input type="password" class="w220 pl10" id="tradePassword">
        			<br />
					 <span class="input_tis none ts_lh w215 mb5 block " style="margin-left: 98px;">
					<!-- 	ts_re 是红色警告背景  
						ts_lh 是黄色警告背景
						ts_lv 是绿色正确提示背景 -->
									交易密码不正确！
					</span>
	        	</span>
	        </p>
	         <p class="kong45"></p>
		</div>
		<div class="kong35"></div>
		<a class="btn" onclick="nextStep();">下一步</a>
	</div>
	<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>
<script type="text/javascript">
	var isIdCard = false;
	//验证身份证号
	function verificeIdCard(){
		var idCard = $("#idCard").val();
		if(isCardNo(idCard)){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"login/userController/getIdCard.shtml",
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data && data.idCard){
			    		if(data.idCard != idCard){
			    			$("#idCard_message").html = "您输入的身份证号与您实名制认证的身份证号不匹配、请仔细检查！";
			    			//3、移除样式
			    			$("#idCard_message").removeClass("none");
			    			return;
			    		}else{
			    			isIdCard = true;
			    		}
			    	}else{
			    		$("#idCard_message").html = "您的身份证号还未做实名制认证、请您认证之后再来修改！";
		    			//3、移除样式
		    			$("#idCard_message").removeClass("none");
			    	}
			    }
			});
		}
	}
	// 验证身份证号是否合法
	function isCardNo(idCard){  
		if(!idCard || idCard==""){
			$("#idCard_message").html = " 身份证号码不能为空！";
			//3、移除样式
			$("#idCard_message").removeClass("none");
			//2、追加样式
			//$("#idCard_message").addClass("ts_lh")
			return false;
		}
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   if(reg.test(idCard) === false){  
		   $("#idCard_message").html = "请正确输入您的身份证号码！";
			//3、移除样式
			$("#idCard_message").removeClass("none");
	       return false;  
	   }
	   $("#idCard_message").addClass("none")
	   return true;
	}
	//下一步		
	function nextStep(){
		if(isIdCard){
			var tradePassword = $("#tradePassword").val();
			if(!tradePassword || tradePassword==""){
				layer.msg('交易密码不能为空!', {
					icon : 5
				});
				return;
			}
			var oldPwd = rsa.encode64(tradePassword);//加密后的为空的话值等于AA==
			var idCard = $("#idCard").val();
				$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/securityCenter/bankController/searcherTradingPwd.shtml",
		    	data:{"oldPwd":oldPwd},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		if(data.flag){
			    			//跳转到跟换邮箱选择页面
			    			window.location.href = BASE_PATH+"login/userController/toSsafetyNewMobilePage.shtml";
			    		}else{
			    			layer.msg('交易密码错误! 请您重新输入', {
								icon : 5
							});
							return;
			    		}
			    	}
			    }
			});
		}
	}
</script>
</div>