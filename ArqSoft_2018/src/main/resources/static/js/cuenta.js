$(document).ready(
    function(){
        $("#postCuenta").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type : "POST",
                    url : "/crearCuenta",
                    data : JSON.stringify({"nombreUsuario":document.getElementById("nombreUsr").value,
                                           "password":document.getElementById("pass").value}),
                    contentType : "application/json",
                    success : function() {
                        $("#resCuenta").html(
                            "<div class=\"alert alert-success\" role=\"alert\">Cuenta creada</div>");
                    },
                    error : function(){
                        $("#resCuenta").html(
                            "<div class=\"alert alert-danger\" role=\"alert\">"
                              +"Fallo al crear cuenta"
                            +"</div>");
                    }
                });
        });
    });