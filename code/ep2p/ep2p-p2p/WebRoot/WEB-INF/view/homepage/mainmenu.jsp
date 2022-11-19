<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/login.js"></script> 
<!-- 主菜单 mainMenu 3 -->
<div id="mainNav">
<c:if test="${sessionScope.consumer == null}">
  <div class="frame1000 pos-r">
    <div class="left"><a class="logo" href="<%=basePath %>" title=""></a></div>
    <div class="fr menu_ul">
      <ul class="menu_ul mainMenu">
        <li><a href="<%=basePath%>">首页</a><span></span></li>
        <li><a href="#">我要投资</a> 
          <div class="sub_menu"> <a href="<%= basePath%>business/financialProductsManageController/index/toFinProdPage.shtml">e计划</a> <a href="<%= basePath%>business/optionalInvestController/index/toOptionalInvestList.shtml">e首房/e抵押</a> <a href="<%=basePath%>mybids/transferController/index/toTransferList.shtml">债权转让</a> </div>
          <span></span> </li>
        <li><a href="<%=basePath %>business/borrowController/toBorrowPage.shtml">我要借款</a><span></span></li>
        <li><a href="#" onclick="safeProtectPage()">安全保障</a> <span></span></li>
        <li><a href="#" onclick="bankcommuity.openBankCommuity();">理财社区</a><span></span></li>
      </ul>
    </div>
    <div class="clear"></div>
    <div  class="v_login_before fr"> 
		<div class="goLogin"><a class="" onclick="login.openLoginWin();"><span class="noLoginImg"></span>立即登录 </a><span class="dot"></span></div>     
    </div>
  </div>
</c:if>
 
<%-- 用户登录以后 --%>
 <c:if test="${sessionScope.consumer != null}">
  <div class="frame1000 pos-r">
    <div class="left"><a class="logo" href="<%=basePath %>" title=""></a></div>
    <div class="fr menu_ul">
      <ul class="menu_ul mainMenu">
        <li><a href="<%=basePath%>">首页</a><span></span></li>
        <li><a href="#">我要投资</a>
          <div class="sub_menu"> <a href="<%= basePath%>business/financialProductsManageController/index/toFinProdPage.shtml">e计划</a> <a href="<%= basePath%>business/optionalInvestController/index/toOptionalInvestList.shtml">e首房/e抵押</a> <a href="<%=basePath%>mybids/transferController/index/toTransferList.shtml">债权转让</a> </div>
          <span></span> </li>
        <li><a href="<%=basePath %>business/borrowController/toBorrowPage.shtml">我要借款</a><span></span></li>
        <li><a href="#" onclick="safeProtectPage()">安全保障</a> <span></span></li>
        <li><a href="#" onclick="bankcommuity.openBankCommuity();">理财社区</a><span></span></li>
      </ul>
    </div>
    <div class="clear"></div>
    <div  class="v_login fr"> 
		<div class="goLogin">
			<a href="<%=basePath %>userinfo/centerController/toUserCenterHome.shtml" class="ffffff">
			<span class="hasLoginImg">
				<c:if test="${empty sessionScope.consumer.imageUrl}">
       				<img src="<%=basePath %>theme/default/images/tx.png" />
       			</c:if>
       			<c:if test="${!empty sessionScope.consumer.imageUrl}">
       				<img src="<%=basePath %>${sessionScope.consumer.imageUrl}" style="border-radius:15px;">
       			</c:if>
			</span>
			我的账户 </a>
			<span class="dot"></span>
		</div>  
      <!-- 主导航login S== -->
      <div class="login_in">
        <div class="org_txt f-16">
          <p>
          	<c:if test="${!empty sessionScope.consumer.customerName}">
          		<c:out value="${sessionScope.consumer.customerName}"></c:out>
          	</c:if>
          	<c:if test="${empty sessionScope.consumer.customerName}">
          		<c:out value="${sessionScope.consumer.phoneNo}"></c:out>
          	</c:if>
          </p>
          <p>￥<fmt:formatNumber value="${sessionScope.consumer.availableBalance}" pattern="#,##0.00#"/></p>
          <div class="showvip mt10 f-12">
          	<span class="org_line" style="width:36%;" id="cust_amenu_viplev_num"><c:out value="${sessionScope.consumer.vipLevel}"></c:out></span>
          	<span id="cust_amenu_alvip_leven">${sessionScope.consumer.availablePoint}/100</span>
          </div>
        </div>
        <div class="list f-14">          
          <ul>
          	<li class="on mt5"><span class="fr" id="cust_amenu_sing_num">连续${sessionScope.consumer.signday}天</span>每日签到</li>
            <li><span class="fr" id="cust_amenu_ticke_num">${sessionScope.consumer.interestTicketCount}张</span>加息券</li>
            <li><span class="fr" id="cust_amenu_nmsg_num">${sessionScope.consumer.messRecordCount}</span>未读消息</li>
            <li class="tc exit_s" onclick="login.loginout();">退出</li>
          </ul>
        </div>
      </div>
      <!-- 主导航login E== --> 
    </div>
  </div>
 </c:if>
</div>