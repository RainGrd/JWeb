<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/sysVipinfo_add.js" charset="utf-8"></script>

<body class="easyui-layout">
	
	<div class="easyui-panel" data-options="fit:true" style="width:auto;padding-top:30px;">
		<form id="baseInfo" name="baseInfo" action="<%=basePath%>sysVipinfoController/save.shtml" method="POST">
			<input type="hidden" name="pid" id="pid" value="${pid}" />
			<table class="cus_table formTable percent68">
				<tr>
					<td class="align_right"><label style="color: red;">*</label>VIP编号：</td>
					<td><input class="text_style easyui-validatebox"  name="vipCode" readonly="readonly" /></td>
					<td class="align_right"><label style="color: red;">*</label>VIP级别：</td>
					<td><input class="text_style easyui-validatebox" name="vipLevel" /></td>
				</tr>
				<tr>
					<td class="align_right"><label style="color: red;">*</label>VIP名称：</td>
					<td><input class="text_style easyui-validatebox" name="vipName" data-options="required:true" /></td>
					<td class="align_right"><label style="color: red;">*</label>图标样式：</td>
					<td><input class="text_style easyui-validatebox" name="vipIco" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="align_right">适用开始日期：</td>
					<td><input class="easyui-datetimebox" name="startTime" id="startTime" data-options="editable:false" style="width: 200px;" /></td>
					<td class="align_right">适用结束日期：</td>
					<td><input class="easyui-datetimebox" name="endTime" id="endTime" data-options="editable:false" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td class="align_right"><label style="color: red;">*</label>利息管理费率：</td>
					<td><input class="text_style easyui-numberbox" name="discount" data-options="required:true" style="width: 50px;" />%</td>
					<td class="align_right"><label style="color: red;">*</label>VIP时长(天)：</td>
					<td><input class="text_style easyui-numberbox" name="validTime" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="align_right">活动状态：</td>
					<td colspan="3">
						<input type="radio" name="checkboxStatus" value="1" checked="checked" /> 启用
						<input type="radio" name="checkboxStatus" value="2" /> 禁用
						<input type="hidden" name="status" id="status" />
					</td>
				</tr>
				<tr>
					<td class="align_right vertical_align_top">备注：</td>
					<td colspan="3"><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,120]'" style="width: 634px;height: 80px;" /></td>
				</tr>
				<tr>
					<td class="align_right vertical_align_top" >VIP获取条件：</td>
					<td colspan="3">
						<%-- 左边可选条件列表 --%>
						<div id="zuo" style="width: 200px;float: left;margin-left: 5px;">
							<span style="padding-left: 20px;color: red;">可选条件:</span>
							<select id="zuo_condition" style="width: 200px;margin-top: 10px;height:250px; " size="14" ondblclick="sysVipinfo_add.leftDoubleClick()">
							</select>
						</div>
						
						<div class="activity_condition">
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="sysVipinfo_add.leftToRight()" >></a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="sysVipinfo_add.rightToLeft()" ><</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="sysVipinfo_add.allLeftToRight()" >>></a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="sysVipinfo_add.allRightToLeft()" ><<</a>
						</div>
						
						<%-- 右边已选条件列表 --%>
						<div id="you" style="width: 200px;float: left">
							<span style="padding-left: 20px;color: red;">已选条件:</span>
							<select id="you_condition" style="width: 200px;margin-top: 10px;height:250px; " size="14" ondblclick="sysVipinfo_add.rightDoubleClick()">
							</select>
						</div>
						<input type="hidden" name="condIds" id="condIds" />
					</td>
				</tr>
				<tr>
					<%-- 保存or编辑按钮 --%>
					<td colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="sysVipinfo_add.save()">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="sysVipinfo_add.revert()">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>