<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/detail/actExpActDetail_list.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/actExpActDetail_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<form method="post" action=""  id="search" name="search" style="padding: 0px 0px;">
				<input id="activityId" name="activityId" type="hidden" value="${activityId}" />
				<input name="actName" type="hidden" value="${actName}" />
	     		<div style="margin:5px">
				 	<table>
				 		<tr>
				 			<td class="label_right">活动编号：</td>
				 			<td><div style="width: 200px;" id="actCode" >${actCode}</div></td>
				 			<td class="label_right">活动名称：</td>
				 			<td><div style="width: 200px;" id="actName" >${actName}</div></td>
				 			<td class="label_right">有效期：</td>
				 			<td><div style="width: 100px;" id="expNumber">${expNumber}</div></td>
				 		</tr>
				 		<tr>
				 			<td class="label_right">查询状态：</td>
				 			<td>
				 				<select class="easyui-combobox" name="useStatus" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >已使用</option>
									<option value=2 >未使用</option>
								</select>
				 			</td>
				 			<td class="label_right">是否过期：</td>
				 			<td>
				 				<select class="easyui-combobox" name="isExpired" panelHeight="auto" editable="false" >
									<option value=0  selected="selected" >--请选择--</option>
									<option value=1 >未过期</option>
									<option value=2 >已过期</option>
								</select>
				 			</td>
				 			<td colspan="2">
				 				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="actExpActDetailList.searchDataDetail()">查询</a>
				 			</td>
				 		</tr>
				 	</table>				 		
	       		</div> 
	 		</form>
	 		<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" onclick="actExpActDetailList.toDownloadPage()">导出excel</a>
			</div>
		</div>
		
		<!-- 查看明细 -->
		<div class="showDataListWrap" id="actExpActDetail_dlg">
			<table id="actExpActDetailGrid_det" >
			</table>
		</div>
	</div>	
</body>