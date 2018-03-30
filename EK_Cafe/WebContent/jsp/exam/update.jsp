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

	<table border="1">
		<tr>
			<td align="right">
				<button onclick="toAddProblem('${param.e_level}');">문제추가</button>
			</td>
		</tr>
		<tr>
			<td>
				문제 수정
			</td>
		</tr>	
		<tr>
			<td>
				<table>
					<c:if test="${sessionScope.searchExam.get(0).e_no != null }">
						<c:forEach var="exam" items="${sessionScope.searchExam }">
							<form action="ExamUpdateC?e_no=${exam.e_no}&e_level=${exam.e_level }" onsubmit="return toProblemsCheck(this);" method="post">
								<tr>
									<td class="problemTd">
										문제. <input name="e_problem" value="${exam.e_problem }">
									</td>
								</tr>
								<tr>
									<td class="selectTd">
										1. <input name="e_answer1" value="${exam.e_answer1 }"><br>
										2. <input name="e_answer2" value="${exam.e_answer2 }"><br>
										3. <input name="e_answer3" value="${exam.e_answer3 }"><br>
										답. <input name="e_solution" value="${exam.e_solution }"><br><p>
									</td>
								</tr>
								<tr>
									<td align="right">
										<input type="submit" value="수정">
							</form>
										<button onclick="toDeleteProblem(${exam.e_no}, '${exam.e_level }');">삭제</button>
									</td>
								</tr>
							
						</c:forEach>
					
					</c:if>
					<c:if test="${sessionScope.searchExam.get(0).e_no == null }">
						수정할 문제가 없음
					</c:if>
				</table>
			</td>
		</tr>
	</table>		

</body>
</html>