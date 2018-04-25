package redsocial.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import redsocial.servicio.GestionPerfil;

@RestController
public class PerfilController {

    @Autowired
    private GestionPerfil gestionPerfil;

    private static final Logger log = LoggerFactory.getLogger(PerfilController.class);

    @RequestMapping(value = "/perfilUsuario",method = RequestMethod.POST)
    public ResponseEntity crearPerfil(@RequestBody PlantillaPerfil nuevoPerfil){

        log.info("Peticion de creacion perfil recibida");
        log.info(nuevoPerfil.toString());
        CuentaUsuario cuentaUsuario = cuentaUsuarioRepo.findByNombreUsuario(nuevoPerfil.getNombreCuenta());
        PerfilUsuario perfilUsuario = nuevoPerfil.extraerPerfil();

        GestionCuenta gestionCuenta = new GestionCuenta(cuentaUsuarioRepo);
        if(gestionCuenta.crearPerfil(cuentaUsuario,perfilUsuario)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }


    }
}
