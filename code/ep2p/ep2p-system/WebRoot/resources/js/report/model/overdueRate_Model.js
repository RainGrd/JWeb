var overdueRate_model = [[    
    	        {field:'type',title:'类别',width:'10%',align:'center',sortable:true,formatter:typeFormart},    
    	        {field:'overdueNum',title:'期数',width:'10%',align:'center',sortable:true},    
    	        {field:'overdueAmt',title:'逾期金额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'amtProportion',title:'金额占比（%）',width:'10%',align:'center',sortable:true,formatter:amtProportionFormart}, 
    	        {field:'numProportion',title:'期数占比（%）',width:'10%',align:'center',sortable:true,formatter:amtProportionFormart}
    	    ]];
/**
 * 类别格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function typeFormart(value,row,index){
	if(value == null){
		return '';
	}
	if(value!='90' && value!=''){
		return value+"天";
	}
	if(value == '90'){
		return value+'天以上';
	}
	return "";
}

/**
 * 金额占比格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function amtProportionFormart(value,row,index){
	if(value!='' && value!=null){
		return value+"%";
	}
	return "";
}