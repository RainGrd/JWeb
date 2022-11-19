/**
 * 公共js类
 */
var common = {
		/**
		 * 表单清除
		 * @param form
		 */
		clearForm:function(form){
			$(form).find(':input').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
		},
		/** 
		 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
		 * @param num 数值(Number或者String) 
		 * @return 金额格式的字符串,如'1,234,567.45' 
		 * @type String 
		 */  
		formatCurrency:function(num) {  
			if(num == null || num == ''){
				return '-';
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
		    var result =  (((sign)?'':'-') + num + '.' + cents);  
		    return result;  
		},
		// 加载form 表单数据
		loadData:function (formName,obj){
			  var key,value,tagName,type,arr;
			  for(x in obj){
			    key = x;
			    value = obj[x];
			    $("#"+formName+" [name='"+key+"'],[name='"+key+"[]']").each(function(){
			      tagName = $(this)[0].tagName;
			      type = $(this).attr('type');
			      if(tagName=='INPUT'){
			        if(type=='radio'){
			          $(this).attr('checked',$(this).val()==value);
			        }else if(type=='checkbox'){
			          arr = value.split(',');
			          for(var i =0;i<arr.length;i++){
			            if($(this).val()==arr[i]){
			              $(this).attr('checked',true);
			              break;
			            }
			          }
			        }else{
			          $(this).val(value);
			        }
			      }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
			        $(this).val(value);
			      }
			    });
			  }
		},
		// 切换验证码
		changeVerifyCode:function(){
			$("#kaptchaImg").attr('src', BASE_PATH+'/kaptcha.jpg?' + Math.floor(Math.random()*100) );
		},
		// 验证验证码输入是否正确
		validateKaptcha:function(kaptcha){
			var result = false;
			if(!kaptcha){
				kaptcha = $("#kaptcha").val();
			}
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"/login/userController/kaptchaValidate.shtml",
		    	data:{"kaptcha":kaptcha},
				dataType: "json",
				async:false, 
			    success: function(data){
			    	if(data.message.status ==200){
			    		result = data.result;
			    	}else{
			    		layer.msg('验证验证码失败啦!', {icon: 5});
			    	}
				}
			});
			return result;
		},
		// 手机号码加密显示
		phoneNoEncryption:function(phoneNo){
			var phoneFirst = phoneNo.substring(0,3);
			var phoneLast = phoneNo.substring(phoneNo.length-4,phoneNo.length);
			return phoneFirst+"****"+phoneLast;
		},
		// 当前窗口跳转到首页
		toIndexPage:function(){
			window.location.href=BASE_PATH+"index/homepController/toIndex.shtml"; 
		},
		// 当前窗口跳转到登录页面
		toLoginPage:function(){
			window.location.href=BASE_PATH+"login/loginController/toLogin.shtml"; 
		},
		
		/**
		 * 发送短信验证码
		 * @param phoneNo 手机号码
		 * @param model	   所属模块
		 * @returns {String} // 1 超过限制次数 2 发送成功
		 */
		sendSmsCode:function(phoneNo,model,tempKey){
			var result = "";
			$.ajax( {
				type : "POST",
				url : BASE_PATH+"sms/sysSmsCodeController/sendSmsCode.shtml?mobiles="+phoneNo+"&model="+model+"&tempKey="+tempKey,
				async : false,
				dataType : "json",
				success : function(data) {
					if(data.message.status == 200 ){
						//1 超过限制条数 2 发送成功
						result = data.result;
					}else{
						layer.msg('发送短信验证码失败啦!', {icon: 5});
					}
				}
			}); 
			return result;
		},

		
		/**
		 * 发送语音短信验证码
		 * @param phoneNo 手机号码
		 * @returns {String} //  1 验证码已过期 2 发送成功
		 */
		sendScheduledSMS:function(phoneNo){
			var result = "";
			$.ajax( {
				type : "POST",
				url : BASE_PATH+"sms/sysSmsCodeController/sendScheduledSMS.shtml?mobiles="+phoneNo,
				async : false,
				dataType : "json",
				success : function(data) {
					if(data.message.status == 200 ){
						result = data.result;
					}else{
						layer.msg('发送语音短信验证码失败啦!', {icon: 5});
					}
				}
			}); 
			return result;
		},
		
		/**
		 * 验证手机验证码
		 * @param phoneNo 手机号码
		 * @param code	   验证码
		 * @returns {String}  // 1 验证码已过期 2 验证码错误 3 验证通过
		 */
		validateSmsCode:function(phoneNo,code){
			var result = "";
			$.ajax( {
				type : "POST",
				url : BASE_PATH+"sms/sysSmsCodeController/validateSmsCode.shtml?mobiles="+phoneNo+"&code="+code,
				async : false,
				dataType : "json",
				success : function(data) {
					if(data.message.status == 200 ){
						result = data.result;
					}else{
						layer.msg('手机验证码失败啦!', {icon: 5});
					}
				}
			}); 
			return result;
		},
		/**
		 * 根据系统参数key值获取value
		 * @param paramKey
		 * @returns {String}
		 */
		getParamValueByKey:function(paramKey){
			var result = 0;
			$.ajax({
				type : "POST",
				url : BASE_PATH+"sysParam/SysParamContentController/getParamValueByKey.shtml?paramKey="+paramKey,
				async : false,
				dataType : "json",
				success : function(data) {
					if(data.message.status == 200 ){
						result = data.result;
					}else{
						layer.msg('获取系统参数值失败!', {icon: 5});
					}
				}
			}); 
			return result;
		}
		
}

/**
 * js 去前后空格
 * @returns  
 * @anthor zhangyu 
 */
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
