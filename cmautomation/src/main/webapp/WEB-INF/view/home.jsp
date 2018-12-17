
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
<title>Home</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">


<style type="text/css">
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
				<h2></h2>
			</div>
		</div>

		<div id="container">

			<h3>
				Welcome to CM Automation Web App
				<security:authentication property="principal.username" />
				!
			</h3>
			&nbsp;
			<div class="container">
				<div class=" col-md-12 table table-bordered">
					<p>Thank you for choosing CMAutomation Tool to make your
						business more efficient and productive. This tool is an:</p>
					<ul>
						<li>Automated system for user management</li>
						<li>Automated system to store and retrieve fix information,
							plan, and maintain deployment procedure</li>
						<li>Search functionality to retrieve specific and relevant
							information quickly</li>
						<li>Relational database to store and manipulate data</li>
						<li>Test data</li>
					</ul>
					<p>As a CMA Role:</p>
					<ul>
						<li>You are the primary user of the app</li>
						<li>You will create/update/delete entries to the app.</li>
						<li>You will have full access to the app except specific
							update permission to UIs related to deployment and QA
							information.</li>
					</ul>
					<p>As an Admin:</p>
					<ul>
						<li>You are able to create/update/remove all other users to
							the system</li>
						<li>You are able to assign roles to users</li>
						<li>You are able to have full access to the app</li>
					</ul>
					<p>As a TSA:</p>
					<ul>
						<li>You will have write access to UIs related to deployment
							information</li>
						<li>You will have read only access to other area</li>
					</ul>
					<p>As a QA:</p>
					<ul>
						<li>You will ensure that the defects/changes meet various
							quality check points after deployment in any environment</li>
						<li>You will be able to authorize pass or fail regarding a
							test</li>
						<li>You are given limited access and are able to update
							quality related information in the app</li>
					</ul>
					<p>As a User:</p>
					<ul>
						<li>You will be able to review all or some of the information
							stored in the system</li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div>
		<jsp:include page="/WEB-INF/view/footer.jsp" />
	</div>
</body>
</html>