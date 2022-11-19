<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<div class="sidebar_a fl">
    <div class="vip">
       <p class="tc">
       		<a href="<%=basePath %>userinfo/centerController/toAccountAvatar.shtml">
       			<c:if test="${empty sessionScope.consumer.imageUrl}">
       				<img src="<%=basePath %>theme/default/images/cont_vip_i.png"  class="mt30">
       			</c:if>
       			<c:if test="${!empty sessionScope.consumer.imageUrl}">
       				<img src="<%=basePath %>${sessionScope.consumer.imageUrl}" style="border-radius:50%;width:90px;" class="mt30">
       			</c:if>
       		</a>
       </p>
       <p class="tc color_ff f-14 mt5">
       	<c:if test="${!empty sessionScope.consumer.customerName}">
       		<c:out value="${sessionScope.consumer.customerName}"></c:out>
       	</c:if>
       	<c:if test="${empty sessionScope.consumer.customerName}">
       		<c:out value="${sessionScope.consumer.phoneNo}"></c:out>
       	</c:if>
       </p>
      
       <%-- 积分展示区域 --%>
       <div class="jifen">
			<p class="color_ff"> 
				<span class="fr" id="uc_jifen">0</span> 
				<a id="vip_level" class="tipsBtn" href="javascript:void(0);">
					<span id="vip_level_span"> VIP </span> 
					 <span id="vip_level_spantop" class="tipsWrap">
					 </span>
				</a>
			</p>
			<p> </p>
			<p class="bar pos-r bgc tl">
				<span id="v_jinyana" class="colororg" style="margin-left: 56px;" >/</span>
				<span id="v_jinyanb" class="org_bar pos-a oh"  style="width: 0%;">
					<i id="v_jinyanc" class="i_ fr_ oh ffffff " style=" position: absolute;left: 56px;">/</i>
				</span>
			</p>
		</div>
		<div class="listico tc">
			<!--类名后面加下划线 是点亮状态-->
			<span id="uc_iph"> <a href="javascript:void(0);" class="iph_"></a></span>
			<span id="uc_ren"> <a href="javascript:void(0);" class="ren"></a></span>
			<span id="uc_shezi"> <a href="javascript:void(0);" class="shezi"></a></span>
			<span id="uc_email"> <a href="javascript:void(0);" class="email"></a></span>
		</div>
   </div>
   <div class="subnav mt15">
       <ul>
	       	<li <c:if test="${requestScope.checkmenu == 'USERMENU_HOME'}"> class="current_li" </c:if> >
	       		<a href="<%=basePath %>userinfo/centerController/toUserCenterHome.shtml"><i class="icon1"></i><span class="">账户总览</span></a>
	       	</li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_INVEST'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>userinfo/centerController/toUserCenterInvest.shtml"><i class="icon2"></i>我的投资</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_BORROW'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>business/myLoanController/toBorrowList.shtml"><i class="icon3"></i>我的借款</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_WELFARE'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>securityCenter/bankController/toWelfareList.shtml?welfareType=1"><i class="icon4"></i>我的福利</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_BONDS'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>mybids/transferController/toTransferManage.shtml"><i class="icon5"></i>债权管理</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_FUNDDTL'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>userinfo/centerController/toUserCashflow.shtml"><i class="icon6"></i>资金流水</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_CHARGE'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>/userinfo/custRechargeWithdrawController/toPayIndex.shtml"><i class="icon7"></i>充值提现</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_SECURITY'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath%>securityCenter/bankController/personalData.shtml"><i class="icon8"></i>安全中心</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_INFODTL'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath %>userinfo/centerController/toUserMessage.shtml"><i class="icon9"></i>消息中心</a>
	        </li>
	        <li <c:if test="${requestScope.checkmenu == 'USERMENU_INVITE'}"> class="current_li" </c:if> >
	        	<a href="<%=basePath %>userinfo/centerController/toUserPrize.shtml"><i class="icon10"></i>邀请有奖</a>
	        </li>
       </ul>
    </div>
</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/common/ucenterinfo.js"></script> 