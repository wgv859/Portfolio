<%-- 
    Document   : UpdateLocationPage
    Created on : May 30, 2019, 9:56:41 PM
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
                
                <h2>
                    Update Location
                </h2>
                <form class="form-horizontal" role="form" method="POST" action="updateLocation">
                    <input type="hidden" name="locationId" value="${location.locationId}"/>
                    <div class="form-group">
                        <label for="add-name" class="col-md-4 control-label">Location Name: </label>
                        <div class="col-md-8">
                            <input type="text" size="50" class="form-control" name="name" placeholder="${location.locationName}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Location Description: </label>
                        <div class="col-md-8">
                            <input type="text" size="240" class="form-control" name="description" placeholder="${location.locationDescription}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-street" class="col-md-4 control-label">Location Street: </label>
                        <div class="col-md-8">
                            <input type="text" size="50" class="form-control" name="street" placeholder="${location.locationStreet}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-city" class="col-md-4 control-label">Location City: </label>
                        <div class="col-md-8">
                            <input type="text" size="50" class="form-control" name="city" placeholder="${location.locationCity}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">Location State: </label>
                        <div class="col-md-8">
                            <input type="text" size="50" class="form-control" name="state" placeholder="${location.locationState}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-zip" class="col-md-4 control-label">Location Zip: </label>
                        <div class="col-md-8">
                            <input type="number" size="10" class="form-control" name="zip" placeholder="${location.locationZip}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-latitude" class="col-md-4 control-label">Location Latitude(##.######): </label>
                        <div class="col-md-8">
                            <input type="number" size="9" class="form-control" name="latitude" placeholder="${location.locationLatitude}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-longitude" class="col-md-4 control-label">Location Longitude(###.######): </label>
                        <div class="col-md-8">
                            <input type="number" size="10" class="form-control" name="longitude" placeholder="${location.locationLongitude}" required/>
                        </div>
                    </div>
                    <p class="danger">${message}</p>
                    <div class="form-group">
                        <div class="com-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Update Location" />
                        </div>
                    </div>
                </form>
        
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
