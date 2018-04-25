package redsocial.servicio;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import redsocial.usuario.CuentaUsuario;

@Repository
interface CuentaUsuarioRepo extends Neo4jRepository<CuentaUsuario, Long> {
	
	CuentaUsuario findByNombreUsuario(String nombreUsuario);
	
}
