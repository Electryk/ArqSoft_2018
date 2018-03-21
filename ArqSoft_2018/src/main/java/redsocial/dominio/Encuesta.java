package redsocial.dominio;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Properties;

import redsocial.usuario.PerfilUsuario;
import redsocial.dominio.Voto;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@NodeEntity
public class Encuesta {

    @Id
    @GeneratedValue
    private Long id;
    
    private String pregunta;    
    @Properties
    private Map<String, String> listaRespuestas;

	@Relationship(type = "TIENE_ENCUESTAS", direction = Relationship.INCOMING)
    private PerfilUsuario perfilUsuario;

    @Relationship(type = "VOTA", direction = Relationship.INCOMING)
    private List<Voto> votos = new ArrayList<Voto>();

    public Encuesta(PerfilUsuario perfilUsuario, String pregunta, ArrayList<String> respuestas) {
        assert (respuestas.size() <= 10);
        
        this.pregunta = pregunta;
        this.perfilUsuario = perfilUsuario;
        this.listaRespuestas = new HashMap<String, String>();
        int idRespuesta = 1;
        for (String nombre:respuestas) {
            this.listaRespuestas.put(Integer.toString(idRespuesta), nombre);
            idRespuesta++;
        }
    }
    
    public Encuesta() {}
    
    public String getPregunta() {
    	return pregunta;
    }
    
    public String getAutor() {
    	return perfilUsuario.getNombre();
    }
    
    public List<Voto> getVotos() {
    	return votos;
    }
    
}
