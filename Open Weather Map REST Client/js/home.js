$(document).ready(function () {
   
    $('#getWeatherButton').click(function (event) {

        mega();
    });
    
});

function mega() {
    loadCurrentWeather();
    loadFiveDayForecast();
}

function loadCurrentWeather() {
    clearErrorMessageList();
    
    if($('#add-unit').val() == 1) {
        var userCurrentWeatherUrl = 'http://api.openweathermap.org/data/2.5/weather?zip=';
        userCurrentWeatherUrl+= $('#add-zip').val();
        userCurrentWeatherUrl+= '&units=imperial&APPID=932cd9c3608889563b8fc9e6d53d17d9';
    }
    
    if($('#add-unit').val() == 2) {
        var userCurrentWeatherUrl = 'http://api.openweathermap.org/data/2.5/weather?zip=';
        userCurrentWeatherUrl+= $('#add-zip').val();
        userCurrentWeatherUrl+= '&units=metric&APPID=932cd9c3608889563b8fc9e6d53d17d9';
    }
    
    $.ajax({
        type: 'GET',
        url: userCurrentWeatherUrl,
        success: function (data, status) {
            var cityName = data.name;
            var currentTemp = data.main.temp;
            var currentHumidity = data.main.humidity;
            var currentWindSpeed = data.wind.speed;
            
            setRowTwoHeader(cityName);
            setRowTwoTempAndHumidity(currentTemp, currentHumidity);
            setRowTwoWindSpeed(currentWindSpeed);
            
            $.each(data.weather, function(index, weather) {
                var currentDescription = weather.description;
                var currentWeatherIcon = weather.icon;
                
                setRowTwoImageAndDescription(currentWeatherIcon, currentDescription);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Zip Code: please enter a 5-digit zip code'));
        }
    });
    $('#currentConditionsDiv').show();
    $('#fiveDayForcastDiv').show();
}

function loadFiveDayForecast(){
    clearErrorMessageList();
    
    
    if($('#add-unit').val() == 1) {
        var userCurrentWeatherUrl = 'http://api.openweathermap.org/data/2.5/forecast?zip=';
        userCurrentWeatherUrl+= $('#add-zip').val();
        userCurrentWeatherUrl+= '&units=imperial&APPID=932cd9c3608889563b8fc9e6d53d17d9';
    }
    
    if($('#add-unit').val() == 2) {
        var userCurrentWeatherUrl = 'http://api.openweathermap.org/data/2.5/forecast?zip=';
        userCurrentWeatherUrl+= $('#add-zip').val();
        userCurrentWeatherUrl+= '&units=metric&APPID=932cd9c3608889563b8fc9e6d53d17d9';
    }
    $('#dateOne').empty();
    $('#dateTwo').empty();
    $('#dateThree').empty();
    $('#dateFour').empty();
    $('#dateFive').empty();
    
    $.ajax({
        type: 'GET',
        url: userCurrentWeatherUrl,
        success: function (data, status) {
            $.each(data.list, function(index, list) {
                    
                if(index == 0) {
                    var dateOne = list.dt_txt;
                    $('#dateOne').append(dateOne);
                    
                    $.each(list.weather, function(index, weather) {
                        var oneIcon = weather.icon;
                        var oneDescription = weather.main;
                        var iconSourceAndDescriptionOne = '</br><img src="http://openweathermap.org/img/w/' + oneIcon + '.png" alt="weather icon"><p style="display: inline-block" id="descriptionOne">' + oneDescription + '</p>';
                        $('#dateOne').append(iconSourceAndDescriptionOne).html(); 
                    });
                    
                    var oneHigh = list.main.temp_max;
                    var oneLow = list.main.temp_min;
                    var highLowOne = '</br><p style="display: inline-block">H:</p>' + oneHigh + '<p style="display: inline-block">L:</p>' + oneLow;
                    $('#dateOne').append(highLowOne).html();
                }
                
                if(index == 8) {
                    var dateTwo = list.dt_txt;
                    $('#dateTwo').append(dateTwo);
                    
                    $.each(list.weather, function(index, weather) {
                        var twoIcon = weather.icon;
                        var twoDescription = weather.main;
                        var iconSourceAndDescriptionTwo = '</br><img src="http://openweathermap.org/img/w/' + twoIcon + '.png" alt="weather icon"><p style="display: inline-block" id="descriptionTwo">' + twoDescription + '</p>';
                        $('#dateTwo').append(iconSourceAndDescriptionTwo).html(); 
                    });
                    
                    var twoHigh = list.main.temp_max;
                    var twoLow = list.main.temp_min;
                    var highLowTwo = '</br><p style="display: inline-block">H:</p>' + twoHigh + '<p style="display: inline-block">L:</p>' + twoLow;
                    $('#dateTwo').append(highLowTwo).html();
                }
                
                if(index == 16) {
                    var dateThree = list.dt_txt;
                    $('#dateThree').append(dateThree);
                    
                    $.each(list.weather, function(index, weather) {
                        var threeIcon = weather.icon;
                        var threeDescription = weather.main;
                        var iconSourceAndDescription = '</br><img src="http://openweathermap.org/img/w/' + threeIcon + '.png" alt="weather icon"><p style="display: inline-block" id="descriptionOne">' + threeDescription + '</p>';
                        $('#dateThree').append(iconSourceAndDescription).html(); 
                    });
                    
                    var threeHigh = list.main.temp_max;
                    var threeLow = list.main.temp_min;
                    var highLowThree = '</br><p style="display: inline-block">H:</p>' + threeHigh + '<p style="display: inline-block">L:</p>' + threeLow;
                    $('#dateThree').append(highLowThree).html();
                }
                
                if(index == 24) {
                    var dateFour = list.dt_txt;
                    $('#dateFour').append(dateFour);
                    
                    $.each(list.weather, function(index, weather) {
                        var fourIcon = weather.icon;
                        var fourDescription = weather.main;
                        var iconSourceAndDescription = '</br><img src="http://openweathermap.org/img/w/' + fourIcon + '.png" alt="weather icon"><p style="display: inline-block" id="descriptionOne">' + fourDescription + '</p>';
                        $('#dateFour').append(iconSourceAndDescription).html(); 
                    });
                    
                    var fourHigh = list.main.temp_max;
                    var fourLow = list.main.temp_min;
                    var highLowFour = '</br><p style="display: inline-block">H:</p>' + fourHigh + '<p style="display: inline-block">L:</p>' + fourLow;
                    $('#dateFour').append(highLowFour).html();
                }
                
                if(index == 32) {
                    var dateFive = list.dt_txt;
                    $('#dateFive').append(dateFive);
                    
                    $.each(list.weather, function(index, weather) {
                        var fiveIcon = weather.icon;
                        var fiveDescription = weather.main;
                        var iconSourceAndDescription = '</br><img src="http://openweathermap.org/img/w/' + fiveIcon + '.png" alt="weather icon"><p style="display: inline-block" id="descriptionOne">' + fiveDescription + '</p>';
                        $('#dateFive').append(iconSourceAndDescription).html(); 
                    });
                    
                    var fiveHigh = list.main.temp_max;
                    var fiveLow = list.main.temp_min;
                    var highLowFive = '</br><p style="display: inline-block">H:</p>' + fiveHigh + '<p style="display: inline-block">L:</p>' + fiveLow;
                    $('#dateFive').append(highLowFive).html();
                }
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Zip Code: please enter a 5-digit zip code'));
        }
    })
}

function setContentTableHeaders(input) {
    var dateOne = input;
    
    $('#dateOne').add(dateOne).text();
}

function setRowTwoHeader(input) {
    $('#currentCityWeatherHeader').empty();
    var CurrentWeatherHeader = 'Current Conditions In ';
    CurrentWeatherHeader += input;
    $('#currentCityWeatherHeader').append(CurrentWeatherHeader).text();
}

function setRowTwoImageAndDescription(inputOne, inputTwo) {
    $('#currentWeatherIconAndDescriptionSpot').empty();
    
    var imageIcon = '<img src="http://openweathermap.org/img/w/' + inputOne + '.png" alt="Weather Icon">';
    var weatherDescription = '<p style="display: inline-block" id="description">' + inputTwo + '</p>';
    $('#currentWeatherIconAndDescriptionSpot').append(imageIcon).html();
    $('#currentWeatherIconAndDescriptionSpot').append(weatherDescription).val();
}

function setRowTwoTempAndHumidity(inputOne, inputTwo) {
    $('#currentWeatherMoreInfoSpot').empty();
    
    if($('#add-unit').val() == 1) {
        var currentTemp = '<p>Temperature: ' + inputOne + ' F</p>';
    } else{
        var currentTemp = '<p>Temperature: ' + inputOne + ' C</p>';
    }
    var currentHumidity = '<p>Humidity: ' + inputTwo + '</p>';
    $('#currentWeatherMoreInfoSpot').append(currentTemp).html();
    $('#currentWeatherMoreInfoSpot').append(currentHumidity).html();
}

function setRowTwoWindSpeed(input) {
    var currentWind = '<p>Wind: ' + input + '</p>'
    $('#currentWeatherMoreInfoSpot').append(currentWind).html();
}

function checkAndDisplayValidationErrors(input) {
    clearErrorMessageList();
    
    var errorMessages = [];
    
    input.each(function () {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }   
    });
    
    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>')
                                  .attr({class: 'list-group-item list-group-item-danger'})
                                  .text(message));
        });
        return true;
    } else {
        return false;
    }
}

function clearErrorMessageList() {
    $('#errorMessages').empty();
}
