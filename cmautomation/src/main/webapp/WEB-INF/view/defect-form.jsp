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
	
<script type="text/javascript"
	src="<c:url value="/resources/jquery/1.6/jquery-1.6.1.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui-1.8.10.custom.min.js" />"></script>	
<script type="text/javascript"
	src="<c:url value="https://code.jquery.com/jquery-3.3.1.min.js" />"></script>
	
<script>
$(document).ready(function(){
    $("#status").change(function(){
    	var previousStatus= $("#hiddenStatus").val();
    	
    	var newStatus=$(this).val();
    	
    	if(previousStatus>newStatus)
    	{
    		alert("Status is not valid");
    		$(this).val(previousStatus);
    	}     
    });
    
    $("#defectCreationDate").change(function(){
    	
    	var objDate = new Date();
    	var defectCreationDate = $("#defectCreationDate").val();
    	
    	var fixRecieveDate=$("#fixRecieveDate").val();
    	
    	if(fixRecieveDate!=null && fixRecieveDate!='' && defectCreationDate!=null && defectCreationDate!='' && defectCreationDate>fixRecieveDate)
    	{
    		alert("FixRecieveDate can not be earlier than DefectCreationDate");
    		$("#defectCreationDate").val("");
    	}     
    });
    
		 $("#fixRecieveDate").change(function(){
    	
    	var objDate = new Date();
    	var defectCreationDate = $("#defectCreationDate").val();
    	
    	var fixRecieveDate=$("#fixRecieveDate").val();
    	
    	if(fixRecieveDate!=null && defectCreationDate!=null && fixRecieveDate!='' && defectCreationDate!='' && defectCreationDate>fixRecieveDate)
    	{
    		alert("FixRecieveDate can not be earlier than DefectCreationDate");
    		$("#fixRecieveDate").val("");
    	}     
    });
		 
		 
		 $("#reviewDate").change(function(){
    	
    	var objDate = new Date();
    	var reviewDate = $("#reviewDate").val();
    	
    	var fixRecieveDate=$("#fixRecieveDate").val();
    	
    	if(fixRecieveDate!=null && reviewDate!=null && fixRecieveDate!='' && reviewDate!='' && fixRecieveDate>reviewDate)
    	{
    		alert("Review Date can not be earlier than Fix Recieve Date");
    		$("#reviewDate").val("");
    	}     
    });
})

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
			<h2>Defect Fix Detail</h2>
		</div>
	</div>

	<!-- form---------------------------------------------------->
	<div class="container">
	
	<form:form method="POST" action="saveDefectFixDetail" modelAttribute="theDefectFixDetail">
						<!-- associate this data with Defect id -->
						<form:hidden path="defect_Id" />	
						
		<table>
				<tbody>
					 
					<tr>
						<td>
							<label>Title :</label>
						</td>
						<td colspan="3">
							<form:input path="title" placeholder="Defect Title" required="true"/>
							<form:errors path="title" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Application :</label>
						</td>
						<td>
							<form:select  class="form-control" path="application.application_Id">
								<c:forEach var="tempApplication" items="${applications}">
									<form:option value="${tempApplication.application_Id}">${tempApplication.applicationName}</form:option>
								</c:forEach>
							</form:select>
						</td>
						<td>
							<label>Vendor :</label>
						</td>
						<td>
							<form:select  class="form-control" path="vendor.vendor_Id">
								<c:forEach var="tempVendors" items="${vendors}">
									<form:option value="${tempVendors.vendor_Id}">${tempVendors.vendorName}</form:option>
								</c:forEach>
							</form:select>
						</td>						
					</tr>
					<tr>
						<td>
							<label>Defect Created On :</label>							
						</td>
						<td>
							<form:input type="date" path="defectCreationDate" required="true"/>
							<form:errors path="defectCreationDate" cssClass="error" />
						</td>
						<td>
							<label>Fix Received On :</label>							
						</td>
						<td>
							<form:input type="date" path="fixRecieveDate"/>
							<form:errors path="fixRecieveDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Dependent Defect :</label>	
						</td>
						<td>													
							<form:input path="dependentDefect_Id"
								placeholder="Dependent defect Id" />
						</td>
						<td>								
							<label>Status :</label>						
						</td>
						<td>
							<form:select  class="form-control" path="status">
								<form:option value="1">Waiting For Fix</form:option>
								<form:option value="2">Fix Received</form:option>
								<form:option value="3">Deployed in SDF</form:option>
								<form:option value="4">Deployed in IST1</form:option>
								<form:option value="5">Deployed in IST2</form:option>
								<form:option value="6">Deployed in PROD</form:option>
								<form:option value="7">Deployed in Training</form:option>
								<form:option value="8">Deployed in DR</form:option>
								<form:option value="9">Closed</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Impact Component :</label>		
						</td>
						<td>							
							<form:input path="impactedComponent"
								placeholder="Impacted Components" required="true"/>
								<form:errors path="impactedComponent" cssClass="error" />
						</td>
						<td>
							<label>Package Location :</label>
						</td>
						<td>
							<form:input path="deploymentPackageLocation"
								placeholder="Paste link" required="true"/>
								<form:errors path="deploymentPackageLocation" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Test Case Provided :</label>
						</td>
						<td>
							<form:select  class="form-control" path="isTestCaseprovided">
								<form:option value="1">Provided</form:option>
								<form:option value="2">Not provided</form:option>
							</form:select>
						</td>
						<td>
							<label>Instruction Provided :</label>
						</td>
						<td>							
							<form:select  class="form-control" path="isDeploymentInstructionProvided">
								<form:option value="1">Provided</form:option>
								<form:option value="2">Not provided</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Review Date :</label>
						</td>
						<td colspan="3">							
							<form:input type="date"  class="form-control" path="reviewDate"/>
							<form:errors path="reviewDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Description :</label>
						</td>
						<td colspan="3">
							<form:textarea class="form-control" rows="4" cols="50"
								path="description" required="true"/>
								<form:errors path="description" cssClass="error" />
						</td>					
					</tr>
					<security:authorize access="hasRole('CMA')">
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