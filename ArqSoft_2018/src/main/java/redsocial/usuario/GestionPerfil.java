package redsocial.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import redsocial.usuario.GestionCuenta;
import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.PerfilUsuarioRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

public class GestionPerfil {

    @Autowired
    private static CuentaUsuarioRepo cuentaUsuarioRepo;
    @Autowired
    private static PerfilUsuarioRepo perfilUsuarioRepo;

    public static boolean modificarPerfil(CuentaUsuario cuenta, PerfilUsuario perfil) {
    	CuentaUsuario cuentaBD;
    	PerfilUsuario perfilBD;
    	
        if (GestionCuenta.verificarCuenta(cuenta)) {
        	cuentaBD = cuentaUsuarioRepo.findByNombreUsuario(cuenta.getNombreUsuario());
        	perfilBD = cuentaBD.getPerfilUsuario();
        	perfilBD.modificarPerfil(perfil);
        	perfilUsuarioRepo.save(perfilBD);
        	
        	return true;
        }
        
        return false;
    }
    
    public static void borrarTodo() {
    	perfilUsuarioRepo.deleteAll();
    }
    
}
