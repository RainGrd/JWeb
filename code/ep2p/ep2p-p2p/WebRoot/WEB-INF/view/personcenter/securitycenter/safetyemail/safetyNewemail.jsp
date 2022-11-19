<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/safetyIndex.js"></script>
<div class="sidebar_b borrow_div fl">
	<div class="gh_m">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">更换安全邮箱</span>
		</div>
		<h4 class="mb40">填写新邮箱</h4>
		<div class=" hui_l borderNone size14 colorDarkBlue">
			<p class="lh22 tc ">
				<span class="inline_block">
					原电子邮箱验证通过，请填写您的新的电子邮箱，我们将会向该邮箱发送验证链接，请注意查收 </span>
			</p>
			<p class=" mt10 ">
				<span class="w49_ inline_block tr mr20"> <i
					class="i_ colorc mr10">*</i>填写新邮箱
				</span> 
				<span class=""> 
					<input type="text" class="w220 pl10" id="email_" onblur="return emailCheck('email_')">
					<span class="input_tis w215 mb5 block "
						style="margin-left: 44.6%;" id="prompt_message"> 
						<!--ts_re 是红色警告背景  
						ts_lh 是黄色警告背景
						ts_lv 是绿色正确提示背景   邮箱格式不正确！-->
					</span>
				</span>
			</p>
		</div>
		<div class="kong35"></div>
		<a class="btn w300 m_auto displayB" onclick="safetyIndex.sureSubmit()">下一步</a>
	</div>
	<script type="text/javascript">
		//检验填写的邮箱格式
		function emailCheck(obj) {
			var objName = eval("document.all."+obj);
			var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
			if (!pattern.test(objName.value)) {
				//移除样式
				$("#prompt_message").removeClass("ts_lv")
				//追加样式
				$("#prompt_message").addClass("ts_lh")
				$("#prompt_message").html("邮箱格式不正确！");
				objName.focus();
				return false;
			}
			//3、移除样式
			$("#prompt_message").removeClass("ts_lh")
			$("#prompt_message").html("");
			return true;
		}
	</script>
</div>