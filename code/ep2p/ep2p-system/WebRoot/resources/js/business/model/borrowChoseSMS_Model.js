/**
 * 借后管理中的信息选中框
 */
var borrowAftCSmsManage_model = [[    
       	        {field:'pid',title:'选择', formatter: radioCheckFormat},
    	        {field:'tempCode',title:'短信编码',width:'15%',align:'center',sortable:true},    
    	        {field:'smsTempName',title:'短信名称',width:'15%',align:'center',sortable:true},    
    	        {field:'smsContent',title:'短信内容',width:'25%',align:'center',sortable:true}, 
    	        {field:'createUser',title:'创建人',width:'15%',align:'center',sortable:true}, 
    	        {field:'createTime',title:'创建时间',width:'18%',align:'center',sortable:true,formatter:convertDate} 
    	    ]];


//单选框格式化
function radioCheckFormat(value, rowData, rowIndex){
    return '<input type="radio" name="selectRadio" id="selectRadio"' + rowIndex + '    value="' + rowData.oid + '" />';
}