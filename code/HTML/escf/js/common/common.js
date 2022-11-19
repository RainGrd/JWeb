function getEvent(e) {  
    return e || window.event;  
}  
function contains(parentNode, childNode) {
    if (parentNode.contains) {
        return parentNode != childNode && parentNode.contains(childNode);
    } else {
        return !!(parentNode.compareDocumentPosition(childNode) & 16);
    }
}
function checkHover(e,target){
    if (getEvent(e).type=="mouseover")  {
        return !contains(target,getEvent(e).relatedTarget||getEvent(e).fromElement) && !((getEvent(e).relatedTarget||getEvent(e).fromElement)===target);
    } else {
        return !contains(target,getEvent(e).relatedTarget||getEvent(e).toElement) && !((getEvent(e).relatedTarget||getEvent(e).toElement)===target);
    }
}function getEvent(e){
    return e||window.event;
}

function DateDiff(sDate1,sDate2)
{ 	
	var arrDate,objDate1,objDate2,intDays;
	arrDate=sDate1.split("-");
	objDate1=new Date(arrDate[1]+'-'+arrDate[2]+'-'+arrDate[0]);
	arrDate=sDate2.split("-");
	objDate2=new Date(arrDate[1] + '-'+arrDate[2]+'-'+arrDate[0]);
	alert(sDate1+"sDate1");
	alert(sDate2+"sDate2");
	intDays=parseInt(Math.abs(objDate1-objDate2)/(1000*60*60*24));	
	alert(objDate1-objDate2);
	alert(Math.abs(objDate1-objDate2));
	alert(intDays);
	return intDays;
}
 function Datedhms(){ 	
		var sDate2 = new Date().format("yyyy-MM-dd hh:mm:ss");
		var sDate1 = $("#hidden").val();
		var arrDate,objDate1,objDate2,intDays,inth,intm,ints;
		arrDate=sDate1.split("-");
		objDate1=new Date(arrDate[1]+'-'+arrDate[2]+'-'+arrDate[0]);
		arrDate=sDate2.split("-");
		objDate2=new Date(arrDate[1] + '-'+arrDate[2]+'-'+arrDate[0]);
		intDays=parseInt(Math.abs(objDate1-objDate2)/1000/60/60/24);
		$('.intDays').text(intDays);
		inth = parseInt(Math.abs(objDate1-objDate2)%(1000*60*60*24)/(1000*60*60));
		$('.inth').text(inth);
		intm =  parseInt(Math.abs(objDate1-objDate2)%(1000*60*60*24)%(1000*60*60)/(1000*60));										        
		$('.intm').text(intm);
		ints =  parseInt(Math.abs(objDate1-objDate2)%(1000*60*60*24)%(1000*60*60)%(1000*60)/1000);										        
		$('.ints').text(ints);										        
	}

function DiffLong(datestr1, datestr2) {
	var date1 = new Date(Date.parse(datestr1.replace(/-/g, "/")));
	var date2 = new Date(Date.parse(datestr2.replace(/-/g, "/")));
	var datetimeTemp;
	var isLater = true;
	if (date1.getTime() > date2.getTime()) {
		isLater = false;
		datetimeTemp = date1;
		date1 = date2;
		date2 = datetimeTemp;
	}
	difference = date2.getTime() - date1.getTime();
	thisdays = Math.floor(difference / (1000 * 60 * 60 * 24));
	difference = difference - thisdays * (1000 * 60 * 60 * 24);
	thishours = Math.floor(difference / (1000 * 60 * 60));
	//var strRet = thisdays + 'Ìì' + thishours + 'Ð¡Ê±';
	//alert(strRet);
	return thisdays;
}

var widthPx = function(idx){
		var oWidth = $(".nav .idx:eq("+idx+")").outerWidth(),
			left =0;
		$(".nav .idx:lt("+idx+")").each(function(){
			left += $(this).width() + 20;
		});
		left = idx == 0 ? left:idx==4?left+48:idx==5?left+72:left+24;
		return [left,oWidth];
	}