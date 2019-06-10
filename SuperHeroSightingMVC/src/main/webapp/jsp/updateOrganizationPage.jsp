<%-- 
    Document   : updateOrganization
    Created on : May 30, 2019, 10:02:04 PM
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
                <li role="presentation" class ="active"><a href="${pageContext.request.contextPath}/organizationsPage">Organizations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/abilitiesPage">Abilities</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/locationsPage">Locations</a></li>
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
                
                <h2>Edit an Organization</h2>
                    <form class="form-horizontal" role="form" method="POST" action="updateOrganization">
                        <input type="hidden" name="organizationId" value="${organization.organizationId}"/>
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Organization Name: </label>
                            <div class="col-md-8">
                                <input type="text" size="50" class="form-control" name="name" placeholder="${organization.organizationName}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description: </label>
                            <div class="col-md-8">
                                <input type="text" size="240" class="form-control" name="description" placeholder="${organization.organizationDescription}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street" class="col-md-4 control-label">Street Address: </label>
                            <div class="col-md-8">
                                <input type="text" size="50" class="form-control" name="street" placeholder="${organization.organizationStreet}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City: </label>
                            <div class="col-md-8">
                                <input type="text" size="50" class="form-control" name="city" placeholder="${organization.organizationCity}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State: </label>
                            <div class="col-md-8">
                                <input type="text" size="50" class="form-control" name="state" placeholder="${organization.organizationState}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Postal Code: </label>
                            <div class="col-md-8">
                                <input type="text" size="10" class="form-control" name="zip" placeholder="${organization.organizationZip}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone: </label>
                            <div class="col-md-8">
                                <input type="tel" size="15" class="form-control" name="phone" placeholder="${organization.organizationPhone}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email: </label>
                            <div class="col-md-8">
                                <input type="email" size="50" class="form-control" name="email" placeholder="${organization.organizationEmail}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Edit Organization"/>
                            </div>
                        </div>

</form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>