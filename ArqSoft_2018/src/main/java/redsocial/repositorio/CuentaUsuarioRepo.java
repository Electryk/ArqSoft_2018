package redsocial.repositorio;

import redsocial.dominio.CuentaUsuario;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

@Repository
public interface CuentaUsuarioRepo extends GraphRepository<CuentaUsuario> {
	
	CuentaUsuario findByNick(String Nick);
	
}
