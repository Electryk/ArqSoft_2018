$(document).ready(
    function(){
        var nRespuesta;
        var votosRespuesta;
        var votosTotales;
        $.ajax({
            type : "GET",
            url : "/encuesta",
            data : {"nombreUsuario":""},
            success : function(res) {
                $("#pregunta").text(res.pregunta);
                $("#nvotos").text(res.votos);
                votosTotales=res.votos;
                votosRespuesta=res.votosPorRespuesta;
                res.respuestas.forEach(
                    function(item,index){
                        $("#respuestas").append("<div class='row p-3 mb-2 votar' id="+index+">" +
                            "<h4 class='col-3'>"+item+"</h4>"+
                            "<div class='progress col-8' style='height: 30px;'><div class='progress-bar bg-warning text-dark' role='progressbar' " +
                            "style='width: 0%' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100'></div></div>"+
                            "</div");
                    }
                );
                $(".votar").hover(function() {
                    $(this).css('cursor','pointer');
                    $(this).addClass("bg-secondary");
                }, function() {
                    $(this).css('cursor','auto');
                    $(this).removeClass("bg-secondary");
                });
                $(".votar").on("click",function () {
                    nRespuesta=$(this).attr("id");
                    $.ajax({
                        type : "PUT",
                        url: "/encuesta",
                        data :{"nombreCuenta":sessionStorage.getItem("usr"),"nombreEncuesta":$("#pregunta").text(),
                            "nRespuesta":$(this).attr("id")},
                        success : function () {
                            votosTotales++;
                            votosRespuesta[nRespuesta]++;
                            $("#nvotos").text(votosTotales);
                            votosRespuesta.forEach(function (item, index) {
                                var fill=(item/votosTotales)*100;
                                $("#"+index+" .progress-bar").attr("aria-valuenow",fill);
                                $("#"+index+" .progress-bar").css("width", fill+"%");
                                $("#"+index+" .progress-bar").text(item+" votos");
                            });
                            $(".votar").off("click");
                            $(".votar").off("hover");
                        },
                        error: function() {
                            alert("Error al votar");
                        }
                    });
                });
            },
            error : function(){
                $("#pregunta").html(
                    "<div class=\"alert alert-danger\" role=\"alert\">"
                    +"Login incorrecto"
                    +"</div>");
            }
        });
    });