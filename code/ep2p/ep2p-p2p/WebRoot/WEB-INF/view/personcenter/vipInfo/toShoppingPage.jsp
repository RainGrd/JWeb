<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>

<head>
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/comcss/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/yscf.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>theme/default/css/usercenter.css"/>
</head>

<!-- 购买vip弹层 s-->
						<!-- 购买vip弹层 s-->
						<div class="goumai_vip ">
							<div class="vip_g_b"></div>
							<div class="vip_g m_auto">
								<div class="kong15"></div> 
								<div class="vip_g_tc">
									 <p class="vip_g_t size16 tc">购买VIP</p>
								</div>
								<div class="kong25"></div>
								<div class="vip_g_lists m_auto colorDarkBlue size14">
									<p class="vip_g_list">
									            开通帐号:<span class="colorc plr10 ktzh"></span><i class="size12"></i>
									</p>
									<p class="vip_g_list">
										开通时长:  
										<span class="plr10 vip_radioa inline_block c2980b9">
											<i class="radio_s radio_xiu1 m5 c2980b9 v1_" value="12" val="1"><img src="/ep2p/theme/default/images/radio_x.png" class="block"></i>12个月
											<!--如果选中了i便签的值val=1,否则等于0-->
										</span><span class="plr10 vip_radioa inline_block">
												<i class="radio_s radio_xiu1 m5" value="24"></i>24个月
											</span><span class="plr10 vip_radioa inline_block">
													<i class="radio_s radio_xiu1 m5 "value="36"></i>36个月
												</span>
									</p>
									<p class="vip_g_list">
										应付金额:<span class="colorc vip_w_145 inline_block plr10 ceshi_q">￥120</span>可用余额:<span class="colorc inline_block plr10 kyye"></span><span><a class="btn_samllss g_vip" href="javascript:shoppingVip.chongzhi();">充值</a></span>
									</p>
									<p class="vip_g_list">
										交易密码:<input type="password" class="ml10" id="tradPwd" value="">
									</p>
									<span id="tradPwdError" class="input_tis ts_re  mt5" style="padding:0 5px;display: none;"></span>
									<div class="kong25"></div>
								</div>
								<div class="vip_g_but">
									<a class="btn_samll ml140" href="javascript:shoppingVip.sure();">确定</a><a class="btn_samll_gray ml30 vip_qu_js" href="javascript:shoppingVip.cancle();">取消</a>
								</div>
							</div>
						</div>
						<!-- 购买vip弹层 e-->
<script type="text/javascript">
</script>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/vipInfo/toShoppingPage.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/plugins/rsa/RSA.js"></script>

