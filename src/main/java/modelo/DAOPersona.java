/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import inicio.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import inicio.*;



/**
 *
 * @author tecnico
 */
public class DAOPersona {
     
    private static final String PERSONASXID = "SELECT * PERSONAS WHERE ID_PERSONA=?";
    private static final String PERSONASXDOCUMENTO = "SELECT * PERSONAS WHERE DOCUMENTO=?";
    private static final String INSERT_PERSONAS = "INSERT INTO PERSONAS (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2, FECHA_NAC, CLAVE, ID_ROL, MAIL) VALUES (PERSONAS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String BUSCAR_PERSONA= "SELECT * FROM PERSONA WHERE APELLIDO1=? AND NOMBRE1=?";;
    
    public static boolean insert(Persona p){
        boolean resultado = false;
        
        try{
            PreparedStatement st = Conexion.getConnection().prepareStatement(INSERT_PERSONAS);
            
            st.setString(1, p.getDocumento());
            st.setString(1, p.getApellido1());
            st.setString(1, p.getApellido2());
            st.setString(1, p.getNombre1());
            st.setString(1, p.getNombre2());
            st.setDate(1, p.getFechaNac());
            st.setString(1, p.getClave());
            st.setInt(1, p.getRolNombre().getId());
            st.setString(1, p.getEmail());
            st.setString(1, p.getDocumento());
            
            int nro = st.executeUpdate();
            
            if(nro>0){
                resultado=true;
            }else{
                resultado=false;
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
        
        return resultado;
        
    }
    
    /**
     * Función personaXID, dado un ID se devuelve la Persona
     * @param id
     * @return Persona
     */
    public Persona findPersonaXID(int id){
        
        try{
            Persona p = new Persona();
            PreparedStatement st = Conexion.getConnection().prepareStatement(PERSONASXID);
			
			st.setInt(1,  id);
			
			ResultSet resultado = st.executeQuery();			
				
			while(resultado.next()) {
				p.setId(resultado.getInt("ID_PERSONA"));
				p.setDocumento(resultado.getString("DOCUMENTO"));
				p.setNombre1(resultado.getString("NOMBRE1"));
				p.setNombre2(resultado.getString("NOMBRE2"));
				p.setApellido1(resultado.getString("APELLIDO1"));
				p.setApellido2(resultado.getString("APELLIDO2"));
                                p.setEmail(resultado.getString("EMAIL"));
                                p.setFechaNac(resultado.getDate("FECHA_NAC"));
			}
            return p;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * findPersonaXDocumento dadp un documento retorna una persona
     * @param documento
     * @return 
     */
    public Persona findPersonaXDocumento(String documento){
        try{
            Persona p = new Persona();
              PreparedStatement st = Conexion.getConnection().prepareStatement(PERSONASXDOCUMENTO);
			
			st.setString(1,  documento);
			
			ResultSet resultado = st.executeQuery();			
				
			while(resultado.next()) {
				p.setId(resultado.getInt("ID_PERSONA"));
				p.setDocumento(resultado.getString("DOCUMENTO"));
				p.setNombre1(resultado.getString("NOMBRE1"));
				p.setNombre2(resultado.getString("NOMBRE2"));
				p.setApellido1(resultado.getString("APELLIDO1"));
				p.setApellido2(resultado.getString("APELLIDO2"));
                                p.setEmail(resultado.getString("EMAIL"));
                                p.setFechaNac(resultado.getDate("FECHA_NAC"));
			}
            
            return p;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    public Persona findXApellido1Nombre1(String apellido1, String nombre1){    
    
        try{
            Persona p = new Persona();        
             PreparedStatement st = Conexion.getConnection().prepareStatement(BUSCAR_PERSONA);
			
			st.setString(1,  apellido1);
                        st.setString(2,  nombre1);
			
			ResultSet resultado = st.executeQuery();			
				
			while(resultado.next()) {
				p.setId(resultado.getInt("ID_PERSONA"));
				p.setDocumento(resultado.getString("DOCUMENTO"));
				p.setNombre1(resultado.getString("NOMBRE1"));
				p.setNombre2(resultado.getString("NOMBRE2"));
				p.setApellido1(resultado.getString("APELLIDO1"));
				p.setApellido2(resultado.getString("APELLIDO2"));
                                p.setEmail(resultado.getString("EMAIL"));
                                p.setFechaNac(resultado.getDate("FECHA_NAC"));
			}
            
                        return p;
                        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
         
    }
    
      /**
     * Obtener persona desde base de datos
     *
     * @param email
     * @param pass
     * @return Persona en caso que se encuentre
     */
    public LinkedList<Persona> obtenerDesdeBD(String email, String pass) throws Exception {
        Connection conexion = Conexion.getConnection();
        System.out.println("coso ----------------------");
        try {
            System.out.println("cosito ----------------------");
            PreparedStatement miConsulta = conexion.prepareStatement(
                    "SELECT p.*,r.ID_ROL ID_ROL, r.NOMBRE ROL_NOMBRE, r.DESCRIPCION ROL_DESCRIPCION "
                            + "FROM PERSONAS p INNER JOIN ROLES r ON p.ID_ROL = r.ID_ROL "
                            + "WHERE p.MAIL = ? AND p.CLAVE = ?"
            );
            miConsulta.setString(1, email);
            miConsulta.setString(2, pass);
            ResultSet personasRS = miConsulta.executeQuery();
            
            LinkedList<Persona> personas = new LinkedList<Persona>();
            
            if (personasRS != null) {
                while (personasRS.next()) {
                    
                    Persona p = new Persona();
                    p.setDocumento(personasRS.getString("DOCUMENTO"));
                    p.setApellido1(personasRS.getString("APELLIDO1"));
                    p.setApellido2(personasRS.getString("APELLIDO2"));
                    p.setNombre1(personasRS.getString("NOMBRE1"));
                    p.setNombre2(personasRS.getString("NOMBRE2"));
                    p.setFechaNac(personasRS.getDate("FECHA_NAC"));
                    p.setClave(personasRS.getString("CLAVE"));
                    p.setEmail(personasRS.getString("MAIL"));
                    
                    Rol r = new Rol();
                    r.setId(personasRS.getInt("ID_ROL"));
                    r.setNombre(personasRS.getString("ROL_NOMBRE"));
                    
                    p.setRolNombre(r);
                    
     //Eliminado               this.rolDescripcion = personasRS.getString("ROL_DESCRIPCION");
                    
                    personas.add(p);
                }
                
            } else {
                
                System.out.println("ES NULO ----------------------");
            }
            return personas;
            
        } catch (SQLException e) {
            System.out.println("cosa ----------------------");
            throw new Exception(e.getMessage() + " > " + e.getStackTrace());
        }
              
    }
}
