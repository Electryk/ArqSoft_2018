$(document).ready(
    function(){
        $("#unaEncuesta").load("./encuesta.html");
        $.ajax({
            type : "GET",
            url : "/perfilUsuario",
            data : {"nombreCuenta":sessionStorage.getItem("usr")},
            success : function(res) {
                $("#nombrePerfil").text(res.nombre);
                sessionStorage.setItem("nombrePerfil",res.nombre);
            },
            error : function(){
                $("#nombrePerfil").html(
                    "<div class=\"alert alert-danger\" role=\"alert\">"
                    +"Login incorrecto"
                    +"</div>");
            }
        });
    });