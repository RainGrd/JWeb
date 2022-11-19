/**
 * 新手标体验标选择协议js
 */
var newStandard_Protocol = {
		//跳转到发信息页面
		toChoseProtocolPage:function(){
			$("<div id='newStandard_Protocol' /> ").dialog({
				href:basePath + "agreementContextController/chooseAgreement.shtml",
				title:"借款协议选择",
				method:'post',
				maximized:false,
				width:580,
				height:450,
				modal: true,
				buttons:[{
					text:'选择',
					iconCls:'icon-save',
					handler:function(){
						newStandard_Protocol.checkInfo();
					}
				}],
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
		},
		//选择协议的信息数据
		checkInfo:function(){
			var checkList = $("#agreementMContext_datagrid_list").datagrid("getSelections");
			if(checkList.length == 1){
				var pid = checkList[0].pid;
				var agrContName = checkList[0].borAgrAnme;
				$("#financeName").textbox("setValue",agrContName);
				$("#borrowAgreementId").val(pid);
				$("#newStandard_Protocol").dialog('close');
			}else{
				$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
			}
		}
}