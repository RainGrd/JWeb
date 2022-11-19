/**
 * 协议模板管理model
 */
var agreementTempManListModel = [[
      {field:'pid',checkbox:true},
      {field:'agrContTempName',title:'协议模版名称',width:'18%',align:'center',sortable:true},
      {field:'status',title:'状态',width:'10%',align:'center',sortable:true,formatter:stateFormater}, 
      {field:'createUser',title:'创建人',width:'10%',align:'center',sortable:true}, 
      {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
      {field:'lastUpdateUser',title:'最后更新人',width:'10%',align:'center',sortable:true}, 
      {field:'lastUpdateTime',title:'最后更新时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
      {field:'op',title:'操作',width:'10%',align:'center',sortable:true,formatter:operateFormatter},
 ]];


//操作栏目显示数据
function operateFormatter(value, row, index) {
	var v = "";
	v = v + '<a href="javascript:void(0);" onClick="agreementManageTemp.toAgreementMTempEidtPage(\'' + row.pid +'\')" >编辑</a>';
	v = v + "&nbsp;&nbsp;";
	v = v + '<a href="javascript:void(0);" onClick="agreementManageTemp.eidtAgreementTempAlement(\'' + row.pid +'\',\'' + row.agrContTempName + '\')" >查看</a>';
	return v;
}