package redsocial.infraestructura;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redsocial.usuario.PerfilUsuario;

@Repository
public interface PerfilRepo extends CrudRepository<PerfilUsuario,Long> {
    PerfilUsuario findByNombre(String nombre);
}
