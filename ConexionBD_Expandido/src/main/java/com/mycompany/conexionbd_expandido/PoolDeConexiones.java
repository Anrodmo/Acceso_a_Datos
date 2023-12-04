
package com.mycompany.conexionbd_expandido;


import static com.mycompany.conexionbd_expandido.ConexionBD_Expandido.DB_URL;
import static com.mycompany.conexionbd_expandido.ConexionBD_Expandido.PASS;
import static com.mycompany.conexionbd_expandido.ConexionBD_Expandido.USER;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

/**
 * Clase con atributos staticos para crear un pull de conexiones y conseguir conexion
 * al pool
 * @author anrod
 */
public class PoolDeConexiones {
    
    // variable estática donde genero un pool de conexiones sin configurar
    static private PoolDataSource poolDeConexiones = PoolDataSourceFactory.getPoolDataSource();
    // variable estática donde voy a guardar la conexión que vaya creando.
    static private Connection conexion;

    /**
     * Método que  devuelve una conexión reciñen creada del pool de conexiones
     * @return Objeto Conecction con una conexin del pool
     * @throws SQLException Error al crear la conexión
     */
    public static Connection getConexion() throws SQLException {
        return conexion= poolDeConexiones.getConnection();
    }
    
    /**
     * Método estático que setea el pool de conexiones a las variables globlales
     * y usa driver mysql para que conecte a la BBDD. Según se va seteando lo coteja
     * con la BBDD (cada mñetodo puede generar excepcion)
     * @throws SQLException Error de conexion con la BBDD al setear el pool
     */
    static public void inciarPool() throws SQLException{
        
        poolDeConexiones.setConnectionFactoryClassName("com.mysql.cj.jdbc.Driver");
        poolDeConexiones.setURL(DB_URL);
        poolDeConexiones.setUser(USER);
        poolDeConexiones.setPassword(PASS);
        poolDeConexiones.setMaxIdleTime(5);  // tiempo de espera para inactivo esperando 
                                               // esperando conexiones.
    }
    
    /**
     * Mñetodo que cierra la conexión actual almacenada en pool
     * @throws SQLException
     */
    static public void cerrarConexion() throws SQLException{
        conexion.close();
    }
    
    /**
     * Mletodo booleano que me informa de si la conexión actual es nula.
     * Se usa para verificar si cerrarConexión()
     * @return True --> conexion es null, False en caso contrario
     */
    static public boolean conexionEsNull(){
        return conexion == null;
    }
    
}
