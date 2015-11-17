<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>WoopIt -Profile</title>

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
				<h1>View profile</h1>
				<hr>
				<br>
				<!-- Show User Profile Columns -->
				<div class="col-lg-2">
					<b>Username</b>
					<c:out value="${currentUser.userName}"/>
				</div>
				<div class="col-lg-6">
				</div>
				<div class="col-lg-4">
				</div>
				<br>
				<!-- Show User Profile -->
				<div class="col-lg-2">
					<img src="${pageContext.request.contextPath}/user/avatar/${currentUser.userPhotoPath}" alt="" class="img-thumbnail">									
				</div>
				<div class="col-lg-6">
					<p class="text-left">Country:<c:out value="${currentUser.userCountry}"/></p> 
					
					<p class="text-left">Sex:<c:out value="${currentUser.userSex}"/> </p>
				</div>
				<c:if test="${isLoggedIn == false}">
					<div class="col-lg-4">
					    <p class="text-left"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;&nbsp;<a href="#">Send Private Message</a></p>
						<p class="text-left"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;<a href="/viewprofile?name=${currentUser.userName}&add=true">Add to Friends</a></p>
						<p class="text-left"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>&nbsp;&nbsp;<a href="/viewprofile?name=${currentUser.userName}&block=true">Block User</a></p>
				
						<!--<p class="text-left"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;&nbsp;<a href="#">Send Private Message</a></p>
						  <form action="<c:url value='/viewprofile'/>" method="post">
							<input name="addName" value="${currentUser.userName}" type="hidden"/>
							<p class="text-left"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;<input type="submit" name="submit" value="Add to Friends"></p>
						</form>
						<form action="<c:url value='/viewprofile'/>" method="post">
							<input name="blockName" value="${currentUser.userName}" type="hidden"/>
							<p class="text-left"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>&nbsp;&nbsp;<input type="submit" name="submit" value="Block ${currentUser.userName}"></p>
						</form>-->

					</div>
				</c:if>
			</div>
			<br/>
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
