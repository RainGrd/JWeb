/**
 * 合同管理列表模型试图
 */
var contractMListModel = [[    
         {field:'pid',checkbox:true},
         {field:'borAgrAnme',title:'协议名称',width:'10%',align:'center',sortable:true},
         {field:'status',title:'状态',width:'10%',align:'center',sortable:true,formatter:stateFormater}, 
         {field:'createUser',title:'创建人',width:'10%',align:'center',sortable:true}, 
         {field:'createTime',title:'创建时间',width:'12%',align:'center',sortable:true,formatter:convertDateDetail}, 
         {field:'lastUpdateUser',title:'最后更新人',width:'10%',align:'center',sortable:true}, 
         {field:'lastUpdateTime',title:'最后更新时间',width:'12%',align:'center',sortable:true,formatter:convertDateDetail}, 
         {field:'op',title:'操作',width:'15%',align:'center',sortable:true,formatter:cOperateFormatter}
     ]];


//操作栏目显示数据
function cOperateFormatter(value, row, index) {
	var v = "";
	v = v + '<a href="javascript:void(0);" onClick="contractManageList.toContractEidtPage(\'' + row.pid +'\')" >编辑</a>';
	v = v + "&nbsp;&nbsp;";
	v = v + '<a href="javascript:void(0);" onClick="contractManageList.downloadContractFile(\'' + row.pid +'\',\'temp\')" >下载</a>';
	v = v + "&nbsp;&nbsp;";
	v = v + '<a href="javascript:void(0);" onClick="contractManageList.openFileUploadDiv(\'' + row.pid +'\')" >重新上传</a>';
	v = v + "&nbsp;&nbsp;";
	v = v + '<a href="javascript:void(0);" onClick="contractManageList.downloadContractFile(\'' + row.pid +'\',\'context\')" >预览</a>';
	return v;
}