<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<form action="" id="borrowAft_updescForm" name="updescForm" method="post">
			<table class="formTable percent100">
				<tr>
					<td class="align_right" width="90">借款编号：</td>
					<td>
						${bizb.borrowCode}
					</td>
				</tr>
				<tr>
					<td class="align_right">借款名称：</td>
					<td>
						${bizb.borrowName }
					</td>
				</tr>
				<tr>
					<td class="align_right">发布时间：</td>
					<td>
						${bizb.publishTime}
					</td>
				</tr>
				<tr>
					<td class="align_right">备注：</td>
					<td>
						<input type="hidden" name="pid" id="pid" value="${pypid}">
						<textarea name="repPlaDesc" id="repPlaDesc" class="percent70">${repPlaDesc}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>