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
								<div class="panel panel-warning">
									<table class="table table-striped">
										<tr class="active">
											<td></td>
											<td><b>Sent to</b>
											</td>
											<td><b>Message</b>
											</td>
											<td><b>Date</b>
											</td>
										</tr>
										<c:forEach items="${allHistory}" var="pm">
											<c:url var="profileUrl" value="/viewprofile?name=${pm.receiverUser.userName}" />
											<tr class="warning">
												<td width="65px"><img class="media-object img-circle" height="64" width="64" src="${pageContext.request.contextPath}/user/avatar/${pm.receiverUser.userPhotoPath}" /></td>
												<td><a href="${profileUrl}"><c:out value="${pm.receiverUser.userName}" /></a></td>
												<td><c:out value="${pm.body}" /></td>
												<td><c:out value="${pm.date}" /></td>
											</tr>
											</c:forEach>
									</table>
								</div>
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