package redsocial.dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Catalogo implements Iterable<Encuesta> {
    private List<Encuesta> listaEncuestas = new ArrayList<Encuesta>();

    public Catalogo(List<Encuesta> listaEncuestas){
        this.listaEncuestas = listaEncuestas;
    }
    @Override
    public Iterator<Encuesta> iterator() {
        return listaEncuestas.iterator();
    }

    public static Catalogo ObtenerEncuestasUsuario(String usuario){
        List<Encuesta> resultado= new ArrayList<Encuesta>();
        //TODO:Obtener resultado de la base de datos
        return new Catalogo(resultado);
    }

    public static Catalogo ObtenerEncuestas(int tamaño){
        List<Encuesta> resultado= new ArrayList<Encuesta>();
        //TODO:Obtener de la BD una lista aleatoria de tamaño "tamaño"
        return new Catalogo(resultado);
    }

}
