/**
 * 数据字典管理列表字段
 */
var houseManager_model = [[    
    	        {field:'homesProvince',title:'省份',width:'10%',align:'center',sortable:true},    
    	        {field:'homesCity',title:'市',width:'10%',align:'center',sortable:true},    
    	        {field:'homesArea',title:'区',width:'10%',align:'center',sortable:true}, 
    	        {field:'homesName',title:'楼盘',width:'20%',align:'center',sortable:true}, 
    	        {field:'homesType',title:'户型',width:'20%',align:'center',sortable:true},
    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
    	    ]];


//操作 格式化
function formatOperation(value,row,index){
	return '<a href="#" onclick="houseManager.toUpdate(\''+row.pid+'\')">编辑</a> | <a href="#" onclick="houseManager.deleteHouse(\''+row.pid+'\')">删除</a>';
}

