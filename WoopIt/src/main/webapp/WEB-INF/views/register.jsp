<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

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
		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />