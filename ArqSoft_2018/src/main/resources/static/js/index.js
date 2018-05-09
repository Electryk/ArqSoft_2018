$(document).ready(
    function(){
        $("#login").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type : "GET",
                    url : "/login",
                    data : {"nombreUsuario":document.getElementById("nombreUsr").value,
                        "password":$.md5(document.getElementById("pass").value)},
                    success : function() {
                        sessionStorage.setItem("usr",$("#nombreUsr").val());
                        $("#resLogin").html(
                            "<div class=\"alert alert-success\" role=\"alert\">Login correcto</div>");
                        window.location.href = "/home.html"
                    },
                    error : function(){
                        $("#resLogin").html(
                            "<div class=\"alert alert-danger\" role=\"alert\">"
                            +"Login incorrecto"
                            +"</div>");
                    }
                });
            });
    });