<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<input type="hidden" name="customerId" id="customer_id" value="${userId}"/>
<!-- 确认解绑银行卡弹层 s-->
		<div class="goumai_vip">
			<div class="vip_g_b"></div>
			<div class="vip_g m_auto h350">
				<div class="ge_ye_tilte">
							<span class="ge_ye_tilte_nav  ms_t_se">添加银行卡</span>
				</div>
				<div class="kong25"></div>
				<div class="vip_g_lists m_auto colorDarkBlue size14">
					<p class="lh45">
						<span class="w130 inline_block">真实姓名:</span><span class="userName lh22">${userName}</span><i class="c798383" style="margin-left: 130px; display: block;line-height: 20px;">为了您的账户资金安全,只能绑定本人实名认证的银行卡</i>
						
					</p>
					<p class="lh45">
					   <i class="i_ colorc">*</i>
						<span class="w130 inline_block">开户银行:</span>
						<span class="">
							<select class="bgffffff w210" id="belongingBank">
								<option value="中国银行">中国银行</option> 
								<option value="招商银行">中国招商银行</option> 
								<option value="中国农业银行">中国农业银行</option> 
								<option value="建设银行">中国建设银行</option> 
								<option value="上海浦东发展银行银行">上海浦东发展银行</option> 
								<option value="交通银行">交通银行</option> 
								<option value="中国邮政储蓄银行">中国邮政储蓄银行</option> 
								<option value="上海浦东发展银行">上海浦东发展银行</option> 
								<option value="广东发展银行">广东发展银行</option> 
								<option value="中国民生银行">中国民生银行</option> 
								<option value="平安银行(深发展)">平安银行(深发展)</option> 
								<option value="中国光大银行">中国光大银行</option> 
								<option value="兴业银行">兴业银行</option> 
								<option value="华夏银行">华夏银行</option> 
								<option value=">中信银行">中信银行</option> 
							</select>
						</span>
					</p>
					 <p class="lh45">
					    <i class="i_ colorc">*</i>
						<span class="w130 inline_block">银行卡卡号:</span>
						<span class="">
							<input type="text" class="w200 pl10" name="bankcardNo" id="bankcardNo" value=""style="width:196px;" onblur="addBankCard.validateBankCard();"/>
							<span class="input_tis ts_lh w200 mb5  tc " style="margin-left: 107px; display: none" id="bankCard_">
									<!--ts_re 是红色警告背景  
									ts_lh 是黄色警告背景
									ts_lv 是绿色正确提示背景-->
<!-- 										登录密码不能为空 -->
							</span>
						</span>
					 </p>
					<p class="lh45">
							<i class="i_ colorc">*</i>
							<span class="w130 inline_block" >开户行所在省份:</span>
						<span>
							<select name="belongingProvince" id="nowProvince" required="true" onchange="borrow.selectNowProvince();" class="bgffffff w210" missingMessage="请选择现居住址省份!" ></select>
						</span>
					</p>
					 <p class="lh45">
							<i class="i_ colorc">*</i>
							<span class="w130 inline_block">开户行所在城市:</span>
						<span>
							<select class="bgffffff w210" name="belongingCity" id="nowCity" required="true"  missingMessage="请选择现居住址市区!"></select>
						</span>
					</p>
					<p class="lh45">
						<i class="i_ colorc">*</i>
						<span class="w130 inline_block">开户支行名称:</span>
						<span class="">
							<input type="text" class="w200 pl10" name="openAddress" id="openAddress" value="" style="width:196px;"/>
						</span>
					</p>
					<p class="lh45">
						<span class="w130 inline_block"></span>
						<span class="tan_ka_js_g  inblock w250">
						</span>
					</p>
					<p class="lh45">
						<span class="w130 inline_block"></span>
						<span class="">
							<a class="btn_samll  lh20 " href="javascript:addBankCard.saveBankCard();">开启</a>
<!-- 							<a href="http://apis.haoservice.com/lifeservice/bankcard/query?key=1a36f44c8ccd4d90b3cb6c41e0a428a1&card=6214856551072188">test</a> -->
						</span>
					</p>
					<div class="kong35"></div>
				</div>
			</div>
		</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/securitycenter/addBankCard.js"></script>
<!-- 引入下面这个js是为了添加完银行卡后刷新页面 -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>theme/js/personcenter/securitycenter/myBankList.js"></script>
<script type="text/javascript" src="${basePath}theme/js/mywtborrow/borrow.js"></script>
<script type="text/javascript" src="${basePath}theme/js/mywtborrow/cityArray.js"></script>
<script type="text/javascript">
		$(function(){ 
			borrow.initAddBorrow();
		})
	</script>