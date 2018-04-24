package redsocial.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redsocial.usuario.CuentaUsuarioRepo;

@RestController
public class PerfilController {

    @Autowired
    CuentaUsuarioRepo cuentaUsuarioRepo;

    @RequestMapping(path = "/perfilUsuario",method = RequestMethod.POST)
    public void crearPerfil(@RequestBody PlantillaPerfil nuevoPerfil){

    }
}
