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

    public InfoEncuesta(){
        pregunta="def";
        respuestas=new ArrayList<>();
        votosPorRespuesta=new ArrayList<>();
        votos=0;
    }

    public InfoEncuesta(Encuesta encuesta,Map<Integer,Integer> votos){
        this.pregunta=encuesta.getPregunta();
        this.respuestas= new ArrayList<String>();
        votosPorRespuesta=new ArrayList<>();
        encuesta.getListaRespuestas().forEach((k,v)-> respuestas.add(Integer.parseInt(k),v));
        votos.forEach((k,v) -> {
            votosPorRespuesta.add(k,v);
            this.votos=this.votos+v;
        });
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
}
