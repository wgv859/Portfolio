<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Interest Calculator</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Interest Calculator</h1>
            <hr/>
            <form method="POST" action="userInputs">
                <p>Please enter the amount you would like to invest (Multiples of 100: </p>
                <input type="text" name="userOriginalAmount"/><br/>
                <p>Please enter the desired interest rate: </p>
                <input type="text" name="userInterestRate"/><br/>
                <p>Please enter the number of years you would like to invest: </p>
                <input type="text" name="userYears"/><br/>
                <input type="submit" value="Let's check out those gainz!"/>
            </form>
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

