<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/pubRedpacket_song_add.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>

<body class="easyui-layout">
	
	<div class="easyui-panel" data-options="fit:true" style="width:auto;padding-top:30px;">
		<form id="baseInfo" name="baseInfo" action="<%=basePath%>pubRedpacketController/save.shtml" method="POST">
			<input type="hidden" name="pid" id="pid" value="${pid}" />
			<input type="hidden" name="redpacketType" id="redpacketType" value="1" />
			<table class="cus_table formTable percent68">
				<tr>
					<td class="align_right"><label style="color: red;">*</label>红包编号：</td>
					<td><input class="text_style easyui-validatebox"  name="actCode" readonly="readonly" /></td>
					<td class="align_right">红包标签：</td>
					<td><input class="text_style easyui-validatebox" name="actTag" /></td>
				</tr>
				<tr>
					<td class="align_right"><label style="color: red;">*</label>红包名称：</td>
					<td><input class="text_style easyui-validatebox" name="repName" data-options="required:true" /></td>
					<td class="align_right"><label style="color: red;">*</label>红包金额：</td>
					<td><input class="text_style easyui-numberbox" name="repAmount" data-options="required:true" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="align_right">是否VIP生日特权：</td>
					<td colspan="3">
						<input type="checkbox" name="checkboxPrivilege" /> 是
						<input type="hidden" name="isBirPrivilege" id="isBirPrivilege" />
					</td>
				</tr>
				<tr>
					<td class="align_right">适用开始日期：</td>
					<td><input class="easyui-datetimebox" name="effTime" id="effTime" data-options="editable:false" style="width: 200px;" /></td>
					<td class="align_right">适用结束日期：</td>
					<td><input class="easyui-datetimebox" name="expTime" id="expTime" data-options="editable:false" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="align_right vertical_align_top">活动描述：</td>
					<td colspan="3"><input class="easyui-textbox" name="redDesc" data-options="multiline:true,validType:'length[0,120]'" style="width: 634px;height: 80px;" /></td>
				</tr>
				<tr>
					<td class="align_right">选择提醒短信：</td>
					<td colspan="3">
						<input class="text_style easyui-validatebox" id="tempCode" name="tempCode" readonly="readonly" style="width: 100px;" />
						<input class="text_style easyui-validatebox" id="smsContent" name="smsContent" readonly="readonly" style="width: 400px;" />
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="commonActivity.openSmsTemplates()">选择</a>
						<input type="hidden" name="smsId" id="smsId" />
					</td>
				</tr>
				<tr>
					<td class="align_right">短信提醒状态：</td>
					<td>
						<input type="checkbox" name="checkboxWarn" /> 启用
						<input type="hidden" name="isSmsWarn" id="isSmsWarn" />
					</td>
					<td class="align_right">活动状态：</td>
					<td>
						<input type="checkbox" name="checkboxStatus" /> 启用
						<input type="hidden" name="status" id="status" />
					</td>
				</tr>
				<tr>
					<td class="align_right vertical_align_top" >活动参与条件：</td>
					<td colspan="3">
						<input type="hidden" name="condIds" id="condIds" />
						<%-- 条件选择 --%>
						<%@ include file="common/pubCondition.jsp" %>	
					</td>
				</tr>
				<tr>
					<%-- 保存or编辑按钮 --%>
					<td colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="pubRedpacket_song_add.save()">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="pubRedpacket_song_add.revert()">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<%-- 信息管理窗口 --%>
	<%@ include file="common/smsTemplates.jsp" %>	
	
</body>