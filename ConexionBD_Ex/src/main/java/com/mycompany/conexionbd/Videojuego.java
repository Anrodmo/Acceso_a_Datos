
package com.mycompany.conexionbd;

import static com.mycompany.conexionbd.ConexionBDEx.DB_URL;
import static com.mycompany.conexionbd.ConexionBDEx.PASS;
import static com.mycompany.conexionbd.ConexionBDEx.USER;
import java.math.BigDecimal;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
Si quiero recuperar las claves generadas en un insert, si es autogenerado 
se puede con el método  
PreparedStatement argumento = miConexion.prepareStatement(query , 
    Preparedstatement.RETURN_GEENERATED_KEYS );
y para recuperar:
ResultSet resultado = argumento.getGeneratedKeys();
*/


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
        if(genero != null && genero.length()>25)
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
        if(compañia != null && genero.length()>50)
            compañia = compañia.substring(0, 50);
        this.compañia = compañia;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        if(precio!= null)
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
     * Método  que lanza la consulta introducida y la muestra por pantalla.
     * @param consulta String
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
     * Método que inserta un nuevo videojuego en la BBDD
     * @param nombre String not null
     * @param genero String
     * @param fechaLanzamiento LocalDate
     * @param compañia String   
     * @param precio Float
     * @return True -> Operacion correcta, False -> caso contrario.
     */
    static public boolean nuevoRegistro(String nombre,String genero, LocalDate fechaLanzamiento,
            String compañia, Float precio){
        boolean correcto = false;

        if(nombre != null){  // en la  BBDD nombre es  obligatorio por lo tanto si viene null ni intamos el insert
            if(precio != null)
                precio = Math.round(precio*100)/100f;  // redondeamos a dos decimales
//            String query = "INSERT INTO videojuegos (Nombre,genero,FechaLanzamiento,Compañia,Precio)" 
//                        + "VALUES(?,?,?,?,?)";
            
                String query = "INSERT INTO videojuegos " 
                            + "VALUES(null,?,?,?,?,?)";
        
    //        String query = "INSERT INTO videojuegos (Nombre,genero,FechaLanzamiento,Compañia,Precio)" 
    //                    + "VALUES('"+nombre+"','"+genero+"','"+fecha+"', '"+compañia+"',"+precio+")";
        
            try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);
                    PreparedStatement argumento = miConexion.prepareStatement(query);) {
                argumento.setString(1, nombre);
                argumento.setString(2, genero);
                argumento.setString(4, compañia);
                if(fechaLanzamiento != null)
                    argumento.setDate(3, java.sql.Date.valueOf(fechaLanzamiento));
                else
                    argumento.setNull(3, java.sql.Types.DATE);
                if(precio !=null)
                    argumento.setFloat(5, precio);
                else
                    argumento.setNull(5, java.sql.Types.FLOAT);
                correcto = argumento.executeUpdate() == 1;
            } catch (SQLException ex) {
                ex.printStackTrace();           
            }                 
        }            
        return correcto;
    }
    
    /**
     * Pide al usuario los datos de un nuevo videojuego por consola, los asigna al objeto
     * Videojuego y si todo es correcto lo añade a la BBDD
     * @return True -> Videojuego  añadido con éxito a la BBDD, False -> caso contrario.
     */
    public boolean nuevoRegistro(){
         
        System.out.println("-----------------------------------------------");
        System.out.println("introduzca los datos de un nuevo juego.");
        
        this.setNombre(pedirNombre());  
        String cadena =pedirDatoString("Genero");
        this.setGenero(cadena);    
        this.setFechaLanzamiento(pedirFechaPublicacion());  
        this.setGenero(pedirDatoString("Compañia")); 
        this.setPrecio(pedirPrecio());
               
        return Videojuego.nuevoRegistro(this.nombre, this.genero, 
                this.fechaLanzamiento, this.compañia, this.precio);
    }
    
    /**
     * Método que pide el nombre del videojuego, al ser campo obligatorio reitera hasta
     * que se introduce uno.
     * @return String valor
     */
    private String pedirNombre (){
        String  nombre;
        Scanner teclado = new Scanner(System.in);       
        do{           
            System.out.print("Nombre del juego ->");
            nombre=teclado.nextLine();
            nombre = nombre.trim();
            if(nombre == null || nombre.length() == 0)
                System.out.println("El nombre es obligatorio");                     
        }while (nombre == null || nombre.length() == 0);
        return nombre;
    }
    
    /**
     * Método auxiliar que pide un dato de texto por teclado y lo devuelve
     * @param dato String , el dato a a pedir
     * @return String valor introducido por teclado  o null si no se introduce nada
     */
    private String pedirDatoString (String dato){
        String valor;
        Scanner teclado = new Scanner(System.in);
        
        System.out.print(dato+" -> ");
        valor=teclado.nextLine();
        valor = valor.trim();
        if( valor == null || valor.length()==0)
            valor=null;
        return valor;
    }
    
    /**
     * Método auxiliar que pide la fecha de publicación y comprueba que es válida.
     * Para ser válida debe ser 1980+ (es  un videojuego) y anterior a año actual
     * y la fecha debe ser una fecha  válida. Devuelve null si el usuario no la quiere
     * establecer.
     * @return LocalDate con la fecha de publicacion o null
     */
    private  LocalDate pedirFechaPublicacion(){
        Scanner teclado = new Scanner(System.in);
        LocalDate fecha= null;
        int año=0,mes=0,dia;
        do{            
            System.out.println("Ahota vamos a introducir la fecha, introduzca 0 "
                    + "para omitir este paso.");
            System.out.print("Año -> "); 
            
            try{
                año = teclado.nextInt();
                if(año != 0 && (año<1980 || año > LocalDate.now().getYear()) ){
                    System.out.println("El año "+año+" no es válido");                               
                }           
            }catch (InputMismatchException ex){
                System.out.println("Error, debe introducir un año o 'intro' para saltar fecha");
                año=0;
            }           
        }while (año != 0 && ( año<1980 || año > LocalDate.now().getYear()) );
         
        if(año !=0){
            do{
                System.out.print("Mes (1-12) -> ");
                mes = teclado.nextInt();
                if (mes<0 || mes >12)
                    System.out.println("El mes nº "+mes+" no es válido.");                
            }while (mes<0 || mes >12);            
        }
        
        if(año !=0){
            boolean fechaCorrecta;
            do{                   
                System.out.println("Día (1-29,30 o 31) -> ");
                dia = teclado.nextInt();
                //DateTimeFormatter miFormto = DateTimeFormatter.ofPattern("yyy-MM-dd");
                try{
                fecha = LocalDate.of(año,mes,dia);
                fechaCorrecta = true;
                }catch (DateTimeException ex){
                    fechaCorrecta = false;
                    System.out.println("El día "+dia+" no es válido para el año "+año+" y mes "+mes);
                }                               
            }while(!fechaCorrecta);
        }        
        return fecha;                  
    }
    
    /**
     * Método que pide el precio, redondea el  precio a dos decimales.
     * Cero es null
     * @return Float con el valor introducido o null
     */
    private Float pedirPrecio(){
        float precio=0;
        Float retorno;
        Scanner teclado = new Scanner(System.in);
        boolean floatCorrecto;
        do{          
            System.out.print("Precio ( 0 para omitir ) -> ");
            try{
                precio = teclado.nextFloat();               
                precio = ( Math.round(precio*100))/100f;
                floatCorrecto=true;
            }catch (InputMismatchException ex){
                floatCorrecto= false;
                teclado.next();
                System.out.println("Precio incorrecto, recuerde : el separador de decimales es la , ");
            }          
        }while (!floatCorrecto);
        if (precio == 0)
            retorno = null;
        else
            retorno = precio;
        return retorno;
    }
    
    /**
     * Método que elimina de la BBDD jcvd y tabla videojuegos las entradas cuyo 
     * Nomnre coincida con el valor introducido.
     * @param nombre
     * @return
     */
    static public boolean eliminarRegistro (String nombre){
        boolean correcto;       
//        String query = "DELETE FROM videojuegos WHERE Nombre = '"+nombre+"'";
        if(nombre == null)     // dado que Nombre es obligatorio si se introduce 
            correcto = false;  // null ya no  hago más.
        else{
            String query = "DELETE FROM videojuegos WHERE Nombre = ?";
         
            try (Connection miConexion = DriverManager.getConnection(DB_URL,USER,PASS);
                    PreparedStatement argumento = miConexion.prepareStatement(query);) {
                argumento.setString(1, nombre);
                correcto = argumento.executeUpdate()>0;            
            }catch (SQLException ex) {
                ex.printStackTrace();
                correcto=false;
            }                        
        }
        return correcto;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "nombre=" + nombre + ", genero=" + genero + ", fechaLanzamiento=" + fechaLanzamiento + ", compa\u00f1ia=" + compañia + ", precio=" + precio + '}';
    }
    
    
    
    
}
