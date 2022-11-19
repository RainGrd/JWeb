/**
 * 查看投标记录详细列表字段
 */
var view_cus_bidRecord = [[    
    	        {field:'investmentTime',title:'投资时间',width:'12%',align:'center',sortable:true},    
    	        {field:'interestTime',title:'计息时间',width:'12%',align:'center',sortable:true},    
    	        {field:'investmentAmount',title:'投资金额',width:'12%',align:'right',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'borDeadline',title:'期限',width:'6%',align:'center',sortable:true}, 
    	        {field:'awardAmount',title:'奖励',width:'6%',align:'center',sortable:true}, 
    	        {field:'interest',title:'利息',width:'12%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowName',title:'项目',width:'10%',align:'center',sortable:true},
    	        {field:'borStatus',title:'借款状态',width:'10%',align:'center',sortable:true},
    	        {field:'borDetDesc',title:'备注',width:'10%',align:'center',sortable:true}
    	    ]];