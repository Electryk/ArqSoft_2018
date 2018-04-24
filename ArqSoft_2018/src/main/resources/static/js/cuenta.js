$(document).ready(
    function(){
        $('.datepicker').datepicker({
            language: 'es'
        });
        $("#inlineRadio1").click(function() {
            if(this.checked == true){
                this.checked = false;
            }
        });
        $("#inlineRadio2").click(function() {
            if(this.checked == true){
                this.checked = false;
            }
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