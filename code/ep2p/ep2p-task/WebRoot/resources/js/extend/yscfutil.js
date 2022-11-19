/**
 *  内容状态格式化
 */
function stateFormater(val,row){
	if(val==1){
		return '启用';
	}else if(val==2){
		return '禁用';
	}
}
/**
 *  备注 鼠标悬浮 提示 格式化
 */
function detailsFormater(val,row){
		return '<div title="'+val+'">'+val+'</div>';
}
/**
 *  时间格式转换  用于表格显示 年-月-日
 */
function convertDate(val,row){
	// 判断是否存在数据  如果不存在，直接退出方法 
	if(null == val || "" == val){
		return "";
	}
	if(isNaN(val)){ //如果不是时间戳格式
		// 去掉时间后面的  .加数字 
		var index = val.indexOf(".");
		// 如果不存在,那index就等于总长度
		if(index == -1){
			index = val.length;
		}
		// 截取时间字符串,并转换
		var str=val.substring(0,index).toString();
		// 把时间里面的  -  转换城 /
		str = str.replace(/-/g,"/");
	}else{//时间戳格式
		str=val;
	}
	//str=str.replace(/CST/, "");
	// 转换成时间
	var date = new Date(str);
	// 获取时间的年月日
	var y = date.getFullYear();
  var m = date.getMonth()+1;
  var d = date.getDate();
  // 返回指定的时间格式
  return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
/**
 *  时间格式转换  用于表格显示 年-月-日 时分秒
 */
function convertDateDetail(val,row){
	// 判断是否存在数据  如果不存在，直接退出方法 
	if(null == val || "" == val){
		return "";
	}
	if(isNaN(val)){ //如果不是时间戳格式
		// 去掉时间后面的  .加数字 
		var index = val.indexOf(".");
		// 如果不存在,那index就等于总长度
		if(index == -1){
			index = val.length;
		}
		// 截取时间字符串,并转换
		var str=val.substring(0,index).toString();
		// 把时间里面的  -  转换城 /
		str = str.replace(/-/g,"/");
	}else{//时间戳格式
		str=val;
	}
	//str=str.replace(/CST/, "");
	// 转换成时间
	var date = new Date(str);
	// 获取时间的年月日
	var y = date.getFullYear();
  var m = date.getMonth()+1;
  var d = date.getDate();
  
  var h= date.getHours();
  var mm= date.getMinutes();
  var s= date.getSeconds();
  // 返回指定的时间格式
  return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(mm<10?('0'+mm):mm)+':'+(s<10?('0'+s):s);
}
/**
 * 用于表单显示格式
 * eg:
 * <input name="checkDttb" 
 * type="text" 
 * class="easyui-datebox" 
 * data-options="formatter:gtsformatter,parser:gtsparser"	
 * required="required" value="${dcmodel.checkDttb}"/>
 * 
 */
function gtsformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y + '-' + (m<10?('0'+m):m) + '-' + (d<10?('0'+d):d);
}

/**
 * 用于表单提交格式化
 * eg:data-options="parser:gtsparser"	;
 */
function gtsparser(val) {
	// 判断是否存在数据 如果不存在，直接退出方法
	if (null == val || "" == val) {
		return "";
	}
	// 去掉时间后面的 .加数字
	var index = val.indexOf(".");
	// 如果不存在,那index就等于总长度
	if (index == -1) {
		index = val.length;
	}
	// 截取时间字符串,并转换
	var str = val.substring(0, index).toString();
	// 把时间里面的 - 转换城 /
	str = str.replace(/-/g, "/");
	// 转换成时间
	var date = new Date(str);
	// 获取时间的年月日
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}


/**
 * 用于表格选中多行之后把fid转换为字符串的形式提交
 * eg:1,2,3,4
 * in=> obj:选中行对象数组
 */
function objToArrayPid(obj){
	var oar = new Array(obj.length);
	for(var i = 0;i<obj.length;i++){
		oar[i]=obj[i].pid;
	}
	return oar;
}


/**
 * 判断病虫害显示效果
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function isDiseaseFmater(value,row,index){
	var showvalue = "";
	if(value > 0){
		showvalue = "<span style='color:red;'>有病害</span>";
	}else{
		showvalue ="<span>无病害</span>";
	}
	return showvalue;
}
function hideMessage() {
	var aM=$('#alertMessage');
	if($('#alertMessage').length==0){
		aM=parent.$('#alertMessage')
	}
	aM.animate({
		opacity : 0,
		right : '-20'
	}, 500, function() {
		$(this).hide();
	});
}
/**
 * alert Message 提示框
 * info
 */
function alertMessage(message){
	var aM=$('#alertMessage');
	if($('#alertMessage').length==0){
		aM=parent.$('#alertMessage')
	}
	aM.removeClass('warning').removeClass('success').addClass('info').html(message).stop().show().animate({
		opacity : 1,
		right : '0'
	}, 500);
	setTimeout(hideMessage, 2500);
	/*
	$.messager.alert('提示',message,'info');
	
	setTimeout(function(){
		if($('.messager-body').length&&$('.messager-body').data("window")){
			$('.messager-body').window("close");
		}
		},4000);*/
}

/**
 * alert Message 提示框
 * warning
 */
function warningMessage(message){
	var aM=$('#alertMessage');
	if($('#alertMessage').length==0){
		aM=parent.$('#alertMessage')
	}
	aM.removeClass('info').removeClass('success').addClass('warning').html(message).stop().show().animate({
		opacity : 1,
		right : '0'
	}, 500);
	setTimeout(hideMessage, 2500);
	/*$.messager.alert('提示',message,'warning');
	setTimeout(function(){
		if($('.messager-body').length&&$('.messager-body').data("window")){
			$('.messager-body').window("close");
		}
		},4000);*/
}

/**
 * alert Message 
 * close Dialog and reload Datagrid
 * 弹出提示框，点击确定按钮之后，关闭对话框并刷新datagrid数据列表
 * info
 */
function alertMessageAndReloadData(message,dialogId,datagridId){
	var aM=$('#alertMessage');
	if($('#alertMessage').length==0){
		aM=parent.$('#alertMessage')
	}
	aM.removeClass('info').removeClass('warning').addClass('success').html(message).stop().show().animate({
		opacity : 1,
		right : '0'
	}, 500);
	if(dialogId){
		$('#'+dialogId).dialog('close');
	}
	$('#'+datagridId).datagrid('reload');
	setTimeout(hideMessage, 2500);
	
	/*$.messager.alert('提示',message,'info',function(){
		if(dialogId){
			$('#'+dialogId).dialog('close');
		}
		$('#'+datagridId).datagrid('reload');
	});
	setTimeout(function(){
		if($('.messager-body').length&&$('.messager-body').data("window")){
			$('.messager-body .messager-button a').trigger("click");
		}
		},4000);*/
}
/**
 * fromatter Textarea 
 * 格式化 Textarea 换行
 * 
 */
function replaceTextarea(value, row, index) {
	if(value){
	var reg=new RegExp('\r\n','g');
	var reg1=new RegExp(' ','g');
	value=value.replace(reg,'<br/>');
	value=value.replace(reg1,'<p>');
	return value
	}
}

/**
* 
* @requires jQuery,EasyUI
* 
* 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
*/
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if(fildOption.title){
				if (!fildOption.hidden) {
					$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
				} else {
					$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
				}
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					if($(item.target).parent().find('.icon-ok').length>2){
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
					}
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
				
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * 设置token的值
 */
function setDefalutToken(fromId){
	var frmid = "#" + fromId +" #token";
	var tkey = guid().replace(/-/g,"");
	$(frmid).val(tkey);
}

/**
 * js guid
 */
function guid(){
	function S4(){
		return (((1 + Math.random())*0x10000)|0).toString(16).substring(1);
	}
	var reval = (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
	return reval;
}


/**
 * 处理js相乘的精度缺失
 * a 第一个数
 * b 第二个数
 */
function multiply(a,b){
	var m = 0;
	var n = 0;
	var s1 = a.toString();
	var s2 = b.toString();
	//获取第一个数的小数点后面的位数
	try{
		m = s1.split(".")[1].length;
	}catch(e){
		m = 0;
	}
	//获取第二个小数点后面的位数
	try{
		n = s2.split(".")[1].length;
	}catch(e){
		n = 0;
	}
	//计算值
	var cjv = Number(s1.replace(".","")) * Number(s2.replace(".","")) / Math.pow(10,m + n); 
	return cjv;
}

/**
 * 去掉空字符
 */
function removeloadFilter(datas){
	var data = datas.data;
	for(var i = 0;i<data.length;i++ ){
		if(data[i].dictContName = '-- 请选择 --'){
			data.splice(i,1);
			break;
		}
	}
	return data;		
}