$(document).ready(
    function(){
        $('.datepicker').datepicker({
            language: 'es'
        });
        $('input[type="checkbox"]').on('change', function() {
            $('input[type="checkbox"]').not(this).prop('checked', false);
        });
        $("#postCuenta").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type : "POST",
                    url : "/crearCuenta",
                    data : JSON.stringify({"nombreUsuario":document.getElementById("nombreUsr").value,
                                           "password":$.md5(document.getElementById("pass").value)}),
                    contentType : "application/json",
                    success : function() {
                        // $("#crearButton").attr({"type":"",class:"btn btn-success right float-right"});
                        // $("#crearButton").text("Crear Perfil!")
                        $("#tabPerfil").removeClass("disabled");
                        $('a[href="#perfil"]').tab('show');
                        $("#tabCuenta").addClass("disabled");
                        $("#resCuentaOK").html(
                            "<div class=\"alert alert-success\" role=\"alert\">Cuenta creada! Hora de crear un perfil</div>");
                        sessionStorage.setItem("usr",$("#nombreUsr").text());
                    },
                    error : function(){
                        $("#resCuenta").html(
                            "<div class=\"alert alert-danger\" role=\"alert\">"
                              +"Fallo al crear cuenta"
                            +"</div>");
                    }
                });
        });
        $("#postPerfil").submit(
            function (event) {
                event.preventDefault();
                var fechaNaciemiento = moment(document.getElementById("calendario").value,"DD-MM-YYYY");
                var sexo;
                if($("#sexoF").is(":checked")){
                    sexo="F";
                }
                if($("#sexoM").is(":checked")){
                    sexo="M";
                }
                $.ajax({
                   type: "POST",
                   url : "/perfilUsuario",
                    data : JSON.stringify({"nombre":document.getElementById("nombrePer").value,
                        "pais":document.getElementById("pais").value,
                        "nacimiento":fechaNaciemiento, "nombreCuenta":document.getElementById("nombreUsr").value,
                        "sexo":sexo
                    }),
                    contentType : "application/json",
                    success: function() {
                        $("#btnPerfil").remove();
                        $("#resPerfil").html(
                            "<div class=\"alert alert-success\" role=\"alert\">Perfil creado correctamente</div>" +
                            "<a class='btn btn-primary right float-right' href='../nuevaEncuesta.html'>" +
                            "Crear mi primera encuesta" +
                            "</a>");
                    },
                    error : function() {
                        $("#resPerfil").html(
                            "<div class=\"alert alert-danger\" role=\"alert\">"
                            +"Fallo al crear perfil"
                            +"</div>");
                    }
                });

            }
        )
    });