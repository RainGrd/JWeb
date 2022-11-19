<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/mywtborrow/borrow.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>e生财富</title>
	</head>
	<body>
		<!-- 个人基本资料 -->
		<div class="w1200 m_auto">
			<form action="" method="post" id="loanInfoForm" name="loanInfoForm">
				<div class="bo_title w1140 m_auto">
					<div class="kong50"></div>
					<div class="bo_t_quan">
						<span class="w60 tc inline_block w1140">
							<img src="<%=basePath %>theme/default/images/an_b.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
							<img src="<%=basePath %>theme/default/images/an_a.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
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
					<div class="kong45 hui_l"></div>
				</div>
				<!--资料上传s-->
				<div class="bo_bd w1140 m_auto">
					<div class="kong45"></div>
						<ul class="ul size14 colorDarkBlue">
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									楼盘地址  省
								</span>
								<span class="fr_">
									<select id="homesProvince" name="homesProvince" onchange="borrow.selectProvince();" class="w220 fl mt10" required="true"  missingMessage="请选择楼盘所在省!"></select> 
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									市
								</span>
								<span class="fr_">
									<select id="homesCity"  name="homesCity" onchange="borrow.selectCity();" class="w220 fl mt10" required="true"  missingMessage="请选择楼盘所在市!"></select> 
								</span>
							</li>
							<li class="li" >
								<span class="w125 fl_ inline_block ">
									区
								</span>
								<span class="fr_">
									<select id="homesArea"   name="homesArea" onchange="borrow.selectArea();" class="w220 fl mt10" required="true"  missingMessage="请选择楼盘所在区!"></select> 
								</span>
							</li>
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									楼盘
								</span>
								<span class="fr_ colorc">
									<select id="homesName"  name="homesName" onchange="borrow.selectHousesName(); "  class="w220 fl mt10" required="true"  missingMessage="请选择楼盘!"></select> 
								</span>
							</li>
							<li class="li">
							</li>
							<li class="li">
							</li>
							
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									户型
								</span>
								<span class="fr_">
									<select id="homeId" name="homeId"  class="w220 fl mt10" required="true"  missingMessage="请选择楼盘户型!"></select> 
								</span>
							</li>
							<li class="li">
							</li>
							<li class="li">
							</li>
							<li class="li" style="margin-left: 0;">
								<span class="w125 fl_ inline_block ">
									<i class="i_ colorc">*</i>
									房产总价
								</span>
								<span class="fr_">
									<input type="text" class="w220 pl10 fl mt10" name="homeTotal" value="${borrow.homeTotal}" id="homeTotal" placeholder="房产总价" required="true"  missingMessage="请输入房产总价!" />
								</span>
							</li>
							<li class="li">
							</li>
							<li class="li">
							</li>
						</ul>
					<div class="kong50 hui_l"></div>
					
					<div class="">
						<p class="mt30 mb30">
							<span class="btn_h">上一步</span>
							<span class="btn ml15"  onclick="borrow.next('${borrow.borrowType}',1);">下一步</span>
						</p>
					</div>
				</div>
				<div class="kong20"></div>
				<!-- 客户ID隐藏域 -->
				<input type="hidden" name="pid" id="borrowId" value="${borrow.pid}">
				<input type="hidden" name="homePid" id="homePid" value="${borrow.homeId}"/>	
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){ 
			borrow.initHouse();
		})
	</script>
</html>