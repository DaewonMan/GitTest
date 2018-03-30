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
	<table>
		<tr>
			<td colspan="2" class="enrollTitleTd">출제자 등록</td>
		</tr>
		<form name="enrollForm" action="ExaminerEnrollC" onsubmit="return toExaminerEnrollCheck();" method="post">
			<tr>
				<td class="enrollUserTd">ID : </td>
				<td class="enrollUserTd"><input name="emr_id"></td>
			</tr>
			<tr>
				<td colspan="2" class="enrollBtnTd">
					<input type="submit" value="출제자 등록">					
				</td>
			</tr>
		</form>
		<c:if test="${sessionScope.searchExaminer.size() > 1}">
			<form name="deleteForm" action="ExaminerDeleteC" onsubmit="return toExaminerDeleteCheck(this);">
				<tr>
					<td colspan="2">
						출제자 삭제
					</td>
				</tr>
				<c:forEach var="examiner" items="${sessionScope.searchExaminer}">
					<c:if test="${examiner.m_id != 'admin' }">
						<tr>
							<td colspan="2" class="deleteUserTd">
								<input type="radio" name="emr_id" value="${examiner.m_id }"> ${examiner.m_id }
							</td>
						</tr>		
					</c:if>
				</c:forEach>
				<tr>
					<td colspan="2" class="deleteBtnTd">
						<input type="submit" value="출제자 삭제">					
					</td>				
				</tr>
			</form>
		</c:if>
		
	</table>		
</body>
</html>