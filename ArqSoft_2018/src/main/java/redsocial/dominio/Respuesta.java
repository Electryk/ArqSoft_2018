package redsocial.dominio;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


//Clase no usada pero mantenida en caso de rollback
@NodeEntity
public class Respuesta {

    @Id
    @GeneratedValue
    private long id;

    private String titulo;
    private int votos;

    public String getTitulo() {
        return titulo;
    }

    public int getVotos() {
        return votos;
    }

    public Respuesta(String nombre) {
        this.titulo=nombre;
        this.votos = 0;
    }
    public void VotaA(){
        this.votos++;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "titulo='" + titulo + '\'' +
                ", votos=" + votos +
                '}';
    }
}
