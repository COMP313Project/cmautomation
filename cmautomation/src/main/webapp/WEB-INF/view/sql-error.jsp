<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp" />
	<h2>You are not authorized to perform this operation. Please contact Admin</h2>
	<!-- <h4>URL: ${url}</h4>
	<br>
	<h4>Message: ${message}</h4> -->

<!-- 
	Failed URL: ${url} Exception: ${sqlException.message}
	<c:forEach items="${sqlException.stackTrace}" var="ste">    ${ste} 
    </c:forEach>
 -->
</body>
</html>