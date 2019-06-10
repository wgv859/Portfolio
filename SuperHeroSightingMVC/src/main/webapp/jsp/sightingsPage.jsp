<%-- 
    Document   : sightings
    Created on : May 27, 2019, 10:39:50 PM
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locationsPage">Locations</a></li>
                    <li role="presentation" class ="active"><a href="${pageContext.request.contextPath}/sightingsPage">Sightings</a></li>
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
                
            <h2>List of Sightings</h2>
            
            <form action="addSightingPage" method="GET">
                    <button style="border:3px solid black" class="btn btn-block" type="submit"> Report a new Anomaly sighting!</button>
                </form>
            
            <table class="table table-bordered">
                <tr>
                    <th width="15%">Date</th>
                    <th width="15%">Location</th>
                    <th width="15%">Anomaly Sighted</th>
                    <th width="15%">Show Details</th>
                    <th width="20%">Update</th>
                    <th width="20%">Delete</th>
                </tr>
                <c:forEach items="${sightingsList}" var="sighting">
                    <tr>
                        <td>${sighting.sightingDate}</td>
                        <td>${sighting.location.locationName}</td>
                        <td>
                            <ul>
                                <c:forEach items="${sighting.anomalies}" var="anomaly">
                                    <li>${anomaly.anomalyName}</li>
                                    </c:forEach>
                            </ul>
                        </td>
                        <td>
                            <form action="detailsSightingPage" method="GET">
                                <button type="submit" name="detailsSightingId" value="${sighting.sightingId}">Show Details</button>
                            </form>
                        </td>
                        <td>
                            <form action="updateSightingPage" method="GET">
                                <button type="submit" name="updateSightingId" value="${sighting.sightingId}">Update</button>
                            </form>
                        </td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <form action="deleteSighting" method="POST">
                                <button type="submit" name="deleteSightingId" value="${sighting.sightingId}">Delete</button>
                            </form>
                        </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>
            <div class="col-md-4">
                <form action="sightingsByDatePage" method="GET">
                    Search Sightings By Date:<input class="form-control" type="date" name="date" required/>
                    <button style="border: 3px solid black" class="btn btn-block" type="submit">Get Sightings By Date</button>
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
