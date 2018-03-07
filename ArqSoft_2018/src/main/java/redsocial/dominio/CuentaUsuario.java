package redsocial.dominio;

public class CuentaUsuario {

	@Id
	@GeneratedValue
	private Long Id;
	
	private String Nick;
	private String Pass;
	
	@Relationship(type = "TIENE_PERFIL", direction = "OUTGOING")
    private PerfilUsuario perfilUsuario;
	
}
