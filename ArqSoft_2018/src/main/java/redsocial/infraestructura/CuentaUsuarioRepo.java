package redsocial.infraestructura;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import redsocial.usuario.CuentaUsuario;

import java.util.List;

@Repository
public interface CuentaUsuarioRepo extends Neo4jRepository<CuentaUsuario, Long> {
	
	List<CuentaUsuario> findByNombreUsuario(String nombreUsuario);
	Boolean existsByNombreUsuario(String nombreUsuario);
	
}
