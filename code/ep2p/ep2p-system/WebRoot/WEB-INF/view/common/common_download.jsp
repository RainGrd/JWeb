<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<form id="exportSelectForm" name="exportSelectForm" action="" method="post" >
		<table class="formTable lineHeight25">
			<tr>
				<td class="align_right" width="100">导出方式选择：</td>
				<td>
					<ul class="exportSelectUl">
						<li>
							<input type="radio" checked name="exportSet" setShow='exportSet1' value="1"/>
							画面选中记录导出
						</li>
						<li>
							<input type="radio" name="exportSet" setShow='exportSet2' value="2"/>
							页数指定导出
						</li>
						<li>
							<input type="radio" name="exportSet" setShow='exportSet3' value="3"/>
							起始记录数导出
						</li>
					</ul>
				</td>
			</tr>
			<tr class="exportSet1 styleSet">
				<td></td>
				<td height="50">
					<div>
						您选择了 <span id="show_checkrows">0</span> 件记录,确认导出？(如果未勾选则导出全部)
						<input type="hidden" id="checkrows" name="checkrows" value="">
						<!--  您未选择导出的记录，请回返原画面选择 -->						
					</div>
				</td>
			</tr>
			<tr class="exportSet2 styleSet none">
				<td class="align_right vertical_align_top">页数设定：</td>
				<td>
					<div class="rangDate">
						<input class="easyui-numberbox" name="checkpage" />-
						<input class="easyui-numberbox" name="checkpageE" /> 
					</div>
					<div>

					设定格式两种： 1-50 （导出1到50页的内容）；
					空白时全部数据导出
					</div>
				</td>
			</tr>
			<tr class="exportSet3 styleSet none">
				<td class="align_right vertical_align_top">记录数设定：</td>
				<td>
					<div class="rangDate">
						<input class="easyui-numberbox" name="checkrecords" />-
						<input class="easyui-numberbox" name="checkrecordsE" />
						<input id="datagrid_id" name="datagrid_id" type="hidden" value="${dgid}">
					</div>
					<div>

					设定格式两种： 2-50 （导出2到50条记录的内容）；
					空白时全部数据导出
					</div>
				</td>
			</tr>
		</table>
	</form>
	<script>
		//进行初始化
		$(function(){
			var dgid = "#" + $("#datagrid_id").val();
			initdown.changeSelectExportStyle(dgid);
		});
	</script>
	</div>
</body>