<%-- 
    Document   : locations
    Created on : May 27, 2019, 10:40:48 PM
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
                
                <h2>Locations</h2>
                
                <form action="addLocationPage" method="GET">
                    <button style="border:3px solid black" class="btn btn-block" type="submit"> Add A New Location to the Database!</button>
                </form>
                
                <table class="table table-bordered">
                    <tr>
                        <th width="15%">Location Name</th>
                        <th width="15%">Latitude</th>
                        <th width="15%">Longitude</th>
                        <th width="15%">Show Details</th>
                        <th width="20%">Update</th>
                        <th width="20%">Delete</th>
                    </tr>
                    <c:forEach items="${locationsList}" var="location">
                        <tr>
                            <td>${location.locationName}</td>
                            <td>${location.locationLatitude}</td>
                            <td>${location.locationLongitude}</td>
                            <td>
                                <form action="detailsLocationPage" method="GET">
                                    <button type="submit" name="detailsLocationId" value="${location.locationId}">Show Details</button>
                                </form>
                            </td>
                            <td>
                                <form action="updateLocationPage" method="GET">
                                    <button type="submit" name="updateLocation" value="${location.locationId}">Update</button>
                                </form>
                            </td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <td>
                                <form action="deleteLocation" method="POST">
                                    <button type="submit" name="deleteLocation" value="${location.locationId}">Delete</button>
                                </form>
                            </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </table>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>