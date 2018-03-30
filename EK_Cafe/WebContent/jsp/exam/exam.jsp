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
	<table id="examTbl">
		<tr>
			<td colspan="4" align="right" class="examEnrollTd">
				<c:if test="${sessionScope.loginMember.m_id == 'admin' }">
					<button onclick="toExaminerEnroll();">출제자 등록/삭제</button>				
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="4"class="examTitldTd">
				영어레벨테스트
			</td>
		</tr>
		<tr>
			<td colspan="4" class="examSubTitldTd">
				아래 4개의 난이도 중 하나를 선택.		
			</td>
		</tr>
		<tr>
			<td align="center">
				<table class="examLevelTbl">
					<tr>
						<td class="examLevelTd" align="center">
							<span>Beginner</span><p>
							<img src="img/beginner.jpg"><br>
							<button onclick="toExamTest('bgn');">START</button><br>
							<c:if test="${permitedId }">
								<button onclick="toExamUpdate('bgn');">UPDATE</button>
							</c:if>
						</td>
					</tr>
				</table>
			</td>
		
			<td align="center">
				<table class="examLevelTbl">
					<tr>
						<td class="examLevelTd" align="center">
							<span>Low-Intermediate</span><p>
							<img src="img/intermediate1.png"><br>
							<button onclick="toExamTest('i_Low');">START</button>
							<c:if test="${permitedId }">
								<button onclick="toExamUpdate('i_Low');">UPDATE</button>
							</c:if>
						</td>
					</tr>
				</table>
			</td>
			
			<td align="center">
				<table class="examLevelTbl">
					<tr>
						<td class="examLevelTd" align="center">
							<span>High-Intermediate</span><p>
							<img src="img/intermediate2.jpg"><br>
							<button onclick="toExamTest('i_High');">START</button>
							<c:if test="${permitedId }">
								<button onclick="toExamUpdate('i_High');">UPDATE</button>
							</c:if>
						</td>
					</tr>
				</table>
			</td>
			
			<td align="center">
				<table class="examLevelTbl">
					<tr>
						<td class="examLevelTd" align="center">
							<span>Advanced</span><p>
							<img src="img/advanced.jpg"><br>
							<button onclick="toExamTest('adv');">START</button>
							<c:if test="${permitedId }">
								<button onclick="toExamUpdate('adv');">UPDATE</button>
							</c:if>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4"class="examBottomTd"></td>
		</tr>
	</table>
</body>
</html>