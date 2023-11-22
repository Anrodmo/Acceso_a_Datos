/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.accesobbddmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author anrod
 */
public class AccesoBBDDMySQL {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "dam";
    static final String PASS = "dam";
    
      

    public static void main(String[] args) {
        
        String query = "SELECT * FROM videojuegos";
      
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);) {
            
            Statement argumento = miConexion.createStatement();
            ResultSet resultado = argumento.executeQuery(query);
            
            while (resultado.next()){
                System.out.print("EL id es : "+resultado.getInt("id"));
                System.out.print(", EL nombre es : "+resultado.getString("Nombre"));
                System.out.print(", EL genero es : "+resultado.getString("Genero"));
                System.out.print(", la fecha de lanzamiento es : "+resultado.getDate("FechaLanzamiento"));
                System.out.print(", La compañia es : "+resultado.getString("Compañia"));
                System.out.println(", EL precio es : "+resultado.getFloat("Precio"));
                
            }
//            query = "INSERT INTO videojuegos (Nombre,Genero,FechaLanzamiento,Compañia,Precio)" 
//                    + "VALUES('wow classic','MMORPG','2012-02-25', 'Blizzard',34.95)";
//            argumento.executeUpdate(query);

//            query = "INSERT INTO videojuegos (Nombre,Genero,FechaLanzamiento,Compañia,Precio)" 
//                    + "VALUES('wow ','MMORPG','2004-02-25', 'Blizzard',34.95)";
//            argumento.executeUpdate(query);

            query ="DELETE FROM videojuegos WHERE nombre = 'wow'";
            argumento.executeUpdate(query);
            argumento.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();           
        }
        
        
        
        
    }
}
