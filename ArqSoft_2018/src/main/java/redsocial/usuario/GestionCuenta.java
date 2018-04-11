package redsocial.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

public class GestionCuenta {

    @Autowired
    private static CuentaUsuarioRepo cuentaUsuarioRepo;

    public static boolean crearCuenta(CuentaUsuario cuenta){
        if (!cuentaUsuarioRepo.existsByNombreUsuario(cuenta.getNombreUsuario())) {
        	cuentaUsuarioRepo.save(cuenta);
        	
        	return true;
        }
        
        return false;
    }
    
    public static boolean crearPerfil(CuentaUsuario cuenta, PerfilUsuario perfil) {
    	CuentaUsuario cuentaBD;
    	
        if (verificarCuenta(cuenta)) {
        	cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        	cuentaBD.asignarPerfil(perfil);
        	cuentaUsuarioRepo.save(cuentaBD);
        }
        
        return false;
    }
    
    public static boolean verificarCuenta(CuentaUsuario cuenta) {
    	CuentaUsuario cuentaBD;
    	
        if (cuentaUsuarioRepo.existsByNombreUsuario(cuenta.getNombreUsuario())) {
        	cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        	if (cuenta.getPassword().equals(cuentaBD.getPassword())) {
        		return true;
        	}
        	
        	return false;
        }
        
        return false;
    }
    
    public static void borrarTodo() {
    	cuentaUsuarioRepo.deleteAll();
    }
    
}
