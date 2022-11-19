/**
 * 节目查询列表
 */
var programList_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'programTitle',title:'标题',width:'10%',align:'center',sortable:true},    
    	        {field:'programPlateId',title:'版块',width:'5%',align:'center',sortable:true},    
    	        {field:'status',title:'状态 ',width:'5%',align:'center',sortable:true}, 
    	        {field:'listenNum',title:'收听数',width:'5%',align:'center',sortable:true}, 
    	        {field:'praiseNum',title:'获赞数',width:'5%',align:'center',sortable:true}, 
    	        {field:'uploadTime',title:'上传时间',width:'10%',align:'center',sortable:true}, 
    	        {field:'publishTime',title:'发布时间',width:'10%',align:'center',sortable:true}, 
    	        {field:'eliminateTime',title:'下架时间',width:'10%',align:'center',sortable:true}, 
    	        {field:'yy',title:'操作',width:'25%',align:'center',sortable:true,formatter:formatOperation}
    	    ]];
//操作格式化
function formatOperation (value,row,index){
	// 操作格式化
		var result = '<a onclick="viewDetail(\''+row.pid+'\')" href="#">预览</a>';
		var result1 = '<a onclick="viewDetail1(\''+row.pid+'\',\''+row.publishTime+'\',\''+row.status+'\')" href="#">预发布</a>';
		if(row.status == "已发布"){
		    result1 = '<span style="color: Grey;">预发布</span>';
		}
		var result2 = '<a onclick="viewDetail2(\''+row.pid+'\')" href="#">发布</a>';
		var result3 = '<a onclick="viewDetail3(\''+row.pid+'\',\''+row.status+'\')" href="#">编辑</a>';
		var result4 = '<a onclick="viewDetail4(\''+row.pid+'\',\''+row.status+'\')" href="#">下架</a>';
		return result+" | "+result1+" | " +result2+" | " +result3+" | " +result4;
};
//节目预览
function viewDetail(pid){
	var path = BASE_PATH+"radioController/toPreviewOrEditProgramList.shtml?pid="+pid+'&flag='+1;
	$("<div id='programDetailsDialog' /> ").dialog({
		href:path,
		title:"节目预览",
		method:'post',
		width:'700',
		height:'250',
		pagination: true,
	    rownumbers:true,
		modal: true,
		buttons:[{
			text:'返回',
			iconCls:'icon-cancel',
			handler:function(){
				$("#programDetailsDialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}
//预发布
function viewDetail1(pid,publishTime,status){
	if(status =="预发布"){
		var path = BASE_PATH+"radioController/toPredictProgram.shtml?pid="+pid+'&publishTime='+publishTime;
		$("<div id='predictProgramDetailsDialog' /> ").dialog({
			href:path,
			title:"预发布节目",
			method:'post',
			width:'300',
			height:'150',
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					radio.savePredict();
				}
			},{
				text:'返回',
				iconCls:'icon-cancel',
				handler:function(){
					$("#predictProgramDetailsDialog").dialog('close');
				}
			}],
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	}
	else{
	var path = BASE_PATH+"radioController/toPredictProgram.shtml?pid="+pid;
	$("<div id='predictProgramDetailsDialog' /> ").dialog({
		href:path,
		title:"预发布节目",
		method:'post',
		width:'300',
		height:'150',
		modal: true,
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				radio.savePredict();
			}
		},{
			text:'返回',
			iconCls:'icon-cancel',
			handler:function(){
				$("#predictProgramDetailsDialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
	}
}
//发布
function viewDetail2(pid){
	$.messager.confirm('确认','确定发布？',function(r){    
	    if (r){    
	    	$.post(BASE_PATH+"radioController/updatepublishTime.shtml",{pid:pid}, 
					function(ret) {
						if(ret.message.status == 200){
							$.messager.alert('操作提示',"发布成功！",'success');
							$("#programListGrid").datagrid('reload');
						}else{
							$.messager.alert('操作提示',"发布失败！",'error');
						}
					},'json');
	    }    
	});  
}
//编辑
function viewDetail3(pid,status){
	if(status == "已发布"){
		$.messager.confirm('确认','已发布的节目不能编辑!');
		return;
	}else{
	var path = BASE_PATH+"radioController/toPreviewOrEditProgramList.shtml?pid="+pid+'&flag='+2;
	$("<div id='editProgramDetailsDialog' /> ").dialog({
		href:path,
		title:"节目编辑",
		method:'post',
		width:'900',
		height:'250',
		modal: true,
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				radio.update(pid);
			}
		},{
			text:'返回',
			iconCls:'icon-cancel',
			handler:function(){
				$("#editProgramDetailsDialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
	}
}
//下架
function viewDetail4(pid,status){
	if(status == "已下架"){
		$.messager.confirm('确认','你选的节目已下架!');
		return;
	}else{
		$.messager.confirm('确认','确定是否要下架该节目?',function(r){    
		    if (r){    
		    	$.post(BASE_PATH+"radioController/offlineProgram.shtml",{pid:pid}, 
						function(ret) {
							if(ret.message.status == 200){
								$.messager.alert('操作提示',"下架成功！",'success');
								$("#programListGrid").datagrid('reload');
							}else{
								$.messager.alert('操作提示',"下架失败！",'error');
							}
						},'json');
		    }    
		}); 
	}
}