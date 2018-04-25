package redsocial.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import redsocial.servicio.GestionCuenta;
import redsocial.servicio.GestionPerfil;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

@RestController
public class PerfilController {

    @Autowired
    private GestionPerfil gestionPerfil;

    @Autowired
    private GestionCuenta gestionCuenta;

    private static final Logger log = LoggerFactory.getLogger(PerfilController.class);

    @RequestMapping(value = "/perfilUsuario",method = RequestMethod.POST)
    public ResponseEntity crearPerfil(@RequestBody PlantillaPerfil nuevoPerfil){

        log.info("Peticion de creacion perfil recibida");
        log.info(nuevoPerfil.toString());
        CuentaUsuario cuentaUsuario = gestionCuenta.obtenerCuenta(nuevoPerfil.getNombreCuenta());
        PerfilUsuario perfilUsuario = nuevoPerfil.extraerPerfil();

        if(cuentaUsuario != null && gestionPerfil.crearPerfil(cuentaUsuario,perfilUsuario)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }


    }
}
