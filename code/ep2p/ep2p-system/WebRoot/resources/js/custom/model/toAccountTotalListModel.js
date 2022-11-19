/**
 * 查看账户总额流水详细列表字段
 */
var view_cus_accountTotal = [[    
    	        {field:'happenTime',title:'日期',width:'25%',align:'center',sortable:true},    
    	        {field:'fundValue',title:'收入',width:'15%',align:'center',sortable:true,formatter:common.formatCurrency},    
    	        {field:'expenditure',title:'支出',width:'15%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'accountBalance',title:'账户总额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'businessType',title:'交易类型',width:'10%',align:'center',sortable:true}, 
    	        {field:'funWatDesc',title:'备注',width:'9%',align:'center',sortable:true}
    	    ]];

