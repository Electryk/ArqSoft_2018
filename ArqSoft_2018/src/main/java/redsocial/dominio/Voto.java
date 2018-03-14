package redsocial.dominio;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.EndNode;

import redsocial.usuario.PerfilUsuario;
import redsocial.dominio.Encuesta;

@RelationshipEntity(type = "VOTA")
public class Voto {

	@Id
	@GeneratedValue
	private Long id;
	
	@StartNode private PerfilUsuario perfilUsuario;
	@EndNode private Encuesta encuesta;
	private int respuesta;
	
	public Voto (PerfilUsuario perfilUsuario, Encuesta encuesta, int respuesta) {
		this.perfilUsuario = perfilUsuario;
		this.encuesta = encuesta;
		this.respuesta = respuesta;
	}
	
}
