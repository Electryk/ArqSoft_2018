import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


//TODO: Porcentajes de votos de cada respuesta
public class Encuesta {

    private String identificador;
    private String pregunta;
    private String idUsuario;
    private HashMap<Integer,Respuesta> listaRespuestas;
    private int votosTotales;

    //Constructor encuesta nueva
    protected Encuesta(String pregunta, String idUsuario, ArrayList<String> respuestas){
        assert (respuestas.size() <= 10);
        this.identificador = UUID.randomUUID().toString();
        this.pregunta=pregunta;
        this.idUsuario=idUsuario;
        int idRespuesta=0;
        for (String nombre:respuestas) {
            this.listaRespuestas.put(idRespuesta,new Respuesta(nombre));
            idRespuesta++;
        }
        this.votosTotales = 0;

    }

    //Constructor encuesta existente
    protected Encuesta(String pregunta, String idUsuario, String identificador, HashMap<Integer,
            Respuesta> listaRespuestas){
        this.identificador = identificador;
        this.pregunta=pregunta;
        this.idUsuario=idUsuario;
        this.listaRespuestas=listaRespuestas;
        listaRespuestas.forEach((k,v) -> this.votosTotales += v.getVotos());
    }

    public boolean CrearEncuesta(){
        //TODO:Uso de la infraestructura para insertar una nueva encuesta en la BD
        boolean exito=false;
        return exito;
    }

    public static Encuesta ObtenerEncuesta(String identificador){
        String pregunta="", idUsuario="";
        HashMap<Integer,Respuesta> listaRespuestas = new HashMap<>();
        //TODO:Uso de la infraestructura para obtener una encuesta existente de la BD
        return new Encuesta(pregunta, idUsuario,identificador, listaRespuestas);
    }

    public boolean VotarEncuesta(int idRespuesta){
        this.votosTotales++;
        this.listaRespuestas.get(idRespuesta).VotaA();
        //TODO:Actualizar entrada en la BD
        boolean exito=false;
        return exito;
    }
}
