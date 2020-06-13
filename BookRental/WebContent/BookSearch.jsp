<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색</title>
</head>
<body>
	<table>
		<form action="/BookRental/book-search" method="post">
		<tr>
			<td>검색 방법</td>
		</tr>
		<tr>
			<td><input type="radio" name="chk_info" value="code">ID</td>
		</tr>
		<tr>
			<td><input type="radio" name="chk_info" value="title">이름</td>
		</tr>
		<tr>
			<td><input type="radio" name="chk_info" value="writer">저자</td>
		</tr>
		<tr>
			<td><br>검색내용</td>
		</tr>
		<tr>
			<td><input type="text" name="Search"></td>
		</tr>
		<tr>
			<td><input type="submit" value="검색" ></td>
		</tr>
		</form>
	</table>
</body>
</html>