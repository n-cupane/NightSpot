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

    let name = $('#name');
    let position = $('#position');
    let contacts = $('#contacts');
    let timeTables = $('#time-tables');
    let category = $('#category');
    let spotPictures;
    let photos;

    let params = new URLSearchParams(window.location.search);
    let spotId = params.get('spotId');

    // POPULATE INPUTS WITH SPOT INFOS
    var settings = {
        "url": "http://localhost:8080/auth/spot/findByIdWithCategory/id/" + spotId,
        "method": "GET",
        "timeout": 0,
        "headers": {
          "Authorization": sessionStorage.getItem('token')
        },
      };
      
    $.ajax(settings).done(function (response) {
        
        name.val(response.name);
        position.val(response.position);
        contacts.val(response.contact);
        timeTables.val(response.timetables);
        category.val(response.category.name);
        photos = response.photos;
        
        for (let i = 0; i < photos.length; i++) {
            $('#photo-div').append(`
            <div class="spot-img-div">
                <img class="spot-img" src="http://localhost:8080/` + photos[i].path +`" value="`+ photos[i].id +`" />
                </div>
            `);
        }

        // REMOVE PHOTOS ON CLICK
        // IN THE FUTURE WE COULD REMOVE THEM ONLY ONCE THE SUBMIT BUTTON IS PRESSED
        $(document).on('click', '.spot-img-div', function() {
            let imageToRemoveId = $(this).children('.spot-img').attr('value');
            var settings = {
                "url": "http://localhost:8080/admin/photo/delete/" + imageToRemoveId,
                "method": "DELETE",
                "timeout": 0,
                "headers": {
                  "Authorization": sessionStorage.getItem('token')
                },
              };
              
              $.ajax(settings).done(function (response) {
                console.log(response);
              });
            
            photos.splice($('.spot-img-div').index(this), 1);
            $('#photo-div').empty();
            for (let i = 0; i < photos.length; i++) {
                $('#photo-div').append(`
                <div class="spot-img-div">
                <img class="spot-img" src="http://localhost:8080/` + photos[i].path +`" value="`+ photos[i].id +`" />
                    </div>
                `);
            }
        });

    });

    // DISPLAY SELECTED FILE NAMES
    $(document).on('change', '.photo', function() {
        var parent = $(this).parent();
        let photos = "";
        for (let i = 0; i < this.files.length; i++) {
            photos += '"' +  this.files[i].name + '"\t';
        }
        parent.children('.photo-span').text(photos);
        parent.children('.del').removeClass('hideBtn');
    });

    $('#update-form').on('submit', function(event) {
        event.preventDefault();

        let categories;
        // GET CATEGORIES
        var settings = {
            "url": "http://localhost:8080/admin/category/findAllWithoutSpots",
            "method": "GET",
            "timeout": 0,
            "headers": {
              "Authorization": sessionStorage.getItem('token')
            },
          };
          
        $.ajax(settings).done(function (response, textStatus, jqXHR) {
            categories = response;
            // CHECK IF CATEGORY ALREADY EXISTS
        let spotCategoryId = -1;
        for (let i = 0; i < categories.length; i++) {
            if (categories[i].name == category.val()) {
                spotCategoryId = categories[i].id;
                break;
            } 
        }
        // IF CATEGORY DOESN'T EXIST, ADD IT
        if (spotCategoryId == -1) {
            var settings = {
                "url": "http://localhost:8080/admin/category/insert",
                "method": "POST",
                "timeout": 0,
                "headers": {
                  "Authorization": sessionStorage.getItem('token'),
                  "Content-Type": "application/json"
                },
                "data": JSON.stringify({
                  "name": category.val()
                }),
              };
              
              $.ajax(settings).done(function (response, textStatus, jqXHR) {
                if (jqXHR.status == 200) {
                    var settings = {
                        "url": "http://localhost:8080/admin/category/findByName/" + category.val(),
                        "method": "GET",
                        "timeout": 0,
                        "headers": {
                          "Authorization": sessionStorage.getItem('token')
                        },
                      };
                      
                      $.ajax(settings).done(function (response) {
                        spotCategoryId = response.id;
                      });
                }
              });
        }

        setTimeout(function(){
            // UPDATE SPOT
        var settings = {
            "url": "http://localhost:8080/admin/spot/update",
            "method": "POST",
            "timeout": 0,
            "headers": {
              "Authorization": sessionStorage.getItem('token'),
              "Content-Type": "application/json"
            },
            "data": JSON.stringify({
              "id": spotId,
              "name": name.val(),
              "position": position.val(),
              "contact": contacts.val(),
              "timetables": timeTables.val(),
              "category": {
                "id": spotCategoryId
              }
            }),
        };
       
        $.ajax(settings).done(function (response, textStatus, jqXHR) {
            console.log("Response code for spot update: " + jqXHR.status);
        });

        // UPLOAD NEW PHOTOS
        let spotPictures = $('#photo')[0].files;
        for (let i = 0; i < spotPictures.length; i++) {
        
            var form = new FormData();
            form.append("image", spotPictures[i]);

            var settings = {
            "url": "http://localhost:8080/admin/photo/upload/" + spotId,
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Authorization": sessionStorage.getItem('token')
            },
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
            };

            $.ajax(settings).done(function (response, textStatus, jqXHR) {
                console.log("Response code for image update: " + jqXHR.status)
            });

          }
        }, 5000);

        });
        

        
    });

});