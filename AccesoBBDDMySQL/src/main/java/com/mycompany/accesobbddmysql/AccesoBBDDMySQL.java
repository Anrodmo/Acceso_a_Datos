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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anrod
 */
public class AccesoBBDDMySQL {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/videojuegos";
    static final String USER = "angel33";
    static final String PASS = "angel33";
    
      

    public static void main(String[] args) {
        
        String query = "SELECT * FROM videojuego";
        
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);) {
            
            Statement argumento = miConexion.createStatement();
            ResultSet resultado = argumento.executeQuery(query);
            
            while (resultado.next()){
                System.out.print("EL id es : "+resultado.getInt("id"));
                System.out.print(", EL nombre es : "+resultado.getString("nombre"));
                System.out.print(", EL genero es : "+resultado.getString("genero"));
                System.out.print(", la fecha de lanzamiento es : "+resultado.getDate("fecha_lanzamiento"));
                System.out.print(", La compañia es : "+resultado.getString("compañia"));
                System.out.println(", EL precio es : "+resultado.getFloat("precio"));
                
            }
//            query = "INSERT INTO videojuego (nombre,genero,fecha_lanzamiento,compañia,precio)" 
//                    + "VALUES('wow classic','MMORPG','2012-02-25', 'Blizzaard',34.95)";
//            argumento.executeUpdate(query);
            
            query ="DELETE FROM videojuego WHERE nombre = 'wow'";
            argumento.executeUpdate(query);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        System.out.println("Hello World!");
        
        //Connection miConexion
    }
}
