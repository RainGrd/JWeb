/**
 * 活动专区信息列表字段
 */
var pubActivityArea_model = [[    
    	        {field:'activityCode',title:'编号',width:'10%',align:'center',sortable:true,formatter:formatCode},    
    	        {field:'activityType',title:'类型',width:'5%',align:'center',sortable:true,formatter:formatType},    
    	        {field:'activityName',title:'名称',width:'20%',align:'center',sortable:true},
    	        {field:'activityStartDate',title:'开始日期',width:'10%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'activityEndDate',title:'结束日期',width:'10%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'activityImage',title:'配图',width:'5%',align:'center',sortable:true,formatter:formatImage},    
    	        {field:'activityUrl',title:'URL',width:'15%',align:'center',sortable:true,formatter:formatUrl},    
    	        {field:'isShows',title:'展示状态',width:'5%',align:'center',sortable:true,formatter:formatShows},  
    	        {field:'cz',title:'操作',width:'10%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];


/**
 * 格式化编号
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCode(value,row,index){
	var code = "";
	if(row.activityType == 1){
		// 如果是抢红包
		// 根据活动ID获取活动对象
		$.ajax( {
			type : "GET",
			url : BASE_PATH+"actActivityController/getActivityByPid.shtml?pid="+row.activityId,
			async : false,
			dataType : "json",
			success : function(data) {
				var path = BASE_PATH + "pubRedpacketController/openPubRedpacketAdd.shtml?pid="+data.actActivity.objectId;
				var str = "查看抢红包活动";
				code = '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">'+row.activityCode+'</a>';
			}
		}); 
	}else if(row.activityType == 2){
		// 如果是加息劵
		// 根据活动ID获取活动对象
		$.ajax( {
			type : "GET",
			url : BASE_PATH+"actActivityController/getActivityByPid.shtml?pid="+row.activityId,
			async : false,
			dataType : "json",
			success : function(data) {
				var path = BASE_PATH + "pubInvestAwardController/openPubInvestAwardAdd.shtml?pid="+data.actActivity.objectId;
				var str = "查看投资奖励活动";
				code = '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">'+row.activityCode+'</a>';
			}
		});
	}else {
		code = row.activityCode;
	}
	return code;
}

/**
 * 格式化图片是否配了
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatType(value,row,index){
	if(row.activityType == 1){
		return "抢红包";
	}else if(row.activityType == 2){
		return "加息劵";
	}else if(row.activityType == 99){
		return "其他";
	}
}

/**
 * 格式化图片是否配了
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatImage(value,row,index){
	if(null != row.activityImage && row.activityImage != ""){
		var url = basePath + row.activityImage;
		return '<a href="'+url+'" target="blank">预览</a>';
	}else{
		return "未配图";
	}
}

/**
 * 格式化URL
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatUrl(value,row,index){
	if(null != row.activityUrl && row.activityUrl != ""){
		return '<a href="'+row.activityUrl+'" target="blank">'+row.activityUrl+'</a>';
	}else{
		return "";
	}
}

/**
 * 格式化是否展示
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatShows(value,row,index){
	var str = "";
	if(row.isShows == 1){
		str = "展示";
	}else if(row.isShows == 2){
		str = "不展示";
	}
	return '<a onclick="pubActivityArea.editIsShows('+"'"+row.pid+"'"+','+row.isShows+')" href="#">'+str+'</a>';
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	if(row.activityType == 99){
		return '<a onclick="pubActivityArea.openEdit('+index+')" href="#">编辑</a> | <a onclick="pubActivityArea.remove('+row.pid+')" href="#">删除</a>';
	}else{
		return '<a onclick="pubActivityArea.openEdit('+index+')" href="#">编辑</a>';
	}
}