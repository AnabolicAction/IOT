<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<style>
#main_center {
	margin: 0 20px;
	width: 760px;
	height: 480px;
	background: white;
}
</style>
<script>
function display(input1,input2){
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'if you live in forest.'
	    },
	    subtitle: {
	        text: '���� ������ �ູ�� ���°Ŷ� ����'
	    },
	    xAxis: {
	        categories: input1,
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '������/1000'
	        }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    series: input2 
	    
	    /* [{
	        name: '������',
	        data: [1,2]
	    }, {
	        name: '��������',
	        data: [1,2]
	    }] */
	});
}

$(document).ready(function(){
	// Server�� �����͸� ��û�Ѵ�.
	// AJAX��
$.ajax({
		url:'chart4impl.do',
		success:function(data){
			 $.ajax({
				url:'chart4impl2.do',
				success:function(data1){

					display(data, data1);
				},error:function(){
					alert('fail');
				}
			});  
		},error:function(){
			alert('fail');
		}
	}); 

});
</script>
<div id="main_center">
	<h1>Main Center</h1>
	<div id="container"
		style="min-width: 300px; height: 400px; margin: 0 auto"></div>

</div>







