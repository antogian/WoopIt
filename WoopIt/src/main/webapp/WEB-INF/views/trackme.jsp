<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>WoopIt - Main</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/resources/assets/css/bootstrap.css' />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/assets/css/main.css' />" rel="stylesheet">
    
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>
    
    <script src="<c:url value='/resources/assets/js/jquery.min.js' />"></script>
    <script src="<c:url value='/resources/assets/js/smoothscroll.js' />"></script>

    <jsp:include page="geolocation.jsp" />

  </head>

  <body data-spy="scroll" data-offset="0" data-target="#navigation" onload="geoloc()">

	<jsp:include page="nav.jsp" />



	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<ol class="breadcrumb">
					<li class="active"><a href="<c:url value='/welcome' />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;Welcome</a></li>
				  	<li><a href="<c:url value='/trackme' />"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>&nbsp;&nbsp;Track Your Location</a></li>
				  	<li class="active"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;&nbsp;Enter WoopIt</li>
				</ol>
				<div class="panel panel-primary">
				      <div class="panel-heading">
				        <h3 class="panel-title">Your current location</h3>
				      </div>
				      <br/>
				      <div class="row">
				      <div class="col-md-4">
				      	<!-- TRACK MANUAL -->
				      	<a class="btn btn-success" href="javascript:geoloc()" role="button">Track me again</a>
				      	</div>
				      	<!-- SET CIRCLE DIAMETER -->
				      	<div class="col-md-4">
						<div class="btn-group">
						  <button type="button" id="radiusButton" class="btn btn-default dropdown-toggle btn-warning" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" autocomplete="off">
						    Change radius <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu">
						    <li><a href="javascript:setRadius(10)">10 meters</a></li>
						    <li><a href="javascript:setRadius(20)">20 meters</a></li>
						    <li><a href="javascript:setRadius(30)">30 meters</a></li>
						    <li><a href="javascript:setRadius(50)">50 meters</a></li>
						    <li><a href="javascript:setRadius(100)">100 meters</a></li>
						  </ul>
						</div>
						</div>
						<div class="col-md-4">
						<c:url var="insertGeoDataUrl" value="/trackme" />
						<form id="userLocation" name="userLocation" method="post" action="${insertGeoDataUrl}">
							<input type="hidden" name="userLatitude" value="">
							<input type="hidden" name="userLongitude" value="">
							<input type="hidden" name="userRadius" value="">
							<button type="submit" id="buttonNext" class="btn btn-primary" disabled="disabled">Enter WoopIt&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></button>
				      	</form>
				      	</div>
				      	</div>
				      <div class="panel-body">
				      	<div id="map-canvas">
				      	
				      	<div class="progress">
						  <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
						</div>
						
				      	</div>
				      </div>
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
