/**
 *客户资金流水管理列表字段
 */
var custFundWaterManager_model = [[    
       	        {field:'pid',checkbox:true},
       	        {field:'customerName',title:'客户名称',width:'10%',align:'center',formatter:common.getCustomerName},    
       	        {field:'sname',title:'真实姓名',width:'10%',align:'center',formatter:common.getSName}, 
       	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',formatter:common.getPhoneNo},
    	        {field:'happenTime',title:'日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},    
    	        {field:'srValue',title:'收入',width:'10%',align:'center',sortable:true,formatter:srValue}, 
    	        {field:'zcValue',title:'支出',width:'10%',align:'center',sortable:true,formatter:zcValue},
    	        {field:'businessType',title:'流水类型',width:'10%',align:'center',sortable:true,formatter:businessType},
    	        {field:'accountBalance',title:'账户余额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'funWatDesc',title:'备注',width:'10%',align:'center',sortable:true}
    	    ]];


function businessType(value,row,index){
	if(row.businessType==null){
		return "";
	}else if(row.businessType=="101"){
		return "线下充值";
	}else if(row.businessType=="103"){
		return "线上充值";
	}else if(row.businessType=="202"){
		return "提现记录";
	}else if(row.businessType=="302"){
		return "投资成功";
	}else if(row.businessType=="401"){
		return "加息奖励";
	}else if(row.businessType=="403"){
		return "投资奖励";
	}else if(row.businessType=="405"){
		return "推荐奖励";
	}else if(row.businessType=="407"){
		return "收取本金";
	}else if(row.businessType=="409"){
		return "收取利息";
	}else if(row.businessType=="411"){
		return "收取罚息";
	}else if(row.businessType=="413"){
		return "债权转让";
	}else if(row.businessType=="415"){
		return "红包";
	}else if(row.businessType=="419"){
		return "积分兑现";
	}else if(row.businessType=="421"){
		return "系统奖励";
	}else if(row.businessType=="423"){
		return "利息管理费分成";
	}else if(row.businessType=="425"){
		return "收款";
	}else if(row.businessType=="502"){
		return "利息管理费";
	}else if(row.businessType=="503"){
		return "借款成功";
	}else if(row.businessType=="504"){
		return "还款";
	}else if(row.businessType=="506"){
		return "VIP付费";
	}else if(row.businessType=="508"){
		return "提现手续费";
	}else if(row.businessType=="510"){
		return "已还本金";
	}else if(row.businessType=="512"){
		return "已还利息";
	}else if(row.businessType=="514"){
		return "支付逾期罚息";
	}else if(row.businessType=="516"){
		return "债权转让服务费";
	}else if(row.businessType=="518"){
		return "购买债权";
	}else if(row.businessType=="520"){
		return "账户管理费";
	}else if(row.businessType=="521"){
		return "续投奖励";
	}
}

function srValue(value,row,index){
	if(row.fundType==1){
		return row.fundValue;
	}
	if(row.businessType==999&&row.totalIncome){
		return row.totalIncome;
	}else{
		return "0";
	}
}

function zcValue(value,row,index){
	if(row.fundType==2){
		return "-"+row.fundValue;
	}
	if(row.businessType==999&&row.totalPay){
		return "-"+row.totalPay;
	}else{
		return "0";
	}
}



