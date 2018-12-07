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
	
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp" />	
	<!-- table to show application list -->
<div class="content-page">
	<div id="wrapper">
		<div id="header">
			<h2>Applications</h2>
		</div>
	</div>
	
	<div id="container">
			<security:authorize access="hasRole('ADMIN')">

				<!--new button: Add Application ----------------------->						
				<input type="button" value="Add Application"
					onclick="window.location.href='appAddForm'; return false;"
					class="add-button" />
			</security:authorize>
			<!--  add our html table here --------------------------------->
				
				<div class="wrap-table100">
				<div class="table100">
				<div class="col-md-12">
							
			<table class="table table-bordered table-striped table-hover">
			
			
			<thead>
			
				<tr class="table100-head">
				
					<th>Application Name</th>
					<th>Description</th>
					<th>Vendor</th>

					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasRole('ADMIN')">

						<th>Update</th>
						<th>Delete</th>

					</security:authorize>
				
				</tr>
				</thead>
				
				<!-- loop over and print applications -->
				<c:forEach var="tempApplication" items="${applications}">

					<!-- construct an "update" link with application id -->
					<c:url var="updateLink" value="/admin/app/appUpdateForm">
						<c:param name="applicationId" value="${tempApplication.application_Id}" />
					</c:url>

					<!-- construct an "delete" link with application id -->
					<c:url var="deleteLink" value="/admin/app/delete">
						<c:param name="applicationId" value="${tempApplication.application_Id}" />
					</c:url>
					
					<tbody>
					<tr>
						<td>${tempApplication.applicationName}</td>
						<td>${tempApplication.description}</td>
						<td class="text-center">${tempApplication.vendor.vendorName}</td>

						<security:authorize access="hasRole('ADMIN')">

							<td><security:authorize access="hasRole('ADMIN')">
									<!-- display the update link -->
									<a href="${updateLink}">Update</a>
								</security:authorize>
							</td>					 
							
								<security:authorize access="hasRole('ADMIN')">
									<c:if test="${empty tempApplication.getDefectFixDetail()}">
										<td><a href="${deleteLink}"
											onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a></td>										
									</c:if>
									<c:if test="${not empty tempApplication.getDefectFixDetail()}">								
										<td>Delete</td>								
									</c:if>	
								</security:authorize>					
													
						</security:authorize>
					</tr>
				</tbody>
				</c:forEach>
			</table>
			  </div>
			</div>
		</div><!-- content -->
	</div>
</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>