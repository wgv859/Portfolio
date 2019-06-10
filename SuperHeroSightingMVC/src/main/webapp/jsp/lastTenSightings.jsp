<%-- 
    Document   : lastTenSightings
    Created on : May 27, 2019, 4:28:35 PM
    Author     : wgv85
--%>

<%--
         "${}" is a  is Expression Language(EL) Syntax that allows us to access and display properities of Java Objects and primitive values
          in a webpage
--%>

<%--
        JavaServer Pages(JSP)
        JavaServer Pages Standard Tag Library (JSTL) is used for simple display logic. NO BUSINESS LOGIC IN JSP's.
        We can use it for ifs, choose/when/otherwise, forEach and out

        There are other librarys for wide ranges of purposes.
        
Below are the JSP Taglib directives I am using. 
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
        <div class="container-fluid"><!--Container fluid allows a webpage to adjust to any size. Container adjusts at different predetermined sizes-->
            <h1>Super Hero Sightings</h1>
            <hr/>
            <div class="navbar"> <!--Navigation Bar-->
                <ul class="nav nav-tabs">
                    <li role="presentation" class ="active"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/anomaliesPage">Anomaly</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizationsPage">Organizations</a></li>
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

            <h2>Last Ten Sightings: </h2>

            <table class="table table-bordered">

                <tr>
                    <th width="25%">Date: </th>
                    <th width="25%"></th>
                    <th width="25%">Location City: </th>
                    <th width="25%">Anomalies: </th>
                </tr>

                <c:forEach items="${lastTenSightings}" var="sighting">
                    <tr>
                        <td>${sighting.sightingDate}</td>
                        <td></td>
                        <td>${sighting.location.locationCity}</td>
                        <td>
                            <ul>
                                <c:forEach items="${sighting.anomalies}" var="anomaly">
                                    <li>${anomaly.anomalyName}</li>
                                    </c:forEach>
                            </ul>
                        </td>
                    </tr>

                </c:forEach>
            </table>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
