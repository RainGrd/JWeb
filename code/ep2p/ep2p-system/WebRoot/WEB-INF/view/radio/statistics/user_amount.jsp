<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/radio/statistics/user_amount.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div class="queryTerms">
			<!-- 查询条件 -->
			<form action="" method="post">
				<div class="p5">
					<table class="beforeloanTable formTable">
						<tr>
							<td class="label_right">时间：</td>
							<td>
								<input class="easyui-datebox" id="usercountd" name="startDate" editable="false" />
							</td>	
																
							<td>
								<a href="javascript:void(0)" onclick="lineSearch();" class="easyui-linkbutton"
								data-options="iconCls:'icon-search'"
								>统计</a>
								<a href="javascript:void(0)" onclick="columnSearch();" class="easyui-linkbutton"
								data-options="iconCls:'icon-column'"
								>柱状图</a>
								<a href="javascript:void(0)" onclick="lineSearch();" class="easyui-linkbutton"
								data-options="iconCls:'icon-diagram'"
								>曲线图</a>
							</td>
						</tr>						
					</table>
				</div>
			</form>
		</div>
		
		<!-- 统计结果 -->
		<div id="users_statisticResult"></div>
	</div>
</body>