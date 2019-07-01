$(document).ready(function () {
    loadItems();
    
    $('#changeReturnButton').click(function(event) {
       clearAllFields(); 
    });
    
    $('#makePurchaseButton').click(function(event) {
       var itemChoiceId = $('#itemChoiceInput').val();
        
        buyProduct(itemChoiceId);
    });
    
});

function clearAllFields() {
    resetUserMoneyAndTotalDisplay();
    $('#total-money').val('');
    $('#itemChoiceInput').val('');
    $('#messagesDisplay').val('');
    $('#change-display').val('');
    loadItems();
}

var userMoney = 0;
var totalDisplay = 0;

function resetUserMoneyAndTotalDisplay() {
    userMoney = 0;
    totalDisplay = 0;
}

function addDollar() {
    userMoney += 100;
    totalDisplay = userMoney/100;
    $('#total-money').val(totalDisplay);
}

function addQuarter() {
    userMoney += 25;
    totalDisplay = userMoney/100;
    $('#total-money').val(totalDisplay);
}

function addDime() {
    userMoney += 10;
    totalDisplay = userMoney/100;
    $('#total-money').val(totalDisplay);
}

function addNickel() {
    userMoney += 5;
    totalDisplay = userMoney/100;
    $('#total-money').val(totalDisplay);
}

function chooseProduct(input) {
    $('#itemChoiceInput').val(input);
    
}

function buyProduct(input) {
        $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + totalDisplay + '/item/' + input,
        success: function (data, status) {
            console.log(data);
            var quarters = data.quarters;
            var dimes = data.dimes;
            var nickels = data.nickels;
            var pennies = data.pennies;
            
            $('#messagesDisplay').val('Thank you!');
            var change = "";
            
            if(quarters > 0) {
                change += "Quarters: " + quarters;
            }
            if(dimes > 0) {
                change += " Dimes: " + dimes;
            }
            if(nickels > 0) {
                change += " Nickels: " + nickels;
            }
            if(pennies > 0) {
                $('#messagesDisplay').text('HOW DID PENNIES GET IN HERE?!?!?');
            }
            
            $('#change-display').val(change);
            
        },
        error: function (response, failure, errorThrown) {
            console.log(response);
            console.log(failure);
            console.log(errorThrown);
            var vendError = response.responseJSON.message;
            $('#messagesDisplay').val(vendError);
        }
    });
}

function loadItems() {
    
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/items",
        success: function (data, status) {
            console.log(data);
            $.each(data, function (index, item) {
                var itemId = item.id;
                var itemName = item.name;
                var itemPrice = item.price;
                var itemQuantity = item.quantity;
                var currentItemNumber = 'itemNumber' + itemId;
                var currentItemName = 'itemName' + itemId;
                var currentItemPrice = 'itemPrice' + itemId;
                var currentItemQuantity = 'itemQuantity' + itemId;
                
                var itemHtml = '<div class="col-md-4">';
                itemHtml += '<square class="col-md-8" style="border-style: solid; margin: 10px; padding: 5px">';
                itemHtml += '<p id="' + currentItemNumber + '"></p>';
                itemHtml += '<a class="text-align: center" id="' + currentItemName + '" onclick="chooseProduct('+ itemId +')"></a>';
                itemHtml +='<p class="text-center" id="' + currentItemPrice + '"></p>';
                itemHtml += '<br/>';
                itemHtml += '<p class="text-center" id="' + currentItemQuantity + '"></p>';
                itemHtml += '</square>';
                itemHtml += '</div>';

                $('#items').append(itemHtml);
                
                $('#itemNumber' + itemId).add().text(itemId);
                $('#itemName' + itemId).append().text(itemName);
                $('#itemPrice' + itemId).append().text('$' + itemPrice);
                $('#itemQuantity' + itemId).append().text('Quantity Left: ' + itemQuantity);
            });
        },
        error: function () {
            $('#errorMessagesDiv').show();
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}

//processes validation errors for the given input. returns true if there
//are validation errors, false otherwise
function checkAndDisplayValidationErrors(input) {
    $('#errorMessageDiv').hide();
    //clear displayed error message if there are any
    $('#errorMessages').empty();
    //check for HTML5 validation errors and process/display appropriately
    //a place to hold error messages
    var errorMessages = [];

    //Loop through each input and check for validation errors
    input.each(function () {
        ////Use the HTML5 validation API to find the validation errors
        if (!this.validity.valid)
        {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    //put any error messages in the errorMessages div
    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        //return true, indicating that there were errors
        return true;
    } else {
        //return false, indicating that there were no errors
        return false;
    }
}