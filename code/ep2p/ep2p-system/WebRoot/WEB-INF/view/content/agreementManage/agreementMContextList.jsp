<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/model/agreementMContextListModel.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/content/agreementManage/handle/agreementMContextHandle.js"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 搜索选项及工具栏 -->
		<div id="agreementMContext_list_toolbar">
		<div>
			<!-- 查询条件 -->
			<form method="post" id="agreementMContext_serach_from" >
				<div style="padding: 5px" > 
					<table class="beforeloanTable" width="70%">
						<tr>
							<td class="label_right"> 内容名称：</td>
							<td>
								<input type="hidden" name="agrConTemId" id="agrConTemId" value="${ppid}"/>
								<input class="easyui-validatebox" name="agrContName" id="agrContName" />
							</td>
							<td class="label_right"> 关键字：</td>
							<td>
								<input class="easyui-validatebox" name="protocol" id="protocol" />
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="agreementMContext.serachAgreementContext();">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="$('#agreementMContext_serach_from').form('clear');">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
			<!-- tool button  -->
			<div class="p10"> 
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="agreementMContext.toAgreementContextAddPage()">新增</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-enable" plain="true" onclick="agreementMContext.activAndDisAgreementContext(1)">启用</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-disable" plain="true" onclick="agreementMContext.activAndDisAgreementContext(2)">禁用</a>
			</div>
		</div>
		</div>
		
		<!-- 合同管理内容信息 -->
		<div id="agreementMContext_context">
		<div class="showDataListWrap" >
			<table id="agreementMContext_datagrid_list" ></table>
		</div>
		</div>
		
	</div>
</body>