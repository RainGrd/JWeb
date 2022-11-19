<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">

		<div class="loanExtensionListDiv" style="height:100%">
			<table id="grid" class="easyui-datagrid"
				style="height: auto; width: auto;"
				data-options="
			     url:'${basePath}scheduleJobController/selectLogAllPage.shtml?jobid=${jobid}',
		         method: 'POST',
				 pagination: true,
				 rownumbers:true,
				 sortOrder:'asc',
				 fitColumns: true,
				 onLoadSuccess:function(data){
						clearSelectRows('#grid');
				 }
			    ">
				<!-- 表头 -->
				<thead>
					<tr>
						<th data-options="field:'content',width:250">内容</th>
						<th data-options="field:'execStatus',width:150">执行状态</th>
						<th data-options="field:'createTime',width:150">创建时间</th>
					</tr>
				</thead>
			</table>
		</div>
</body>