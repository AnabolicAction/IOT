<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<style>
#main_center{
	margin:0 20px;
	width:760px;
	height:480px;
	background:white;
}
</style>    
<script>
function display(input){
	Highcharts.chart('container', {
	    chart: {
	        type: 'variablepie'
	    },
	    title: {
	        text: 'Countries compared by population density and total area.'
	    },
	    tooltip: {
	        headerFormat: '',
	        pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
	            'Area (square km): <b>{point.y}</b><br/>' +
	            'Population density (people per square km): <b>{point.z}</b><br/>'
	    },
	    series: [{
	        minPointSize: 10,
	        innerSize: '80%',
	        zMin: 0,
	        name: 'countries',
	        data: input
	    }]
	});
}

$(document).ready(function(){
	// Server에 데이터를 요청한다.
	// AJAX로
	$.ajax({
		url:'chart2impl.do',
		success:function(data){
			display(data);
			/* alert(JSON.stringify(data)); */
		},
		error:function(){
			alert('fail');
		}
	}); 

/* 	var datas =[
				{"name":"1월","y":233144},
				{"name":"4월","y":250085},
				{"name":"2월","y":233511},
				{"name":"3월","y":272779}
				];
	display(datas); */
	
	/* var datas = [
		{name:'지훈',y:70},
		{name:'다혜',y:90},
		{name:'용원',y:80},
		{name:'영무',y:60},
		{name:'란영',y:99},
		{name:'선빈',y:40},
		{name:'호진',y:50},
		{name:'유정',y:80},
		{name:'상우',y:70},
		{name:'현경',y:90}
		]; */
		
});
</script>
<div id="main_center">
<h1>Chart2</h1>
<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

</div>







