package redsocial.dominio;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.*;


//TODO: Porcentajes de votos de cada respuesta
@NodeEntity
public class Encuesta {

    @Id
    @GeneratedValue
    private Long identificador;

    private String pregunta;
    private Long idUsuario;

    @Relationship(type="TIENE_RESPUESTAS")
    private ArrayList<Respuesta> listaRespuestas = new ArrayList<>();

    private int votosTotales;

    //Constructor encuesta nueva
    public Encuesta(String pregunta, Long idUsuario, ArrayList<String> respuestas){
        assert (respuestas.size() <= 10);
        this.pregunta=pregunta;
        this.idUsuario=idUsuario;
        for (String nombre:respuestas) {
            this.listaRespuestas.add(new Respuesta(nombre));
        }
        this.votosTotales = 0;

    }

    //Constructor encuesta existente
    public Encuesta(String pregunta, Long idUsuario, Long identificador, ArrayList<Respuesta> listaRespuestas){
        this.identificador = identificador;
        this.pregunta=pregunta;
        this.idUsuario=idUsuario;
        this.listaRespuestas=listaRespuestas;
        for (Respuesta v: listaRespuestas) {
            this.votosTotales+=v.getVotos();
        }
    }

    public boolean CrearEncuesta(){
        //TODO:Uso de la infraestructura para insertar una nueva encuesta en la BD
        boolean exito=false;
        return exito;
    }

    public static Encuesta ObtenerEncuesta(Long identificador){
        String pregunta="";
        Long idUsuario= new Long(0);
        ArrayList<Respuesta> listaRespuestas = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Encuesta{" +
                "pregunta='" + pregunta + '\'' +
                ", idUsuario=" + idUsuario +
                ", listaRespuestas=" + listaRespuestas +
                ", votosTotales=" + votosTotales +
                '}';
    }
}
