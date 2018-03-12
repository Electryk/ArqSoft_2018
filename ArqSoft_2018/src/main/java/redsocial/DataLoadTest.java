package redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redsocial.infraestructura.CuentaUsuarioRepo;
import redsocial.infraestructura.PerfilRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

import java.util.Date;

@Component
public class DataLoadTest implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataLoadTest.class);

    @Autowired
    private CuentaUsuarioRepo cuentaUsuarioRepo;
    @Autowired
    private PerfilRepo perfilRepo;

    @Override
    public void run(String... args) throws Exception {

        cuentaUsuarioRepo.deleteAll();
        perfilRepo.deleteAll();

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
    }
}
