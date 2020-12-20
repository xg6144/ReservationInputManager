<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "user.UserDAO" %>
<%@ page import = "user.UserVO" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	String user_id = request.getParameter("id");
	String user_pw = request.getParameter("pw");
	
	UserDAO dao = UserDAO.getInstance();
	
	int result = dao.loginUser(user_id, user_pw);
	
	if(result == UserDAO.FAIL)
	{
%>
	<script type="text/javascript">
		alert("아이디가 없습니다.");
		history.go(-1);
	</script>
<%
	}else if(result == UserDAO.NOT_EQUAL_PASSWORD){
%>
	<script type="text/javascript">
		alert("비밀번호가 일치하지 않습니다.");
		history.go(-1);
	</script>
<%
	}else if(result == UserDAO.SUCCESS){
		UserVO vo = dao.getUser(user_id);
		
		if(vo == null){
%>
	<script type="text/javascript">
		alert("존재하지 않는 회원입니다.");
		history.go(-1);
	</script>
<%
		}else{
			String name = vo.getName();
			session.setAttribute("id", user_id);
			session.setAttribute("name", name);
			session.setAttribute("Pass", "ok");
			response.sendRedirect("../main.jsp");
		}
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