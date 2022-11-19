/**
 * 新手标体验标管理js 文件
 * 
 * @author JunJie.Liu
 * 
 * @date 2015-10-19
 */
var newStandard = {
		// 查询标的列表
		searchCusTomer:function(){
			jqueryUtil.ajaxSearchPage("#new_standard_list","#searcm");
		},
		/**
		 * 初始化页面新手标体验标元素
		 */
		initDataGrid:function(type){
			//加载投资数据列表
			$('#new_standard_list').datagrid({    
				url:basePath + 'bizBorrowController/newStandard.shtml?type='+type,
				width:'100%',
				fit:true,
				title:'体验标新手标管理',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:newStandard_model,
			    onClickRow:function(rowIndex, rowData){
					$('#new_standard_list').datagrid('clearChecked');
					$('#new_standard_list').datagrid('checkRow',rowIndex);
			    }
			});
		},
		/**
		 * 初始化新手标体验标发布页面元素
		 */
		initDataGridRelease:function(type){
			if(type==null || type==""){
				return;
			}
			//加载投资数据列表
			$('#new_standard_list').datagrid({    
				url:basePath + 'bizBorrowController/newStandardRelease.shtml?type='+type,
				width:'100%',
				fit:true,
				title:'新手标体验标发布管理',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:newStandardRelease_model,
			    onClickRow:function(rowIndex, rowData){
					$('#new_standard_list').datagrid('clearChecked');
					$('#new_standard_list').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//重置查询表单
		resetForm:function(){
			$("#searcm").form("reset");
			var val = $("#newStandardType").combobox("getData");
            if(val.length>0){
               $("#newStandardType").combobox("select", val[0].dictContCode);
            }
		},
		// 打开新增
		openAdd:function(type){
			// 跳转到新增页面
			if(type == '1'){
				// 新手标
				// 跳转到新增页面
				var path = BASE_PATH + "bizBorrowController/toFinancialBorrowCusList.shtml?type=newStandard";
				childLayout_addTab(path,'选择客户');
			}else{
				// 体验标
				var path = BASE_PATH + "bizBorrowController/newStandardAdd.shtml?pid=-1&paramsKey=SYSTEM_USER&type=2";
				childLayout_addTab(path,'新增体验标');
			}
			
		},
		// 编辑
		edit:function(pid,view){
			var path = BASE_PATH + "bizBorrowController/newStandardAdd.shtml?pid="+pid+"&view="+view;
			childLayout_addTab(path,'编辑新手标体验标');
		},
		// 删除 
		remove:function(pid){
			if(pid==null || pid==""){
				$.messager.alert('操作提示',"删除失败,请重新操作!","error");
				return;
			}
			$.messager.confirm("操作提示","确定删除选中的数据吗?",
				function(r) {
		 			if(r){
		 				$.ajax( {
		 					type : "post",
		 					url : BASE_PATH+"bizBorrowController/deleteById.shtml?pid="+pid,
		 					async : false,
		 					dataType : "json",
		 					success : function(data) {
		 						if(data.message == 200){
			 						$.messager.alert('操作提示',"删除成功!");
									$("#new_standard_list").datagrid('reload');
		 						}else{
		 							$.messager.alert('操作提示',"删除失败,请重新操作!","error");
		 						}
		 					}
		 				}); 
		 			}
			});
		},
		// 弹出体验标新手标发布
		toRelease:function(pid){
			$("<div id='releaseDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toNewRelease.shtml?pid="+pid,
				title:"体验标新手标-发布",
				method:'post',
				width:'700',
				height:'450',
				modal: true,
				buttons:[{
					text:'发布',
					iconCls:'icon-save',
					handler:newStandard.toReleaseConfirm
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#releaseDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 弹出借款项目发布确认
		toReleaseConfirm:function(){
			// 验证表单必填项
			if(!$("#bizBorrowRelease").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#bizBorrowRelease"));
			var objStr = JSON.stringify(obj);
			$("<div id='releaseConfirmDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toNewReleaseConfirm.shtml?data="+objStr,
				title:"体验标新手标-发布确认",
				method:'post',
				width:'700',
				height:'350',
				modal: true,
				buttons:[{
					text:'确认',
					iconCls:'icon-save',
					handler:newStandard.release
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#releaseConfirmDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 发布
		release:function(){
			// 验证表单必填项
			if(!$("#bizBorrowRelease").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#bizBorrowRelease"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/toNewReleaseConfirmIsOk.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"发布成功！",'success');
			    		$('#new_standard_list').datagrid('reload'); 
			    		$("#releaseConfirmDialog").dialog('close');
			    		$("#releaseDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"发布失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 跳转到撤销
		toRevoke:function(pid){
			$("<div id='releaseDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toNewStandardRevoke.shtml?pid="+pid,
				title:"体验标新手标-撤销",
				method:'post',
				width:'700',
				height:'450',
				modal: true,
				buttons:[{
					text:'撤销',
					iconCls:'icon-save',
					handler:newStandard.toRevokeConfirm
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#releaseDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//撤销确认
		toRevokeConfirm:function(){
			// 验证表单必填项
			if(!$("#bizBorrowRelease").form('validate')){
				return ;
			}
			$.messager.confirm("操作提示","确认撤消当前标的吗？",
					function(r) {
						
			 			if(r){
			 				newStandard.revoke();
			 			}
				});
		},
		// 撤销
		revoke:function(){
			var obj = jqueryUtil.serializeObject($("#bizBorrowRelease"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/toNewStandardRevokeConfirmIsOk.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"撤销成功！",'success');
			    		$('#new_standard_list').datagrid('reload'); 
			    		$("#releaseConfirmDialog").dialog('close');
			    		$("#releaseDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"撤销失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 跳转到投标记录详情
		toDetail:function(pid){
			$("<div id='detailDialog' /> ").dialog({
				href: basePath + "bizBorrowController/getBorrowById.shtml?pid="+pid+"&toUrl="+"bizBorrowDetail/bizBorrowDetailByBorrowId",
				title:"新手标体验标-投标记录",
				method:'post',
				width:'700',
				height:'500',
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#detailDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		}
}




/**
 * 样式控制
 */
var styleController ={
		// 产品资料显示
		productInfoShow:function(){
			// 移除产品介绍的样式
			// 移除bar的样式
			$("#bar").removeClass("overBar");
			// 移除产品介绍的样式
			$("#bproc").addClass("greyState").removeClass("overState");
			// 隐藏产品介绍
			$("#productInc").hide();
			// 显示产品资料
			$("#productInfo").show();
		},
		// 产品介绍显示
		productIncShow:function(){
			// 添加产品介绍的样式
			// 增加bar的样式
			$("#bar").addClass("overBar");
			// 增加产品介绍的样式
			$("#bproc").removeClass("greyState").addClass("overState");
			// 隐藏产品资料
			$("#productInfo").hide();
			// 显示产品介绍
			$("#productInc").show();
		}
		
}



//借款发布查看借款人资料
function viewCustomerInfo(pid){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+pid;
	childLayout_addTab(path,'查看客户资料');
}
