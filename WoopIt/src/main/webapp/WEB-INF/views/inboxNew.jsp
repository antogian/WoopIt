<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<h1><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>&nbsp;&nbsp;Inbox</h1>
				<hr>
				<br>
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2">
								<jsp:include page="inboxMenu.jsp" />
							</div>
							<div class="col-md-10">
								<c:forEach items="${allFriends}" var="friend">
								<div class="row">
									<div class="col-xs-6 col-md-3">
										<a href="<c:url value='/user/inboxSend?name=${friend.userName}' />" class="thumbnail">
											<img class="media-object img-thumbnail" height="64" width="64" src="${pageContext.request.contextPath}/user/avatar/${friend.userPhotoPath}" alt="..." />
											<div class="caption">
												<c:out value="${friend.userName}" />
											</div>
										</a>
 									</div>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br>

		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />