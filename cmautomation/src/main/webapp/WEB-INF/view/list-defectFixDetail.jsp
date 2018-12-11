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
	
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">

 <script>
$(document).ready(function() {
	$(function() {
	$("#theSearchName").focus();
	});
});
</script>

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
			<h2>List of Defects</h2>
		</div>
	</div>

	<!-- form---------------------------------------------------->
	<div class="container">
		
		<div class=" col-md-12 table table-bordered">
		
			<!-- button for search -->
			<div  class=" col-md-12 table ">
			
			<div>
			&nbsp;
			</div>			
				<form:form action="search" method="POST" id="search">
					<input type="text" name="theSearchName" id="theSearchName"  placeholder="search"/>
					<input type="submit" value="Search" class="add-button" />
				</form:form>
			</div>
			<div class="row">
				<!--  add our html table here --------------------------------->
				<div class="col-md-12">
					<table class="table table-bordered table-striped table-hover">
						<tr>
							<th class="text-center">Defect Id</th>
							<th class="text-center">Defect Name</th>
							<th class="text-center">Application</th>
							<th class="text-center">Vendor</th>
							<th class="text-center">Current Status</th>
							
							<%-- Only show "Action" column for CMA --%>
							<security:authorize access="hasRole('CMA')">

								<th class="text-center">Update</th>
								<th class="text-center">Delete</th>

							</security:authorize>
							<%-- Only show "Action" column for CMA --%>
							<security:authorize access="hasAnyRole('QA','TSA')">

								<th class="text-center">ViewDetail</th>

							</security:authorize>
						</tr>
						<!-- loop over and print applications -->
						<c:forEach var="tempDefectList" items="${theDefectFixList}">

							<!-- construct an "update" link with application id -->
							<c:url var="updateLink" value="/cma/defect/defectUpdateForm">
								<c:param name="defectId" value="${tempDefectList.defect_Id}" />
							</c:url>

							<!-- construct an "delete" link with application id -->
							<c:url var="deleteLink" value="/cma/defect/delete">
								<c:param name="defectId" value="${tempDefectList.defect_Id}" />
							</c:url>

							<tr>
								<td class="text-center">${tempDefectList.defect_Id}</td>
								<td class="text-center">${tempDefectList.title}</td>
								<td class="text-center">${tempDefectList.application.applicationName}</td>
								<td class="text-center">${tempDefectList.vendor.vendorName}</td>
								<td class="text-center">${tempDefectList.viewStatus}</td>
								
								<security:authorize access="hasRole('CMA')">

									<td><security:authorize access="hasRole('CMA')">
											<!-- display the update link -->
											<a href="${updateLink}">Update</a>
										</security:authorize> 
										
										</td>
										
								<security:authorize access="hasRole('CMA')">
									<c:if test="${tempDefectList.getIsUsedInDdeploymentPlan()==false}">
										<td><a href="${deleteLink}"
												onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a></td>									
									</c:if>
									<c:if test="${tempDefectList.getIsUsedInDdeploymentPlan()==true}">								
										<td>Delete</td>								
									</c:if>	
								</security:authorize>
									
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
						<!-- col-md-12 -->
					</div>
					<!-- col-md-12 table table-bordered -->

				</div>
				<!-- row -->

			</div>
		</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>