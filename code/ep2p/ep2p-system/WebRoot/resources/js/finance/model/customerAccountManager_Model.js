/**
 *客户账户管理列表字段
 */
var customerAccountManager_model = [[    
       	        {field:'pid',checkbox:true},
       	        {field:'customerName',title:'客户名称',width:'10%',align:'center',formatter:common.getCustomerName},    
       	        {field:'sname',title:'真实姓名',width:'10%',align:'center',formatter:common.getSName}, 
       	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',formatter:common.getPhoneNo},
    	        {field:'registrationTime',title:'注册时间',width:'12%',align:'center',sortable:true,formatter:convertRegistrationTime},    
    	        {field:'rechargeAmount',title:'充值总额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'withdrawAmount',title:'提现总额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'dueAmount',title:'总待收金额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'availableAmount',title:'可用金额',width:'7%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'freezeAmount',title:'冻结金额',width:'7%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'tenderAmount',title:'投标总额',width:'7%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'experienceAmount',title:'体验金',width:'7%',align:'right',sortable:true,formatter:common.formatCurrency}
    	    ]];

/**
 * 注册时间取值
 * @param value
 * @param row
 * @param index
 * @returns
 */
function convertRegistrationTime(value,row,index){
	if(row.customer==null){
		return "";
	}else{
	return row.customer.registrationTime;
	}
}

