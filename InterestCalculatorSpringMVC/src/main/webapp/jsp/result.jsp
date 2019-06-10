<%-- 
    Document   : result
    Created on : Apr 24, 2019, 8:41:57 PM
    Author     : wgv85
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Result</title>
    </head>
    <body>
        <h1>Here's how ballin you would be: </h1>
        <p>Your original investment: $${userOriginalAmount}</p>
        <p>
            total interest earned for each year: 
            <c:forEach var="currentYearInterest" items="${yearlyInterestList}">
                <c:out value="$${currentYearInterest}, "/>
            </c:forEach>
        </p>
    </body>
</html>
