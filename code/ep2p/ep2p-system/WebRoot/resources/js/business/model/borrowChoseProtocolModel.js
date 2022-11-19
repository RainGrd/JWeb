/**
 * 协议模板管理model
 */
var agreementMContextListModel = [[
           {field:'pid',checkbox:true},
           {field:'borAgrAnme',title:'协议名称',width:'18%',align:'center',sortable:true},
           {field:'status',title:'状态',width:'5%',align:'center',sortable:true,formatter:stateFormater}, 
           {field:'createUser',title:'创建人',width:'10%',align:'center',sortable:true}, 
           {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
           {field:'lastUpdateUser',title:'最后更新人',width:'10%',align:'center',sortable:true}, 
           {field:'lastUpdateTime',title:'最后更新时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail} 
 ]];

