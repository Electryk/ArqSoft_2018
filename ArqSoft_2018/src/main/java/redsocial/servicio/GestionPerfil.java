package redsocial.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redsocial.dominio.Voto;
import redsocial.spring.InfoPerfil;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;
import redsocial.servicio.CuentaUsuarioRepo;
import redsocial.servicio.PerfilUsuarioRepo;
import redsocial.servicio.GestionCuenta;

import java.util.List;

@Service
public class GestionPerfil {

	@Autowired
    private CuentaUsuarioRepo cuentaUsuarioRepo;
	@Autowired
    private PerfilUsuarioRepo perfilUsuarioRepo;
	
	@Autowired
	private GestionCuenta gestionCuenta;
	
    public boolean crearPerfil(CuentaUsuario cuenta, PerfilUsuario perfil) {
    	CuentaUsuario cuentaBD;
    	
        if (gestionCuenta.verificarCuenta(cuenta)) {
        	cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        	cuentaBD.asignarPerfil(perfil);
        	cuentaUsuarioRepo.save(cuentaBD);
        	return true;
        }else {
			return false;
		}
    }
    
    public  boolean modificarPerfil(Long id, InfoPerfil perfil, CuentaUsuario cuenta) {
    	CuentaUsuario cuentaBD;
    	PerfilUsuario perfilBD;
        
        if (gestionCuenta.verificarCuenta(cuenta)) {
        	perfilBD = perfilUsuarioRepo.findById(id).get();
        	perfilBD.modificarPerfil(perfil);
        	perfilUsuarioRepo.save(perfilBD);
        	return true;
        }
        
        return false;
    }

    public List<PerfilUsuario> buscarPerfilNombre(String nombre){
    	return perfilUsuarioRepo.findByNombreContainingOrNombreIsLike(nombre,nombre);
	}

	public PerfilUsuario obtenerPerfil(Long id){
    	return perfilUsuarioRepo.findById(id).get();
	}
    
    public void borrarTodo() {
    	perfilUsuarioRepo.deleteAll();
    }
    
}
