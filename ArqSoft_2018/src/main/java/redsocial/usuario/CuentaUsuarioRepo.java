package redsocial.usuario;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaUsuarioRepo extends Neo4jRepository<CuentaUsuario, Long> {
	
	CuentaUsuario findByNombreUsuario(String nombreUsuario);
	
}
