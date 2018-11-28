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
	     
	        $("#planDate").change(function(){
	        	
	        	var objDate = new Date();
	        	var planDate = $("#planDate").val();
	        	
	        	if(objDate>planDate)
	        	{
	        		alert("Plan date can not be earlier than current date");
	        		$("#planDate").val("");
	        	}     
	        });
	        
 			 $("#dev_DeploymentDate").change(function(){
	        	
	        	var objDate = new Date();
	        	var planDate = $("#planDate").val();
	        	var dev_DeploymentDate = $("#dev_DeploymentDate").val();

	        	if(dev_DeploymentDate<planDate)
	        	{
	        		alert("DEV Deployment date can not be earlier than plan date");
	        		$("#dev_DeploymentDate").val("");
	        	}
	        	else if(dev_DeploymentDate<objDate)
	        	{
	        		alert("DEV Deployment date can not be earlier than current date");
	        		$("#dev_DeploymentDate").val("");
	        	}
	        	compareDate();
	        	
	        });
 			 
 			 $("#sdf_DeploymentDate").change(function(){
 	        	
 	        	var objDate = new Date();
 	        	var planDate = $("#planDate").val();
 	        	var sdf_DeploymentDate = $("#sdf_DeploymentDate").val();

 	        	if(sdf_DeploymentDate<planDate)
 	        	{
 	        		alert("SDF Deployment date can not be earlier than plan date");
 	        		$("#sdf_DeploymentDate").val("");
 	        	}
 	        	else if(sdf_DeploymentDate<objDate)
 	        	{
 	        		alert("SDF Deployment date can not be earlier than current date");
 	        		$("#sdf_DeploymentDate").val("");
 	        	}
 	        	compareDate();
 	        	
 	        });
 			 
 			 $("#ist1_DeploymentDate").change(function(){
  	        	
  	        	var objDate = new Date();
  	        	var planDate = $("#planDate").val();
  	        	var ist1_DeploymentDate = $("#ist1_DeploymentDate").val();

  	        	if(ist1_DeploymentDate<planDate)
  	        	{
  	        		alert("IST1 Deployment date can not be earlier than plan date");
  	        		$("#ist1_DeploymentDate").val("");
  	        	}
  	        	else if(ist1_DeploymentDate<objDate)
  	        	{
  	        		alert("IST1 Deployment date can not be earlier than current date");
  	        		$("#ist1_DeploymentDate").val("");
  	        	}
  	        	compareDate();
  	        });
 			 
 			 
 			 $("#ist2_DeploymentDate").change(function(){
  	        	
  	        	var objDate = new Date();
  	        	var planDate = $("#planDate").val();
  	        	var ist2_DeploymentDate = $("#ist2_DeploymentDate").val();

  	        	if(ist2_DeploymentDate<planDate)
  	        	{
  	        		alert("IST2 Deployment date can not be earlier than plan date");
  	        		$("#ist2_DeploymentDate").val("");
  	        	}
  	        	else if(ist2_DeploymentDate<objDate)
  	        	{
  	        		alert("IST2 Deployment date can not be earlier than current date");
  	        		$("#ist2_DeploymentDate").val("");
  	        	}  	  
  	        	compareDate();
  	        });
 			 
 			 
 			 $("#prod_DeploymentDate").change(function(){
  	        	
  	        	var objDate = new Date();
  	        	var planDate = $("#planDate").val();
  	        	var prod_DeploymentDate = $("#prod_DeploymentDate").val();

  	        	if(prod_DeploymentDate<planDate)
  	        	{
  	        		alert("PROD Deployment date can not be earlier than plan date");
  	        		$("#prod_DeploymentDate").val("");
  	        	}
  	        	else if(prod_DeploymentDate<objDate)
  	        	{
  	        		alert("PROD Deployment date can not be earlier than current date");
  	        		$("#prod_DeploymentDate").val("");
  	        	}  	 
  	        	compareDate();
  	        });
  			  			 
 			 function compareDate()
 			 {
 				var objDate = new Date();
	        	var planDate = $("#planDate").val();
	        	var dev_DeploymentDate = $("#dev_DeploymentDate").val();

	        	var sdf_DeploymentDate = $("#sdf_DeploymentDate").val();

	        	var ist1_DeploymentDate = $("#ist1_DeploymentDate").val();

	        	var ist2_DeploymentDate = $("#ist2_DeploymentDate").val();

	        	var prod_DeploymentDate = $("#prod_DeploymentDate").val();
	        	
	        	if(dev_DeploymentDate!=null && sdf_DeploymentDate!=null && dev_DeploymentDate!='' && sdf_DeploymentDate!='' && (dev_DeploymentDate>sdf_DeploymentDate))
	        	{
	        		alert("SDF Deployment date can not be earlier than DEV deployment date");
	        		$("#sdf_DeploymentDate").val("");
	        		return;
	        	}
	        	else if(sdf_DeploymentDate!=null && ist1_DeploymentDate!=null && sdf_DeploymentDate!='' && ist1_DeploymentDate!='' && (sdf_DeploymentDate>ist1_DeploymentDate))
	        	{
	        		alert("IST1 Deployment date can not be earlier than SDF deployment date");
	        		$("#ist1_DeploymentDate").val("");
	        		return;
	        	}  
	        	else if(ist1_DeploymentDate!=null && ist2_DeploymentDate!=null && ist1_DeploymentDate!='' && ist2_DeploymentDate!='' && (ist1_DeploymentDate>ist2_DeploymentDate))
	        	{
	        		alert("IST2 Deployment date can not be earlier than IST1 deployment date");
	        		$("#ist2_DeploymentDate").val("");
	        		return;
	        	}  
	        	else if(prod_DeploymentDate!=null && ist2_DeploymentDate!=null && prod_DeploymentDate!='' && ist2_DeploymentDate!='' && (ist2_DeploymentDate>prod_DeploymentDate))
	        	{
	        		alert("PROD Deployment date can not be earlier than IST2 deployment date");
	        		$("#prod_DeploymentDate").val("");
	        		return;
	        	}  
 			 }
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
			<h2>Deployment Plan</h2>
		</div>
	</div>

	<!-- form---------------------------------------------------->
	<div class="container">
	<form:form method="POST" action="saveDeploymentPlan"
						modelAttribute="deploymentPlanDetail">
		<form:hidden path="deployment_Id" />
		<table>
				<tbody>					 
					<tr>
						<td>
							<label>	Deployment Title :</label>
						</td>
						<td>
							<form:input path="title"  class="form-control" placeholder="Deployment Title"
								required="true" />
							<form:errors path="title" cssClass="error" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label>Application :</label>
						</td>
						<td colspan="3">
							<form:select  class="form-control" path="application.application_Id" id="applications">
								<c:forEach var="tempApplication" items="${applications}">
									<form:option value="${tempApplication.application_Id}">${tempApplication.applicationName}
                					</form:option>
								</c:forEach>
							</form:select>						
						</td>
					</tr>
					<tr>
						<td>
							<label>Defects to Deploy :</label>
						</td>
						<td colspan="3">
							<form:select  class="form-control" path="defects" required="true">
								<form:options items="${listDefectFixDetail}"
									itemValue="defect_Id" itemLabel="title" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Plan Date :</label>
						</td>
						<td>
							<form:input type="date" class="form-control" path="planDate" placeholder="yyyy-mm-dd"
								required="true" />
							<form:errors path="planDate" cssClass="error" />
						</td>
						<td>
							<label>Dev Deployment Date :</label>
						</td>
						<td>
							<form:input type="date" class="form-control" path="dev_DeploymentDate"
								placeholder="yyyy-mm-dd" required="true" />
							<form:errors path="dev_DeploymentDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>SDF Deployment Date :</label>
						</td>
						<td>
							<form:input type="date" class="form-control" path="sdf_DeploymentDate"
								placeholder="yyyy-mm-dd" required="true" />
							<form:errors path="sdf_DeploymentDate" cssClass="error" />
						</td>
						<td>
							<label>IST1 Deployment Date :</label>
						</td>
						<td>
							<form:input type="date" class="form-control" path="ist1_DeploymentDate"
								placeholder="yyyy-mm-dd" required="true" />
							<form:errors path="ist1_DeploymentDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>IST2 Deployment Date :</label>
						</td>
						<td>
							<form:input type="date" class="form-control" path="ist2_DeploymentDate"
								placeholder="yyyy-mm-dd" required="true" />
							<form:errors path="ist2_DeploymentDate" cssClass="error" />
						</td>
						<td>
							<label>PROD Deployment Date :</label>
						</td>
						<td>
							<form:input type="date" class="form-control" path="prod_DeploymentDate"
								placeholder="yyyy-mm-dd" required="true" />
							<form:errors path="prod_DeploymentDate" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<label>Comment :</label>
						</td>
						<td colspan="3">
							<form:textarea path="comment" class="form-control" rows="3"
								cols="50" required="true" />
							<form:errors path="comment" cssClass="error" />
						</td>
					</tr>
					<security:authorize access="hasRole('CMA')">
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
</div>
<div>
<jsp:include page="/WEB-INF/view/footer.jsp" />
</div>