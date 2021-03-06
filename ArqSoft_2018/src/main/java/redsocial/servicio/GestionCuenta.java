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

    public CuentaUsuario obtenerCuenta(String nombre){
        return cuentaUsuarioRepo.findByNombreUsuario(nombre);
    }
    
    public boolean verificarCuenta(CuentaUsuario cuenta) {
        CuentaUsuario cuentaBD=cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        if (cuentaBD != null) {
            if (cuenta.getPassword().equals(cuentaBD.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }
    
    public void borrarTodo() {
    	cuentaUsuarioRepo.deleteAll();
    }
    
}
