<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<div class="sidebar_b borrow_div fl">
	<!--  安全中心 s-->
	<div class="m_auto an_x w890">
		<div class="kong30"></div>
		<div class="an_x_t lh55 colorDarkBlue hui_l">
		<span class="size24 w280 securityScore">您当前的安全得分为分</span>
		<span class="fr_">
			<span class="fl_ an_x_jd inblock  ">
				<!--绿色进度条长度为535px -->
				<!--an_x_jd_lv_se_7 大于等于75的时候加载这个类
				an_x_jd_lv_se_5 大于等于50的时候加载这个类
				an_x_jd_lv_se_2 大于等于25的时候加载这个类-->
				<i class="i_ an_x_jd_lv inline_block fl_ an_x_jd_lv_se_7 jindu_" style="width:0%;"></i>
			</span>
			<span class="fr_ c9b9c9d size14">
			 <i class="i_ ml15 an_x_jd_jifen">0</i>/100
			</span>
		</span>
		</div>
		<div class="kong20"></div>
		<div class="an_x_gr">
			<p class="lh40 colorDarkBlue size16">
				个人资料
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_1.png" im="0" jf="10" class="pos-a an_x_gr_list_im phone_"/>
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class="c96bc7f ml10 mr40"> 保护中</span><span class="w110 inblock">安全手机</span><span class="" id="phoneNo"></span>
				<span class="fr_ c2980b9 cus_p phoneBd" onclick="safetyIndex.bindEmailPhonePage()">绑定</span>
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="5" class="pos-a an_x_gr_list_im username_" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 unset_"> 未设定</span><span class="w110 inblock">用户名</span><span class="" id="userName"></span>
				<span class="fr_ c2980b9 cus_p updateCustName" onclick="safetyIndex.updateCustName();">设定</span>
			</p>
			
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="10" class="pos-a an_x_gr_list_im softEmail_" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 unb_"> 未绑定</span><span class="w110 inblock">安全邮箱</span><span class="" id="email"></span>
				<span class="fr_ c2980b9 cus_p email_key_js emailBd" onclick="safetyIndex.bindEmail();">绑定</span>
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="20" class="pos-a an_x_gr_list_im smrzPic" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 smrz_"> 未认证</span><span class="w110 inblock">实名认证</span><span class="">实名认证方便日后转账和提高账户安全级别</span>
				<span class="fr_ c2980b9 cus_p smrzOk">认证</span>
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="5" class="pos-a an_x_gr_list_im addres_" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 unaddress_"> 未设定</span><span class="w110 inblock">联系地址</span><span class="homeAddress"></span>
				<span class="fr_ c2980b9 cus_p updateAddress" onclick="safetyIndex.updateHomeAddress();">设定</span>
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="5" class="pos-a an_x_gr_list_im relation_" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 relat_"> 未设定</span><span class="w110 inblock">紧急联系人</span>姓名:<span class="emergerncyName"></span><span class="ml20">关系</span>
				<select class="bgffffff ml10 mr10 emergerncyRelation">
					<option class="ddd"  value="1"></option> 
				</select>
				联系方式:<span class="emergerncyPhoneNo"></span>
				<span class="fr_ c2980b9 cus_p updateEmergerncyName" onclick="safetyIndex.updateEmergerncyName();">设定</span>
			</p>
			
		</div>
		<div class="kong30"></div>
		<div class="an_x_anquan">
			<p class="lh40 colorDarkBlue size16">
				安全信息
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_1.png" im="0" jf="20" class="pos-a an_x_gr_list_im loginPwd_"  />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class="c96bc7f ml10 mr40"> 保护中</span><span class="w110 inblock">登录密码</span>上次登录时间：<span class="loginTime"></span>
				<span class="fr_ c2980b9"><i class="i_ mr10 cus_p login_key_js" onclick="safetyIndex.toupdateLoginPwd();">修改</i>|<i class="i_ ml10 cus_p" onclick="safetyIndex.forgetLoginPwd();">忘记登录密码</i></span>
			</p>
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="20" class="pos-a an_x_gr_list_im tradss_" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 trads_"> 未设定</span><span class="w110 inblock">交易密码</span>上次交易时间：<span class="tradTime"></span>
				<span class="fr_ c2980b9">
				<i class="i_ mr10 cus_p jiaoyi_key_js none update_tradpwd" onclick="safetyIndex.toupdateTrad();">修改</i><i class="i_ mr10 cus_p jiaoyi_key_js none set_tradpwd" onclick="safetyIndex.setTradPwd();">设定</i>|<i class="i_ ml10 cus_p" onclick="safetyIndex.forgetTradPwd();">忘记交易密码</i></span>
			</p>
			
			<p class="lh60 hui_l oh size14">
				<i class="i_ inblock pos-r an_x_gr_list_i fl_ ">
					<!--如果是没有设定的就是灰色图片 那么下面的图片引用an_2.png-->
					<img src="/ep2p/theme/default/images/an_2.png" im="0" jf="5" class="pos-a an_x_gr_list_im bank_ts" />
				</i>
				<!--如果是没有设定的就是灰色文字 那么下面的c96bc7f样式不要添加-->
				<span class=" ml10 mr40 bank_"> 保护中</span><span class="w110 inblock">银行卡</span><span class="bankNum"></span><span class="lh33 p0 ml20 tc size16 tis_yin_tian colorDarkBlue w100 tan_ka_js"  onclick='safetyIndex.addBankCare();'">添加银行卡</span>
				<span class="fr_ c2980b9 cus_p" onclick="safetyIndex.banKManger();">管理</span>
			</p>
		</div>
		<div class="kong150"></div>
		<div class="kong100"></div>
		<%-- js脚本 --%>
		<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/securitycenter/safetyIndex.js"></script>
	</div>
	<!-- 安全中心 e-->
</div>


