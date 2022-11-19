/**
 * 银行卡管理列表
 * @param vos
 */
function htmlBankList(vos){
	var html = "";
	if(vos != null && vos.length>0){
		for(var i=0;i<vos.length;i++){
			var pid = vos[i].pid;
			var bankcardNo = vos[i].bankcardNo;
			html += '<span class="ka_g_list_ka mr7 mb25 ">'+
				'<img class="ml10 mt13 yin_h_ka" alt="'+vos[i].belongingBank+'"/>'+
				'<i class="ka_g_list_i i_ inline_block size14 tc ffffff">信用卡</i>'+
				'<p class="ka_g_list_p">'+
					'<span class="ml15 fl_">尾号'+bankcardNo+
					'</span><span class="ml15 fr_ c2980b9 mr20 jie_ka_js cus_p" onclick="unbundling('+"'"+bankcardNo+"'"+','+"'"+pid+"'"+');">解绑</span>'+
				'</p>'+
			'</span>';
		}
	}
	html += '<span class="ka_g_list_ka_k mr7 mb25 tan_ka_js" onclick="openAddBankCard()">'+
	'<img src="/ep2p/theme/default/images/yin_3.png" class="ml10 mt13"/>'+
	'<p class="ka_g_list_ka_kp size14 tc">添加银行卡</p>'+
	'</span>';
	$("#pay_bank_list_div").html(html);
	//添加银行图片,给银行图片添加class ="yin_h_ka" ,自动判断 alt的值 然后自动填充图片
	var y_;
	var src_;
	for(var i=0;i<$(".yin_h_ka").length;i++){
		y_ = $(".yin_h_ka").eq(i).attr("alt");
		$(".yin_h_ka").eq(i).attr("src",'/ep2p/theme/default/images/bank'+f_(y_)+'.png');
	}
}

function loadData(){
	var url = BASE_PATH + "securityCenter/bankController/selectBankListByCusId.shtml";
	$.ajax({
		type: "POST",
    	url : url,
    	data:{data:""},
		dataType: "json",
	    success: function(data){
	    	if(data.message.status ==200){
	    		htmlBankList(data.data);
	    	}else{
	    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
	    	}
		}
	});
}

/**
 * 银行卡解绑事件
 * @param bankCardNo 银行卡号
 * @param pid
 */
function unbundling(bankCardNo,pid){
	//iframe层
	layer.open({
	    type: 2,
	    title: '',
	    shadeClose: true,
	    shade: 0.8,
	    offset: ['20%', '40%'],
	    area: ['600px', '30%'],
	    content: BASE_PATH+'securityCenter/bankController/toUnbundlingBankPage.shtml?bankCardNo='+bankCardNo+'&pid='+pid //iframe的url
	});
}

$(function(){
	loadData();
});