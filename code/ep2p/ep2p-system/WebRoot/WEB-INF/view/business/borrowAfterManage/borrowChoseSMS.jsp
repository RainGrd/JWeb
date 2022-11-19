<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript" src="<%=basePath%>resources/js/business/model/borrowChoseSMS_Model.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/business/borrowAfterManage/borrowChoseSMSHandle.js"></script>
	
	<div data-options="region:'center',border:false">
		<div id="borrowcsms_toolbar" class="easyui-panel" style="border-bottom: 0;">
			<div>
				<!-- 查询条件 -->
				<form action="" method="post" id="borrowcsms_search_from" name="borrowcsms_search_from">
					<div style="padding: 5px">
						<table class="beforeloanTable">
							<tr>
								<td class="label_right">
									短信编号： 
								</td>
								<td>
									<input class="easyui-validatebox" name="tempCodeLike" id="tempCodeLike" />
								</td>
								<td class="label_right">
									短信名称： 
								</td>
								<td>
									<input class="easyui-validatebox" name="smsTempNameLike" id="smsTempNameLike" />
								</td>
							</tr>
							<tr>
								<td class="label_right">
									创建人： 
								</td>
								<td>
									<input class="easyui-validatebox" name="createUserLike" id="createUserLike" />
								</td>
								<td class="label_right">
								&nbsp;
								</td>
								<td >
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowAftMChoseSMS.searchBorrowAftCSms()">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="borrowAftMChoseSMS.resetBorrowAftCSms()">重置</a>
								</td>
							</tr>
						</table>	
					</div>
				</form>
			</div>	
		</div>
		
		<!-- 表单选项框 -->
		<div id="borrowcsms_dagrid_list">
			<div class="showDataListWrap">
				<table id=borrowAftCSms_datagrid_list></table>
			</div>
		</div>
	</div>
</body>