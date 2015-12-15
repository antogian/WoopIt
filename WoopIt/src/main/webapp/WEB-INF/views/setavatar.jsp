<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

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

					<c:if test="${param.success == 'true'}">
						<div class="alert alert-success" role="alert">You have successfully uploaded a new profile photo!</div>
					</c:if>
					<c:if test="${param.success == 'false'}">
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

		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />