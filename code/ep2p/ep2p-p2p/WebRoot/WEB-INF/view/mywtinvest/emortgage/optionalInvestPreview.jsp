<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %> 
<style>

</style>
<div>
     	<div class="dropPopupH4">
     		<h4 class="size16 dropBlue">投资确认</h4>
        </div> 
        <div class="dropPopupTab">
        	<div class="dropPopupTabCon">
            	<p class="size16 dropBlue"><c:out value="${borrow.borrowName }" /><span class="size14 colorDarkBlue dropPopupTabConSpan"><c:out value="${borrow.borrowCode}" /></span></p>
                <table class="dropPopupTable">
                	<tr>
                    	<td>项目金额：</td>
                        <td>￥<fmt:formatNumber value="${borrow.borrowMoney}" pattern="#,##0.00"/></td>
                        <td>发布时间：</td>
                        <td>${borrow.publishTime}</td>
                    </tr>
                    <tr>
                    	<td>年化利率：</td>
                        <td><fmt:formatNumber value="${borrow.borrowRate*100}" pattern="#,##0.00"/>%</td>
                        <td>还款方式：</td>
                        <td><c:out value="${borrow.repaymentTypeName }" /></td>
                    </tr>
                	<tr>
                    	<td>项目期限：</td>
                        <td><c:out value="${borrow.borDeadline}"/>月</td>
                        <td>计息方式：</td>
                        <td><c:out value="${borrow.accrualTypeName }"/></td>
                    </tr>
                	<tr>
                    	<td>投资奖励：</td>
                        <td> <fmt:formatNumber value="${borrow.investRewardScale*100}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>% </td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <form id="conditionForm">
                <div class="transfer_info dropPopupInfo fl size14">  
                    <div class="ti1">
                        <div style="width:50%;" class="fl">可用余额：<font class="colororg">
							 ￥<fmt:formatNumber value="${availableAmount}" pattern="##.##"></fmt:formatNumber>
			                 
						</font><a target="_parent" href="<%=basePath %>recharge/userRechargeController/toUserRecharge.shtml" class="btn_samllss">充值</a></div>
                        <div style="width:50%;" class="fl">已投金额：<font>￥<fmt:formatNumber value="${borrow.alreadyMoney==null?0:borrow.alreadyMoney }" pattern="#,##0.00"/></font></div>
                    </div>
                    <div class="ti3">可投金额： ￥ ${borrow.startMoney==null?0:borrow.startMoney}~  ￥${borrow.endMoney==null?"不限":borrow.endMoney}</div>
                    <div class="ti2">
                        <div style="margin-top:16px;">
                        	投资金额：<input type="text" value="${investNumber}" name="investmentAmount" id="investmentAmount" class="dropMonysum dorpInvest" onblur="count(this.value)" style="width: 200px" placeholder="输入投资金额"/> <span class="ml024">元</span>
                        	<span id="imsg2" class="input_tis ts_lh  mt5" style="padding:0 5px;display: none;margin-left: 5px;"></span>
                        </div>
                    </div>
                    <div class="ti4">
                        <div style="width:40%;" class="fl">投资奖励：<font class="colororg" id="investReward">￥${investReward }</font></div>
                        <div style="width:60%;" class="fl">可获积分：<font id="eplanIntegral">${eplanIntegral}</font></div>
                    </div> 
                    <c:if test="${borrow.isJiaxiTicket eq 1}">
	                    <p class="vip_g_list" id="jiaxi">
	                        		使用加息券:   
	                    </p>
                    </c:if>
<%--                     +￥${expectAmount }</font>（加息收益） --%>
                    <div class="ti3" style="margin-top:16px;">预期收益：
                    <font class="colororg" id="expectAmount">￥<fmt:formatNumber value="${expectAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></font>
                    <font class="colororg" id="investAward"></font>
                    <font class="size12 gray">包含<fmt:formatNumber value="${discount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>%利息管理费</font></div>
                    
                     <c:if test="${!empty borrow.borrowPassword}">
	                   <div class="ti2" style="margin-top:16px;" id="borrowPasswordDiv">
                        <div>
                        	招标密码：<input type="password" name="borrowPassword" id="borrowPassword" style="width:116px;" class="pl10"/>
                        	<span id="pwdBorError" class="input_tis ts_re  mt5" style="padding:0 5px;display: none;"></span>
                        </div>
                    	</div>
                    </c:if>
                    
                    <div class="ti2" style="margin-top:16px;">
                        <div>
                        	交易密码：<input type="password" name="pwd" id="pwd" style="width:116px;" class="pl10"/>
                        	<span id="pwdError" class="input_tis ts_re  mt5" style="padding:0 5px;display: none;"></span>
                        </div>
                    </div>
                    <div class="kong5"></div>
                    <div class="ti4" style="padding-left: 70px;margin-top:16px;">
                  			<a class="btn_samll zhuangr_key_js" id="dropPopupSure" style="margin-right: 20px;" href="javascript:void(0)" onclick="invest()">确定</a> 
		                    <a class="btn_samll_gray" href="javascript:void(0)" id="dropPopupDone" onclick="closeWindow(this)">取消</a> 
                    </div>
				</div>
							<input type="hidden" id="isTimesInvest" name="isTimesInvest" value="${borrow.isTimesInvest }" /><!--是否倍数投资 -->
							<input type="hidden" id="endMoney" name="endMoney" value="${borrow.endMoney}" /><!--投资上限 -->
							<input type="hidden" id="startMoney" name="startMoney" value="${borrow.startMoney}" /><!--起投金额 -->
							<input type="hidden" id="investAwardId" name="investAwardId" value="" /><!--加息id -->
							<input type="hidden" id="availableAmount" value="${availableAmount}" /><!--可用余额 -->
							<input type="hidden" name="borrowId" id="borrowId" value="${borrow.pid }"><!--投标id -->
							<input type="hidden" name="accrualType" value="${borrow.accrualType }" /><!--计息类型 -->
							<input type="hidden" name="expectAmount" value="${investNumber }" /><%-- 投资金额  --%>
							<input type="hidden" name="investmentType" value="1" /><%-- 投标方式  --%>
							<input type="hidden" name="investmentPayoffs" id="expectAmountVal" value="${expectAmount }" /> <%-- 预期收益  --%>
							<input type="hidden" name="awardAmount" id="investRewardVal" value="${investReward }" /> <%-- 投资奖励 --%>
				</form>
				<form id="investRewardForm">    
							<input type="hidden" id="repaymentAmt" name="repaymentAmt" value=""/><!--借款金额 -->
							<input type="hidden" id="repaymentDeadline" name="repaymentDeadline" value="${borrow.borDeadline}"/><!--借款期限 -->
							<input type="hidden" id="repaymentRate" name="repaymentRate" value="${borrow.borrowRate*100}"/><!--借款利率 -->   
							<input type="hidden" id="repaymentType" name="repaymentType" value="${borrow.repaymentType}"/><!--还款方式 -->
							<input type="hidden" id="rewardRate" name="rewardRate" value="${borrow.investRewardScale*100}"/><!--投资奖励百分比 -->
				</form>
            </div>
        </div>
     </div>
 <div class="dropPopupBox2" id="suc_msg" style="top:0px">
     	<div class="dropPopupH4">
     		<h4 class="size16 dropBlue">投资成功</h4>
        </div>
        <div class="dropPopupSuessBox">
        	<div class="dropPopupSuess">
            	<h4 class="size18 colorDarkBlue"><span class="yes"></span>恭喜，${borrow.borrowName}:${borrow.borrowCode}</h4>
                <h2 class="size24 colororg">投资成功！</h2>
                <p><span id="temp">10</span>秒后自动跳转到系统首页</p>
            </div>
            <div class="dropPopupSuessInfo">
            	<ul>
                	<li>投资金额：<font class="colororg" id="m_pi">￥<fmt:formatNumber value="${investNumber}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></font></li>
                    <li>预期收益：<font class="colororg" id="m_transferAmount">￥<fmt:formatNumber value="${expectAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></font>
	                    <font class="colororg" id="m_investAward"></font>
                    </li>
                    <li>投资奖励：<font class="colororg" id="m_money">￥<fmt:formatNumber value="${investReward }" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></font></li>
                    <li>可获积分：<font class="colororg" id="m_yearRate">${eplanIntegral}</font></li>
                </ul>
                <div class="ti4" style="padding-left:10px;margin-top:16px;"><a class="btn_samll zhuangr_key_js" style="margin-right: 20px;" target="_parent" href="<%=basePath %>index/homepController/toIndex.shtml">再逛逛</a> <a class="btn_samll" target="_parent" href="<%=basePath %>userinfo/centerController/toUserCenterInvest.shtml">我的投资</a> </div>
            </div>
        </div>
</div>
 <script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/eplan/investmentPreview.js"></script>