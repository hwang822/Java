<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "hwang822.onlineide.applcations.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Applications</h1>
<%
	ApplicationDAO dao = new ApplicationDAO();

	String action = request.getParameter("action");
	String name = request.getParameter("name");
	String price = request.getParameter("price");
	String id = request.getParameter("id");
	Application app = new Application();
	
	if("create".equals(action))
	{
		double priceD = Double.parseDouble(price);
		app = new Application(name, priceD);
		dao.create(app);
		
	} else if ("remove".equals(action))
	{
		
		int idInt = Integer.parseInt(id);
		dao.remote(idInt);
	}
	else if ("select".equals(action))
	{
		
		int idInt = Integer.parseInt(id);
		app = dao.selctOne(idInt);
	}

	else if ("update".equals(action))
	{
		
		int idInt = Integer.parseInt(id);
		double priceD = Double.parseDouble(price);		
		app = new Application(name, priceD);
		dao.update(idInt, app);		
	}
	
	//http://localhost:8080/OnlineIDE/applications.jsp?name=Henry&price=2.3&action=
			
			
	
	List<Application> applications = dao.selectAll();
	%>
	
<form action="Applications.jsp">
	<input type="hidden" name="id" value="<%= app.getId() %>"/>
	<table class="table">
		<tr>
			<td><input name="name" class="form-control" value="<%= app.getName() %>"/></td>
			<td><input name="price" class="form-control" value="<%= app.getPrice()  %>"/></td>
			<td>
				<button class="btn btn-scussfully" name="action" value="create">
					Add
				</button>
				<button class="btn btn-scussfully" name="action" value="update">
					Update
				</button>
				
			</td>		
		</tr>
	
	
	<%	
	for(Application ap:applications)		
	{%>	
		<tr>
			<td><%= ap.getName() %></td>
			<td><%= ap.getPrice() %></td>
			<td>
				<a class "btn btn-danger" href="Applications.jsp?action=remove&id=<%=ap.getId() %>">
					Delete
				</a>
			</td>
			<td>
				<a class "btn" href="Applications.jsp?action=select&id=<%=ap.getId() %>">
					Select
				</a>
			</td>			
		</tr>	
	<%		
	}	
	%>	
	</table>	
</form>	
	
</body>
</html>