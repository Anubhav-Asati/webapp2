<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>List of Users</h1>
	
	<table border="1">
	    <tr><th>userId</th><th>userName</th><th>password</th></tr>
		<c:forEach var="e" items="${userList}">
			<tr><td>${e.userList}</td><td>${e.userName }</td><td>${e.password}</td></tr>
		</c:forEach>	
	
	</table>

</body>
</html>