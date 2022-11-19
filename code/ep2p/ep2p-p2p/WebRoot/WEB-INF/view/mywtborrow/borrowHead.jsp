<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>e生财富</title>
	</head>
	<body>
		<div class="w1200 m_auto">
			<div class="bo_title w1140 m_auto">
				<div class="kong50"></div>
				<c:if test="${borrow.borrowType == 1}">
					<div class="bo_t_quan">
						<span class="w60 tc inline_block w1140">
							<img src="<%=basePath %>theme/default/images/bo_6.png" /><i class="i_ bo_title_line ml10 mr10"></i>
							<img src="<%=basePath %>theme/default/images/bo_5.png" /><i class="i_ bo_title_line ml10 mr10"></i>
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
							<img src="<%=basePath %>theme/default/images/bo_6.png" /><i class="i_ bo_title_line ml10 mr10"></i>
							<img src="<%=basePath %>theme/default/images/bo_6.png" /><i class="i_ bo_title_line ml10 mr10"></i>
							<img src="<%=basePath %>theme/default/images/bo_5.png" /><i class="i_ bo_title_line ml10 mr10"></i>
							<img src="<%=basePath %>theme/default/images/bo_3.png" /><i class="i_ bo_title_line ml10 mr10"></i>
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
			<div class="kong50"></div>
		</div>
	</body>
</html>