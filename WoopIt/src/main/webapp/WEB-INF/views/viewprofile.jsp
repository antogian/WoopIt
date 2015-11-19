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
					<c:out value="${targetUser.userName}"/>
					
				</div>
				<div class="col-lg-6">
				</div>
				<div class="col-lg-4">
				</div>
				<br>
				<!-- Show User Profile -->
				<div class="col-lg-2">
					<img src="${pageContext.request.contextPath}/user/avatar/${targetUser.userPhotoPath}" alt="" class="img-thumbnail">									
				</div>
				<div class="col-lg-6">
					<p class="text-left">Country:<c:out value="${target.userCountry}"/></p> 
					
					<p class="text-left">Sex:<c:out value="${target.userSex}"/> </p>
				</div>

				<c:if test="${currentUser.userName == targetUser.userName}">
					<div class="col-lg-4">
						<p class="text-left"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;<a href="friendlist">View Friends</a></p>
						<p class="text-left"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>&nbsp;&nbsp;<a href="unwantedlist">View Unwanted</a></p>
					</div>
				</c:if>
				<c:if test="${currentUser.userName != targetUser.userName}">
					<div class="col-lg-4">
					    <p class="text-left"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;&nbsp;<a href="#">Send Private Message</a></p>
						<p class="text-left"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;<a href="viewprofile?name=${targetUser.userName}&friend=true&unwanted=false">Add to Friends</a></p>
						<p class="text-left"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>&nbsp;&nbsp;<a href="viewprofile?name=${targetUser.userName}&friend=false&unwanted=true">Block User</a></p>
						<c:if test="${param.friend == 'true' && param.unwanted == 'false'}" >
							<div class="alert alert-success" role="alert">You have added ${targetUser.userName} to your friend list!</div>
						</c:if>
						<c:if test="${param.friend == 'false' && param.unwawnted == 'true'}" >
							<div class="alert alert-danger" role="alert">You have added ${targetUser.userName} to your unwanted list!</div>
						</c:if>
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
