<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!--第一个tis是余额 -->
<div class="tis m_auto">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">余额提现</span>
		<a class=" fr_ mt20 size16 c95a1a6 tis_jilu" href="#" onclick="queryWithdrawaClick(2)">查看提现记录</a>
	</div>
	
	<div class="kong25"></div>
	<div class="tis_wen colorDarkBlue">
		<p class="zi_cen_p2 size14">
				1&nbsp;未投标的资金提现需收取金额0.5%的手续费。
		</p>
		<p class="zi_cen_p2 size14">
				2&nbsp;工作日10：00 12：00 14：00 16：00四个处理时间点，周末不提现。
		</p>
		<p class="zi_cen_p2 size14">
				3&nbsp;为了保证银行转账成功，填写完整正确的开户行信息(XX市XX分行XX支行)。
		</p>
		<p class="zi_cen_p2 size14">
				4&nbsp;单笔提现金额下限100元起，不设上限。单个账户单日低于5万的提现只能申请一次；如多于1次，每笔收取1元。
		</p>
		<p class="zi_cen_p2 size14">
				5&nbsp; 每笔收益收取10%作为利息管理费。网银在线代付（提现）目前仅适用于20家银行。（详见提现与充值）
		</p>
	</div>
	<div class="kong35 hui_l"></div>
	<div class="kong25"></div>
	<p class="lh55 size24 colorDarkBlue posRe pos-r">
		目前可提现金额&nbsp;&nbsp;<span class="">￥<fmt:formatNumber value="${availableAmount}" pattern="#,##0.00#"/></span>
		<span class="tipsWrap tipsWrapPay ">
            <em>充值资金：￥<fmt:formatNumber value="${rechargeAmount}" pattern="#,##0.00#"/>
            <br>普通资金：￥<fmt:formatNumber value="${commonAmount}" pattern="#,##0.00#"/></em>
        </span>
	</p>
	<!--第一个tis_yin是还没有添加银行卡的界面-->
	<div class="tis_yin colorDarkBlue">
		<p class="size14 lh40">未添加银行卡不能提现,请先添加银行卡</p>
		<p class="kong15"></p>
		<p><span class=" btn_samll_k colorDarkBlue" onclick="openAddBankCard()">添加银行卡</span></p>
	</div>
</div>
<!-- 提现 e-->
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/rechargewithdrawal/payIndex.js"></script>