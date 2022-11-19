var reg= new RegExp(/^(?!00)\d+(\.\d{1,2})?$/);
var rechargeOnline = {
		check:function(){
			var val = $("#availableAmount").val();
			if(!rechargeOnline.is_double(val) || parseFloat(val) <= 0){
				layer.tips('金额必须大于0.01,并且最多只能为两位小数', '#availableAmount');
				return false;
			}else{
				var result = true;
				$.ajax({
					type: "POST",
					async: false,
					data : "",
			    	url : BASE_PATH + "recharge/userRechargeController/checkIsAttestation.shtml",
					dataType: "json",
				    success: function(data){ 
				    	if (data.message.status == 200) {
				    		if("2"==data.isAttestation){
				    			result = true;;
				    		}else{
				    			layer.alert('请先完成实名认证！');
				    			result = false;
				    		}
				    	}else{
				    		result = false;
				    	}
					}
				});
				return result;
			}
		},
		is_numeric:function(val){
			if(rechargeOnline.is_null(val) || isNaN(val)){
				return false;
			}
			return true;
		},
		is_null:function(obj){
			if(typeof(obj) == 'undefined' || obj == null || obj == ''){
				return true;
			}
			return false;
		},
		is_double:function(val){
			if(!reg.test(val)){
				return false;
			}
			return true;
		},
		loadData:function(val){
			$.ajax({
				type: "POST",
				async: false,
				data : "",
		    	url : BASE_PATH + "recharge/userRechargeController/checkIsAttestation.shtml",
				dataType: "json",
			    success: function(data){ 
			    	if (data.message.status == 200) {
			    		if("2"!=data.isAttestation){
			    			layer.alert('完成实名认证才能充值,请先完成实名认证！');
			    		}
			    	}
				}
			});
		}
}

$(function(){
	rechargeOnline.loadData();
});
