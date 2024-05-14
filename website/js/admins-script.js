$(document).ready(function(){

    let adminItem = '<div class="left-item current">' +
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

    // FIND ALL ADMINS
    var settings = {
        "url": "http://localhost:8080/admin/user/show-admins",
        "method": "GET",
        "timeout": 0,
        "headers": {
          "Authorization": sessionStorage.getItem('token')
        },
      };
      
      $.ajax(settings).done(function (response) {
        console.log(response);
        // DISPLAY ADMINS
        if (response.length > 0) {
            // IN ADMINS.HTML
            for (let i = 0; i < response.length; i++) {
              let text = `
              <div class="main-item d-flex justify-content-start align-items-center">
                      <p class="mt-3 ms-5 mi iid">` + response[i].id + `</p>
                      <p class="mt-3 ms-5 mi">` + response[i].firstName + " " + response[i].lastName + `</p>
                      <p class="mt-3 ms-5 mi">` + response[i].role + `</p>
                      <button class="remove-admin" value="`+response[i].id+`">Rimuovi privilegi</button>
              </div>
          `;
      
          $('.main-list').append(text);
        
          }
          $(document).on('click', '.remove-admin', function(){
                let adminToRemoveId = $(this).val();
                console.log(adminToRemoveId)
                var settings = {
                    "url": "http://localhost:8080/super/user/role/"+adminToRemoveId+"/2",
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

          }
      });


      
            //   DISPLAY USERS IN ADMINS-ADD.HTML
            var settings = {
                "url": "http://localhost:8080/admin/user/show-all",
                "method": "GET",
                "timeout": 0,
                "headers": {
                  "Authorization": sessionStorage.getItem('token')
                },
              };
              
              $.ajax(settings).done(function (response) {
                console.log(response);
                for (let i = 0; i < response.length; i++) {
                    if (response[i].role == 'BASE') {
                    $('#admins').append(`
                    <option value="`+ response[i].id +`">`+ response[i].firstName + ` `+ response[i].lastName +`</option>
                    `);
                    }
                }
                $(document).on('click', '#promote', function(){
                    var settings = {
                        "url": "http://localhost:8080/super/user/role/" + $('#admins').val() + "/1",
                        "method": "POST",
                        "timeout": 0,
                        "headers": {
                          "Authorization": sessionStorage.getItem('token')
                        },
                      };
                      
                      $.ajax(settings).done(function (response) {
                        console.log(response);
                        window.location.reload();
                      });
                });
              });
            

});