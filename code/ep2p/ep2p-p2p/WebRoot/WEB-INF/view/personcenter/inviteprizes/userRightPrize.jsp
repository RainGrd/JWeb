<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/inviteprizes/userRightPrize.js"></script>

<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/inviteprizes/jquery.zclip.min.js"></script>

<script type="text/javascript" src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/plugins/swfobject/jquery.zclip.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/plugins/swfobject/jquery.qrcode.min.js"></script>
 <script type="text/javascript">
			$(function(){
				$("#copyBtn").zclip({
					path: '<%=basePath%>resources/plugins/swfobject/ZeroClipboard.swf',
					copy: $('#copyTxt').text()
				});
				var yqyj = $("#copyTxt").html();
				$("#code").qrcode({ 
				    render: "canvas", //canvas方式 
				    width: 150, //宽度 
				    height:150, //高度 
				    text: yqyj //任意内容 
				});
			});
		</script>

<div class="sidebar_b borrow_div fl">
	<!--  邀请有奖 s-->
	<div class="yao m_auto">
		<div class="kong30"></div>
		<div class="yao_banenr">
<%-- 			<img src="<%=basePath %>theme/default/images/yao_1.png" alt="i am is banner" class="" /> --%>
			<img src="" alt="i am is banner" class="yaoImage_" />
		</div>
		<div class="kong30"></div>
		<div class="yao_c">
			<div class="yao_c_l colorDarkBlue size16 fl_" >
				<p class="yao_c_l_p">
					我的推荐邀请码 <a href="javascript:void(0)" class="2980b9 ml30 ccc">D0001</a>
				</p>
				<p class="yao_c_l_p">
					我的推荐邀请链接 <a href="#" style="color: gray" class="2980b9 ml30 ddd" id="copyTxt"><%=basePath%>login/userController/toRegistered.shtml?referralCode=${referCode}</a>
				</p>
				<div class="kong30"></div>
				<div class="yao_free b0b0b0 size14">
					<div class="yao_free_a fl_">
						<p class="yao_free_b1 inline_block" onclick="javascript:window.open('http://weibo.com');"></p>
						<p class="tc w75" onclick="javascript:window.open('http://weibo.com');">微博分享</p>
					</div>
					<div class="yao_free_a fl_ ml15">
						<p class="yao_free_b2 inline_block  ml15" onclick="javasrcript:window.open('https://wx.qq.com')"></p>
						<p class="tc" onclick="javasrcript:window.open('https://wx.qq.com')">微信分享</p>
					</div>
					<div class="yao_free_a fl_ ml35" id="copyBtn">
						<span class="yao_free_b3 inline_block  ml15"></span>
						<p class="tc" id="copyBtn">复制链接分享</p>
					</div>
				</div>
			</div>
			<div class="yao_c_r fr_ " >
<%-- 				<img src="<%=basePath %>theme/default/images/yao_2.png" alt="" class="mt10" /> --%>
				<div id="code"></div>
				<p class="tc size16 yao_c_er colorDarkBlue">邀请推荐二维码</p>
			</div>
			
		</div>
		
		<div class="kong25"></div>
		<div class="yao_titl  size16 colorDarkBlue">
			<p>我分享的合伙人</p>
		</div>
		<div class="yao_tab_list size16">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="esc_t"> 
	           <thead> 
	            <tr class="yao_th ">
	             <th width="15%">用户名</th> 
	             <th width="35%">注册时间</th> 
	             <th width="25%">手机验证</th> 
	             <th width="25%">分享获得奖励</th> 
	            </tr>
	           </thead> 
	           <tbody class="yao_tr colorDarkBlue my_share">
	            <tr> 
	             <td class="size14">张晓明</td> 
	             <td class="size14">2015-01-01</td> 
	             <td class="">已验证手机</td> 
	             <td class="size14">100000.00</td> 
	            </tr>
	            <tr> 
	             <td class="size14">张晓明</td> 
	             <td class="size14">2015-01-01</td> 
	             <td class="">已验证手机</td> 
	             <td class="size14">100000.00</td> 
	            </tr>
	            <tr> 
	             <td class="size14">张晓明</td> 
	             <td class="size14">2015-01-01</td> 
	             <td class="">已验证手机</td> 
	             <td class="size14">100000.00</td> 
	            </tr>
	            <tr> 
	             <td class="size14">张晓明</td> 
	             <td class="size14">2015-01-01</td> 
	             <td class="">已验证手机</td> 
	             <td class="size14">100000.00</td> 
	            </tr>
	            

	           </tbody>
	          </table>
		</div>
		<div class="kong35"></div>
		<div class="yao_foot colorDarkBlue yqyj_">
			
		</div>
		<div class="kong55"></div>
	</div>
	<!-- 邀请有奖 e-->

</div>
