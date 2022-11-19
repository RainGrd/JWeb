/**
 * 数据字典管理列表字段
 */
var dictionaryManager_model = [[    
       	        {field:'pid',checkbox:true},
       	        //{field:'tt',title:'序号',width:'3%',align:'center',sortable:true,formatter:formatSer}, 
    	        {field:'dictCode',title:'数据类型编码',width:'10%',align:'center',sortable:true},    
    	        {field:'dictName',title:'数据类型名称',width:'20%',align:'center',sortable:true},    
    	        {field:'isUpdate',title:'是否允许修改',width:'10%',align:'center',sortable:true,formatter:formatIsUpdate}, 
    	        {field:'dictDesc',title:'备注',width:'25%',align:'center',sortable:true},    
    	        {field:'yy',title:'操作',width:'30%',align:'center',formatter:formatOperation}
    	    ]];

var ser = 0 ;

/**
 * 序号
 * @param value
 * @param row
 * @param index
 * @returns {Number}
 */
function formatSer(value,row,index){
	return ser++;
}

//操作 格式化
function formatOperation(value,row,index){
	var result = '<a onclick="viewDetail(\''+row.pid+'\',\''+row.isUpdate+'\')" href="#">查看内容</a>';
	
	if(row.isUpdate == 1){
		result+= ' | <a onclick="distionary.toUpdate(\''+row.pid+'\')" href="#"> 修改 </a>';
	}
	return result;
}

function viewDetail(pid,isUpdate){
	var path = BASE_PATH+"sysDistionaryContentController/toList.shtml?dictId="+pid+"&isUpdate="+isUpdate;
	childLayout_addTab(path,'数据字典内容查看');
	
}
// 是否允许修改 格式化
function formatIsUpdate(value,row,index){
	if(row.isUpdate == 0){
		return "否";
	}
	return "是";
}

