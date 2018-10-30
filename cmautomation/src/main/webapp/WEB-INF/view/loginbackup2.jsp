<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">


	<link rel="icon" href="src/main/webapp/resources/images/icons/favicon.ico"/>

	<link rel="stylesheet" href="src/main/webapp/resources/vendor/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" href="src/main/webapp/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet"  href="src/main/webapp/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet"  href="src/main/webapp/resources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet"  href="src/main/webapp/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet"  href="src/main/webapp/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet"  href="src/main/webapp/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet"  href="src/main/webapp/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet"  href="src/main/webapp/resources/css/util.css">
	<link rel="stylesheet"  href="src/main/webapp/resources/css/main.css">
<!--===============================================================================================-->
  <script>
$(document).ready(function() {
	$(function() {
	$("#username").focus();
	});
	});
</script>
</head>
<body>
	
	<div class="limiter" id="loginbox">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/authenticateTheUser" 
				method="POST" class="form-horizontal">
					<span class="login100-form-title p-b-32">
						Account Login
					</span>
 <!-- Place for messages: error, alert etc ... -->
 <div class="container-login100-form-btn  p-r-5">
	<div class="container-login100-form-btn  p-r-5">
		<div class="container-login100-form-btn  p-r-5">
				
		<!-- Check for login error -->

		<c:if test="${param.error != null}">
			<div class="alert alert-danger container-login100-form-btn  p-r-5">
				Invalid username and/or password.
			</div>				
		</c:if>																	
				<!-- 	            
			<div class="alert alert-success col-xs-offset-1 col-xs-10">
				You have been logged out.
			</div>
			 -->
					<span class="container-login100-form-btn  p-r-5">
						Username
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
						<input class="input100" type="text" name="username" id="username" placeholder="username" tabindex="1" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="password" placeholder="password"  tabindex="2" >
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label>
						</div>

						<div>
							<a href="#" class="txt3">
								Forgot Password?
							</a>
						</div>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	

	<script type="text/javascript" src="src/main/webapp/resources/jquery/jquery-3.2.1.min.js"  ></script>

	<script type="text/javascript" src="src/main/webapp/resources/vendor/animsition/js/animsition.min.js"></script>

	<script type="text/javascript" src="src/main/webapp/resources/vendor/bootstrap/js/popper.js"></script>
	<script type="text/javascript" src="src/main/webapp/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="src/main/webapp/resources/vendor/select2/select2.min.js"></script>

	<script type="text/javascript" src="src/main/webapp/resources/vendor/daterangepicker/moment.js"></script>
	<script type="text/javascript" src="src/main/webapp/resources/vendor/daterangepicker/daterangepicker.js"></script>

	<script type="text/javascript" src="src/main/webapp/resources/vendor/countdowntime/countdowntime.js"></script>

	<script type="text/javascript" src="src/main/webapp/resources/js/main.js"></script>

</body>
</html>