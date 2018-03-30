<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="js/validCheck.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<title>Insert title here</title>
</head>
<body>
<table id="loginTbl" border="1" align="center">
	<form name="loginForm" action="MemberLoginC" method="post" onsubmit="return loginCheck();">
	<tr>
		<td colspan="2">
			<img id="loginImg" src="img/coffeebook.jpg" width="500">
		</td>
	</tr>
	<tr>
		<td>ID </td>
		<td>
			<input name="m_id" autofocus="autofocus" autocomplete="off" value="${cookie.lastLoginId.value }">
		</td>
	</tr>
	<tr>
		<td>PW </td>
		<td>
			<input type="password" name="m_pw">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="checkbox" name="autoLogin"> 자동로그인
			<input type="submit" value="로그인">
	</form>
			<button onclick="toJoin();">회원가입</button>
		</td>
	</tr>
</table>

</body>
</html>