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
		<form action="" method="post" id="fileInfoForm" name="fileInfoForm">
			<div class="w1200 m_auto">
				<div class="bo_title w1140 m_auto">
					<div class="kong50"></div>
					<c:if test="${borrow.borrowType eq 1}">
						<div class="bo_t_quan">
							<span class="w60 tc inline_block w1140">
								<img src="<%=basePath %>theme/default/images/bo_1.png" /><i class="i_ bo_title_line ml10 mr10"></i>
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
					<c:if test="${borrow.borrowType eq 2}">
						<div class="bo_t_quan">
							<span class="w60 tc inline_block w1140">
								<img src="<%=basePath %>theme/default/images/bo_1.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_1.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
								<img src="<%=basePath %>theme/default/images/bo_5.png" /><i class="i_ bo_title_line_e ml10 mr10"></i>
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
				<div class=" w1140 m_auto">
					<div class="bo_zizhi_list">
						<div class="kong25"></div>
						<div class="kong100">
							<span class="fl_ w110 inline_block tc hui_r">
								<p class="kong20"></p>
								<img src="<%=basePath %>theme/default/images/po_13.png" class="block m_auto" />
								<p class="size14 b0b0b0 lh30">身份证明</p>
							</span>
							<span class="fl_ inline_block ml30" id="fileDiv1_">
								<!--我是添加上传按钮s-->
								<i class="i_ bo_zizhi_list_tj" onclick="borrow.openFileUploadDiv(1)"></i>
								<!--我是添加上传按钮e-->
							</span>
						</div>
					</div>
					<div class="bo_zizhi_list">
						<div class="kong25"></div>
						<div class="kong100">
							<span class="fl_ w110 inline_block tc hui_r">
								<p class="kong20"></p>
								<img src="<%=basePath %>theme/default/images/po_12.png" class="block m_auto" />
								<p class="size14 b0b0b0 lh30">个人征信报告</p>
							</span>
							<span class="fl_ inline_block ml30" id="fileDiv2_">
								<!--我是添加上传按钮s-->
								<i class="i_ bo_zizhi_list_tj" onclick="borrow.openFileUploadDiv(2)"></i>
								<!--我是添加上传按钮e-->
							</span>
						</div>
					</div>
					<div class="bo_zizhi_list">
						<div class="kong25"></div>
						<div class="kong100">
							<span class="fl_ w110 inline_block tc hui_r">
								<p class="kong20"></p>
								<img src="<%=basePath %>theme/default/images/po_11.png" class="block m_auto" />
								<p class="size14 b0b0b0 lh30">收入证明</p>
							</span>
							<span class="fl_ inline_block ml30" id="fileDiv3_">
								<!--我是添加上传按钮s-->
								<i class="i_ bo_zizhi_list_tj" onclick="borrow.openFileUploadDiv(3)"></i>
								<!--我是添加上传按钮e-->
							</span>
						</div>
					</div>
					<div class="bo_zizhi_list">
						<div class="kong25"></div>
						<div class="kong100">
							<span class="fl_ w110 inline_block tc hui_r">
								<p class="kong20"></p>
								<img src="<%=basePath %>theme/default/images/po_10.png" class="block m_auto" />
								<p class="size14 b0b0b0 lh30">近半年银行流水</p>
							</span>
							<span class="fl_ inline_block ml30" id="fileDiv4_">
								<!--我是添加上传按钮s-->
								<i class="i_ bo_zizhi_list_tj" onclick="borrow.openFileUploadDiv(4)"></i>
								<!--我是添加上传按钮e-->
							</span>
						</div>
					</div>
					<div class="bo_zizhi_list">
						<div class="kong25"></div>
						<div class="kong100">
							<span class="fl_ w110 inline_block tc hui_r">
								<p class="kong20"></p>
								<img src="<%=basePath %>theme/default/images/bo_9.png" class="block m_auto" />
								<p class="size14 b0b0b0 lh30">社保查询单</p>
							</span>
							<span class="fl_ inline_block ml30" id="fileDiv5_">
								<!--我是添加上传按钮s-->
								<i class="i_ bo_zizhi_list_tj" onclick="borrow.openFileUploadDiv(5)"></i>
								<!--我是添加上传按钮e-->
							</span>
						</div>
					</div>
					<div class="bo_zizhi_list">
						<div class="kong25"></div>
						<div class="kong100">
							<span class="fl_ w110 inline_block tc hui_r">
								<p class="kong20"></p>
								<img src="<%=basePath %>theme/default/images/bo_8.png" class="block m_auto" />
								<p class="size14 b0b0b0 lh30">房产证材料</p>
							</span>
							<span class="fl_ inline_block ml30" id="fileDiv6_" >
									<i class="i_ bo_zizhi_list_img pos-r">
										<img src="<%=basePath %>theme/default/images/bo_11.png" />
										<i class="i_ bo_zizhi_list_img_sc pos-a"><img src="<%=basePath %>theme/default/images/bo_7.png" /></i>
									</i>
								<!--我是添加上传按钮s-->
								<i class="i_ bo_zizhi_list_tj"  onclick="borrow.openFileUploadDiv(6)"></i>
								<!--我是添加上传按钮e-->
							</span>
						</div>
					</div>
					
					<div class="">
						<p class="mt30 mb30">
							<span class="btn ml15" onclick="borrow.Last('${borrow.borrowType}',${borrow.borrowType==1?'2':'3'});">上一步</span>
							<span class="btn ml15" onclick="borrow.next('${borrow.borrowType}',${borrow.borrowType==1?'2':'3'});">下一步</span>
						</p>
					</div>
				</div>
				<div class="kong50"></div>
				<!--资料上传e-->
			</div>
			<!-- 借款类型,借款ID隐藏域 -->
			<input type="hidden" name="borrowType" id="borrowType" value="${borrow.borrowType}"/>	
			<input type="hidden" name="pid" id="pid" value="${borrow.pid}"/>	
		</form>
						
		
		<!-- 文件上传 -->
		<div id="uploadFile" title="文件上传" style="display:none">
			<form action="${basePath}business/borrowController/uploadFile.shtml" id="fileUploadForm" method="post" enctype="multipart/form-data">
				<table height="120">				
					<tr>
						<td ><font color="red">*</font>选择文件：</td>
						<td>
							<input missingMessage="请选择文件!" type="text" id="txt2" name="txt2"  class="w230" />
					        <input style="width:70px;" type="file" name="borrowFile" id="borrowFile" onchange="txt2.value=this.value;if(this.value!=''){borrow.uploadFile();}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="left" >
							<span class="btn ml15" onclick="borrow.uploadFile()">上传</span>
						</td>
					</tr>
				</table>
				<input type="hidden" id="borrowId" name="borrowId" value="${borrow.pid}">
				<input type="hidden" id="borrowType" name="borrowType" value="${borrow.borrowType}">
				<input type="hidden" id="borFileRelType" name="borFileRelType">
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){ 
			borrow.initQualificationMaterial();
		})
	</script>
</html>