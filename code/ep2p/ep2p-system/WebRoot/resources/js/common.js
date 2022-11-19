/**
 * 清除datagrid的选中数据
 * @param datagrid datagrid ID 
 * @anthor zhangyu
 */
function clearSelectRows(datagrid){
	// 清除所有选择的行。
	$(datagrid).datagrid("clearSelections");
	// 清除所有勾选的行。
	$(datagrid).datagrid("clearChecked");
}

/**
 * 刷新指定的title列表
 * @param title tba的名称
 */
function reloadTabGrid(title){
	if (parent.$("#centerFrame" ).tabs('exists', title)) {
		parent.$('#centerFrame').tabs('select' , title);
        window.top.reload_Abnormal_Monitor.call();
   }
}

/**
 * 公用js类
 */
var common = {
	/**
	 * 禁用或者启用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]  
	 * @param formId 表单ID
	 * @param isDisabled boolean值
	 */
	disableOrEnabledForm : function(formId,isDisabled){
		var attr="disable";  
	    if(!isDisabled){  
	       attr="enable";  
	    }  
	    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
	    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
	    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
	    $("form[id='"+formId+"'] a").attr("disabled",isDisabled);  
	    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
	    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
	      
	    //禁用jquery easyui中的下拉选（使用input生成的combox）  
	    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
	        if (this.id) {alert("input"+this.id);  
	            $("#" + this.id).combobox(attr);  
	        }  
	    });  
	    +
	    //禁用jquery easyui中的下拉选（使用select生成的combox）  
	    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
	        if (this.id) {  
	        alert(this.id);  
	            $("#" + this.id).combobox(attr);  
	        }  
	    });  
	      
	    //禁用jquery easyui中的日期组件dataBox  
	    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
	        if (this.id) {  
	        alert(this.id)  
	            $("#" + this.id).datebox(attr);  
	        }  
	    });  
	},
	/**
	 * 只读或者启用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]  
	 * @param formId 表单ID
	 * @param isReadonly boolean值
	 */
	readonlyOrEnabledForm : function(formId,isReadonly){
		var attr="readonly";  
	    if(!isReadonly){  
	       attr="enable";  
	    }  
	    $("#" + formId + " :input").attr("readonly", "readonly"); //对form里面的禁用
	    $("form[id='"+formId+"'] :text").attr("readonly",isReadonly);  
	    $("form[id='"+formId+"'] :text").attr("readonly",isReadonly);  
	    $("form[id='"+formId+"'] textarea").attr("readonly",isReadonly);  
	    $("form[id='"+formId+"'] select").attr("readonly",isReadonly);  
	    $("form[id='"+formId+"'] :radio").attr("readonly",isReadonly);  
	    $("form[id='"+formId+"'] :checkbox").attr("readonly",isReadonly);  
	    /*$("form[id='"+formId+"'] .easyui-combobox").combobox({disabled: isReadonly });  
	    $("form[id='"+formId+"'] .easyui-combobox").combobox({disabled: isReadonly });
	    $("form[id='"+formId+"'] .easyui-linkbutton").linkbutton('disable');*/

	    //禁用jquery easyui中的下拉选（使用input生成的combox）  
	    /*$("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
	        if (this.id) {
	        	alert("input"+this.id);  
	            $("#" + this.id).combobox(attr);  
	        }  
	    });*/ 
	    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
	        if (this.id) {
	        	alert("input"+this.id);  
	            $("#" + this.id).combobox(attr);  
	        }  
	    });
	    
	    //禁用jquery easyui中的下拉选（使用select生成的combox）  
	    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
	        if (this.id) {  
	        	alert(this.id);  
	            $("#" + this.id).combobox(attr);  
	        }  
	    }); 
	    
	    //禁用jquery easyui中的日期组件dataBox  
	    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
	        if (this.id) {  
	        	alert(this.id)  
	            $("#" + this.id).datebox(attr);  
	        }  
	    });  
	}, dictionaryFilter:function(data){
		return data.data;
	},
	/**
	 * 创建单选按钮
	 * @param parentElementId 上级元素ID
	 * @param data 数据集合
	 */
	createRadioButtons:function(parentElementId, elementId, data){
		var htmlStr = "";
		//下面使用each进 行遍历  
	    $.each(data,function(n,element) { 
	    	if(element.dictContCode==1){
	    		htmlStr +='<input type="radio" checked="checked" id="'+elementId+'" name="'+elementId+'" value="'+element.dictContCode+'" />'+element.dictContName;
	    	}else{
	    		htmlStr +='<input type="radio" id="'+elementId+'" name="'+elementId+'" value="'+element.dictContCode+'" />'+element.dictContName;
	    	}
	     });  
	     $("#"+parentElementId).prepend(htmlStr);
	},
	/** 
	 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
	 * @param num 数值(Number或者String) 
	 * @return 金额格式的字符串,如'1,234,567.45' 
	 * @type String 
	 */  
	formatCurrency:function(num) {  
		if(num == null || num == ''){
			return '';
		}
	    num = num.toString().replace(/\$|\,/g,'');  
	    if(isNaN(num))  
	        num = "0";  
	    sign = (num == (num = Math.abs(num)));  
	    num = Math.floor(num*100+0.50000000001);  
	    cents = num%100;  
	    num = Math.floor(num/100).toString();  
	    if(cents<10)  
	    cents = "0" + cents;  
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	    num = num.substring(0,num.length-(4*i+3))+','+  
	    num.substring(num.length-(4*i+3));  
	    return (((sign)?'':'-') + num + '.' + cents);  
	},
	/**
	 * 获取格式化客户名 
	 * @param value 
	 * @param row
	 * @param index
	 */
	getCustomerName:function(value,row,index){
		var result = "";
		if(row.customer){
			if(row.customer.customerName != '合计'){
				var customerName = row.customer.customerName;
				if(null == customerName){
					customerName = "";
				}
				result = '<a onclick="common.viewCusDetail(\''+row.customerId+'\')" href="javascript:void(0);">'+customerName+'</a>';
			}else{
				return row.customer.customerName;
			}
		}
		return result;
	},
	getCustomerName2:function(value,row,index){
		var result = "";
		if(value != '合计'){
			result = '<a onclick="common.viewCusDetail(\''+row.customerId+'\')" href="javascript:void(0);">'+value+'</a>';
		}
		return result;
	},
	/**
	 * 获取真实姓名 
	 * @param value 
	 * @param row
	 * @param index
	 */
	getSName:function(value,row,index){
		if(row.customer){
			return row.customer.sname;
		}
		return "";
	},
	/**
	 * 获取手机号码
	 * @param value 
	 * @param row
	 * @param index
	 */
	getPhoneNo:function(value,row,index){
		if(row.customer){
			return row.customer.phoneNo;
		}
		return "";
	},
	
	// 查看客户资料
	viewCusDetail:function(pid){
		var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+pid;
		childLayout_addTab(path,'查看客户资料');
	},
	// 生成临时编号
	generatNoRuleTemNoDateTime:function(rulePrefix,ruleType,seqLength){
		// 临时编号
		var no = "";
		// 获取临时单号,并显示在页面
		$.ajax( {
			type : "GET",
			url : BASE_PATH+"sysCreateCodeRuleController/generatNoRuleTemNoDateTime.shtml?rulePrefix="+rulePrefix+"&ruleType="+ruleType+"&seqLength="+seqLength,
			async : false,
			dataType : "json",
			success : function(data) {
				// 返回临时活动单号
				no = data.message;
			}
		}); 
		return no;
	}

};

/**
 * js 去前后空格
 * @returns  
 * @anthor zhangyu 
 */
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
