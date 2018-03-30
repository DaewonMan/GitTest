<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="result" items="${sessionScope.examResult }">
		dd : ${result }<br>
	</c:forEach>
	
	<c:choose>
		<c:when test="${sessionScope.giveLevel }">
			Pass
		</c:when>
		<c:otherwise>
			Non-Pass
		</c:otherwise>
	</c:choose>
</body>
</html>