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

    let categories;

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
        console.log(jqXHR.status);

        for (let i = 0; i < categories.length; i++) {
            $('#category-list').append('<option value="' +categories[i].name +'">');
        }
      });

    $(document).on('change', '.photo', function() {
        var parent = $(this).parent();
        let photos = "";
        for (let i = 0; i < this.files.length; i++) {
            photos += '"' +  this.files[i].name + '"\t';
        }
        parent.children('.photo-span').text(photos);
        parent.children('.del').removeClass('hideBtn');
    });

    $(document).on('click', '.del', function() {
        var parent = $(this).parent();
        parent.children('.photo-span').text('Nessuna foto selezionata');
        parent.children('.photo').val('');
        $(this).addClass('hideBtn');
    });

    $('#add-btn').on('click', function(){

        let spotId;
        let spotName = $('#name').val();
        let spotPosition = $('#position').val();
        let spotContacts = $('#contacts').val();
        let spotTimeTables = $('#time-tables').val();
        let spotCategoryName = $('#category').val();
        let spotCategoryId = -1;
        let spotPictures = $("#photo")[0].files;
        console.log("Number of photos: " + spotPictures.length);

        if (categories != null && categories != undefined) {
          for (let i = 0; i < categories.length; i++) {
              if (categories[i].name == spotCategoryName) {
                  spotCategoryId = categories[i].id;
                  break;
              }
          }
        }
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
                  "name": spotCategoryName
                }),
              };
              
              $.ajax(settings).done(function (response, textStatus, jqXHR) {
                if (jqXHR.status == 200) {
                    var settings = {
                        "url": "http://localhost:8080/admin/category/findByName/" + spotCategoryName,
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
          var settings = {
            "url": "http://localhost:8080/admin/spot/insert",
            "method": "POST",
            "timeout": 0,
            "headers": {
              "Authorization": sessionStorage.getItem('token'),
              "Content-Type": "application/json"
            },
            "data": JSON.stringify({
              "name": spotName,
              "position": spotPosition,
              "contact": spotContacts,
              "timetables": spotTimeTables,
              "category": {
                "id": spotCategoryId
              }
            }),
          };
          
          $.ajax(settings).done(function (response, textStatus, jqXHR) {
            if (jqXHR.status == 200) {
                alert("Spot inserito");
                var settings = {
                    "url": "http://localhost:8080/auth/spot/findByName/" + encodeURIComponent(spotName),
                    "method": "GET",
                    "timeout": 0,
                    "headers": {
                      "Authorization": sessionStorage.getItem('token')
                    },
                  };
                  
                  $.ajax(settings).done(function (response) {
                    spotId = response.id;
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
                            console.log("Image upload status: " + jqXHR.status)
                        });
            
                      }
                      window.location.replace("/html/spots.html");
                  });
            }
          });
        }, 3000);  
    });

    

    // setTimeout(function() {
    //     let spotPictures = $('#photo');
    //     files = $("#photo")[0].files[0];
    //     console.log(files);
    //   }, 5000);

});