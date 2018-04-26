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
<h1>Correlationlist List Page</h1>
<table>
<thead>
	<tr><th>main</th><th>score</th><th>total_num</th><th>total_area</th></tr>
</thead>
<tbody>
<c:forEach var="c" items="${correlationlist }">
	<tr>
		<td>${c.main }></td>
		<td>${c.score }</td>
		<td>${c.total_num }</td>
		<td>${c.total_area }</td>
	</tr>
</c:forEach>
</tbody>
</table>

</div>











