<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>WoopIt - Main</title>

	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/resources/assets/css/bootstrap.css' />" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="<c:url value='/resources/assets/css/main.css' />" rel="stylesheet">

	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>

	<script src="<c:url value='/resources/assets/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/assets/js/smoothscroll.js' />"></script>

	<script type="text/javascript">
		var datLength = 0;
		var notifLength = 0;
	    function homeAjax() {
	        $.ajax({
	            url : 'subHome',
	            success : function(data) {
					$('#result').html(data);
					datLength = data.length;
	            }
	        });
	    }
	    function newAjax() {
	        $.ajax({
	            url : 'subHome',
	            success : function(data) {
	            	if(data.length > datLength){
	                	$('#result').html(data);
	                	datLength = data.length;
	                	document.getElementById("newMessage").play();
	            	}else{
	            		
	            	}
	            }
	        });
	    }
	    function notificationNewAjax() {
	        $.ajax({
	            url : 'subNav',
	            success : function(data) {
	            	if(data.length > notifLength){
	                	$('#notify').html(data);
	                	notifLength = data.length;
	                	document.getElementById("newNotification").play();
	            	}else{
	            		
	            	}
	            }
	        });
	    }
	    function notificationAjax() {
	        $.ajax({
	            url : 'subNav',
	            success : function(data) {
					$('#notify').html(data);
					notifLength = data.length;
	            }
	        });
	    }
	</script>

	<script type="text/javascript">
	    var intervalId = 0;
	    intervalId = setInterval(newAjax, 3000);
	    
	    var intervalNotifId = 0;
	    intervalNotifId = setInterval(notificationNewAjax, 5000);
	</script>

</head>

<body data-spy="scroll" data-offset="0" data-target="#navigation" onload="homeAjax(); notificationAjax();">

	<audio id="newNotification" preload="auto" src="<c:url value='/resources/assets/sounds/newNotification.mp3' />"></audio>	
	<audio id="newMessage" preload="auto" src="<c:url value='/resources/assets/sounds/newMessage.mp3' />"></audio>
	
	<jsp:include page="nav.jsp" />

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<ol class="breadcrumb">
					<li class="active"><a href="<c:url value='/welcome' />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;Welcome</a>
					</li>
					<li class="active"><a href="<c:url value='/trackme' />"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>&nbsp;&nbsp;Track Your Location</a>
					</li>
					<li class="active"><a href="<c:url value='/home' />"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;&nbsp;Enter WoopIt</a>
					</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-body">
						<div id="result"></div>
					</div>
					<div class="panel-footer">
						<form action="<c:url value='/home'/>" method="post">
							<input id="message" type="text" name="message" class="form-control" placeholder="What are you thinking?">
							<br/>
							<input type="submit" id="submit" class="btn btn-primary btn-lg btn-block" name="submit" value="Woop It!" />
						</form>
					</div>
				</div>
			</div>
			<br>
		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />