/**
 * 查看客户资金流水列表字段
 */
var view_cus_fundwater = [[    
    	        {field:'happenTime',title:'日期',width:'12%',align:'center',sortable:true},    
    	        {field:'fundValue',title:'收入',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},    
    	        {field:'expenditure',title:'支出',width:'15%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'accountBalance',title:'账户总额',width:'25%',align:'right',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'businessType',title:'交易类型',width:'10%',align:'center',sortable:true}, 
    	        {field:'funWatDesc',title:'备注',width:'15%',align:'center',sortable:true}
    	       
    	    ]];

