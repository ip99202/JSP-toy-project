<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대여 결과</title>
</head>
<body>
	<h3>도서 대여</h3>
	<c:set var="result" value="fail"/>
	<c:if test="${param.RESULT eq result}">
		대여할 수 없습니다.
	</c:if>
	<c:if test="${param.RESULT ne result }">
		대여되었습니다.
	</c:if>
</body>
</html>