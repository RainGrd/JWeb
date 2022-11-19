/**
 * 发送信息页面处理js
 */
var borrowAftMSendSMS = {
	//跳转到发信息页面
	toChoseSMSPage:function(){
		$("<div id='borrowAfterM_ChoseSMS_Page' /> ").dialog({
			href:basePath + "borrowAfterManageController/toChoseSMSPage.shtml",
			title:"系统短信查询",
			method:'post',
			maximized:false,
			width:580,
			height:450,
			modal: true,
			buttons:[{
				text:'选择',
				iconCls:'icon-save',
				handler:function(){
					borrowAftMSendSMS.smsCheckInfo();
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//选择短信的信息数据
	smsCheckInfo:function(){
		var checkList = $("#borrowAftCSms_datagrid_list").datagrid("getSelections");
		if(checkList.length == 1){
			var smscode = checkList[0].tempCode;
			var smscontent = checkList[0].smsContent;
			$("#borrowAft_sendSMSForm #smsCode").textbox("setValue",smscode);
			$("#borrowAft_sendSMSForm #smsContext").val(smscontent);
			$("#borrowAfterM_ChoseSMS_Page").dialog('close');
		}else{
			$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
		}
	}
	
};