$(document).ready(
    function(){
        $.ajax({
            type : "GET",
            url : "/perfilUsuario",
            data : {"nombreCuenta":sessionStorage.getItem("usr")},
            success : function(res) {
                $("#nombrePerfil").text(res.nombre);
                $("#nombre").text(res.nombre);
                controlRespuesta(res.nombre);
            },
            error : function(){
                $("#nombrePerfil").html(
                    "<div class=\"alert alert-danger\" role=\"alert\">"
                    +"Login incorrecto"
                    +"</div>");
            }
        });
        // $("#paginacion").pagination({
        //     dataSource:[1,2,3,4,5,6,7,8],
        //     pageSize: 1,
        //     autoHidePrevious: true,
        //     autoHideNext: true,
        //     callback: function (data,pagination) {
        //         var html = simpleTemplating(data);
        //         $('#encuestas').html(html);
        //     }
        // });
    });