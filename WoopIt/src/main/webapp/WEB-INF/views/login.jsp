<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

	<div id="headerwrap">
		<div class="container">
			<div class="row centered">
				<div class="col-lg-12">
					<h1>Welcome To <b>WoopIt</b></h1>
					<h3>Login or Signup!</h3>
					<br>
				</div>

				<div class="col-lg-2"></div>
				<div class="col-lg-1"></div>
				<div class="col-lg-6">

					<div class="panel panel-default">
						<div class="panel-body">
							<c:url var="loginUrl" value="/login" />
							<form class="form-horizontal" action="${loginUrl}" method="post">
								<div class="form-group">
									<br/>
									<label for="userName" class="col-sm-2 control-label">Username:</label>
									<div class="col-sm-10">
										<input id="username" type="text" name="ssoId" class="form-control" placeholder="Username">
									</div>
								</div>
								<div class="form-group">
									<label for="userPass" class="col-sm-2 control-label">Password:</label>
									<div class="col-sm-10">
										<input id="password" type="password" name="password" class="form-control" placeholder="Password">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-8">
										<button type="submit" class="btn btn-lg btn-default">Sign in</button>
										<a class="btn btn-lg btn-success" href="<c:url value='/user/register'/>" role="button">Sign up</a>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>
		</div>
		<!--/ .container -->
	</div>
	<!--/ #headerwrap -->


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
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />