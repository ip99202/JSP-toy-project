<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>대여량 기준 정렬 책 목록</title>
</head>
<body>
	<table border=1>
		<tr>
			<td width=60><center>ID</center></td>
			<td width=200><center>이름</center></td>
			<td width=180><center>저자</center></td>
			<td width=90><center>가격</center></td>
			<td width=80><center>대여 가능</center></td>
			<td width=80><center>대여 횟수</center></td>
		</tr>
		<c:forEach var="cnt" begin="0" end="${BOOK_LIST.listSize - 1 }">
			<tr>
				<td><center>${BOOK_LIST.code[cnt] }</center></td>
				<td><center>${BOOK_LIST.title[cnt] }</center></td>
				<td><center>${BOOK_LIST.writer[cnt] }</center></td>
				<td><center>${BOOK_LIST.price[cnt] }</center></td>
				<td><center>${BOOK_LIST.rent[cnt] }</center></td>
				<td><center>${BOOK_LIST.count[cnt] }</center></td>
			</tr>
		</c:forEach>
	</table>
		<c:if test="${!BOOK_LIST.firstPage}">
			<A href='book-list-rent-count?PAGE_NO=${param.PAGE_NO - 1}'>이전 페이지</A>
		</c:if>
		<c:forEach var="cnt" begin="1" end="${BOOK_LIST.pageNum}">
			<A href='book-list-rent-count?PAGE_NO=${cnt}'>${cnt}</A>
		</c:forEach>
		<c:if test="${!BOOK_LIST.lastPage }">
			<A href='book-list-rent-count?PAGE_NO=${param.PAGE_NO + 1}'>다음 페이지</A>
		</c:if>
</body>
</html>