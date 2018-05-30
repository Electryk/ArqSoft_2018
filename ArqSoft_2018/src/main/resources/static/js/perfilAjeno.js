$(document).ready(
    function () {
        $.ajax({
            type: "GET",
            url: "/perfilUsuario/" + sessionStorage.getItem('perfilClickado'),
            success: function (res) {
                $("#nombrePerfil").text(sessionStorage.getItem('nombrePerfil'));
                $("#nombre").text(res.nombre);
                $("#calendario").text(res.nacimiento);
                if (res.sexo === 'M') {
                    $('sexoM').prop('checked', true);
                } else if (res.sexo === 'F') {
                    $('sexoF').prop('checked', true);
                }
                $("#pais").val(res.pais.toLowerCase());
                controlRespuesta(res.nombre);
            },
            error: function () {
                $("#nombrePerfil").html(
                    "<div class=\"alert alert-danger\" role=\"alert\">"
                    + "Login incorrecto"
                    + "</div>");
            }
        });
    });