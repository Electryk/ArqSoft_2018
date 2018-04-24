package redsocial.dominio;

import redsocial.usuario.GestionCuenta;
import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.dominio.EncuestaRepo;
import redsocial.dominio.Encuesta;

public class GestionEncuesta {

    public CuentaUsuarioRepo cuentaUsuarioRepo;
    public EncuestaRepo encuestaRepo;

    public GestionEncuesta(EncuestaRepo encuestaRepo, CuentaUsuarioRepo cuentaUsuarioRepo) {
    	this.encuestaRepo = encuestaRepo;
        this.cuentaUsuarioRepo = cuentaUsuarioRepo;
    }
    
    public boolean crearEncuesta(CuentaUsuario cuenta, Encuesta encuesta) {
        GestionCuenta gestionCuenta = new GestionCuenta(cuentaUsuarioRepo);
        
        if (gestionCuenta.verificarCuenta(cuenta)) {
        	encuestaRepo.save(encuesta);
        	
        	return true;
        }
        
        return false;
    }
    
    public void borrarTodo() {
    	encuestaRepo.deleteAll();
    }
    
}
