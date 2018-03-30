<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>	
	
		<table id="noMemberTbl">
			<tr>
				<td colspan="2">메니저 : Copain</td>
			</tr>
			<tr>
				<td>since&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
				<td style="font-size: 9pt;"> <button onclick="toIntroduce();">카페 소개</button>  </td>
			</tr>
 			<tr>
				<td colspan="2">2018.03.22.</td>
			</tr>
 			<tr>			
				<td colspan="2">회원 수 : 3명 </td>
			</tr>
 			<tr>			
				<td colspan="2">게시글 수 : 0개 </td><br>
				${r }
			</tr>
		</table>
</body>
</html>