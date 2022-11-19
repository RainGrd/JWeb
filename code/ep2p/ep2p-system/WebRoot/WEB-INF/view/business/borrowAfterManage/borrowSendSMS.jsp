<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript" src="<%=basePath%>resources/js/business/borrowAfterManage/borrowSendSMSHandle.js"></script>
	
	<div data-options="region:'center',border:false">
		<form action="" id="borrowAft_sendSMSForm" name="sendSMSForm" method="post">
			<table class="formTable">
				<tr>
					<td class="align_right"><span class="red">*</span>短信编号：</td>
					<td>
						<input type="hidden" id="borpid" name="borpid" value="${pids}">
						<input class="easyui-textbox" name="smsCode" id="smsCode" required="true" missingMessage="请选择短信编号!"/>
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="borrowAftMSendSMS.toChoseSMSPage();">选择短信模板</a>
					</td>
				</tr>
				<tr>
					<td class="align_right">短信内容：</td>
					<td>
						<textarea name="smsContext" id="smsContext" class="percent70"></textarea>
					</td>
				</tr>
				<tr>
					<td class="align_right">推送平台：</td>
					<td>
						<div class="check_box">
							<label class="fontNormal"><input type="checkbox" name="pushTypes" value="1"/>短信</label>
							<label class="fontNormal"><input type="checkbox" name="pushTypes" value="2" />微信</label>
							<label class="fontNormal"><input type="checkbox" name="pushTypes" value="3"/>APP</label>
							<label class="fontNormal"><input type="checkbox" name="pushTypes" value="4"/>系统消息</label>
						</div>
					</td>
				</tr>				
			</table>
		</form>
	</div>
</body>