<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
	 	<title>custom-spring-security</title> 
	</head>
	<body>
		<h1>${message}</h1>	
		<br />
		<br />

		<h4>User Info</h4>

		<table>
			<tr>
				<td>User name:</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td>First name:</td>
				<td>${user.firstName}</td>
			</tr>
			<tr>
				<td>Last name:</td>
				<td>${user.lastName}</td>
			</tr>
			<tr>
				<td>DOB:</td>
				<td>${user.dateOfBirth}</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>${user.emailAddress}</td>
			</tr>

		</table>
		
		<br />
		<br />
		<br />
		<br />
		 
		<s:url value="/j_spring_security_logout" var="logouturl" htmlEscape="true" />
        <a href="${logouturl}">Logout</a>
	</body>
</html>