<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!-- 安全中心>>>个人资料>>>更改安全邮箱>>>原邮箱更换页面 -->
<div class="sidebar_b borrow_div fl">
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全邮箱</span>
		</div>
		<h4>验证原邮箱</h4>
		<div class=" hui_l borderNone size14 colorDarkBlue">
			<p class="lh45 ">
				<span class="w49_ inline_block tr mr20"> <i
					class="i_ colorc mr10"></i>已绑定邮箱
				</span> <span class="">${encryptionEmail}</span>
				<input type="hidden" id="email_" value="${email}">
				<!-- 更改邮箱默认为1 -->
				<input type="hidden" id="isEmail" value="1">
			</p>
			<p class="lh45  ">
				<span class="w49_ inline_block tr mr20"> <i
					class="i_ colorc mr10">*</i>验证码
				</span> 
				<span class=""> 
					<input type="text" class="w100 pl10" name="kaptcha" id="kaptcha">
					<i class="i_ ml20 c2980b9">
						<img title = "点我换一张" alt="点我换一张" id="kaptchaImg" src="${basePath}kaptcha.jpg" 
							style="cursor:pointer;" onclick="common.changeVerifyCode();">
					</i>
				</span>
			</p>
			<p class="kong35"></p>
			<a class="btn w300 m_auto displayB" onclick="updateEmail();">发送验证邮件</a>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/safetyIndex.js"></script>
	<script type="text/javascript">
	//修改邮箱
	function updateEmail(){
		//获取验证码
		var kaptcha = $("#kaptcha").val();
		if(!kaptcha || kaptcha==""){
			layer.msg('验证码不能为空!', {icon: 5});
			return;
		}
		$.ajax({
			type: "POST",
	    	url : BASE_PATH+"/login/userController/kaptchaValidate.shtml",
	    	data:{"kaptcha":kaptcha},
			dataType: "json",
			async:false, 
		    success: function(data){
		    	if(data.message.status ==200){
		    		if(data.result==true){
		    			safetyIndex.sureSubmit();
		    		}else{
		    			layer.msg('验证码错误!', {icon: 5});
		    		}
		    		result = data.result;
		    	}else{
		    		layer.msg('验证验证码失败啦!', {icon: 5});
		    	}
			}
		});
	}
	</script>
</div>
