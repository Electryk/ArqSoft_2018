package redsocial.servicio;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import redsocial.usuario.PerfilUsuario;

@Repository
interface PerfilUsuarioRepo extends Neo4jRepository<PerfilUsuario, Long> {
	
    PerfilUsuario findByNombre(String nombre);
    
}
