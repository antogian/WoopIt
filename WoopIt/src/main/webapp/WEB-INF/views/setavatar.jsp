<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>WoopIt - Avatar</title>

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
				<h1><span class="glyphicon glyphicon-picture" aria-hidden="true"></span>&nbsp;&nbsp;Set your avatar</h1>
				<hr>
				<br>
				<div class="col-lg-4">
					<img src="${pageContext.request.contextPath}/user/avatar/${profileAvatar}" alt="" class="img-thumbnail">
				</div>
				<div class="col-lg-5 text-left">
				
				<c:if test="${param.success == 'true'}" >
					<div class="alert alert-success" role="alert">You have successfully uploaded a new profile photo!</div>
				</c:if>
				<c:if test="${param.success == 'false'}" >
					<div class="alert alert-danger" role="alert"><b>Oh snap!</b> We coudn't upload your new photo. More info: ${param.message}</div>
				</c:if>
				
				<c:url var="uploadAvatarURL" value="/user/setavatar" />
				<form action="${uploadAvatarURL}" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="exampleInputFile">Upload photo</label>
						<input type="file" id="file" name="file">
						<p class="help-block">Choose your profile picture.</p>
						<div class="alert alert-warning" role="alert">
  							Your profile picture will be visible to everyone!
						</div>
					</div>
	                <button type="submit" class="btn btn-success">Upload</button>&nbsp;&nbsp;<a class="btn btn-danger" href="<c:url value='/user/removeavatar' />" role="button">Delete picture</a>
            	</form>          
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
