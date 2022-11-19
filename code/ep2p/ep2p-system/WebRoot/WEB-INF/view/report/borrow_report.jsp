<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>

<script type="text/javascript" src="${basePath}resources/js/report/borrow_report.js" charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<!-- 查询条件 -->
		<form action="" method="post" id="searcm" name="searcm">
		
			<label >日期: </label>
			<input class="easyui-datebox" name="startApproveTime" id="startApproveTime" />
			-
			<input class="easyui-datebox" name="endApproveTime" id="endApproveTime" />
			&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowReport.searchData();">统计</a>
		</form>
		<div class="showDataListWrap" id="borrowPrelimGridDiv">
			<table  width="40%">
				<tr>
					<td>
						<fieldset  id="allApprove" >
							<legend>全部借款</legend>
							借款申请笔数：<label id="allApproveCount"> </label> 笔<br>
							借款申请通过：<label id="allApproveViaCount"> </label> 笔<br>
							借款申请通过率：<label id="allApproveViaRate"> </label> %<br>
						</fieldset >
					</td>
					<td>
						<fieldset >
							<legend>e抵押</legend>
							借款申请笔数：<label id="mortgageApproveCount"> </label> 笔<br>
							借款申请通过：<label id="mortgageApproveViaCount"> </label> 笔<br>
							借款申请通过率：<label id="mortgageApproveViaRate"> </label> %<br>
						</fieldset >
					</td>
				</tr>
				<tr>
					<td>
						<fieldset id="downLoanApprove"> 
							<legend>e首房</legend>
							借款申请笔数：<label id="downLoanApproveCount"> </label> 笔<br>
							借款申请通过：<label id="downLoanApproveViaCount"> </label> 笔<br>
							借款申请通过率：<label id="downLoanApproveViaRate"> </label> %<br>
						</fieldset >
					</td>
					<td>
						<fieldset id="allApprove">
							<legend>其他</legend>
							e计划：<br>
							体验标：<label id="experienceStandardCount"> </label> 笔<br>
							新手标：<label id="newStandardCount"> </label> 笔<br>
						</fieldset >
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			borrowReport.searchData();
		})
	</script>
</body>