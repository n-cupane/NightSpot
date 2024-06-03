$(document).ready(function() {

    let adminItem = '<div class="left-item">' +
                         '<a href="/html/admins.html" class="left-item-anchor d-flex align-items-center ms-3">' +
                             '<span class="material-icons-outlined ms-2">&#xef3d</span>' +
                             '<p class="left-item-p mt-3 ms-2">Admin</p>' +
                         '</a>' +
                     '</div>';
    
 
    let tickets;


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
    
    // POPULATE TICKET LIST
    for (let i = 0; i < tickets.length; i++) {
        let text = `
        <div class="main-item d-flex justify-content-start align-items-center">
            <a href="ticket-details.html?ticketId=` + tickets[i].id + `">
                <p class="mt-3 ms-5 mi iid">` + tickets[i].id + `</p>
                <p class="mt-3 ms-5 mi">` + tickets[i].spot.name + `</p>
                <p class="mt-3 ms-5 mi">` + tickets[i].user.username + `</p>
                <p class="mt-3 ms-5 mi">` + (tickets[i].solved ? "chiuso" : "aperto") + `</p>
            </a>
        </div>
    `;

    $('.main-list').append(text);

    }

    // SEARCH
    let rows = $(".main-item");

    $("#search-bar").on("input", function() {

        let query = $("#search-bar").val().toLowerCase();

        for (let j = 0; j < rows.length; j++) {
            rows[j].classList.add('hideRow');
            rows[j].classList.remove('d-flex');
        }

        for (let i = 0; i < tickets.length; i++) {
            if (tickets[i].spot.name.toLowerCase().includes(query) || tickets[i].user.username.toLowerCase().includes(query)) {
                for (let j = 0; j < rows.length; j++) {
                    if (rows[j].querySelector('.iid').innerText == tickets[i].id) {
                        rows[j].classList.remove('hideRow');
                    }
                }
            }
        }

    });

});









});