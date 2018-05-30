package redsocial.spring;

import redsocial.usuario.PerfilUsuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InfoPerfil {
    private String nombreCuenta;
    private String nombre;
    private String pais;
    private String nacimiento;
    private String sexo;
    private Long id;

    public InfoPerfil(){}


    public InfoPerfil(String nombre, String pais, String nacimiento, String sexo) {
        this.nombre = nombre;
        this.pais = pais;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
    }

    public InfoPerfil(String nombre, String pais, String nacimiento, String sexo,String nombreCuenta) {
        this.nombre = nombre;
        this.pais = pais;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
        this.nombreCuenta=nombreCuenta;
    }

    public InfoPerfil(PerfilUsuario perfilUsuario){
        this.nombre=perfilUsuario.getNombre();
        this.pais=perfilUsuario.getPais();
        this.sexo=perfilUsuario.getSexo();
        DateFormat df = new SimpleDateFormat("DD-MM-YYYY");
        this.nacimiento=df.format(perfilUsuario.getNacimiento());
        this.id=perfilUsuario.getId();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public Long getId() {
        return id;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }
}
