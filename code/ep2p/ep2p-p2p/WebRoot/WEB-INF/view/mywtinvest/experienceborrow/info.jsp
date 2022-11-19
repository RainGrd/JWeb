<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>
<script type="text/javascript">
var borDeadline = "${borrow.borDeadline }";
var borrowRate = "${borrow.borrowRate }";
var sMoney  = "${borrow.surplusMoney}";
var startMoney  = "${borrow.startMoney}";
var isTimesInvest = "${borrow.isTimesInvest}";
var pointValue = "${pointValue}";
var manageFee = "${manageFee}";
var borrowId = "${borrow.pid}";
var d = "${timeRemain}";
d = d/1000;

$(document).ready(function(){
	countDown(d);
	
	//进度条
	var jindu=$(".size12 span").eq(0).text();
	  $(".dr_jindu").animate({ 
	    width: jindu
	  }, 1500 ); 
});
</script>

<!--内容区-->

     <div class="kong40"></div>
		<div class="tyb_all m_auto">
			<div class="kong40"></div>
			<div class="tyb_a_l fl_">
				<p class="tyb_a_l_p1 cf1c40f size16 lh20"><c:out value="${borrow.borrowName }"/></p>
				<p class=" ffffff size14 lh18"><c:out value="${borrow.borrowCode }"/></p>
				<p class="kong30"></p>
				<p class="oh">
					<span class="tyb_a_l_s1 cf1c40f fl">
						<i class="i_ size62 inline_block"><fmt:formatNumber value="${borrow.borrowRate }" pattern="#0.00" /></i><i class="i_ size26">%</i>
						<i class="block i_ size14 ffffff mb25">年化利率</i>
						<i class="block i_   tyb_a_l_i1  bgffffff c2980b9">免服务费</i>
					</span>
					<span class="tyb_a_l_s2 fl ">
						<i class="i_ size24 inline_block cf1c40f" style="height:62px;line-height:62px;"><c:out value="${borrow.borDeadline }"/>天</i>
						<i class="block i_ size14 ffffff mb25">项目期限</i>
						<i class="block i_   tyb_a_l_i1   c2980b9"></i>
					</span>
					<span class="tyb_a_l_s3 fl ">
						<i class="i_ size24 inline_block cf1c40f" style="height:62px;line-height:62px;">￥<fmt:formatNumber value="${borrow.borrowMoney }" pattern="#,##0.00" /></i>
						<i class="block i_ size14 ffffff mb25">项目金额</i>
						<i class="block i_ size14 bdc3c7">发布时间: ${borrow.publishTime }</i>
					</span>
				</p>
			</div>
			<div class="tyb_a_r fr_">
				<div class="kong30"></div>
				<p class="ffffff size14">剩余时间: <span id="countDown">00天00时00分00秒</span></p>
				<p class="tyb_a_r_p1">
					<span class="dr_jindu" style="width: 0px;"></span>
				</p>
				<p class="size12 ffffff oh">
					<span class="fl_"><fmt:formatNumber value="${borrow.borrowProgress }" pattern="#0.00"/>%</span>已完成
					<span class="fr_ dr_jindu_s">￥<fmt:formatNumber value="${borrow.surplusMoney }" pattern="#,##0.00"/>剩余</span>
				</p>
				<p class="kong25"></p>
				<c:if test="${empty(ca) }">
					<p class="dr_tyj_lg mb15 size14 tc lh35">
						<span class="ffffff"><a href="javascript:login.openLoginWin();" class="i_ colorc cus_p">登录</a>后查看可用体验券</span>
					</p>
					<p class="dr_tyj_login lh55 ffffff pos-r">
						<c:if test="${borrow.borStatus == 1 }">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p" href="javascript:void(0)">待招标</a>
						</c:if>
						<c:if test="${borrow.borStatus == 2 }">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p " href="javascript:login.openLoginWin();">立即加入</a>
						</c:if>
						<c:if test="${borrow.borStatus == 3 || borrow.borStatus == 4 || borrow.borStatus == 5}">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p" href="javascript:void(0)">已结束</a>
						</c:if>
						<c:if test="${borrow.borStatus == 8 }">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p" href="javascript:void(0)">已结清</a>
						</c:if>
					</p>
				</c:if>
				<c:if test="${!empty(ca) }">
					<p class="dr_tyj lh55 ffffff pos-r">
						<span class="fl_">
							<i class="size24 i_ ml15"><c:out value="${fn:length(experiences)}"></c:out> </i><i class="i_ ml10">张体验券</i>
						</span>
						<span class="fr_ size24 mr15">
							￥<fmt:formatNumber value="${empty(ca.experienceAmount)?0:ca.experienceAmount }"  pattern="#,##0.00"/>
						</span>
						<c:if test="${borrow.borStatus == 1 }">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p" href="javascript:void(0)">待招标</a>
						</c:if>
						<c:if test="${borrow.borStatus == 2 }">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p email_key_js" href="javascript:experienceBorrow.confirmOk();">立即加入</a>
						</c:if>
						<c:if test="${borrow.borStatus == 3 || borrow.borStatus == 4 || borrow.borStatus == 5}">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p" href="javascript:void(0)">已结束</a>
						</c:if>
						<c:if test="${borrow.borStatus == 8 }">
							<a class="block lh35 c2980b9 size16 w100x  pos-a dr_tyj_j tc cus_p" href="javascript:void(0)">已结清</a>
						</c:if>
					</p>
				</c:if>
				<p class="bdc3c7 size14 mt10 dropConTopRight" style="margin-left: 0px;">
					<span class="gou_s gou_s_js fl_ mr10" val="1" id="gou"><img src="<%=basePath %>theme/default/images/gou_b.png" class="block"></span>同意《e生财富债权转让协议》
				</p>
			</div>
		</div>
		<div class="tyb_c oh w1140 m_auto">
				<div class="ge_ye_tilte">
					<span class="ge_ye_tilte_nav  ms_t_se ">系统消息</span>
				</div>
				<div class="vip_shuo pl40 pt30">
					<p>
						1、体验标是由e生财富设立的一个专门提供给平台客户使用体验金进行投资体验的虚拟项目。
					</p>
					<p>
						2、投资体验标无需实名认证。
					</p>
					<p>
						3、体验金是由e生财富用平台自有资金设立的一个专门用于提供给平台客户进行平台项目投资体验的基金，只能投资体验项目，使用后可产生利息收益
					</p>
					<p>
						4、获得体验金后，在体验标项目专区点击使用。
					</p>
					<p>
						5、体验金是一种投资体验项目的虚拟资金，不能提现，但是投资后产生的收益归用户所有，可用于投资或提现等操作。
					</p>
					<p>
						6、客户使用体验金投资项目到期后，所得的收益归客户所有(可提现或续投e生财富互联网金融平台的任意项目)，本金部分由平台收回。

					</p>
					<p>本活动规则解释权归e生财富所有，如有疑问请联系 在线客服 或 拨打4009-998-992</p>
					
					<div class="kong70"></div>
				</div>
			</div>
        <!--内容区End-->
<script type="text/javascript" src="<%=basePath %>theme/js/personcenter/common/usercenter.js"></script>
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/investPublic.js"></script>
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/experienceborrow/info.js"></script>
<!-- 投资确认 -->
 <div class="dropPopupBox" style="top:0" id="investOK">
     	<div class="vip_g_b top1500" ></div>
			<div class="vip_g_d m_auto  top1500">
				<div class="ge_ye_tilte">
							<span class="ge_ye_tilte_nav  ms_t_se">投资确认</span>
				</div>
				<div class=" m_auto colorDarkBlue size14 goumai_vip_d_meial p30">
				<form id="investForm">
					<input type="hidden" name="pid" value="${borrow.pid }" />
					<div class="inline_block p30 bgecf0f1 w580">
						<p class="size14 colorDarkBlue">
							<span class=" w300 inline_block">
								<i class="i_ block">本次投资金额：</i>
								<i class="i_ size24 colorc">￥<span id="tb">0.00</span></i>
								<input type="hidden" name="amount" id="am" />
							</span>
							<span class=" w220 inline_block">
								<i class=" i_ block">预计赚取收益： </i>
								<i class="i_ size24 colorc">￥<span id="ci">0.00</span></i>
							</span>
						</p>
					</div>
					
					<div id="abc" class="w580 p30">
						<p class="size12 mt5">
							<img class="mr5" src="<%=basePath %>theme/default/images/an_4.png" />您可以选择使用多张赠券
						</p>
						<div class="po_relative h220">
							<div id="es" class="pt20 scrollBox">
								<c:forEach items="${experiences }" var="epce">
									<span class="drop_list drop_list_bg_b pos-r mr20 cus_p">
										<input type="hidden" name="epces" value="${epce.pid }" disabled="disabled" />
										<i class="i_ pos-a size24 drop_list_ia">￥<span>${epce.expAmount }</span></i>
										<i class="i_ pos-a size12 drop_list_ib">体验金</i>
									</span>
								</c:forEach>
							</div>
						</div>
						<div class="ti2 po_relative" style="margin-top:16px;">
	                    	<input type="hidden" name="pwd" id="rsaPwd">
	                        <div>交易密码：<input type="password" id="pwd" style="width:116px;" maxlength="6" class="pl10"/>
	                        <span id="pwdError" class="input_tis ts_re  mt5" style="padding:0 5px;display: none;"></span>
	                        </div>
	                    </div>
					</div>
					
					<div class="w580 pl30 po_relative">
						<span class="btn_samll ceshi_js" onclick="javascript:experienceBorrow.submit();">确定</span><span class="btn_samll_gray ml10" onclick="javascript:experienceBorrow.closePop();">取消</span>
					</div>
				</form>
				</div>
			</div>
			<div class="vip_g_d m_auto h500 none">
				<div class="ge_ye_tilte">
							<span class="ge_ye_tilte_nav  ms_t_se">投资成功</span>
				</div>
				<div class=" m_auto colorDarkBlue size14 goumai_vip_d_meial ">
					<div class="kong90"></div>
					<p class="tc size18 colorDarkBlue"><img src="../images/dr_6.png" /> 恭喜，体验标:ZCX134512123-3</p>
					<p class="size24 colorc tc lh50">
						投资成功！
					</p>
					<p class="colorDarkBlue siz12 tc">
						10秒后自动跳转到系统首页
					</p>
					<p class="tc">--------------------------------------------------------------</p>
					<p class="colorDarkBlue size14 tc lh30">
						<span class="">投资金额：<i class="i_ colorc">￥5000.00</i></span>
					</p>
					<p class="colorDarkBlue size14 tc lh30">
						<span class="">预期收益：<i class="i_ colorc">￥5000.00</i></span>
					</p>
					<p class="tc mt10">
						<span class="btn_samll">再逛逛</span><span class="btn_samll ml10">我的投资</span>
					</p>
				</div>
			</div>
     </div>

<!-- 投资成功 -->
     <div class="dropPopupBox2" id="suc" style="top:0">
     	<div class="dropPopupH4">
     		<h4 class="size16 dropBlue">投资成功</h4>
        </div>
        <div class="dropPopupSuessBox">
        	<div class="dropPopupSuess">
            	<h4 class="size18 colorDarkBlue"><span class="yes"></span>恭喜，新手标，${borrow.borrowName }:${borrow.borrowCode }</h4>
                <h2 class="size24 colororg" id="s_msg">投资成功！</h2>
                <p><span id="sc">10</span>秒后自动跳转到系统首页</p>
            </div>
            <div class="dropPopupSuessInfo">
            	<ul>
                	<li>投资金额：<span class="colororg" id="s_amount">￥0.00</span></li>
                    <li>预期收益：<span class="colororg" id="s_invest">￥0.00</span></li>
                </ul>
                <div class="ti4" style="padding-left:10px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" style="margin-right: 20px;" href="<%=basePath%>index/homepController/toIndex.shtml">再逛逛</a> <a class="btn_samll" href="<%=basePath %>userinfo/centerController/toUserCenterInvest.shtml">我的投资</a> </div>
            </div>
        </div>
     </div>
<!-- 投资成失败-->
     <div class="dropPopupBox2" id="fail" style="top:0">
     	<div class="dropPopupH4">
     		<h4 class="size16 dropBlue">投资失败</h4>
        </div>
        <div class="dropPopupSuessBox">
        	<div class="dropPopupSuess">
            	<h4 class="size18 colorDarkBlue"><span class="yes"></span>很遗憾，新手标，${borrow.borrowName }:${borrow.borrowCode }</h4>
                <h2 class="size24 colororg" id="s_msg">投标失败了，可能有人抢先了一步！</h2>
                <p><span id="sc2">10</span>秒后自动跳转到系统首页</p>
            </div>
            <div class="dropPopupSuessInfo">
            	<ul>
                	<li>投资金额：<span class="colororg" id="s_amount2">￥0.00</span></li>
                    <li>预期收益：<span class="colororg" id="s_invest2">￥0.00</span></li>
                </ul>
                <div class="ti4" style="padding-left:10px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" style="margin-right: 20px;" href="<%=basePath%>index/homepController/toIndex.shtml">再逛逛</a> <a class="btn_samll" href="<%=basePath %>userinfo/centerController/toUserCenterInvest.shtml">我的投资</a> </div>
            </div>
        </div>
     </div>
     	<script src="<%=basePath %>theme/js/scrollBar/scrollBar.js" type="text/javascript" ></script>
		<script src="<%=basePath %>theme/js/scrollBar/scrollBar1.js" type="text/javascript" ></script>
		<link href="<%=basePath %>theme/js/scrollBar/scrollBar.css" type="text/css" rel="stylesheet"  />
		<script>
		$(function(){

		     $(".scrollBox").mCustomScrollbar({
		        scrollButtons: {
		           enable: true
		        }
		     });
		})
		
		</script>