$(document).ready(function() {

    let adminItem = '<div class="left-item">' +
        '<a href="/html/admins.html" class="left-item-anchor d-flex align-items-center ms-3">' +
            '<span class="material-icons-outlined ms-2">&#xef3d</span>' +
            '<p class="left-item-p mt-3 ms-2">Admin</p>' +
        '</a>' +
    '</div>';

    let users;


    // GET USER AND CHECK IF ADMIN
    var settings = {
    "url": "http://localhost:8080/auth/user/show/username/" + sessionStorage.getItem("username"),
    "method": "GET",
    "timeout": 0,
    "headers": {
    "Authorization": sessionStorage.getItem("token")
    },
    };

    $.ajax(settings).done(function (response) {
    let userRole = response.role;
    if (userRole != "ADMIN" && userRole != "SUPER_ADMIN") {
    window.location.replace("/html/login.html");
    }

    if (userRole == "SUPER_ADMIN") {
    $("#left").append(adminItem);
    }

    });

    // GET ALL SPOTS
      var settings = {
          "url": "http://localhost:8080/all/spot/findAllWithoutCategory",
          "method": "GET",
          "timeout": 0,
          "headers": {
            "Authorization": sessionStorage.getItem('token')
          },
        };
        
        $.ajax(settings).done(function (response) {

        // POPULATE SPOT SELECT
          if (response != null && response != undefined){
            for (let i = 0; i < response.length; i++) {
              $('#spot-select').append('<option value="' + response[i].id + '">'+ response[i].name+'</option>');
            }
            // POPULATE VISIT LIST
            $('#search-visits-btn').on('click', function(){
              var settings = {
                "url": "http://localhost:8080/admin/visit/findAllBySpot/" + $('#spot-select').val(),
                "method": "GET",
                "timeout": 0,
                "headers": {
                  "Authorization": sessionStorage.getItem('token')
                },
              };
              
              $.ajax(settings).done(function (response) {
                console.log(response);
                $('.main-list').empty();
                if (response.length > 0) {
                for (let i = 0; i < response.length; i++) {
                  let text = `
                  <div class="main-item d-flex justify-content-start align-items-center">
                          <p class="mt-3 ms-5 mi iid">` + response[i].id + `</p>
                          <p class="mt-3 ms-5 mi">` + response[i].user.firstName + " " + response[i].user.lastName + `</p>
                          <p class="mt-3 ms-5 mi">` + response[i].visitDate + `</p>
                          <p class="mt-3 ms-5 mi">` + response[i].visitTime + `</p>
                  </div>
              `;
          
              $('.main-list').append(text);
          
              }
              } else {
                $('.main-list').append("Nessun risultato");
              }
              });
            });
          }
        });


});