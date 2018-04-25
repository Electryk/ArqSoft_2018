package redsocial.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;
import redsocial.servicio.CuentaUsuarioRepo;

@Service
public class GestionCuenta {

	@Autowired
    private CuentaUsuarioRepo cuentaUsuarioRepo;
    
    public boolean crearCuenta(CuentaUsuario cuenta) {
        if (cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario()) == null) {
        	cuentaUsuarioRepo.save(cuenta);
        	
        	return true;
        }

        return false;
    }
    
    public boolean verificarCuenta(CuentaUsuario cuenta) {
    	CuentaUsuario cuentaBD;
    	
        if ((cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario())) != null) {
        	if (cuenta.getPassword().equals(cuentaBD.getPassword())) {
        		return true;
        	}
        	
        	return false;
        }
        
        return false;
    }
    
    public void borrarTodo() {
    	cuentaUsuarioRepo.deleteAll();
    }
    
}
