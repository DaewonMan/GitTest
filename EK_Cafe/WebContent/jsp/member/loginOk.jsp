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
			<td id="memberWrite">&nbsp; �Խñ� : 0��</td>
		</tr>
		<tr>
			<td id="memberComment">&nbsp; ��� : 0��</td>
		</tr>
		<tr>
			<td id="memberLevel">&nbsp; ��� : VIP</td>
		</tr>
		<tr>
			<td class="btnTd" align="center" colspan="2">
				<button onclick="toUpdate();">��������</button>
				<button onclick="toDelete();">ȸ��Ż��</button>
				<button onclick="toLogout();">�α׾ƿ�</button><br>
				${r }
			</td>
		</tr>
	</table>
</body>
</html>