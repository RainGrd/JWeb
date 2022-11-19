<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/otherinfo/user.js"></script>
<!-- 服务热线 2 -->
<div id="serviceBar">
  <div class="frame1000 size14 pos-r">
    <div class="left"> <span class="fl">服务热线：4009-998-992</span> <span class="ml10 mt6 fl">
    <a href="javascript:void(0)" onclick="playJsAdPopWin();"><img src="<%=basePath %>theme/default/images/qqIcon.png"></a></span>
    
          <div class="weixin_q fl">
              <a target="_blank"><img src="<%=basePath %>theme/default/images/weixinIcon.png"></a> 
              <span class="s_int"><a href="javascript:void(0)"><img src="<%=basePath %>theme/default/images/weixin.png"></a> </span>
          </div>
          
           <div class="weixin_q fl">
           		<a target="_blank"><img src="<%=basePath %>theme/default/images/sinaIcon.png"></a> 
	      		<span class="s_int"><a href="javascript:void(0)"><img src="<%=basePath %>theme/default/images/weibo.png"></a></span>
	      </div>
      <div class="clear"></div>
    </div>
    <ul class="right serviceMenu">
      <li> <a href="javascript:void(0);" class="calculator" onclick="calculator.openCalcPage();">计算器</a> </li>
      <li class="slipeLine"><span>|</span></li>
      <li class="mobile_client"><a href="javascript:void(0)" onclick="openPhonePage()" class="mobileClient">手机客户端</a>
      <span class="u_code">
<%--       <img src="<%=basePath %>theme/default/images/w2.png"> --%>
      </span>
      </li>
      <li class="slipeLine"><span>|</span></li>
      <li> <a href="javascript:void(0)" onclick="noviceGuidePage()">新手指引</a> </li>
      <li> <a href="<%=basePath %>help/helpCenterController/index/toHelpCenterPage.shtml">帮助中心</a> </li>
      <li> <a href="<%=basePath %>about/aboutUsController/index/toAboutUsPage.shtml" >关于我们</a> </li>
      <li class="last"> <a href="javascript:void(0);" class="registerGift" onclick="user.openUserRegisteredWin();">注册有礼</a> </li>
    </ul>
    <div class="clear"></div>
  </div>
</div>