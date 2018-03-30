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
	<form action="DownloadRegC" method="post" enctype="multipart/form-data"
		name="downloadForm">
		<table id="registrationTbl">
			<c:choose>
				<c:when test="${sessionScope.loginMember != null }">
					<tr>
						<td align="center"><input name="d_title" placeholder="제목 입력"
							maxlength="10" autocomplete="off"></td>
					</tr>
					<tr>
						<td align="center"><input name="d_file" type="file"></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="등록"></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="center" id="drBlankTitleTd">자료실</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
	<table id="downloadArea">
		<tr>
			<td valign="top">
			<c:forEach var="d" items="${Downloads }">
					<table class="downloedItem">
						<tr>
							<c:choose>
								<c:when test="${sessionScope.loginMember.m_id == d.d_id }">
									<td align="center" colspan="3" class="downloadTitle">${d.d_title }</td>
								</c:when>
								<c:otherwise>
									<td align="center" colspan="3" class="downloadTitle">${d.d_title }</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td rowspan="2" class="downloadImgTd" align="center"><a
								href="etc/${d.d_file }"> <img src="img/d.png">
							</a></td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성자 : ${d.d_id }</td>
						</tr>
						<tr>
							<td align="right">
							<fmt:formatDate value="${d.d_date }"
									pattern="yyyy-MM-dd" />&nbsp;</td>
							<td align="right">
								 <button onclick="toDownloadDelete(${d.d_no}, '${d.d_file }');">삭제</button>
							</td>
						</tr>
					</table>
				</c:forEach></td>
		</tr>
	</table>
</body>
</html>