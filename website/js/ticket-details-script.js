$(document).ready(function() {

    let adminItem = '<div class="left-item">' +
  '<a href="/html/admins.html" class="left-item-anchor d-flex align-items-center ms-3">' +
      '<span class="material-icons-outlined ms-2">&#xef3d</span>' +
      '<p class="left-item-p mt-3 ms-2">Admin</p>' +
  '</a>' +
'</div>';
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

    let params = new URLSearchParams(window.location.search);
    let ticketId = params.get('ticketId');

    var settings = {
        "url": "http://localhost:8080/auth/ticket/show/id/" + ticketId,
        "method": "GET",
        "timeout": 0,
        "headers": {
          "Authorization": sessionStorage.getItem('token')
        },
      };
      
    $.ajax(settings).done(function (response) {
        console.log(response);
        $('#ticket-content').children('h3').append(response.spot.name);
        $('#ticket-content').children('h5').append(response.user.username);
        $('#ticket-content').children('h6').append(response.solved ? "chiuso" : "aperto");
        $('#ticket-content').children('p').text(response.description);
        if (response.solved) {
          $('#close-ticket').addClass('hideBtn');
        }

        $(document).on('click', '#close-ticket', function(){
          var settings = {
            "url": "http://localhost:8080/admin/ticket/close/" + response.id,
            "method": "POST",
            "timeout": 0,
            "headers": {
              "Authorization": sessionStorage.getItem('token')
            },
          };
          
          $.ajax(settings).done(function (response) {
            window.location.reload();
          });
        });

    });

      

});