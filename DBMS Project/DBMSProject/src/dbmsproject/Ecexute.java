/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmsproject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author aastha
 * 
 */

public class Ecexute {
    public static void main(String[] args) throws SQLException{
        Connection con=null;
        CallableStatement cstmt=null;
        ResultSet rs= null;
        
        String search_name="{call Search_Patient(?,?)}";
        try{
            con = getOracleDBConnection();
            cstmt = con.prepareCall(search_name);
            cstmt.setString(1, "P1");
           cstmt.registerOutParameter(2, OracleTypes.CURSOR);
           cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
                        
             while (rs.next()) { 
                 
                System.out.println("Patient id: " +  rs.getString("P_ID"));
                System.out.println("Patient name: " +  rs.getString("P_Name"));
                //System.out.println("Passenger age: " +  rs.getString("p_age"));
                //System.out.println("Passenger gender: " +  rs.getString("gender"));
                //System.out.println("Passenger contact_no: " +  rs.getString("p_contact_no"));
             }
        }
     catch(SQLException e){
         e.printStackTrace();
     }finally{
            if(cstmt!=null){
                cstmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
        
        
        
    }
      private static Connection getOracleDBConnection(){
        
        //Here TEST is oracle database name.
        String ORACLE_DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE"; 
        String ORACLE_DB_USER = "yugamsinh";
        String ORACLE_DB_PASSWORD = "yugamsinh";            
                    
                         
                        
        Connection oracleDbConnection = null;    
                 
        try {
            oracleDbConnection = DriverManager.getConnection
                        (ORACLE_DB_CONNECTION, ORACLE_DB_USER,ORACLE_DB_PASSWORD);
            return oracleDbConnection;    
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return oracleDbConnection;
    }  
}

