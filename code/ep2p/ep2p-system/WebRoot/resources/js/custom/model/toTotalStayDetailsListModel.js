/**
 * 查看总代收资金流水详细列表字段
 */
var view_cus_totalStay = [[    
    	        {field:'expireTime',title:'回款日期',width:'22%',align:'center',sortable:true},    
    	        {field:'dueinTotalValue',title:'应收总额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},    
    	        {field:'capital',title:'本金',width:'13%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'receivableInterest',title:'利息',width:'13%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'customerId',title:'借款人',width:'15%',align:'center',sortable:true}, 
    	        {field:'borrowId',title:'借款',width:'10%',align:'center',sortable:true},
    	        {field:'planIndex',title:'期次',width:'10%',align:'center',sortable:true},
    	        {field:'recPlaDesc',title:'备注',width:'15%',align:'center',sortable:true}
    	    ]];
/**
 * 查看待收利息流水详细列表字段
 */
var view_cus_collectInterest = [[    
//       	        {field:'pid',checkbox:true},
//    	        {field:'expireTime',title:'回款日期',width:'30%',align:'center',sortable:true},    
//    	        {field:'receivableInterest',title:'利息',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency}, 
//    	        {field:'customerId',title:'借款人',width:'15%',align:'center',sortable:true}, 
//    	        {field:'borrowId',title:'借款',width:'10%',align:'center',sortable:true},
//    	        {field:'planIndex',title:'期次',width:'15%',align:'center',sortable:true},
//    	        {field:'recPlaDesc',title:'备注',width:'10%',align:'center',sortable:true}
				{field:'expireTime',title:'回款日期',width:'22%',align:'center',sortable:true},    
    	        {field:'dueinTotalValue',title:'应收总额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},    
    	        {field:'capital',title:'本金',width:'13%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'receivableInterest',title:'利息',width:'13%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'customerId',title:'借款人',width:'15%',align:'center',sortable:true}, 
    	        {field:'borrowId',title:'借款',width:'10%',align:'center',sortable:true},
    	        {field:'planIndex',title:'期次',width:'10%',align:'center',sortable:true},
    	        {field:'recPlaDesc',title:'备注',width:'15%',align:'center',sortable:true}
    	    ]];

