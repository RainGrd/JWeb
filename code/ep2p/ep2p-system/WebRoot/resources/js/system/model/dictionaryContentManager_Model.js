/**
 * 数据字典内容管理列表字段
 */
var dictionaryContentManager_model = [[    
       	        {field:'pid',checkbox:true},
       	        //{field:'tt',title:'序号',width:'3%',align:'center',sortable:true,formatter:formatSer},    
    	        {field:'dictContCode',title:'字典编码',width:'10%',align:'center',sortable:true},    
    	        {field:'dictContName',title:'字典名称',width:'20%',align:'center',sortable:true},    
    	        {field:'dictContOrder',title:'顺序号',width:'10%',align:'center',sortable:true},    
    	        {field:'status',title:'状态',width:'10%',align:'center',sortable:true,formatter:formatStatus}, 
    	        {field:'dictContDesc',title:'描述',width:'25%',align:'center',sortable:true},    
    	        {field:'yy',title:'操作',width:'20%',align:'center',formatter:formatOperation}
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


/**
 * 操作格式化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOperation(value,row,index){
	// 数据字典标记为不可修改,则隐藏内容的修改超链接
	if(distionaryContent.isUpdate != 0 ){
		var func = 'distionaryContent.toUpdate("'+row.pid+'")';
		return "<a href='javascript:"+func+"'>修改</a>";
	}
	return "";
}

/**
 * 状态格式化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatStatus (value,row,index){
	if(row.status == 0){
		return "禁用";
	}
	return "启用";
}
