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
<title>${title} </title>
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
<div id="wrapper">
		<div id="header">
			<h2>QA CheckList Form</h2>
		</div>
	</div>
<div class="content-page">	
	<!-- form---------------------------------------------------->
	<div class="container">
	<div class="container">
			<form:form method="POST" action="saveQACheckList"
						modelAttribute="theQACheckListDetail">
						<!-- associate this data with Defect id -->
		<table>
				<tbody>					 
					<tr>
						<td>
							<label>	Deployment Name:</label>
						</td>
						<td>
							<form:select  class="form-control" path="deploymentPlan.deployment_Id">
								<c:forEach var="tempDeploymentPlan" items="${deploymentPlan}">
									<form:option value="${tempDeploymentPlan.deployment_Id}">${tempDeploymentPlan.title}</form:option>
								</c:forEach>
							</form:select>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label>Environment :</label>
						</td>
						<td>
							<form:select  class="form-control" path="deploymentEnvironment.environment_Id">
								<c:forEach var="tempEnvironment"
									items="${deploymentEnvironmentList}">
									<form:option value="${tempEnvironment.environment_Id}">${tempEnvironment.environmentName}</form:option>
								</c:forEach>
							</form:select>
						</td>
						<td>
							<label>Test Date :</label>
						</td>
						<td>
							<form:input   type="date" path="testDate" required="true" /><span class="required-field">  </span>
							<form:errors path="testDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Status :</label>
						</td>
						<td>
							<form:select  class="form-control" path="testStatus">
								<form:option value="1">Pass</form:option>
								<form:option value="2">Fail</form:option>
							</form:select>
						</td>
						<td>
							<label>Tested by:</label>
						</td>
						<td>
							<form:input path="testedBy"  type="text" required="true" /><span class="required-field">  </span>
							<form:errors path="testedBy" cssClass="error" />
						</td>
						
					</tr>
					<tr>
						<td>
							<label>Comment :</label>
						</td>
						<td colspan="3">
							<form:textarea rows="3" cols="30"
								path="comment" required="true" /><span class="required-field">  </span>
							<form:errors path="comment" cssClass="error" />
						</td>
					</tr>
					<security:authorize access="hasRole('QA')">
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Save" />
						</td>						
					</tr>
					</security:authorize>
				</tbody>
			</table>
					</form:form>
			</div>
				<!-- centre -->
		</div>
			<!-- row -->
</div>		
</body>
</html>