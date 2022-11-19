/**
 * 节目查询列表
 */
var displayList_model = [[    
                {field:'pid',hidden:true},
    	        {field:'programTitle',title:'节目名称',width:'10%',align:'center',sortable:true},    
    	        {field:'listenNum',title:'播放数',width:'10%',align:'center',sortable:true},    
    	        {field:'listenNum',title:'播放用户数 ',width:'15%',align:'center',sortable:true}, 
    	        {field:'praiseNum',title:'获赞数',width:'15%',align:'center',sortable:true}, 
    	        {field:'forwardNum',title:'转发数',width:'15%',align:'center',sortable:true}, 
    	        {field:'yy',title:'操作',width:'25%',align:'center',sortable:true,formatter:formatOperation}
    	    ]];
//操作格式化
function formatOperation(value,row,index){
	// 操作格式化
	var result = '';
	if(row.pid != '' && row.pid != null){
		result = '<a onclick="mystatistics.viewDetail(\''+row.pid+'\',\''+ row.programTitle +'\')" href="javascript:void(0);">查看趋势图</a>';
	}
	return result;
};

