package redsocial.spring;

import redsocial.usuario.PerfilUsuario;

import java.util.Date;

public class PlantillaPerfil extends PerfilUsuario {
    private String nombreCuenta;

    public PlantillaPerfil() {
        super();
    }

    public PlantillaPerfil(String nombre, Date nacimiento, String pais, String nombreCuenta){
        super(nombre,nacimiento,pais);
        this.nombreCuenta=nombreCuenta;
    }

    public PlantillaPerfil(String nombre, Date nacimiento, String pais,String sexo,String nombreCuenta){
        super(nombre,nacimiento,pais,sexo);
        this.nombreCuenta=nombreCuenta;
    }

    PerfilUsuario extraerPerfil(){
        if(getSexo()==null){
            return new PerfilUsuario(getNombre(),getNacimiento(),getPais());
        }else{
            return new PerfilUsuario(getNombre(),getNacimiento(),getPais(),getSexo());
        }
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }
}
