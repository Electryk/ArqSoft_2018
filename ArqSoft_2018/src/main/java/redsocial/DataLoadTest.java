package redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import redsocial.infraestructura.CuentaUsuarioRepo;
import redsocial.infraestructura.PerfilUsuarioRepo;
import redsocial.infraestructura.EncuestaRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;
import redsocial.dominio.Encuesta;

import java.util.ArrayList;
import java.util.Date;

@Component
public class DataLoadTest implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataLoadTest.class);

    @Autowired
    private CuentaUsuarioRepo cuentaRepo;
    @Autowired
    private PerfilUsuarioRepo perfilRepo;
    @Autowired
    private EncuestaRepo encuestaRepo;

    @Override
    public void run(String... args) throws Exception {

        cuentaRepo.deleteAll();
        perfilRepo.deleteAll();
        encuestaRepo.deleteAll();

        CuentaUsuario testCuenta = new CuentaUsuario("Ivan","123");
        PerfilUsuario testPerfil = new PerfilUsuario("Ivan Escuin",new Date(1995,02,10),"España");

        log.info("Creando cuenta de usuario y perfil " + testCuenta +" " + testPerfil);
        testCuenta.asignarPerfil(testPerfil);

//        perfilRepo.save(testPerfil);
        cuentaRepo.save(testCuenta);

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
        Encuesta testEncuesta = new Encuesta("Quien ganara la liga", respuestas, testPerfil);
        encuestaRepo.save(testEncuesta);
        log.info("Creando encuesta");
        
        Encuesta testEncuestaNeo4j = encuestaRepo.findByPregunta("Quien ganara la liga");
        log.info("Pregunta guardada: => "+ testEncuestaNeo4j.getPregunta() + ", autor => "
                + testEncuestaNeo4j.getAutor());
        testEncuestaNeo4j = encuestaRepo.findByPreguntaContaining("liga");
        log.info("Pregunta guardada: => "+ testEncuestaNeo4j.getPregunta() + ", autor => "
                + testEncuestaNeo4j.getAutor());
        testEncuestaNeo4j = encuestaRepo.findByPerfilUsuarioNombre("Ivan Escuin");
        log.info("Pregunta guardada: => "+ testEncuestaNeo4j.getPregunta() + ", autor => "
                + testEncuestaNeo4j.getAutor());
    }
}
