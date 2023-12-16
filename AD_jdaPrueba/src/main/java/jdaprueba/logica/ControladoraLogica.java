
package jdaprueba.logica;

import jdaprueba.persistencia.ControladoraPersistencia;

/**
 *
 * @author anrod
 */
public class ControladoraLogica {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void crearAlumno(Alumno alu){
        
        controlPersis.crearAlumno(alu);
    }
    
}
