<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<!--滚动后 浮动导航  0 -->
<div class="nav_small">
<%-- 判断用户是否登陆，显示注册有礼图标--%>
<c:if test="${sessionScope.consumer == null}">
  <div class="frame1000 pos-r">
    <div class="left"><a href="<%=basePath %>"><img src="<%= basePath%>theme/default/images/logo_small.png"></a></div>
	
    <div class="fr">
		<div  class="small_login_before fr"> 
		<div class="goLogin"><a class="" onclick="login.openLoginWin();"><span class="noLoginImg"></span>立即登录 </a><span class="dot"></span></div>     
		</div>
     
    </div>
    
    <div class="fr"><a href="#" class="reg_bt">注册有礼</a></div>
    
    <div class="fr" style="width:430px;">
      <ul class="menu_small mainMenu">
        <li><a href="<%=basePath%>">首页</a><span></span></li>
        <li><a href="javascript:;">我要投资</a>
          <div class="sub_menu_small"> <a href="<%= basePath%>business/financialProductsManageController/index/toFinProdPage.shtml">e计划</a> <a href="<%= basePath%>business/optionalInvestController/index/toOptionalInvestList.shtml">e首房/e抵押</a> <a href="<%=basePath %>mybids/transferController/index/toTransferList.shtml">债权转让</a> </div>
          <span></span> </li>
        <li><a href="<%=basePath %>business/borrowController/toBorrowPage.shtml">我要借款</a><span></span> </li>
        <li><a href="javascript:;" onclick="safeProtectPage()">安全保障</a> 
          <!--<div class="sub_menu_small">
                    <a href="javascript:;">家财保险</a>
                    <a href="javascript:;">企业财产险</a>
                    <a href="javascript:;">货物运输保险</a>
                </div>--> 
          <span></span> </li>
        <li><a href="javascript:;" onclick="bankcommuity.openBankCommuity();">理财社区</a><span></span></li>
      </ul>
    </div>
	<div class="clear"></div>
  </div>
 </c:if>
 
 <c:if test="${sessionScope.consumer != null}">
  <div class="frame1000 pos-r">
    <div class="left"><a href="<%=basePath %>"><img src="<%= basePath%>theme/default/images/logo_small.png"></a></div>
    <div class="fr">
      <div  class="small_login"> 
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
        <%-- 我的账户 展开 --%>
        <div class="login_s">
          <div class="org_txt f-16">
            <p>
            	<c:out value="${sessionScope.consumer.customerName}"></c:out>
            </p>
            <p>
            	￥<fmt:formatNumber value="${sessionScope.consumer.availableBalance}" pattern="#,#00.00#"/>
            </p>
            <div class="showvip mt10 f-12"><span id="cust_bmenu_viplev_num">12/100</span><span class="grey_line" style="width:36%;">Vip<c:out value="${sessionScope.consumer.vipLevel}"></c:out></span></div>
          </div>
          <div class="list f-14">
            <p><span class="fr" id="cust_bamenu_sing_num">连续<c:out value="${sessionScope.consumer.signday}"></c:out>天</span>每日签到</p>
            <ul>
              <li class="on mt5"><span class="fr" id="cust_smenu_ticke_num">${sessionScope.consumer.interestTicketCount}张</span>加息券</li>
              <li><span class="fr" id="cust_bmenu_nmsg_num">${sessionScope.consumer.messRecordCount}</span>未读消息</li>
              <li class="tc exit_s" onclick="login.loginout();">退出</li>
            </ul>
          </div>
        </div>       
      </div>
    </div>
    <div class="fr"></div>
    <div class="fr" style="width:430px;">
      <ul class="menu_small mainMenu">
        <li><a href="<%=basePath%>">首页</a><span></span></li>
        <li><a href="javascript:;">我要投资</a>
          <div class="sub_menu_small"> <a href="javascript:;">e计划A</a> <a href="javascript:;">e首房/e抵押</a> <a  href="<%=basePath%>mybids/transferController/index/toTransferList.shtml">债权转让</a> </div>
          <span></span> </li>
        <li><a href="<%=basePath %>business/borrowController/toBorrowPage.shtml">我要借款</a><span></span> </li>
        <li><a href="javascript:;" onclick="safeProtectPage()">安全保障</a> 
          <!--<div class="sub_menu_small">
                    <a href="javascript:;">家财保险</a>
                    <a href="javascript:;">企业财产险</a>
                    <a href="javascript:;">货物运输保险</a>
                </div>--> 
          <span></span> </li>
        <li><a href="javascript:;" onclick="bankcommuity.openBankCommuity();">理财社区</a><span></span></li>
      </ul>
    </div>
  </div>
 </c:if>
 
</div>