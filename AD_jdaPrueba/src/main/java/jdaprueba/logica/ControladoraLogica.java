
package jdaprueba.logica;

import java.util.ArrayList;
import java.util.LinkedList;
import jdaprueba.persistencia.ControladoraPersistencia;

/**
 * Coon esta clase recogeremos los datos de la interfaz gráfica (en este ejemplo 
 * el main) y los trasladaremos a la controladora de la persistencia
 * @author anrod
 */
public class ControladoraLogica {
    // Creamos un objeto de la controladora de persistencia para manejar la 
    // transferencia de datos
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    
    // =========== Alumno =================
    public void crearAlumno(Alumno alu){       
        controlPersis.crearAlumno(alu);
    }
    
    public void eliminarAlumno(int id){
        controlPersis.eliminarAlumno(id);
    }
    
    public void editarAlumno(Alumno alu){
        controlPersis.editarAlumno(alu);
    }
    
    public Alumno traerAlumno(int id){
        return controlPersis.traerAlumno(id);
    }
    
    public ArrayList<Alumno> traerListaAlumnos(){
        return controlPersis.traerListaAlimnos();
    }
    
    // =========== Carrera =================
    
    public void crearCarrera(Carrera carr){    
        controlPersis.crearCarrera(carr);
    }
    
    public void eliminarCarrra(int id){
        controlPersis.eliminarCarrera(id);
    }
    
    public void editarCarrera(Carrera carr){
        controlPersis.editarCarrera(carr);
    }
    
    public Carrera traerCarrera(int id){
        return controlPersis.traerCarrera(id);
    }
    
    public ArrayList<Carrera> traerListaCarreras(){
        return controlPersis.traerListaCarreras();
    }
    
    // =========== Materia =================
    
    public void crearMateria(Materia materia){     
        controlPersis.crearMateria(materia);
    }
    
    public void eliminarMateria(int id){
        controlPersis.eliminarMateria(id);
    }
    
    public void editarMateria(Materia materia){
        controlPersis.editarMateria(materia);
    }
    
    public Materia traerMateria(int id){
        return controlPersis.traerMateria(id);
    }
    
    public LinkedList<Materia> traerListaMaterias(){
        return controlPersis.traerListaMaterias();
    }
}
