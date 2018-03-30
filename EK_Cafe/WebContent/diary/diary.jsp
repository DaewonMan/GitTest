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
				<h1>���� �ϱ⾲��</h1>
				<p>
					<em>�����ϱ� ����</em>��?
				</p>
				<p>
					�������� ��� ����, �ܾ���� �����Ӱ� �����غ�����.<br> �� �ܾ�� �ڲ� ���ٺ��� ������ �ǰ� �Ƿ���
					�˴ϴ�!
				</p><p>
			</td></tr>
			<tr>
				<td align="right" height="20px" colspan="2">���� - ${r }<p></td>
		</tr>
			<tr>
						<td id="diarySearchTd" align="right" colspan="2">
							<form action="DiarySearchC" name="searchForm"
								onsubmit="return searchCmtCheck();">
								<input id="diarySearch" name="" type="text">
								<input id="diarySearchBtn" type="submit" value="�˻�"></input>
								
							</form>
						</td></tr>

		<tr>
			<td id="diaryPageArea" align="center" colspan="2">
				<table id="diaryTbl" cellpadding="0" cellspacing="0">
					
						<tr>
						<td style="width:50%; height: 30px;" align="center">����</td>
						<td style="width: 10%" align="center">�ۼ���</td>
						<td align="center">�ۼ���</td>
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
			<td><a href="DiaryWriteC">���ۼ�</a></td>
		</tr>

	</table>
</body>
</html>