<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" href="css/exam.css">
<link rel="stylesheet" href="css/download.css">
<script type="text/javascript" src="js/validCheck.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/board.js"></script>
<script type="text/javascript" src="js/exam.js"></script>
</head>
<body>
	<table id="homeTbl" border="1">
		<tr>
			<td id="loginTd" colspan="2" align="right">
				<c:choose>
					<c:when test="${sessionScope.loginMember == null }">
						<button onclick="toLogin();" class="loginBtn">로그인</button>	
					</c:when>
					<c:when test="${sessionScope.loginMember != null }">
						<a>${sessionScope.loginMember.m_id } 님</a>
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td id="mainImg" colspan="2"><a href="HC"> <img src="img/coffeebook.jpg"></a> </td>
		</tr>
		<tr>
			<td id="loginInfo"><jsp:include page="${loginPage }"></jsp:include></td>
			<td id="contentTd" rowspan="3" align="center"><jsp:include page="${contentPage }"></jsp:include>
			</td>
		</tr>
		<tr>
			<td class="searchTd"><input class="searchInput"> <button>검색</button></td>
		</tr>
		<tr>
			<td class="menuTd" align="center">
				<a href="BoardC">질의응답</a><p>
				<a href="ExamC">영어레벨테스트</a><p>
				<a href="BoardC">Study Zone</a><p>
				<a href="DiaryC">Diary</a>
				<a href="DownloadC">자료실</a><p>
			</td>
		</tr>
	</table>
</body>
</html>