<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${basePath}theme/js/mywtborrow/borrow.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>e生财富</title>
	</head>
	<body>
		<!--借款信息s-->
		<form action="" method="post" id="loanInfoForm" name="loanInfoForm">
			<div class="w1200 m_auto">
				<div class="bo_title w1140 m_auto">
					<div class="kong50"></div>
					<c:if test="${borrow.borrowType == 1}">
						<div class="bo_t_quan">
							<span class="w60 tc inline_block w1140">
								<img src="<%=basePath %>theme/default/images/bo_6.png" /><i class="i_ bo_title_line ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_5.png" /><i class="i_ bo_title_line ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_13.png" /><i class="i_ bo_title_line ml10 mr10"></i>
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
								<img src="<%=basePath %>theme/default/images/bo_6.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_6.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_5.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_13.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
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
				
				<!-- e首付才展示房产描述 -->
				<c:if test="${borrow.borrowType == 1}">
					<div class="kong50"></div>
					<div class="size14 colorDarkBlue oh">
						<span class="inline_block w130 fl_">
							<i class="i_ colorc">*</i>房产描述
						</span>
						<span class="fl_">
							<textarea class="bo_f_m cd5d5d5"  name="homeDesc" id="homeDesc" placeholder="房产区域描述100文字以内  " required="true"  missingMessage="请输入房产描述!">房产区域描述100文字以内
							</textarea>
						</span>
					</div>
				</c:if>
				
				<div class="kong15"></div>
				<div class="size14 colorDarkBlue oh">
					<span class="inline_block w130 fl_">
						<i class="i_ colorc">*</i>借款金额
					</span>
					<span class="fl_">
						<input type="text" placeholder="借款金额" name="borrowMoney" id="borrowMoney"  class="pl10" required="true"  missingMessage="请输入借款金额!"/> &nbsp;元
					</span>
				</div>
				<div class="kong15"></div>
				<div class="size14 colorDarkBlue oh">
					<span class="inline_block w130 fl_">
						<i class="i_ colorc">*</i>借款期限
					</span>
					<span class="fl_">
						<ptpfm:select id="borDeadline" name="borDeadline" isdictory="true" dicKey="BORROW_TIME" optText="dictContName" optVal="dictContCode" defoption="--请选择--" class="mr10 w160 colorDarkBlue" required="true"  missingMessage="请选择借款期限!"></ptpfm:select>
					</span>
				</div>
				<div class="kong15"></div>
				<div class="size14 colorDarkBlue oh">
					<span class="inline_block w130 fl_">
						<i class="i_ colorc">*</i>借款用途
					</span>
					<span class="fl_">
						<textarea class="bo_y_m cd5d5d5" name="borrowUse" id="borrowUse"  placeholder="  " required="true"  missingMessage="请输入借款用途!">借款的用途录入，255个字以内  
							
						</textarea>
					</span>
				</div>
				<div class="kong15"></div>
				<div class="size14 colorDarkBlue oh">
					<span class="inline_block w130 fl_">
						<i class="i_ colorc">*</i>还款来源
					</span>
					<span class="fl_">
						<textarea class="bo_h_m cd5d5d5" name="payment" id="payment" placeholder="  " required="true"  missingMessage="请输入还款来源!">1、资金去向还款来源（大笔，大于借款金额） ；2、预期收入来源（大笔，大于借款金额） 500字以内
						</textarea>
					</span>
				</div>
				<div class="kong15"></div>
				<div class="size14 colorDarkBlue oh">
					<span class="inline_block w130 fl_">
						<i class="i_ colorc">*</i>其它
					</span>
					<span class="fl_">
						<textarea class="bo_h_m cd5d5d5" name="other" id="other"  placeholder="  " required="true"  missingMessage="请输入其它内容!">其他资产补充500字以内  
						</textarea>
					</span>
				</div>
				<div class="">
					<p class="mt30 mb30">
						<span class="btn ml15" onclick="borrow.Last('${borrow.borrowType}',${borrow.borrowType==1?'3':'4'});">上一步</span>
						<span class="btn ml15" onclick="borrow.next('${borrow.borrowType}',${borrow.borrowType==1?'3':'4'});">完成</span>
					</p>
				</div>
				<div class="kong50"></div>
			</div>
			<!-- 借款类型,借款ID隐藏域 -->
			<input type="hidden" name="borrowType" id="borrowType" value="${borrow.borrowType}"/>	
			<input type="hidden" name="pid" id="pid" value="${borrow.pid}"/>	
			<input type="hidden" name="approveId" id="approveId" value="${borrow.approveId}"/>	
			<input type="hidden" name="approveStatus" id="approveStatus" value="${borrow.approveStatus}"/>	
			<input type="hidden" name="surplusMoney" id="surplusMoney" />
		</form>
		<!--资料上传e-->
	</body>
	<script type="text/javascript">
		$(function(){ 
			borrow.initLoadInfo();
		})
	</script>
</html>