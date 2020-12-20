<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		if(id==null || name == null)
		{
			response.sendRedirect("index.html");
		}else{
	%>
		<h3><%=name %>님 환영합니다.</h3>
		<h4>현재 예약목록</h4>
		<a href="#">추가</a>
	<%
		}
	%>
</body>
</html>