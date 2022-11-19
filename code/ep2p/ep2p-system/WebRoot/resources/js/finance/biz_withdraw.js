/**
 * 业务提现审核类js
 */
var BizWithdraw = {
		/**
		 * @param gridId datagrid表格ID
		 * @param title 标题
		 * @param toolbar 工具栏
		 * @param queryParams 查询参数 (queryStatus 必须字段)
		 * 这个字段用于查询逻辑处理
		 * 查询状态queryStatus（提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4）
		 * @param status 提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4
		 */
		init:function(gridId,title,toolbarId,queryParams,status){
			$(gridId).datagrid({    
				url:basePath + 'bizWithdrawController/bizWithdrawList.shtml',
				width:'100%',
				fit:true,
				title:title,
				toolbar:toolbarId,
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    queryParams:queryParams,
			    columns:biz_withdraw_model,
			    onClickRow:function(rowIndex, rowData){
					$(gridId).datagrid('clearChecked');
					$(gridId).datagrid('checkRow',rowIndex);
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
			    			$(gridId).datagrid('uncheckRow',i);
			    			$(gridId).datagrid('unselectRow',i);
			    		}
			    	}
			    	$('.datagrid-header .datagrid-htable input[type="checkbox"]').prop('checked',true);
			    },
			    onLoadSuccess: function(data){//加载完毕后获取所有的checkbox遍历
		            if (data.rows.length > 0) {
		                //循环判断操作为新增的不能选择
		                for (var i = 0; i < data.rows.length; i++) {
		                    //根据operate让某些行不可选
		                    if (data.rows[i].auditStatus != "1" && data.rows[i].auditStatus != "2" && data.rows[i].auditStatus != "4") {
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
		/**
		 * 查询待审核任务
		 */
		selectReferendumTask:function(queryParam,gridId){
			$(gridId).datagrid({    
				url:basePath + 'bizWithdrawController/bizWithdrawList.shtml',
				width:'100%',
				fit:true,
				title:'提现列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    queryParams:queryParam,
			    columns:biz_withdraw_model,
			    onClickRow:function(rowIndex, rowData){
					$(gridId).datagrid('clearChecked');
					$(gridId).datagrid('checkRow',rowIndex);
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
			    			$('#bizWithdrawGrid').datagrid('uncheckRow',i);
			    			$('#bizWithdrawGrid').datagrid('unselectRow',i);
			    		}
			    	}
			    	$('.datagrid-header .datagrid-htable input[type="checkbox"]').prop('checked',true);
			    },
			    onLoadSuccess: function(data){//加载完毕后获取所有的checkbox遍历
		            if (data.rows.length > 0) {
		                //循环判断操作为新增的不能选择
		                for (var i = 0; i < data.rows.length; i++) {
		                    //根据operate让某些行不可选
		                	if (data.rows[i].auditStatus != "1" && data.rows[i].auditStatus != "2" && data.rows[i].auditStatus != "4"){
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
		
		/**
		 * 批量审核
		 */
		batchAudit:function(){
			var rows = $('#biz_withdraw_grid').datagrid('getSelections');
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
			$("<div id='biz_withdraw_dialog' /> ").dialog({
				href:basePath + "bizWithdrawController/toBatchAudit.shtml?pid="+pId,
				title:"批量审核",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						BizWithdraw.save(1);
					}
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#biz_withdraw_dialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		/**
		 * 批量确认
		 */
		batchAffirm:function(){
			var rows = $('#transfer_affirm_grid').datagrid('getSelections');
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
			$("<div id='transfer_affirm_dialog' /> ").dialog({
				href:basePath + "alreadyTransferServiceController/toBatchAudit.shtml?pid="+pId,
				title:"客户提现批量转账确认",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						BizWithdraw.save(2);
					}
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#transfer_affirm_dialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		/**
		 * 审核
		 * @param status 
		 */
		save:function(status){
			var obj;
			var url;
			if(1==status){
				// 验证表单必填项
				if(!$("#bizWithdrawAdd").form('validate')){
					return ;
				}
				obj = jqueryUtil.serializeObject($("#bizWithdrawAdd"),true);
				obj.ifStatus = "1";
				url = BASE_PATH+"bizWithdrawController/batchAudit.shtml";
			}else if(2==status){
				if(!$("#transferServiceAdd").form('validate')){
					return ;
				}
				// 验证表单必填项
				// 验证表单必填项
				var obj = jqueryUtil.serializeObject($("#transferServiceAdd"),true);
				obj.ifStatus = "2";
				url = BASE_PATH+"bizWithdrawController/batchAudit.shtml";
			}
			$.ajax({
				type: "POST",
		        async:false, 
		        url:url,
		        data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		if(1==status){
			    			$.messager.alert('操作提示',"申请成功！",'success');
				    		$('#biz_withdraw_grid').datagrid('reload'); 
				    		$("#biz_withdraw_dialog").dialog('close');
			    		}else if(2==status){
			    			$.messager.alert('操作提示',"审核成功！",'success');
				    		$('#transfer_affirm_grid').datagrid('reload'); 
				    		$("#transfer_affirm_dialog").dialog('close');
			    		}
			    		
			    	}else{
			    		$.messager.alert('操作提示',"审核失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 新增
		add:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "BizWithdrawController/toAdd.shtml",
				title:"提现-新增",
				method:'post',
				width:'500',
				data:{pid:pid},
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:BizWithdraw.save
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
			var rows = $('#biz_withdraw_grid').datagrid('getSelections');
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
						$.post(BASE_PATH+"bizWithdrawController/deleteBtach.shtml",{pid:pId}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#bizWithdrawGrid").datagrid('load');
								}else{
									$.messager.alert('操作提示',"删除失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
		},
		/**
		 * * 查询按钮onclilk 事件
		 * @param gridId datagrid 列表ID
		 * @param queryParams 查询参数 (queryStatus 必须字段)
		 * 这个字段用于查询逻辑处理
		 * 查询状态queryStatus（提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4）
		 */
		searchButClick:function(gridId,queryStatus){
			var customerName = $("#customerName").textbox("getValue");
			var sname = $("#sname").textbox("getValue");
			var applyTime = $("#applyTime").textbox("getValue");
			var phoneNo = $("#phoneNo").textbox("getValue");
			var auditStatus;
			if(2 != queryStatus){
				 auditStatus = $("#auditStatus").combobox("getValue");
			}
			$(gridId).datagrid('load',{customerName:customerName,sname:sname,phoneNo:phoneNo,auditStatus:auditStatus,applyTime:applyTime,queryStatus:queryStatus});
		},
		/**
		 * 重置按钮
		 * @param formId form表单ID
		 */
		resetButClick:function(formId){
			$(formId).form('reset');
		},
		//跳转至数据下载页面
		toDownloadPage:function(gridId,formId){
			$.comdownload("#"+gridId,"#"+formId,
					basePath + "bizWithdrawController/exportDownLoadFile.shtml");
		},
		setWitSureDesc:function(obj){
			$("#witSureDesc").textbox("setValue",obj.dictContDesc); 
		},
		/**
		 * 加载下拉框的项
		 * @param selType
		 */
		getSelectData:function(url){
			$("#witSureDesc").textbox("setValue",""); 
			$("#descPromptId").combobox("clear");
			$("#descPromptId").combobox("reload",url);
		}
}
