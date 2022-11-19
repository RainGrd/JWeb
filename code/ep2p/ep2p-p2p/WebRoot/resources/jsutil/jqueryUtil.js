(function ($){
	
	//全局系统对象
    window['jqueryUtil'] = {};
    
	/**
	 * 表单序列化
	 */
	jqueryUtil.serializeObject = function(form,falg) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + (this['value']==''?'':this['value']);
			} else {
				if(falg){
					var value = this['value'];
					if(value && !value=='' && !value==""){
						o[this['name']] = value;
					}
				}else{
					o[this['name']] = this['value']==''?'':this['value'];
				}
			}
		});
		return o;
	};
		
})(jQuery);
