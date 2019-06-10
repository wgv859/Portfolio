<%-- 
    Document   : addAnomalyPage
    Created on : May 31, 2019, 5:35:45 AM
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
                <li role="presentation" class ="active"><a href="${pageContext.request.contextPath}/anomaliesPage">Anomaly</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/organizationsPage">Organizations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/abilitiesPage">Abilities</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/locationsPage">Locations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/sightingsPage">Sightings</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li role="presentation"><a href="${pageContext.request.contextPath}/displayUserList">User Admin</a></li>
                </sec:authorize>
                </ul>   
            </div>
                
                
                <!--JSPs Expression Language uses the dot operator on the Model attribute from the Controller Servlet we returned if the attribute is an Object -->
                <h2>Add a new Anomaly to the Database</h2>
                
            <form class="form-horizontal" role="form" method="POST" action="addAnomaly">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name: </label>
                    <div class="col-md-8">
                        <input type="text" size="50" class="form-control" name="name" placeholder="Name" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description: </label>
                    <div class="col-md-8">
                        <input type="text" size="240" class="form-control" name="description" placeholder="Description" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-power" class="col-md-4 control-label">Add Ability: </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple name="abilities" required>
                            <c:forEach items="${abilities}" var="ability">
                                <option value="${ability.abilityId}">${ability.abilityName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org" class="col-md-4 control-label">Add Organization: </label>
                    <div class="col-md-8">
                        <select class="selectpicker" multiple name="organizations" required>
                            <c:forEach items="${organizations}" var="organization">
                                <option value="${organization.organizationId}">${organization.organizationName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Anomaly"/>
                    </div>
                </div>

</form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>