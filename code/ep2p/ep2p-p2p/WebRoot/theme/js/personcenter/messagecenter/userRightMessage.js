/**
 * 个人中心-消息中心-主页
 */
/*勾选效果*/
function gougou(a,b){
	$(a +" .gou_s").attr("val","0");
	$(b).attr("val","0");
	$(a +" .gou_s").click(function(){
		if($(this).attr("val")==1){
			$(this).html('');
			$(this).attr("val","0");
			$(this).parent().removeClass("c2980b9");
		}else{
			$(this).html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
			$(this).attr("val","1");
			$(this).parent().addClass("c2980b9");
		}
	});
	 $(b).click(function(){
	    	if($(b).attr("val")=="0"){
	    		 $(this).html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
	    		  $(a +" .gou_s").html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
	    		  $(a +" .gou_s").attr("val","1");
	    		  $(b).attr("val","1");
	    	}else{
	    		 $(this).html('');
	    		  $(a +" .gou_s").html('');
	    		  $(a +" .gou_s").attr("val","0");
	    		  $(b).attr("val","0");
	    	}
	    })

}

var msgCenter = {
		initfunc:function(flag){
			if(flag==null || flag == ''){
				flag = "";
			}
			msgCenter.loadData(flag,null);
		},
		// 加载数据
		loadData:function(flag,elId){
			var pageElId='messageList';
			var params;
			if(elId!=null && elId != '' ){
				params = $("#mgsList_").serializeArray();//formId
				$("#"+pageElId).page( 'destroy' );
			}
			var beginTime = $("#beginTimes").val();
			var url = BASE_PATH + "userinfo/centerController/selectMsgList.shtml?beginTime="+beginTime;
			msgCenter.initPage(url,params,pageElId);
		},
		
		// 初始化分页
		initPage:function(url,params,pageElId){
			$("#"+pageElId).page({
				pageSize : 10,    // 测试设置为1
				showInfo : false,
				showJump : false,
				showPageSizes : false,
				loadFirstPage : true,  // 自动加载第一页
				remote : {
					url : url,
					params : params, //条件查询
					success : function(result, pageIndex) {
						if(result.message=='查询成功'){
				    	      msgCenter.mesList(result.data,pageIndex*10);
				    	}
					},
					pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
					pageSizeName : 'pageSize'//请求参数，每页数量
				}
			});
		},
		
	    //填充消息中心数据列表
		mesList:function(data,startNum){
			var html="";
			if(data!=null && data.length>0){
				for(var i =0;i<data.length;i++){
					var pid=data[i].pid;
					var is_read ="已读";
					var num=i+1;
					if(data[i].isRead ==0){
						is_read="未读";
						var nei_ = '<span class="du_msg " style="padding:6px; color:#34495e; font-size:14px; font-weight:blod; display: block;white-space: nowrap;overflow: hidden; text-overflow: ellipsis;" onclick="msgCenter.markRead('+i+');">'+data[i].sendContent+'</span>';
					}else{
						var nei_ = '<span class="du_msg c798383" style="padding:6px; color: #798383; display: block;white-space: nowrap;overflow: hidden; text-overflow: ellipsis;">'+data[i].sendContent+'</span>';
					}
					var createTime =  data[i].createTime;
					var creteTime1=createTime.slice(0,19);//去掉时间戳  .0
					html += '<tr class="ms_c_tb_">'+
								'<td class="js_gou_ce">'+
									'<span class="gou_s">'+
									 '</span>'+
									    '<input type="hidden" name="pid" id="msgPid" value="'+data[i].pid+'"/>'+
								'</td>'+
								'<td>'+(startNum+num)+'</td>'+
								'<td>'+creteTime1+'</td>'+
								'<td style="position: relative;">'+nei_+'</td>'+
								'<td class="pl30">'+is_read+'</td>'+
							'</tr>'
				}
				
			}
			$(".mesList").html(html);
			gougou(".ms_c_tabf",".gou_s_js");
		},
		//根据条件查消息
		selectMsgList:function(){
			var beginTime = $("#beginTimes").val();
			var endTime = $("#endTime").val();
			var msgConten = $("#msgContent").val();
			var url = BASE_PATH + "userinfo/centerController/selectMsgList.shtml";
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{"beginTime":beginTime,"endTime":endTime,"msgConten":msgConten},
				dataType: "json",
			    success: function(data){
			    	if(data.code ==200){
			    		msgCenter.mesList(data.data);
			    	}else{
			    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
			    	}
				}
			});
		},
		
		//标记为已读
		markRead:function(eq){
			var pid="";
			var flag=true;
			var v_=0;
			//点击未读的消息内容事件
			$(".ms_c_tabf .gou_s").eq(eq).html('<img src="/ep2p/theme/default/images/gou_b.png" class="block" />');
			$(".ms_c_tabf .gou_s").eq(eq).attr("val","1");
			
			for(var i=0;i<$(".ms_c_tabf .gou_s").length;i++){
			if($(".ms_c_tabf .gou_s").eq(i).attr("val")=="1"){//判断是否选中
				if(i==0){
					pid+=$(".ms_c_tabf .gou_s").eq(i).siblings("input").val();
				}else{
					pid+=","+$(".ms_c_tabf .gou_s").eq(i).siblings("input").val();
				}
				v_=1;
			}
			}
			if(v_=="0"){
					layer.msg('请选择数据!', {icon: 5});
					flag=false;
			}
			if(flag){
				var url = BASE_PATH + "userinfo/centerController/markRead.shtml";
				$.ajax({
					type: "POST",
			    	url : url,
			    	data:{"pid":pid},
					dataType: "json",
				    success: function(data){
			    	if(data.message.status ==200){
			    		if(data.count>0){
			    			layer.msg('操作成功!', {icon: 5});
			    			//msgCenter.loadData();
			    			location.reload(false);
			    		}
			    	}else{
			    		layer.msg('操作失败!', {icon: 5});
			    	}
				}
			});
			}
		},
};


//加载文件
(function(){
//	$("#beginTime").val(new Date());
	msgCenter.initfunc();
	msgCenter.loadData();
	
})();