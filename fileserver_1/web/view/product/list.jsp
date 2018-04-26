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
<div id="main_center">
<h1>Product List Page</h1>
<table>
<thead>
	<tr><th>ID</th><th>NAME</th><th>PRICE</th><th>REGDATE</th><th>IMGNAME</th></tr>
</thead>
<tbody>
<c:forEach var="p" items="${productlist }">
	<tr>
		<td><a href="productupdate.do?id=${p.id }">${p.id }</td>
		<td>${p.name }</td>
		<td>${p.price }</td>
		<td>${p.regdate }</td>
		<td>${p.imgname }</td>
	</tr>
</c:forEach>
</tbody>
</table>

</div>





