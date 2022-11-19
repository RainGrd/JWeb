/**
 * 个人中心-vip信息
 */

var vipInfo = {
		initfunc:function(){
		},
		// 加载数据
		loadData:function(){
			var v_temp = 0;
			var v_temp1=0;
			var v_temp2=0;
			var url = BASE_PATH + "userinfo/centerController/selectVipInfo.shtml";
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{data:""},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status){
			    		//当前vip级别 1,2,3,4,5
			    		var vipLevel = data.data.vipLevel;
			    		//当前等级开始经验值
			    		var experienceStart = data.data.experienceStart;
			    		//当前等级的最大经验值
			    		var thisLevelEmpiriVal = data.data.maxEmpiricalValueEnd;
			    		var serviceCharge = data.data.serviceCharge *100;
			    		//经验值
			    		var empiricaValue= data.data.empiricalValue;
			    		$(".level_").html('VIP'+vipLevel);
			    		$(".jyz").html(empiricaValue);
			    		if(data.data.expirationTime=="" ||data.data.expirationTime == null){
			    			$(".dqsj").html('');
			    		}else{
			    			$(".dqsj").html(data.data.expirationTime+'到期');//vip到期时间
			    		}
			    		//没有信息服务管理费
			    		if(serviceCharge=="0"){
			    			$(".info_servcie").html(10+'%');
			    		}else{
			    			$(".info_servcie").html(serviceCharge+'%');
			    		}
			    		//当前等级的最高值
			    		var totalEmpiriVal = Number(thisLevelEmpiriVal);
			    		if(vipLevel =='1'){
			    			//当前的经验值/当前等级最大的经验值*所占进度条的比例
				    		 v_temp = (Number(empiricaValue)-experienceStart)/totalEmpiriVal*200;
				    		 v_temp1 = Math.round(v_temp);
				    		 v_temp2 = Math.round(v_temp) - 65;
			    		}else if(vipLevel =='2'){
			    			//当前的经验值/当前等级最大的经验值*所占进度条的比例
				    		 v_temp = (Number(empiricaValue)-experienceStart)/totalEmpiriVal*200;
				    		 v_temp1 = Math.round(v_temp)+200;
				    		 v_temp2 = Math.round(v_temp)+200 - 65;
			    		}else if(vipLevel =='3'){
			    			
			    			//当前的经验值/当前等级最大的经验值*所占进度条的比例
				    		 v_temp = (Number(empiricaValue)-experienceStart)/totalEmpiriVal * 200;
				    		 v_temp1 = Math.round(v_temp)+400;
				    		 v_temp2 = Math.round(v_temp)+400 - 65;
			    		}else{
			    			//当前的经验值/当前等级最大的经验值*所占进度条的比例
				    		 v_temp = (Number(empiricaValue)/Number(totalEmpiriVal))*200;
				    		 v_temp1 = Math.round(v_temp)+600;
				    		 v_temp2 = Math.round(v_temp)+600 - 65;
			    		}
			    		//radio 单选效果调用,提交父容器参数
			    		//飞船 飞效果 <!--船的范围是 -65px-735px-->  进度条的范围是0-800px  数值注意换算
			    		$(".chuan").animate({ 
			    		   left: v_temp2
			    		 }, 2000);
			    		$(".jd_h").animate({ 
			    		   width: v_temp1
			    		 }, 2000); 
			    		
			    		
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			//加载栏目位的
			vipInfo.loadColum();
		},
	    
		//加载栏目位
		loadColum:function(){
			var url = BASE_PATH + "userinfo/centerController/selectVipCloum.shtml";
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status=="200"){
			    		$(".ss_").html(data.data.content);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		},
		
		//购买
		shopping:function(){
			//iframe层
			layer.open({
			    type: 2,
			    title: '',
			    shadeClose: true,
			    shade: 0.8,
			    offset: ['20%', '30%'],
			    area: ['700px', '40%'],
			    content: BASE_PATH+"userinfo/centerController/toShoppingPage.shtml" //iframe的url
			});
		},
};


//加载文件
(function(){
	//获取vip等级
   var vipLevel = $("#vip_level").val();
//   加载飞船的效果
    var z_=$(".vip_jd").attr("vl");
	var l_=	parseInt(z_)*8-65;
	if (parseInt(z_)>25) {
		$(".vip_sa").addClass("inblock");
		if (parseInt(z_)>50) {
			$(".vip_sb").addClass("inblock");
			if (parseInt(z_)>75) {
				$(".vip_sc").addClass("inline_block");
			}
		}
	}


   
})();