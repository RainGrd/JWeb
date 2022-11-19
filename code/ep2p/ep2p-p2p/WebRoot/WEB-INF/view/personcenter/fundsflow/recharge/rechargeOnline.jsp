<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>"
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/rechargeOnline.css">
<div class=" borrow_div fl w945">
	<!--  充值 s-->
	<div class="tis m_auto">
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
		<form id="rechargeForm" method="post" action="<%=basePath %>recharge/userRechargeController/confirmRecharge.shtml" onsubmit="return rechargeOnline.check()">
		<p class="lh55 size16">
			选择充值平台
		</p>
		<div class="tis_yin colorDarkBlue">
			<span class="tis_yin_ping mr15 inline_block pos-r oh">
				<img  src="<%=basePath%>theme/default/images/recharge/tenPay.jpg" class="block" />
				<input id="rechargePlatform" name="rechargePlatform" value="1" hidden="true" />
			</span>
		</div>
		<p class="kong20"></p>
		<p class="lh55 size16 colorDarkBlue">
			充值金额
		</p>
		<p class="lh26 b0b0b0 size16">
			<input class="pl10 mr10 w260 b0b0b0" id="availableAmount" name="availableAmount" type="text" value="${availableAmount }"></input>元
		</p>
		<p class="kong55 "></p>
		<p>
			<input class="btn_samll tc" type="submit" value="下 一 步" ></input>
		</p>
		</form>
		<div class="kong150"></div>
	</div>
	

	
	<!-- 充值 e-->

</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/fundsflow/rechargeOnline.js"></script> 
