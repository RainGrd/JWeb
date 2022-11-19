<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<!-- 绑定安全邮箱 s-->
		<div class="goumai_vip_d">
			<div class="vip_g_b"></div>
			<div class="vip_g_d m_auto h350">
				<div class="ge_ye_tilte">
							<span class="ge_ye_tilte_nav  ms_t_se">绑定安全邮箱</span>
				</div>
				 <div class="vip_g_lists m_auto colorDarkBlue size14 none goumai_vip_d_meial">
					<div class="kong40"></div>
					<p class="lh45 w360 m_auto tc">
						<img src="/ep2p/theme/default/images/an_6.png" class="tc" />
					</p>
					<p class="kong10"></p>
					<p class="lh45 w360 m_auto tc">
						验证信息已发送到您的邮箱
					</p>
					<p class="kong10"></p>
					<p class="lh45 w360 m_auto">
<!-- 						<span class="w115 inline_block tr h10"></span><span class=""><a class="btn_samll ml15" href="javascript:emailValidate.retrunSafeCenter();">返回安全中心</a></span> -->
						<span class="w115 inline_block tr h10"></span><span class="" onclick="safetyIndex.retrunSafeCenter();">返回安全中心</span>
					</p>
					<div class="kong35"></div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/safetyIndex.js"></script>
