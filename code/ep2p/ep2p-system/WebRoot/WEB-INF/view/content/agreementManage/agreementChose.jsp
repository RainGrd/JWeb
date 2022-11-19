<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
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
								<input class="easyui-validatebox" name="borAgrAnme" id="borAgrAnme" />
							</td>
						</tr> 
						<tr>
							<td></td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="borrowChoose.serachAgreementContext();">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-clear" onclick="$('#agreementMContext_serach_from').form('clear');">重置</a>
							</td>
						</tr>
					</table>	
				</div>
			</form>
			
		</div>
		</div>
		
		<!-- 合同管理内容信息 -->
		<div id="agreementMContext_context">
		<div class="showDataListWrap" >
			<table id="agreementMContext_datagrid_list" ></table>
		</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function myFunction()
	{
	  
		borrowChoose.initBorrow();
	}
	myFunction()

// 	function function1(){
// 	  alert(1);
// 	}
	//初始化信息数据
// 	$(function(){
// 		alert(1);
		
// 		borrowChoose.initBorrow();
// 	});
	</script>
</body>