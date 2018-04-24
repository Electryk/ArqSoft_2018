package redsocial.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

public class GestionCuenta {

    public CuentaUsuarioRepo cuentaUsuarioRepo;

    public GestionCuenta(CuentaUsuarioRepo cuentaUsuarioRepo){
        this.cuentaUsuarioRepo=cuentaUsuarioRepo;
    }
    public  boolean crearCuenta(CuentaUsuario cuenta){
        if (cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario())==null) {
        	cuentaUsuarioRepo.save(cuenta);
        	
        	return true;
        }
        
        return false;
    }
    
    public  boolean crearPerfil(CuentaUsuario cuenta, PerfilUsuario perfil) {
    	CuentaUsuario cuentaBD;
    	
        if (verificarCuenta(cuenta)) {
        	cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        	cuentaBD.asignarPerfil(perfil);
        	cuentaUsuarioRepo.save(cuentaBD);
        	return true;
        }
        
        return false;
    }
    
    public  boolean verificarCuenta(CuentaUsuario cuenta) {
    	CuentaUsuario cuentaBD;
    	
        if ((cuentaBD=cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario()))!=null) {
        	if (cuenta.getPassword().equals(cuentaBD.getPassword())) {
        		return true;
        	}
        	
        	return false;
        }
        
        return false;
    }
    
    public  void borrarTodo() {
    	cuentaUsuarioRepo.deleteAll();
    }
    
}
