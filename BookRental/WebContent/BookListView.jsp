<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>책 목록</title>
</head>
<body>
	<table border=1>
		<tr>
			<td width=40>ID</td>
			<td width=200>이름</td>
			<td width=80>저자</td>
			<td width=90>가격</td>
			<td width=70>대여 가능</td>
			<td width=70>대여 횟수</td>
		</tr>
		<c:forEach var="cnt" begin="0" end="${BOOK_LIST.listSize - 1 }">
			<tr>
				<td>${BOOK_LIST.code[cnt] }</td>
				<td>${BOOK_LIST.title[cnt] }</td>
				<td>${BOOK_LIST.writer[cnt] }</td>
				<td>${BOOK_LIST.price[cnt] }</td>
				<td>${BOOK_LIST.rent[cnt] }</td>
				<td>${BOOK_LIST.count[cnt] }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:if test="${!BOOK_LIST.firstPage}">
			<A href='book-list?PAGE_NO=${param.PAGE_NO - 1}'>이전 페이지</A>
		</c:if>
		<c:forEach var="cnt" begin="1" end="${BOOK_LIST.pageNum}">
			<A href='book-list?PAGE_NO=${cnt}'>${cnt}</A>
		</c:forEach>
		<c:if test="${!BOOK_LIST.lastPage }">
			<A href='book-list?PAGE_NO=${param.PAGE_NO + 1}'>다음 페이지</A>
		</c:if>
	</div>
</body>
</html>