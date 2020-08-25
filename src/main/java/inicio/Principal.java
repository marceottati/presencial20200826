package inicio;

import vista.VisLogin;
import controlador.ContLogin;
import java.sql.Connection;
import modelo.Persona;
import modelo.Funcionalidad;

public class Principal {

    public static void main(String[] args) {
        try {
            VisLogin login = new VisLogin();
            Persona persona = new Persona();
            Funcionalidad funcionalidad = new Funcionalidad();
            Connection conexion = Conexion.getConnection();
            ContLogin contLogin = new ContLogin(login, persona, funcionalidad, conexion);
            contLogin.mostrarLogin();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

}
