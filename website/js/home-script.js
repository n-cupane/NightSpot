$(document).ready(function() {

    let adminItem = '<div class="left-item">' +
                         '<a href="/html/admins.html" class="left-item-anchor d-flex align-items-center ms-3">' +
                             '<span class="material-icons-outlined ms-2">&#xef3d</span>' +
                             '<p class="left-item-p mt-3 ms-2">Admin</p>' +
                         '</a>' +
                     '</div>';
    
    let users;
    let spots;
    let tickets;
    let visits;


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

// GET ALL USERS
var settings = {
  "url": "http://localhost:8080/admin/user/show-all",
  "method": "GET",
  "timeout": 0,
  "headers": {
    "Authorization": sessionStorage.getItem("token")
  },
};

$.ajax(settings).done(async function (response) {
  users = await response;
  $('#user-number').append(users.length);
});

// GET ALL SPOTS
var settings = {
  "url": "http://localhost:8080/auth/spot/findAllWithCategory",
  "method": "GET",
  "timeout": 0,
  "headers": {
    "Authorization": sessionStorage.getItem("token")
  },
};

$.ajax(settings).done(async function (response) {
  spots = await response;
  $('#spot-number').append(spots.length);
});

// GET ALL TICKETS
var settings = {
  "url": "http://localhost:8080/admin/ticket/show-all",
  "method": "GET",
  "timeout": 0,
  "headers": {
    "Authorization": sessionStorage.getItem("token")
  },
};

$.ajax(settings).done(async function (response) {
  tickets = await response;
  // LOAD NOTIFICATIONS
for (let i = 0; i < tickets.length; i++) {
  let text = `
    <div class="notification-item">
    <a class="d-flex" href="ticket-details.html?ticketId=` + tickets[i].id + `">
          <span class="material-icons-outlined me-4">&#xe8b2</span>
          <h6>Segnalazione per ` + tickets[i].spot.name + `</h6>
      </a>
  </div>
  `;
  if (!tickets[i].solved){
  $('#notification-bar').append(text);
  }
}
});

// GET ALL VISITS
var settings = {
  "url": "http://localhost:8080/admin/visit/show-all",
  "method": "GET",
  "timeout": 0,
  "headers": {
    "Authorization": sessionStorage.getItem("token")
  },
};

$.ajax(settings).done(async function (response) {
  visits = await response;
  $('#visit-number').append(visits.length);
});



setTimeout(function() {
  console.log(users.length);
  console.log(spots)
}, 2000);

});