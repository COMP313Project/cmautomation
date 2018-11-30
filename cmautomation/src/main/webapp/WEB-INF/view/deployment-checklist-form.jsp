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
			<h2>Deployment CheckList Form</h2>
		</div>
	</div>
<div class="content-page">	
	<!-- form---------------------------------------------------->
	<div class="container">
		
			<form:form method="POST" action="saveDeploymentCheckList"
						modelAttribute="theDeploymentCheckList">
					<!-- associate this data with deploymentChecklist_Id -->
						<form:hidden path="deploymentChecklist_Id" />
			<table>
				<tbody>					 
					<tr>
						<td>
							<label>Deployment Name:</label>
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
							<label>Environment:</label>
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
							<label>Actual Deployment Date:</label>
						</td>
						<td>
							<form:input type="date"  class="form-control" path="actualDeploymentDate"	required="true" />
							<form:errors path="actualDeploymentDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Package Deployed:</label>
						</td>
						<td>
							<form:select  class="form-control" path="isPackageDeployed">														
								<form:option value="y">Yes</form:option>
								<form:option value="n">No</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Deployed Package Info:</label>
						</td>
						<td colspan="3">
							<form:textarea class="form-control" rows="3" cols="30"
								path="deployedPackageInfo" required="true" />
								<form:errors path="deployedPackageInfo" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Parameter Deployed:</label>
						</td>
						<td>
							<form:select  class="form-control" path="isParameterDeployed">								
								<form:option value="y">Yes</form:option>
								<form:option value="n">No</form:option>
							</form:select>
							
						</td>
					</tr>
					<tr>
						<td>
							<label>Deployed Parameter Information:</label>
						</td>
						<td colspan="3">
							<form:textarea class="form-control" rows="3" cols="30"
								path="deployedParameterInfo" required="true" />
								<form:errors path="deployedParameterInfo" cssClass="error" />
								<form:errors path="deployedPackageInfo" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Database Executed:</label>
						</td>
						<td>
							<form:select  class="form-control" path="isDBScriptExecuted">								
								<form:option value="y">Yes</form:option>
								<form:option value="n">No</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Database Script Information:</label>
						</td>
						<td colspan="3">
							<form:textarea class="form-control" rows="3" cols="30"
								path="dBScriptInfo" required="true" />
								<form:errors path="dBScriptInfo" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Additional Information:</label>
						</td>
						<td colspan="3">
							<form:textarea class="form-control" rows="3" cols="30"
								path="otherDeploymentInfo" required="true" />
								<form:errors path="otherDeploymentInfo" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Deployed by:</label>
						</td>
						<td colspan="3">
							<form:input type="text"  class="form-control" path="deployedBy" required="true" />
							<form:errors path="deployedBy" cssClass="error" />
						</td>
					</tr>
					<security:authorize access="hasRole('TSA')">
					<tr>
						<td>
						</td>
						<td colspan="3">
							<input type="submit" value="Save" />
						</td>						
					</tr>
					</security:authorize>
				</tbody>
			</table>
		</form:form>
	</div>		
</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>