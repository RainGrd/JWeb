<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/safetyIndex.js"></script>
	<!-- 绑定安全邮箱 s-->
		<div class="goumai_vip_d">
			<div class="vip_g_b"></div>
			<div class="vip_g_d m_auto h350">
				<div class="ge_ye_tilte">
							<span class="ge_ye_tilte_nav  ms_t_se">绑定安全邮箱</span>
				</div>
				
				<div class="vip_g_lists m_auto colorDarkBlue size14 goumai_vip_d_meial">
					<div class="kong85"></div>
					<p class="lh45 w360 m_auto">
						<span class="w95 inline_block tl"><i class="i_ colorc mr10">*</i>邮箱:</span><span class="">
							<input type="text" id="email_" placeholder="例如：xxxx@qq.com" class="pl10 w220 " value=""/>
							<input type="hidden" id="isEmail" value="${isEmail}"/>
						</span>
					</p>
					<p class="kong15"></p>
					<p class="lh45 w360 m_auto">
<!-- 						<span class="w115 inline_block tr h10"></span><span class=""><a class="btn_samll ml15 goumai_vip_d_meial_but" href="safetyIndex.sureSubmit();">确认提交</a></span> -->
						<span class="w115 inline_block tr h10"></span><span class="btn_samll ml15 goumai_vip_d_meial_but" onclick="safetyIndex.sureSubmit();">确认提交</span>
					</p>

					<div class="kong35"></div>
				</div>
			</div>
		</div>
		