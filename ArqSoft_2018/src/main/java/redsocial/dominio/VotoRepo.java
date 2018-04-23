package redsocial.dominio;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import redsocial.dominio.Voto;

@Repository
interface VotoRepo extends Neo4jRepository<Voto, Long> {

    List<Voto> findByPerfilUsuarioNombre(String nombre);

}
