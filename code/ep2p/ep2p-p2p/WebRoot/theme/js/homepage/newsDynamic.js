/**
 * 首页-动态投资列表-主页
 */
var dynaInverstList = {
		
		//初始化页面
		loadData:function(){
			var url = BASE_PATH + "login/userController/selectNewDynamic.shtml";
			//加载最新动态
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{data:""},
				dataType: "json",
				async: false,
			    success: function(data){
			    	if(data.message.status ==200){
			    		dynaInverstList.newDynamic(data.data);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			var url1 = BASE_PATH + "login/userController/selectLatestReport.shtml";
			//加载媒体报道
			$.ajax({
				type: "POST",
		    	url : url1,
		    	data:{data:""},
				dataType: "json",
				async: false,
			    success: function(data){
			    	if(data.message.status ==200){
			    		dynaInverstList.meitiBD(data.data);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
			dynaInverstList.loadInverstList();//动态投资列表
		},
		//填充最新动态
		newDynamic:function(data){
			var str="";
			//更多的链接路径
//			var zxdtMorePath=BASE_PATH + 'about/aboutUsController/index/toAboutUsLeftList.shtml?webTag=ep2p_col_news_t_1&contentPid='+data[0].parentId;
			var zxdtMorePath=""
			$(".zxdt_more").attr("href",zxdtMorePath);
			var path = BASE_PATH+data[0].fileUrlImg
//			var path = BASE_PATH+data[0].fileUrl
			$(".cont_").html(data[0].content);
			$(".cont_").attr("href",path);
			$(".ttt_").attr("src",path);//
			$(".li_3").html(data[0].titleName);
			$(".li_3").attr("href",path1);
			if(data!=null && data.length>0){
				for(var i=1;i<4;i++){
					var path1=BASE_PATH+data[i].fileUrl;
					str +='<li>'+'<a href="'+path1+'">'+data[i].titleName+ '</a></li>'
			}
				$(".zxdt_li").html(str);
			}
		},
		//填充媒体报道
		meitiBD:function(data){
//			var mtbdMorePath=BASE_PATH + 'about/aboutUsController/index/toAboutUsLeftList.shtml?webTag=B002&contentPid='+data[0].parentId;
			var mtbdMorePath="";
			$(".mtbd_more").attr("href",mtbdMorePath);
			var str="";
			var path=BASE_PATH+data[0].fileUrlImg;
			$(".meitiBD").attr("src",path);//图片
			$(".mtbd_cont").attr("href",path);
			$(".mtbd_cont").html(data[0].content);//内容
			$(".mt_c").html(data[0].titleName);//标题
			$(".mt_c").attr("href",path);
			if(data!=null && data.length>0){
				for(var i=1;i<4;i++){
					var path1=BASE_PATH+data[i].fileUrl;
					str +='<li>'+'<a href="'+path1+'">'+data[i].titleName+ '</a></li>'
				}
				$(".mtbd_li").html(str);
			}
		},
		
		//动态投资列表
		loadInverstList:function(){
			var url1 = BASE_PATH + "index/homepController/selectInverstList.shtml";
			$.ajax({
				type: "POST",
		    	url : url1,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status ==200){
			    		dynaInverstList.inverstListDetailed(data.data);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		},
		
		//动态投资明细
		inverstListDetailed:function(data){
			var str = "";
			var customerName="";
			var path="";
			if(data!=null && data.length>0){
				for(var i=0;i<data.length;i++){
					var name = data[i].customerName;
					if(name!=null && name!=""){
						var myName = name.substr(2,3);
						customerName = name.replace(myName,"***");
					}
					if(data[i].imageUrl ==null || data[i].imageUrl == ""){
						path = BASE_PATH+'theme/default/images/tx.png';
					}else{
						path=BASE_PATH+data[i].imageUrl;//图片路径
					}
					if(customerName==null || customerName==""){
						var ph=data[i].phoneNo;
						var ph_1 = ph.substr(3,4);
						customerName = ph.replace(ph_1,"****");
					}
					str += '<li><div>'+
								'<span class="imgpr"><img src="'+path+'"></span>'+
								'<span class="fr"><a href="###" class="size18 coloreb">'+data[i].investmentAmount+'</a></span>'+
								'<span class="info_n" ><a href="###" class="size14">'+customerName+'</a></br>'+
								'<a href="###">十分钟前投资了</a></span>'+
						   '</div></li>'
				}
				$("#test_").html(str);
			}
			
		},
};


//加载文件
(function(){
	dynaInverstList.loadData();
})();