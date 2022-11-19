<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!-- 设定交易密码 s-->
		<div class="goumai_vip_b_b ">
			<div class="vip_g_bs_b m_auto ">
				<div class="ge_ye_tilte">
							<span class="ge_ye_tilte_nav  ms_t_se">设定交易密码</span>
				</div>
				<div class="kong35"></div>
				<div class="vip_g_lists m_auto colorDarkBlue size14">
					<p class="lh30 w360 m_auto loginPwd">
						<span class="w95 inline_block tl"><i class="i_ colorc mr10">*</i>登录密码:</span><span class="">
							<input type="password" class="w230 pl10 " id="login_pass_word" name="loginPassWord" value=""/>
						<span class="input_tis ts_lh w210 mb5 ml99 tc loginPwd_ " style="display:none;">
<!-- 									ts_re 是红色警告背景  
<!-- 									ts_lh 是黄色警告背景 -->
<!-- 									ts_lv 是绿色正确提示背景--> 
<!-- 										登录密码不能为空 -->
						</span>
						 </span>
					</p>
					<p class="lh30 w360 m_auto none tradPwd">
						<span class="w104 inline_block tl"><i class="i_ colorc mr10">*</i>交易密码:</span><span class="">
							<input type="password" class="w220 " id="trad_pass_word" name="tradPassWord" value=""/>
						<span class="input_tis ts_lh w205 mb5  tc tradPwd_" style="margin-left: 107px; display: none;">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景
									登录密码不能为空-->
						</span>
						</span>
					</p>
					<p class="lh30 w360 m_auto none sureTradPwd">
						<span class="w104 inline_block tl"><i class="i_ colorc mr10">*</i>确认交易密码:</span><span class="">
							<input type="password" class="w220 " id="sureTrad_pass_word" name="sureTradPassWord" value=""/>
						<span class="input_tis ts_lh w205 mb5 tc suretradPwd_" style="margin-left: 107px; display: none">
<!-- 									ts_re 是红色警告背景  
<!-- 									ts_lh 是黄色警告背景 -->
<!-- 									ts_lv 是绿色正确提示背景--> 
<!--  										登录密码不能为空 --> 
						</span>
						</span>
					</p>
					
					<p class="kong15"></p>
					<p class="lh45 w360 m_auto">
						<span class="w115 inline_block tr h10"></span>
						<span class="next_"><a class="btn_samll ml15 " href="javascript:myPerson_data.next();">下一步</a></span>
						<span class="save_ none"><a class="btn_samll ml15" href="javascript:myPerson_data.save();">保存</a></span>
					</p>
					<div class="kong35"></div>
				</div>
			</div>
		</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/securitycenter/personal_data.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/rsa/RSA.js"></script> 		