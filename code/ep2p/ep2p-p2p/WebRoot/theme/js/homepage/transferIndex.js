var TransferIndex = {
		initData:function(){
			$.ajax({
				type: "POST",
		    	url : BASE_PATH+"mybids/transferController/index/findTransferListData.shtml",
		    	data:{"pageSize":5,"pageIndex":0},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$("#transferCount").text(data.total);
			    		loadTransferDataHtml(data.vos);
			    	}
				}
			}); 
			
		}
		
}



/*将数据加载到页面*/
function loadTransferDataHtml(vos){
	if(vos!=null && vos.length>0){
		var html = "";
		for(var i=0;i<vos.length;i++){
			
			var option = '<td><a class="btn_samll_gray" href="javascript:void(0)" onclick="toTransferInfo(\''+vos[i].transferId+'\')">已转让</a></td>';
			if(vos[i].transferStatus=='1'){
				option = '<td><a class="btn_samll" href="javascript:void(0)" onclick="toTransferInfo(\''+vos[i].transferId+'\')">立即购买</a></td>';
			}
			
			var yearRate = vos[i].yearRate==null?0:parseFloat((vos[i].yearRate*100));
			var successAmount = vos[i].successAmount==null?0.00:accounting.formatNumber(vos[i].successAmount,2);
			var projectValue = vos[i].projectValue==null?0.00:accounting.formatNumber(vos[i].projectValue,2);
		
			html +='<tr>'+
			      '<td class="colororg">'+yearRate+'%</td>'+
		          '<td>'+vos[i].transferCode+vos[i].borrowName+'</td>'+
		          '<td>'+vos[i].timeRemaining+'个月</td>'+
		          '<td>￥'+projectValue+'</td>'+
		          '<td>￥'+successAmount+'</td>'+
		          option+
		        '</tr>';
		}
		$("#transferData").html(html);
	}
}

/*跳转到债权详情页面*/
function toTransferInfo(pid){
	window.location.href = BASE_PATH+"mybids/transferController/index/toTransferInfo.shtml?pid="+pid;
}
