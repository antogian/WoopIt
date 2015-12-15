<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<h1>You dont have permission to view this page :(</h1>
				<br/>
				<div class="alert alert-danger" role="alert">
					You might need to authenticate first before you try to access this page.
					<br/> Please use the <a href="<c:url value='/user/login' />">sign in</a> or <a href="<c:url value='/user/register' />">sign up</a> form if you don't have already an account.
				</div>



			</div>
			<br>
		</div>
		<!--/ .container -->
	</div>
	<!--/ #introwrap -->

<jsp:include page="footer.jsp" />