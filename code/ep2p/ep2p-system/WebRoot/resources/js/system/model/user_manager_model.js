/**
 * 用户管理列表字段
 */
var userManager_model = [[    
       	        {field:'fid',checkbox:true},
    	        {field:'loginNmae',title:'用户账户',width:'4%',align:'center'},    
    	        {field:'realName',title:'真实姓名',width:'9%',align:'center',sortable:true},    
    	        {field:'weixinNumber',title:'微信号',width:'12%',align:'center',sortable:true}, 
    	        {field:'qqNumber',title:'QQ',width:'6%',align:'center',sortable:true},    
    	        {field:'tel',title:'手机号码',width:'6%',align:'center',sortable:true},  
    	        {field:'state',title:'状态',width:'6%',align:'center',sortable:true},    
    	        {field:'operation',title:'操作',width:'18%',align:'center'}
    	    ]];

/**
 * 角色管理   用户分配 model
 */
var user_assign_model = [[
	{field:'pid',checkbox:true},
	{field:'accountNo',title:'用户账户',width:'30%',align:'center'},    
	{field:'name',title:'真实姓名',width:'30%',align:'center'},    
	{field:'phoneNo',title:'手机号码',width:'30%',align:'center',sortable:true}
]];



