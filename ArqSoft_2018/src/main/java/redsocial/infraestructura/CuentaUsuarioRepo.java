package redsocial.infraestructura;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redsocial.dominio.CuentaUsuario;

@Repository
public interface CuentaUsuarioRepo extends CrudRepository<CuentaUsuario,Long> {
	
	CuentaUsuario findByNick(String Nick);
	
}
