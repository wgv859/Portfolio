<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container-fluid">
            <div id="vendingMachineHeader">

                <h1 class="text-center">Vending Machine</h1>
            </div>
        </div>
        <div id="errorMessageDiv" style="display: none">
            <!--unordered list to display error messages-->
            <ul class="list-group" id="errorMessages"><li></li></ul>
        </div>
        <div class="container-fluid">
            <div id="horizontalRule">
                <hr>
            </div>
        </div>

        <div class="container-fluid">
            <div class="col-md-9">
                
                    <c:forEach var="currentItem" items="${itemList}">
                        <div class="col-md-4">
                            <square class="col-md-8" style="border-style: solid; margin: 10px; padding: 5px">
                                <p> <c:out value="${currentItem.itemNumber}"/></p>
                                <a href="displayItem?itemNumber=${currentItem.itemNumber}"><c:out value="${currentItem.itemName}"/></a>
                                <p>$ <c:out value="${currentItem.itemPrice}"/></p>
                                <br/>
                                <p><c:out value="${currentItem.itemQuantity}"/> available.</p>
                            </square>
                        </div>
                    </c:forEach>
                
            </div>

            <!--Right side of the main body. Holds three forms: top, middle, and bottom-->


            <div class="col-md-3">
                <div class="col-md-12">
                    <label for="total-money" class="control-label col-md-12 text-center"> 
                        Total $ In   
                    </label>
                </div>

                <form method="GET"
                      action="displayMachinePage"
                      role="form">
                    <div class="col-md-12">
                        <input type="text"

                               class="form-control"
                               id="total-money"
                               value="${userMoney}" readonly/>
                    </div>
                </form>    
                <form method="GET"
                      action="addDollar"
                      role="form"
                      name="dollar">

                    <button type="submit"

                            id="addDollarButton"
                            class="btn btn-default col-md-5"
                            style="margin-top: 10px; margin-right: 20px">Add Dollar</button>

                </form>    
                <form method="GET"
                      action="addQuarter"
                      role="form"
                      name="quarter">
                    <div>
                        <button type="submit"

                                id="addQuarterButton"
                                class="btn btn-default col-md-5"
                                style="margin-top: 10px; margin-left: 20px">Add Quarter</button>
                    </div>
                </form>    
                <form method="GET"
                      action="addDime"
                      role="form"
                      name="dime">
                    <div class="col-md-12">
                        <button type="submit"

                                id="addDimeButton"
                                class="btn btn-default col-md-5"
                                style="margin-top: 10px; margin-right: 20px"> Add Dime</button>
                    </div>
                </form>    
                <form method="GET"
                      action="addNickel"
                      role="form"
                      name="nickel">
                    <div>
                        <button type="submit"

                                id="addNickelButton"
                                class="btn btn-default col-md-5"
                                style="margin-top: 10px; margin-left: 20px">Add Nickel</button>
                    </div>
                </form>    

                <div class="col-md-12">
                    <hr>
                </div>



                <form class="form" role="form" id="messages-and-item-form">
                    <div class="col-md-12">
                        <label for="messagesDisplay" class="control-label col-md-12 text-center">
                            Messages
                        </label>
                    </div>
                </form>
                <form>
                    <div class="col-md-12">
                        <input type="text"
                               class="form-control"
                               id="messagesDisplay"
                               style="margin-top: 10px"
                               value="${message}" readonly/>
                    </div>
                </form>
                <form>
                    <div class="col-md-12">
                        <label for="itemDisplay" class="control-label col-md-2"
                               style="margin-top: 20px; margin-bottom: 10px">
                            Item: 
                        </label>
                        <div class="col-md-10">
                            <input type="text"
                                   class="form-control"
                                   style="margin-top: 10px"
                                   id="itemChoiceInput"
                                   placeholder="<c:out value="${itemNumber}"/>" readonly/>
                        </div>
                    </div>
                </form>    
                <form method="GET"
                      action="purchaseItem"
                      role="form"
                      name="nickel">
                    <div class="col-md-12">
                        <button type="submit"
                                id="makePurchaseButton"
                                style="margin-top: 10px"
                                class="btn btn-default col-md-12">Make Purchase</button>
                    </div>
                </form>    

                <div class="col-md-12">
                    <hr>
                </div>

                <form class="form" role="form" id="change-form">
                    <div class="col-md-12">
                        <label for="changeDisplay" class="control-label col-md-12 text-center">
                            Change
                        </label>
                    </div>
                </form>
                <form method="GET"
                            action="displayMachinePage"
                            role="form">
                    <div class="col-md-12">
                        <input type="text"
                               class="form-control"
                               id="change-display"
                               placeholder="${changeString}" readonly/>
                    </div>
                </form>
                <form method="GET"
                      action="clearFields"
                      role="form">
                    <div class="col-md-12">
                        <button type="submit"
                                id="changeReturnButton"
                                style="margin-top: 10px"
                                class="btn btn-default col-md-12">Change return</button>
                    </div>
                </form>
            </div>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

