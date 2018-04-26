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
			
			$('#user_register').attr({
				'method':'post',
				'action':'usermodity.do'
			});
			$('#user_register').submit();
		};
	});
	$('#r_del').click(function(){
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			
			$('#user_register').attr({
				'method':'post',
				'action':'userdelete.do'
			});
			$('#user_register').submit();
		};
	});
	$('#r_cel').click(function(){
		var c = confirm('취소 하시겠습니까?');
		if(c == true){
			
			$('#user_register').attr({
				'method':'post',
				'action':'userlist.do'
			});
			$('#user_register').submit();
		};
	});
});
</script>
<div id="main_center">
<h1>User List One Page</h1>
	<form id="user_register">
	ID<input type="text" name="id" id="id" value="${userlistone.id }"><br>
	PWD<input type="text" name="pwd" id="pwd" value="${userlistone.pwd }"><br>
	NAME<input type="text" name="name" id="name" value="${userlistone.name }"><br>
	<input type="button" value="modity" id="r_mod">
	<input type="button" value="delete" id="r_del">
	<input type="button" value="cancel" id="r_cel">
	</form>
</div>