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
								<div class="panel panel-success">
									<table class="table table-striped">
										<tr class="active">
											<td width="65px"></td>
											<td><b>Sent by</b>
											</td>
											<td><b>Message</b>
											</td>
											<td><b>Date</b>
											</td>
											<td width="15px"></td>
										</tr>
										<c:forEach items="${allInbox}" var="pm">
										<c:url var="profileUrl" value="/viewprofile?name=${pm.senderUser.userName}" />
										<tr class="success">
											<td width="65px"><img class="media-object img-circle" height="54" width="54" src="${pageContext.request.contextPath}/user/avatar/${pm.senderUser.userPhotoPath}" /></td>
											<td width="40px"><a href="${profileUrl}"><c:out value="${pm.senderUser.userName}" /></a></td>
											<td><c:out value="${pm.body}" /></td>
											<td width="20px"><c:out value="${pm.date}" /></td>
											<td width="15px">
									        	<c:url var="deleteUrl" value="/user/inbox?delete=true&id=${pm.id}" />
									        	<a href="${deleteUrl}" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
											</td>
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