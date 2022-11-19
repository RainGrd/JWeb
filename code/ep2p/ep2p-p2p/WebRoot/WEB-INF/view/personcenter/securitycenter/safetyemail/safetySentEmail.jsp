<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<div class="sidebar_b borrow_div fl">

	<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>更换已发送提醒页面 -->
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全邮箱</span>
		</div>
        <h4 class="mb40">邮件已发送</h4>
        <!-- 更改邮箱默认为1 -->
		<input type="hidden" id="isEmail" value="1">
		<div class=" hui_l borderNone size14 colorDarkBlue">
			<p class="lh22 tc ">
		        <span class="inline_block">
		        	邮件已发送到您的邮箱 ${encryptionEmail}，请到邮箱点开链接继续
		        </span>
	        </p>
		</div>
		<div class="kong35"></div>
		<a class="btn w300 m_auto displayB" onclick="toEmail('${email}',true)">立即查看邮件</a>
        <p class="lh45 tc ">
        	<i class="tc i_ ml20 d2d2d2 an_yan_mobile_s f-14" id="sendEmailCodeBut">重新发送邮件</i>
        	<i class="i_ ml20 c2980b9">
        		<a href="<%=basePath %>/login/userController/toSafetyChangeemailPage.shtml?email=${email}">选择其他方式</a>
        	</i>
        </p>
	</div>
	<script type="text/javascript">
		//邮箱对象
		var Email = {};
		//等待发送邮件
		function sendEmailTime(){
			if(Email.smsTimeTemp == 0){  
		    	 $('#sendEmailCodeBut').bind('click', function(){
		    		 safetyIndex.sureSubmit();
		    	 });
		    	 $('#sendEmailCodeBut').html("重新发送邮件");  
		    	 $('#sendEmailCodeBut').removeClass("c798383"); 
		    	 $('#sendEmailCodeBut').addClass("c2980b9"); 
		    }else{
		    	  $('#sendEmailCodeBut').html(Email.smsTimeTemp--+" 秒后重新发送邮件");  
				  setTimeout("sendEmailTime()",1000); 
		    }
		}
		$(function(){
			// 获取系统参数中配置的 发送短信间隔时间
			Email.smsTimeTemp = common.getParamValueByKey("SEND_SMS_TIME");
			sendEmailTime();
		});
	</script>
	<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/safetyIndex.js"></script>
	<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>原邮箱更换页面 -->
</div>
			