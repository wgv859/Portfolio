<%-- 
    Document   : detailsLocationPage
    Created on : May 30, 2019, 9:55:44 PM
    Author     : wgv85
--%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Hero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <h1>Super Hero Sightings</h1>
            <hr/>
            <div class="navbar">
                 <ul class="nav nav-tabs">
                 <li role="presentation"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/anomaliesPage">Anomaly</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/organizationsPage">Organizations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/abilitiesPage">Abilities</a></li>
                <li role="presentation" class ="active"><a href="${pageContext.request.contextPath}/locationsPage">Locations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/sightingsPage">Sightings</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li role="presentation"><a href="${pageContext.request.contextPath}/displayUserList">User Admin</a></li>
                </sec:authorize>
                </ul>
            </div>
                
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h4>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </h4>
            </c:if>
                
                <h2>Location Details</h2>
                <p>Location Name: ${location.locationName}</p>
                <p>Description: ${location.locationDescription}</p>
                <p>Street: ${location.locationStreet}</p>
                <p>City: ${location.locationCity}</p>
                <p>State: ${location.locationState}</p>
                <p>Zip: ${location.locationZip}</p>
                <p>Latitude: ${location.locationLatitude}</p>
                <p>Longitude: ${location.locationLongitude}</p>
                <br/>
                <p>Anomalies that have been sighted at this Location</p>
                <ul>
                    <c:forEach items="${anomalies}" var="anomaly">
                        <li>${anomaly.anomalyName}</li>
                    </c:forEach>
                </ul>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
