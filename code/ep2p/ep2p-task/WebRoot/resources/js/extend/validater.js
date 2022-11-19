//扩展easyui表单的验证
	$.extend($.fn.validatebox.defaults.rules, {
	    //数值
	    number: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^\d+(\.\d+)?$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的数值!'
	    },
	    //整数
	    integer: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^-?[1-9]\d*$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的整数!'
	    },
	    //下拉框必选
	    selrequired: {//value值为文本框中的值
	        validator: function (value) {
	            return value !='--请选择--';
	        },
	        message: '请选择数据!'
	    },
	    //手机号码
	    telephone: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^1[3|4|5|8|9]\d{9}$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的手机号码!'
	    },
	    //移动电话
	    mobile: {//value值为文本框中的值
	    	validator: function (value) {
	    		var reg = /^(\d{3,4})?-?\d{7,8}$/;
	    		return reg.test(value);
	    	},
	    	message: '请输入正确的移动电话!'
	    },
	  //移动电话
	    phone: {//value值为文本框中的值
	    	validator: function (value) {
	    		var reg = /^(\d{3,4})?-?\d{7,8}$/;
	    		var reg2 = /^1[3|4|5|8|9]\d{9}$/;
	    		return (reg.test(value)||reg2.test(value));
	    	},
	    	message: '请输入正确的电话号码!'
	    },
	    //邮箱
	    email: {//value值为文本框中的值
	    	validator: function (value) {
	    		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    		return reg.test(value);
	    	},
	    	message: '请输入正确的邮箱号码!'
	    },
	    
	  //证件号码验证
	    certNumber: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^1[3|4|5|8|9]\d{9}$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的证件号码!'
	    },
	    //传真号码验证
	    faxNumber: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^1[3|4|5|8|9]\d{9}$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的传真号码!'
	    },
	    //
	    char: {//value值为文本框中的值
	    	validator: function (value) {
	            if (value) {
	                var sz = /^[a-z]*$/; 
	                if (sz.test(value)) { return true;}
	                else {return false;}
	            }else {return true;}
	        },
	        message: '必须是字母!'
	    },
	  //国内邮编验证
	    zipcode: {
	        validator: function (value) {
	            var reg = /^[1-9]\d{5}$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的邮编号码！'
	    },
	  //匹配中文 数字 字母 下划线  
	    cleanSpelChar: {
	        validator: function (value) {
	            var reg = /^[\w\u4e00-\u9fa5]+$/gi;
	            return reg.test(value);
	        },
	        message: '不允许输入特殊验证！'
	    },
	    //验证汉字
	    CHS: {
	        validator: function (value) {//param的值为[]中值
	            if(value){
	            	var sz = /^[\u0391-\uFFE5]+$/;
	            	if(sz.test(value)){
	            		return true;
	            	}else{
	            		return false;
	            	}
	            }else{
	            	return true;
	            }
	        },
	        message: '此处必须是汉字!'
	    },
	  //数字 字母 下划线  
	    checkUsername: {  
	        validator: function(value){
	        	if (value) {
	        		var reg = /^\w+$/;
	        		if(reg.test(value)){
	        			var regs = /^\S{6,20}$/;//校验字符长度为6到20
	        			return  regs.test(value);
	        		}else{
	        			return false;
	        		}
	        	} else {
        			return false;
	        	}
	        },    
	        message: '只能输入6~20个数字、字母或者下划线！'   
	    },
	    checkSelectedValue: {    
	        validator: function(value){
	        	if (value == '-- 请选择 --'||value == '---请选择---'||value == '') {
        			return false;
	        	} else {
	        		return true;
	        	}
	        },    
	        message: '请选择一条数据！'   
	    },
	    trimValue: {    
	        validator: function(value,param){
	        	if ($.trim(value)=='') {
        			return false;
	        	} else {
	        		if(value==$('input[name="'+$(param[0]).attr('id')+'"]').val()){
	        			return true;
	        		}
	        		$('input[name="'+$(param[0]).attr('id')+'"]').val($.trim(value));
	        		$(param[0]).textbox('setValue',$.trim(value));
	        		return true;
	        	}
	        },    
	        message: '不能只输入空格'   
	    },
	    doctypevalid:{
	    	validator: function(value,param){
	    		if ($.trim(value)=='') {
        			return false;
	        	} else {
	        		var extype = value.slice(value.lastIndexOf(".")+1).toLowerCase();
	        		if ("doc" == extype || "docx" == extype ) {   
	        			return true;
	        		}else{
	        			return false;
	        		}
	        	}
	    	},
	    	message:"只能上传格式为doc和docx的文件"
	    }
	});