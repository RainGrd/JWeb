/**
 * 专属ESCF（e生财富）工具类
 */
var escfutil = {
		/*单选效果*/
		radios_:function(a,b){
			    $(a +" .radio_s").click(function(){
				$(a +" "+b).removeClass("c2980b9");
				$(a +" "+b).eq($(this).index(a +" .radio_s")).addClass("c2980b9");
				$(a +" .radio_s").html("");
				$(a +" .radio_s").attr("val","0");
				$(this).html('<img src="'+ basePath + 'theme/default/images/radio_x.png" class="block" />');
				$(this).attr("val","1");
			})
		},
		/*单选效果 用于登录找回密码模块*/
		radios_login:function(a,b){
			    $(a +" .radio_s").click(function(){
				$(a +" "+b).removeClass("c2980b9");
				$(a +" "+b).eq($(this).index(a +" .radio_s")).addClass("c2980b9");
				$(a +" .radio_s").html("");
//				$(a +" .radio_s").attr("val","0");
				$(this).html('<img src="'+ basePath + 'theme/default/images/radio_x.png" class="block" />');
//				$(this).attr("val","1");
			})
		},
		/*勾选效果*/
		gougou:function(a,b){
			//$(a +" .gou_s").attr("val","0");
			//$(b).attr("val","0");
			$(a +" .gou_s").click(function(){
				if($(this).attr("val")==1){
					$(this).html('');
					$(this).attr("val","0");
					$(this).parent().removeClass("c2980b9");
				}else{
					$(this).html('<img src="'+ basePath + 'theme/default/images/gou_b.png" class="block" />');
					$(this).attr("val","1");
					$(this).parent().addClass("c2980b9");
				}
			});
			$(b).toggle(
				  function () {
					  $(this).html('<img src="../images/gou_b.png" class="block" />');
					  $(a +" .gou_s").html('<img src="'+ basePath + 'theme/default/images/gou_b.png" class="block" />');
					  $(a +" .gou_s").attr("val","1");
					  $(b).attr("val","1");
				  },
				  function () {
					  $(this).html('');
					  $(a +" .gou_s").html('');
					  $(b).attr("val","0");
				  }
			);
		},
		/**
		 * radio 单选效果调用,提交父容器参数
		 * @param a
		 */
		se : function (a){
			$(a).click(function(){
				$(a).html("");
				$(a).removeClass("cd5d5d5").addClass("colorDarkBlue");
			});
		},
		/**
		 * 将数值四舍五入(保留2位小数)后格式化成金额形式
		 * @param num 数值(Number或者String)
		 * @return 金额格式的字符串,如'1,234,567.45'
		 * @type String
		 */
		formatCurrency:function(num,fchar) {
			var vchar = (fchar == null || fchar == '') ? ',' : fchar;
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
		    num = num.substring(0,num.length-(4*i+3)) + vchar +
		    num.substring(num.length-(4*i+3));
		    return (((sign)?'':'-') + num + '.' + cents);
		},
		 
		/**
		 * 将数值四舍五入(保留1位小数)后格式化成金额形式
		 * @param num 数值(Number或者String)
		 * @return 金额格式的字符串,如'1,234,567.4'
		 * @type String
		 */
		formatCurrencyTenThou:function(num,fchar) {
			var vchar = (fchar == null || fchar == '') ? ',' : fchar;
		    num = num.toString().replace(/\$|\,/g,'');
		    if(isNaN(num))
		    num = "0";
		    sign = (num == (num = Math.abs(num)));
		    num = Math.floor(num*10+0.50000000001);
		    cents = num%10;
		    num = Math.floor(num/10).toString();
		    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		    num = num.substring(0,num.length-(4*i+3))+ vchar +
		    num.substring(num.length-(4*i+3));
		    return (((sign)?'':'-') + num + '.' + cents);
		},
		/**
		 * 根据数据字典编码查询数据字典值
		 * @param dictCode 数据字典编码
		 * @param dictContCode  数据字典编码内容编码
		 * @return 系统数据字典内容名称
		 * @type String 
		 */
		formatDictionaryContentName:function(dictCode,dictContCode){
			var dictContName="";
			// 查询URL
			var url = BASE_PATH + "personcenter/sysDistionaryContentController/selectDictionaryContentName.shtml?dictCode="+dictCode+"&dictContCode="+dictContCode;
			$.ajax({
				type: "POST",
		    	url : url,
		    	async : false,// 必须设置为同步，否者Ajax还没有执行完，就直接return了，那样就会返回""
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		dictContName = data.dictContName;
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			return dictContName;
		}
};