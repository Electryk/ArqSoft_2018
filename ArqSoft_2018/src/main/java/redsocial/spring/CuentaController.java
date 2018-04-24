package redsocial.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import redsocial.usuario.CuentaUsuarioRepo;
import redsocial.usuario.GestionCuenta;
import redsocial.usuario.CuentaUsuario;

@RestController
public class CuentaController {

    private static final Logger log = LoggerFactory.getLogger(CuentaController.class);
    @Autowired
    CuentaUsuarioRepo cuentaUsuarioRepo;
    @RequestMapping(value = "/crearCuenta", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity crearNuevaCuenta(@RequestBody CuentaUsuario nuevaCuenta ){
        log.info("He recibido cuenta");
        log.info(nuevaCuenta.getPassword());
        log.info(nuevaCuenta.toString());
        GestionCuenta gestionCuenta=new GestionCuenta(cuentaUsuarioRepo);
        if(!gestionCuenta.crearCuenta(nuevaCuenta)) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        log.info("Cuenta creada");
        return new ResponseEntity(HttpStatus.OK);
    }
}
