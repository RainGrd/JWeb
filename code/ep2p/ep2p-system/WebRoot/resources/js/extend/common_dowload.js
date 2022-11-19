/**
 * 公共导出控件信息js
 */
var initdown = {
	//初始下载窗口
	initwin:function(dgid,sechFid,hurl){
		var vhref = basePath + "commonController/toDownloadPage.shtml?dgid=" + dgid.substring(1,dgid.length);
		$("<div id='common_Download' /> ").dialog({
			href:vhref,
			title:"导出数据设定",
			method:'post',
			maximized:false,
			width:460,
			height:300,
			modal: true,
			buttons:[{
				text:'导出',
				iconCls:'icon-save',
				handler:function(){
					initdown.exportDownLoadF(sechFid,dgid,hurl);
				}
			},{
				text:'返回',
				iconCls:'icon-back',
				handler:function(){
					$('#common_Download').dialog('close');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	},
	//导出页面的选择信息
	changeSelectExportStyle:function(dgid){
		$('.exportSelectUl input[type="radio"]').each(function(){
			$(this).click(function(){
				$('.styleSet').addClass('none');
				$('.'+$(this).attr('setShow')).removeClass('none');
			});
		});
		
		//进行加载数据
		var checkList = $(dgid).datagrid("getSelections");
		if(checkList.length > 0){
			$("#exportSelectForm #show_checkrows").html(checkList.length);
			var pids = objToArrayPid(checkList).join(",");
			$("#exportSelectForm #checkrows").val(pids);
		}
	},
	//导出数据借后管理数据信息
	exportDownLoadF:function(sechFid,dgid,expUrl){
		//校验数据
		var bool = true;
		//获取父页面的搜索对象
		var parentObj = jqueryUtil.serializeObject($(sechFid),true);
		var childObj = jqueryUtil.serializeObject($("#exportSelectForm"),true);
		//把导出页面的数据进行封装在上传的参数中
		for(var opt in childObj){
			parentObj[opt] = childObj[opt];
		}
		var dgPager = $(dgid).datagrid("options").pageSize;
		parentObj["rows"] = dgPager;
		
		//校验参数
		if(parentObj["exportSet"] == '2'){
			if(parentObj["checkpageE"] < parentObj["checkpage"]){
				bool = false;
			}
		}
		if(parentObj["exportSet"] == '3'){
			if(parentObj["checkrecordsE"] < parentObj["checkrecords"]){
				bool = false;
			}
		}
		if(bool){
			$.download(expUrl,parentObj,'post');
		}else{
			$.messager.alert('操作提示',"输入内容有误",'error');
		}
	}
	
}

//调用入口
jQuery.comdownload = function(dgid,sechFid,hurl){
	//开窗口
	initdown.initwin(dgid,sechFid,hurl);
}
