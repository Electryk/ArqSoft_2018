package redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import redsocial.usuario.*;
import redsocial.dominio.*;
import redsocial.servicio.GestionCuenta;
import redsocial.servicio.GestionPerfil;
import redsocial.servicio.GestionEncuesta;
import redsocial.servicio.GestionVoto;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Date;
import java.util.List;

@Component
public class DataLoadTest implements CommandLineRunner {
	
    private final Logger log = LoggerFactory.getLogger(DataLoadTest.class);
    
    @Autowired
    private GestionCuenta gestionCuenta;
    @Autowired
    private GestionPerfil gestionPerfil;
    @Autowired
    private GestionEncuesta gestionEncuesta;
    @Autowired
    private GestionVoto gestionVoto;
    
    @Override
    public void run(String... args) throws Exception {
        gestionCuenta.borrarTodo();
        gestionPerfil.borrarTodo();
        gestionEncuesta.borrarTodo();
        gestionVoto.borrarTodo();

        CuentaUsuario testCuenta1 = new CuentaUsuario("Ivan", "123");
        PerfilUsuario testPerfil1 = new PerfilUsuario("Ivan Escuin", new Date(1995,02,10), "España");

        log.info("Creando cuenta de usuario y perfil " + testCuenta1 +" " + testPerfil1);
        gestionCuenta.crearCuenta(testCuenta1);
        gestionPerfil.crearPerfil(testCuenta1, testPerfil1);

        CuentaUsuario testCuenta2 = new CuentaUsuario("Adrian", "321");
        PerfilUsuario testPerfil2 = new PerfilUsuario("Adrian Martinez", new Date(1989,06,01), "España");

        log.info("Creando cuenta de usuario y perfil " + testCuenta2 +" " + testPerfil2);
        gestionCuenta.crearCuenta(testCuenta2);
        gestionPerfil.crearPerfil(testCuenta2, testPerfil2);

        CuentaUsuario testCuenta3 = new CuentaUsuario("Chuck", "132");
        PerfilUsuario testPerfil3 = new PerfilUsuario("Chuck Norris", new Date(1977,07,07), "EEUU");

        log.info("Creando cuenta de usuario y perfil " + testCuenta3 +" " + testPerfil3);
        gestionCuenta.crearCuenta(testCuenta3);
        gestionPerfil.crearPerfil(testCuenta3, testPerfil3);

        log.info("Verificación de cuenta de usuario (login)");

        CuentaUsuario testCuentaNeo4j = new CuentaUsuario("Ivan", "123");
        log.info("login: " + gestionCuenta.verificarCuenta(testCuentaNeo4j));

        ArrayList<String> respuestas = new ArrayList<String>();
        respuestas.add("Real Madrid");
        respuestas.add("FC Barcelona");
        Encuesta testEncuesta = new Encuesta(testPerfil1, "Quien ganara la liga", respuestas);
        log.info("Encuesta creada: " + gestionEncuesta.crearEncuesta(testCuenta1, testEncuesta));

        Encuesta testEncuestaNeo4j = gestionEncuesta.buscarEncuestaPorPregunta("Quien ganara la liga");
        log.info("Pregunta guardada: => "+ testEncuestaNeo4j.getPregunta() + ", autor => "
                + testEncuestaNeo4j.getAutor());
        for (Encuesta e : gestionEncuesta.buscarEncuestasPorPreguntaAproximada("liga")) {
	        log.info("Pregunta guardada: => "+ e.getPregunta() + ", autor => "
	                + e.getAutor());
    	}
    	for (Encuesta e : gestionEncuesta.buscarEncuestasPorPerfilUsuarioAproximado("Ivan")) {
	        log.info("Pregunta guardada: => "+ e.getPregunta() + ", autor => "
	                + e.getAutor());
    	}
    	
    	Voto testVoto = new Voto(testPerfil1, testEncuesta, 2);
    	log.info("Voto 1 creado: " + gestionVoto.crearVoto(testCuenta1, testVoto));
    	log.info("Voto 2 creado: " +
    			gestionVoto.crearVoto(testCuenta2, new Voto(testPerfil2, testEncuesta, 1)));
    	log.info("Voto 3 creado: " +
    			gestionVoto.crearVoto(testCuenta3, new Voto(testPerfil3, testEncuesta, 1)));

    	testEncuesta = gestionEncuesta.buscarEncuestaPorPregunta(testEncuesta.getPregunta());
    	SortedMap<Integer, Integer> votos = gestionVoto.obtenerVotosporRespuesta(testEncuesta);
    	votos.forEach((m,k)->log.info("Respuesta " + m + ": " + k+ " votos"));
    }
    
}
