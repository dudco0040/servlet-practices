<%@page import="guestbook.Vo.GuestBookVo"%>
<%@page import="guestbook.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String Name = request.getParameter("name");
	String Password = request.getParameter("password");
	String Contents = request.getParameter("message");
	
	GuestBookVo vo = new GuestBookVo();
	vo.setName(Name);
	vo.setPassword(Password);
	vo.setContents(Contents);
	new GuestBookDao().insert(vo);
	
	response.sendRedirect("/guestbook1");
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