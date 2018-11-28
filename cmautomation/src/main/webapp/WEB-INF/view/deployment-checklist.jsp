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
			<h2>Deployment Checklist Page</h2>
		</div>
	</div>
	
	<div id="container">
		<div class=" col-md-12 table table-bordered">
			<!-- <div class="left">	<h3 style="margin-top: 10px;">Menu</h3>	</div> -->

			<div class="row">
				<!--  add our html table here --------------------------------->
				<div class="col-md-12">
				<div>&nbsp;</div>
					<table class="table table-bordered table-striped table-hover">
						<tr>
							<th>Deployment Name</th>
							<th>Environment Name</th>
							<th>Deployment Date</th>
							<th>Deployment Status</th>

							<%-- Only show "Action" column for TSA --%>
							<security:authorize access="hasRole('TSA')">

								<th class="text-center">Update</th>
								<th class="text-center">Delete</th>

							</security:authorize>
							
							<security:authorize access="hasAnyRole('QA','CMA')">

								<th class="text-center">ViewDetail</th>

							</security:authorize>
						</tr>
						<!-- loop over and print applications -->
						<c:forEach var="tempDeployList" items="${theDeploymentCheckList}">

							<!-- construct an "update" link with application id -->
							<c:url var="updateLink" value="/tsa/checkList/deploymentCheckListUpdateForm">
								<c:param name="deployCheckListId" value="${tempDeployList.deploymentChecklist_Id}" />
							</c:url>

							<!-- construct an "delete" link with application id -->
							<c:url var="deleteLink" value="/tsa/checkList/delete">
								<c:param name="deployCheckListId" value="${tempDeployList.deploymentChecklist_Id}" />
							</c:url>

							<tr>
								<td class="text-center">${tempDeployList.deploymentPlan.title}</td>
								<td class="text-center">${tempDeployList.deploymentEnvironment.environmentName}</td>
								<td class="text-center">${tempDeployList.actualDeploymentDate}</td>
								<td class="text-center">${tempDeployList.viewIsPackageDeployed}</td>

								<security:authorize access="hasRole('TSA')">

									<td><security:authorize access="hasRole('TSA')">
											<!-- display the update link -->
											<a href="${updateLink}">Update</a>
										</security:authorize></td>
									<td><security:authorize access="hasRole('TSA')">
											<a href="${deleteLink}"
												onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a>
										</security:authorize></td>

								</security:authorize>
								<security:authorize access="hasAnyRole('QA','CMA')">

										<td>
											<a href="${updateLink}">View Detail</a>										
										</td>

								</security:authorize>

							</tr>

						</c:forEach>

					</table>
				</div>
				<!-- col-md-12 -->
			</div>
			<!-- col-md-12 table table-bordered -->
</div>
		</div>
		<!-- row -->

	</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>