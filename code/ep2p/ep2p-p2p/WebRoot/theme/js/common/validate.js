/**
 * 验证表单空
 */
var validate = {
	// 验证form表单是否存在为空
	validateForm:function(formId){
		// 检查是否存在style_tis样式
		if($(".style_tis").length>0){
			return true;
		}
		
		var isInput = false;
		$("#"+formId+" input[required='true']").each(function(i){
			if(validate.contentVali($(this))){
				isInput = true;
			}
		});
		
		var isSelect = false;
		$("#"+formId+" select[required='true']").each(function(i){
			if(validate.contentVali($(this))){
				isSelect = true;
			}
		});
		
		var isTextareas = false;
		$("#"+formId+" textarea[required='true']").each(function(i){
			if(validate.contentVali($(this))){
				isTextareas = true;
			}
		});
		
		if( isInput == false && isSelect == false && isSelect ==false){
			return false;
		}
		
		return true;
	},
	contentVali:function(obj){
		var isNull =false;
		if(obj.val() == null || obj.val() == '' || obj.val() == '请选择' || obj.val() == '--请选择--'){
			isNull = true;
			obj.addClass("style_tis");
			obj.attr("va",obj.val());
			obj.val(obj.attr("missingMessage"));
			// 设置获得焦点事件
			obj.focus(function(){
				obj.val(obj.attr("va"));
		    });
			// 设置失去焦点事件
			obj.blur(function(){
				if(null != obj.val() && ''!= obj.val() && '请选择'!= obj.val() && '--请选择--'!= obj.val()){
					obj.removeClass("style_tis");
					obj.unbind("focus");
				}else{
					obj.addClass("style_tis");
					obj.attr("va",obj.val());
					obj.val(obj.attr("missingMessage"));
					// 设置获得焦点事件
					obj.focus(function(){
						obj.val(obj.attr("va"));
				    });
				}
			});
		}else{
			obj.removeClass("style_tis");
		}
		return isNull;
	}
}