package redsocial.servicio;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import redsocial.dominio.Encuesta;

import java.util.List;

@Repository
interface EncuestaRepo extends Neo4jRepository<Encuesta, Long> {

    Encuesta findByPregunta(String pregunta);
    List<Encuesta> findByPreguntaContainingOrPreguntaIsLike(String pregunta1,String pregunta2);
    List<Encuesta> findByPerfilUsuarioNombreContaining(String nombre);
    List<Encuesta> findByPerfilUsuarioNombre(String nombre);
}
