package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VisLogin;
import modelo.Persona;
import modelo.Funcionalidad;
import java.sql.Connection;

public class ContLogin implements ActionListener{

    private VisLogin vistaLogin;
    private Persona persona;
    private Funcionalidad funcionalidad;
    private Connection conexion;

    public ContLogin(VisLogin vistaLogin, Persona persona, Funcionalidad funcionalidad, Connection conexion) {
        this.vistaLogin = vistaLogin;
        this.persona = persona;
        this.funcionalidad = funcionalidad;
        this.conexion = conexion;
    }

    public void mostrarLogin() {
        this.vistaLogin.setVisible(true);
    }
    
    public void actionPerformed (ActionEvent e){
        try {
            this.persona.setEmail("xxxxx");
            this.persona.setPass("xxxxxxxxx");
            this.persona.obtenerDesdeBD();
        } catch (Exception xe) {
            System.out.println(xe.getMessage());
        }
    }

}
