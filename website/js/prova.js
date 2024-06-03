$(document).ready(function(){

    var settings = {
        "url": "http://localhost:8080/auth/photo/show/id/1",
        "method": "GET",
        "timeout": 0,
        "headers": {
          "Authorization": sessionStorage.getItem('token')
        },
      };
      
      $.ajax(settings).done(function (response, textStatus, jqXHR) {
        console.log(jqXHR.status);
        $('#immaginozza').attr('src',"data:image/png;base64, " +  response);
      });

});
