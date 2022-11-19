<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/pubActivityArea_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/pubActivityArea_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right">编号：</td>
							<td>
								<input class="easyui-textbox" name="activityCode"/>
							</td>
							<td class="align_right">活动开始日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="beginStartData" editable="false" />~
								<input class="easyui-datebox" name="endStartData" editable="false" /> 
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="align_right">名称：</td>
							<td>
								<input class="easyui-textbox" name="activityName" />
							</td>
							<td class="align_right">活动结束日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="beginFinishData" editable="false" />~
								<input class="easyui-datebox" name="endFinishData" editable="false" />
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="align_right">类型：</td>
							<td>
								<select class="easyui-combobox" name="activityType" panelHeight="auto" editable="false" >
									<option value=-1  selected="selected" >--请选择--</option>
									<option value=1 >抢红包</option>
									<option value=2 >加息卷</option>
									<option value=99 >其他</option>
								</select>
							</td>
							<td class="align_right">展示状态：</td>
							<td>
								<select class="easyui-combobox" name="isShows" panelHeight="auto" editable="false" >
									<option value=-1  selected="selected" >--请选择--</option>
									<option value=1 >展示</option>
									<option value=2 >不展示</option>
								</select>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="pubActivityArea.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="pubActivityArea.openAdd()">新增其他活动配图</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="pubActivityAreaGrid" >
			</table>
		</div>
		
		<%-- 活动专区新增窗口 --%>
		<div class="easyui-dialog" fitColumns="true"  id="pubActivityArea_dlg"
			style="width:750px;padding: 10px;height: 450px;" data-options="modal:true" closed="true" >
			<form id="baseInfo" name="baseInfo" action="<%=basePath%>pubActivityAreaController/save.shtml" enctype="multipart/form-data" method="POST">
				<input type="hidden" name="pid" id="pid" />
				<input type="hidden" name="isShows" value="1" />
				<input type="hidden" name="activityType" id="activityType" />
				<input type="hidden" name="version" />
				<table class="cus_table beforeloanTable" style="line-height: 40px">
					<tr>
						<td class="align_right"><label style="color: red;">*</label>编号：</td>
						<td colspan="3"><input class="text_style easyui-validatebox" name="activityCode"/></td>
					</tr>
					<tr>
						<td class="align_right"><label style="color: red;">*</label>名称：</td>
						<td colspan="3"><input class="text_style easyui-validatebox" name="activityName" style="width: 565px;" /></td>
					</tr>
					<tr>
						<td class="align_right"><label style="color: red;">*</label>开始日期：</td>
						<td><input class="easyui-datetimebox" name="activityStartDate" id="activityStartDate" data-options="editable:false" style="width: 200px;" /></td>
						<td class="align_right"><label style="color: red;">*</label>结束日期：</td>
						<td><input class="easyui-datetimebox" name="activityEndDate" id="activityEndDate" data-options="editable:false" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td class="align_right">选择图片：</td>
						<td colspan="3">
							<input class="text_style easyui-validatebox" disabled="disabled" missingMessage="请选择图片!" type="text" name="activityImage" id="activityImageFile" style="width:480px;"/>
					        <input style="width:70px;" type="file" name="activityFile" id="activityFile" onchange="activityImageFile.value=this.value"/>
					        <input type="hidden" id="activityImage" name="activityImage" />
						</td>
					</tr>
					<tr id="activityUrl">
						<td class="align_right">配置URL：</td>
						<td colspan="3"><input class="text_style easyui-validatebox" name="activityUrl" style="width: 565px;" /></td>
					</tr>
					<tr>
						<td class="align_right vertical_align_top">描述：</td>
						<td colspan="3"><input class="easyui-textbox" name="activityDesc" data-options="multiline:true,validType:'length[0,120]'" style="width: 565px;height: 80px;" /></td>
					</tr>
					<tr>
						<%-- 保存or编辑按钮 --%>
						<td colspan="4" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="pubActivityArea.save()">保存</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#pubActivityArea_dlg').dialog('close');">返回</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
</body>