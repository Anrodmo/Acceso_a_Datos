

package com.mycompany.conexionbd;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ConexionBD {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "dam";  
    static final String PASS = "dam";  

    public static void main(String[] args) {
        String query = "SELECT * FROM videojuegos";
        LocalDate fecha = LocalDate.now();
            
        System.out.println("============== Método para mostrar consulta por parámetro de entrada ==============");
        System.out.println("               Mostramos una consulta *");        
        Videojuego.muestraConsulta(query);
        query = "SELECT Nombre,Genero FROM videojuegos";
        System.out.println("\n               Mostramos consulta Nombre, Genero");
        Videojuego.muestraConsulta(query);
        
        query = "SELECT * FROM videojuegos"; // para que las siguientes veces lo muestre todo
        
         System.out.println("\n\n==============Probamos consulta por titulo con tres valores ===================\n"
                 + " existente(en mi BBDD), no existente y nulo:");
        System.out.println("Existe el juego wow ? ->"+Videojuego.buscaNombre("wow"));
        System.out.println("Existe el juego CoD ? ->"+Videojuego.buscaNombre("CoD"));
        System.out.println("Existe el juego null ? ->"+Videojuego.buscaNombre(null));
       
        
        System.out.println("\n\n ===================== Probamos a insertar con función con parámetros de entrada"
                + "\n introducimos wow4,null,null,null,null  y  Diablo I,arpg,now,Blizzard,49.99");
        System.out.println("registro con exito wow4 ? "+        
        Videojuego.nuevoRegistro("wow4", null, null, null, null)  );
        System.out.println("registro con exito Diablo I ? "+        
        Videojuego.nuevoRegistro("Diablo I", "arpg", LocalDate.now(), "Blizzard", 49.99f)  );
        System.out.println("");
        System.out.println("=======================Mostramos para verlos instados:");
        Videojuego.muestraConsulta(query);
        
        System.out.println("\n\n ==================Probamos a borrar por título===============================");
        System.out.println("Borrado con éxito wow4 ? :  "+Videojuego.eliminarRegistro("wow4"));
        System.out.println("=======================Mostramos para ver que se ha borrado:");
        Videojuego.muestraConsulta(query);
        
        System.out.println("\n\n ===================Vamos a insertar pidiendole al usuario los datos ==========\n\n");
        Videojuego juego = new Videojuego();
        System.out.println("Añadido manual con éxito ? :" +juego.nuevoRegistro());
        System.out.println("=======================Mostramos para ver que se ha insertado:");
        Videojuego.muestraConsulta(query);
       
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
