/**
 * 个人中心-左边客户信息
 */
var ucenterinfom = {
	getUCenterCmVo:function(){
		$.ajax({
			type : "POST",
			url : basePath + "login/userController/getCustomerVo.shtml",
			data : "",
			success : function(data) {
				//当用户登录之后，进行时时刷新数据
				if(undefined != data.result && null != data.result ){
					ucenterinfom.setUserCUserInfo(data.result,data.managementRate);
				}
			}
		});
	},
	//设置左边菜单栏的信息 csvo 用户vo
	setUserCUserInfo:function(csvo,sxflv){
		//积分数量
		var v_jifen = undefined == csvo.availablePoint ? 0 : csvo.availablePoint;
		$("#uc_jifen").html(" 积分： " + v_jifen);
		
		//设置VIP等级
		var vip_level = undefined == csvo.vipLevel || '' == csvo.vipLevel  || null == csvo.vipLevel  ? "" : csvo.vipLevel;
		var v_isVip = undefined == csvo.isVip || "" == csvo.isVip || null == csvo.isVip ? "0" : csvo.isVip;
		var updURL = basePath + "userinfo/centerController/toVipLevelInfo.shtml?vipLevel=VIP" + (vip_level == "" ? "" : vip_level);
		$("#vip_level").attr("href",updURL);
		$("#vip_level_span").html("VIP" + vip_level );
		if("" == vip_level){
			$("#vip_level_span").css("color","#FFFAFA");
		}else{
			$("#vip_level_span").css("color","#0000FF");
		}
		
		var v_discount = undefined == csvo.discount ? 10 : (csvo.discount * 100);
		if("0" == v_isVip && "" == vip_level){
			$("#vip_level_span").css("color","#FFFAFA");
			$("#vip_level_spantop").css("color","#000000");
			$("#vip_level_spantop").html("您当前不是会员，立即开通可享受VIP特权。");
		}else{
			if("0" == v_isVip){
				v_discount = sxflv;
				$("#vip_level_span").css("color","#FFFAFA");
			}
			$("#vip_level_spantop").html(
					'<i class="i_ ml10">当前VIP级别为VIP'+ vip_level +'。</i>' +
					'<br />' + 
					'<i class="i_ ml10">距离VIP' + (Number(vip_level) + Number(1)) + ' 还差'+ (csvo.experienceEnd - csvo.empiricalValue) +'点经验。</i>' +
					'<br />' + 
					'<i class="i_ ml10">当前利息管理服务费 ' + v_discount + '%。</i>'
			);
		}
		
		//经验值展示
		var v_jingyan = undefined == csvo.empiricalValue || null == csvo.empiricalValue || "" == csvo.empiricalValue ? 0 : csvo.empiricalValue;
		var v_experienceStart = undefined == csvo.experienceStart || null == csvo.experienceStart || "" == csvo.experienceStart ? 0 : csvo.experienceStart;
		var v_experienceEnd = undefined == csvo.experienceEnd || null == csvo.experienceEnd || "" == csvo.experienceEnd ? 0 : csvo.experienceEnd ;
//		alert(Number(v_experienceEnd));
		var v_cushu = v_jingyan - v_experienceStart;
		var v_bfbjingyan = v_cushu/Number(v_experienceEnd);
		$("#v_jinyana").html(v_jingyan + "/" + v_experienceEnd);
		$("#v_jinyanb").css("width",Math.round(v_bfbjingyan*100) + '%');
		$("#v_jinyanc").html(v_jingyan + "/" + v_experienceEnd);
		
		//突变点亮
		var vuc_iph = undefined == csvo.isBingPhone || "" == csvo.isBingPhone ? "0" : csvo.isBingPhone;
		var vuc_ren = undefined == csvo.isAttestation || "" == csvo.isAttestation ? "0" : csvo.isAttestation;
		var vuc_shezi = undefined == csvo.isSetTradePwd || "" == csvo.isSetTradePwd ? "0" : csvo.isSetTradePwd;
		var vuc_email = undefined != csvo.email && "" != csvo.email ? "1" : "0";
		if(vuc_iph == "1"){
			$("#uc_iph a").addClass("iph_").removeClass("iph").attr("title","手机已绑定");
		}else{
			$("#uc_iph a").addClass("iph").removeClass("iph_").attr("title","手机未绑定");
		}
		if(vuc_ren == "1"){
			$("#uc_ren a").addClass("ren_").removeClass("ren").attr("title","实名已认证");
		}else{
			$("#uc_ren a").addClass("ren").removeClass("ren_").attr("title","实名未认证");
		}
		if(vuc_shezi == "1"){
			$("#uc_shezi a").addClass("shezi_").removeClass("shezi").attr("title","交易密码已设定");
		}else{
			$("#uc_shezi a").addClass("shezi").removeClass("shezi_").attr("title","交易密码未设定");
		}
		if(vuc_email == "1"){
			$("#uc_email a").addClass("email_").removeClass("email").attr("title","邮箱已设定");
		}else{
			$("#uc_email a").addClass("email").removeClass("email_").attr("title","邮箱未设定");
		}
	}
};

//初始化信息
$(function(){
	ucenterinfom.getUCenterCmVo();
});