<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript"src="${basePath}resources/js/statistics/model/inv_cus_statistical_model.js" charset="utf-8"></script>
	<script type="text/javascript"src="${basePath}resources/js/statistics/inv_cus_statistical.js"charset="utf-8"></script>
	<div data-options="region:'center',border:false,fit:true" >
		<!-- 查询条件 -->
		<div>
			<table class="formTable">
				<tr>
					<td></td>
					<td><a href="#" class="easyui-linkbutton"
						onclick="InvCusStatistical.initInvCusStatisticsGroupProvince(1)">投资金额排行</a></td>
					<td><a href="#" class="easyui-linkbutton"
						onclick="InvCusStatistical.initInvCusStatisticsGroupProvince(2)">投资人数排行</a></td>
					<td></td>
				</tr>
			</table>
		</div>
		<div style="padding: 5px">
			<table class="beforeloanTable">
				<tr>
					<td class="label_right">
						<div class="showDataListWrap">
							<table id="inv_cus_statistical_province_grid"></table>
						</div>
					</td>
					<td class="label_right">
						<div class="showDataListWrap">
							<table id="inv_cus_statistical_city_grid"></table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>