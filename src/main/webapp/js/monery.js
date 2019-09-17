$(function(){
	
	var thisURL = document.URL;
	var getval =thisURL.split('?')[1];
	monery = getval.split("=")[1];
	$("h1").html("总共花费了"+monery+"元");
});