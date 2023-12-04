
package com.mycompany.conexionbd_expandido;


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
    // columnas de la tabla videojuegos de la BBDD
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

    /**
     * Controlo que el texto no se más largo de lo que admite la BBDD.
     * @param nombre String
     */
    public void setNombre(String nombre) {
        if(nombre.length()>50)  
            nombre = nombre.substring(0, 50);
        this.nombre = nombre;
    }

    public String getGenero() {       
        return genero;
    }

    /**
     * Controlo que el texto no sea más largo de lo que admite la BBDD, tengo
     * en cuenta ue sea null pq la BBDD lo admite 
     * @param genero Strng
     */
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

    /**
     * Controlo que el texto no sea más largo de lo que admite la BBDD, tengo
     * en cuenta que sea null pq la BBDD lo admite 
     * @param compañia
     */
    public void setCompañia(String compañia) {
        if(compañia != null && genero.length()>50)
            compañia = compañia.substring(0, 50);
        this.compañia = compañia;
    }

    public Float getPrecio() {
        return precio;
    }

    /**
     * El precio puede ser null (por eso uso Float en luga de float) y en la BBDD
     * solo admite dos decimales.
     * @param precio
     */
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
        try (Connection miConexion = PoolDeConexiones.getConexion();
                PreparedStatement argumento = miConexion.prepareStatement(query);) {
            argumento.setString(1, nombre);  
            ResultSet resultado = argumento.executeQuery();
            existe=resultado.next(); // con que haya un solo resultado me vale.
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
        
        try (Connection miConexion = PoolDeConexiones.getConexion();
                Statement argumento = miConexion.createStatement();) {
            // ejecuto la consulta y la guardo en un resulset.
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
            
            // en los metadatos de la consulta se guarda la infomración que no es 
            // los valores de las columnas de las tablas.
            ResultSetMetaData  metadatos = resultado.getMetaData();
            // consigo el número de columnas por el que se ha consultado
            int numeroColumnas = metadatos.getColumnCount();
            
            // mientras haya una fila nueva trabajo
            while (resultado.next()){
                // según la cantidad de columnas que tenga la consulta itero por cada una 
                for (int i = 0; i < numeroColumnas; i++) {  
                    // recogo el nombre de los metadadtos de ese nº de columna
                    String nombreColumna = metadatos.getColumnLabel(i+1);
                    // recojo el tipo de dato de los metadatos de ese número de columna
                    String tipoDato = metadatos.getColumnTypeName(i+1);
                    // obtengo el valor del resultado del esa fila y el indice de la columna
                    // en sql no empieza en 0 sino 1.
                    Object valor = resultado.getObject(i+1);
                    // llamo método que muestra por pantalla los datos.
                    Videojuego.muestraDato(nombreColumna, tipoDato, valor);
                }
                System.out.println(""); // TRas cada linea pongo un salto de linea
            } 
            // si la conexión no es null la cierro.
            if(!PoolDeConexiones.conexionEsNull())
                    PoolDeConexiones.cerrarConexion();
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
        
        // según sea el tipo de dato hay que castearlo de una forma u otra para mostrarlo
        if(valor == null){
            System.out.print(nombreCol+": valor nulo, ");
        }else if (tipoDato.equals("VARCHAR")){
            System.out.print(nombreCol+": "+( (String)valor) +", ");
        }else if (tipoDato.equals("DATE")){  // mysql devuelve Date no LocalDate
            System.out.print(nombreCol+": "+( (Date)valor) +", ");
        }else if (tipoDato.equals("DECIMAL")){  // el tipo de la BBDD es BigDecimal
            System.out.print(nombreCol+": "+( (BigDecimal)valor) +", ");
        }else if (tipoDato.equals("INT")){  // esto es para el id, comentar si no lo quiero mostrar
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
        
            try (Connection miConexion = PoolDeConexiones.getConexion();  // método que me coje una conexión del pool
                    PreparedStatement argumento = miConexion.prepareStatement(query);) {  // creo un prepared statement
                argumento.setString(1, nombre);  // seteo el nombre 1er ?
                argumento.setString(2, genero);  // seteo el genero 2º ?
                argumento.setString(4, compañia);// seteo compañía 4º ?
                // para los set string no hay problema si el string es null.
                
                if(fechaLanzamiento != null)  // la fecha puede ser null en la BBDD
                    // si no es null seteo la fecha en la 3a ?
                    argumento.setDate(3, java.sql.Date.valueOf(fechaLanzamiento));
                else  // si es null seteo a null un dato tipo DATE de sql
                    argumento.setNull(3, java.sql.Types.DATE);
                if(precio !=null)  // el precio puede ser null
                    // si no es null lo seteo -- ya lo redondee arriba
                    argumento.setFloat(5, precio);
                else  // si es null envio un sql Float en null
                    argumento.setNull(5, java.sql.Types.FLOAT);
                
                // ejecuto la consulta, si se modifica una fila (es un insert de una fila) es correcto
                correcto = argumento.executeUpdate() == 1;
                
                // si la conexion no es null la cierro, dudo de si el try con recursos cerrara la conexio
                // al traerla con un método y no crearla directamente en el try
                if(!PoolDeConexiones.conexionEsNull())
                    PoolDeConexiones.cerrarConexion();
                           
            } catch (SQLException ex) {
                ex.printStackTrace();           
            }             
        }            
        return correcto;  // para informar si el insert se ha realziado con éxitoo o no.
    }
    
    /**
     * Pide al usuario los datos de un nuevo videojuego por consola, los asigna al objeto
     * Videojuego y si todo es correcto lo añade a la BBDD
     * @return True -> Videojuego  añadido con éxito a la BBDD, False -> caso contrario.
     */
    public boolean nuevoRegistro(){
         
        System.out.println("-----------------------------------------------");
        System.out.println("introduzca los datos de un nuevo juego.");
        
        this.setNombre(pedirNombre());  // metodo que pide y devuelve nombre
        String cadena =pedirDatoString("Genero"); // método que pide y devuelve genero
        this.setGenero(cadena);    // lo he tenido qye gacer en dos pasos pq en uno falla si es null 
        this.setFechaLanzamiento(pedirFechaPublicacion());   // método que pide y devuelve fecha
        cadena = pedirDatoString("Compañia"); // lo mismo que para genero
        this.setCompañia(cadena); 
        this.setPrecio(pedirPrecio()); // mñetodo que pide y devuelve precio
        
        // ya tengo un método booleano que hace in insert desde todos los parámetros así
        // que lo llamo con los datos recabdos y retorno si es exitoso o o no
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
            nombre = nombre.trim(); // para eliminar espacios en blanco delante o detrás
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
        valor = valor.trim();  // para eliminar espacios en blanco delante o detrás
        if( valor == null || valor.length()==0)
            valor=null;  // si no pone nada considero que no quiere poner nada y seteo a null
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
        LocalDate fecha= null; // incializo a null, si no s establece se devuelve el null
        int año=0,mes=0,dia=0;
        do{  // bucle mientras el usuario no introduzca un año valido o 0 para decir que ni quiere
             // poner fecha.
            System.out.println("Ahota vamos a introducir la fecha, introduzca 0 "
                    + "para omitir este paso.");
            System.out.print("Año -> "); 
            
            try{  // compruebo el entero a ver si es un año valido
                año = teclado.nextInt();  // no admito años anteriores a 80 ni futuros
                if(año != 0 && (año<1980 || año > LocalDate.now().getYear()) ){
                    System.out.println("El año "+año+" no es válido");                               
                }
                // si hay excepcion por que no mete un entero controlo y sigue el bucle
            }catch (InputMismatchException ex){
                System.out.println("Error, debe introducir un año valido o cero para saltar fecha");
                año=-1; // año no válido
            }           
        }while (año != 0 && ( año<1980 || año >= LocalDate.now().getYear()) );
         
        if(año !=0){  // si el año no es 0 es que eligio introducir fecha, ya no hay vuelta atrás
            do{
                System.out.print("Mes (1-12) -> ");
                               
                try{  // controlo que se introduzca un dato válido
                    mes = teclado.nextInt();
                }catch (InputMismatchException ex){                  
                    mes = -1; // mes no válido
                }
                if (mes<=0 || mes >12)
                    System.out.println("Error debe introducir un mes válido : 1 -12");                
            }while (mes<=0 || mes >12);            
        }
        
        if(año !=0){  // si el año se estableció se pidio mes y ahora se pide dia
            boolean fechaCorrecta;  // guardo si la fecha completa es correcta
            
            do{                   
                System.out.println("Día ( 1- 28,29,30 o 31) -> ");
                
                //DateTimeFormatter miFormto = DateTimeFormatter.ofPattern("yyy-MM-dd");
                try{
                    dia = teclado.nextInt();
                    fecha = LocalDate.of(año,mes,dia); // parseo la fecha
                    // asi compruebo que sea fecha válida (dias mes bisiestos y demás)
                    fechaCorrecta = true; // si llego aquí es que todo está bien
                }catch (DateTimeException ex){ // si el dia no concuerda con el mes y año
                    fechaCorrecta = false;
                    System.out.println("El día "+dia+" no es válido para el año "+año+" y mes "+mes);
                }catch (InputMismatchException ex){  // si introducen un valor no entero 
                    fechaCorrecta = false;
                    System.out.println("Debe introducirse un numero 1-28, 29, 30 o 31. ");
                }                               
            }while(!fechaCorrecta); // mienrtas la fecha no sea correcta se pide el dia
        }        
        return fecha;  // devuelve fecha correcta o null;                
    }
    
    /**
     * Método que pide el precio, redondea el  precio a dos decimales.
     * Cero es null
     * @return Float con el valor introducido o null
     */
    private Float pedirPrecio(){
        float precio=0;
        Float retorno; // pongo un Float en lugar de floart para que admita null
        Scanner teclado = new Scanner(System.in);
        boolean floatCorrecto; // controlo si es correcto.
        do{    // mientras no sea un precio válido o introduzca un 0      
            System.out.print("Precio ( 0 para omitir ) -> ");
            try{
                precio = teclado.nextFloat();               
                precio = ( Math.round(precio*100))/100f;  // redondeo a dos decimales
                floatCorrecto=true; // si llego aqui esta todo correcto
            }catch (InputMismatchException ex){ // controlo que no metan un número
                floatCorrecto= false;
                teclado.next();
                System.out.println("Precio incorrecto, recuerde : el separador de decimales es la , ");
            }          
        }while (!floatCorrecto);
        if (precio == 0) // si el precio es 0 es pq no quieren pponer precio, null para la BBDD
            retorno = null;
        else
            retorno = precio;
        
        return retorno;
    }
    
    /**
     * Método que elimina de la BBDD jcvd y tabla videojuegos las entradas cuyo 
     * Nombre coincida con el valor introducido.
     * @param nombre
     * @return
     */
    static public boolean eliminarRegistro (String nombre){
        boolean correcto;       
//        String query = "DELETE FROM videojuegos WHERE Nombre = '"+nombre+"'";
        if(nombre == null)     // no puedo borrar un titulo null (titulo es obligtorio)
            correcto = false;  // si introducen null ya no  hago más.
        else{
            String query = "DELETE FROM videojuegos WHERE Nombre = ?";
         
            try (Connection miConexion = PoolDeConexiones.getConexion(); // conexion del pull
                    PreparedStatement argumento = miConexion.prepareStatement(query);) {
                // seteo el nombre:
                argumento.setString(1, nombre);
                // si la consulta modific - elimina alguna fila es que elimino corectamente
                correcto = argumento.executeUpdate()>0;
                // si la conexion no es null la cierro
                if(!PoolDeConexiones.conexionEsNull())
                    PoolDeConexiones.cerrarConexion();
            }catch (SQLException ex) { // controlo las excepciones que lanza lla conexion o el prepared
                ex.printStackTrace();
                correcto=false;
            }                        
        }
        return correcto;
    }

    @Override
    public String toString() {  // no es encesario lo generé solo para probar al princpio.
        return "Videojuego{" + "nombre=" + nombre + ", genero=" + genero + ", fechaLanzamiento=" + fechaLanzamiento + ", compa\u00f1ia=" + compañia + ", precio=" + precio + '}';
    }
    
}
    
    

