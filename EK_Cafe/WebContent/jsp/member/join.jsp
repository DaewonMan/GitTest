<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="js/validCheck.js"></script>
<script type="text/javascript" src="js/member.js"></script>
</head>
<body>
	<form action="MemberJoinC" method="post" enctype="multipart/form-data" name="joinForm" onsubmit="return joinCheck();">
		<table id="joinTbl">
			<tr>
				<td colspan="2" class="joinTblBlank"></td>
			</tr>
			<tr>
				<td class="joinTblTd1">id</td>
				<td><input name="m_id" maxlength="10" autofocus="autofocus" class="joinInput" autocomplete="off" placeholder="�ѱ� �ȵ�, �ִ� 10��"></td>
			</tr>
			<tr>
				<td class="joinTblTd1">pw</td>
				<td><input name="m_pw" type="password" maxlength="10" class="joinInput" placeholder="���� ��ҹ���, ���� ����, �ִ� 10��"></td>
			</tr>
			<tr>
				<td class="joinTblTd1">pwȮ��</td>
				<td><input name="m_pwChk" type="password" maxlength="10" class="joinInput" placeholder="���� ��ҹ���, ���� ����, �ִ� 10��"></td>
			</tr>
			<tr>
				<td class="joinTblTd1">�̸�</td>
				<td><input name="m_name" maxlength="10" class="joinInput" autocomplete="off" placeholder="�ִ� 10��"></td>
			</tr>
			<tr>
				<td class="joinTblTd1">����</td>
				<td>
					<select name="m_y" class="joinSelect">
						<c:forEach var="y" begin="${curYear - 50 }" end="${curYear }">
							<option>${y }</option>
						</c:forEach>
					</select>��&nbsp;&nbsp;
					<select name="m_m" class="joinSelect">
						<c:forEach var="m" begin="1" end="12">
							<option>${m }</option>
						</c:forEach>
					</select>��&nbsp;&nbsp;
					<select name="m_d" class="joinSelect">
						<c:forEach var="d" begin="1" end="31">
							<option>${d }</option>
						</c:forEach>
					</select>��
				</td>
			</tr>
			<tr>
				<td class="joinTblTd1">����</td>
				<td><input name="m_img" type="file"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="joinBtn" type="submit" value="����">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="joinTblBlank"></td>
			</tr>
		</table>
	</form>
</body>
</html>