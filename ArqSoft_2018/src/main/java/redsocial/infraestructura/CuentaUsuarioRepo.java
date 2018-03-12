package redsocial.infraestructura;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redsocial.usuario.CuentaUsuario;

@Repository
public interface CuentaUsuarioRepo extends CrudRepository<CuentaUsuario,Long> {
	
	CuentaUsuario findByNick(String Nick);
	Boolean existsByNick(String Nick);

//    CuentaUsuario findByName(String Nick);
//	Boolean existsByName(String Nick);
	
}
