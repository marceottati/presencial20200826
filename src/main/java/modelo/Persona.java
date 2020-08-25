package modelo;

import inicio.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

public class Persona extends Observable {

    private int id;
    private String documento;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private Date fechaNac;
    private String pass;
    private String email;
    private Rol rol;

    public Persona() {
    }

    public Persona(String documento, String apellido1, String apellido2, String nombre1, String nombre2, Date fechaNac,
            String pass, String email, Rol rol) {

        this.documento = documento;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.fechaNac = fechaNac;
        this.pass = pass;
        this.email = email;
        this.rol = rol;

    }

    /**
     * Obtener persona desde base de datos
     *
     * @return Persona en caso que se encuentre
     */
    public Persona obtenerDesdeBD() throws Exception {
        Connection conexion = Conexion.getConnection();
        try {
            System.out.println("cosito ----------------------");
            PreparedStatement miConsulta = conexion.prepareStatement(
                    "SELECT p.*, r.ID ROL_ID, r.NOMBRE ROL_NOMBRE, r.DESCRIPCION ROL_DESCRIPCION "
                            + "FROM PERSONAS p INNER JOIN ROLES r ON p.ROL_ID = r.ID "
                            + "WHERE p.EMAIL = ? AND p.CLAVE = ?"
            );
            miConsulta.setString(1, this.email);
            miConsulta.setString(2, this.pass);
            ResultSet personasRS = miConsulta.executeQuery();
            if (personasRS != null) {
                while (personasRS.next()) {
                    this.documento = personasRS.getString("DOCUMENTO");
                    this.apellido1 = personasRS.getString("APELLIDO1");
                    this.apellido2 = personasRS.getString("APELLIDO2");
                    this.nombre1 = personasRS.getString("NOMBRE1");
                    this.nombre2 = personasRS.getString("NOMBRE2");
                    this.fechaNac = personasRS.getDate("FECHA_NAC");
                    this.pass = personasRS.getString("PASS");
                    this.email = personasRS.getString("EMAIL");
                    Rol rol = new Rol(personasRS.getInt("ROL_ID"), personasRS.getString("ROL_NOMBRE"), personasRS.getString("ROL_DESCRIPCION"));
                    this.rol = rol;
                }
                notifyObservers();
                return this;
            } else {                
                System.out.println("ES NULO ----------------------");
            }
            return new Persona();
        } catch (SQLException e) {
            throw new Exception(e.getMessage() + " > " + e.getStackTrace());
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Persona{" + "documento=" + documento + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", fechaNac=" + fechaNac + ", pass=" + pass + ", email=" + email + ", rolNombre=" + rol.toString() + '}';
    }

}
