<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
#main_center{
	margin:0 20px;
	width:760px;
	height:480px;
	background:white;
}
</style>
<script>
$(document).ready(function(){
	$('#r_mod').click(function(){
		var c = confirm('수정 하시겠습니까?');
		if(c == true){
			$('#add_product').attr({
				'method':'post',
				'action':'productmodity.do',
				'enctype':'multipart/form-data'
			});
			$('#add_product').submit();
		};
	});
});
$(document).ready(function(){
	$('#r_del').click(function(){
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			$('#add_product').attr({
				'method':'post',
				'action':'productdelete.do',
				'enctype':'multipart/form-data'
			});
			$('#add_product').submit();
		};
	});
});
$(document).ready(function(){
	$('#r_cel').click(function(){
		var c = confirm('취소 하시겠습니까?');
		if(c == true){
			$('#add_product').attr({
				'method':'post',
				'action':'productlist.do',
				'enctype':'multipart/form-data'
			});
			$('#add_product').submit();
		};
	});
});

</script>    
<div id="main_center">
<h1>Product Update Page</h1>
<form id="add_product">
	ID<input type="number" name="id" value="${productupdate.id }"><br>
	NAME<input type="text" name="name" value="${productupdate.name }"><br>
	PRICE<input type="number" name="price" value="${productupdate.price }"><br>
	IMG<input type="file" name="mf"><br>
	<input type="hidden" name="imgname" value="${productupdate.imgname }">
	<input type="button" value="modity" id="r_mod">
	<input type="button" value="delete" id="r_del">
	<input type="button" value="cancel" id="r_cel">	
</form> 
</div>