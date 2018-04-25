package redsocial.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;
import redsocial.servicio.CuentaUsuarioRepo;
import redsocial.servicio.PerfilUsuarioRepo;
import redsocial.servicio.GestionCuenta;

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
        }
        
        return false;
    }
    
    public  boolean modificarPerfil(CuentaUsuario cuenta, PerfilUsuario perfil) {
    	CuentaUsuario cuentaBD;
    	PerfilUsuario perfilBD;
        
        if (gestionCuenta.verificarCuenta(cuenta)) {
        	cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        	perfilBD = cuentaBD.getPerfilUsuario();
        	perfilBD.modificarPerfil(perfil);
        	perfilUsuarioRepo.save(perfilBD);
        	
        	return true;
        }
        
        return false;
    }
    
    public void borrarTodo() {
    	perfilUsuarioRepo.deleteAll();
    }
    
}
