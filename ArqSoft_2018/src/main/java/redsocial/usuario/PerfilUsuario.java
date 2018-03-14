package redsocial.usuario;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import redsocial.dominio.Encuesta;
import redsocial.dominio.Voto;

import java.util.Set;
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
    
   // @Relationship(type = "VOTA")
   // private Set<Voto> votosRealizados;

//    @Relationship(type = "TIENE_ENCUESTAS")
//    private ArrayList<Encuesta> encuestas;
    //TODO:Imagen de perfil

    //Empty constructor requiered by NeoJ4
    public PerfilUsuario() {

    }

    public PerfilUsuario(String nombre, Date nacimiento, String pais){
        this.nombre=nombre;
        this.nacimiento=nacimiento;
        this.pais=pais;
    }

//    public void agregarEncuesta(Encuesta encuesta){
//        this.encuestas.add(encuesta);
//    }

    public String getNombre() {
    	return nombre;
    }
    
    @Override
    public String toString() {
        return "PerfilUsuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
