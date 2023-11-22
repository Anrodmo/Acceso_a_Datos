
package com.mycompany.conexionbd;

import static com.mycompany.conexionbd.ConexionBD.DB_URL;
import static com.mycompany.conexionbd.ConexionBD.PASS;
import static com.mycompany.conexionbd.ConexionBD.USER;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class Videojuego {
    
    private String nombre;
    private String genero;
    private LocalDate fechaLanzamiento;
    private String compañia;
    Float precio;

    public Videojuego() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.length()>50)
            nombre = nombre.substring(0, 50);
        this.nombre = nombre;
    }

    public String getGenero() {       
        return genero;
    }

    public void setGenero(String genero) {
        if(genero.length()>25)
            genero = genero.substring(0, 25);
        this.genero = genero;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        precio = Math.round(precio*100)/100f;
        this.precio = precio;
    }
    
    /**
     * Método que informa si en la tabla videojuegos del esquema jcvd existe una
     * entrada non el Nombre facilitado
     * @param nombre String nombre del videojuego a buscar
     * @return True -> Si hay al menos un resultado, False -> en caso contrario.
     */
    static public boolean buscaNombre(String nombre){
        boolean existe= false;
        //String query = "SELECT Nombre FROM videojuegos WHERE Nombre = '"+nombre+"'";
        String query = "SELECT Nombre FROM videojuegos WHERE Nombre = ?";         
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);
                PreparedStatement argumento = miConexion.prepareStatement(query);) {
            argumento.setString(1, nombre);
            ResultSet resultado = argumento.executeQuery();
            existe=resultado.next();
        }catch (SQLException ex) {
            ex.printStackTrace();            
        }                 
        return existe;
    }
    
    /**
     * Mñetodo  que lanza la consulta introducida y la muestra por pantalla.
     * @param consulta
     */
    static public void muestraConsulta(String consulta){
        
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement argumento = miConexion.createStatement();) {                  
            ResultSet resultado = argumento.executeQuery(consulta); 
                       
            // Esto funciona solo si la consulta es *
//            while (resultado.next()){            
//                System.out.print("EL id es : "+resultado.getInt("id"));
//                System.out.print(", EL nombre es : "+resultado.getString("Nombre"));
//                System.out.print(", EL genero es : "+resultado.getString("Genero"));
//                System.out.print(", la fecha de lanzamiento es : "+resultado.getDate("FechaLanzamiento"));
//                System.out.print(", La compañia es : "+resultado.getString("Compañia"));
//                System.out.println(", EL precio es : "+resultado.getFloat("Precio"));                
//            }  

            ResultSetMetaData  metadatos = resultado.getMetaData();
            int numeroColumnas = metadatos.getColumnCount();
            
            while (resultado.next()){              
                for (int i = 0; i < numeroColumnas; i++) {                  
                    String nombreColumna = metadatos.getColumnLabel(i+1);
                    String tipoDato = metadatos.getColumnTypeName(i+1);
                    Object valor = resultado.getObject(i+1);
                    Videojuego.muestraDato(nombreColumna, tipoDato, valor);
                }
                System.out.println("");
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();           
        }       
    }
    
    /**
     * Método auxiliar para mostrar por pantalla datos de una columna de un Resulset  
     * @param nombreCol  label de la columna
     * @param tipoDato tiposql del Object
     * @param valor Object con el valor
     */
    private static void muestraDato(String nombreCol, String tipoDato, Object valor){
        if(valor == null){
            System.out.print(nombreCol+": valor nulo, ");
        }else if (tipoDato.equals("VARCHAR")){
            System.out.print(nombreCol+": "+( (String)valor) +", ");
        }else if (tipoDato.equals("DATE")){
            System.out.print(nombreCol+": "+( (Date)valor) +", ");
        }else if (tipoDato.equals("DECIMAL")){
            System.out.print(nombreCol+": "+( (BigDecimal)valor) +", ");
        }else if (tipoDato.equals("INT")){
            System.out.print(nombreCol+": "+( (int)valor) +", ");
        }
        
        
        
    }
    
    /**
     * Mñetodo que inserta un nuevo videojuego en la BBDD
     * @param nombre
     * @param genero
     * @param fechaLanzamiento
     * @param compañia
     * @param precio
     * @return
     */
    static public boolean nuevoRegistro(String nombre,String genero, LocalDate fechaLanzamiento,
            String compañia, float precio){
        boolean correcto = false;
//        String fecha = fechaLanzamiento.getYear()+"-"+fechaLanzamiento.getMonthValue()+"-"
//                +fechaLanzamiento.getDayOfMonth();
        precio = Math.round(precio*100)/100f;  // redondeamos a dos decimales
        String query = "INSERT INTO videojuegos (Nombre,genero,FechaLanzamiento,Compañia,Precio)" 
                    + "VALUES(?,?,?,?,?)";
        
//        String query = "INSERT INTO videojuegos (Nombre,genero,FechaLanzamiento,Compañia,Precio)" 
//                    + "VALUES('"+nombre+"','"+genero+"','"+fecha+"', '"+compañia+"',"+precio+")";
        
        try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);
                PreparedStatement argumento = miConexion.prepareStatement(query);) {
            argumento.setString(1, nombre);
            argumento.setString(2, genero);
            argumento.setDate(3, java.sql.Date.valueOf(fechaLanzamiento) );
            argumento.setString(4, compañia);
            argumento.setFloat(5, precio);
                       
            correcto = argumento.executeUpdate() == 1;
        
        } catch (SQLException ex) {
            ex.printStackTrace();           
        }       
        return correcto;
    }
    
    
    
    
}
