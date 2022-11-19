var  mystatistics  = {
		//初始化加载角色首页列表
		initRadioDataGrid:function(){
			$('#queryTerms_grid').datagrid({    
				url: basePath + 'radioStatisticsController/getDisPlayLists.shtml',
				width:'100%',
				fit:true,
				title:'节目列表统计',
				toolbar:'#queryTermsToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:displayList_model
			});
		},
		
		searchData:function(){
			var thisDay = new Date();
			var timeType = $("#queryTerms_form #timeType").combobox("getValue");
			if("1" == timeType){
				var ymonthDay = $("#queryTerms_form #year").combobox("getValue") == "" ?  thisDay.getFullYear() : $("#queryTerms_form #year").combobox("getValue");
				$('#queryTerms_grid').datagrid('load',{timetype:"1",ymonthDay:ymonthDay});
			}
			if("2" == timeType){
				var years = $("#queryTerms_form #year1").combobox("getValue") == "" ? thisDay.getFullYear() : $("#queryTerms_form #year1").combobox("getValue");
				var months = $("#queryTerms_form #month").combobox("getValue");
				var ymonthDay = years + "-" + months;
				$('#queryTerms_grid').datagrid('load',{timetype:"2",ymonthDay:ymonthDay});
			}
			if("3" == timeType){
				var starttimes = $("#queryTerms_form #startDate").datebox("getValue");
				var endtimes = $("#queryTerms_form #endDate").datebox("getValue");
				$('#queryTerms_grid').datagrid('load',{timetype:"3",publishBeginTime:starttimes,publishEndTime:endtimes});
			}
		},
		
		selectType:function(record){
			$('.typeItem').addClass('none');
			$('.timeType'+record.value).removeClass('none');
		},
		
		selectTypeInfo:function(record){
			$('.typeItem_info').addClass('none');
			$('.timeType_info'+record.value).removeClass('none');
		},
		
		//查看趋势图
		viewDetail:function(pid,title){
			var path = basePath + "radioStatisticsController/toPorgramePage.shtml";
			$("<div id='displayDetailsDialog' /> ").dialog({
				href:path,
				queryParams:{
					prgpid:pid,
					programTitle:title
				},
				title:"趋势图",
				method:'post',
				width:'700',
				height:'520',
				pagination: true,
			    rownumbers:true,
				modal: true,
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//设置年份信息
		setDefulYear:function(){
			var date = new Date();
			var fully = date.getFullYear();
			$('#queryTerms_form #year').combobox('setValue',fully + "");
			$('#queryTerms_form #year1').combobox('setValue',fully + "");
		}
};


$(function(){
	mystatistics.initRadioDataGrid();
	mystatistics.setDefulYear();
});
