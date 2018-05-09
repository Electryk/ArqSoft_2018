var nRespuestas = 2

function addRespuesta(){
    if(nRespuestas < 10){
        nRespuestas++;
        $("#finEntradas").before(
            "<div class=\"form-group\">"
               +"<label>Respuesta "+nRespuestas+"</label>"
                +"<input class=\"form-control\" type=\"text\" id=\"r"+nRespuestas+"\" />"
            +"</div>"
        );
    }
}
$(document).ready(
    function(){
        $("#postEncuesta").submit(
            function(event) {
                event.preventDefault();
                var respuestas = [];
                for(i=1;i<=nRespuestas;i++){
                    respuestas.push(document.getElementById("r"+i).value);
                }
                $.ajax({
                    type : "POST",
                    url : "/crearEncuesta",
                    data : JSON.stringify({"pregunta":document.getElementById("pregunta").value,
                                            "respuestas":respuestas,"autor":sessionStorage.getItem("usr")}),
                    contentType : "application/json",
                    success : function() {
                        $("#resEncuesta").html(
                            "<div class=\"alert alert-success\" role=\"alert\">Encuesta creada</div>");
                    },
                    error : function(){
                        $("#resEncuesta").html(
                            "<div class=\"alert alert-danger\" role=\"alert\">"
                              +"Fallo al crear encuesta"
                            +"</div>");
                    }
                });
        });
    });