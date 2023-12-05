

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
    
    /*
        Este proyecyo se quedó muerto en este punto.
        Esta forma de realizar el ejercicio me parecio deficiente, por el uso 
        de Statement que no ofrece seguridad y por no usar una clase y métodos
        para gestionas las operaciones.
        Lo rehice en el proyecto ConexionBD y luego añadí las operaciones que faltan
        utilizando métodos de la clase y Prepared Statements.       
        */
         

    public static void main(String[] args) {
        
        String query = "SELECT * FROM videojuegos";
        
        
        // try con recursos para que cierre solo la conexion
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);) {
            
            // creo un statement para la conexion
            Statement argumento = miConexion.createStatement();
            // realizo la consulta  con el query definido arriba (select *) y recojo en Resulset
            ResultSet resultado = argumento.executeQuery(query);
            
            // muestro una a una las lineas del resultado
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
    
            // Genero un nuevo query de insert
            query = "INSERT INTO videojuegos (Nombre,Genero,FechaLanzamiento,Compañia,Precio)" 
                    + "VALUES('wow ','MMORPG','2004-02-25', 'Blizzard',34.95)";
            // lanzo el argumento con la nueva query, en este caso con executeUpdate
            argumento.executeUpdate(query);
            
            // ahora genero un query de delete
            query ="DELETE FROM videojuegos WHERE nombre = 'wow'";
            // lanzo el argumento con la nueva query, en este caso con executeUpdate
            argumento.executeUpdate(query);
            
            // cierro el Sttement, auqnue es redundante pues al cerrarse la conexion tammbien lo cierra
            argumento.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();           
        }
      
        
    }
}
