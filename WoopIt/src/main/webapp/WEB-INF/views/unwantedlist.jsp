<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>WoopIt - Unwanted list</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/resources/assets/css/bootstrap.css' />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/assets/css/main.css' />" rel="stylesheet">
    
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>
    
    <script src="<c:url value='/resources/assets/js/jquery.min.js' />"></script>
    <script src="<c:url value='/resources/assets/js/smoothscroll.js' />"></script>
    

  </head>

  <body data-spy="scroll" data-offset="0" data-target="#navigation">

	<jsp:include page="nav.jsp" />

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<h1><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;Your Unwanted List</h1>
				<hr>
				<br>
				<div class="col-lg-4">
				</div>
				<div class="col-lg-6">
				<c:forEach items="${allUnwanted}" var="unwanted">
						<div class="well well-sm">
							<div class="media">
							  <div class="media-left media-top">
							    <a href="#">
							      <img class="media-object img-circle" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTBmNzk1ZWE4NSB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1MGY3OTVlYTg1Ij48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxMi41IiB5PSIzNi44Ij42NHg2NDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" alt="...">
							    </a>
							  </div>
							  <div class="media-body text-left">
							  <c:if test="${url=='/WoopIt/WEB-INF/views/viewprofile.jsp'}">class="active"</c:if>
							  	<a href="<c:url value='/viewprofile?name=${unwanted.userName}&friend=false&unwanted=false' />"><c:out value="${unwanted.userName}"/></a><br/>
							  </div>
							</div>
						</div>
				</c:forEach>
				</div>
				<div class="col-lg-4">
				</div>
			</div>
			<br>
			
	    </div> <!--/ .container -->
	</div><!--/ #introwrap -->
	
	<div id="c">
		<div class="container">
			<p>WoopIt Project</p>
		</div>
	</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value='/resources/assets/js/bootstrap.js' />"></script>
  </body>
</html>
