<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<h1><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;View profile</h1>
				<hr>
				<br>
				<!-- Show User Profile Columns -->
				<div class="col-lg-2">
					<b>Username</b>
					<c:out value="${targetUser.userName}" />

				</div>
				<div class="col-lg-6">
				</div>
				<div class="col-lg-4">
				</div>
				<br>
				<!-- Show User Profile -->
				<div class="col-lg-2">
					<img src="${pageContext.request.contextPath}/user/avatar/${targetUser.userPhotoPath}" alt="" class="img-thumbnail">
				</div>
				<div class="col-lg-6">
					<p class="text-left">Country:
						<c:out value="${target.userCountry}" />
					</p>

					<p class="text-left">Sex:
						<c:out value="${target.userSex}" /> </p>
				</div>

				<c:if test="${currentUser.userName == targetUser.userName}">
					<div class="col-lg-4">
						<p class="text-left"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;<a href="friendlist">View Friends List</a>
						</p>
						<p class="text-left"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>&nbsp;&nbsp;<a href="blacklist">View Blocked List</a>
						</p>
					</div>
				</c:if>
				<c:if test="${currentUser.userName != targetUser.userName}">
					<div class="col-lg-4">
						<p class="text-left"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;&nbsp;<a href="#">Send Private Message</a>
						</p>
						<p class="text-left"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;<a href="viewprofile?name=${targetUser.userName}&friend=true&blocked=false">Add to Friends</a>
						</p>
						<p class="text-left"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>&nbsp;&nbsp;<a href="viewprofile?name=${targetUser.userName}&friend=false&blocked=true">Block User</a>
						</p>
						<c:if test="${param.friend == 'true' && param.blocked == 'false'}">
							<div class="alert alert-success" role="alert">You have added ${targetUser.userName} to your friend list!</div>
						</c:if>
						<c:if test="${param.friend == 'false' && param.blocked == 'true'}">
							<div class="alert alert-danger" role="alert">You have added ${targetUser.userName} to your blocked list!</div>
						</c:if>
					</div>
				</c:if>
			</div>
			<br/>
		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />