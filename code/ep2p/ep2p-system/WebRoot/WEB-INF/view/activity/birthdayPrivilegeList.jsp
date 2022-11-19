<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script> 
<script type="text/javascript" src="${basePath}resources/js/activity/experience/birPriList.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/birPri_Model.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<!-- 查询条件 -->
			<form action="" method="post" id="searcm" name="searcm">
				<div style="padding: 5px">
					<table class="beforeloanTable formTable">
						<tr>
							<td style="width: 120px;" class="align_right">生日特权编号：</td>
							<td style="width: 220px;">
								<input class="easyui-textbox" name="actCode"/>
							</td>
							<td style="width: 120px;"  class="align_right">生日特权名称：</td>
							<td style="width: 220px;">
								<input class="easyui-textbox" name="actName" />
							</td>
							<td class="align_right">适用日期：</td>
							<td>
								<div class="rangDate">
								<input class="easyui-datebox" name="startDate" editable="false" />~
								<input class="easyui-datebox" name="endDate" editable="false" /> 
								</div>
							</td>
						</tr> 
						<tr>
							<td style="width: 120px;" class="align_right">标签：</td>
							<td>
								<input class="easyui-textbox" name="actTag"/>
							</td>
							<td class="align_right">状态：</td>
							<td>
								<select class="easyui-combobox" name="status" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >启用</option>
									<option value=2 >禁用</option>
								</select>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="birPriList.searchData()">查询</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="birPriList.openAdd()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="birPriList.remove()">删除</a>
			</div>
		</div>
		
		<div class="showDataListWrap">
			<table id="birPriGrid" >
			</table>
		</div>
</body>