<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>WoopIt - Friends List</title>

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
				<h1><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;Search Results</h1>
				<hr>
				<br>
				<div class="col-lg-4">
				</div>
				<div class="col-lg-4">
					<c:choose>
						<c:when test="${not empty matchedUsers}">
							<c:forEach items="${matchedUsers}" var="user">
								<div class="well well-sm">
									<div class="media">
										<div class="media-left media-top">
											<a href="<c:url value='/viewprofile?name=${user.userName}&friend=false&blocked=false' />">
											<img class="media-object img-circle" src="${pageContext.request.contextPath}/user/avatar/${user.userPhotoPath}" alt="...">
											</a>
										</div>
										<div class="media-body text-left">
											<c:if test="${url=='/WoopIt/WEB-INF/views/viewprofile.jsp'}">class="active"</c:if>
											<a href="<c:url value='/viewprofile?name=${user.userName}&friend=false&blocked=false' />">
												<c:out value="${user.userName}" />
											</a>
											<br/>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							No results found for <c:out value="${keyword}" />
						</c:otherwise>
				</c:choose>
				</div>
				<div class="col-lg-4">
				</div>
			</div>
			<br>

		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

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