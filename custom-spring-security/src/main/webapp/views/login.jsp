<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
	 	<title>custom-spring-security</title> 	 	
	</head>
	<body>
		<form name="loginForm" action="j_spring_security_check"  method='POST'>
        <div style="width:300px;">
	        <c:if test="${error == '1'}">
	            <p style="margin:5px 5px 5px 40px;color:red">${errormessage}</p>
                <br />
	        </c:if>
	
	        <label for="j_username" style="margin:5px 5px 5px 40px;">Username</label><input type="text" name="j_username" id="j_username" style="margin:5px;" /><br>
	        <label for="j_password" style="margin:5px 5px 5px 41px;">Password</label><input type="password" name="j_password" id="j_password" style="margin:5px;"/><br>
	        
	        <div style="width:140px;margin-left:auto;margin-right:auto;padding:10px;">
		        <!--Primary button-->
		        <a class="SubmitButton" id="login" href="#" onclick="javascript:document.loginForm.submit();"><span>Login</span></a>
		        <!--Secondary button-->
		        <a class="SubmitSecondaryButton" id="clear" href="#" onclick="javascript:document.loginForm.reset();return false;"><span>Clear</span></a>
	        </div>
        </div>
	</form>
	</body>
</html>