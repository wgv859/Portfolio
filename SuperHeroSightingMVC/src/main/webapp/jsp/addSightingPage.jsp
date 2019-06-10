<%-- 
    Document   : addSightingPage
    Created on : May 31, 2019, 8:35:26 AM
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
                
                <h2>Add a Sighting</h2>
                <form class="form-horizontal" role="form" method="POST" action="addSighting">
                    <div class="form-group">
                        <label for="add-date" class="col-md-4 control-label">Date of Sighting: </label>
                        <div class="col-md-8">
                            <input type="date" class="form-control" name="date" placeholder="Date" required/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    <p>If the Anomaly you sighted is not in the drop-down, then you have discovered a new one! Please add the new Anomaly before reporting a sighting.</p>
                    <label for="add-mutant" class="col-md-4 control-label">Add Anomaly: </label>
                    <div class="col-md-8">
                        <select multiple name="anomalies" required>
                            <c:forEach items="${anomalies}" var="anomaly">
                                <option value="${anomaly.anomalyId}">${anomaly.anomalyName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <p>If the location of your sighting is not in the drop-down, you must add that location first. </p>
                    <label for="add-location" class="col-md-4 control-label">Add Location: </label>
                    <div class="col-md-8">
                        <select name="location" required>
                            <c:forEach items="${locations}" var="location">
                                <option value="${location.locationId}">${location.locationName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>



                <p class="danger">${message}</p>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Sighting"/>
                    </div>
                </div>

                </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
