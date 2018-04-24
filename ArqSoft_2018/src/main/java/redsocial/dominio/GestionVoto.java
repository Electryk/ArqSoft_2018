package redsocial.dominio;

import redsocial.usuario.GestionCuenta;
import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.DataLoadTest;
import redsocial.dominio.Encuesta;
import redsocial.dominio.VotoRepo;
import redsocial.dominio.Voto;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class GestionVoto {

    public CuentaUsuarioRepo cuentaUsuarioRepo;
    public VotoRepo votoRepo;

    public GestionVoto(VotoRepo votoRepo, CuentaUsuarioRepo cuentaUsuarioRepo) {
    	this.votoRepo = votoRepo;
        this.cuentaUsuarioRepo = cuentaUsuarioRepo;
    }
    
    public boolean crearVoto(CuentaUsuario cuenta, Voto voto) {
        GestionCuenta gestionCuenta = new GestionCuenta(cuentaUsuarioRepo);
        
        if (gestionCuenta.verificarCuenta(cuenta)) {
        	votoRepo.save(voto);
        	
        	return true;
        }
        
        return false;
    }
    
    public SortedMap<Integer, Integer> obtenerVotosporRespuesta(Encuesta encuesta) {
    	TreeMap<Integer, Integer> votos = new TreeMap<Integer,Integer>();
    	for (Voto v : encuesta.getVotos()) {
    		votos.put(v.getRespuesta(), votos.containsKey(v.getRespuesta()) ?
    				votos.get(v.getRespuesta())+1 :
    				1);
    	}
    	
    	return votos;
    }
    
    public void borrarTodo() {
    	votoRepo.deleteAll();
    }
    
}
