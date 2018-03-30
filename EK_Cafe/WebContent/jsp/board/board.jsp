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
	<form action="BoardWriteC" name="boardWriteForm" onsubmit="return boardWriteCheck();">
		<table id="boardWriteTbl">
			<c:choose>
				<c:when test="${sessionScope.loginMember != null }">
					<tr>
						<td id="blankTd" align="center">게시글</td>
						<td id="boardWriteTxtTd" align="center">
							<textarea name="b_txt" autofocus="autofocus" id="boardWriteTxtTextarea" placeholder="What's your Q?" maxlength="200"></textarea>
						</td>
						<td id="boardWriteBtnTd">
							<input id="boardWriteBtn" type="submit" value="작성">
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="center" id="boardTitleTd">글 작성은 로그인 후 가능합니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
	<table id="boardArea">
		<tr>
			<td align="center" id="pageLTd">
				<c:if test="${curPageNo != 1 }">
					<a class="pageL" href="BoardPageC?p=${curPageNo-1 }">◀</a>
				</c:if>			
			</td>
			<td align="center"><c:forEach var="b" items="${lists }">
					<table id="boardList" cellpadding="0" cellspacing="0">
						<tr>
							<td rowspan="5" class="boardImgTd" valign="top" align="center">
								<img onclick="searchBoard(img/'${b.b_id}');" class="boardImg" src="img/${b.m_img }">
							</td>
							<td class="boardId">작성자 : ${b.b_id }</td>
						</tr>
						<tr>
							<td class="boardDate" align="right" style="font-weight: 900;">
							<fmt:formatDate	value="${b.b_date }" type="both" dateStyle="long" timeStyle="short" /> &nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td class="boardTxt">${b.b_txt }</td>
						</tr>
						<tr>
							<td class="boardCmtArea" align="right">
								<c:forEach var="r"	items="${b.b_repls }">
									<div class="boardCmt">
										<span class="boardCmtId">${r.r_id }</span> : ${r.r_txt } -&nbsp;
										<span class="boardCmtDate">
											<fmt:formatDate value="${r.r_date }" type="both" dateStyle="short" />
										</span>
										<c:if test="${r.r_id == sessionScope.loginMember.m_id }">
											<button class="boardCmtBtn" onclick="toDeleteRepl(${r.r_no});">삭제</button>&nbsp;
										</c:if>
										<p>
									</div>
								</c:forEach>
								<c:if test="${sessionScope.loginMember!=null }">
									<form onsubmit="return writeRepCheck(this);" action="BoardWriteReplC">
										<span class="boardCmtId">${sessionScope.loginMember.m_id }</span>
										<input type="hidden" name="r_b_no" value="${b.b_no }">
										<input class="rinput" name="r_txt">
										 <input	class="rinputBtn" type="submit" value="쓰기">&nbsp;
									</form>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="boardBtnTd" colspan="2" align="right">
								<c:if test="${b.b_id == sessionScope.loginMember.m_id }">
									<button onclick="toUpdateBoard(${b.b_no});" class="boardBtn">수정</button>
									<button onclick="toDeleteBoard(${b.b_no});" class="boardBtn">삭제</button>&nbsp;
								</c:if>
							</td>
						</tr>
					</table>
				</c:forEach>
			</td>
			<td align="center" id="pageRTd">
				<c:if test="${curPageNo != pageCount }">
					<a class="pageR" href="BoardPageC?p=${curPageNo+1 }">▶</a>
				</c:if>
			</td>
		</tr>
	</table>

</body>
</html>