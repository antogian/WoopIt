<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>WoopIt - Inbox</title>

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
				<h1><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>&nbsp;&nbsp;Inbox</h1>
				<hr>
				<br>
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2">
								<ul class="nav nav-pills nav-stacked">
									<li role="presentation" class="active"><a href="#">Inbox&nbsp;&nbsp;<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>
									</li>
									<li role="presentation"><a href="#">Sent&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span></a>
									</li>
									<li role="presentation"><a href="#">Friend Requests&nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
									</li>
								</ul>
							</div>
							<div class="col-md-10">
								<div class="panel panel-success">
									<table class="table table-striped">
										<tr class="active">
											<td></td>
											<td><b>Sent by</b>
											</td>
											<td><b>Message</b>
											</td>
											<td><b>Date</b>
											</td>
										</tr>
										<tr class="success">
											<td>photo edw</td>
											<td>cthulu</td>
											<td>hellooooooooooooooo (new messageee)</td>
											<td>10/10/2015 3:35p.m</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
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