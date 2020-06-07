<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 대여 시스템</title>
</head>
<body>
	<center><h1>도서 대여 시스템</h1></center>
	<table border=1 cellpadding=10>
		<tr>
			<td width=150 valign=top>
				<a href="/BookRental/book-list?PAGE_NO=1">목록</a><br>
				<a href="/BookRental/book-list-rent-count?PAGE_NO=1">대여량 기준 정렬</a><br>
				<a href="/BookRental/book-list-isrent?PAGE_NO=1&RENT=1">대여된 책</a><br>
				<a href="/BookRental/book-list-isrent?PAGE_NO=1&RENT=0">대여 가능한 책</a><br>
				<a href="/BookRental/RentBook.jsp">대여</a><br>
				<a href="">반납</a><br>
				<a href="">추가</a><br>	
			</td>
			<td valign="top" width="650">
				<jsp:include page="${param.BODY_PATH }"/>
			</td>
		</tr>
	</table>
</body>
</html>