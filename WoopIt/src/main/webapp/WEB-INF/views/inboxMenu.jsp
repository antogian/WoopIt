<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${ pageContext.request.requestURI }" />
<ul class="nav nav-pills nav-stacked">
	<li role="presentation" <c:if test="${url=='/WoopIt/WEB-INF/views/inbox.jsp'}">class="active"</c:if>><a href="<c:url value='/user/inbox' />">Inbox&nbsp;&nbsp;<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>
	</li>
	<li role="presentation" <c:if test="${url=='/WoopIt/WEB-INF/views/inboxNew.jsp'}">class="active"</c:if>><a href="<c:url value='/user/inboxNew' />">New Message&nbsp;&nbsp;<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
	</li>
	<li role="presentation" <c:if test="${url=='/WoopIt/WEB-INF/views/inboxRequests.jsp'}">class="active"</c:if>><a href="<c:url value='/user/inboxRequests' />">Friend Requests&nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
	</li>
	<li role="presentation" <c:if test="${url=='/WoopIt/WEB-INF/views/inboxHistory.jsp'}">class="active"</c:if>><a href="<c:url value='/user/inboxHistory' />">History&nbsp;&nbsp;<span class="glyphicon glyphicon-folder-close" aria-hidden="true"></span></a>
	</li>
</ul>