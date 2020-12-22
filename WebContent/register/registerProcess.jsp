<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "user.UserDAO" %>
	<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	
	UserDAO dao = UserDAO.getInstance();
	
	int result = dao.insertUser(id, pw, name);
	
	if(result == UserDAO.SUCCESS)
	{
	%>
	<script type="text/javascript">
		alert("회원가입에 성공했습니다.");
		location.href="../login/login.html";
	</script>
	<%
	}else if(result == UserDAO.FAIL){
	%>
	<script type="text/javascript">
		alert("이미 존재하는 계정입니다.");
		location.href(-1);
	</script>
	<%
	}else{
		
	}
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>