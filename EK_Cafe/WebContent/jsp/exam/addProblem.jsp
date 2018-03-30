<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form name="addProForm" action="ExamAddProblemC?e_level=${param.e_level }" onsubmit="return toAddProblemCheck();" method="post">
		<table>
			<tr>
				<td>
					문제 등록
				</td>
			</tr>
			<tr>
				<td class="problemTd">
					문제 : <input name="e_problem">
				</td>
			</tr>
			<tr>
				<td class="selectTd">	
					1. <input name="e_answer1"><br>
					2. <input name="e_answer2"><br>
					3. <input name="e_answer3"><br>
					답. <input name="e_solution"><br><p>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>