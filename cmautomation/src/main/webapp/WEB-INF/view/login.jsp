<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link type="text/css" rel="icon"
	href="${pageContext.request.contextPath}/resources/images/icons/favicon.ico" />

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/util.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<!--===============================================================================================-->

<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#username").focus();
		});
	});
</script>
</head>

<body>

	<div>

		<div class="limiter" id="loginbox">
			<div class="container-login100">
				<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
					<form:form class="login100-form validate-form flex-sb flex-w"
						action="${pageContext.request.contextPath}/authenticateTheUser">
						<span class="login100-form-title p-b-32"> Welcome to CMAutomation </span>
						<div class="form-group">
							<div class="col-xs-15">
								<div>
									<c:if test="${param.error != null}">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10" style="text-align: center">
											Invalid username and/or password.</div>
									</c:if>
								</div>
							</div>
						</div>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								name="username" id="username" placeholder="username"
								tabindex="1" class="form-control">
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="password"
								name="password" placeholder="password" tabindex="2"
								class="form-control">
						</div>


						<!-- Login/Submit Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Login</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	
</body>
</html>