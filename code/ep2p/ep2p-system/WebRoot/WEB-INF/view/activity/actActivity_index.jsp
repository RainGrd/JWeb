<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/actActivity_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/actActivity_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
		<input type="hidden" id="isTaskListOpen" value="${status}">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable percent90">
						<tr>
							<td class="align_right">活动编号：</td>
							<td>
								<input class="easyui-textbox" name="actCode"/>
							</td>
							<td class="align_right">活动名称：</td>
							<td>
								<input class="easyui-textbox" name="actName" />
							</td>
						</tr>
						<tr>
							<td class="align_right">适用开始日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="beginApplyStartData"  editable="false" />~
								<input class="easyui-datebox" name="endApplyStartData" editable="false" /> 
								</div>
							</td>
							<td class="align_right">适用结束日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="beginApplyFinishData" editable="false" />~
								<input class="easyui-datebox" name="endApplyFinishData"  editable="false" /> 
								</div>
							</td>
						</tr>
						<tr>
							<td class="align_right">活动标签：</td>
							<td >
								<input class="easyui-textbox" name="actTag"/>
							</td>
							<td class="align_right">活动类型：</td>
							<td>
								<select class="easyui-combobox" name="activityType" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >赠送VIP</option>
									<option value=102 >抢红包</option>
									<option value=101 >送红包</option>
									<option value=3 >体验金</option>
									<option value=4 >投资奖励</option>
									<option value=7 >VIP生日特权</option>
									<option value=8 >财富合伙人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="align_right">进行状态：</td>
							<td>
								<select class="easyui-combobox" id="status" name="status" panelHeight="auto" editable="false">
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >未开始</option>
									<option value=2 >进行中</option>
									<option value=3 >过期</option>
								</select>
							</td>
							<td colspan="2">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="actActivity.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" iconCls="icon-clear" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
		</div>
		
		<div class="showDataListWrap">
			<table id="actActivityGrid" >
			</table>
		</div>
</body>