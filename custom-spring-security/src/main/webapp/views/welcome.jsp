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
		 
		<s:url value="/j_spring_security_logout" var="logouturl" htmlEscape="true" />
        <a href="${logouturl}">Logout</a>
	</body>
</html>