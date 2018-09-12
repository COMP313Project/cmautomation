<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deployment Plan</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<style>
.error {
	color: red
}
</style>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp" />
	
<div class="content-page">
	<div id="wrapper">
		<div id="header">
			<h2>Deployment Environment</h2>
		</div>
	</div>
	<div id="container">	
		<form:form action="saveEnvironment" modelAttribute="environments" method="POST">
			<!-- need to associate this data with environment_Id -->
			<form:hidden path="environment_Id" />					
			<table>
				<tbody>					
					<tr>
						<td><label>Environment Name:</label></td>
						<td><form:input path="environmentName" required="true"/></td>
						<td><form:errors path="environmentName" cssClass="error" /></td>
												
					</tr>
					<tr>
						<td><label>Description:</label></td>
						<td><form:input path="description" required="true"/></td>
						<td><form:errors path="description" cssClass="error" /></td>
											
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>	
	</div>
</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>