<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

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

<jsp:include page="footer.jsp" />