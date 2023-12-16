
package jdaprueba.persistencia;

import jdaprueba.logica.Alumno;


public class ControladoraPersistencia {
    
    AlumnoJpaController alumnoJPA = new AlumnoJpaController();

    public void crearAlumno(Alumno alu) {
          alumnoJPA.create(alu);
    }
    
}
