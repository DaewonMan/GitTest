<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
</head>
<body>
	<table id="diaryArea" cellpadding="0" cellspacing="0">
		<tr>
			<td id="diaryTitle" align="center" colspan="2">
				<h1>영어 일기쓰기</h1>
				<p>
					<em>영어일기 쓰기</em>는?
				</p>
				<p>
					여러분이 배운 문장, 단어들을 자유롭게 연습해보세요.<br> 한 단어라도 자꾸 쓰다보면 습관이 되고 실력이
					됩니다!
				</p><p>
			</td></tr>
			<tr>
				<td align="right" height="20px" colspan="2">상태 - ${r }<p></td>
		</tr>
			<tr>
						<td id="diarySearchTd" align="right" colspan="2">
							<form action="DiarySearchC" name="searchForm"
								onsubmit="return searchCmtCheck();">
								<input id="diarySearch" name="" type="text">
								<input id="diarySearchBtn" type="submit" value="검색"></input>
								
							</form>
						</td></tr>

		<tr>
			<td id="diaryPageArea" align="center" colspan="2">
				<table id="diaryTbl" cellpadding="0" cellspacing="0">
					
						<tr>
						<td style="width:50%; height: 30px;" align="center">제목</td>
						<td style="width: 10%" align="center">작성자</td>
						<td align="center">작성일</td>
					</tr>
					<c:forEach var="d" items="${diaries }">
						<tr>
							<td><a href="DiaryDetailC?d_no=${d.d_no }">${d.d_title }</a></td>
							<td>${d.d_id }</td>
							<td><fmt:formatDate value="${d.d_date }" type="both"
									dateStyle="short" timeStyle="short" /></td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center"><c:forEach var="p" begin="1"
					end="${pageCount }">
				&nbsp;<a href="DiaryPageC?p=${p }">${p }</a>&nbsp;
			
			</c:forEach></td>
			<td><a href="DiaryWriteC">글작성</a></td>
		</tr>

	</table>
</body>
</html>