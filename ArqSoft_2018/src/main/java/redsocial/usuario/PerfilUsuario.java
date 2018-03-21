package redsocial.usuario;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import redsocial.dominio.Encuesta;
import redsocial.dominio.Voto;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@NodeEntity
public class PerfilUsuario {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private Date nacimiento;
    private String pais;
    private String sexo;
    
    @Relationship(type = "VOTA")
    private List<Voto> votosRealizados = new ArrayList<Voto>();

    @Relationship(type = "TIENE_ENCUESTAS")
    private List<Encuesta> encuestas = new ArrayList<>();

    public PerfilUsuario(String nombre, Date nacimiento, String pais){
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.pais = pais;
    }

    public PerfilUsuario() {}
/*
    public void agregarEncuesta(Encuesta encuesta) {
        this.encuestas.add(encuesta);
    }
*/
    public String getNombre() {
    	return nombre;
    }
    
    public void votar(Encuesta encuesta, int respuesta) {
    	votosRealizados.add(new Voto(this, encuesta, respuesta));
    }
    
    public List<Encuesta> getEncuestas() {
    	return encuestas;
    }
    
    @Override
    public String toString() {
        return "PerfilUsuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
