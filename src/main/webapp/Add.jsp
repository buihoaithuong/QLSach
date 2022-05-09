<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add new book</h1>
	<form action="/QLSach/AddController" method="post">
		<input name="ten" type="text" placeholder="Tên sach"></br>
		</br> <input name="sl" type="text" placeholder="So Luong"></br>
		</br> <input name="nam" type="text" placeholder="Nam"></br>
		</br> <input type="submit" value="Thêm">
	</form>
</body>
</html>