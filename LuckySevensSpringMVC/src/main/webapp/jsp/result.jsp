<%-- 
    Document   : result
    Created on : Apr 24, 2019, 10:48:04 PM
    Author     : wgv85
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Lucky Sevens</title>
    </head>
    <body>
        <h1>Lucky Sevens</h1>
        
        <p>You bet $<c:out value="${originalBetAmt}"/></p>
        <p>You are broke after <c:out value="${numberOfRolls}"/> rolls</p>
        <p>You should have quit after <c:out value="${rollsToHighestAmt}"/> rolls when you had $<c:out value="${highestAmt}"/></p>
        <a href="index.jsp">Run em back!</a>
    </body>
</html>