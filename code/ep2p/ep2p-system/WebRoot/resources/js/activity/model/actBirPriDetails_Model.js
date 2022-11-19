/**
 * 赠送VIP活动明细datagrid字段
 */
var actBirPriDetails_model = [[     
    	        {field:'actCode',title:'编号',width:'12%',align:'center',sortable:true},    
    	        {field:'actName',title:'名称',width:'15%',align:'center',sortable:true},    
    	        {field:'actTag',title:'活动标签',width:'10%',align:'center',sortable:true},     
    	        {field:'priType',title:'特权种类',width:'10%',align:'center',sortable:true,formatter:formatType},   
    	        {field:'status',title:'进行状态',width:'10%',align:'center',sortable:true,formatter:formatStatus},   
    	        {field:'participantsNumber',title:'参与人数',width:'8%',align:'center',sortable:true,formatter:formatNumber},    
    	        {field:'cz',title:'操作',width:'5%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];

var actBirPriDetailsGrid_det_model= [[
				{field:'customerName',title:'用户名',width:'120px',align:'center',sortable:true},    
				{field:'custName',title:'用户名称',width:'100px',align:'center',sortable:true},    
				{field:'phoneNo',title:'手机号码',width:'100px',align:'center',sortable:true},   
				{field:'investAwardValue',title:'奖励',width:'100px',align:'center',sortable:true},   
				{field:'useStatus',title:'使用情况',width:'100px',align:'center',sortable:true},    
				{field:'getTime',title:'获得时间',width:'100px',align:'center',sortable:true},   
				{field:'investAwardType',title:'备注',width:'150px',align:'center',sortable:true,formatter:formatAwardType},     
            ]];

/**
 * 奖励类型
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatAwardType(value,row,index){
	if(row.investAwardType == 1){
		return "积分";
	}else if(row.investAwardType == 2){
		return "经验";
	}else if(row.investAwardType == 3){
		return "加息";
	} 
}
/**
 * 格式化状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	var myDate= new Date().getTime();
	var endTime =  new Date(row.endDate).getTime();
	var startTime = new Date(row.startDate).getTime();
	if(endTime == 0 || startTime== 0){
		return "";
	}
	if(myDate > endTime){
		return "已过期";
	}else if(myDate >= startTime && myDate <= endTime){
		return "进行中";
	}else if(myDate < startTime){
		return "未开始";
	} 
}
/**
 * 格式化特权类型
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatType(value,row,index){
	if(row.priType == 1){
		return "加息券";
	}else if(row.priType == 2){
		return "红包";
	}else if(row.priType == 3){
		return "体验金";
	}else if(row.priType == 4){
		return "经验券";
	}else if(row.priType == 5){
		return "积分券";
	}else if(row.priType == 6){
		return "财富合伙人 ";
	} 
}
/**
 * 格式化参与人数
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatNumber(value,row,index){
	if(row.paychecksAmount != 0 && row.paychecksAmount != null){
		return row.paychecksAmount;
	}
	if(null == row.participantsNumber || row.participantsNumber == 0){
		return "-";
	}  
	return row.participantsNumber;
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	 
	if(row.priType == "2"){
		// 跳转到编辑页面
		path = BASE_PATH + "actRedpActDetailController/openActRedpActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName+"&expNumber="+"";
		str = "红包获得明细";
	}else if(row.priType == "3"){ 
		// 跳转到编辑页面
		path = BASE_PATH + "actExpActDetailController/openActExpActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		str = "查看体验金赠送明细";
		path = encodeURI(encodeURI(path));
		return '<a onclick="selectActExpAct(\''+path+'\',\''+str+'\',\''+row.objectId+'\')" href="#">查看</a>';
	}else{
		//投资奖励
		// 跳转到编辑页面
		var path = BASE_PATH + "actInvAwaActDetailController/openActInvAwaActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName+"&expNumber="+"";
		var str = "查看投资奖励赠送明细";
		
	}
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	path = encodeURI(encodeURI(path));
	if(row.paychecksAmount != 0 && row.paychecksAmount != null){
		return "";
	}else{
		return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">查看</a>';
	}
}

/**
 * 跳转到体验金详情页面
 */
function selectActExpAct(path,str,pid){
	var expNumber = ""; 
	$.ajax( {
		type : "GET",
		url : BASE_PATH+"pubExpGoldController/selectByActivityId.shtml?activityId="+pid,
		async : false,
		dataType : "json",
		success : function(data) { 
			if(data.expGold != null){
				expNumber = data.expGold.expNumber;
			}
		}
	}); 
	path+="&expNumber="+expNumber;
	childLayout_addTab(path,str);
}
/**
 * 打开明细表窗口
 */
function openDetail(activityId,actCode,actName,expNumber,priType){
	// 跳转到编辑页面
	
	
	
//	
//	
//	if(){
//		//红包
//		// 获取明细数据的URL
//		var url = BASE_PATH + "actRedpActDetailController/selectAllPageDetail.shtml?activityId="+activityId;
//		actRedpActDetail.initDataGrid_det(url);
//		// 初始化展示数据
//		$("#toolbar_actRedpActDetail #activityId").val(activityId);// 活动ID
//		$("#toolbar_actRedpActDetail #actCode").text( actCode );// 活动编号
//		$("#toolbar_actRedpActDetail #actName").text( actName );// 活动名称
//		// 打开dialog
//		$("#actRedpActDetail_dlg").dialog("open").dialog('setTitle', "查看赠送明细");
//	}else if(priType == "3"){
//		//体验金
//		// 获取明细数据的URL
//		var url = BASE_PATH + "actExpActDetailController/selectAllPageDetail.shtml?activityId="+activityId;
//		actExpActDetail.initDataGrid_det(url);
//		// 初始化展示数据
//		$("#toolbar_actExpActDetail #activityId").val(activityId);// 活动ID
//		$("#toolbar_actExpActDetail #actCode").text( actCode );// 活动编号
//		$("#toolbar_actExpActDetail #actName").text( actName );// 活动名称 
//		// 打开dialog
//		$("#actExpActDetail_dlg").dialog("open").dialog('setTitle', "查看赠送明细");
//	}else{
//		//投资奖励（经验券、加息券、积分券）
//		// 获取明细数据的URL
//		var url = BASE_PATH + "actInvAwaActDetailController/selectAllPageDetail.shtml?activityId="+activityId;
//		actBirPriDetails.initDataGrid_det(url);
//		// 初始化展示数据
//		$("#toolbar_actInvAwaActDetail #activityId").val(activityId);// 活动ID
//		$("#toolbar_actInvAwaActDetail #actCode").text(actCode);// 活动编号
//		$("#toolbar_actInvAwaActDetail #actName").text(actName);// 活动名称 
//		// 打开dialog
//		$("#actInvAwaActDetail_dlg").dialog("open").dialog('setTitle', "查看赠送明细");
//	}
}
/**
 * 体验金
 */
var actExpActDetail = {
		//初始化赠送明细
		initDataGrid_det:function(url){
			// 初始化datagrid
			$('#actExpActDetailGrid_det').datagrid({    
				url:url,
				width:'100%',
				fit:true,
				method: 'POST',
				toolbar:'#toolbar_actExpActDetail',
				pagination: true,
			    rownumbers:true,
			    remoteSort:false,
			    fitColumns:true,
				selectOnCheck: true,
				singleSelect: false,
				checkOnSelect: true,
			    columns:actExpActDetailGrid_det_model,
			    onLoadSuccess:function(data){
					clearSelectRows('#actExpActDetailGrid_det');
					if($("#actExpActDetail_dlg .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#actExpActDetail_dlg .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#actExpActDetailGrid_det').datagrid("deleteRow",0);
					}
				}
			});
		}
}

/**
 * 红包
 */
var actRedpActDetail = { 
		// 初始化赠送明细
		initDataGrid_det:function(url){
			// 初始化datagrid
			$('#actRedpActDetailGrid_det').datagrid({    
				url:url,
				width:'100%',
				fit:true,
				method: 'POST',
				toolbar:'#toolbar_actRedpActDetail',
				pagination: true,
			    rownumbers:true,
			    remoteSort:false,
			    fitColumns:true,
				selectOnCheck: true,
				singleSelect: false,
				checkOnSelect: true,
			    columns:actRedpActDetailGrid_det_model,
			    onLoadSuccess:function(data){
					clearSelectRows('#actRedpActDetailGrid_det');
					if($("#actRedpActDetail_dlg .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#actRedpActDetail_dlg .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#actRedpActDetailGrid_det').datagrid("deleteRow",0);
					}
				}
			});
		} 
}
var actExpActDetailGrid_det_model= [[
                     				{field:'customerName',title:'用户名',width:'120px',align:'center',sortable:true},    
                     				{field:'custName',title:'姓名',width:'100px',align:'center',sortable:true},    
                     				{field:'phoneNo',title:'手机号码',width:'100px',align:'center',sortable:true},   
                     				{field:'paychecksAmount',title:'获得金额',width:'100px',align:'right',sortable:true,formatter:common.formatCurrency},   
                     				{field:'useAmount',title:'使用金额',width:'100px',align:'right',sortable:true,formatter:common.formatCurrency},    
                     				{field:'useStatus',title:'使用状况',width:'100px',align:'center',sortable:true},   
                     				{field:'getTime',title:'获取时间',width:'150px',align:'center',sortable:true,formatter:convertDateDetail},    
                     				{field:'expireTime',title:'过期时间',width:'150px',align:'center',sortable:true,formatter:convertDateDetail},    
                                 ]];
var actRedpActDetailGrid_det_model= [[
                      				{field:'customerName',title:'用户名',width:'120px',align:'center',sortable:true},    
                      				{field:'custName',title:'姓名',width:'100px',align:'center',sortable:true},    
                      				{field:'phoneNo',title:'手机号码',width:'100px',align:'center',sortable:true},   
                      				{field:'paychecksAmount',title:'领取金额',width:'100px',align:'right',sortable:true,formatter:common.formatCurrency},    
                      				{field:'getTime',title:'领取时间',width:'150px',align:'center',sortable:true,formatter:convertDateDetail},    
                      				{field:'remark',title:'备注',width:'150px',align:'center',sortable:true}                     
                                  ]];