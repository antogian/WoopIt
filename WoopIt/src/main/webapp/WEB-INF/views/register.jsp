<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>WoopIt - Register</title>

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
				<h1>Sign up</h1>
				<hr>
				<br>
				<div class="col-lg-4">
				</div>
				<div class="col-lg-6">
					<c:url var="registerUrl" value="/user/register" />
					<form class="form-horizontal" action="${register}" method="post">
					  <div class="form-group">
						<label for="userName" class="col-sm-2 control-label">Username:</label>
						<div class="col-sm-6">
						  <input type="text" class="form-control" id="userName" name="userName" placeholder="Username" required />
						</div>
					  </div>
					  <div class="form-group">
						<label for="userPass" class="col-sm-2 control-label">Password:</label>
						<div class="col-sm-6">
						  <input type="password" class="form-control" id="userPass" name="userPass" placeholder="Password" required />
						</div>
					  </div>
					    <div class="form-group">
							<label for="userEmail" class="col-sm-2 control-label">Email:</label>
							<div class="col-sm-6">
							  <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="Email" required />
							</div>
					  </div>
					  <div class="form-group">
						<label for="radio-inline" class="col-sm-2 control-label">Gender:</label>
						  <div class="col-sm-4">
							  <label class="radio-inline">
								  <input type="radio" name="userSex" id="userSex" value="Male"> Male
								</label>
								<label class="radio-inline">
								  <input type="radio" name="userSex" id="userSex" value="Female"> Female
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="userCountry" class="col-sm-2 control-label">Country:</label>
							<div class="col-sm-6">
								<select class="form-control" id="userCountry" name="userCountry">
								  <option>Greece</option>
								  <option>United Kingdom</option>
								  <option>Germany</option>
								  <option>USA</option>
								</select>
							</div>
						</div>
					  <div class="form-group">
						<div class=" col-sm-8">
							<br/>
						  <button type="submit" class="btn btn-lg btn-block btn-success">Sign up</button>
						</div>
					  </div>
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
