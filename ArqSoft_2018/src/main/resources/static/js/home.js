$(document).ready(
    function(){
        $("#unaEncuesta").load("./encuesta.html");
    	$("#busquedaEncuesta").load("./busquedaEncuesta.html").hide();
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
        $("#buscar").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type : "GET",
                    url : "/buscarEncuesta",
                    data : {"nombreUsuario":sessionStorage.getItem('nombrePerfil'),
                            "pregunta":document.getElementById("pregunta").value},
                    contentType : "application/json",
                    success : function(res) {
                    	$("#preguntas").empty();
                    	res.forEach(
                            function(item,index) {
                                $("#preguntas").append("<div class='row p-3 mb-2 mostrar' id="+item.pregunta+">" +
                                    "<h4 class='col-3'>"+item.pregunta+"</h4>" +
                                    "</div");
                            });
                        $("#unaEncuesta").toggle();
                        $("#busquedaEncuesta").toggle();
                        
                        $(".mostrar").hover(function () {
                            $(this).css('cursor', 'pointer');
                            $(this).addClass("bg-secondary");
                        }, function () {
                            $(this).css('cursor', 'auto');
                            $(this).removeClass("bg-secondary");
                        });
                        $(".mostrar").on("click", function () {
                            $("#unaEncuesta").toggle();
                            $("#busquedaEncuesta").toggle();
                            
                            encuesta($(this).attr("id"));
                            
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