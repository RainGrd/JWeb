<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>

<%-- 局部脚本 --%>
<script type="text/javascript" src="<%= basePath%>theme/js/personcenter/accountoverview/personcAccViewPage.js"></script>

 <div class="sidebar_b fl" id="withdraw_div"> 
   <div class="tips fl mt20"> 
    <ul> 
     	<li> 
     	<a href="<%=basePath %>userinfo/centerController/toAutomaticBidPage.shtml"> 
     		   <h5>账户余额 
     		   		<img src="<%=basePath %>theme/default/images/l_sok.png" />
     		   </h5> 
		       <div class="colororg f-20 mt10">
		        	￥<fmt:formatNumber value="${accountBalance}" pattern="#,##0.00#"/>
		       </div> 
		       <p class="tc"><img src="<%=basePath %>theme/default/images/tt.png" /></p> 
		       <div class="show"> 
			        <h5>账户余额 <img src="<%=basePath %>theme/default/images/l_sok.png" /></h5> 
			        <div class="colororg f-20 mt10">
			        	￥<fmt:formatNumber value="${accountBalance}" pattern="#,##0.00#"/>
			        </div> 
			        <p class="tl mt10">可用余额：
			        	<font class="colororg">￥<fmt:formatNumber value="${aivo.availableBalance}" pattern="#,##0.00#"/></font>
			        </p> 
			        <p class="tl">冻结金额：
			        	<font class="colororg">￥<fmt:formatNumber value="${aivo.freezingAmount}" pattern="#,##0.00#"/></font>
			        </p> 
		       </div> 
       	</a> 
       </li> 
       <li> 
       <a href="javascript:void(0);"> 
       		<h5>我的总资产</h5> 
      		<div class="colororg f-20 mt10">
        		￥<fmt:formatNumber value="${totalAssets}" pattern="#,##0.00#"/>
       		</div> 
       		<p class="tc"><img src="<%=basePath %>theme/default/images/tt.png" /></p> 
	        <div class="show"> 
	            <h5>我的总资产</h5> 
	            <div class="colororg f-20 mt10">
	             	￥<fmt:formatNumber value="${totalAssets}" pattern="#,##0.00#"/>
	            </div> 
		        <p class="tl mt10">可用余额：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.availableBalance}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">冻结金额：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.freezingAmount}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">待收本金：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.dueinAmount}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">待收利息：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.dueinInterest}" pattern="#,##0.00#"/></font>
		        </p> 
	       </div> 
	   </a> 
       </li> 
       <li> 
       <a href="javascript:void(0);"> <h5>累积净收益</h5> 
	       <div class="colororg f-20 mt10">
	          	￥<fmt:formatNumber value="${totalRevenue}" pattern="#,##0.00#"/>
	       </div> 
	       <p class="tc"><img src="<%=basePath %>theme/default/images/tt.png" /></p> 
	       <div class="show"> 
		        <h5>累积净收益</h5> 
		        <div class="colororg f-20 mt10">
		         	￥<fmt:formatNumber value="${totalRevenue}" pattern="#,##0.00#"/>
		        </div> 
		        <p class="tl mt10">投资利息：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.investInterest}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">加息收益：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.rateInterest}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">投资奖励：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.investmentIncentive}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">红包收益：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.redEnvelope}" pattern="#,##0.00#"/></font>
		        </p> 
		        <p class="tl">推荐奖励：
		        	<font class="colororg">￥<fmt:formatNumber value="${aivo.recommendedAwards}" pattern="#,##0.00#"/></font>
		        </p> 
	       </div> 
       	</a> 
       </li> 
    </ul> 
   </div> 
   
   <div class="recharge fl mt20"> 
    <a href="<%=basePath%>securityCenter/bankController/toBankList.shtml"><img src="<%=basePath %>theme/default/images/card.png" /></a> 
    <div class="mt10"> 
     <span class="fr"><a href="###" class="btn_samll" style="padding:5px 17px;width:auto;" onclick="clickWithdraw()">提现</a></span> 
     <span><a href="<%=basePath %>recharge/userRechargeController/toUserRecharge.shtml" class="btn_samll" style="padding:5px 17px;width:auto;">充值</a></span>
    </div> 
   </div> 
   
   <div class="account_info mt20"> 
    <div id="CalendarMain"> 
     <div id="title"> 
      <a class="selectBtn month" href="javascript:void(0)" onclick="CalendarHandler.CalculateLastMonthDays();">&lt;</a> 
      <a class="selectBtn selectYear" href="javascript:void(0)" onclick="CalendarHandler.CreateSelectYear(CalendarHandler.showYearStart);">2014年</a> 
      <a class="selectBtn selectMonth" onclick="CalendarHandler.CreateSelectMonth()">6月</a> 
      <a class="selectBtn nextMonth" href="javascript:void(0)" onclick="CalendarHandler.CalculateNextMonthDays();">&gt;</a> 
      <a class="selectBtn currentDay" href="javascript:void(0)" onclick="CalendarHandler.CreateCurrentCalendar(0,0,0);">今天</a> 
     </div> 
     <div id="context"> 
      <div class="week"> 
       <h3> 一 </h3> 
       <h3> 二 </h3> 
       <h3> 三 </h3> 
       <h3> 四 </h3> 
       <h3> 五 </h3> 
       <h3> 六 </h3> 
       <h3> 日 </h3> 
      </div> 
      <div id="center"> 
       <div id="centerMain"> 
        <div id="selectYearDiv"></div> 
        <div id="centerCalendarMain"> 
         <div id="Container"></div> 
        </div> 
        <div id="selectMonthDiv"></div> 
       </div> 
      </div> 
      <!-- 	<div id="foots"><a id="footNow">19:14:34</a></div> --> 
     </div> 
    </div> 

    <div class="detail_info fl"> 
     <div class="title_info fl"> 
      <span class="text_blue2 f-16 select_date fl"><fmt:formatDate pattern="yyyy年MM月dd日" value="${today}" /> 待收</span> 
      <span class="fr href"><a href="<%=basePath%>usercenter/borrowDetailController/toReceiptPlanList.shtml">更多&gt;</a></span> 
     </div> 
     <div id="usercenter_daishou">
     <c:forEach items="${brpmlist}" var="items">
     	<ul class="record_ul"> 
	      <li class="name"><a href="javascript:void(0);" onclick="personcAccview.pronameClick('${items.borrowId}','${items.borrowType}');"><c:out value="${items.borrowName}"></c:out></a></li> 
	      <li class="type">本息</li> 
	      <li class="price">￥<fmt:formatNumber value="${items.capital + items.interest}" pattern="#,##0.00#"></fmt:formatNumber></li> 
	     </ul> 
     </c:forEach>
     </div>
    </div> 
   </div>
   
	<div class="ad fl pos-r oh" id="uesr_banner">
		<ul class="pos-a">
			<c:forEach items="${pubActivList}" var="itemes">
			<li class="fl_">				
				<img src='<%=basePath %>${itemes.activityImage}' width="655" height="180" alt="广告位" />
				<div class="al_l_list_r fr_ ml12">
					<p class="mt15 colorDarkBlue size16 pr15"><c:out value="${itemes.activityName}"></c:out></p>
					<p class=" bdc3c7 size12 mt5">
						<img src="<%=basePath %>theme/default/images/clockGray.png" class="mr10 mt1 fl" />
						<fmt:parseDate var="startDateObj" value="${itemes.activityStartDate}" type="DATE" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value='${startDateObj}' pattern='yyyy年MM月dd日' /> 
						 - 
						 <fmt:parseDate var="endDateObj" value="${itemes.activityEndDate}" type="DATE" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value="${endDateObj}" pattern="yyyy年MM月dd日"/>
					</p>
					<p class="mt30"><a href="<%=basePath %>mybids/borrowNewStandardController/index/toBorrowStandardInfo.shtml?pid=${itemes.pid}" class="btn" >查看详情</a></p>
				</div>				
			</li>
			</c:forEach>
		</ul>
		<div class="scrollBoxBtn">
             <a href="javascript:;" class="scrollPageP">&lt;</a>
             <a href="javascript:;" class="scrollPageN">&gt;</a>
        </div>
	</div>

	<div class="clearfix"></div> 
</div>