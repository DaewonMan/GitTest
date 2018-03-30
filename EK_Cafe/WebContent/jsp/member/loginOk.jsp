<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table id="loginInfo">
		<tr>
			<td id="memberImg" rowspan="4" align="center"><img src="img/${sessionScope.loginMember.m_img }"></td>
			<td id="memberID">&nbsp; ID : ${sessionScope.loginMember.m_id } </td>
		</tr>
		<tr>
			<td id="memberWrite">&nbsp; 게시글 : 0개</td>
		</tr>
		<tr>
			<td id="memberComment">&nbsp; 댓글 : 0개</td>
		</tr>
		<tr>
			<td id="memberLevel">&nbsp; 등급 : VIP</td>
		</tr>
		<tr>
			<td class="btnTd" align="center" colspan="2">
				<button onclick="toUpdate();">정보수정</button>
				<button onclick="toDelete();">회원탈퇴</button>
				<button onclick="toLogout();">로그아웃</button><br>
				${r }
			</td>
		</tr>
	</table>
</body>
</html>