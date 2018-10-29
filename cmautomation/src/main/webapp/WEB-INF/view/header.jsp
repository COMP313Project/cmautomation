<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	
	<style> .error{color:red} </style>
	
	<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-inverse">

  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">CM Automation</a>
    </div>
    <ul class="nav navbar-nav">
      	<li><a href="${pageContext.request.contextPath}/">Home</a></li>
      
	  	<security:authorize access="hasAnyRole('CMA','QA','TSA')">
	  	<li class="${title=='listDefectFixDetail' || title=='defectForm'? 'active':''}" ><a name="ddlManageDefects" id="ddlManageDefects" class="dropdown-toggle" data-toggle="dropdown" href="#">ManageDefects<span class="caret"></span></a>
		   <ul class="dropdown-menu">
				<!-- Add link to point to Leaders..this is for the managers	 -->
				<li class="${title=='listDefectFixDetail'? 'active':''}" }><a id="lnkDefectList" name="lnkDefectList" href="${pageContext.request.contextPath}/cma/defect/list">Defect List</a></li>
				
				<li class="${title=='defectForm'? 'active':''}" >
				<security:authorize access="hasRole('CMA')">
				<a id="lnkDefectAddForm" name="lnkDefectAddForm" href="${pageContext.request.contextPath}/cma/defect/defectAddForm">Defect Detail</a>
				</security:authorize>
				</li>
				
		   </ul>
	   	</li>
	  	</security:authorize>
	   
	    <security:authorize access="hasAnyRole('CMA','QA','TSA')">
	   	<li class="dropdown"><a id="ddlManageDeployment" name="ddlManageDeployment" class="dropdown-toggle" data-toggle="dropdown" href="#">ManageDeployment<span class="caret"></span></a>
	   		<ul class="dropdown-menu">
	   			<li  class="${title=='DeploymentSchedule'? 'active':''}">
	   			<a id="lnkDeploymentSchedule" name="lnkDeploymentSchedule" href="${pageContext.request.contextPath}/cma/deploymentPlan/list">Deployment Schedule</a>
	   			</li>
      			<li class="${title=='PlanDeployment'? 'active':''}">
      			<security:authorize access="hasRole('CMA')">
      			<a  id="lnkPlanDeployment" name="lnkPlanDeployment" href="${pageContext.request.contextPath}/cma/deploymentPlan/addForm">Plan Deployment</a>
      			</security:authorize>
      			</li>
      	  	</ul>
	   	</li>	
	   	</security:authorize>
	   	
	   	<security:authorize access="hasRole('ADMIN')">	
       	<li  class="${title=='listApplication' || title=='applicationForm' || title=='listVendors' || title=='vendorForm' || title=='deploymentEnvironmentList' || title=='environmentForm'? 'active':''}"><a id="ddlManageSystemParameter" name="ddlManageSystemParameter" class="dropdown-toggle" data-toggle="dropdown" href="#">ManageSystemParameter<span class="caret"></span></a>
        	<ul class="dropdown-menu">
          		<li class="${title=='listApplication'? 'active':''}" ><a id="lnkApplications" name="lnkApplications" href="${pageContext.request.contextPath}/admin/app/list">Applications</a></li>
          		<li class="${title=='listVendors'? 'active':''}" ><a id="lnkVendors" href="${pageContext.request.contextPath}/vendor/list">Vendors</a></li>
          		<li class="${title=='deploymentEnvironmentList'? 'active':''}" ><a href="${pageContext.request.contextPath}/environment/list">Environments</a></li>
          	</ul>
      	</li>
		</security:authorize>		
    </ul>    
    <ul class="nav navbar-nav navbar-right">
		<li><a href="#"><span class="glyphicon glyphicon-user"></span><security:authentication property="principal.username" /></a></li>
		<li><a href="#"><span class="glyphicon glyphicon-certificate"></span><security:authentication property="principal.authorities" /></a></li>
	    <li><form:form action="${pageContext.request.contextPath}/logout" method="POST" id="logout">
					<input type="hidden" value="Logout" /></form:form>
				<a href="#" onclick="document.getElementById('logout').submit();">Logout</a>				
			</li>	       
    </ul>
  </div>
</nav>
</body>
</html>
  