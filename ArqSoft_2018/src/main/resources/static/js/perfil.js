$(document).ready(
    function(){
        $('.datepicker').datepicker({
            language: 'es'
        });
        $('input[type="checkbox"]').on('change', function() {
            $('input[type="checkbox"]').not(this).prop('checked', false);
        });
        $.ajax({
            type : "GET",
            url : "/perfilUsuario",
            data : {"nombreCuenta":sessionStorage.getItem("usr")},
            success : function(res) {
                $("#nombrePerfil").text(res.nombre);
                $("#nombre").val(res.nombre);
                $("#calendario").val(res.nacimiento);
                if(res.sexo ==='M'){
                    $('sexoM').attr('checked',true);
                }else if(res.sexo === 'F'){
                    $('sexoF').attr('checked',true);
                }
                $("#pais").val(res.pais.toLowerCase());
                controlRespuesta(res.nombre);

                $("#putPerfil").submit(
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
                            type: "PUT",
                            url : "/perfilUsuario/"+res.id,
                            data : JSON.stringify({"nombre":document.getElementById("nombre").value,
                                "pais":document.getElementById("pais").value,
                                "nacimiento":fechaNaciemiento, "nombreCuenta":sessionStorage.getItem('usr'),
                                "sexo":sexo
                            }),
                            contentType : "application/json",
                            success: function() {
                                sessionStorage.setItem('nombrePerfil',document.getElementById("nombre").value);
                                $("#resPerfil").html(
                                    "<div class=\"alert alert-success\" role=\"alert\">Perfil actualizado correctamente</div>");
                            },
                            error : function() {
                                $("#resPerfil").html(
                                    "<div class=\"alert alert-danger\" role=\"alert\">"
                                    +"Fallo al cambiar perfil"
                                    +"</div>");
                            }
                        });

                    }
                )
            },
            error : function(){
                $("#nombrePerfil").html(
                    "<div class=\"alert alert-danger\" role=\"alert\">"
                    +"Login incorrecto"
                    +"</div>");
            }
        });
    });