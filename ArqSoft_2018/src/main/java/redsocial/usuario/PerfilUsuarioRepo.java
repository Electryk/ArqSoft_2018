package redsocial.usuario;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepo extends Neo4jRepository<PerfilUsuario, Long> {
	
    PerfilUsuario findByNombre(String nombre);
    
}
