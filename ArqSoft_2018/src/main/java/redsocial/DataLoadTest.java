package redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redsocial.dominio.Encuesta;
import redsocial.infraestructura.CuentaUsuarioRepo;
import redsocial.infraestructura.PerfilRepo;
import redsocial.usuario.*;
import redsocial.infraestructura.EncuestaRepo;
import redsocial.infraestructura.RespuestaRepo;

import java.util.ArrayList;
import java.util.Date;

@Component
public class DataLoadTest implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataLoadTest.class);

    @Autowired
    private CuentaUsuarioRepo cuentaUsuarioRepo;
    @Autowired
    private PerfilRepo perfilRepo;
    @Autowired
    private EncuestaRepo encuestaRepo;

    @Autowired
    private RespuestaRepo respuestaRepo;
    @Override
    public void run(String... args) throws Exception {

        cuentaUsuarioRepo.deleteAll();
        perfilRepo.deleteAll();
        encuestaRepo.deleteAll();
        respuestaRepo.deleteAll();

        CuentaUsuario testCuenta = new CuentaUsuario("Ivan","123");
        PerfilUsuario testPerfil = new PerfilUsuario("Ivan Escuin",new Date(1995,02,10),"España");

        log.info("Creando cuenta de usuario y perfil " + testCuenta +" " + testPerfil);
        testCuenta.AsignarPerfil(testPerfil);
//        perfilRepo.save(testPerfil);
        cuentaUsuarioRepo.save(testCuenta);

        log.info("Extracción de cuenta de usuario");

        CuentaUsuario testCuentaNeo4j = cuentaUsuarioRepo.findByNick("Ivan");
//        PerfilUsuario testPerfilNeo4k = perfilRepo.findByNombre("Ivan Escuin");
//                log.info("Nombre del perfil guardada: perfil asociado => "
//                + testPerfilNeo4k);

        log.info("Nombre de la cuenta guardada: Nick=> "+ testCuentaNeo4j.getNick() + "perfil asociado => "
                + testCuentaNeo4j.getPerfilUsuario());

        String r1 = "Opcion 1";
        String r2 = "Opcion 2";
        String r3 = "Opcion 3";

        ArrayList<String> testRespuestas = new ArrayList<String>();
        testRespuestas.add(r1);
        testRespuestas.add(r2);
        testRespuestas.add(r3);

        Encuesta testEncuesta = new Encuesta("Elige una opción",testCuentaNeo4j.getId(),testRespuestas);

        log.info("Creando encuesta: pregunta => "+ testEncuesta.toString());
//        encuestaRepo.save(testEncuesta);

        PerfilUsuario testPerfilNeo4j = testCuentaNeo4j.getPerfilUsuario();
        testPerfilNeo4j.agregarEncuesta(testEncuesta);
        perfilRepo.save(testPerfilNeo4j);



    }
}
