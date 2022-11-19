<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/sysParam.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="scroll-bar-div">

			<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<form action="loadSysParams.shtml" method="post" id="searchFrom"
					name="searchFrom">
					<!-- 查询条件 -->
					<div style="padding: 5px">
						<table>
							<tr>
								<td width="20%" align="right" height="25">数据查询：</td>
								<td width="20" align="center" height="25"><input
									class="easyui-textbox" id="paramKey" name="paramKey" /></td>
								<td width="120" align="left">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="loadSysParam('')">查询</a>
								</td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="page" id="page" value="">
				</form>
				<!-- 操作按钮 -->
				<div style="padding-bottom: 5px">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addPara()">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="batchDelete()">删除</a>
		
				</div>
			</div>
			<div class="comBaseInfoDiv" style="height: 100%;">
				<table id="grid">
				</table>
			</div>
		</div>
	</div>
</body>




