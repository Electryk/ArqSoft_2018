package redsocial.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redsocial.usuario.CuentaUsuario;
import redsocial.dominio.Encuesta;
import redsocial.servicio.EncuestaRepo;
import redsocial.servicio.GestionCuenta;

import java.util.List;

@Service
public class GestionEncuesta {

	@Autowired
    private EncuestaRepo encuestaRepo;
	
	@Autowired
	private GestionCuenta gestionCuenta;
    
    public Encuesta buscarEncuestaPorPregunta(String pregunta) {
        return encuestaRepo.findByPregunta(pregunta);
    }
    
    public List<Encuesta> buscarEncuestasPorPreguntaAproximada(String pregunta) {
        return encuestaRepo.findByPreguntaContainingOrPreguntaIsLike(pregunta,pregunta);
    }
    public List<Encuesta> buscarEncuestasPorPerfilusuario(String nombre){
        return encuestaRepo.findByPerfilUsuarioNombre(nombre);
    }
    public List<Encuesta> buscarEncuestasPorPerfilUsuarioAproximado(String nombre) {
        return encuestaRepo.findByPerfilUsuarioNombreContaining(nombre);
    }
    
    public boolean crearEncuesta(CuentaUsuario cuenta, Encuesta encuesta) {
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
