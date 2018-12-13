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
<title>${title}</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp" />	
	<!-- table to show application list -->
<div class="content-page">
	<div id="wrapper">
		<div id="header">
			<h2>Deployment Plan List</h2>
		</div>
	</div>	
	<div class="container">
	<div>&nbsp;</div>
		<table class="table table-bordered table-striped table-hover">
			<tr>				
				<th>Deployment Title</th>
				<th>Application Name</th>
				<th>Defects</th>

				<%-- Only show "Action" column for managers or admin --%>
				<security:authorize access="hasRole('CMA')">
					<th>Update</th>
					<th>Delete</th>
				</security:authorize>
				<security:authorize access="hasAnyRole('QA','TSA')">

					<th class="text-center">ViewDetail</th>

				</security:authorize>
			</tr>
			<!-- loop over and print applications -->
			<c:forEach var="tempDeploymentPlan" items="${theDeploymentPlanList}">

				<!-- construct an "update" link with application id -->
				<c:url var="updateLink"
					value="/cma/deploymentPlan/deploymentPlanUpdateForm">
					<c:param name="deployment_Id"
						value="${tempDeploymentPlan.deployment_Id}" />
				</c:url>

				<!-- construct an "delete" link with application id -->
				<c:url var="deleteLink" value="/cma/deploymentPlan/deleteDeploymentPlan">
					<c:param name="deployment_Id"
						value="${tempDeploymentPlan.deployment_Id}" />
				</c:url>

				<tr>
					
					<td>${tempDeploymentPlan.title}</td>
					<td>${tempDeploymentPlan.application.applicationName}</td>
					<td><ul>
					<c:forEach var="tempDeploymentDefects" items="${tempDeploymentPlan.listDeploymentDefects}">
						<li>${tempDeploymentDefects.title}</li>
					</c:forEach>
					</ul>
					
					</td>

					<security:authorize access="hasRole('CMA')">

						<td><security:authorize access="hasRole('CMA')">
								<!-- display the update link -->
								<a href="${updateLink}">Update</a>
							</security:authorize></td>
						<td><security:authorize access="hasRole('CMA')">
								<a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a>
							</security:authorize></td>

					</security:authorize>
					<security:authorize access="hasAnyRole('QA','TSA')">

						<td>
							<a href="${updateLink}">View Detail</a>										
						</td>
					</security:authorize>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>