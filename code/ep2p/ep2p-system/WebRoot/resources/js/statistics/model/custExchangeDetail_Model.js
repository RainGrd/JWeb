/**
 * 实名认证列表字段
 */
var custExchangeDetail_Model = [[    
                {field:'pid',checkbox:true},
                {field:'exchangeTime',title:'兑换时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},    
    	        {field:'customerName',title:'用户名',width:'8%',align:'center',sortable:true,formatter:formatterCusName},    
    	        {field:'sname',title:'姓名',width:'8%',align:'center',sortable:true},    
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
    	        {field:'exchangeType',title:'兑换类别',width:'10%',align:'center',sortable:true,formatter:formatExchangeType},
    	        {field:'exchangeRemark',title:'兑换商品',width:'10%',align:'center',sortable:true},
    	        {field:'exchangePoint',title:'积分',width:'10%',align:'center',sortable:true},
    	        {field:'exchangeStatus',title:'兑换状态',width:'8%',align:'center',sortable:true,formatter:formatExchangeStatus},
    	    ]];

/**
 * 格式化兑换类别
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatExchangeType(value,row,index){
	if(row.exchangeType == 1 ){
		return "兑换话费";
	}else if(row.exchangeType == 2 ){
		return "兑换加息劵";
	}else if(row.exchangeType == 3 ){
		return "兑换VIP";
	}else if(row.exchangeType == 4 ){
		return "兑换现金";
	}
	return "";
}

/**
 * 格式化兑换状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatExchangeStatus(value,row,index){
	if(row.exchangeStatus == 1 ){
		return "兑换成功";
	}else if(row.exchangeStatus == 2 ){
		return "兑换失败";
	}
	return "";
}

/**
 * 格式化用户名
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatterCusName(value,row,index){
	if(null != row.customerName && row.customerName != ""){
		var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+row.customerId;
		var str = "查看客户资料";
		return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">'+row.customerName+'</a>';
	}
	return "";
}