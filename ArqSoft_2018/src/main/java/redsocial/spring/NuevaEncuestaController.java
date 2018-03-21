package redsocial.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redsocial.dominio.Encuesta;
import redsocial.infraestructura.CuentaUsuarioRepo;
import redsocial.infraestructura.EncuestaRepo;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

import java.util.Date;


@RestController
public class NuevaEncuestaController {

    @Autowired
    private EncuestaRepo encuestaRepo;

    private static final Logger log = LoggerFactory.getLogger(NuevaEncuestaController.class);

    @RequestMapping(value = "/crearEncuesta", method = RequestMethod.POST, consumes = "application/json")
    public void crearNuevaCuenta(@RequestBody PlantillaEncuesta nuevaPlantilla){
        log.info("He recibido encuesta");
        log.info(nuevaPlantilla.pregunta);
        log.info(nuevaPlantilla.respuestas.get(0));

        PerfilUsuario defecto  = new PerfilUsuario("TestEncuesta",new Date(),"España");
        Encuesta nuevaEncuesta = new Encuesta(defecto,nuevaPlantilla.pregunta,nuevaPlantilla.respuestas);

        log.info(nuevaEncuesta.getPregunta());

        encuestaRepo.save(nuevaEncuesta);
    }
}
