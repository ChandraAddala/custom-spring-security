<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
	 	<title>custom-spring-security</title> 
	</head>
	<body>
		<h1>Change Password</h1>	
		<br />
		<br />
		<s:url var="changePwdURL" value="/changePassword" />
		<sf:form method="POST" action="${changePwdURL}" modelAttribute="changePassword" id="changePassword" name="changePassword">
			
			<table>
				<tr>
					<td>Current Password: </td>
					<td><sf:input path="currentPassword" type="password" id="currentPassword" name="currentPassword" maxlength="20" ></sf:input></td>
				</tr>
				<tr>
					<td>New Password: </td>
					<td><sf:input path="newPassword" type="password" id="newPassword" name="newPassword" maxlength="20" ></sf:input></td>
				</tr>
				<tr>
					<td>Confirm Password: </td>
					<td><sf:input path="confirmNewPassword" type="password" id="confirmNewPassword" name="confirmNewPassword" maxlength="20" ></sf:input></td>
				</tr>
			</table>			
			<br />
			<a class="SubmitButton" id="login" href="#" onclick="javascript:document.changePassword.submit();"><span>Submit</span></a>
		</sf:form>
		
		<br />
		<br />		 
		<s:url value="/j_spring_security_logout" var="logouturl" htmlEscape="true" />
        <a href="${logouturl}">Logout</a>
	</body>
</html>