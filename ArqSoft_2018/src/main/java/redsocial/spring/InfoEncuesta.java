package redsocial.spring;

import redsocial.dominio.Encuesta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoEncuesta {
    private String pregunta;
    private List<String> respuestas;
    private List<Integer> votosPorRespuesta;
    private int votos=0;
    private boolean votada;

    public InfoEncuesta(){
        pregunta="def";
        respuestas=new ArrayList<>();
        votosPorRespuesta=new ArrayList<>();
        votos=0;
        votada=false;
    }

    public InfoEncuesta(Encuesta encuesta,Map<Integer,Integer> votos,boolean votada){
        this.pregunta=encuesta.getPregunta();
        this.respuestas= new ArrayList<String>();
        this.votosPorRespuesta=new ArrayList<Integer>();
        encuesta.getListaRespuestas().forEach((k,v)-> {
            respuestas.add(Integer.parseInt(k),v);
            votosPorRespuesta.add(Integer.parseInt(k),0);
        });
        votos.forEach((k,v) -> {
            votosPorRespuesta.add(k,v);
            this.votos=this.votos+v;
        });
        this.votada = votada;
        }

    public String getPregunta() {
        return pregunta;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    public int getVotos() {
        return votos;
    }

    public List<Integer> getVotosPorRespuesta() {
        return votosPorRespuesta;
    }

    public Boolean getVotada() {return votada;}
}
