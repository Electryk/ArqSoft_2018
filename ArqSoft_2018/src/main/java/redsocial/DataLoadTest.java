package redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import redsocial.usuario.*;
import redsocial.dominio.Encuesta;
import redsocial.dominio.Voto;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.List;

@Component
public class DataLoadTest implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataLoadTest.class);
    @Autowired
    private CuentaUsuarioRepo cuentaRepo;
    @Autowired
    private PerfilUsuarioRepo perfilRepo;
    @Override
    public void run(String... args) throws Exception {

        GestionCuenta gestionCuenta =  new GestionCuenta(cuentaRepo);
        GestionPerfil gestionPerfil = new GestionPerfil(perfilRepo,cuentaRepo);
        gestionCuenta.borrarTodo();
        gestionPerfil.borrarTodo();

        CuentaUsuario testCuenta1 = new CuentaUsuario("Ivan","123");
        PerfilUsuario testPerfil1 = new PerfilUsuario("Ivan Escuin",new Date(1995,02,10),"España");

        log.info("Creando cuenta de usuario y perfil " + testCuenta1 +" " + testPerfil1);
        gestionCuenta.crearCuenta(testCuenta1);
        gestionCuenta.crearPerfil(testCuenta1, testPerfil1);

        CuentaUsuario testCuenta2 = new CuentaUsuario("Adrian","321");
        PerfilUsuario testPerfil2 = new PerfilUsuario("Adrian Martinez",new Date(1989,06,01),"España");

        log.info("Creando cuenta de usuario y perfil " + testCuenta2 +" " + testPerfil2);
        gestionCuenta.crearCuenta(testCuenta2);
        gestionCuenta.crearPerfil(testCuenta2, testPerfil2);

        CuentaUsuario testCuenta3 = new CuentaUsuario("Chuck","132");
        PerfilUsuario testPerfil3 = new PerfilUsuario("Chuck Norris",new Date(1977,07,07),"EEUU");

        log.info("Creando cuenta de usuario y perfil " + testCuenta3 +" " + testPerfil3);
        gestionCuenta.crearCuenta(testCuenta3);
        gestionCuenta.crearPerfil(testCuenta3, testPerfil3);
/*
        log.info("Extracción de cuenta de usuario");

        CuentaUsuario testCuentaNeo4j = cuentaRepo.findByNombreUsuario("Ivan");
//        PerfilUsuario testPerfilNeo4k = perfilRepo.findByNombre("Ivan Escuin");
//                log.info("Nombre del perfil guardada: perfil asociado => "
//                + testPerfilNeo4k);

        log.info("Nombre de la cuenta guardada: => "+ testCuentaNeo4j.getNombreUsuario() + "perfil asociado => "
                + testCuentaNeo4j.getPerfilUsuario());

        ArrayList<String> respuestas = new ArrayList<String>();
        respuestas.add("Real Madrid");
        respuestas.add("FC Barcelona");
        Encuesta testEncuesta = new Encuesta(testPerfil1, "Quien ganara la liga", respuestas);
        encuestaRepo.save(testEncuesta);
//      Encuesta testEncuesta = new Encuesta("Quien ganara la liga", respuestas);
//      testPerfil1.agregarEncuesta(testEncuesta);
//      perfilRepo.save(testPerfil1);
        log.info("Creando encuesta");

        Encuesta testEncuestaNeo4j = encuestaRepo.findByPregunta("Quien ganara la liga");
        log.info("Pregunta guardada: => "+ testEncuestaNeo4j.getPregunta() + ", autor => "
                + testEncuestaNeo4j.getAutor());
        for (Encuesta e : encuestaRepo.findByPreguntaContaining("liga")) {
	        log.info("Pregunta guardada: => "+ e.getPregunta() + ", autor => "
	                + e.getAutor());
    	}
    	for (Encuesta e : encuestaRepo.findByPerfilUsuarioNombre("Ivan Escuin")) {
	        log.info("Pregunta guardada: => "+ e.getPregunta() + ", autor => "
	                + e.getAutor());
    	}

    	testPerfil2.votar(testEncuesta, 1);
    	perfilRepo.save(testPerfil2);
    	testPerfil3.votar(testEncuesta, 2);
    	perfilRepo.save(testPerfil3);

    	testEncuestaNeo4j = encuestaRepo.findByPregunta("Quien ganara la liga");
    	HashMap<Integer, Integer> votos = new HashMap<Integer,Integer>();
    	for (Voto v : testEncuestaNeo4j.getVotos()) {
    		votos.put(v.getRespuesta(), votos.containsKey(v.getRespuesta()) ?
    				votos.get(v.getRespuesta())+1 :
    				1);
    	}
//        votos.forEach((m,k)->log.info("Respuesta " + m + ": " + k+ " votos"));
        for (Map.Entry<Integer, Integer> e : votos.entrySet()) {
        	log.info("Respuesta " + e.getKey() + ": " + e.getValue() + " votos");
        }
*/

    }
}
