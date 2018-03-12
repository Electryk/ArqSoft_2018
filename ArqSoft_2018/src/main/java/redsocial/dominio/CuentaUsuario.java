package redsocial.dominio;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.GeneratedValue;

@NodeEntity
public class CuentaUsuario {

	@org.neo4j.ogm.annotation.Id
    @GeneratedValue
	private Long Id;
	
	private String Nick;
	private String Pass;
	
//	@Relationship(type = "TIENE_PERFIL", direction = "OUTGOING")
//    private PerfilUsuario perfilUsuario;
	
}
