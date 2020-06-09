<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 추가</title>
</head>
<body>
	<h3>도서 추가</h3>
	<form action="/BookRental/book-add" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="ID" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="TITLE" /></td>
			</tr>
			<tr>
				<td>저자</td>
				<td><input type="text" name="WRITER" /></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="PRICE" /></td>
			</tr>
		</table>
		<input type="submit" value="저장" />
	</form>
</body>
</html>