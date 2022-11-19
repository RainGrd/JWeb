<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/personcenter/myborrowing/borrowDetail.js"></script>
<!-- 借款详情-->
<div class="sidebar_b fl ">
	<div class="ge_ye_tilte">
		<span class="ge_ye_tilte_nav  ms_t_se">借款详情</span>
	</div>
	<div class="borrow_info loanInfo fl f-12">
		<div class="binfo size14">
			<div class="binfotop">
				<div class="fl">${borrow.borrowCode }/${borrow.borrowName }</div>
				<div class="fl "></div>
				<div class="fr w200 colorDarkBlue">还款方式：${borrow.repaymentTypeName }</div>
			</div>
			<div class="cb kong20"></div>
			<div class="ml40">
				<p class="w310">借款金额：<span class="f-24 colorc">￥${borrow.borrowMoney }</span></p>
                            <p>年化率：<span class="f-24 colorc"><fmt:formatNumber value="${borrow.borrowRate*100 }" pattern="0.00"/>%</span></p>
                            <p>管理费率为 <span class="colorc"><fmt:formatNumber value="${borrow.manageExpenseRate*100 }" pattern="0.00"/>%</span></p>
			</div>
				<c:if test="${borrow.borStatus == 5}">
                        <!--还款中状态-->
                        <p class="statusRota"></p>
                </c:if>
                <c:if test="${borrow.borStatus == 6 || borrow.borStatus == 7}">
                        <!--已逾期状态-->
                        <p class="statusRota statusRota2"></p>
                </c:if>
		</div>
	</div>
</div>
<div class="invest_table loanInfoTable" id="tb_01">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t">
        <thead>
            <tr>
                <th width="118px">待还时间</th>
                <th width="44px">期次</th>
                <th width="100px">还款金额</th>
                <th width="100px">应还本金</th>
                <th width="100px">应还利息</th>
                <th width="82px">应还管理费</th>
                <th width="78px">罚息</th>
                <th width="78px">状态</th>
                <th width="252px">备注</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${repays }" var="repay" varStatus="status">
            	<tr>
	                <td>
	                	<fmt:parseDate value="${repay.expireTime }" pattern="yyyy-MM-dd" var="expireTime"/>
	                	<fmt:formatDate value="${expireTime }" pattern="yyyy-MM-dd"/>  
	                </td>
	                <td>${repay.planindex }/${repays.size() }</td> 
	                <td>￥<fmt:formatNumber value="${repay.capital+repay.interest+repay.managementCost+repay.latefee }" pattern="0.00"/> </td>
	                <td>￥<fmt:formatNumber value="${repay.capital }" pattern="0.00"/></td>
	                <td>￥<fmt:formatNumber value="${repay.interest }" pattern="0.00"/></td>
	                <td>￥<fmt:formatNumber value="${repay.managementCost }" pattern="0.00"/> </td>
	                <td>
	                	<c:if test="${repay.latefee != null && repay.latefee != '0.0000'}">
	                		￥<fmt:formatNumber value="${repay.latefee }" pattern="0.00"/>
	                	</c:if> 
	                </td>
	                <td>${repay.receiptPalnStatusName }</td>
	                <td>${repay.repPlaDesc }</td>
	            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<c:if test="${repayInfoVo != null }">
<div class="loanInfoForm">
	<p>可用余额：<span class="colorc plr10">￥<fmt:formatNumber value="${availableAmount }" pattern="0.00"/></span><a href="<%=basePath %>recharge/userRechargeController/toUserRecharge.shtml" class="btn_samllss">充值</a></p>
    <p>还款方式：<i class="radio_s radio_xiu1 m5 " val = "1">
     			</i>为
     			<c:if test="${borrow.borStatus == 6 || borrow.borStatus == 7}">
     				逾期
     			</c:if>
     			<c:if test="${borrow.borStatus == 5}">
     				当期
     			</c:if>
     			还款￥<fmt:formatNumber value="${repayInfoVo.capital+repayInfoVo.interest+repayInfoVo.latefee+repayInfoVo.managementCost }" pattern="0.00"/><br />
                  <strong>${repayDesc }</strong><br />
                  <!--如果选中了i便签的值val=1,否则等于0-->
                <i class="radio_s radio_xiu1 m5 ml75" val = "2"></i>提前还清所有￥<fmt:formatNumber value="${repaymentDto.paymentAmount }" pattern="0.00"/> 
                <span class="tipsBtn">
                    <i class="tipsWrap">
                        提前还款可少支付利息￥<fmt:formatNumber value="${repaymentDto.prepaymentInterest }" pattern="0.00"/>
                    </i>
                </span>
    </p>
    <div class="kong30"></div>
     <a class="btn_samll ml75 loanInfoBtn" href="javascript:void(0)">确认还款</a>
     <input type="hidden" id="borrowId" name = "borrowId" value="${borrow.pid }"/>
</div>
</c:if>
<!-- 立即还款确认账单 s -->
<div class="loanInfoPopUp tan_a" id="payloanInfoPopUp">
	<div class="vip_g_b"></div>
	<div class="vip_g_d tan_s_a m_auto ">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">立即还款确认</span>
		</div>
		<div class="vip_g_lists m_auto colorDarkBlue size14 goumai_vip_d_meial ">
			<!--债权转让成功提示信息-->
			<div class="success_div fl m_auto" style="width: 100%;">
				<h4>${repayInfoVo.borrowName }<span id="transferCode">${repayInfoVo.borrowCode }</span></h4>
                      <p>借款日期：<span>${repaymentDto.borrowTime }</span></p>
                      <p>借款金额：<span>￥<fmt:formatNumber value="${repayInfoVo.borrowMoney }" pattern="0.00"/></span></p>
                      <p>还款方式：<span>${repayInfoVo.repaymentType }</span></p>
                      <p>期次：<span>${repayIndex }</span></p>
                      <p>本期还款日：<span>
                         <fmt:parseDate value="${repayInfoVo.waitRepayTime }" pattern="yyyy-MM-dd" var="waitRepayTime"/>
	                	 <fmt:formatDate value="${waitRepayTime }" pattern="yyyy-MM-dd"/>  
                      </span></p>
                      <p>本期待还款：<span>￥<fmt:formatNumber value="${repayInfoVo.capital+repayInfoVo.interest+repayInfoVo.latefee+repayInfoVo.managementCost }" pattern="0.00"/></span></p>
                      <div class="loanInfoGray">
                      	<p>本金：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repayInfoVo.capital }" pattern="0.00"/></span></p>
                      	<c:if test="${repayInfoVo.interest!= null && repayInfoVo.interest!= 0 }">
                        	<p>利息：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repayInfoVo.interest }" pattern="0.00"/></span></p>
                        </c:if>
                        <c:if test="${repayInfoVo.latefee!= null && repayInfoVo.latefee!= 0 }">
                        	<p>逾期罚息：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repayInfoVo.latefee }" pattern="0.00"/></span></p>
                        </c:if>
                        <c:if test="${repayInfoVo.managementCost!= null && repayInfoVo.managementCost!= 0 }">
                        	<p>管理费：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repayInfoVo.managementCost }" pattern="0.00"/></span></p>
                        </c:if>
                      </div>
				<p class="kong40"></p>
                      <p class="colorDarkBlue">应付金额：￥<span id="repayAmount" class="colorc w130"><fmt:formatNumber value="${repayInfoVo.capital+repayInfoVo.interest+repayInfoVo.latefee+repayInfoVo.managementCost }" pattern="0.00"/></span>可用余额：￥<span id="availableAmount" class="colorc mr10">${availableAmount }</span><a href="<%=basePath %>recharge/userRechargeController/toUserRecharge.shtml" class="btn_samllss">充值</a></p>
				<p class="colorDarkBlue">交易密码：<input type="password" id="tradePassword" class="w205 inline_block size14 000000_ fui_juan_chong">
		    	<br>
		    		<div id="payrepayError" class="input_tis ts_lh w210 mb5 fl none_" style="margin-left: 70px">&nbsp;&nbsp;</div>
				</p>
				<div class="kong20"></div>
				<div style="margin-top: 20px;">
					<input type="hidden" id="borrowId" name = "borrowId" value="${repaymentDto.borrowId }"/>
					<a class="btn_samll loanPopupSuessBtn" id="repaySuccess" href="javascript:void(0)" onclick="javascript:void(0)">确定</a>
					<a class="btn_samll_gray ml20 loanPopClose " href="javascript:void(0)" onclick="javascript:void(0)">取消</a>
				</div>
			</div>
		</div>
	</div>
    <div class="dropPopupBox2" id="payRepayResult" >
          <div class="dropPopupH4">
              <h4 class="size16 dropBlue">还款结果</h4>
          </div>
          <div class="dropPopupSuessBox">
              <div class="dropPopupSuess">
                  <h4 class="size18 colorDarkBlue"><span class="yes"></span>恭喜，${repaymentDto.borrowName }</h4>
                  <h2 class="size24 colororg">还款成功！</h2>
              </div>
              <div class="dropPopupSuessInfo">
                  <ul>
                      <li>本次还款金额：<font class="colororg">￥<fmt:formatNumber value="${repayInfoVo.capital+repayInfoVo.interest+repayInfoVo.latefee+repayInfoVo.managementCost }" pattern="0.00"/></font></li>
                  </ul>
                  <div class="ti4" style="margin-top:45px;"><a class="btn_samll" style="margin-right: 20px;" href="javascript:void(0)">返回首页</a> <a class="btn_samll_gray" href="${basePath}business/myLoanController/toBorrowList.shtml">返回</a> </div>
              </div>
          </div>
       </div>
</div>
<!-- 立即还款确认账单 e -->

<!-- 提前还款确认账单 s-->
<div class="loanInfoPopUp tan_a" id="loanInfoPopUp">
	<div class="vip_g_b"></div>
	<div class="vip_g_d tan_s_a m_auto ">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se">提前还款确认</span>
		</div>
		<div class="vip_g_lists m_auto colorDarkBlue size14 goumai_vip_d_meial ">
			<!--债权转让成功提示信息-->
			<div class="success_div fl m_auto" style="width: 100%;">
				<h4>${repaymentDto.borrowName }<span id="transferCode">${repaymentDto.borrowCode }</span></h4>
                      <p>借款日期：<span>${repaymentDto.borrowTime }</span></p>
                      <p>借款金额：<span>￥<fmt:formatNumber value="${repaymentDto.borrowMoney }" pattern="0.00"/></span></p>
                      <p>还款方式：<span>${repaymentDto.repaymentTypeName }</span></p>
                      <p>提前还款金额：<span>￥<fmt:formatNumber value="${repaymentDto.paymentAmount }" pattern="0.00"/></span></p>
                      <div class="loanInfoGray">
                      	<p>本金：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repaymentDto.capital }" pattern="0.00"/></span></p>
                      	<c:if test="${repaymentDto.interest!= null && repaymentDto.interest!= 0 }">
                        	<p>利息：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repaymentDto.interest }" pattern="0.00"/></span></p>
                        </c:if>
                      	<c:if test="${repaymentDto.managementCost!= null && repaymentDto.managementCost!= 0 }">
                        	<p>管理费：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repaymentDto.managementCost }" pattern="0.00"/></span></p>
                        </c:if>
                        <c:if test="${repaymentDto.latefee!= null && repaymentDto.latefee!= 0 }">
                        	<p>逾期罚息：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repaymentDto.latefee }" pattern="0.00"/></span></p>
                        </c:if>
                        <c:if test="${repaymentDto.prepaymentFee!= null && repaymentDto.prepaymentFee!= 0 }">
                        	<p>提前还款罚息：<span>￥<span class="colorc w130"><fmt:formatNumber value="${repaymentDto.prepaymentFee }" pattern="0.00"/></span></p>
                        </c:if>
                      </div>
				<p class="kong40"></p>
                      <p class="colorDarkBlue">应付金额：<span class="colorc w130">￥<fmt:formatNumber value="${repaymentDto.paymentAmount }" pattern="0.00"/></span>可用余额：<span class="colorc mr10">￥${availableAmount }</span><a href="" class="btn_samllss">充值</a></p>
				<p class="colorDarkBlue">交易密码：<input type="password" id="tradePassword_2" class="w205 inline_block size14 000000_ fui_juan_chong">
		    	<br>
		    		<div id="promptError" class="input_tis ts_lh w210 mb5 fl none_" style="margin-left: 70px">&nbsp;&nbsp;</div>
				</p>
				<div class="kong20"></div>
				<div class="tc">
					<input type="hidden" id="borrowId" name = "borrowId" value="${repaymentDto.borrowId }"/>
					<a class="btn_samll loanPopupSuessBtn" href="javascript:void(0)" onclick="javascript:void(0)">确定</a>
					<a class="btn_samll_gray ml20 loanPopClose " href="javascript:void(0)" onclick="javascript:void(0)">取消</a>
				</div>
			</div>
		</div>
	</div>
    <div class="dropPopupBox2" >
          <div class="dropPopupH4">
              <h4 class="size16 dropBlue">还款结果</h4>
          </div>
          <div class="dropPopupSuessBox">
              <div class="dropPopupSuess">
                  <h4 class="size18 colorDarkBlue"><span class="yes"></span>恭喜，${repaymentDto.borrowName }</h4>
                  <h2 class="size24 colororg">还款成功！</h2>
              </div>
              <div class="dropPopupSuessInfo">
                  <ul>
                      <li>本次还款金额：<font class="colororg">￥<fmt:formatNumber value="${repaymentDto.paymentAmount }" pattern="0.00"/></font></li>
                  </ul>
                  <div class="ti4" style="margin-top:45px;"><a class="btn_samll" style="margin-right: 20px;" href="javascript:void(0)">返回首页</a> <a class="btn_samll_gray" href="javascript:$('.loanInfoPopUp').hide();">返回</a> </div>
              </div>
          </div>
       </div>
</div>
<!-- 提前还款确认账单 e-->


<script type="text/javascript" src="${basePath}theme/js/personcenter/myborrowing/personalCenterBorrow.js"></script>
