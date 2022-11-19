/**
 *业务提现审核类js_model
 */
var biz_withdraw_model = [[    
       	        {field:'pid',checkbox:true},
	       	    {field:'customerName',title:'客户名称',width:'10%',align:'center',formatter:common.getCustomerName},    
	            {field:'sname',title:'真实姓名',width:'10%',align:'center',formatter:common.getSName}, 
	            {field:'phoneNo',title:'手机号码',width:'10%',align:'center',formatter:common.getPhoneNo},
    	        
    	        
    	        {field:'applyTime',title:'申请时间',width:'12%',align:'center',sortable:true},    
    	        {field:'amount',title:'提现金额',width:'8%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'fee',title:'提现手续费',width:'8%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'belongingBank',title:'所属银行',width:'10%',align:'center',sortable:true,formatter:belongingBank},
    	        {field:'openAddress',title:'开户支行',width:'10%',align:'center',sortable:true,formatter:openAddress},
    	        {field:'bankcardNo',title:'银行卡号',width:'10%',align:'center',sortable:true,formatter:bankcardNo},
    	        {field:'auditStatus',title:'状态',width:'6%',align:'center',sortable:true,formatter:formatAuditStatus},
    	        {field:'userName',title:'处理人',width:'6%',align:'center',sortable:true,formatter:getUserName},
    	        {field:'witDesc',title:'备注',width:'20%',align:'center',sortable:true,formatter:getWitDesc}
    	    ]];

/**
 * 所属银行取值
 * @param value
 * @param row
 * @param index
 * @returns
 */
function belongingBank(value,row,index){
	if(row.bank){
		return row.bank.belongingBank;
	}
	return "";
}
/**
 * 开户支行取值
 * @param value
 * @param row
 * @param index
 * @returns
 */
function openAddress(value,row,index){
	if(row.bank){
		return row.bank.openAddress;
	}
	return "";
}
/**
 * 银行卡号取值
 * @param value
 * @param row
 * @param index
 * @returns
 */
function bankcardNo(value,row,index){
	if(row.bank){
		return row.bank.bankcardNo;
	}
	return "";
}

/**
 * 获取备注
 * @param value
 * @param row
 * @param index
 */
function getWitDesc(value,row,index){
	if(row.witSureDesc){
		return row.witSureDesc;
	}
	return value;
}

/**
 * 获取用户名
 * @param value
 * @param row
 * @param index
 * @returns
 */
function getUserName(value,row,index){
	if(row.transferedUserName){
		return row.transferedUserName;
	}
	return value;
}

/**
 * 获取状态
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatAuditStatus(value,row,index){
	//审核状态（1：申请中、2：同意（确认中）、3：拒绝、4：转账成功、5：转账失败）
	if(row.auditStatus == 1){
		return "提现审核";
	}else if(row.auditStatus == 2){
		return "转账确认";
	}else if(row.auditStatus == 3){
		return "提现拒绝";
	}else if(row.auditStatus == 4){
		return "转账成功";
	}else if(row.auditStatus == 5){
		return "转账失败";
	}
	return "";
}

