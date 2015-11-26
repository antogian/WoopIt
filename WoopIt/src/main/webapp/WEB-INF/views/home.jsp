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

  </head>

  <body data-spy="scroll" data-offset="0" data-target="#navigation">

	<jsp:include page="nav.jsp" />



	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<ol class="breadcrumb">
					<li class="active"><a href="<c:url value='/welcome' />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;Welcome</a></li>
				  	<li class="active"><a href="<c:url value='/trackme' />"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>&nbsp;&nbsp;Track Your Location</a></li>
				  	<li class="active"><a href="<c:url value='/home' />"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;&nbsp;Enter WoopIt</a></li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-body">
						<c:forEach items="${allMessages}" var="message">
						<div class="well well-sm">
							<div class="media">
							  <div class="media-left media-top">
							    <a href="#">
							      <img class="media-object img-circle"  height="64" width="64" src="${pageContext.request.contextPath}/user/avatar/${message.sender.userPhotoPath}" alt="...">
							    </a>
							  </div>
							  <div class="media-body text-left">
							  <c:if test="${url=='/WoopIt/WEB-INF/views/viewprofile.jsp'}">class="active"</c:if>
							  	<a href="<c:url value='/viewprofile?name=${message.sender.userName}&friend=false&blocked=false' />"><c:out value="${message.sender.userName}"/></a> says:<br/>
							  <c:out value="${message.body}"/>
							</div>
							<div class="media-body text-right">
							  <a class="btn btn-success btn-sm" href="<c:url value='/like?msgId=${message.id}&like=true' />" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></a>
							  <br/>
							  <a class="top-buffer btn btn-danger btn-sm" href="#" role="button"><span class=" glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></a>
							</div>
						</div>
						</div>
						</c:forEach>
					</div>
					<div class="panel-footer">
						<form action="<c:url value='/home'/>" method="post">
							<input type="text" name="message" class="form-control" placeholder="What are you thinking?">
							<br/>
							<input type="submit" class="btn btn-primary btn-lg btn-block" name="submit" value="Woop It!">
						</form>
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
