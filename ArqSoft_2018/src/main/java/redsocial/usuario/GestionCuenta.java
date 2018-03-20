package redsocial.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import redsocial.infraestructura.CuentaUsuarioRepo;

public class GestionCuenta {

    @Autowired
     static CuentaUsuarioRepo cuentaUsuarioRepo;

    public static void CrearCuenta(CuentaUsuario u){
        cuentaUsuarioRepo.save(u);
    }
}
