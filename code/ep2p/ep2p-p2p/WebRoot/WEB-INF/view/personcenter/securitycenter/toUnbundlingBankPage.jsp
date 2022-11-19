<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<%-- <%@include file="/common/config.jsp" %> --%>
<%@include file="/common/taglibs.jsp" %>
<!-- 解绑银行卡 s-->
<div class="goumai_vip_a">
	<div class="vip_g_b"></div>
	<div class="vip_g_a m_auto h350">
		<div class="ge_ye_tilte">
					<span class="ge_ye_tilte_nav  ms_t_se">解绑银行卡${bankCardNo}</span>
					<input type="text" hidden="true" id="bank_pid" value="${pid}"/>
		</div>
		<div class="kong85"></div>
		<div class="vip_g_lists m_auto colorDarkBlue size14">
			<p class="lh45 w360 m_auto">
				<span class="w115 inline_block tr">交易密码:</span><span class=""><input type="password" class="w220 ml15" id="tradPassWord" name="tradPassWord" value=""/></span>
			</p>
			<span id="tradPwdError" class="input_tis ts_re  mt5" style="padding:0 5px;display: none; margin-left: 226px;"></span>
			<p class="kong15"></p>
			<p class="lh45 w360 m_auto">
				<span class="w115 inline_block tr h10"></span><span class=""><a class="btn_samll ml15" href="javascript:unbun_Bank.sure();">开启</a></span>
			</p>
			<div class="kong35"></div>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/securitycenter/toUnbundling_Bank_Page.js"></script>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/vipInfo/toShoppingPage.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>resources/plugins/rsa/RSA.js"></script>
		