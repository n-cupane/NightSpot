$(document).ready(function(){

    $("#form").submit(function(event){

        event.preventDefault();

        let firstName = $("#firstName").val();
        let lastName = $("#lastName").val();
        let username = $("#username").val();
        let password = $("#password").val();
        let email = $("#email").val();
        let dateOfBirth = $("#dateOfBirth").val();

        var settings = {
            "url": "http://localhost:8080/all/user/insert",
            "method": "POST",
            "timeout": 0,
            "headers": {
              "Content-Type": "application/json"
            },
            "data": JSON.stringify({
              "firstName": firstName,
              "lastName": lastName,
              "username": username,
              "email": email,
              "password": password,
              "instagramHandle": "",
              "dateOfBirth": dateOfBirth
            }),
          };
          
          $.ajax(settings).done(function (response) {
            console.log(response);
          });

    })

});