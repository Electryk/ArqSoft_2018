package redsocial.dominio;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Respuesta {
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
}
