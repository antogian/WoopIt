<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>WoopIt - Settings</title>

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
				<h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;&nbsp;Settings</h1>
				<hr>
				<br>
				<div class="col-lg-4">
				</div>
				<div class="col-lg-6">

				</div>
				<div class="col-lg-4">
				</div>
			</div>
			<br>

		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

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