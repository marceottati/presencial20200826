package inicio;

import vista.VisLogin;
import controlador.ContLogin;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.Persona;
import modelo.Funcionalidad;

public class Principal {

    public static void main(String[] args) {
        Persona persona = new Persona();
        VisLogin login = new VisLogin();
        Funcionalidad funcionalidad = new Funcionalidad();
        Connection conexion = Conexion.getConnection();
        ContLogin contLogin = new ContLogin(login, persona, funcionalidad, conexion);
        contLogin.mostrarLogin();
    }
    
}
