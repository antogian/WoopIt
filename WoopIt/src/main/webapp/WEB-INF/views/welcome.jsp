<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>WoopIt</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/resources/assets/css/bootstrap.css'/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/assets/css/main.css'/>" rel="stylesheet">
    
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>
    
    <script src="<c:url value='/resources/assets/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resources/assets/js/smoothscroll.js'/>"></script>
    
  </head>

  <body data-spy="scroll" data-offset="0" data-target="#navigation">

	<jsp:include page="nav.jsp" />

	<div id="headerwrap">
	    <div class="container">
	    	<div class="row centered">
	    		<div class="col-lg-12">
					<h1>Welcome To <b>WoopIt</b></h1>
					<h3>Share your thoughts!</h3>
					<br>
	    		</div>
	    		<div class="col-lg-2">
	    			<h5>What is it</h5>
	    			<p>WoopIt connects you with other people around you. Cool isn't it?</p>
	    		</div>
				<div class="col-lg-1"></div>
	    		<div class="col-lg-6">	
					<div class="panel panel-default">
						<div class="panel-body">
							<img src="https://ergomag.files.wordpress.com/2014/06/facebook-worldb.jpg" />
						</div>
					</div>
	    		</div>
	    		<div class="col-lg-2">
	    			<br>
	    			<img class="hidden-xs hidden-sm hidden-md" src="<c:url value='/resources/assets/img/arrow2.png' />">
	    			<h5>Join WoopIt</h5>
	    			<p>Be a member so you can express your feelings to everyone.</p>
	    		</div>
	    	</div>
	    </div> <!--/ .container -->
	</div><!--/ #headerwrap -->


	<section id="desc"></section>
	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<h1></h1>
				<br>
				<br>
				<div class="col-lg-4">
				</div>
				<div class="col-lg-4">
				</div>
				<div class="col-lg-4">
				</div>
			</div>
			<br><br><br><br><br><br><br>
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
    <script src="<c:url value='/resources/assets/js/bootstrap.js'/>"></script>
  </body>
</html>
