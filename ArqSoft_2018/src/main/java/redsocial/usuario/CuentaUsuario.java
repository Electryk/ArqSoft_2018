package redsocial.usuario;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Index;

@NodeEntity
public class CuentaUsuario {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
	private Long id;

    @Index(unique = true) private String nombreUsuario;
    private String password;

	@Relationship(type = "TIENE_PERFIL")
    private PerfilUsuario perfilUsuario;

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    //Empty constructor requiered by NeoJ4
	public CuentaUsuario() {

    }

    public CuentaUsuario(String nombreUsuario,String password) {
	    this.nombreUsuario=nombreUsuario;
	    this.password=password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void asignarPerfil(PerfilUsuario perfilUsuario) {
	    this.perfilUsuario=perfilUsuario;
    }

    @Override
    public String toString() {
        return "CuentaUsuario{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", Contraseña='" + password + '\'' +
                ", perfilUsuario=" + perfilUsuario +
                '}';
    }

    public String getPassword() {
        return password;
    }
}
