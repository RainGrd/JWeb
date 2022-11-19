(function($)  
{  
    $.extend({  
        /** 
         * 数字千分位格式化 
         * @public 
         * @param mixed mVal 数值 
         * @param int iAccuracy 小数位精度(默认为2) 
         * @return string 
         */  
        xuxian:function(mVal, iAccuracy){  
            var fTmp = 0.00;//临时变量  
            var iFra = 0;//小数部分  
            var iInt = 0;//整数部分  
            var aBuf = new Array(); //输出缓存  
            var bPositive = true; //保存正负值标记(true:正数)  
            /** 
             * 输出定长字符串，不够补0 
             * <li>闭包函数</li> 
             * @param int iVal 值 
             * @param int iLen 输出的长度 
             */  
            function funZero(iVal, iLen){  
                var sTmp = iVal.toString();  
                var sBuf = new Array();  
                for(var i=0,iLoop=iLen-sTmp.length; i<iLoop; i++)  
                    sBuf.push('0');  
                sBuf.push(sTmp);  
                return sBuf.join('');  
            };  
  
            if (typeof(iAccuracy) === 'undefined')  
                iAccuracy = 2;  
            bPositive = (mVal >= 0);//取出正负号  
            fTmp = (isNaN(fTmp = parseFloat(mVal))) ? 0 : Math.abs(fTmp);//强制转换为绝对值数浮点  
            //所有内容用正数规则处理  
            iInt = parseInt(fTmp); //分离整数部分  
            iFra = parseInt((fTmp - iInt) * Math.pow(10,iAccuracy) + 0.5); //分离小数部分(四舍五入)  
  
            do{  
                aBuf.unshift(funZero(iInt % 1000, 3));  
            }while((iInt = parseInt(iInt/1000)));  
            aBuf[0] = parseInt(aBuf[0]).toString();//最高段区去掉前导0  
            return ((bPositive)?'':'-') + aBuf.join(',') +'.'+ ((0 === iFra)?'00':funZero(iFra, iAccuracy));  
        },  
        /** 
         * 将千分位格式的数字字符串转换为浮点数 
         * @public 
         * @param string sVal 数值字符串 
         * @return float 
         */  
        unformatMoney:function(sVal){  
            var fTmp = parseFloat(sVal.replace(/,/g, ''));  
            return (isNaN(fTmp) ? 0 : fTmp);  
        },  
    });  
})(jQuery);  
/**
 * 资金流水
 */
var cashFlow = {
		initfunc:function(){
		},
		// 加载数据
		loadData:function(flag){
			//选中交易记录的时候去掉“其他按钮的背景颜色”
			if(flag==6){
				$(".liu_but").addClass("b0b0b0");
			 	$(".liu_but").addClass("bgffffff");
			}
		    if(flag==undefined){
		    	flag=$(".ceshi_js").attr("val");
		    }
		    
			var beginTime = $("#beginTimes").val();
			var endTime = $("#endTime").val();
			var url = BASE_PATH + "userinfo/centerController/selectZiJinWater.shtml";
			$.ajax({
				type: "POST",
		    	url : url,
		    	data:{"beginTime":beginTime,"endTime":endTime,"watrType":flag},
				dataType: "json",
			    success: function(data){ 
			    	if(data.message.status ==200 && data.rows.length > 0){
			    		cashFlow.cashFlowDetailed(data.rows);
			    	}else{
			    		cashFlow.cashFlowDetailed(data.rows);
			    	}
				}
			});
		},
		
		//资金流水详细
		cashFlowDetailed:function(data){
			var str;
			var shouru=0;
			var zhichu=0;
			if(data.length<1){
				$(".cash_flow").html('');
			}
			if(data!=null && data.length>0){ 
				for(var i=0;i<data.length;i++){
					var fundValues=0;
					var flag=0;
					var businessTypeValue=data[i].businessTypeValue;
					if(businessTypeValue %2 ==0){//判断是否为基偶数 基数是收入  偶数是支出
						fundValues = '-'+data[i].fundValue;//支出
					}else{
						flag=data[i].fundValue;//收入
					}
					str+='<tr>'+
					         '<td>'+data[i].happenTime+'</td>'+
					         '<td>'+$.xuxian(flag, 2)+'</td>'+
					         '<td>'+$.xuxian(fundValues, 2)+'</td>'+
					         '<td>'+$.xuxian(data[i].accountBalance, 2)+'</td>'+
					         '<td>'+data[i].businessType+'</td>'+
					         '<td>'+data[i].funWatDesc+'</td>'+
					'</tr>'
				}
				$(".cash_flow").html(str);
//				$(".cash_flow").append('<tr>'+'<td>总计'+'</td>'+'<td>'+shouru+'</td>'+'<td>'+zhichu+'</td>'+'<tr>');
			}
		
			
		},
};

//加载文件
(function(){
		//4个表格切换
	    $(".liu_but").click(function(){
	 	$(".liu_but").addClass("b0b0b0");
	 	$(".liu_but").addClass("bgffffff");
	 	$(this).removeClass("b0b0b0");
	 	$(this).removeClass("bgffffff");
	 	$(".liu_but").removeClass("ceshi_js");
	 	$(this).addClass("ceshi_js");
	 });
	cashFlow.loadData();
})();