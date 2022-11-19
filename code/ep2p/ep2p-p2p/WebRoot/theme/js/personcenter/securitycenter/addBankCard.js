//添加银行卡
var addBankCard = {
	//添加银行卡事件
	saveBankCard:function(){
		debugger;
		var flag=true;
		var custId = $("#customer_id").val();
		var userName = $(".userName").html();//客户名称
		var belongingBank = $("#belongingBank").val();//开户银行
		var bankcardNo = $("#bankcardNo").val();//银行卡卡号
		var belongingProvince = $("#nowProvince").val();//开户行所在省份
		var belongingCity = $("#nowCity").val();//开户行城市
		var openAddress = $("#openAddress").val();//开户支行名称
		if(belongingProvince=="请选择"){
			layer.msg('开户行所在省份不能为空!', {icon: 5});
			flag=false;
			$("#nowProvince").focus();
		}
		if(belongingCity=="请选择"){
			layer.msg('开户行所在城市不能为空!', {icon: 5});
			flag=false;
			$("#nowCity").focus();
		}
		if(openAddress==null || openAddress==""){
			layer.msg('开户支行名称不能为空!', {icon: 5});
			flag=false;
			$("#openAddress").focus();
		}
		if(bankcardNo.length < 13 || bankcardNo.length> 19){
			layer.msg('请输入正确的银行卡卡号!', {icon: 5});
			flag=false;
			$("#bankcardNo").focus();
		}
		var url = BASE_PATH + "securityCenter/bankController/saveBankInfo.shtml";
		if(flag){
			//验证是否有绑定过银行卡，如果大于1 提示你已绑定过银行卡
			var ss = addBankCard.validataBankNumber();
			if(ss > 0){
				layer.msg('只能绑定一张银行卡!', {icon: 5});
			}else{
				$.ajax({
					type: "POST",
			    	url : url,
			    	data:{"bankcardName":userName,"customerId":custId,"belongingBank":belongingBank,"bankcardNo":bankcardNo,
			    	  	  "belongingProvince":belongingProvince,"belongingCity":belongingCity, "openAddress":openAddress},
					dataType: "json",
				    success: function(data){
				    	if(data.flag >0){
				    		//刷新父页面
				    		parent.location.reload()
				    	}else{
				    		layer.msg('添加银行卡失败,请联系管理员!', {icon: 5});
				    	}
					}
				});
			}
		}
	},
	
	//输入银行卡号带出开户行，所在地等信息
	validateBankCard:function(){
		var card = $("#bankcardNo").val();//银行卡卡号
		if(card==null || card==""){
			layer.msg('请输入银行卡!', {icon: 5});
			$("#bankcardNo").focus();
		}else if(bankcardNo.length < 13 || bankcardNo.length> 19){
			layer.msg('请输入正确的银行卡卡号!', {icon: 5});
			$("#bankcardNo").focus();
		}
		else{
		var httpUrl = 'http://apis.haoservice.com/lifeservice/bankcard/query';
		var url = BASE_PATH + "securityCenter/bankController/validateBankCard.shtml";
		$.ajax({
			type: "POST",
	    	url : url,
	    	data:{"card":card},
			dataType: "json",
		    success: function(data){
		    	if(data.data.belongingBank ==null || data.data.belongingBank==""){
		    		layer.msg('银行卡号有误,请重新输入!', {icon: 5});
		    	}else{
		    		$("#belongingBank").val(data.data.belongingBank);//省
		    		$("#nowProvince").val(data.data.belongingProvince);//市
		    		var nowProvince = data.data.belongingProvince;
					$("#nowCity").empty();
					var cityArr = [];
					// 获取选中省下的城市值
					for(var i=0;i<arrCity.length;i++){
						if(arrCity[i].name == nowProvince){
							cityArr = arrCity[i].sub;
							break;
						}
					}
					// 将城市信息加载到数组中
					for(var i=0;i<cityArr.length;i++){
					    $("#nowCity").append("<option value='"+cityArr[i].name+"'>"+cityArr[i].name+"</option>");
					}
		    		$("#nowCity").val(data.data.belongingCity);
		    	}
		    	
			}
		});
		}
	},
	//验证是否有绑定过银行卡，如果大于1 提示你已绑定过银行卡
	validataBankNumber:function(){
		var number = 0;
		var url = BASE_PATH + "securityCenter/bankController/selectBankListByCusId.shtml";
		$.ajax({
				type : "POST",
				url : url,
				data : {
					data : ""
				},
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.message.status == 200) {
						// 绑定了银行卡
						if (data.count > 0) {
							number = data.count;
						}
					}
				}
			});
					return number;
	}
}


