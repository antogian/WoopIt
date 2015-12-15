<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<ol class="breadcrumb">
					<li class="active"><a href="<c:url value='/welcome' />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;Welcome</a>
					</li>
					<li><a href="<c:url value='/trackme' />"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>&nbsp;&nbsp;Track Your Location</a>
					</li>
					<li class="active"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;&nbsp;Enter WoopIt</li>
				</ol>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Your current location</h3>
					</div>
					<br/>
					<div class="row">
						<div class="col-md-4">
							<!-- TRACK MANUAL -->
							<a class="btn btn-success" href="javascript:geoloc()" role="button">Track me again</a>
						</div>
						<!-- SET CIRCLE DIAMETER -->
						<div class="col-md-4">
							<div class="btn-group">
								<button type="button" id="radiusButton" class="btn btn-default dropdown-toggle btn-warning" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Change radius <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="javascript:setRadius(10)">10 meters</a>
									</li>
									<li><a href="javascript:setRadius(20)">20 meters</a>
									</li>
									<li><a href="javascript:setRadius(30)">30 meters</a>
									</li>
									<li><a href="javascript:setRadius(50)">50 meters</a>
									</li>
									<li><a href="javascript:setRadius(100)">100 meters</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-md-4">
							<c:url var="insertGeoDataUrl" value="/trackme" />
							<form id="userLocation" name="userLocation" method="post" action="${insertGeoDataUrl}">
								<input type="hidden" name="userLatitude" value="">
								<input type="hidden" name="userLongitude" value="">
								<input type="hidden" name="userRadius" value="">
								<button type="submit" id="buttonNext" class="btn btn-primary" disabled="disabled">Enter WoopIt&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
								</button>
							</form>
						</div>
					</div>
					<div class="panel-body">
						<div id="map-canvas">

							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
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