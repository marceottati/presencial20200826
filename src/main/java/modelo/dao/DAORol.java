/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dao;

import inicio.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author tecnico
 */
public class DAORol {
    /**
     * Variables con sentencias precargadas
     */
    	private static final String ALL_ROLES = "SELECT * FROM PERSONA ORDER BY ID_PERSONA";
	
	private static final String INSERT_ROL = "INSERT INTO ROLES (ID_ROL, NOMBRE, DESCRIPCION) VALUES (ROLES_SEQ.NEXTVAL, ?, ?)";
	
	private static final String UPDATE_ROL = "UPDATE PERSONA SET NOMBRE=?, DESCRIPCIOM=?  WHERE ID_ROL=?";
	
	private static final String DELETE_ROL = "DELETE FROM ROLES WHERE ID=?";
	
	private static final String SELECCIONAR_ROL_BY_ID = "SELECT * FROM ROLES WHERE ID_ROL=?";
    
        /**
         * Función findRol, dado un ID devuelve un rol
         * @param id
         * @return Rol
         */
        public Rol findRol(int id){
            
            try{
                Rol r = new Rol();        
                PreparedStatement st = Conexion.getConnection().prepareStatement(SELECCIONAR_ROL_BY_ID);
			
			st.setInt(1,  id);
                        
			
			ResultSet resultado = st.executeQuery();			
				
			while(resultado.next()) {
				r.setId(resultado.getInt("ID_ROL"));
				r.setNombre(resultado.getString("NOMBRE"));
				r.setDescripcion(resultado.getString("DESCRIPCION"));
			}
            
                        return r;
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                return null;
            }
        }
        
        /**
         * Función insertRol, registra un Rol retornando True si el mismo se registró
         * @param r
         * @return boolean
         */
        public boolean insertRol(Rol r){
            try{
                boolean resultado=false;
                PreparedStatement st = Conexion.getConnection().prepareStatement(INSERT_ROL);
                
                st.setString(1, r.getNombre());
                st.setString(2, r.getDescripcion());
                
                int ret = st.executeUpdate();
                
                if(ret > 0){
                    resultado = true;
                }else{
                    resultado = false;
                }                
                
                return resultado;
                
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        
        /**
         * Función updateRol recibe un rol y se actualiza referenciado por el ID del mismo. 
         * @param r
         * @return boolean
         */
        public boolean updateRol(Rol r){
            try{
                boolean resultado=false;
                PreparedStatement st = Conexion.getConnection().prepareStatement(UPDATE_ROL);
                
                
                st.setString(1, r.getNombre());
                st.setString(2, r.getDescripcion());
                st.setInt(3, r.getId());
                
                int ret = st.executeUpdate();
                
                if(ret > 0){
                    resultado = true;
                }else{
                    resultado = false;
                }                
                
                return resultado;
                
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
}
