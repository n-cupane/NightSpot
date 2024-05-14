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
let userId = params.get('userId');

var settings = {
  "url": "http://localhost:8080/user/show/id/" + userId,
  "method": "GET",
  "timeout": 0,
};

$.ajax(settings).done(function (response) {
  console.log(response);

  $('#first-name').val(response.firstName);
  $('#last-name').val(response.lastName);
  $('#username').val(response.username);
  $('#email').val(response.email);
  $('#instagram-handle').val(response.instagramHandle);
  $('#date-of-birth').val(response.dateOfBirth);
  $('#role').val(response.role);
  $('#userImg').attr("src", "http://localhost:8080/" + response.photo);

});

});