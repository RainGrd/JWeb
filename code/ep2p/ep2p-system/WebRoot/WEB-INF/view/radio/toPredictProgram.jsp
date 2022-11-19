<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript" src="${basePath}resources/js/radio/programList.js"></script>
	<div data-options="region:'center',border:false">
		<div id="toolbar" class="easyui-panel">
				<form action=".shtml" method="post" id="baseInfo"
					name="baseInfo">
					<input type="hidden" id="pid" name="pid" value="${pid}">
					<div style="padding: 5px">
						<table class="userTable percent100 formTable">
						   <tr>
							  <td>
							    <input class="easyui-datetimebox" id="predictTimes" name="predictTime" value="${publishTime}"/>
							  </td>						   
						   </tr>
						</table>
				</div>
				</form>
		</div>
	</div>
</body>