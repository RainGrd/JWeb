<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<style type="text/css">
</style>

<script type="text/javascript"
	src="${basePath}resources/js/content/advertisementManage/advContent.js"
	charset="utf-8"></script>
<body class="easyui-layout">
	<div data-options="region:'center',border:false">
		<div id="scroll-bar-div">
			<div id="toolbar" class="easyui-panel" style="border-bottom: 0;">
				<form action="addContColumn.shtml" method="post" id="selectContForm"
					name="selectCont" enctype="multipart/form-data">
					<div style="padding: 5px">
						<table class="formTable">
							<tr>
								<td class="align_right"><font color="red">*</font>广告名称：</td>
								<td><input type="text" name="titleName" id="titleNameForm"
									value="${content.titleName}" /> <input type="hidden"
									value="${content.pid}" name="pid" id="pidForm" /> <input
									type="hidden" value="${advId}" name="advId" id="advIdForm" /></td>
							</tr>
							<tr>
								<td class="align_right">URL：</td>
								<td><input type="text" name="url" id="urlForm"
									value="${content.url}" /></td>
							</tr>
							<tr>
								<td class="align_right"><font color="red">*</font>状态：</td>
								<td><input type="hidden" id="hiddenStatus"
									value="${content.status}" /> <input type="radio" name="status"
									id="activationStatus" value="1" />启用<input type="radio"
									name="status" id="disableStatus" value="2" />禁用</td>
							</tr>
							<tr>

								<td width="" align="right" height=""><font color="red">*</font>文件路径：</td>
								<td><input class="text_style" required="true"  style="width: 200px;"
									missingMessage="请选择文件!" type="text" id="txt2" 
									value="${content.fileUrl}"/>
									<input type="hidden" name="fileId" value="${content.fileId}" name="fileId"/>
									 <input style="width:70px;"
									type="file" name="borrowFile" id="borrowFile"
									onchange="txt2.value=this.value" /></td>
							</tr>



<!-- 							<tr> -->
<!-- 								<td colspan="2" width="100%"><a href="javascript:void(0);" -->
<!-- 									class="easyui-linkbutton" iconCls="icon-save" -->
<!-- 									onclick="addContent()">保存</a> <a href="javascript:void(0);" -->
<!-- 									class="easyui-linkbutton" iconCls="icon-back" -->
<!-- 									onclick="retPage()">返回</a></td> -->
<!-- 							</tr> -->
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		if ($("#pidForm").val() == "") {
			$("#activationStatus").attr("checked", "true")
		} else {
			if ($("#hiddenStatus").val() == "1") {
				$("#activationStatus").attr("checked", "checked")
			} else {
				$("#disableStatus").attr("checked", "checked")
			}
		}
	})
	</script>
</body>




