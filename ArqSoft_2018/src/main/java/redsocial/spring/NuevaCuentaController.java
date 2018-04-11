package redsocial.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import redsocial.usuario.GestionCuenta;
import redsocial.usuario.CuentaUsuario;

@RestController
public class NuevaCuentaController {

    private static final Logger log = LoggerFactory.getLogger(NuevaCuentaController.class);
    @RequestMapping(value = "/crearCuenta", method = RequestMethod.POST, consumes = "application/json")
    public void crearNuevaCuenta(@RequestBody CuentaUsuario nuevaCuenta){
        log.info("He recibido cuenta");
        log.info(nuevaCuenta.getPassword());
        log.info(nuevaCuenta.toString());

        if(!GestionCuenta.crearCuenta(nuevaCuenta)) {
            throw new DuplicateKeyException("Nombre en uso");
        }
        log.info("Cuenta creada");
    }
}
