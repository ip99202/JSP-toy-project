<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 관리 시스템</title>
</head>
<body>
	<center><h1>도서 관리 시스템</h1></center>
	<table cellpadding=10>
		<tr>
			<td width=150 valign=top>
				<a href="/BookRental/book-list?MODE=code&PAGE_NO=1">- 목록</a><br>
				<a href="/BookRental/book-list?MODE=rent&PAGE_NO=1">- 대여량 기준 정렬</a><br>
				<a href="/BookRental/book-list-isrent?MODE=isrent&PAGE_NO=1">- 대여된 책</a><br>
				<a href="/BookRental/book-list-isrent?MODE=canrent&PAGE_NO=1">- 대여 가능한 책</a><br>
				<a href="/BookRental/WebTemplate.jsp?BODY_PATH=RentBook.jsp">- 대여</a><br>
				<a href="/BookRental/WebTemplate.jsp?BODY_PATH=ReturnBook.jsp">- 반납</a><br>
				<a href="/BookRental/WebTemplate.jsp?BODY_PATH=AddBook.jsp">- 추가</a><br>
			</td>
			<td valign="top">
				<jsp:include page="${param.BODY_PATH }"/>
			</td>
		</tr>
	</table>
</body>
</html>