package redsocial.spring;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import redsocial.servicio.GestionCuenta;
import redsocial.servicio.GestionPerfil;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/perfilUsuario", method = RequestMethod.GET)
    public PerfilUsuario obtenerPerfil(@RequestParam(value="nombreCuenta") String nombreCuenta, HttpServletResponse response){

        log.info("Peticion de obtencion de perfil recibida");
        try {
            return gestionCuenta.obtenerCuenta(nombreCuenta).getPerfilUsuario();
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
}
