// JavaScript Document
function getByClass( oParent , sClass){  
	var aEle = oParent.getElementsByTagName("*"); 
	var aResult = [] ;
	
	for( var i=0; i<aEle.length ; i++){
		if( aEle[i].className == sClass ){
			aResult.push(aEle[i]) ;
		}
	}
	return aResult ;
}


function getByClass02( sClass){  
	var aEle = document.getElementsByTagName("*"); 
	var aResult = [] ;
	
	for( var i=0; i<aEle.length ; i++){
		if( aEle[i].className == sClass ){
			aResult.push(aEle[i]) ;
		}
	}
	return aResult ;
}


function startMove( obj , json ,mul ,fnEnd ){
	clearInterval( obj.timer );
	obj.timer = setInterval(function(){
		var bStop = true; 
		for( var attr in json ){ 
			var cur = 0;
			if( attr == "opacity" ){
				cur = Math.round(parseFloat(getStyle( obj , "opacity"))*100) ;	
			}else{ 
				cur = parseInt(getStyle( obj , attr));
			}
			var speed = (json[attr]-cur)/mul ;
			
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			if( cur!=json[attr] ){  
				bStop = false ;
				if ( attr == "opacity" ){
					obj.style["filter"] = "alpha(opacity:"+(speed+cur)+")" ;
					obj.style["opacity"] = (speed+cur)/100 ;
				}else{
					obj.style[attr] = speed+cur+"px" ;	
				}
			 }
		}
		if( bStop ){ 
			clearInterval( obj.timer );
			if(fnEnd)fnEnd();
		}
	},30)
}

function startMove02 ( obj ,json , speed ,fnEnd){
	clearInterval( obj.timer );
	obj.timer = setInterval(function(){
		var bStop = true;
		for( var attr in json ){
			var cur = parseInt(getStyle( obj ,attr));
			
			if( cur!=json[attr] ){
				if( cur+speed > json[attr] ){
					speed = json[attr]-cur ;
				}
				bStop = false ;
				obj.style[attr]=cur+speed+"px";
			}
		}
		if( bStop ){ 
			clearInterval( obj.timer );
			if(fnEnd){fnEnd();}
		}
	},30)
}


function getStyle( obj , attr ){
	if( obj.currentStyle ){
		return obj.currentStyle[attr];
	}else{
		return getComputedStyle( obj ,false )[attr];
	}
}