/**
 * 借后管理
 */
var borrowAfterManage = {
	//初始化数据信息
	initBorrowAfterMDataList:function(){
		$('#borrowAfterM_datagrid_list').datagrid({    
			url:basePath + 'borrowAfterManageController/getBorrowAfterMList.shtml',
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    toolbar:'#borrowAfterM_list_toolbar',
		    columns:borrowAfterManage_Model,
		    onLoadSuccess: function(data){
		    	//加载完毕后获取所有的checkbox遍历
	            if (data.rows.length > 0) {
	                //循环判断操作为新增的不能选择
	                for (var i = 0; i < data.rows.length; i++) {
	                    //根据operate让某些行不可选
	                    if (data.rows[i].pid == "1") {
	                        $("input[type='checkbox']")[i + 1].disabled = true;
	                    }
	                }
	            }
			    if($("#borrowAfterM_context .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#borrowAfterM_context .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#borrowAfterM_context').datagrid("deleteRow",0);
				}
	        },
	        onClickRow: function(rowIndex, rowData){
	            //加载完毕后获取所有的checkbox遍历
	            $("input[type='checkbox']").each(function(index, el){
	                //如果当前的复选框不可选，则不让其选中
	                if (el.disabled == true) {
	                	$('#borrowAfterM_datagrid_list').datagrid('unselectRow', index - 1);
	                }
	            })
	        }
		});
	},
	
	//展开更多条件
	searchHidenShow:function(){
		$('table .moreTr').each(function(){
			if($(this).hasClass('none')){
				$(this).removeClass('none');
			}else{
				$(this).addClass('none');
			}
		});
		borrowAfterManage.initBorrowAfterMDataList();
	},
	
	//搜索功能
	searchMoreParam:function(){
		jqueryUtil.ajaxSearchPage("#borrowAfterM_datagrid_list","#borrowAfterM_search_from");
	},
	//清空表单内容信息
	resetBorrowAfterForm:function(){
		$("#borrowAfterM_search_from").form("reset");
	},
	
	// 借款贷前审核查看资料
	viewBorrowInfo: function(pid){
		var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
		childLayout_addTab(path,"借款信息查看");
	},
	
	//借款发布查看借款人资料
	viewCustomerInfo:function(pid){
		var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+pid;
		childLayout_addTab(path,'查看客户资料');
	},
	
	//跳转到发信息页面
	toSendSMSPage:function(){
		var checkList = $("#borrowAfterM_datagrid_list").datagrid("getSelections");
		if(checkList.length <= 0){
			$.messager.alert('操作提示',"请选择需要操作的数据行",'error');
		}else{
			$("<div id='borrowAfterM_SendSMS' /> ").dialog({
				href:basePath + "borrowAfterManageController/toSendSMSPage.shtml",
				queryParams:{
					pids:objToArrayPid(checkList).join(",")
				},
				title:"借款还款提醒短信选择",
				method:'post',
				maximized:false,
				width:460,
				height:300,
				modal: true,
				buttons:[{
					text:'立即发送',
					iconCls:'icon-save',
					handler:function(){
						borrowAfterManage.sendBorrowAfterSms();
					}
				}],
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
		}
	},
	
	//立即发送
	sendBorrowAfterSms:function(){
		$('#borrowAft_sendSMSForm').form('submit', {
			url: basePath + "smsRecordsController/sendBorrowAfterSms.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#borrowAfterM_datagrid_list').datagrid('reload'); 
		    		$("#borrowAfterM_SendSMS").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	},
	
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#borrowAfterM_datagrid_list","#borrowAfterM_search_from",
				basePath + "borrowAfterManageController/exportDownLoadFile.shtml");
	},
	
	//跳转至修改页面
	toUpdateDescPage:function(fpid,pid,desc){
		$("<div id='borrowAfterM_Update' /> ").dialog({
			href:basePath + "borrowAfterManageController/toUpdateDescPage.shtml",
			title:"修改备注",
			queryParams:{
				pid:pid,
				fpid:fpid,
				repPlaDesc:desc
			},
			method:'post',
			maximized:false,
			width:400,
			height:300,
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					borrowAfterManage.updateBizRemPlan();
				}
			},{
				text:'返回',
				iconCls:'icon-back',
				handler:function(){
					$('#borrowAfterM_Update').dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	
	//修改备注信息
	updateBizRemPlan:function(){
		$('#borrowAft_updescForm').form('submit', {
			url: basePath + "bizRepaymentPlanController/updateBizRemPlan.shtml",
			onSubmit : function() {
				return $(this).form('validate');
			},
			success: function(data){
				var data = eval('(' + data + ')');
				if(data.code == "200" ){
		    		$.messager.alert('操作提示',data.message,'success');
		    		$('#borrowAfterM_datagrid_list').datagrid('reload'); 
		    		$("#borrowAfterM_Update").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
			}
		});
	}
};







//进行初始化
$(function(){
	var repaidEndTime = $("#repaidEndTime").val();
	var repaidTime = $("#repaidTime").val();
	if(repaidTime && "" != repaidTime){
		$('#borrowAfterM_datagrid_list').datagrid({    
			url:basePath + 'borrowAfterManageController/getBorrowAfterMList.shtml',
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    toolbar:'#borrowAfterM_list_toolbar',
		    columns:borrowAfterManage_Model,
		    queryParams:{"repaidTime":repaidTime,"repaidEndTime":repaidEndTime},
		    onLoadSuccess: function(data){
		    	//加载完毕后获取所有的checkbox遍历
	            if (data.rows.length > 0) {
	                //循环判断操作为新增的不能选择
	                for (var i = 0; i < data.rows.length; i++) {
	                    //根据operate让某些行不可选
	                    if (data.rows[i].pid == "1") {
	                        $("input[type='checkbox']")[i + 1].disabled = true;
	                    }
	                }
	            }
			    if($("#borrowAfterM_context .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#borrowAfterM_context .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#borrowAfterM_context').datagrid("deleteRow",0);
				}
	        },
	        onClickRow: function(rowIndex, rowData){
	            //加载完毕后获取所有的checkbox遍历
	            $("input[type='checkbox']").each(function(index, el){
	                //如果当前的复选框不可选，则不让其选中
	                if (el.disabled == true) {
	                	$('#borrowAfterM_datagrid_list').datagrid('unselectRow', index - 1);
	                }
	            })
	        }
		});
	}
	borrowAfterManage.initBorrowAfterMDataList();
});