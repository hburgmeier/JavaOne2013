<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/inc/header.jsp"></jsp:include>

	<title>Authorize Client Application</title>
</head>
<body>
	<div class="mainContent">
		<h1>Authorize Client Application</h1>
		<p>Do you trust the application ${clientApp.applicationName} ?</p>
		<p>It requested the following permissions:<p>
		<ul>
		<c:forEach var="scopeDescItem" items="${scopeDesc}">
			<li>${scopeDescItem}</li>
		</c:forEach>	
		</ul>
		<form id="approveForm" action="<c:url value="/oauth2/allow" />" method="post">
			<button class="btn" id="approveBtn" type="button">Approve</button>
			<button class="btn" id="denyBtn" type="button">Deny</button>
		</form>
	</div>
	<script type="text/javascript" src="js/auth.js"></script>
</body>
</html>