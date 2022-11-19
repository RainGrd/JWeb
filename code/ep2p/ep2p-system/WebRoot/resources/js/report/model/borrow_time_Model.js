var borrowTime_model = [[    
    	        {field:'timeType',title:'期限',width:'10%',align:'center',sortable:true,formatter:typeFormart},    
    	        {field:'borrowAmt',title:'金额',width:'10%',align:'center',sortable:true,formatter:formatCurrency},    
    	        {field:'borrowNum',title:'笔数',width:'10%',align:'center',sortable:true},
    	        {field:'amtProportion',title:'金额占比（%）',width:'10%',align:'center',sortable:true,formatter:amtProportionFormart}, 
    	        {field:'numProportion',title:'笔数占比（%）',width:'10%',align:'center',sortable:true,formatter:amtProportionFormart}
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
	if(value!='24' && value!=''){
		return value+"个月";
	}
	if(value == '24'){
		return value+'个月以上';
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
		return fixed(value)+"%";
	}
	if(value==0){
		return fixed(value)+"%";
	}
	return "";
}

function formatCurrency(value,row,index) {  
	if(value == null || value == ''){
		if(value == 0)
			return '0.00';
		return '';
	}
    value = value.toString().replace(/\$|\,/g,'');  
    if(isNaN(value))  
        value = "0";  
    sign = (value == (value = Math.abs(value)));  
    value = Math.floor(value*100+0.50000000001);  
    cents = value%100;  
    value = Math.floor(value/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((value.length-(1+i))/3); i++)  
    value = value.substring(0,value.length-(4*i+3))+','+  
    value.substring(value.length-(4*i+3));  
    return (((sign)?'':'-') + value + '.' + cents);  
}

/**
 * 转换为两位小数
 * @param num
 * @returns
 */
function fixed(num){
	  return num.toFixed(2);
	}