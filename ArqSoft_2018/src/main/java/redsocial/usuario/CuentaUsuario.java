package redsocial.usuario;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class CuentaUsuario {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
	private Long id;

	private String nick;

    public String getNick() {
        return nick;
    }

    private String Pass;

	@Relationship(type = "TIENE_PERFIL")
    private PerfilUsuario perfilUsuario;

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    //Empty constructor requiered by NeoJ4
	public CuentaUsuario(){

    }

    public CuentaUsuario(String nick,String pass){
	    this.nick=nick;
	    this.Pass=pass;
    }

    public void AsignarPerfil(PerfilUsuario perfilUsuario){
	    this.perfilUsuario=perfilUsuario;
    }

    @Override
    public String toString() {
        return "CuentaUsuario{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", Pass='" + Pass + '\'' +
                ", perfilUsuario=" + perfilUsuario +
                '}';
    }
}
