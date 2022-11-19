/**
 * 
 */
var borrowAftMChoseSMS = {
	//初始化数据信息
	initBorrowAftMChoseSMSDataList:function(){
		$('#borrowAftCSms_datagrid_list').datagrid({    
			url:basePath + 'sysSmsTemplatesController/smsTemplatesList.shtml',
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    singleSelect:true,
		    toolbar:'#borrowcsms_toolbar',
		    columns:borrowAftCSmsManage_model,
		    onLoadSuccess: function(data){
	            //加载完毕后获取所有的checkbox遍历
	            if (data.rows.length > 0) {
	                //循环判断操作为新增的不能选择
	                for (var i = 0; i < data.rows.length; i++) {
	                    //根据operate让某些行不可选
	                    if (data.rows[i].operate == "false") {
	                        $("input[type='radio']")[i].disabled = true;
	                    }
	                }
	            }
	        },
	        onClickRow: function(rowIndex, rowData){
	            //加载完毕后获取所有的checkbox遍历
	            var radio = $("input[type='radio']")[rowIndex].disabled;
	            //如果当前的单选框不可选，则不让其选中
	            if (radio!= true) {
	                //让点击的行单选按钮选中
	                $("input[type='radio']")[rowIndex].checked = true;
	            }
	            else {
	                $("input[type='radio']")[rowIndex].checked = false;
	            }
	        }
		});
	},
	//短信搜素
	searchBorrowAftCSms:function(){
		var obj = jqueryUtil.serializeObject($("#borrowcsms_search_from"));
		var objStr = JSON.stringify(obj);
		$('#borrowAftCSms_datagrid_list').datagrid('load',{data:objStr});
	},
	//查询框清空
	resetBorrowAftCSms:function(){
		$("#borrowcsms_search_from input").val("");
	}
};



//初始化信息数据
$(function(){
	borrowAftMChoseSMS.initBorrowAftMChoseSMSDataList();
});