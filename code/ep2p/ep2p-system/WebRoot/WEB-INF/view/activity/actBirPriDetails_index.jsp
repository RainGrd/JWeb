<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/actBirPriDetails_index.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/actBirPriDetails_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="actBirPriItemActivityRelController/selectBirPriItem.shtml" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable">
						<tr>
							<td style="width: 120px;" class="align_right">编号：</td>
							<td style="width: 220px;">
								<input class="easyui-textbox" name="actCode"/>
								<input type="hidden" value="${activityId}" id="activityId" name="activityId" />
							</td>
							<td style="width: 120px;"  class="align_right">名称：</td>
							<td  colspan="2" style="width: 220px;">
								<input class="easyui-textbox" name="actName" />
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" class="align_right">标签：</td>
							<td >
								<input class="easyui-textbox" name="actTag"/>
							</td>
							<td class="align_right">进行状态：</td>
							<td>
								<select class="easyui-combobox" name="status" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >未开始</option>
									<option value=2 >进行中</option>
									<option value=3 >已过期</option>
								</select>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="actBirPriDetails.searchData()">查询</a>
								<a href="#" onclick="javascript:$('#searcm').form('reset');" class="easyui-linkbutton" style="width: 60px;">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
		</div>
		
		<div class="showDataListWrap" id="actBirPriDetailsGrid_dlg">
			<table id="actBirPriDetailsGrid" >
			</table>
		</div>
		 
</body>