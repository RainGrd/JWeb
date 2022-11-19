 $(function() {	
	var time = 500;
	var interval,interval2,interval3; //调度器对象。
	run();
	var date1 = new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate();
	var date2 = "2015-10-28";		
	$("#dateDay").text(DiffLong(date1,date2));
	function run() {
		interval = setInterval("seconds()", time);
		interval2 = setInterval("minutes()", '60s');
		interval3 = setInterval("hours()", '3600s');	                
	}
					
});
function hours(){
	 var d=new Date()
   var str=d.getHours(); 
  $("#hours").text(str);
}
function minutes(){
	 var d=new Date()
   var str=d.getMinutes(); 
  $("#minutes").text(str);
}
function seconds(){
	 var d=new Date()
	   var str=d.getSeconds(); 
	  $("#seconds").text(str);
}