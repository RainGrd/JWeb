/**
 * 客户账户js类
 */
var customerAccount = {
		//初始化
		initDataGrid:function(){
			$('#customerAccountGrid').datagrid({    
				url:basePath + 'customerAccountController/customerAccountListData.shtml',
				width:'100%',
				fit:true,
				title:'客户账户列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:customerAccountManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#customerAccountGrid').datagrid('clearChecked');
					$('#customerAccountGrid').datagrid('checkRow',rowIndex);
					$("input[type='checkbox']").each(function(index, el){
		                //如果当前的复选框不可选，则不让其选中
		                if (el.disabled == true) {
		                	$(gridId).datagrid('unselectRow', index - 1);
		                }
			        });
			    },
			    /**
			     * 全选去除已经审核通过和拒绝的行
			     */
			    onCheckAll:function(rows){			
			    	for(var i=0; i<rows.length;i++){
			    		if(rows[i].isDisabled){
			    			$('#customerAccountGrid').datagrid('uncheckRow',i);
			    			$('#customerAccountGrid').datagrid('unselectRow',i);
			    		}
			    	}
			    	$('.datagrid-header .datagrid-htable input[type="checkbox"]').prop('checked',true);
			    },
			    onLoadSuccess: function(data){//加载完毕后获取所有的checkbox遍历
		            if (data.rows.length > 0) {
		                //循环判断操作为新增的不能选择
		                for (var i = 0; i < data.rows.length; i++) {
		                    //根据operate让某些行不可选
		                    if (data.rows[i].customer.customerName == '合计') {
		                        $("input[type='checkbox']")[i + 1].disabled = true;
		                        data.rows[i].isDisabled = true;
		                    }else{
		                    	data.rows[i].isDisabled = false;
		                    }
		                }
		            }
		        }
			});
		},
		
		// 新增
		add:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "customerAccountController/toAdd.shtml",
				title:"客户账户-新增",
				method:'post',
				width:'500',
				data:{pid:pid},
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:customerAccount.save
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#addDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 删除
		batchRemove:function(){
			var rows = $('#customerAccountGrid').datagrid('getSelections');
		 	if ( rows.length == 0 ) {
		 		$.messager.alert("操作提示","请选择数据","error");
				return;
			}
		 	// 获取选中pid
		 	var pId = "";
			for(var i=0;i<rows.length;i++){
		  		if(i==0){
		  			pId+=rows[i].pid;
		  		}else{
		  			pId+=","+rows[i].pid;
		  		}
		  	}
			$.messager.confirm("操作提示","确定删除选择项?",
				function(r) {
		 			if(r){
						$.post(BASE_PATH+"customerAccountController/deleteBtach.shtml",{pid:pId}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#customerAccountGrid").datagrid('load');
								}else{
									$.messager.alert('操作提示',"删除失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
		},
		// 保存
		save:function(){
			// 验证表单必填项
			if(!$("#customerAccountAdd").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#customerAccountAdd"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"customerAccountController/batchAudit.shtml?pid="+obj.pid+"&auditStatus="+obj.auditStatus+"&witDesc="+obj.witDesc,
		    	//data:{pid:obj.pid,auditStatus:obj.auditStatus,witDesc:obj.witDesc},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"审核成功！",'success');
			    		$('#customerAccountGrid').datagrid('reload'); 
			    		$("#toBatchAudit").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"审核失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		searchData:function(){
			var customerName = $("#customerName").textbox("getValue");
			var sname = $("#sname").textbox("getValue");
			var phoneNo = $("#phoneNo").textbox("getValue");
			var rechargeStartValue = $("#rechargeStartValue").textbox("getValue");
			var rechargeEndValue = $("#rechargeEndValue").textbox("getValue");
			var withdrawStartValue = $("#withdrawStartValue").textbox("getValue");
			var withdrawEndValue = $("#withdrawEndValue").textbox("getValue");
			var dueStartValue = $("#dueStartValue").textbox("getValue");
			var dueEndValue = $("#dueEndValue").textbox("getValue");
			var availableStartValue = $("#availableStartValue").textbox("getValue");
			var availableEndValue = $("#availableEndValue").textbox("getValue");
			var freezeStartValue = $("#freezeStartValue").textbox("getValue");
			var freezeEndValue = $("#freezeEndValue").textbox("getValue");
			var tenderStartValue = $("#tenderStartValue").textbox("getValue");
			var tenderEndValue = $("#tenderEndValue").textbox("getValue");
			var registrationStartValue = $("#registrationStartValue").datebox("getValue");
			var registrationEndValue = $("#registrationEndValue").datebox("getValue");
			$('#customerAccountGrid').datagrid('load',{customerName:customerName,sname:sname,phoneNo:phoneNo,rechargeStartValue:rechargeStartValue,
				rechargeEndValue:rechargeEndValue,withdrawStartValue:withdrawStartValue,withdrawEndValue:withdrawEndValue,dueStartValue:dueStartValue,
				dueEndValue:dueEndValue,availableStartValue:availableStartValue,availableEndValue:availableEndValue,freezeStartValue:freezeStartValue,
				freezeEndValue:freezeEndValue,tenderStartValue:tenderStartValue,tenderEndValue:tenderEndValue,registrationStartValue:registrationStartValue,
				registrationEndValue:registrationEndValue
			});
		},
		cleanData:function(){
			$('#searcm').form('reset');
		},
		//跳转至数据下载页面
		toDownloadPage:function(){
			$.comdownload("#customerAccountGrid","#searcm",
					basePath + "customerAccountController/exportDownLoadFile.shtml");
		}
}


$(document).ready(function(){
	customerAccount.initDataGrid();			
});
