<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="" name="diaryWriteForm">
		<table id="diaryArea2" cellpadding="0" cellspacing="0">
			<tr>
				<td id="diaryTitle" align="center" colspan="2">
					<h1>���� �ϱ⾲��</h1>
					<p>
						<em>�����ϱ� ����</em>��?
					</p>
					<p>
						�������� ��� ����, �ܾ���� �����Ӱ� �����غ�����.<br> �� �ܾ�� �ڲ� ���ٺ��� ������ �ǰ� �Ƿ���
						�˴ϴ�!
					</p>
					<p>
				</td>
			</tr>
			<tr>
				<td align="right" height="20px" colspan="2">���� - ${r }
					<p>
				</td>
			</tr>
			<tr>
				<td id="diaryWritePageArea" align="center" colspan="2">
					<table id="diaryWriteTbl" cellpadding="0" cellspacing="0">
						<tr>
						<td class="wtd">�ۼ��� </td>
						<td class="wtd2">${loginMember.m_id } </td>
						</tr>
						
						<tr>
							<td class="wtd">����</td>
							<td class="wtd2"><input readonly="readonly" name="d_title" size="90%" id="writeInput" value="${d.d_title }"></td>
						</tr>
						<tr>
							<td class="wtdTextTd">����</td>
							<td><textarea readonly="readonly" name="d_txt" id="writeTextArea" >${d.d_txt }</textarea></td>
						</tr>
					</table>
			<tr>
				<td id="btntd" colspan="2" align="right">
				<input type="submit" value="���"></td>
			</tr>

		</table>

	</form>
</body>
</html>