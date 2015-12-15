<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="url" value="${ pageContext.request.requestURI }" />
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<c:if test="${url=='/WoopIt/WEB-INF/views/welcome.jsp'}"><title>WoopIt</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/404.jsp'}"><title>WoopIt - Error 404</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/accessDenied.jsp'}"><title>WoopIt - Access Denied</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/editprofile.jsp'}"><title>WoopIt - Profile</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/friendlist.jsp'}"><title>WoopIt - Friend list</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/history.jsp'}"><title>WoopIt - History</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/home.jsp'}"><title>WoopIt - Home</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/howto.jsp'}"><title>WoopIt - How it works</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/inbox.jsp'}"><title>WoopIt - Inbox</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/inboxNew.jsp'}"><title>WoopIt - Send PM</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/inboxSend.jsp'}"><title>WoopIt - Send PM</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/inboxSent.jsp'}"><title>WoopIt - PM Sent</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/login.jsp'}"><title>WoopIt - Login</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/register.jsp'}"><title>WoopIt - Register</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/registerFailed.jsp'}"><title>WoopIt - Register Failed</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/registerSuccess.jsp'}"><title>WoopIt - Registered</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/searchResults.jsp'}"><title>WoopIt - Search Results</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/setavatar.jsp'}"><title>WoopIt - Set Avatar</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/settings.jsp'}"><title>WoopIt - Settings</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/support.jsp'}"><title>WoopIt - Support</title></c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/trackme.jsp'}"><title>WoopIt - Track Your Position</title>
		<jsp:include page="geolocation.jsp" />
	</c:if>
	<c:if test="${url=='/WoopIt/WEB-INF/views/viewprofile.jsp'}"><title>WoopIt - View Profile</title></c:if>
	
	<!-- Bootstrap core CSS -->
	<link href="<c:url value='/resources/assets/css/bootstrap.css'/>" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="<c:url value='/resources/assets/css/main.css'/>" rel="stylesheet">

	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>
	
	<script type="text/javascript" src="<c:url value='/resources/assets/js/jquery-1.11.3.min.js'/>"></script>
	<script src="<c:url value='/resources/assets/js/smoothscroll.js'/>"></script>
	
	<script type="text/javascript">
		var notifLength = 0;
	    function notificationNewAjax() {
	        $.ajax({
	            url : 'subNav',
	            success : function(data) {
	            	if(data.length > notifLength){
	                	$('#notify').html(data);
	                	notifLength = data.length;
	                	document.getElementById("newNotification").play();
	            	}else{
	            		
	            	}
	            }
	        });
	    }
	    function notificationAjax() {
	        $.ajax({
	            url : 'subNav',
	            success : function(data) {
					$('#notify').html(data);
					notifLength = data.length;
	            }
	        });
	    }
	</script>
	
	<script type="text/javascript">
	    var intervalNotifId = 0;
	    intervalNotifId = setInterval(notificationNewAjax, 5000);
	</script>

</head>

<body data-spy="scroll" data-offset="0" data-target="#navigation" onload="notificationAjax(); <c:if test="${url=='/WoopIt/WEB-INF/views/trackme.jsp'}">geoloc();</c:if>">
	<audio id="newNotification" preload="auto" src="<c:url value='/resources/assets/sounds/newNotification.mp3' />"></audio>	
	<jsp:include page="nav.jsp" />