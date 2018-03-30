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
					<h1>영어 일기쓰기</h1>
					<p>
						<em>영어일기 쓰기</em>는?
					</p>
					<p>
						여러분이 배운 문장, 단어들을 자유롭게 연습해보세요.<br> 한 단어라도 자꾸 쓰다보면 습관이 되고 실력이
						됩니다!
					</p>
					<p>
				</td>
			</tr>
			<tr>
				<td align="right" height="20px" colspan="2">상태 - ${r }
					<p>
				</td>
			</tr>
			<tr>
				<td id="diaryWritePageArea" align="center" colspan="2">
					<table id="diaryWriteTbl" cellpadding="0" cellspacing="0">
						<tr>
						<td class="wtd">작성자 </td>
						<td class="wtd2">${loginMember.m_id } </td>
						</tr>
						
						<tr>
							<td class="wtd">제목</td>
							<td class="wtd2"><input readonly="readonly" name="d_title" size="90%" id="writeInput" value="${d.d_title }"></td>
						</tr>
						<tr>
							<td class="wtdTextTd">내용</td>
							<td><textarea readonly="readonly" name="d_txt" id="writeTextArea" >${d.d_txt }</textarea></td>
						</tr>
					</table>
			<tr>
				<td id="btntd" colspan="2" align="right">
				<input type="submit" value="목록"></td>
			</tr>

		</table>

	</form>
</body>
</html>