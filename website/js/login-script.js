$(document).ready(function() {

$("#login-form").submit(function(event){
    event.preventDefault();

    let username = $("#username").val();
    let password = $("#password").val();

    var settings = {
        "url": "http://localhost:8080/all/user/login",
        "method": "POST",
        "timeout": 0,
        "headers": {
          "Content-Type": "application/json"
        },
        "data": JSON.stringify({
          "username": username,
          "password": password
        }),
      };
      
      $.ajax(settings).done(function (response, textStatus, jqXHR) {
        if (jqXHR.status == 200) {
            console.log("Login successful for user: " + username);
            sessionStorage.setItem("token", "Bearer " + jqXHR.getResponseHeader("Authorization"));
            sessionStorage.setItem("username", username);
            window.location.replace("/html/home.html");
        }
      });
})

});