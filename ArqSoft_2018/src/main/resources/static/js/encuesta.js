
    function controlRespuesta(usuarioEncuesta,busqueda) {
        var nRespuesta;
        var votosRespuesta;
        var votosTotales;
        var encuestas;
        $("#unaEncuesta").load("./encuesta.html");
        $("#paginacion").pagination({
            dataSource:function (done) {
                if(busqueda){
                    $.ajax({
                        type: "GET",
                        url: "/buscarEncuesta",
                        data: {
                            "nombreUsuario": sessionStorage.getItem('nombrePerfil'),
                            "pregunta": busqueda
                        },
                        success: function (res) {
                            encuestas = res;
                            done(res);
                        }
                    });
                }else {
                    $.ajax({
                        type: "GET",
                        url: "/encuesta",
                        data: {
                            "nombreUsuario": sessionStorage.getItem('nombrePerfil'),
                            "usuarioEncuesta": usuarioEncuesta
                        },
                        success: function (res) {
                            encuestas = res;
                            done(res);
                        }
                    });
                }
            },
            pageSize: 1,
            autoHidePrevious: true,
            autoHideNext: true,
            beforePageOnClick: function(){
                $(".votar").remove();
            },
            beforeNextOnClick: function(){
                $(".votar").remove();
            },
            beforePreviousOnClick: function(){
                $(".votar").remove();
            },
            callback: function (data,pagination) {
                //$("#unaEncuesta").load("./encuesta.html");
                console.log(data);
                var res = data[0];
                $("#titulo").text(res.pregunta);
                $("#nvotos").text("Total de votos: " + res.votos);
                votosTotales = res.votos;
                votosRespuesta = res.votosPorRespuesta;
                res.respuestas.forEach(
                    function (item, index) {
                        $("#respuestas").append("<div class='row p-3 mb-2 votar' id=" + index + ">" +
                            "<h4 class='col-3'>" + item + "</h4>" +
                            "<div class='progress col-8' style='height: 30px;'><div class='progress-bar bg-warning text-dark' role='progressbar' " +
                            "style='width: 0%' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100'></div></div>" +
                            "</div");
                    }
                );
                if (encuestas[pagination.pageNumber -1].votada) {
                    mostrarEncuesta(votosRespuesta, votosTotales);
                } else {
                    $(".votar").hover(function () {
                        $(this).css('cursor', 'pointer');
                        $(this).addClass("bg-secondary");
                    }, function () {
                        $(this).css('cursor', 'auto');
                        $(this).removeClass("bg-secondary");
                    });
                    $(".votar").on("click", function () {
                        nRespuesta = $(this).attr("id");
                        $.ajax({
                            type: "PUT",
                            url: "/encuesta",
                            data: {
                                "nombreCuenta": sessionStorage.getItem("usr"), "nombreEncuesta": $("#titulo").text(),
                                "nRespuesta": $(this).attr("id")
                            },
                            success: function () {
                                votosTotales++;
                                votosRespuesta[nRespuesta]++;
                                encuestas[pagination.pageNumber -1].votada = true;
                                location.reload();
                                $("#nvotos").text(votosTotales);
                                mostrarEncuesta(votosRespuesta, votosTotales);

                            },
                            error: function () {
                                alert("Error al votar");
                            }
                        });
                    });
                }
            }
        })
    }

function mostrarEncuesta(votosRespuesta,votosTotales) {
    votosRespuesta.forEach(function (item, index) {
        var fill=(item/votosTotales)*100;
        $("#"+index+" .progress-bar").attr("aria-valuenow",fill)
        .css("width", fill+"%")
        .text(item+" votos");
    });
    $(".votar").off("click");
    $(".votar").off("hover");
}