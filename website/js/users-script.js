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
    
    // POPULATE USER LIST
    for (let i = 0; i < users.length; i++) {
        let text = `
        <div class="main-item d-flex justify-content-start align-items-center">
            <a href="user-details.html?userId=`+ users[i].id + `">
                <p class="mt-3 ms-5 mi iid">` + users[i].id + `</p>
                <p class="mt-3 ms-5 mi">` + users[i].firstName + " " + users[i].lastName + `</p>
                <p class="mt-3 ms-5 mi">` + users[i].username + `</p>
                <p class="mt-3 ms-5 mi">` + users[i].email + `</p>
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
            console.log(rows[j])
        }

        for (let i = 0; i < users.length; i++) {
            if ((users[i].firstName + " " + users[i].lastName).toLowerCase().includes(query) || users[i].email.toLowerCase().includes(query) || users[i].username.toLowerCase().includes(query)) {
                for (let j = 0; j < rows.length; j++) {
                    if (rows[j].querySelector('.iid').innerText == users[i].id) {
                        rows[j].classList.remove('hideRow');
                    }
                }
            }
        }

    });

});









});