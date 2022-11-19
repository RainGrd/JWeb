<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/activity/commonActivity.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/experience/detail/actInvAwaActDetail_list.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath}resources/js/activity/model/actInvAwaActDetail_Model.js" charset="utf-8"></script>

<body class="easyui-layout">
	<div data-options="region:'center',border:false">  
		<!-- 展示信息 -->
		<div id="toolbar_actInvAwaActDetail" class="none">
			<form method="post" action=""  id="searchFromactInvAwaActDetail" name="searchFromactInvAwaActDetail" style="padding: 0px 0px;">
				<input id="activityId" type="hidden" value="${activityId }"/>
	     		<div style="margin:5px">
				 	<table class="beforeloanTable formTable">
				 		<tr>
				 			<td class="label_right">编号：</td>
				 			<td><div style="width: 150px;" id="actCode">${actCode}</div></td>
				 			<td class="label_right">名称：</td>
				 			<td><div style="width: 150px;" id="actName">${actName}</div></td> 
				 		</tr> 
				 	</table>				 		
	       		</div> 
	 		</form>
	 		<!-- 操作按钮 -->
			<div style="padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" onclick="actInvWealthCoopDetail_list.toDownloadPage()">导出excel</a>
			</div>
		</div>
		<div class="showDataListWrap" id="actInvAwaActDetail_dlg">
			<table id="actInvAwaActDetailGrid_det" >
			</table>
		</div>
	</div>
</body>