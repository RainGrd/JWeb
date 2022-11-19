<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%-- 账户头像 --%>
<div class="sidebar_b borrow_div fl">
	<!--  修改头像 s-->
	<div class="ms m_auto">
		<div class="ge_ye_tilte">
			<span class="ge_ye_tilte_nav  ms_t_se ">修改头像</span>
		</div>
	</div>

	<div class="w580 m_auto">
		<!--我是空间s-->
		<p class="kong50"></p>
		<div class="tc w400 oh m_auto">
			<img id="avatarimage" src="<%=basePath %>theme/default/images/cont_vip_i.png" >		
		</div>
		<p class="kong50"></p>
		<p class="tc">
			请选择头像图片：<input type="file" id="avatarfile" name="avatarfile" onchange="accountAvatar.fileOnchangeImage();">
		</p>
		<p class="kong20"></p>
		<p class="tc">
			<span class="btn_samll" onclick="accountAvatar.saveCutImage();">上传</span>
		</p>
		
		<!-- 裁剪图片信息数据 -->
		<div id="cutimage_info">
			<form id="cutimage_info_form" action="" >
				<input type="hidden" name="imgtop" id="imgtop" value="0">
				<input type="hidden" name="imgleft" id="imgleft" value="0">
				<input type="hidden" name="imgwidth" id="imgwidth" value="0">
				<input type="hidden" name="imgheight" id="imgheight" value="0">
			</form>
		</div>
	</div>

	<!-- 修改头像 e-->
</div>

<%--  脚本区域  --%>
<script type="text/javascript" src="<%=basePath%>resources/plugins/Jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=basePath%>theme/js/personcenter/accountinfo/accountAvatar.js"></script>