/**
 * 借款审核js 文件
 * 
 * @author Yu.zhang
 * 
 * @date 2015-09-24 
 */

var borrowApprove = {
		
		// 查询 借款担保审核项目
		searchPrelimList:function(){
			jqueryUtil.ajaxSearchPage("#borrowPrelimGrid","#searcm");
		},
		// 初始化 借款担保审核项目table
		initBorrowPrelimDataGrid:function(){
			$('#borrowPrelimGrid').datagrid({    
				url:basePath + 'bizBorrowApproveController/selectAllPage.shtml?approveNode=1',
				width:'100%',
				fit:true,
				title:'担保初审项目列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:borrowPrelimManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#borrowPrelimGrid').datagrid('clearChecked');
					$('#borrowPrelimGrid').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
			    	if($("#borrowPrelimGridDiv .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowPrelimGridDiv .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowPrelimGrid').datagrid("deleteRow",0);
					}
			    }
			});
		},
		selectReferendumPrelimTask:function(){
			$('#borrowPrelimGrid').datagrid({    
				url:basePath + 'bizBorrowApproveController/selectAllPage.shtml',
				width:'100%',
				fit:true,
				title:'担保初审项目列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    queryParams:{approveStatus:"1",approveNode:2,isApproveList:true},
			    columns:borrowPrelimManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#borrowPrelimGrid').datagrid('clearChecked');
					$('#borrowPrelimGrid').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
			    	if($("#borrowPrelimGridDiv .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowPrelimGridDiv .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowPrelimGrid').datagrid("deleteRow",0);
					}
			    }
			});
		},
		// 查询 借款贷前审核项目
		searchReviewList:function(){
			jqueryUtil.ajaxSearchPage("#borrowReviewGrid","#searcm");
		},
		// 初始化 借款贷前审核项目table
		initBorrowReviewDataGrid:function(){
			$('#borrowReviewGrid').datagrid({    
				url:basePath + 'bizBorrowApproveController/selectAllPage.shtml?approveNode=2',
				width:'100%',
				height:'70%',
				fit:true,
				title:'借款贷前审核列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    columns:borrowReviewManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#borrowReviewGrid').datagrid('clearChecked');
					$('#borrowReviewGrid').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
			    	if($("#borrowReviewGridDiv .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowReviewGridDiv .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowReviewGrid').datagrid("deleteRow",0);
					}
			    }
			});
		},
		selectReferendumReviewTask:function(){
			$('#borrowReviewGrid').datagrid({    
				url:basePath + 'bizBorrowApproveController/selectAllPage.shtml',
				width:'100%',
				fit:true,
				title:'借款贷前审核列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    queryParams:{approveStatus:"1",approveNode:3,isApproveList:true},
			    columns:borrowReviewManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#borrowReviewGrid').datagrid('clearChecked');
					$('#borrowReviewGrid').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
			    	if($("#borrowReviewGridDiv .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowReviewGridDiv .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowReviewGrid').datagrid("deleteRow",0);
					}
			    }
			});
		},
		// 借款贷前审核列表更多条件
		moreConditions:function(){
			if($('.more').hasClass('none')){
				$(".more").removeClass("none");
			}else{
				$(".more").addClass("none");
			}
			borrowApprove.initBorrowReviewDataGrid();
		},
		// 提交审批
		submitApprove:function(){
			if(!$("#borrowApprove").form('validate')){
				return returnValu;
			}
			
			var obj = jqueryUtil.serializeObject($("#borrowApprove"));
			$.messager.confirm("操作提示", "您确定要提交吗？", function (data) {
			  if (data) {
					$.ajax({
						type: "POST",
				    	url : BASE_PATH+"bizBorrowApproveController/submitAudit.shtml",
				    	data:obj,
						dataType: "json",
					    success: function(data){
					    	if(data.message.status ==200){
					    		$.messager.alert("操作提示","提交成功!","success",function(){
					    			parent.$('#centerFrame').tabs('close',parent.$('#centerFrame').tabs('getSelected').panel('options').title);
					    		});
					    	}else{
					    		$.messager.alert("操作提示","审批失败,请联系管理员!","error");
					    	}
						}
					});
	            }
	        });
		}
}

