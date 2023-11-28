
package com.mycompany.conexionbd;

import static com.mycompany.conexionbd.ConexionBDEx.DB_URL;
import static com.mycompany.conexionbd.ConexionBDEx.PASS;
import static com.mycompany.conexionbd.ConexionBDEx.USER;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;


public class PoolDeConexiones {
    
    static private PoolDataSource poolDeConexiones = PoolDataSourceFactory.getPoolDataSource();
 
    static private Connection conexion;

    public static Connection getConexion() throws SQLException {
        return conexion= poolDeConexiones.getConnection();
    }
    
    static public void inciarPool() throws SQLException{
        
        poolDeConexiones.setConnectionFactoryClassName("com.mysql.cj.jdbc.Driver");
        poolDeConexiones.setURL(DB_URL);
        poolDeConexiones.setUser(USER);
        poolDeConexiones.setPassword(PASS);
        poolDeConexiones.setMaxIdleTime(5);  // tiempo de espera para inactivo esperando 
                                               // esperando conexiones.
    }
    
    static public void cerrarConexion() throws SQLException{
        conexion.close();
    }
    
}
