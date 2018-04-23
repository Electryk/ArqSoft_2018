package redsocial.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import redsocial.usuario.GestionCuenta;
import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.PerfilUsuarioRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

public class GestionPerfil {

    private  CuentaUsuarioRepo cuentaUsuarioRepo;
    private  PerfilUsuarioRepo perfilUsuarioRepo;

    public GestionPerfil(PerfilUsuarioRepo perfilUsuarioRepo, CuentaUsuarioRepo cuentaUsuarioRepo){
        this.perfilUsuarioRepo=perfilUsuarioRepo;
        this.cuentaUsuarioRepo=cuentaUsuarioRepo;
    }
    public  boolean modificarPerfil(CuentaUsuario cuenta, PerfilUsuario perfil) {
    	CuentaUsuario cuentaBD;
    	PerfilUsuario perfilBD;
        GestionCuenta gestionCuenta = new GestionCuenta(cuentaUsuarioRepo);
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
