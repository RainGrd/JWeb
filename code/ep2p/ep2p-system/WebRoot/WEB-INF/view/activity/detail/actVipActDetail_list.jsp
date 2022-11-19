<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/detail/actVipActDetail_list.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/actVipActDetail_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
			<form method="post" action=""  id="actVipActDetailForm" name="actVipActDetailForm" style="padding: 0px 0px;">
				<input id="activityId" name="activityId" type="hidden" value="${activityId}" />
				<input name="actName" type="hidden" value="${actName}" />
	     		<div style="margin:5px">
				 	<table>
				 		<tr>
				 			<td class="label_right">活动编号：</td>
				 			<td><div style="width: 200px;" id="actCode" >${actCode}</div></td>
				 			<td class="label_right">活动名称：</td>
				 			<td><div style="width: 200px;" id="actName" >${actName}</div></td>
				 		</tr>
				 	</table>				 		
	       		</div> 
	 		</form>
	 		<!-- 操作按钮 -->
			<div style="padding-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" onclick="actVipActDetailList.toDownloadPage()">导出excel</a>
			</div>
		</div>
		
		<!-- 查看明细 -->
		<div  class="showDataListWrap">
			<table id="actVipActDetailGrid_det" >
			</table>
		</div>
	</div>	
</body>