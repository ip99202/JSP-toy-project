<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>책 목록</title>
</head>
<body>
	<c:set var="code" value="code" />
	<c:set var="rent" value="rent" />
	<c:set var="isrent" value="isrent" />
	<c:set var="canrent" value="canrent" />
	<c:set var="search" value="search" />
	<c:set var="isreturn" value="return" />
	<c:set var="isdel" value="delete" />
	<c:if test="${param.MODE eq code}">
		<h3>도서 목록</h3>
		<c:set var="page" value="book-list" />
	</c:if>
	<c:if test="${param.MODE eq rent}">
		<h3>도서 목록(대여량 기준)</h3>
		<c:set var="page" value="book-list" />
	</c:if>
	<c:if test="${param.MODE eq isrent}">
		<h3>대여된 도서 목록</h3>
		<c:set var="page" value="book-list" />
	</c:if>
	<c:if test="${param.MODE eq canrent}">
		<h3>대여 가능한 도서 목록</h3>
		<c:set var="page" value="book-list" />
	</c:if>
	<c:if test="${param.MODE eq search}">
		<h3>검색 결과</h3>
		<c:set var="page" value="book-search" />
	</c:if>
	
	<table border=1>
		<tr>
			<td style="text-align: center" width=60>ID</td>
			<td style="text-align: center" width=200>이름</td>
			<td style="text-align: center" width=180>저자</td>
			<td style="text-align: center" width=90>가격</td>
			<td style="text-align: center" width=80>대여 가능</td>
			<td style="text-align: center" width=80>대여 횟수</td>
		</tr>
		<c:if test="${BOOK_LIST.listSize < 1}">
			빈 목록입니다.
		</c:if>
		<c:if test="${BOOK_LIST.listSize > 0}">
			<c:forEach var="cnt" begin="0" end="${BOOK_LIST.listSize - 1 }">
				<tr>
					<td style="text-align: center;">${BOOK_LIST.code[cnt] }</td>
					<td style="text-align: center">${BOOK_LIST.title[cnt] }</td>
					<td style="text-align: center">${BOOK_LIST.writer[cnt] }</td>
					<td style="text-align: center">${BOOK_LIST.price[cnt] }</td>
					<td style="text-align: center">${BOOK_LIST.rent[cnt] }</td>
					<td style="text-align: center">${BOOK_LIST.count[cnt] }</td>
					<c:if test="${param.HANDEL eq isreturn }">
						<td style="text-align: center"><a
							href="/BookRental/book-return?ID=${BOOK_LIST.code[cnt] }">반납</a>
						</td>
					</c:if>
					<c:if test="${param.HANDEL eq isdel }">
						<td style="text-align: center"><a
							href="/BookRental/book-delete?ID=${BOOK_LIST.code[cnt] }">삭제</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</table>

	<table style="margin: auto">
		<tr>
			<c:if test="${param.MODE ne search }">
				<td width=86><c:if test="${!BOOK_LIST.firstPage}">
						<A
							href='${page }?MODE=${param.MODE }&PAGE_NO=${param.PAGE_NO - 1}&HANDEL=${param.HANDEL}'>이전
							페이지</A>
					</c:if></td>
				<td><c:forEach var="cnt" begin="1" end="${BOOK_LIST.pageNum}">
						<A href='${page }?MODE=${param.MODE }&PAGE_NO=${cnt}&HANDEL=${param.HANDEL}'>${cnt}</A>
					</c:forEach></td>
				<td width=86><c:if test="${!BOOK_LIST.lastPage }">
						<A
							href='${page }?MODE=${param.MODE }&PAGE_NO=${param.PAGE_NO + 1}&HANDEL=${param.HANDEL}'>다음
							페이지</A>
					</c:if></td>
			</c:if>
			<c:if test="${param.MODE eq search }">
					검색결과는 5개만 표시됩니다.
				</c:if>
		</tr>
	</table>

</body>
</html>