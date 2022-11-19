<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/mywtborrow/borrow.js"></script>
<script type="text/javascript" src="${basePath}theme/js/mywtborrow/cityArray.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>e生财富</title>
	</head>
	<body>
		<!-- 个人基本资料 -->
		<div class="w1200 m_auto">
			<form action="" method="post" id="basicDataForm" name="basicDataForm">
				<div class="bo_title w1140 m_auto">
					<div class="kong50"></div>
					<c:if test="${borrow.borrowType==1}">
						<div class="bo_t_quan">
							<span class="w60 tc inline_block w1140">
								<img src="<%=basePath %>theme/default/images/bo_1.png" /><i class="i_ bo_title_line ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_2.png" /><i class="i_ bo_title_line ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_3.png" /><i class="i_ bo_title_line ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_4.png" />
							</span>
						</div>
						<div class="bo_t_zi pos-r size14 colorDarkBlue">
							<span class="bo_t_zi_sa pos-a">个人资料</span>
							<span class="bo_t_zi_sb pos-a">资质上传</span>
							<span class="bo_t_zi_sc pos-a">借款信息</span>
							<span class="bo_t_zi_sd pos-a">完成</span>
						</div>
					</c:if>
					<c:if test="${borrow.borrowType == 2}">
						<div class="bo_t_quan">
							<span class="w60 tc inline_block w1140">
								<img src="<%=basePath %>theme/default/images/bo_1.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_1.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_2.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_3.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_4.png" />
							</span>
						</div>
						<div class="bo_t_zi pos-r size14 colorDarkBlue">
							<span class="bo_t_zi_sa_e pos-a">选择楼盘</span>
							<span class="bo_t_zi_sb_e pos-a">个人资料</span>
							<span class="bo_t_zi_sc_e pos-a">资质上传</span>
							<span class="bo_t_zi_sd_e pos-a">借款信息</span>
							<span class="bo_t_zi_se_e pos-a">完成</span>
						</div>
					</c:if>
					<div class="kong45 hui_l"></div>
				</div>
				<!--资料上传s-->
				<div class="bo_bd w1140 m_auto">
					<div class="kong45"></div>
						<ul class="ul size14 colorDarkBlue">
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									姓名
								</span>
								<span class="fr_">
									<input type="text" name="sname" id="sname" class="w220 pl10 fl mt10"  placeholder="姓名" required="true"  missingMessage="请输入姓名!" />
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									现居住址省份
								</span>
								<span class="fr_">
									<select name="nowProvince" id="nowProvince" required="true" onchange="borrow.selectNowProvince();" class="w220 fl mt10" missingMessage="请选择现居住址省份!" ></select>
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									直系亲属
								</span>
								<span class="fr_">
									<input type="text" name="immediateFamily" id="immediateFamily" class="w220 pl10 fl mt10" placeholder="直系亲属" required="true"  missingMessage="请输入直系亲属!"/>
								</span>
							</li>
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									性别
								</span>
								<span class="fr_ select_js">
									<i class="radio_s radio_xiu1 m5 yu_b"  val="1"><img src="<%=basePath %>theme/default/images/radio_x.png" class="block"></i>
									<i class="i_ select_c c2980b9">男</i>
									<i class="radio_s radio_xiu1 m5 yu_b ml15" val="2"></i><i class="i_ select_c">女</i>
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									现居住址市区
								</span>
								<span class="fr_">
									<select class="w220 fl mt10" name="nowCity" id="nowCity" required="true"  missingMessage="请选择现居住址市区!"></select>
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									联系方式
								</span>
								<span class="fr_">
									<input type="text" class="w220 pl10 fl mt10" name="immediateFamilyNo" id="immediateFamilyNo" placeholder="联系方式" required="true"  missingMessage="请输入联系方式!" />
								</span>
							</li>
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									婚姻状况
								</span>
								<span class="fr_">
									<ptpfm:select id="isMarriage" name="isMarriage" isdictory="true" dicKey="MARITAL_STATUS" optText="dictContName" optVal="dictContCode" defoption="--请选择--" class="w220 fl mt10" required="true"  missingMessage="请选择婚姻状况!"></ptpfm:select>
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									邮箱
								</span>
								<span class="fr_">
									<input type="text"  name="email" id="email" class="w220 pl10 fl mt10" placeholder="邮箱号码" />
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									手机号码
								</span>
								<span class="fr_ pos-r">
									<input type="text" name="phoneNoEncry" id="phoneNoEncry" disabled="disabled" class="w220 pl10 fl mt10" placeholder="手机号码" />
								</span>
							</li>
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									身份证号
								</span>
								<span class="fr_">
									<input type="text" name="identificationNo" id="identificationNo" class="w220 pl10 fl mt10" placeholder="身份证号" required="true"  missingMessage="请输入身份证号!"/>
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									微信
								</span>
								<span class="fr_">
									<input type="text" name="wechatNo" id="wechatNo" class="w220 pl10 fl mt10" placeholder="微信" />
								</span>
							</li>
							<li class="li">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									家庭地址
								</span>
								<span class="fr_">
									<input type="text"  name="homeAddress" id="homeAddress"  class="w220 pl10 fl mt10" placeholder="家庭地址" required="true"  missingMessage="请输入家庭地址!"/>
								</span>
							</li>
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									现居住详细地址
								</span>
								<span class="fr_">
									<input type="text"  name="nowAddress" id="nowAddress"  class="w220 pl10 fl mt10" placeholder="现居住详细地址" required="true"  missingMessage="请输入现居住详细地址!"/>
								</span>
							</li>
							<li class="li" >
							</li>
							<li class="li" >
							</li>
						</ul>
					<div class="kong50 hui_l"></div>
					
					<div class="">
						<p class="mt30 mb30">
							<c:if test="${borrow.borrowType == 2}">
								<span class="btn ml15" onclick="borrow.Last('${borrow.borrowType}',${borrow.borrowType==1?'1':'2'});">上一步</span>
							</c:if>
							<c:if test="${borrow.borrowType == 1}">
								<span class="btn_h">上一步</span>
							</c:if>
							<span class="btn ml15"  onclick="borrow.next('${borrow.borrowType}',${borrow.borrowType==1?'1':'2'});">下一步</span>
						</p>
					</div>
				</div>
				<div class="kong20"></div>
				<!-- 客户ID隐藏域 -->
				<input type="hidden" name="pid" id="curId" value="${borrow.customerId}">
				<input type="hidden" name="borrowId" id="borrowId" value="${borrow.pid}">
				<input type="hidden" id="sex" name="sex"/>
				<input type="hidden" id="age" name="age"/>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){ 
			borrow.initAddBorrow();
		})
	</script>
</html>