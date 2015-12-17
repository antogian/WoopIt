<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<c:forEach items="${allMessages}" var="message">
	<div class="well well-sm">
		<div class="media">
			<div class="media-left media-top">
				<a href="#">
					<img class="media-object img-circle" height="64" width="64" src="${pageContext.request.contextPath}/user/avatar/${message.sender.userPhotoPath}" alt="...">
				</a>
			</div>
			<div class="media-body text-left">
				<c:if test="${url=='/WoopIt/WEB-INF/views/viewprofile.jsp'}">class="active"</c:if>
				<a href="<c:url value='/viewprofile?name=${message.sender.userName}&friend=false&blocked=false' />">
					<c:out value="${message.sender.userName}" />
				</a> says:
				<br/>
				<c:out value="${message.body}" />
				<br />
				<!--  <div>Deletion in <span id="expiration"></span> minutes</div>-->
			</div>
			<div class="media-body text-right">
				<a class="btn btn-success btn-sm" href="<c:url value='/like?msgId=${message.id}&like=true' />" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></a>
				<br/>
				<a class="top-buffer btn btn-danger btn-sm" href="<c:url value='/like?msgId=${message.id}&like=false' />" role="button"><span class=" glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></a>
			</div>
		</div>
		<div class="progressSmall">
			<c:set var="totalVotes" value="${message.messageLikes + message.messageDislikes}"/>
			<c:set var="likes" value="${message.messageLikes / totalVotes}"/>
			<c:set var="dislikes" value="${message.messageDislikes / totalVotes}"/>
			<c:set var="likePercentage" value="${likes*100}"/>
			<c:set var="dislikePercentage" value="${dislikes*100}"/>
			<div class="progress-bar progress-bar-success" style="width: ${likePercentage}%">
			</div>
			<div class="progress-bar progress-bar-danger" style="width: ${dislikePercentage}%">
			</div>
		</div>
	</div>
</c:forEach>