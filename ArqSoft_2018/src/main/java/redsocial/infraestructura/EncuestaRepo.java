package redsocial.infraestructura;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redsocial.dominio.Encuesta;

@Repository
public interface EncuestaRepo extends CrudRepository<Encuesta, Long> {

    Encuesta findByPregunta(String pregunta);

}
