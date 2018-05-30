package redsocial.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import redsocial.usuario.CuentaUsuario;
import redsocial.dominio.Encuesta;
import redsocial.dominio.Voto;
import redsocial.servicio.CuentaUsuarioRepo;
import redsocial.servicio.VotoRepo;
import redsocial.servicio.GestionCuenta;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class GestionVoto {

	@Autowired
    private VotoRepo votoRepo;
	
	@Autowired
	private GestionCuenta gestionCuenta;
    
    public boolean crearVoto(CuentaUsuario cuenta, Voto voto) {
        if (gestionCuenta.verificarCuenta(cuenta)) {
        	votoRepo.save(voto);
        	
        	return true;
        }
        
        return false;
    }
    
    public Pair<Boolean,SortedMap<Integer, Integer>> obtenerVotosporRespuesta(Encuesta encuesta, String cliente) {
    	TreeMap<Integer, Integer> votos = new TreeMap<Integer,Integer>();
    	Boolean votada = false;
    	for (Voto v : encuesta.getVotos()) {
    		votos.put(v.getRespuesta(), votos.containsKey(v.getRespuesta()) ?
    				votos.get(v.getRespuesta())+1 :
    				1);
    		if(v.getPerfilUsuario().getNombre().equals(cliente)){
    		    votada = true;
            }
    	}
    	
    	return Pair.of(votada,votos);
    }

    public int votosDePerfil(String nombrePerfil){
    	return votoRepo.findByPerfilUsuarioNombre(nombrePerfil).size();
	}
    
    public void borrarTodo() {
    	votoRepo.deleteAll();
    }
    
}
