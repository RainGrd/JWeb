<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %> 
<%@include file="/common/taglibs.jsp" %>
 
<script type="text/javascript"> 
	$(function(){  
		countDown("${timeRemain}"/1000);
	});
</script>
<!--内容区-->
       <div class="content mt40">
			<div class="frame1000 bgc oh">
            	<!--头部灰色框-->
                <div class="dropConTop">
                	<div class="dropConTopLeft">
                    	<p class="size16 dropBlue"><c:out value="${borrow.borrowName}"/></p>
                        <p class="size14 colorDarkBlue">
	                        <c:out value="${borrow.borrowCode}"/>
	                        <input type="hidden" value="${borrowId}" id="borrowId" />
					 		<input type="hidden" value="${borrow.startMoney}" id="startMoney" />
					 		<input type="hidden" value="${borrow.endMoney}" id="endMoney" />
					 		<input type="hidden" value="${borrow.customerId}" id="customerId" /> 
				 		</p>
                        <ul class="dropEPlanUl">
                        	<li class="dropEPlanLi1">
                            	<p class="colorc size26 lineHeight62"><span class="size62"><fmt:formatNumber value="${borrow.borrowRate*100}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></span><i class="size26">%</i> 
                            	+<fmt:formatNumber value="${borrow.investRewardScale eq 0 ? 0 : borrow.investRewardScale*100}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber><i class="size16">%</i></p>
                                <p class="colorDarkBlue mt014 size14">年化利率<span style="margin-left:50px; display:inline-block;">投资奖励</span></p>                           
                            </li>
                            <li class="mt30 dropEPlanLi2">
                            	<p class="colorc size26"><c:out value="${borrow.borDeadline}"/>个月</p>
                                <p class="colorDarkBlue size14"> 项目期限</p>
                            </li>
                            <li class="mt30 dropEPlanLi3">
                            	<p class="colorc size26">￥<fmt:formatNumber value="${borrow.borrowMoney}" pattern="#,##0.00"/></p>
                                <p class="colorDarkBlue size14"> 项目金额</p>
                            </li>
                        </ul>
                        <div class="dropQuan dropBlue size12">
                        <c:if test="${borrow.isJiaxiTicket eq 1 }">
	                        <p>
	                     		<i class="block i_   tyb_a_l_i1  bgffffff c2980b9">加息券</i>
	                     	</p> 
                     	</c:if>
                     	<p>
                     		<i class="block i_   tyb_a_l_i1  bgffffff c2980b9">本息保障</i>
                     	</p> 
                      
                        </div>
                        <p class="dropTime size14">发布时间:${borrow.publishTime}</p>
                    </div>
                	<div class="dropConTopRight">
                    	<p class="colorDarkBlue size14">剩余时间: <span id="countDown">00天00时00分00秒</span></p>
                        <div class="dropSchedule"> 
								<dl class="barbox">
						            <dd class="barline">
						              <div divindex="3" id="chartSlide_3" w="<c:out value="${(empty(borrow.borrowProgress) ? 0 : borrow.borrowProgress*100 )}"/>" style="width:<c:out value="${(empty(borrow.borrowProgress) ? 0 : borrow.borrowProgress )}"/>%;" class="charts"></div>
						            </dd>
						         </dl>  
                            <p class="dropIComplete size12"><span class="colorDarkBlue"><fmt:formatNumber value="${borrow.borrowProgress==null?0.00:borrow.borrowProgress*100}" pattern="##.##" minFractionDigits="0" ></fmt:formatNumber>%</span>已完成</p>
                            <p class="dropISurplus size12"><span class="colorDarkBlue">￥<fmt:formatNumber value="${borrow.surplusMoney == null ? borrow.borrowMoney : borrow.surplusMoney }"  pattern="#,##0.00"/></span>剩余</p>
                        </div>
                        <div class="clear"></div>
                      	<p class="colorDarkBlue size12 mt20">可用余额:<span class="colororg">
                        	￥<fmt:formatNumber value="${empty(availableAmount)? 0 : availableAmount}" pattern="#,##0.00"></fmt:formatNumber>
                        	<input type="hidden" id="availableAmount" value="${availableAmount}" />
                        </span><a target="_parent" href="<%=basePath %>recharge/userRechargeController/toUserRecharge.shtml" class="backgroundBlue" style="display:inline-block; margin-left:10px;">充值</a></p>
                        <input type="text" name="monysum" id="investNumber" class="dropMonysum" placeholder="输入投资金额" onblur="count(this)"><span class="ml020">元</span>
                        <ul class="dropEPlanUl2">  
                         	<li>
                            	<p class="colorc size14" id="expectAmount">￥0.00</p><input type="hidden" id="expectAmountVal" value="0"/>
                                 <a class="tipsBtn" href="javascript:;"> 预计收益
                                	<div class="tipsWrap">
                                        <p>根据收款天数自动计算</p>
                                   </div> 
                                 </a>                           
	                        </li>
                            <li>
                            	<p class="colorc size14" id="investReward">￥0.00</p><input type="hidden" id="investRewardVal" value="0"/>
                                <p class="colorDarkBlue size14">投资奖励</p>
                            </li>
                            <li class="width60">
                            	<p class="colorc size14" id="eplanIntegral">0分</p><input type="hidden" id="eplanIntegralVal" value="0"/>
                                <p class="colorDarkBlue size14">可获积分</p>
                            </li>
                        </ul>
                        <c:choose> 
							<c:when test="${borrow.borStatus eq 2 }">    
								<a class="btn dropJionBtn" href="javascript:void(0);" id="nowInvest">立即加入</a>  
		  					</c:when> 
		  					<c:when test="${borrow.borStatus eq 1 && borrow.publishTime != null}">    
								<a class="btn_gray dropJionBtn" href="javascript:void(0);" >立即加入</a>  
		  					</c:when>
							<c:otherwise>   
								<a class="btn_gray dropJionBtn" href="javascript:void(0);" >已满额</a>  
		   					</c:otherwise>
						</c:choose>
                        <div class="dropAgreeBox">
                        	<div class="gou_s" id="agree"></div>
                            <div class="colorDarkBlue size14 dropAgree">同意《e生财富服务协议》</div>
                        </div>
                    </div>
                </div>
                 <form id="investRewardForm">    
							<input type="hidden" id="repaymentAmt" name="repaymentAmt" value=""/><!--借款金额 -->
							<input type="hidden" id="repaymentDeadline" name="repaymentDeadline" value="${borrow.borDeadline}"/><!--借款期限 -->
							<input type="hidden" id="repaymentRate" name="repaymentRate" value="${borrow.borrowRate*100}"/><!--借款利率 -->   
							<input type="hidden" id="repaymentType" name="repaymentType" value="${borrow.repaymentType}"/><!--还款方式 -->
							<input type="hidden" id="rewardRate" name="rewardRate" value="${borrow.investRewardScale*100}"/><!--投资奖励百分比 -->
				</form>
                 <div class="dropConBox">
                	<div class="ge_ye_tilte" id="title_drop">
                        <span class="ge_ye_tilte_nav  ms_t_se" id="product">产品介绍</span><span class="ge_ye_tilte_nav" id="description">项目描述</span><span class="  ge_ye_tilte_nav  " id="history">投资记录</span>
                    </div>
                    <div class="dropTab"  id="finProContent">
                        <table class="dropTab2Table " ><!-- id="finProDescription" --> 
                            <tr>
                                <td>理财产品</td>
                                <td><c:out value="${borrow.borrowName}"/></td>
                            </tr>
                            <tr>
                                <td>期限</td>
                                <td><c:out value="${borrow.borDeadline}"/>个月</td>
                            </tr>
                            <tr>
                                <td>年化利率</td>
                                <td><fmt:formatNumber value="${borrow.borrowRate*100}" pattern="##.##" minFractionDigits="0" ></fmt:formatNumber>%</td>
                            </tr>
                            <tr>
                                <td>加入条件</td>
                                <td><c:out value="${product.joinCondition}"/></td>
                            </tr>
                            <tr>
                                <td>计息时间</td>
                                <td><c:out value="${product.interestTime}"/></td>
                            </tr>
                            <tr>
                                <td>收益方式</td>
                                <td><c:out value="${product.earningMode}"/></td>
                            </tr>
                            <tr>
                                <td>到期时间</td>
                                <td><c:out value="${product.expirationDate}"/></td>
                            </tr>
                            <tr>
                                <td>保障方式</td>
                                <td><c:out value="${product.guaranteeMode}"/></td>
                            </tr>
                            <tr>
                                <td>服务协议</td>
                                <td><c:out value="${product.financeName}"/></td>
                            </tr>
                            <tr>
                                <td>规则介绍</td>
                                <td><c:out value="${product.ruleIntroduction}"/></td>
                            </tr>
                        </table>
                    </div>
                    <div class="dropTab none" id="finProDescription">  
                        <table style="dropTab2Table" id="finProDescription" >
							<tr> 
								<td style="width: 550px;"><c:out value="${product.projectDescription}"/></td>
							</tr>
						</table>
                    </div>
                    <div class="dropTab none " id="finProHistory"> 
                    	<div class="finProHistory">
                    	</div>
						<div class=" tc oh kong100"> 
				        	<p class="kong40"></p>
					         <ul class="page_ul  m_auto inline_block "> 
					          <div id="page" class="m-pagination "></div>
					         </ul> 
					      </div>
                    </div>
                </div>
       </div>
       </div>
   <!--内容区END-->    
  

<script type="text/javascript" src="<%=basePath %>theme/js/mywtinvest/eplan/financialProductInfoManage.js"></script>