package redsocial.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import redsocial.dominio.Encuesta;
import redsocial.dominio.Voto;
import redsocial.servicio.GestionCuenta;
import redsocial.servicio.GestionPerfil;
import redsocial.servicio.GestionVoto;
import redsocial.usuario.CuentaUsuario;
import redsocial.usuario.PerfilUsuario;
import redsocial.servicio.GestionEncuesta;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;


@RestController
public class EncuestaController {

    @Autowired
    private GestionEncuesta gestionEncuesta;

    @Autowired
    private GestionVoto gestionVoto;

    @Autowired
    private GestionCuenta gestionCuenta;

    private static final Logger log = LoggerFactory.getLogger(EncuestaController.class);

    @RequestMapping(value = "/crearEncuesta", method = RequestMethod.POST, consumes = "application/json")
    public void crearNuevaCuenta(@RequestBody PlantillaEncuesta nuevaPlantilla){
        log.info("He recibido encuesta");
        log.info(nuevaPlantilla.pregunta);
        log.info(nuevaPlantilla.respuestas.get(0));

        CuentaUsuario cuentaAutor = gestionCuenta.obtenerCuenta(nuevaPlantilla.autor);
        PerfilUsuario autorEncuesta  =  cuentaAutor.getPerfilUsuario();
        Encuesta nuevaEncuesta = new Encuesta(autorEncuesta,nuevaPlantilla.pregunta,nuevaPlantilla.respuestas);

        log.info(nuevaEncuesta.getPregunta());

        gestionEncuesta.crearEncuesta(cuentaAutor, nuevaEncuesta);
    }

    @RequestMapping(value="/encuesta", method = RequestMethod.GET)
    public InfoEncuesta obtenerEncuesta(@RequestParam String nombreUsuario,@RequestParam(required = false) String pregunta,
                                        HttpServletResponse response) {
        List<Encuesta> resultado = gestionEncuesta.buscarEncuestasPorPreguntaAproximada(pregunta);
        if (!resultado.isEmpty()) {
            Encuesta e = resultado.get(0);
            Pair<Boolean,SortedMap<Integer,Integer>> votosObtenidos = gestionVoto.obtenerVotosporRespuesta(e,nombreUsuario);
            Map votos= votosObtenidos.getSecond();
            Boolean votada = votosObtenidos.getFirst();
            log.info(votada.toString());
            return new InfoEncuesta(e,votos,votada);
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
    
    @RequestMapping(value="/buscarEncuesta", method = RequestMethod.GET)
    public List<InfoEncuesta> buscarEncuesta(@RequestParam String nombreUsuario,
    										 @RequestParam String pregunta, HttpServletResponse response) {
        log.info("He recibido la busqueda");
        List<Encuesta> resultado = gestionEncuesta.buscarEncuestasPorPreguntaAproximada(pregunta);
        if (!resultado.isEmpty()) {
        	List<InfoEncuesta> respuesta = new ArrayList<InfoEncuesta>();
        	Pair<Boolean,SortedMap<Integer,Integer>> votosObtenidos;
        	Map votos;
        	Boolean votada;
            for (Encuesta e : resultado) {
            	votosObtenidos = gestionVoto.obtenerVotosporRespuesta(e,nombreUsuario);
                votos = votosObtenidos.getSecond();
                votada = votosObtenidos.getFirst();
                if (!votada) {
                	respuesta.add(new InfoEncuesta(e,votos,votada));
                }
            }
            return respuesta;
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @RequestMapping(value = "/encuesta", method = RequestMethod.PUT)
    public void votarRespuesta(@RequestParam String nombreCuenta, @RequestParam String nombreEncuesta,
                                         @RequestParam int nRespuesta){
        CuentaUsuario c =gestionCuenta.obtenerCuenta(nombreCuenta);
        PerfilUsuario p = c.getPerfilUsuario();
        Encuesta e = gestionEncuesta.buscarEncuestaPorPregunta(nombreEncuesta);
        Voto v = new Voto(p,e,nRespuesta);
        gestionVoto.crearVoto(c,v);
    }
}
