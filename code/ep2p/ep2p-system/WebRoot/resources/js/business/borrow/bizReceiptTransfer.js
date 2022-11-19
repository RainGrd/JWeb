/**
 * 债权管理 js 类
 */
var InvestManage = {
		/**
		 * 初始化页面元素
		 */
		initDataGrid:function(data){
			//加载投资数据列表
			$('#invest_manage_grid').datagrid({    
				url:basePath + 'bizReceiptTransferController/bizReceiptTransferList.shtml',
				width:'100%',
				fit:true,
				title:'债权转让管理列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    queryParams:data,
			    columns:invest_manage_model,
			    onClickRow:function(rowIndex, rowData){
					$('#invest_manage_grid').datagrid('clearChecked');
					$('#invest_manage_grid').datagrid('checkRow',rowIndex);
					 //加载完毕后获取所有的checkbox遍历
		            $("input[type='checkbox']").each(function(index, el){
		                //如果当前的复选框不可选，则不让其选中
		                if (el.disabled == true) {
		                	$('#invest_manage_grid').datagrid('unselectRow', index - 1);
		                }
		            })
			    },
			    onLoadSuccess: function(data){
			    	//加载完毕后获取所有的checkbox遍历
		            if (data.rows.length > 0) {
		                //循环判断操作为新增的不能选择
		                for (var i = 0; i < data.rows.length; i++) {
		                    //根据operate让某些行不可选
		                    if (data.rows[i].pid==null || data.rows[i].pid == "" ) {
		                        $("input[type='checkbox']")[i + 1].disabled = true;
		                    }
		                }
		            }
		        }
		      
			});
		},
		//重置查询表单
		resetForm:function(){
			$("#invest_manage_form").form("reset");
		},
		/**
		 * 显示与隐藏查询条件
		 */
		searchCd:function(){
			// 清空隐藏的值
			var nons = $(".non");
			for(var i=0;i<nons.length;i++){
				$(nons[i]).val("");
			}
			
			$('table .moreTr').each(function(){
				if($(this).hasClass('none')){
					$(this).removeClass('none');
				}else{
					$(this).addClass('none');
				}
			});
			
			InvestManage.initDataGrid();	
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#invest_manage_grid","#invest_manage_form");
		},
		// 跳转到撤销
		toRevoke:function(pid){
			$("<div id='releaseDialog' /> ").dialog({
				href: basePath + "bizReceiptTransferController/toReceiptTransferRevoke.shtml?pid="+pid,
				title:"债权管理-撤销",
				method:'post',
				width:'700',
				height:'450',
				modal: true,
				buttons:[{
					text:'撤销',
					iconCls:'icon-save',
					handler:InvestManage.toRevokeConfirm
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
			$.messager.confirm("操作提示","确认撤消当前债权吗？",
					function(r) {
						
			 			if(r){
			 				InvestManage.revoke();
			 			}
				});
		},
		// 撤销
		revoke:function(){
			var obj = jqueryUtil.serializeObject($("#bizBorrowRelease"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizReceiptTransferController/toRevokeConfirmIsOk.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"撤销成功！",'success');
			    		$('#invest_manage_grid').datagrid('reload'); 
			    		$("#releaseConfirmDialog").dialog('close');
			    		$("#releaseDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"撤销失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		//跳转至数据下载页面
		toDownloadPage:function(){
			$.comdownload("#invest_manage_grid","#invest_manage_form",
					basePath + "bizReceiptTransferController/exportDownLoadFile.shtml");
		}
}

$(document).ready(function(){
	//判断是否从任务列表进入审核列表的
	var isTaskListOpen = $("#isTaskListOpen").val();
	var myDate = new Date();
	if("true"==isTaskListOpen){
		$('#releaseStartTime').datebox('setValue', getBeforeDate(14));
		$('#releaseEndTime').datebox('setValue', getSystemDate());
		$('#status').combobox('setValue', '1');
		var data= {status:"1",releaseStartTime:getBeforeDate(14),releaseEndTime:getSystemDate()}
		InvestManage.initDataGrid(data);
	}else{
		InvestManage.initDataGrid();	
	}
});

/**
 * 获取系统当前时间
 * @returns {String}
 */
function getSystemDate(){
  var d,s;
  d = new Date();
  s = d.getFullYear() + "-";             //取年份
  s = s + (d.getMonth() + 1) + "-";//取月份
  s += d.getDate();         //取日期
  return s;  
 } 

/**
 * 获取指定天数前的时间
 * @returns {String}
 */
function getBeforeDate(n){
    var n = n;
    var d = new Date();
    var year = d.getFullYear();
    var mon=d.getMonth()+1;
    var day=d.getDate();
    if(day <= n){
            if(mon>1) {
               mon=mon-1;
            }
           else {
             year = year-1;
             mon = 12;
             }
           }
          d.setDate(d.getDate()-n);
          year = d.getFullYear();
          mon=d.getMonth()+1;
          day=d.getDate();
     s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day);
     return s;
}

//借款发布查看借款人资料
function viewCustomerInfo(pid){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+pid;
	childLayout_addTab(path,'查看客户资料');
}

function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
	childLayout_addTab(path,"借款信息查看");
}