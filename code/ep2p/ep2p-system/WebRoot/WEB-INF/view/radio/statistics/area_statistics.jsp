<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<h3 class="area_sh_h" >各地区用户分布统计</h3>
		<!-- 统计结果 -->
		<div id="area_statisticResult" >
			<table class="area_st_table" border="2">
				<tr>
					<th>序号</th>
					<c:forEach var="item" items="${areaList}" >
						<th>
							<c:out value="${item.name}"></c:out>
						</th>
					</c:forEach>
				</tr>
				<tr>
					<td>1</td>
					<c:forEach var="item" items="${areaList}" >
						<td>
							<c:out value="${item.y}"></c:out>
						</td>
					</c:forEach>
				</tr>	
			</table>
		</div>
	</div>
</body>