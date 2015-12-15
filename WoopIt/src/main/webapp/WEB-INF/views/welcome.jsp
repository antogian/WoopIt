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
					<h3>Share your thoughts!</h3>
					<br>
				</div>

			</div>
		</div>
		<!--/ .container -->
	</div>
	<!--/ #headerwrap -->


	<section id="desc"></section>
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
					<div class="fb-like" data-share="true" data-width="450" data-show-faces="true">
					</div>
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