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
	<form action="ExamGradeC?e_level=${sessionScope.searchExam.get(0).e_level }" method="post">
		<table>
			<tr>
				<td align="center">
					<c:choose>
						<c:when test="${sessionScope.searchExam.get(0).e_level == 'bgn'}">
							Beginner Test
						</c:when>
						<c:when test="${sessionScope.searchExam.get(0).e_level == 'i_Low'}">
							Low-Intermediate Test
						</c:when>
						<c:when test="${sessionScope.searchExam.get(0).e_level == 'i_High'}">
							High-Intermediate Test
						</c:when>
						<c:otherwise>
							Advanced
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<c:if test="${sessionScope.searchExam.get(0).e_no != null }">
							<c:forEach var="exam" items="${sessionScope.searchExam }">
								<tr>
									<td class="problemTd">${exam.e_problem }</td>
								</tr>
								<tr>
									<td class="selectTd">
										<input type="radio" name="${exam.e_no }" value="${exam.e_answer1 }" checked="checked"> ${exam.e_answer1 }<br>
										<input type="radio" name="${exam.e_no }" value="${exam.e_answer2 }"> ${exam.e_answer2 }<br>
										<input type="radio" name="${exam.e_no }" value="${exam.e_answer3 }"> ${exam.e_answer3 }<br><p>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${sessionScope.searchExam.get(0).e_no == null }">
							기출 문제 없음
						</c:if>
					</table>
				</td>
			</tr>
			<tr>
				<c:if test="${sessionScope.searchExam.get(0).e_no != null }">
					<td align="right">
						<input type="submit">
					</td>
				</c:if>
			</tr>
		</table>		
	</form>
</body>
</html>