<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<!-- INTRO WRAP -->
	<div id="intro">
		<div class="container">
			<div class="row centered">
				<h1>Welcome to the WoopIt ${user}</h1>
				<hr>
				<div class="panel panel-default">
					<div class="panel-body">
						<br />
						<c:forEach items="${allMessages}" var="message">
							${message.content}
							<br />
						</c:forEach>
					</div>
					<div class="panel-footer">
						<form action="<c:url value='/home'/>" method="post">
							<input type="text" name="message" class="form-control" placeholder="Text input">
							<br/>
							<input type="submit" class="btn btn-primary btn-lg btn-block" name="submit" value="Woop It!">
						</form>
					</div>
				</div>
			</div>
			<br>
	    </div> <!--/ .container -->
	</div><!--/ #introwrap -->