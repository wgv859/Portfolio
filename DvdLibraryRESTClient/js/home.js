$(document).ready(function () {
    loadAllDvds();

    $('#createButton').click(function() {
        showSectionThree();
    });
    
    $('#searchButton').click(function() {
        $('#errorMessages').empty();
        var haveSearchTermValidationErrors = checkAndDisplayValidationErrors($('#search-term'));
        var haveSearchCategoryValidationErrors = checkAndDisplayValidationErrors($('#search-category'));
        if(haveSearchTermValidationErrors === false && haveSearchCategoryValidationErrors === false) {
            searchDvdLibrary();
        }
        
        if(haveSearchTermValidationErrors === true || haveSearchCategoryValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Both Search Category and Search Term are required.'));
        }
    });
    
    $('#editDvdButton').click(function() {
        $('#errorMessages').empty();
        var haveTitleEditFieldValidationErrors = checkAndDisplayValidationErrors($('#edit-dvd-title'));
        var haveReleaseYearEditFieldValidationErrors = checkAndDisplayValidationErrors($('#edit-release-year'));
        var haveDirectorEditFieldValidationErrors = checkAndDisplayValidationErrors($('#edit-director'));
        var haveRatingEditFieldValidationErrors = checkAndDisplayValidationErrors($('#edit-rating'));
        var haveNotesEditFieldValidationErrors = checkAndDisplayValidationErrors($('#edit-notes'));
        
        if(haveTitleEditFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter a title for the DVD'));
        }
        if(haveReleaseYearEditFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter a 4-digit year for the DVD.'));
        }
        if(haveDirectorEditFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter a director for the DVD'));
        }
        if(haveRatingEditFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('ERROR'));
        }
        if(haveNotesEditFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter some notes about the DVD.'));
        }
        
        if(haveTitleEditFieldValidationErrors === false && haveReleaseYearEditFieldValidationErrors === false && haveDirectorEditFieldValidationErrors === false && haveRatingEditFieldValidationErrors === false && haveNotesEditFieldValidationErrors === false) {
            editDvd();
        }
    });
    
    $('#createDvdButton').click(function() {
        
        $('#errorMessages').empty();
        
        $('#create-dvd-title').empty();
        $('#create-release-year').empty();
        $('#create-director').empty();
        $('#create-notes').empty();
        
        
        
        var haveTitleCreateFieldValidationErrors = checkAndDisplayValidationErrors($('#create-dvd-title'));
        var haveReleaseYearCreateFieldValidationErrors = checkAndDisplayValidationErrors($('#create-release-year'));
        var haveDirectorCreateFieldValidationErrors = checkAndDisplayValidationErrors($('#create-director'));
        var haveRatingCreateFieldValidationErrors = checkAndDisplayValidationErrors($('#create-rating'));
        var haveNotesCreateFieldValidationErrors = checkAndDisplayValidationErrors($('#create-notes'));
        
        if(haveTitleCreateFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter a title for the DVD'));
        }
        if(haveReleaseYearCreateFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter a 4-digit year for the DVD.'));
        }
        if(haveDirectorCreateFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter a director for the DVD'));
        }
        if(haveRatingCreateFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please select a rating for this DVD.'));
        }
        if(haveNotesCreateFieldValidationErrors === true) {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Please enter some notes about the DVD.'));
        }
        
        if(haveTitleCreateFieldValidationErrors === false && haveReleaseYearCreateFieldValidationErrors === false && haveDirectorCreateFieldValidationErrors === false && haveRatingCreateFieldValidationErrors === false && haveNotesCreateFieldValidationErrors === false) {
            createDvd();
        }
    });

});

function clearLibraryTable() {
    $('#contentRows').empty();
}

function showSectionOne() {
    $('#topSectionOne').show();
    $('#bottomSectionOne').show();
    $('#topSectionTwo').hide();
    $('#bottomSectionTwo').hide();
    $('#topSectionThree').hide();
    $('#bottomSectionThree').hide();
}

function showSectionTwo() {
    $('#topSectionTwo').show();
    $('#bottomSectionTwo').show();
    $('#topSectionOne').hide();
    $('#bottomSectionOne').hide();
    $('#topSectionThree').hide();
    $('#bottomSectionThree').hide();
}

function showSectionThree() {
    $('#topSectionThree').show();
    $('#bottomSectionThree').show();
    $('#topSectionTwo').hide();
    $('#bottomSectionTwo').hide();
    $('#topSectionOne').hide();
    $('#bottomSectionOne').hide();
}

function createDvd() {
    
    $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/dvd/',
           data: JSON.stringify({
               title: $('#create-dvd-title').val(),
               releaseYear: $('#create-release-year').val(),
               director: $('#create-director').val(),
               rating: $('#create-rating').val(),
               notes: $('#create-notes').val()
           }),
           headers: {
               'Accept': 'application/json',
               'Content-type': 'application/json'
           },
           'dataType': 'json',
           success: function() {
               //clear errorMessages
               $('#errorMessages').empty();
               $('#create-dvd-title').val('');
               $('#create-release-year').val('');
               $('#create-director').val('');
               $('#create-notes').val('');
               loadAllDvds();
           },
           error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));              
           }
       })
}

var movieId;

function editDvdHeader(input) {
    
    $('#editDvdHeader').empty();
    var currentDvdHeader = 'Edit DVD: ';
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/dvd/' + input,
        success: function (data, status) {
                var dvdId = data.dvdId;
                var title = data.title;
                var releaseYear = data.releaseYear;
                var director = data.director;
                var rating = data.rating;
                var notes = data.notes;
                movieId = data.dvdId;
                currentDvdHeader += title;
                $('#editDvdHeader').append(currentDvdHeader).text();
        },
        error: function () {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Please try again later.'));
        }
        
    });
    showSectionTwo();
}

function searchDvdLibrary() {
    
    var userSearchCategory = $('#search-category').val();
    var searchCategory;
    if(userSearchCategory == 0) {
        searchCategory = 'title';
    }
    
    if(userSearchCategory == 1) {
        searchCategory = 'year';
    }
    
    if(userSearchCategory == 2) {
        searchCategory = 'director';
    }
    
    if(userSearchCategory == 3) {
        searchCategory = 'rating';
    }
    
    var userSearchTerm = $('#search-term').val();
    
    var userSearchUrl = 'http://localhost:8080/dvds/'; 
    userSearchUrl    += searchCategory; 
    userSearchUrl    += '/' + userSearchTerm;
    
    clearLibraryTable();
    
    var contentRows = $('#contentRows');
    
    $.ajax({
        type: 'GET',
        url: userSearchUrl,
        success: function (data, status) {
            $.each(data, function (index, Dvd) {
                var dvdId = Dvd.dvdId;
                var title = Dvd.title;
                var releaseYear = Dvd.releaseYear;
                var director = Dvd.director;
                var rating = Dvd.rating;
                var notes = Dvd.notes;
                
                var row = '<tr>';
                row += '<td>' + title + '</td>';
                row += '<td>' + releaseYear + '</td>';
                row += '<td>' + director +  '</td>';
                row += '<td>' + rating + '</td>';
                row += '<td><a onclick="editDvdHeader('+ dvdId +')">EDIT |</a><a onclick="deleteDvd(' + this.dvdId + ')">| DELETE</a></td>';
                contentRows.append(row);
            });
        },
        error: function () {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Please try again later.'));
        }
    });
}

function deleteDvd(input) {
    $('#errorMessageDiv').hide();
    $('#errorMessages').empty();
    
    //I need to figure out how to get the alert box with the two buttons to pop up before this call gets made
    var confirmation = confirm('Are you sure you want to delete this DVD from your collection?');
    
    if(confirmation === true) {
        $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/dvd/' + input,
        success: function (status) {
            loadAllDvds();
        },
        error: function () {
            $('#errorMessageDiv').show();
            $('#errorMessages')
                .append($('<li>')
                         .attr({class: 'list-group-item list-group-item-danger'})
                         .text('Something is fucky'));
            }
        });
    } else {
    
    }
}

function editDvd() {
    
    
    var editUrl = 'http://localhost:8080/dvd/'; 
    editUrl+= movieId;
    $.ajax({
           type: 'PUT',
           url: editUrl,
           data: JSON.stringify({
               dvdId: movieId,
               title: $('#edit-dvd-title').val(),
               releaseYear: $('#edit-release-year').val(),
               director: $('#edit-director').val(),
               rating: $('#edit-rating').val(),
               notes: $('#edit-notes').val()
           }),
           headers: {
               'Accept': 'application/json',
               'Content-type': 'application/json'
           },
           'dataType': 'json',
           success: function() {
               //clear errorMessages
               $('#errorMessages').empty();
               $('#edit-dvd-title').empty();
               $('#edit-release-year').empty();
               $('#edit-director').empty();
               $('#edit-notes').empty();
               loadAllDvds();
           },
           error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));              
           }
       })
}

function loadAllDvds() {
    $('#errorMessageDiv').hide();
    $('#errorMessages').empty();
    //display top/bottom section one. 
    showSectionOne();
    //create a variable to hold the tablebody element so I can add rows to it.
    //clear the table in #bottomSectionOne
    clearLibraryTable();
    
    var contentRows = $('#contentRows');
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/dvds',
        success: function (data, status) {
            $.each(data, function (index, Dvd) {
                var dvdId = Dvd.dvdId;
                var title = Dvd.title;
                var releaseYear = Dvd.releaseYear;
                var director = Dvd.director;
                var rating = Dvd.rating;
                var notes = Dvd.notes;
                
                var row = '<tr>';
                row += '<td>' + title + '</td>';
                row += '<td>' + releaseYear + '</td>';
                row += '<td>' + director +  '</td>';
                row += '<td>' + rating + '</td>';
                row += '<td><a onclick="editDvdHeader('+ dvdId +')">EDIT |</a><a onclick="deleteDvd('+ dvdId +')">| DELETE</a></td>';
                contentRows.append(row);
            });
        },
        error: function () {
            $('#errorMessageDiv').show();
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
    input.each(function() {
        ////Use the HTML5 validation API to find the validation errors
        if (!this.validity.valid) 
        {
            var errorField = $('label[for=' +this.id+']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });
    
    //put any error messages in the errorMessages div
    if (errorMessages.length > 0) {
        $.each(errorMessages,function(index, message) {
           $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message)); 
        });
        //return true, indicating that there were errors
        return true;
    } else {
        //return false, indicating that there were no errors
        return false;
    }
}