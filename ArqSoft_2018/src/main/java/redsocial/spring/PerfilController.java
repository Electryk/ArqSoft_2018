package redsocial.spring;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import redsocial.servicio.GestionCuenta;
import redsocial.servicio.GestionEncuesta;
import redsocial.servicio.GestionPerfil;
import redsocial.servicio.GestionVoto;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PerfilController {

    @Autowired
    private GestionPerfil gestionPerfil;

    @Autowired
    private GestionCuenta gestionCuenta;

    @Autowired
    private GestionEncuesta gestionEncuesta;

    @Autowired
    private GestionVoto gestionVoto;

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
    public InfoPerfil obtenerPerfil(@RequestParam(value="nombreCuenta") String nombreCuenta, HttpServletResponse response){

        log.info("Peticion de obtencion de perfil recibida");
        try {
            PerfilUsuario res = gestionCuenta.obtenerCuenta(nombreCuenta).getPerfilUsuario();
            return new InfoPerfil(res);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
    @RequestMapping(value = "/perfilUsuario/{id}", method = RequestMethod.GET)
    public InfoPerfil obtenerPerfilDe(@PathVariable("id") String id, HttpServletResponse response){
        try {
            PerfilUsuario perifl = gestionPerfil.obtenerPerfil(Long.parseLong(id));
            return new InfoPerfil(perifl);

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }


    @RequestMapping(value = "/buscarPerfilUsuario", method = RequestMethod.GET)
    public List<InfoPerfil> buscarPerfil(@RequestParam(value="busqueda") String busqueda, HttpServletResponse response){

        log.info("Peticion de obtencion de perfil recibida");
        try {
            List<PerfilUsuario> res = gestionPerfil.buscarPerfilNombre(busqueda);
            List<InfoPerfil> resInfo= new ArrayList<>();
            for(PerfilUsuario p :res){
                resInfo.add(new InfoPerfil(p));
            }
            return resInfo;
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @RequestMapping(value = "/perfilUsuario/{id}", method = RequestMethod.PUT)
    public void actualizarPerfil(@RequestBody InfoPerfil perfil,@PathVariable String id,
                                 HttpServletResponse response){
        gestionPerfil.modificarPerfil(Long.parseLong(id),perfil,gestionCuenta.obtenerCuenta(perfil.getNombreCuenta()));
    }
}
