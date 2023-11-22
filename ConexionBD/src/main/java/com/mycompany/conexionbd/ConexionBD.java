

package com.mycompany.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
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
        
        System.out.println("registro con exito ? "+
        
        Videojuego.nuevoRegistro("wow4", null, fecha, null, 69.99f)
        
        );
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    static private boolean nuevoRegistro(){
        boolean correcto = false;
        Scanner teclado = new Scanner (System.in);
        float precio;
        Videojuego miJuego = new Videojuego();
        
        LocalDate fecha = null;
        
        System.out.println("-----------------------------------------------");
        System.out.println("introduzca los datos de un nuevo juego.");
        
        miJuego.setNombre(pedirNombre());
        
        miJuego.setGenero(pedirDatoString("Genero"));
        
        miJuego.setFechaLanzamiento(pedirFechaPublicacion());
        
        miJuego.setGenero(pedirDatoString("Compañia"));
        
        miJuego.setPrecio(pedirPrecio());
        
        
        return correcto = false;
    }
    
    static private String pedirNombre (){
        String  nombre;
        Scanner teclado = new Scanner(System.in);
        
        do{           
            System.out.print("Nombre del juego ->");
            nombre=teclado.nextLine();
            nombre = nombre.trim();
            if(nombre.length() == 0)
                System.out.println("El nombre es obligatorio");                     
        }while (nombre.length() == 0);
        return nombre;
    }
    
    
    static private String pedirDatoString (String dato){
        String valor;
        Scanner teclado = new Scanner(System.in);
        
        System.out.print(dato+" -> ");
        valor=teclado.nextLine();
        valor = valor.trim();
        
        return valor;
    }
    
    static private LocalDate pedirFechaPublicacion(){
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha= null;
        int año=0,mes=0,dia;
        do{
            teclado.next();
            System.out.println("Ahota vamos a introducir la fecha, pulse intro para "
                    + "si no desea introducirla.");
            System.out.print("Año -> "); 
            
            try{
                año = teclado.nextInt();
                if(año<1980 || año >=  LocalDate.now().getYear()){
                    System.out.println("El año "+año+" no es válido");
                    año=0;               
                }           
            }catch (InputMismatchException ex){
                System.out.println("Error, debe introducir un año o 'intro' para saltar fecha");
                año=0;
            }
            
            // como lo quiero meter en un LocalDate lo compruebo minimamente
        }while (año != 0 && ( año<1980 || año >=  LocalDate.now().getYear()) );
         
        if(año !=0){
            do{
                System.out.print("Mes (1-12) -> ");
                mes = teclado.nextInt();
                if (mes<0 || mes >12)
                    System.out.println("El mes nº "+mes+" no es válido.");                
            }while (mes<0 || mes >12);            
        }
        
        if(año !=0){
            boolean fechaCorrecta = true;
            do{                   
                System.out.println("Día (1-29,30 o 32) -> ");
                dia = teclado.nextInt();
                //DateTimeFormatter miFormto = DateTimeFormatter.ofPattern("yyy-MM-dd");
                try{
                fecha = LocalDate.of(año,mes,dia);
                
                }catch (DateTimeException ex){
                    fechaCorrecta = false;
                    System.out.println("El día "+dia+" no es válido para el año "+año+" y mes "+mes);
                }                               
            }while(!fechaCorrecta);
        }        
        return fecha;                  
    }
    
    static private Float pedirPrecio(){
        Float precio= null;
        Scanner teclado = new Scanner(System.in);
        boolean floatCorrecto = true;
        do{
            //teclado.next();
            System.out.print("Precio -> ");
            try{
                precio = teclado.nextFloat();
                precio = Math.round(precio*100)/100f;
            }catch (InputMismatchException ex){
                floatCorrecto= false;
                System.out.println("Precio incorrecto");
            }           
        }while (!floatCorrecto);                        
        return precio;
    }
    
    
    
    static private boolean eliminarRegistro (String nombre){
        boolean correcto = false;
        
//        String query = "DELETE FROM videojuegos WHERE Nombre = '"+nombre+"'";
        String query = "DELETE FROM videojuegos WHERE Nombre = ?";
         
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);
                PreparedStatement argumento = miConexion.prepareCall(query);) {
            argumento.setString(1, nombre);
            correcto = argumento.executeUpdate(query)>0;            
        }catch (SQLException ex) {
            ex.printStackTrace();            
        }              
        return correcto;
    }
    
    
    
    
    
}
