<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가 결과</title>
</head>
<body>
	<c:set var="result" value="success"/>
	<c:set var="exist" value="exist"/>
	<c:set var="isempty" value="empty"/>
	<c:set var="iserror" value="error"/>
	<c:if test="${param.RESULT eq iserror}">
		입력값이 잘못되었습니다.
	</c:if>
	<c:if test="${param.RESULT eq exist}">
		이미 있는 ID입니다.
	</c:if>
	<c:if test="${param.RESULT eq isempty}">
		모든 데이터를 입력하세요.
	</c:if>
	<c:if test="${param.RESULT eq result }">
		<c:redirect url="/book-list?PAGE_NO=1"/>
	</c:if>
</body>
</html>