$(function(){
	var contentPid = $("#contentPid").val();
	var webTag = $("#webTag").val() == "" || $("#webTag").val() == "undefined" ? "ep2p_col_helpCenterPage_t_1" : $("#webTag").val();
	$.ajax({
			type : "POST",
			url : BASE_PATH + "help/helpCenterController/index/toHelpCenterLeftList.shtml",
			data : {webTag:webTag,"contentPid":contentPid},
			dataType : "json",
			async:true,
			success : function(data) {
				  if(data.retCode != "200"){
					  layer.alert(data.retMessage);
					  return;
				  }
				  if(data.colContentList == null )return
				  var aboutUsListHtml ="";
				  var colContent ;
				  var contentPid = $("#contentPid").val();
				  if(contentPid != null && contentPid != ""){
					  checkInfo(null,contentPid,null);
				  }else{
					  if(data.colContentList.length > 0){
						  colContent = data.colContentList[0];
						  checkInfo(colContent.isLowerLevel,colContent.pid,colContent.fileUrl);
					  } 
				  } 
				  for(var i = 0 ; i<data.colContentList.length ;i++){
					  var colContent = data.colContentList[i];
					  aboutUsListHtml+='<div class="help_list pos-r" onclick=checkInfo("'+colContent.isLowerLevel+'","'+colContent.pid+'","'+colContent.fileUrl+'") >'+ 
						'<span class="bg297FB9 help_list_o ">'+colContent.titleName+'</span> '+ 
					'</div> '
				  }
				 $("#aboutusList").html(aboutUsListHtml);
			},
			error : function() {
				layer.alert("查询二级菜单异常!"); 
			}
		});
})



/**
 * 点击查看详情
 * @param isLowerLevel 是否存在下一级
 * @param contentPid  pid
 */
function checkInfo(isLowerLevel,contentPid,url){
	if(isLowerLevel != "null"){
		$("#isLowerLevel").val(isLowerLevel);
		$("#contentPid").val(contentPid);
		$("#url").val(url);
	} 
	$("#page").page('destroy');
	$("#page").page({ 
		pageSize : 10,    // 测试设置为1
		showInfo : false,
		showJump : false,
		showPageSizes : false,
		loadFirstPage : true,  // 自动加载第一页
		remote : {
			url : BASE_PATH + "about/aboutUsController/index/selectColumnContentByParentId.shtml",
			params : {contentPid:contentPid},
			success : function(result, pageIndex) {
				var html="";
				for(var i =0;i< result.colContentList.length;i++){ 
					html+=  '<div class="list_actie" >'+
							'	<p class="colorDarkBlue size16 lh75" onclick=checkInfoLow("'+result.colContentList[i].pid+'","'+result.colContentList[i].fileUrl+'")>'+
							'	<i class="i_ list_ace" v="1" id="'+result.colContentList[i].pid+'tag">+</i>'+result.colContentList[i].titleName+
							'		<div id="'+result.colContentList[i].pid+'" style="display: none;" class="size14 c798383 mb30 pos-r "></div>'+
							'	</p>'+ 
							'</div> '+
							'<div class="kong60"></div>';
				} 
				$("#righecontext").html("");
				$("#righecontext").html(html);
			},
			pageIndexName : 'pageIndex', //请求参数，当前页数，索引从0开始
			pageSizeName : 'pageSize'//请求参数，每页数量
		}
	}); 
}
/**
 * 下一级
 * @param contentId
 * @param content
 */
function checkInfoLow(contentId,url){ 
	var tempStr1 = contentId.substring(0,contentId.lastIndexOf("!"));
	var tempStr2 = contentId.substring(contentId.lastIndexOf("!")); 
	var contentId = tempStr1+"\\"+ tempStr2	;
	if(!$("#"+contentId).is(":visible")){//显示
		$("#"+contentId+"tag").text("-");
		$("#"+contentId).show();
		$("#"+contentId).load( BASE_PATH + url , function( response, status, xhr ) {
			  $('#moon').html(response);
		});
	}else{
		$("#"+contentId+"tag").text("+");
		$("#"+contentId).hide();
	}

	
}
function goBack(){
	var isLowerLevel = $("#isLowerLevel").val();
	var contentPid = $("#contentPid").val();
	var url = $("#url").val();
	checkInfo(isLowerLevel,contentPid,url);
}

//普通字符转换成转意符
function html2Escape(sHtml) {
	 return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}
//回车转为br标签
function return2Br(str) {
	 return str.replace(/\r?\n/g,"<br />");
}
//去除开头结尾换行,并将连续3次以上换行转换成2次换行
function trimBr(str) {
	 str=str.replace(/((\s|&nbsp;)*\r?\n){3,}/g,"\r\n\r\n");//限制最多2次换行
	 str=str.replace(/^((\s|&nbsp;)*\r?\n)+/g,'');//清除开头换行
	 str=str.replace(/((\s|&nbsp;)*\r?\n)+$/g,'');//清除结尾换行
	 return str;
}
//将多个连续空格合并成一个空格
function mergeSpace(str) {
	 str=str.replace(/(\s|&nbsp;)+/g,' ');
	 return str;
}

function fomtHtml(str){
	var temp ;
	temp = html2Escape(str);
	temp = return2Br(temp);
	temp = trimBr(temp);
	temp = mergeSpace(temp);
	return temp;
}