
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List Sach</h1>
	<form action="/QLSach/SearchController" method="post">
		<input name="search" type="text"> <input type="submit"
			value="Search">
	</form>
	<a href="/QLSach/AddController">Add new book</a>
	<table border="1">

		<tr>
			<th>MA SACH</th>
			<th>TEN SACH</th>
			<th>SO LUONG</th>
			<th>NAM</th>
		</tr>
		<c:forEach var="tempSach" items="${List}">

			<tr>
				<td>${tempSach.id}</td>
				<td>${tempSach.ten}</td>
				<td>${tempSach.sl}</td>
				<td>${tempSach.nam}</td>
				<td><a href="/QLSach/AddController?id=${tempSach.id}">Edit</a></td>
				<td><a href="/QLSach/AddController?id=${tempSach.id}">Xoá</a></td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>