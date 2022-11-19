<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>

<script type="text/javascript">
var borDeadline = "${borrow.borDeadline }";
var borrowRate = "${borrow.borrowRate }";

var sMoney  = "${borrow.surplusMoney}";
var startMoney  = "${borrow.startMoney}";

var endMoney  = "${borrow.endMoney}";
var isTimesInvest = "${borrow.isTimesInvest}";
var pointValue = "${pointValue}";
var manageFee = "${manageFee}";
var borrowId = "${borrow.pid}";
var d = "${timeRemain}";
var t = "${time}";
d = d/1000;
t = t/1000;
var borStatus = "${borrow.borStatus}";

if(startMoney != null && ''!=startMoney){
	startMoney = parseFloat(startMoney);
}else{
	startMoney = 0;
}

if(endMoney != null && ''!=endMoney){
	endMoney = parseFloat(endMoney);
}else{
	endMoney = sMoney;
}

$(document).ready(function(){
	countDown(d);
	
// 	$("#amount")[0].focus();	
	//进度条
	var jindu=$(".dropIComplete span").text();
	//alert(jindu);
	  $(".dropScheduleF").animate({ 
	    width: jindu
	  }, 1500 ); 
});
</script>

<!--内容区-->

        <div class="content mt40">
			<div class="frame1000 bgc oh">
            	<!--头部灰色框-->
                <div class="dropConTop">
                	<div class="dropConTopLeft">
                    	<p class="size16 dropBlue"><c:out value="${borrow.borrowName }"/></p>
                        <p class="size14 colorDarkBlue"><span class="backgroundBlue mr10">${borrow.borrowTypeName }</span><c:out value="${borrow.borrowCode }"/><span class="fr mr30">还款方式：按月等额本息还款</span></p>
                        <ul class="dropEPlanUl">
                        	<li class="dropEPlanLi1">
                            	<p class="colorc size26 lineHeight62"><span class="size62"><fmt:formatNumber value="${borrow.borrowRate*100 }" pattern="#0.##" /></span><i class="size26">%</i></p>
                                <p class="colorDarkBlue mt014 size14 mt10Ie7">年化利率</p>                           
                            </li>
                            <li class="mt24 dropEPlanLi2">
                            	<p class="colorc size26"><c:out value="${borrow.borDeadline }"/>天</p>
                                <p class="colorDarkBlue size14"> 项目期限</p>
                            </li>
                            <li class="mt24 dropEPlanLi3">
                            	<p class="colorc size26">￥<fmt:formatNumber value="${borrow.borrowMoney }" pattern="#,##0.00" /></p>
                                <p class="colorDarkBlue size14"> 项目金额</p>
                            </li>
                        </ul>
                        <div class="dropQuan dropBlue size12">
                            <p> <i class="block i_   tyb_a_l_i1  bgffffff c2980b9">本息保障</i>
                            <i class="block i_   tyb_a_l_i1  bgffffff c2980b9 ml10">新手专享</i>
                            <i class="block i_   tyb_a_l_i1  bgffffff c2980b9 ml10">投资即计息</i>
                            </p>
                        </div>
                        <p class="dropTime size14">发布时间: ${borrow.publishTime }</p>
                    </div>
                	<div class="dropConTopRight">
                    	<p class="colorDarkBlue size14">剩余时间: <span id="countDown">00天00时00分00秒</span></p>
                        <div class="dropSchedule">
                            <div class="dropScheduleZ">
                                <div class="dropScheduleF"></div>
                            </div>
                            <p class="dropIComplete size12"><span class="colorDarkBlue" ><fmt:formatNumber value="${borrow.borrowProgress*100 }" pattern="#0.00"/>%</span>已完成</p>
                            <p class="dropISurplus size12"><span class="colorDarkBlue">￥<fmt:formatNumber value="${borrow.surplusMoney }" pattern="#,##0.00"/></span>剩余</p>
                        </div>
                        <div class="clear"></div>
                        <p class="colorDarkBlue size12 mt20">
                        <c:if test="${!empty(ca) }">
                        	<input type="hidden" id="avamount" value="${empty(ca.availableAmount)?0:ca.availableAmount }">
                        	可用余额: <fmt:formatNumber value="${empty(ca.availableAmount)?0:ca.availableAmount }"  pattern="#,##0.00"/>
                         </c:if>
                         <span id="recharge"></span>
                        </p>
                        <c:if test="${borrow.isTimesInvest == '1' }">
                     	   <input type="text" name="monysum" id="amount" class="dropMonysum" placeholder='输入<fmt:formatNumber value ="${borrow.startMoney }" pattern="#"/>的倍数' onblur="(value=isNaN(parseFloat(value))?value:parseFloat(value).toFixed(2));newStandard.investEvent();"   onkeyup="value=value.replace(/[^\d.]/g,'');newStandard.investEvent();" maxlength="14" ><span class="ml020">元</span>
                        </c:if>
                        <c:if test="${borrow.isTimesInvest != '1' }">
                     	   <input type="text" name="monysum" id="amount" class="dropMonysum" placeholder='输入投资金额' onblur="(value=isNaN(parseFloat(value))?value:parseFloat(value).toFixed(2));newStandard.investEvent();"   onkeyup="value=value.replace(/[^\d.]/g,'');newStandard.investEvent();" maxlength="14" ><span class="ml020">元</span>
                        </c:if>
                        <br />
						<span id="imsg" class="input_tis ts_lh  mt5" style="padding:0 5px;display: none;"></span>
                        <ul class="dropEPlanUl2">
                        	<li>
                            	<p class="colorc size14" id="invest">￥0.00</p>
                                <a class="tipsBtn" href="javascript:;">预计收益
                                    <div class="tipsWrap">
                                        <p>根据收款天数自动计算</p>
                                    </div>
                                </a>                         
                            </li>
                            <li class="width60">
                            	<p class="colorc size14" id="pointValue">0分</p>
                                <p class="colorDarkBlue size14">可获积分</p>
                            </li>
                        </ul>
                       	<div id="btn" >
                       		<c:if  test="${borrow.borStatus == '1' }">
                       			<a class="btn_gray dropJionBtn"  href="javascript:void(0);">立即加入</a>
                       		</c:if>
                       		<c:if test="${borrow.borStatus == '2' }">
		                       	 <c:if test="${empty(ca) }">
		                        	<a class="btn dropJionBtn"  href="javascript:login.openLoginWin();">立即加入</a>
		                         </c:if>
			                     <c:if test="${!empty(ca) }">
		                       		<a class="btn dropJionBtn"  href="javascript:newStandard.confirmOk();">立即加入</a>
		                         </c:if>
	                         </c:if>
                       	</div>
                       	<div id="btn2" style="display: none">
	                       	 <c:if test="${empty(ca) }">
	                        	<a class="btn dropJionBtn"  href="javascript:login.openLoginWin();">立即加入</a>
	                         </c:if>
		                     <c:if test="${!empty(ca) }">
	                       		<a class="btn dropJionBtn"  href="javascript:newStandard.confirmOk();">立即加入</a>
	                         </c:if>
                       	</div>
                        <div class="dropAgreeBox">
                        	<div class="gou_s" id="gou"></div>
                            <div class="colorDarkBlue size14 dropAgree">同意《e生财富借款协议》</div>
                        </div>
                    </div>
                </div>
                <div class="dropConBox">
                	<div class="ge_ye_tilte">
                        <span class="ge_ye_tilte_nav  ms_t_se ">产品介绍</span><span class="  ge_ye_tilte_nav " onclick="newStandard.detail()">投资记录</span>
                    </div>
                    <div class="dropTab">
                        <table class="dropTab2Table">
                            <tr>
                                <td>标的名称</td>
                                <td><c:out value="${financeProducts.financeName }" /></td>
                            </tr>
                            <tr>
                                <td>期限</td>
                                <td><c:out value="${borrow.borDeadline }"/>天</td>
                            </tr>
                            <tr>
                                <td>年化利率</td>
                                <td><fmt:formatNumber value="${borrow.borrowRate*100 }" pattern="#0.##" />%</td>
                            </tr>
                            <tr>
                                <td>加入条件</td>
                                <td><c:out value="${financeProducts.joinCondition }" /></td>
                            </tr>
                            <tr>
                                <td>计息时间</td>
                                <td><c:out value="${financeProducts.interestTime }" /></td>
                            </tr>
                            <tr>
                                <td>收益方式</td>
                                <td><c:out value="${financeProducts.earningMode }" /></td>
                            </tr>
                            <tr>
                                <td>到期时间</td>
                                <td><c:out value="${financeProducts.expirationDate }" /></td>
                            </tr>
                            <tr>
                                <td>保障方式</td>
                                <td><c:out value="${financeProducts.guaranteeMode }" /></td>
                            </tr>
                            <tr>
                                <td>服务协议</td>
<%--                                 <td><c:out value="${financeProducts.borrowAgreementId }" /></td> --%>
                                <td><c:out value="${ag.borAgrAnme }" /></td>
                            </tr>
                            <tr>
                                <td>规则介绍</td>
                                <td><c:out value="${financeProducts.ruleIntroduction }" /></td>
                            </tr>
                        </table>
                        <div class="dropTabAd"></div>
                    </div>
                    <div class="dropTab none">
                    	<div class="invest_table"> 
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t mt30"> 
                           <thead> 
                            <tr>
                             <th width="20%">投资人</th> 
                             <th width="20%">投资方式</th> 
                             <th width="20%">投资金额（元）</th> 
                             <th width="20%">投资时间</th> 
                             <th width="20%">状态</th> 
                            </tr>
                           </thead> 
                           <tbody id="detaiList">
                           
                           </tbody>
                          </table> 
                          <!-- 分页 --> 
                        	 <div id="page" class="m-pagination page_div fr"> </div>
                         </div>
                    </div>
                </div>
            </div>
        </div>
        <!--内容区End-->
<script type="text/javascript" src="<%=basePath %>theme/js/personcenter/common/usercenter.js"></script>
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/investPublic.js"></script>
<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/newstandard/info.js"></script>
<!-- 投资确认 -->
 <div class="dropPopupBox" style="top:0" id="investOK">
     	<div class="dropPopupH4">
     		<h4 class="size16 dropBlue">投资确认</h4>
        </div>
        <div class="dropPopupTab">
        	<div class="dropPopupTabCon">
            	<p class="size16 dropBlue"><span class="backgroundBlue mr10">${borrow.borrowTypeName }</span><c:out value="${borrow.borrowName }"/><span class="size14 colorDarkBlue dropPopupTabConSpan"><c:out value="${borrow.borrowCode }"/></span></p>
                <table class="dropPopupTable">
                	<tr>
                    	<td>项目金额：</td>
                        <td>￥<fmt:formatNumber value="${borrow.borrowMoney }" pattern="#,##0.00" /></td>
                        <td>发布时间：</td>
                        <td>${borrow.publishTime }</td>
                    </tr>
                    <tr>
                    	<td>年化利率：</td>
                        <td><fmt:formatNumber value="${borrow.borrowRate*100 }" pattern="#0.##" />%</td>
                        <td>还款方式：</td>
                        <td>${borrow.repaymentTypeName }<span class="tipsBtn">
                        	<div class="tipsWrap">
                                <p>按月分期还款：按月分期还本金和利息；按月付息，到期还本：按月分期还利息，到最后一期还本金；到期一次性还本息：在最后一期一次性全部本金和利息</p>
                            </div></span></td>
                    </tr>
                	<tr>
                    	<td>项目期限：</td>
                        <td><c:out value="${borrow.borDeadline }"/>天</td>
                        <td>计息方式：</td>
                        <td>满标即计息<span class="tipsBtn">
                        	<div class="tipsWrap">
                                <p>系统有两种计算方式。一是满标计息，是投资标的满标当日作为计算开始日期的一种方式；二是投资即计息，是投资成功当日即开始计息的一种方式</p>
                            </div></span></td>
                    </tr>
                </table>
                <form id="investForm">
                	<input type="hidden" name="pid" value="${borrow.pid }" />
                <div class="transfer_info dropPopupInfo fl size14">
                    <div class="ti1">
                        <div style="width:50%;" class="fl">可用余额：<span class="colororg">￥<fmt:formatNumber value="${ empty(ca.availableAmount)?0:ca.availableAmount}" pattern="#,##0.00" /></span> <span id="recharge2"></span></div>
                        <div style="width:50%;" class="fl">已投金额：<span>￥<fmt:formatNumber value="${empty(borrow.alreadyMoney)?0:borrow.alreadyMoney }" pattern="#,##0.00" /></span></div>
                    </div>
                     <div class="kong5"></div>
                    <div class="ti3">可投金额： ￥<fmt:formatNumber value="${borrow.startMoney }" pattern="#,##0.00" /> ~ ￥<fmt:formatNumber value="${(borrow.endMoney <=0 || borrow.endMoney > borrow.surplusMoney) ? borrow.surplusMoney : borrow.endMoney  }" pattern="#,##0.00" /></div>
                    <div class="ti2">
                        <div style="margin-top:16px;">投资金额：<input type="text" name="amount" id="am" onblur="(value=isNaN(parseFloat(value))?value:parseInt(value));newStandard.investEvent2();"   onkeyup="value=value.replace(/[^\d.]/g,'');newStandard.investEvent2();" maxlength="14" class="dropMonysum dorpInvest" placeholder="输入投资金额"><span class="ml020">元</span>
                       <span id="imsg2" class="input_tis ts_lh  mt5" style="padding:0 5px;display: none;margin-left: 5px;"></span>
                        </div>
                    </div>
                    <div class="ti4">
                        <div class="fl">可获积分：<span id="pointValue2"></span></div>
                    </div>
                    <div class="ti3" style="margin-top:16px;">预期收益：<span class="colororg" id="ci">￥0.00</span>(包含<fmt:formatNumber value="${manageFee*100}" pattern="#0" />%利息管理费)</span></div>
                    <div class="ti2" style="margin-top:16px;">
                    	<input type="hidden" name="pwd" id="rsaPwd">
                        <div>交易密码：<input type="password" id="pwd" onkeyup="newStandard.clearPwd()"  style="width:116px;" maxlength="6" class="pl10"/>
                        <span id="pwdError" class="input_tis ts_re  mt5" style="padding:0 5px;display: none;"></span>
                        </div>
                    </div>
                    <div class="kong5"></div>
                    <div class="ti4" style="padding-left: 70px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" id="dropPopupSure" style="margin-right: 20px;" href="javascript:newStandard.submit();">确定</a> <a class="btn_samll_gray" href="javascript:newStandard.closePop();" id="dropPopupDone">取消</a> </div>
				</div>
				</form>
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
            	<h4 class="size18 colorDarkBlue"><span class="yes"></span>恭喜，<span class="backgroundBlue mr10">${borrow.borrowTypeName }</span>${borrow.borrowName }:${borrow.borrowCode }</h4>
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
            	<h4 class="size18 colorDarkBlue"><span class="yes"></span>很遗憾，<span class="backgroundBlue mr10">${borrow.borrowTypeName }</span>，${borrow.borrowName }:${borrow.borrowCode }</h4>
                <h2 class="size24 colororg" id="e_msg">投标失败了，可能有人抢先了一步！</h2>
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