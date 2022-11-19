<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!--  提现 s-->
<div class=" m_auto ka_g">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">余额提现</span>
		<a class=" fr_ mt20 size16 c95a1a6 tis_jilu" href="#" onclick="queryWithdrawaClick(2)">查看提现记录</a>
	</div>
	<div class="kong25"></div>
	<div class="tis_wen colorDarkBlue">
		<p class="zi_cen_p2 size14">
				1&nbsp;在线充值手续费暂由平台垫付，未投标的资金提现需收取金额0.5%的手续费。
		</p>
		<p class="zi_cen_p2 size14">
				2&nbsp;由于银行系统的限制，部分银行充值功能仅限于IE内核的浏览器。
		</p>
		<p class="zi_cen_p2 size14">
				3&nbsp;本站目前使用网银在线、财付通进行在线充值。两个通道支付方式开通网银即可支付，使用财付通余额也可支付。
		</p>
	</div>
	<div class="kong35 hui_l"></div>
	<div class="kong10"></div>
	<p class="lh55 size24 colorDarkBlue posRe pos-r">
		目前可提现金额&nbsp;&nbsp;<span>￥<fmt:formatNumber value="${availableAmount}" pattern="#,##0.00#"/>
		<input type="hidden" id="can_withdrawal_amount" value="${availableAmount}"></span>
		<span class="tipsWrap tipsWrapPay ">
            <em>充值资金：￥<fmt:formatNumber value="${rechargeAmount}" pattern="#,##0.00#"/>
            <br>普通资金：￥<fmt:formatNumber value="${commonAmount}" pattern="#,##0.00#"/></em>
        </span>
	</p>
	<p class="lh45 size14">选择需要提现的银行卡 &nbsp;|&nbsp;<span class="c2980b9" onclick="CustWithdrawalClass.openBankManager()">管理银行卡</span></p>
	<div class="kong10"></div>
	<!--<p class="lh55 size16">
		选择充值平台
	</p>-->
	 <!--//添加银行图片,给银行图片添加class ="yin_h_ka" ,自动判断 alt的值 然后自动填充图片-->
	<div class=" colorDarkBlue" id="pay_balance_bank_div">
	</div>
	<p class="kong10"></p>
	<form action="" id="pay_balance_withdrawal_form">
		<input type="hidden" name="bank.pid" id="bank_id" >
		<p class="lh55 size16 colorDarkBlue">
			提现金额
		</p>
		<p class="lh26 b0b0b0 size16">
			<input type="text" class="pl10 mr10 w260 b0b0b0" id="amount_id" name="amount"> 
			<br/>
			<span id="amount_is_null" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">
				请您填写金额！
			</span>
			<span id="amount_alert" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">
				金额类型不正确！
			</span>
			<span id="if_amount_max" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">
				当前最高可提现￥<fmt:formatNumber value="${availableAmount}" pattern="#,##0.00#"/>
			</span>
			<span id="if_amount_minus" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">
				小逗比、提现金额不能为负数！
			</span>
		</p>
		<p class="lh55 size16 colorDarkBlue">
			交易密码
		</p>
		<p class="lh26 b0b0b0 size16">
			<input type="password" class="pl10 mr10 w260 b0b0b0" name="customer.tradePassword"> 
			<span id="trade_password_alert" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">
								<!--ts_re 是红色警告背景  
								ts_lh 是黄色警告背景
								ts_lv 是绿色正确提示背景-->
									交易密码错误！请确认。
			</span>
		</p>
		<p class="lh55 size16 colorDarkBlue">
			手机验证码
		</p>
		<p class="lh26 b0b0b0 size16" >
			<input type="text" class="pl10 mr10 w260 b0b0b0" name="code"> <i id="send_sms_code_but" class="i_ c2980b9 cus_p">获取验证码</i>
			<span id="code_alert" class="input_tis mb5 block  jin_e_none ts_lh" style="width: 240px;display:none;">
								<!--ts_re 是红色警告背景  
								ts_lh 是黄色警告背景
								ts_lv 是绿色正确提示背景-->
									验证码有误！请确认。
			</span>
		</p>
	</form>
	<p class="kong55 hui_l"></p>
	<p class="kong15"></p>
	<p>
		<a class="btn tc" href="#" onclick="CustWithdrawalClass.custWithdrawal()">下一步</a>
	</p>
	
	<div class="kong150"></div>
</div>
<!-- 提现 e-->
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/rechargewithdrawal/payIndex.js"></script>
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/rechargewithdrawal/payBalanceWithdrawa.js"></script>