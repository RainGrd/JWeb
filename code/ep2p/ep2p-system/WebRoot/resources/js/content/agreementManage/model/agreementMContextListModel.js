/**
 * 协议模板管理model
 */
var agreementMContextListModel = [[
      {field:'pid',checkbox:true},
      {field:'agrContName',title:'内容名称',width:'18%',align:'center',sortable:true},
      {field:'protocol',title:'关键字',width:'18%',align:'center',sortable:true},
      {field:'status',title:'状态',width:'6%',align:'center',sortable:true,formatter:stateFormater}, 
      {field:'createUser',title:'创建人',width:'9%',align:'center',sortable:true}, 
      {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
      {field:'lastUpdateUser',title:'最后更新人',width:'9%',align:'center',sortable:true}, 
      {field:'lastUpdateTime',title:'最后更新时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
      {field:'op',title:'操作',width:'6%',align:'center',sortable:true,formatter:opContextFormatter},
 ]];


//操作栏目显示数据
function opContextFormatter(value, row, index) {
	var v = "";
	v = v + '<a href="javascript:void(0);" onClick="agreementMContext.toAgreementContextEidtPage(\'' + row.pid + '\')" >编辑</a>';
	return v;
}