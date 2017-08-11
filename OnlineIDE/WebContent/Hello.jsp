<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "hwang822.onlineide.applcations.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World from JSP</h1>
	<%
		ApplicationDAO hello = new ApplicationDAO();
		String message = hello.sayHello("Henry");
		for(int i=0; i<10; i++){%>
			<li> <%=message%> Server Side Rendering Rocks <%= i %>  </li>
		<%} %>
</body>
</html>