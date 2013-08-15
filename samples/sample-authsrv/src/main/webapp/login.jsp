<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="WEB-INF/inc/header.jsp"></jsp:include>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>
<body>
  <div class="mainContent">
	<h1>Login</h1>
    <form name="loginForm" method="POST" action="j_security_check">
        <p><strong>User name: </strong>
            <input type="text" name="j_username" size="25"></p>
        <p><strong>Password: </strong>
            <input type="password" size="15" name="j_password"></p>
        <p>
            <button class="btn" id="loginBtn">Login</button>
    </form> 
  </div>
  <script type="text/javascript" src="/js/login.js"></script>
</body>
</html>