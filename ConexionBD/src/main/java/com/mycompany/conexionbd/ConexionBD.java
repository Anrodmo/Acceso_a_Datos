

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
            
        
        Videojuego.muestraConsulta(query);
        System.out.println("Existe el juego wow ? ->"+Videojuego.buscaNombre("wow"));
        System.out.println("Existe el juego CoD ? ->"+Videojuego.buscaNombre("CoD"));
        System.out.println("Existe el juego null ? ->"+Videojuego.buscaNombre(null));
        
        System.out.println("registro con exito ? "+        
            Videojuego.nuevoRegistro("wow4", null, null, null, null)  );
        System.out.println("");
        Videojuego.muestraConsulta(query);
        System.out.println("Borrado con éxito wow4 ? :  "+Videojuego.eliminarRegistro("wow4"));
        
        Videojuego.muestraConsulta(query);
        
        Videojuego juego = new Videojuego();
        System.out.println("Añadido manual con éxito ? :" +juego.nuevoRegistro());
        Videojuego.muestraConsulta(query);
            
            
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
