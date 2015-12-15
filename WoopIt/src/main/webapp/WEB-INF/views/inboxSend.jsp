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
								<form action="<c:url value='/user/inboxSend'/>" method="post">
									<div class="form-group has-success has-feedback">
										<label class="control-label" for="inputGroupSuccess1">Send a message to:</label>
										<div class="input-group">
											<span class="input-group-addon">@</span>
											<input type="text" class="form-control" id="receiverUser" name="receiverUser" aria-describedby="inputGroupSuccess1Status" value="${name}" />
										</div>
										<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
										<span id="inputGroupSuccess1Status" class="sr-only">(success)</span>
									</div>
									<textarea class="form-control" id="body" name="body" rows="5"></textarea>
									<input type="submit" class="btn btn-primary btn-lg btn-block" name="submit" value="Send PM">
								</form>
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