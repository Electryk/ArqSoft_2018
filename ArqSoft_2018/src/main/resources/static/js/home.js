$(document).ready(
    function(){
    	$("#busquedaEncuesta").load("./busquedaEncuesta.html").hide();
        $.ajax({
            type : "GET",
            url : "/perfilUsuario",
            data : {"nombreCuenta":sessionStorage.getItem("usr")},
            success : function(res) {
                $("#nombrePerfil").text(res.nombre);
                sessionStorage.setItem("nombrePerfil",res.nombre);
                controlRespuesta("");
            },
            error : function(){
                $("#nombrePerfil").html(
                    "<div class=\"alert alert-danger\" role=\"alert\">"
                    +"Login incorrecto"
                    +"</div>");
            }
        });
        $("#buscar").submit(
            function(event) {
                event.preventDefault();
                controlRespuesta("",$('#pregunta').val());
                $.ajax({
                    type : "GET",
                    url : "/buscarPerfilUsuario",
                    data : {"nombreUsuario":sessionStorage.getItem('nombrePerfil'),
                            "pregunta":document.getElementById("preguntaBusqueda").value},
                    contentType : "application/json",
                    success : function(res) {
                        $("#perfiles").empty();
                        res.forEach(
                            function(item,index) {
                                $("#perfiles").append("<div class='row p-3 mb-2 mostrar' id="+item.id+">" +
                                    "<h3class='col-3'>"+item.nombre+"</h3>" +
                                    "</div");
                            });
                        //$("#unaEncuesta").toggle();
                        $("#busquedaEncuesta").toggle();

                        $(".mostrar").hover(function () {
                            $(this).css('cursor', 'pointer');
                            $(this).addClass("bg-secondary");
                        }, function () {
                            $(this).css('cursor', 'auto');
                            $(this).removeClass("bg-secondary");
                        });
                        $(".mostrar").on("click", function () {
                            sessionStorage.setItem("perfilClickado",$(this).attr("id"));
                            window.location.href = "./perfilAjeno.html";
                        });
                    },
                    error : function(){
                        $("#nombrePerfil").html(
                            "<div class=\"alert alert-danger\" role=\"alert\">"
                              +"No se han encontrado resultados"
                            +"</div>");
                    }
                });
        });
    });