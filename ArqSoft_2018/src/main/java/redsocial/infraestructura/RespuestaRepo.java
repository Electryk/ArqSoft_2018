package redsocial.infraestructura;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redsocial.dominio.Respuesta;

@Repository
public interface RespuestaRepo extends CrudRepository<Respuesta,Long> {
}
