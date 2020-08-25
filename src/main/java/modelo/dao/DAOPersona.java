/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import inicio.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import inicio.*;
import modelo.Persona;
import modelo.Rol;

/**
 *
 * @author tecnico
 */
public class DAOPersona {

    private static final String QUERY = "p.*, r.ID ROL_ID, r.NOMBRE ROL_NOMBRE, r.DESCRIPCION ROL_DESCRIPCION FROM PERSONAS p INNER JOIN ROLES r ON p.ROL_ID = r.id";

    private static final String PERSONASXID = "SELECT " + QUERY + " WHERE p.ID = ?";
    private static final String PERSONASXDOCUMENTO = "SELECT * PERSONAS WHERE DOCUMENTO=?";
    private static final String INSERT_PERSONAS = "INSERT INTO PERSONAS (DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2, FECHA_NAC, PASS, ROL_ID, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String BUSCAR_PERSONA = "SELECT " + QUERY + " WHERE APELLIDO1=? AND NOMBRE1=?";
    private static final String LOGIN = "SELECT " + QUERY + " WHERE p.EMAIL = ? AND p.PASS = ?";

    public static boolean insert(Persona p) {
        try {
            PreparedStatement st = Conexion.getConnection().prepareStatement(INSERT_PERSONAS);

            st.setString(1, p.getDocumento());
            st.setString(2, p.getApellido1());
            st.setString(3, p.getApellido2());
            st.setString(4, p.getNombre1());
            st.setString(5, p.getNombre2());
            st.setDate(6, p.getFechaNac());
            st.setString(7, p.getPass());
            st.setInt(8, p.getRol().getId());
            st.setString(9, p.getEmail());

            int nro = st.executeUpdate();

            return (nro > 0) ? true : false;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Función personaXID, dado un ID se devuelve la Persona
     *
     * @param id
     * @return Persona
     */
//    public Persona findPersonaXID(int id) {
//        try {
//            Persona p = new Persona();
//
//            PreparedStatement st = Conexion.getConnection().prepareStatement(PERSONASXID);
//            st.setInt(1, id);
//            ResultSet resultado = st.executeQuery();
//
//            while (resultado.next()) {
//                p.setId(resultado.getInt("ID_PERSONA"));
//                p.setDocumento(resultado.getString("DOCUMENTO"));
//                p.setApellido1(resultado.getString("APELLIDO1"));
//                p.setApellido2(resultado.getString("APELLIDO2"));
//                p.setNombre1(resultado.getString("NOMBRE1"));
//                p.setNombre2(resultado.getString("NOMBRE2"));
//                p.setEmail(resultado.getString("EMAIL"));
//                p.setFechaNac(resultado.getDate("FECHA_NAC"));
//            }
//            return p;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//    }
    /**
     * findPersonaXDocumento dadp un documento retorna una persona
     *
     * @param documento
     * @return
     */
//    public Persona findPersonaXDocumento(String documento) {
//        try {
//            Persona p = new Persona();
//            PreparedStatement st = Conexion.getConnection().prepareStatement(PERSONASXDOCUMENTO);
//
//            st.setString(1, documento);
//
//            ResultSet resultado = st.executeQuery();
//
//            while (resultado.next()) {
//                p.setId(resultado.getInt("ID_PERSONA"));
//                p.setDocumento(resultado.getString("DOCUMENTO"));
//                p.setNombre1(resultado.getString("NOMBRE1"));
//                p.setNombre2(resultado.getString("NOMBRE2"));
//                p.setApellido1(resultado.getString("APELLIDO1"));
//                p.setApellido2(resultado.getString("APELLIDO2"));
//                p.setEmail(resultado.getString("EMAIL"));
//                p.setFechaNac(resultado.getDate("FECHA_NAC"));
//            }
//
//            return p;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//    }
//    public Persona findXApellido1Nombre1(String apellido1, String nombre1) {
//
//        try {
//            Persona p = new Persona();
//            PreparedStatement st = Conexion.getConnection().prepareStatement(BUSCAR_PERSONA);
//
//            st.setString(1, apellido1);
//            st.setString(2, nombre1);
//
//            ResultSet resultado = st.executeQuery();
//
//            while (resultado.next()) {
//                p.setId(resultado.getInt("ID_PERSONA"));
//                p.setDocumento(resultado.getString("DOCUMENTO"));
//                p.setNombre1(resultado.getString("NOMBRE1"));
//                p.setNombre2(resultado.getString("NOMBRE2"));
//                p.setApellido1(resultado.getString("APELLIDO1"));
//                p.setApellido2(resultado.getString("APELLIDO2"));
//                p.setEmail(resultado.getString("EMAIL"));
//                p.setFechaNac(resultado.getDate("FECHA_NAC"));
//            }
//
//            return p;
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//
//    }
    /**
     * Obtener persona desde base de datos
     *
     * @param email
     * @param pass
     * @return Persona en caso que se encuentre
     */
    public Persona obtenerDesdeBD(String email, String pass) throws Exception {
        try {
            PreparedStatement obtener = Conexion.getConnection().prepareStatement(LOGIN);
            obtener.setString(1, email);
            obtener.setString(2, pass);
            ResultSet personasRS = obtener.executeQuery();

            if (personasRS != null) {
                while (personasRS.next()) {
                    Persona p = new Persona();
                    p.setId(personasRS.getInt("ID"));
                    p.setDocumento(personasRS.getString("DOCUMENTO"));
                    p.setApellido1(personasRS.getString("APELLIDO1"));
                    p.setApellido2(personasRS.getString("APELLIDO2"));
                    p.setNombre1(personasRS.getString("NOMBRE1"));
                    p.setNombre2(personasRS.getString("NOMBRE2"));
                    p.setFechaNac(personasRS.getDate("FECHA_NAC"));
                    p.setPass(personasRS.getString("PASS"));
                    p.setEmail(personasRS.getString("EMAIL"));

                    Rol r = new Rol();
                    r.setId(personasRS.getInt("ROL_ID"));
                    r.setNombre(personasRS.getString("ROL_NOMBRE"));
                    r.setDescripcion(personasRS.getString("ROL_DESCRIPCION"));
                    p.setRol(r);

                    return p;
                }
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage() + " > " + e.getStackTrace());
        }
        Persona p = new Persona();
        return p;
    }
}
